package service;

import entity.student;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    void addStudent(student student);
    Optional<student> getStudentById(int id);
    List<student> getAllStudents();
    void updateStudent(student student);
    void deleteStudent(int id);
}
