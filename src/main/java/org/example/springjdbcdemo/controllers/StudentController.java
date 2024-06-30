package org.example.springjdbcdemo.controllers;

import jakarta.validation.Valid;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "Student", description = "Student endpoints")
public class StudentController {

    //TODO:Security??

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/getStudents", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve all students", description = "Read data from the database for the given id")
    public List<Student> read() {
        return this.studentService.getStudents();
    }

    @GetMapping(value = "/getStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve a students data using an ID", description = "Read data from the database")
    public Student read(
            @PathVariable("id")
            @Parameter(description = "The ID of the data to read", required = true, example = "101")
            long id
    ) {
        return this.studentService.getStudent(id);
    }

    @PostMapping(value = "/addStudents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add a student", description = "Store data in the database")
    public ResponseEntity<String> write(@Valid @RequestBody Student request) {
        this.studentService.addStudent(request);
        return ResponseEntity.ok("Student added successfully");
    }

    @GetMapping(value = "/removeStudent/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Remove a student using an ID", description = "Remove data in the database for the given id")
    public void remove(
            @PathVariable("id")
            @Parameter(description = "The ID of the data to remove", required = true, example = "101")
            long id
    ) {
        this.studentService.removeStudent(id);
    }
}
