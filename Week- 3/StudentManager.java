import java.io.*;
import java.util.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private HashMap<Integer, Student> studentMap = new HashMap<>();
    private TreeSet<Student> sortedStudents = new TreeSet<>();

    // add a new student
    public boolean addStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            return false;
        }
        students.add(student);
        studentMap.put(student.getId(), student);
        sortedStudents.add(student);
        return true;
    }

    // remove student by ID
    public boolean removeStudent(int id) {
        Student student = studentMap.remove(id);
        if (student != null) {
            students.remove(student);
            sortedStudents.remove(student);
            return true;
        }
        return false;
    }

    // update student details
    public boolean updateStudent(int id, String name, int age, String grade, String address) {
        Student student = studentMap.get(id);
        if (student != null) {
            sortedStudents.remove(student);
            student.setName(name);
            student.setAge(age);
            student.setGrade(grade);
            student.setAddress(address);
            sortedStudents.add(student);
            return true;
        }
        return false;
    }

    // search student by ID
    public Student searchStudent(int id) {
        return studentMap.get(id);
    }

    // display all sorted students
    public void displayAllStudents() {
        if (sortedStudents.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student student : sortedStudents) {
            System.out.println(student);
        }
    }

    // load students from file
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            students = (ArrayList < Student>) ois.readObject();
            for (Student student : students) {
                studentMap.put(student.getId(), student);
                sortedStudents.add(student);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous data loaded.");
        }
    }

    // save students to file
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Failed to save data.");
        }
    }
}
