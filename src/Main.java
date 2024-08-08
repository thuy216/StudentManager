import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        System.out.println("Click on the number of students:");
        n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= n; i++) {
            input();
        }

        boolean running = true;
        while (running) {
            System.out.println("1. Print student list");
            System.out.println("2. Delete student by code");
            System.out.println("3. Sort students by score in descending order");
            System.out.println("4. Find students by code or name");
            System.out.println("5. Find students with score >= x");
            System.out.println("6. Exit");
            System.out.print("Select function: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    output();
                    break;
                case 2:
                    System.out.print("Enter the student code to delete: ");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 3:
                    sortByGradeDesc();
                    break;
                case 4:
                    System.out.print("Enter student code or name: ");
                    String keyword = scanner.nextLine();
                    Student student = findByCodeOrName(keyword);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("No students found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter minimum score: ");
                    float grade = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    List<Student> filteredStudents = filterByGrade(grade);
                    if (filteredStudents.isEmpty()) {
                        System.out.println("No student found with score >= " + grade);
                    } else {
                        for (Student s : filteredStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please select again.");
                    break;
            }
        }
        scanner.close();
    }

    // Nhap moi 1 sinh vien
    public static void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student information:");

        System.out.println("Enter student code:");
        String code = scanner.nextLine();
        System.out.println("Enter student name:");
        String name = scanner.nextLine();
        System.out.println("Enter student age:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();
        System.out.println("Enter gender (0: Male, 1: Female):");
        int gender = scanner.nextInt();
        System.out.println("Enter score:");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Student student = new Student(name, age, email, phone, code, gender, grade);
        studentList.add(student);
    }

    // Print student list
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Delete students by code
    public static void removeByCode(String code) {
        boolean removed = studentList.removeIf(student -> student.getCode().equals(code));
        if (removed) {
            System.out.println("Students have codes " + code + " skin rub");
        } else {
            System.out.println("No student found with code" + code);
        }
    }

    // Sort students by score in descending order
    public static void sortByGradeDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
        System.out.println("The list of students has been sorted by descending score.");
    }

    // Find students by code or name
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equals(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Find students with score >= x
    public static List<Student> filterByGrade(float x) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getGrade() >= x) {
                result.add(student);
            }
        }
        return result;
    }
}
