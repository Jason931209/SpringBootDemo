package org.example.springjdbcdemo.repo;

import org.example.springjdbcdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepo {

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private JdbcTemplate jdbc;

    public void save(Student student) {
        String sql = "insert into student(rollno, name, marks) values (?,?,?)";
        jdbc.update(sql, student.rollNo(), student.name(), student.marks());
    }

    public void remove(Long id) {
        String sql = "delete from student where rollno = ?";
        jdbc.update(sql, id);
    }

    public List<Student> findAll() {
        String sql = "select * from student";

        return jdbc.query(sql, (rs, rowNum) ->
            new Student(
                    rs.getInt("rollno"),
                    rs.getString("name"),
                    rs.getInt("marks")
            )
        );
    }

    public Optional<Student> find(Long id) {
        String sql = "select * from student where rollno = ?";

        return jdbc.query(sql, (rs, rowNum) ->
            new Student(
                    rs.getInt("rollno"),
                    rs.getString("name"),
                    rs.getInt("marks")
            ), id).stream().findFirst();
    }
}
