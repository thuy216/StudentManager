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
        System.out.println("Nhap vao so luong sv:");
        n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 1; i <= n; i++) {
            input();
        }

        boolean running = true;
        while (running) {
            System.out.println("1. In danh sach sinh vien");
            System.out.println("2. Xoa sinh vien theo ma");
            System.out.println("3. Sap xep sinh vien theo diem giam dan");
            System.out.println("4. Tim sinh vien theo ma hoac ten");
            System.out.println("5. Tim sinh vien co diem >= x");
            System.out.println("6. Thoat");
            System.out.print("Chon chuc nang: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    output();
                    break;
                case 2:
                    System.out.print("Nhap ma sinh vien can xoa: ");
                    String code = scanner.nextLine();
                    removeByCode(code);
                    break;
                case 3:
                    sortByGradeDesc();
                    break;
                case 4:
                    System.out.print("Nhap ma hoac ten sinh vien: ");
                    String keyword = scanner.nextLine();
                    Student student = findByCodeOrName(keyword);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Khong tim thay sinh vien.");
                    }
                    break;
                case 5:
                    System.out.print("Nhap diem toi thieu: ");
                    float grade = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    List<Student> filteredStudents = filterByGrade(grade);
                    if (filteredStudents.isEmpty()) {
                        System.out.println("Khong tim thay sinh vien co diem >= " + grade);
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
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }
        scanner.close();
    }

    // Nhap moi 1 sinh vien
    public static void input() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap vao thong tin sinh vien:");

        System.out.println("Nhap ma sv:");
        String code = scanner.nextLine();
        System.out.println("Nhap ten sv:");
        String name = scanner.nextLine();
        System.out.println("Nhap tuoi sv:");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Nhap email:");
        String email = scanner.nextLine();
        System.out.println("Nhap so dien thoai:");
        String phone = scanner.nextLine();
        System.out.println("Nhap gioi tinh (0: Nam, 1: Nu):");
        int gender = scanner.nextInt();
        System.out.println("Nhap diem:");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        Student student = new Student(name, age, email, phone, code, gender, grade);
        studentList.add(student);
    }

    // In danh sach sinh vien
    public static void output() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    // Xoa sinh vien theo ma
    public static void removeByCode(String code) {
        boolean removed = studentList.removeIf(student -> student.getCode().equals(code));
        if (removed) {
            System.out.println("Sinh vien co ma " + code + " da bi xoa.");
        } else {
            System.out.println("Khong tim thay sinh vien voi ma " + code);
        }
    }

    // Sap xep sinh vien theo diem giam dan
    public static void sortByGradeDesc() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Float.compare(s2.getGrade(), s1.getGrade());
            }
        });
        System.out.println("Danh sach sinh vien da duoc sap xep theo diem giam dan.");
    }

    // Tim sinh vien theo ma hoac ten
    public static Student findByCodeOrName(String keyword) {
        for (Student student : studentList) {
            if (student.getCode().equals(keyword) || student.getName().equalsIgnoreCase(keyword)) {
                return student;
            }
        }
        return null;
    }

    // Tim sinh vien co diem >= x
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
