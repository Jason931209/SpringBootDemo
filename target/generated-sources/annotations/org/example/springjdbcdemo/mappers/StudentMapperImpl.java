package org.example.springjdbcdemo.mappers;

import javax.annotation.processing.Generated;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.model.StudentDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-05T13:56:13+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Microsoft)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentDTO studentToStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        int marks = 0;

        marks = student.marks();

        String firstName = null;

        StudentDTO studentDTO = new StudentDTO( firstName, marks );

        return studentDTO;
    }

    @Override
    public Student studentDTOToStudent(StudentDTO studentDTO) {
        if ( studentDTO == null ) {
            return null;
        }

        int marks = 0;

        marks = studentDTO.marks();

        long rollNo = 0L;
        String name = null;

        Student student = new Student( rollNo, name, marks );

        return student;
    }
}
