package org.example.springjdbcdemo.service;

import org.example.springjdbcdemo.exceptions.NotFound;
import org.example.springjdbcdemo.mappers.StudentMapper;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.model.StudentDTO;
import org.example.springjdbcdemo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public StudentDTO getStudentDTO(Long id) {
        Student student = repo.find(id).orElseThrow(() -> NotFound.of("No student found for Id: " + id));
        return studentMapper.studentToStudentDTO(student);
    }

    @Autowired
    public void setRepo(StudentRepo repo) {
        this.repo = repo;
    }

    private StudentRepo repo;
    public void addStudent(Student student) {
        repo.save(student);
    }

    public List<Student> getStudents() {
        return repo.findAll();
    }

    public Student getStudent(Long id) {
        return repo.find(id)
                .orElseThrow(() -> NotFound.of("No student found for Id: " + id));
    }

    public void removeStudent(Long id) {
        repo.remove(id);
    }
}
