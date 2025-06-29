package dao;

import entity.student;
import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    void save(student student);
    Optional<student> findById(int id);
    List<student> findAll();
    void update(student student);
    void delete(int id);
}
