package ui;

import entity.student;
import java.util.Scanner;
import service.StudentService;
import service.StudentServiceImpl;

public class Main {
    private static StudentService service = new StudentServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Student\n2. View All\n3. Find by ID\n4. Update\n5. Delete\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: addStudent(); break;
                case 2: service.getAllStudents().forEach(System.out::println); break;
                case 3: findStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6: running = false; break;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        service.addStudent(new student(0, name, email, age));
    }

    private static void findStudent() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        service.getStudentById(id).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Student not found")
        );
    }

    private static void updateStudent() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Email: ");
        String email = scanner.nextLine();
        System.out.print("New Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        service.updateStudent(new student(id, name, email, age));
    }

    private static void deleteStudent() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        service.deleteStudent(id);
    }
}
