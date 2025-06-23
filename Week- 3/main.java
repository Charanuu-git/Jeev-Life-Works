import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        manager.loadFromFile("students.dat");

        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit & Save");
            System.out.print("Choose an option: ");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    try {
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        if (manager.searchStudent(id) != null) {
                            System.out.println("Student with this ID already exists.");
                            break;
                        }

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Grade: ");
                        String grade = sc.nextLine().trim();
                        System.out.print("Enter Address: ");
                        String address = sc.nextLine().trim();

                        if (name.isEmpty() || address.isEmpty() || age <= 0) {
                            System.out.println("Invalid input. Please try again.");
                            break;
                        }

                        Student student = new Student(id, name, age, grade, address);
                        if (manager.addStudent(student)) {
                            System.out.println("Student added successfully.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format.");
                    }
                    break;

                case "2":
                    System.out.print("Enter ID to remove: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        if (manager.removeStudent(id)) {
                            System.out.println("Student removed.");
                        } else {
                            System.out.println("Student not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case "3":
                    System.out.print("Enter ID to update: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        if (manager.searchStudent(id) == null) {
                            System.out.println("Student not found.");
                            break;
                        }

                        System.out.print("Enter New Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter New Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter New Grade: ");
                        String grade = sc.nextLine().trim();
                        System.out.print("Enter New Address: ");
                        String address = sc.nextLine().trim();

                        if (name.isEmpty() || address.isEmpty() || age <= 0) {
                            System.out.println("Invalid input.");
                            break;
                        }

                        manager.updateStudent(id, name, age, grade, address);
                        System.out.println("Student updated.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;

                case "4":
                    System.out.print("Enter ID to search: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        Student student = manager.searchStudent(id);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID.");
                    }
                    break;

                case "5":
                    manager.displayAllStudents();
                    break;

                case "6":
                    manager.saveToFile("students.dat");
                    System.out.println("Data saved. Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
