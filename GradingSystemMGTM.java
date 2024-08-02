import java.util.Scanner;

public class GradingSystemMGTM {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        GradingSystem system = new GradingSystem(10, 20, 10);

        System.out.println("Enter the number of students to add:");
        int numStudents = obj.nextInt();
        obj.nextLine(); // Consume newline

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("Student ID: ");
            int studentID = obj.nextInt();
            obj.nextLine(); // Consume newline
            System.out.print("Name: ");
            String name = obj.nextLine();

            Student student = new Student(studentID, name);
            system.addStudent(student);
        }

        System.out.println("Enter the number of grades to add:");
        int numGrades = obj.nextInt();
        obj.nextLine(); // Consume newline

        for (int i = 0; i < numGrades; i++) {
            System.out.println("Enter details for grade " + (i + 1) + ":");
            System.out.print("Student ID: ");
            int studentID = obj.nextInt();
            System.out.print("Course ID: ");
            int courseID = obj.nextInt();
            System.out.print("Grade (A/B/C/D/F): ");
            char grade = obj.next().charAt(0);

            Grade gradeEntry = new Grade(studentID, courseID, grade);
            system.addGrade(gradeEntry);
        }

        System.out.println("Enter the number of courses to add:");
        int numCourses = obj.nextInt();
        obj.nextLine(); // Consume newline

        for (int i = 0; i < numCourses; i++) {
            System.out.println("Enter details for course " + (i + 1) + ":");
            System.out.print("Course ID: ");
            int courseID = obj.nextInt();
            System.out.print("Course Credits: ");
            int credits = obj.nextInt();
            system.addCourseCredits(courseID, credits);
        }

        System.out.print("Enter student ID to calculate GPA: ");
        int searchID = obj.nextInt();
        double gpa = system.calculateGPA(searchID);

        if (gpa != -1) {
            System.out.println("GPA for student ID " + searchID + " is: " + gpa);
        } else {
            System.out.println("Student not found or no grades available.");
        }

        obj.close();
    }
}

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                '}';
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem(int studentSize, int gradeSize, int courseSize) {
        students = new Student[studentSize];
        grades = new Grade[gradeSize];
        courseCredits = new int[courseSize];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Cannot add more students, array is full.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < grades.length) {
            grades[gradeCount] = grade;
            gradeCount++;
        } else {
            System.out.println("Cannot add more grades, array is full.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < courseCredits.length) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    public double calculateGPA(int studentID) {
        int totalCredits = 0;
        int totalPoints = 0;
        boolean studentFound = false;

        for (Grade grade : grades) {
            if (grade != null && grade.getStudentID() == studentID) {
                int credits = courseCredits[grade.getCourseID()];
                int points = gradeToPoints(grade.getGrade());
                totalCredits += credits;
                totalPoints += points * credits;
                studentFound = true;
            }
        }

        if (studentFound && totalCredits > 0) {
            return (double) totalPoints / totalCredits;
        } else {
            return -1;
        }
    }

    public int gradeToPoints(char grade) {
        switch (grade) {
            case 'A':
                return 4;
            case 'B':
                return 3;
            case 'C':
                return 2;
            case 'D':
                return 1;
            case 'F':
                return 0;
            default:
                return -1;
        }
    }
}
