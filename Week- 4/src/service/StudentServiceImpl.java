package service;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import entity.student;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {
    private StudentDAO dao = new StudentDAOImpl();

    public void addStudent(student student) {
        dao.save(student);
    }

    public Optional<student> getStudentById(int id) {
        return dao.findById(id);
    }

    public List<student> getAllStudents() {
        return dao.findAll();
    }

    public void updateStudent(student student) {
        dao.update(student);
    }

    public void deleteStudent(int id) {
        dao.delete(id);
    }
}
