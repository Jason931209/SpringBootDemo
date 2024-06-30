package org.example.springjdbcdemo.controllers;

import org.example.springjdbcdemo.SpringJdbcDemoApplication;
import org.example.springjdbcdemo.exceptions.NotFound;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@ContextConfiguration(classes = {SpringJdbcDemoApplication.class})
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;

    @Autowired
    private WebApplicationContext context;
    @Autowired private MockMvc controllerUnderTest;

    private final long validId = 100L;
    private final long invalidId = -500L;

    private final Student readStudent = new Student(
            100L,
            "Test",
            50
    );

    @BeforeEach
    public void setUp() {
        when(this.studentService.getStudent(validId)).thenReturn(readStudent);
        when(this.studentService.getStudent(invalidId)).thenThrow(NotFound.of("Test Error"));

        this.controllerUnderTest = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();
    }

    @Test
    public void givenInvalidId_whenRead_thenReturnNotFound() throws Exception {
        controllerUnderTest.perform(get("/getStudent/{id}", invalidId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        verify(this.studentService, times(1)).getStudent(invalidId);
    }

    @Test
    public void givenValidId_whenRead_thenReturnOk() throws Exception {
        controllerUnderTest.perform(get("/getStudent/{id}", validId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(this.studentService, times(1)).getStudent(validId);
    }
}
