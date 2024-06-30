package org.example.springjdbcdemo.service;

import org.example.springjdbcdemo.exceptions.NotFound;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.repo.StudentRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock private StudentRepo studentRepo;
    @Mock private StudentService studentService;

    @InjectMocks private StudentService serviceUnderTest;

    private final long existingId = 1L;
    private final long missingId = -100L;
    private final Student studentRecord = new Student(
        1L,
        "test",
        55
    );

    @BeforeEach
    public void setUp() {
        when(studentRepo.find(existingId)).thenReturn(Optional.of(studentRecord));
        when(studentRepo.find(missingId)).thenReturn(Optional.empty());
    }

    @AfterEach
    public void tearDown() {
        reset(studentRepo, studentService);
    }

    @Test
    public void givenInvalidId_whenRead_thenReturnEmptyResult() {
        assertThrows(NotFound.class, () -> serviceUnderTest.getStudent(missingId));
        verify(studentRepo, times(1)).find(missingId);
    }

    @Test
    public void givenValidId_whenRead_thenReturnResult() {
        var result = serviceUnderTest.getStudent(existingId);
        assertEquals(studentRecord, result);
        verify(studentRepo, times(1)).find(existingId);
    }
}
