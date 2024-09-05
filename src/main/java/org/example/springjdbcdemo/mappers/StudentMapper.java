package org.example.springjdbcdemo.mappers;

import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.model.StudentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentDTO studentToStudentDTO(Student student);
    Student studentDTOToStudent(StudentDTO studentDTO);
}
