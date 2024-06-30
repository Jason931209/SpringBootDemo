package org.example.springjdbcdemo.service;

import org.example.springjdbcdemo.exceptions.NotFound;
import org.example.springjdbcdemo.model.Student;
import org.example.springjdbcdemo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

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
