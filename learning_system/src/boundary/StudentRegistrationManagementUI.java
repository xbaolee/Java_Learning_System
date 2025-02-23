package boundary;

/**
 *
 * @author ThyeHan
 */
import entity.*;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentRegistrationManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        printLine(35);
        System.out.println("STUDENT REGISTRATION MANAGEMENT MENU");
        printLine(35);
        System.out.println("1. Add New Student");
        System.out.println("2. Remove a Student");
        System.out.println("3. Amend Student Details");
        System.out.println("4. Search Student for a Registered Courses");
        System.out.println("5. Add Students to a few Courses");
        System.out.println("6. Remove a Student from a Course Registration");
        System.out.println("7. Calculate Fee Paid for Registered Courses");
        System.out.println("8. Filter Students for Courses Based on Criteria");
        System.out.println("9. Generate Summary Report");
        System.out.println("0. Quit");
        printLine(35);
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 9) {
                System.out.println("Invalid choice. Please enter a number between 0 and 9.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println();

        return choice;
    }

    public void printLine(int length) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        System.out.println(line.toString());
    }

    public void printLine(int length, String lineStyle) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append(lineStyle);
        }
        System.out.println(line.toString());
    }

    public void listAllStudents(String outputStr) {
        printLine(137);
        System.out.printf("%-10s %-40s %-25s %-15s\n", "Student Id", "Name", "Date Added", "Courses");
        printLine(137);
        System.out.print(outputStr);
        printLine(137);
    }

    public void listAllCoursesByStudent(String outputStr) {
        printLine(100);
        System.out.printf("%-15s %-40s %-10s %-7s\n", "Course Code", "Name", "Credit Hours", "Fees");
        printLine(100);
        System.out.print(outputStr);
        printLine(100);
    }

    public boolean isValidCourseCode(String code) {
        return code.matches("[A-Z]{4}\\d{4}");
    }

    private boolean isValidCourseCategory(String code) {
        return code.matches("^F[A-Z]{3}$");
    }

    public boolean isValidStudentId(String studentId) {
        return studentId.matches("^\\d{2}[A-Z]{3}\\d{5}$");
    }

    public Student inputStudentDetails() {
        String studentId;
        while (true) {
            System.out.print("Enter student id (e.g., 23WMR12123): ");
            studentId = scanner.nextLine().trim().toUpperCase();
            if (isValidStudentId(studentId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid student id.");
            }
        }
        String name;
        while (true) {
            System.out.print("Enter student name: ");
            name = scanner.nextLine().trim().toUpperCase();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }

        return new Student(studentId, name);
    }

    public Course inputCourseDetails() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g., BACS1234, BAMS1234): ");
            courseCode = scanner.nextLine().trim().toUpperCase();
            if (isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
        String name;
        while (true) {
            System.out.print("Enter course name: ");
            name = scanner.nextLine().trim().toUpperCase();
            if (!name.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }
        String status = getStatus();
        Double fees = 0.0;
        while (true) {
            System.out.print("Enter course fees: ");
            try {
                fees = Double.valueOf(scanner.nextLine());
                if (fees >= 0) {
                    break;
                } else {
                    System.out.println("Fees must be a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return new Course(courseCode, name, status, fees);
    }

    public int getFilterChoice() {
        int filterOption;

        System.out.println("\n");
        printLine(35);
        System.out.println("FILTER STUDENTS FOR COURSES BASED ON CRITERIA");
        printLine(35);
        System.out.println("1. Filter by Course");
        System.out.println("2. Filter by Status");
        System.out.println("3. Back to main menu");
        printLine(35);
        System.out.print("Please enter your filter choice: ");
        filterOption = -1;
        try {
            filterOption = scanner.nextInt();
            if (filterOption < 1 || filterOption > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        return filterOption;
    }

    public int getReportChoice() {
        int filterOption;

        System.out.println("\n");
        printLine(35);
        System.out.println("GENERATE REPORT");
        printLine(35);
        System.out.println("1. Students Report");
        System.out.println("2. Specific Student Registered Course Report");
        System.out.println("3. Back to main menu");
        printLine(35);
        System.out.print("Please enter your choice: ");
        filterOption = -1;
        try {
            filterOption = scanner.nextInt();
            if (filterOption < 1 || filterOption > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        return filterOption;
    }

    public String inputFilterByStatus() {
        String status;
        do {
            System.out.print("Enter status: ");
            status = scanner.nextLine().trim();
            if (status.isEmpty()) {
                System.out.println("Status cannot be empty. Please enter a valid status.");
            }
        } while (status.isEmpty());
        return status;
    }

    public String getStatus() {
        int choice;
        String status = "";

        while (status.isEmpty()) {
            System.out.println("Enter status:");
            System.out.println("1. MAIN");
            System.out.println("2. ELECTIVE");
            System.out.println("3. RESIT");
            System.out.println("4. REPEAT");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                } else {
                    switch (choice) {
                        case 1:
                            status = "MAIN";
                            break;
                        case 2:
                            status = "ELECTIVE";
                            break;
                        case 3:
                            status = "RESIT";
                            break;
                        case 4:
                            status = "REPEAT";
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
        return status;
    }

    public String inputCourseCode() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g.DBMS3433, DSAF2221): ");
            courseCode = scanner.nextLine().trim().toUpperCase();
            if (isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
        return courseCode;
    }

    public String inputStudentId() {
        String studentId;
        while (true) {
            System.out.print("Enter student id (e.g., 23WMR12123): ");
            studentId = scanner.nextLine().trim().toUpperCase();
            if (isValidStudentId(studentId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid student id.");
            }
        }
        return studentId;
    }

    public void displayStudentDetails(Student student) {
        System.out.println("Student Details");
        System.out.println("Student Id: " + student.getStudentInfo().getStudentId());
        System.out.println("Student name: " + student.getStudentInfo().getName());
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayReport(int totalCourses, Student mostCoursesCourse, Student fewestCoursesCourse, Student recentAddedCourse) {
        printLine(70);
        System.out.println("\t\tStudent Registration Management Report");
        printLine(70);
        System.out.println("Total Number of Courses             >> " + totalCourses);
        System.out.println("The student with most courses     >> " + mostCoursesCourse.getStudentInfo().getStudentId() + "\t" + mostCoursesCourse.getCourseCount() + " course(s)");
        System.out.println("The student with fewest courses   >> " + fewestCoursesCourse.getStudentInfo().getStudentId() + "\t" + fewestCoursesCourse.getCourseCount() + " course(s)");
        System.out.println("The recent added course             >> " + recentAddedCourse.getStudentInfo().getStudentId() + "\t" + recentAddedCourse.getStudentInfo().getName());
        printLine(70);

        // Wait for user input before proceeding
        System.out.print("Enter any value to proceed -> ");
        scanner.next();
    }

    public static void printCentered(String text, int width) {
        if (text.length() >= width) {
            System.out.println(text);
        } else {
            int leftPadding = (width - text.length()) / 2;
            int rightPadding = width - text.length() - leftPadding;
            System.out.printf("%" + leftPadding + "s%s%" + rightPadding + "s%n", "", text, "");
        }
    }

    public void waitForEnter() {
        System.out.print("\nPress <ENTER> key to continue...");
        scanner.nextLine();
    }

    public void displayStudentReport(String outputStr, int studentCount, Student highestStudent, Student lowestStudent, Student recentStudent) {
        printLine(137, "=");
        printCentered("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY", 137);
        printCentered("STUDENT REGISTRATION SUBSYSTEM", 137);
        printCentered("STUDENT SUMMARY REPORT", 137);
        printCentered("------------------------------", 137);
        System.out.printf("Generated at: " + LocalDateTime.now() + "\n\n");
        System.out.printf("%-10s %-40s %-25s %-15s\n", "Student Id", "Name", "Date Added", "Courses");
        System.out.printf("%-10s %-40s %-25s %-15s\n", "----------", "----", "----------", "-------");
        System.out.print(outputStr);
        System.out.printf("\nTotal %-2s Students\n", studentCount);
        printLine(137);
        System.out.printf("\nSTUDENT WHO HAD HIGHEST COURSES:\n");
        System.out.printf("\n ->[%-2s COURSES] <%-10s> %-40s\n", highestStudent.getCourses().totalNumberOfObject(), highestStudent.getStudentInfo().getStudentId(), highestStudent.getStudentInfo().getName());
        System.out.printf("\nSTUDENT WHO HAD LOWEST COURSES:\n");
        System.out.printf("\n ->[%-2s COURSES] <%-10s> %-40s\n", lowestStudent.getCourses().totalNumberOfObject(), lowestStudent.getStudentInfo().getStudentId(), lowestStudent.getStudentInfo().getName());
        System.out.printf("\n******************************************\n");
        System.out.printf("\nMOST RECENT ADDED STUDENT:\n");
        System.out.printf("\n ->[%-2s COURSES] <%-10s> %-40s\n", recentStudent.getCourses().totalNumberOfObject(), recentStudent.getStudentInfo().getStudentId(), recentStudent.getStudentInfo().getName());
        printCentered("END OF THE STUDENT COURSE SUMMARY REPORT", 137);
        printLine(137, "=");
        waitForEnter();
    }

    public void displayStudentCourseReport(String courseStr, Student student, int totalCourses, int totalMain, int totalElective, int totalResit, int totalRepeat, double totalFees) {
        printLine(137, "=");
        printCentered("TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY", 137);
        printCentered("STUDENT REGISTRATION SUBSYSTEM", 137);
        printCentered("STUDENT COURSES SUMMARY REPORT", 137);
        printCentered("---------------------------------------", 137);
        System.out.printf("Student Id: " + student.getStudentInfo().getStudentId() + "\n");
        System.out.printf("Student Name: " + student.getStudentInfo().getName() + "\n");
        System.out.printf("Generated at: " + LocalDateTime.now() + "\n\n");
        System.out.printf("%-10s %-40s %12s %15s\n", "Course Code", "Name", "Credit Hours", "Fees");
        System.out.printf("%-10s %-40s %12s %15s\n", "-----------", "----", "------", "---------");
        System.out.print(courseStr);
        System.out.printf("%-10s %-40s %12s %15s\n", "-----------", "", "", "---------");
        System.out.printf("%-10s %-40s %12s %15.2f\n", "Total", "", "", totalFees);
        System.out.printf("%-10s %-40s %12s %15s\n", "-----------", "", "", "---------");
        System.out.printf("\nTotal %-2s Courses: %-2s Main | %-2s Elective | %-2s Resit | %-2s Repeat\n", totalCourses, totalMain, totalElective, totalResit, totalRepeat);
        printLine(137);
        printCentered("END OF THE STUDENT COURSE SUMMARY REPORT", 137);
        printLine(137, "=");
        waitForEnter();
    }
}
