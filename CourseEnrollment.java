import java.util.Scanner;

class Course {
    private int id;
    private String name;
    private int credits;

    public Course(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Credits: " + credits;
    }
}

class Enrollment {
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_COURSES = 10;

    private int[][] studentCourses;
    private int[] courseCount;

    public Enrollment() {
        studentCourses = new int[MAX_STUDENTS][MAX_COURSES];
        courseCount = new int[MAX_STUDENTS];

        for (int i = 0; i < MAX_STUDENTS; i++) {
            for (int j = 0; j < MAX_COURSES; j++) {
                studentCourses[i][j] = -1; // Initialize with -1 indicating no course
            }
        }
    }

    public void enroll(int studentId, int courseId) {
        if (courseCount[studentId] < MAX_COURSES) {
            studentCourses[studentId][courseCount[studentId]++] = courseId;
        } else {
            System.out.println("Cannot enroll more courses for student ID: " + studentId);
        }
    }

    public void drop(int studentId, int courseId) {
        for (int i = 0; i < courseCount[studentId]; i++) {
            if (studentCourses[studentId][i] == courseId) {
                studentCourses[studentId][i] = studentCourses[studentId][--courseCount[studentId]];
                studentCourses[studentId][courseCount[studentId]] = -1;
                break;
            }
        }
    }

    public void viewCourses(int studentId, Course[] catalog) {
        if (courseCount[studentId] == 0) {
            System.out.println("No courses enrolled for student ID: " + studentId);
            return;
        }

        System.out.println("Courses for student ID: " + studentId);
        for (int i = 0; i < courseCount[studentId]; i++) {
            for (Course course : catalog) {
                if (course.getId() == studentCourses[studentId][i]) {
                    System.out.println(course);
                }
            }
        }
    }
}

public class CourseEnrollment {
    private static Course[] catalog = {
        new Course(1, "Math", 3),
        new Course(2, "Physics", 4),
        new Course(3, "Chem", 3),
        new Course(4, "CS", 3)
    };
    
    private static Enrollment enroll = new Enrollment();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Enroll");
            System.out.println("2. Drop");
            System.out.println("3. View Courses");
            System.out.println("4. Exit");
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Student ID: ");
                    int studentId = scanner.nextInt();
                    System.out.print("Course ID: ");
                    int courseId = scanner.nextInt();
                    enroll.enroll(studentId, courseId);
                    System.out.println("Enrolled!");
                    break;

                case 2:
                    System.out.print("Student ID: ");
                    studentId = scanner.nextInt();
                    System.out.print("Course ID: ");
                    courseId = scanner.nextInt();
                    enroll.drop(studentId, courseId);
                    System.out.println("Dropped!");
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    studentId = scanner.nextInt();
                    enroll.viewCourses(studentId, catalog);
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        scanner.close();
    }
}
