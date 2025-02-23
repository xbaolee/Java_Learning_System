/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.*;

import entity.*;
import java.util.Scanner;

/**
 *
 * @author lee xue bao
 */
public class CourseManagementUI {

    private final StudentRegistrationManagementUI studentUI = new StudentRegistrationManagementUI();
    private final TutorialGroupManagementUI tutGrpUI = new TutorialGroupManagementUI();

    Scanner scanner = new Scanner(System.in);

    public int getCourseMenuChoice() {
        System.out.println("===================================================================================");
        System.out.printf("%50s", "COURSE MANAGEMENT MENU\n");
        System.out.println("===================================================================================\n");

        System.out.println("1. Add a new faculty");
        System.out.println("2. Remove faculty");
        System.out.println("3. Add a programme to courses");
        System.out.println("4. Remove a programme from a course");
        System.out.println("5. Add a new course to programmes");
        System.out.println("6. Remove a course from a programme");
        System.out.println("7. Search courses offered in a semester");
        System.out.println("8. Amend course details for a programme");
        System.out.println("9. List all courses");
        System.out.println("10. List all programmes");
        System.out.println("11. List all faculty");
        System.out.println("12. List courses taken by different faculties");
        System.out.println("13. List all courses for a programme");
        System.out.println("14. List all programme for a course");
        System.out.println("15. List all courses for each programme");
        System.out.println("16. List all programmes for each course");
        System.out.println("17. Course Summary Report");
        System.out.println("18. Fee Analysis: Highest and Lowest Fee Courses by Programme Report");
        System.out.println("0. Quit");
        System.out.println("===================================================================================\n");

        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllCourses(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                                   List Of All Courses\n "
                + "\n===================================================================================");
        System.out.printf("%-10s %-40s %12s %15s\n", "Course Id", "Course Name", "Credit Hours", "Fee(RM)");
        System.out.print("\n-----------------------------------------------------------------------------------");

        System.out.println(outputStr);
        System.out.println("\n===================================================================================");

        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public void searchCourses(String outputStr) {
        System.out.println("\n=================================================================================================="
                + "\n\n                                   Course Offered in a Semester\n "
                + "\n==================================================================================================");
        System.out.printf("%-10s %-40s %12s %15s %12s\n", "Course Id", "Course Name", "Credit Hours", "Fee(RM)", "Semester");
        System.out.println(outputStr);
        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
        System.out.println("\n==================================================================================================");

    }

    public String printCourseDetails(Course course) {
        String outputStr = "";
        outputStr += "Course Id: " + course.getCourseCode() + "\n";
        outputStr += "Course Name: " + course.getCourseName() + "\n";
        outputStr += "Credit Hours: " + course.getCreditHour() + "\n";
        outputStr += "Fees: " + course.getFees() + "\n";
        outputStr += "\n-----------------------------------------------------------------------------------";
        return outputStr;
    }

    public String inputCourseCode() {
        String courseCode;
        while (true) {
            System.out.print("Enter course code (e.g.DBMS3433, DSAF2221): ");
            courseCode = scanner.nextLine().trim().toUpperCase();

            if (studentUI.isValidCourseCode(courseCode)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid course code.");
            }
        }
        return courseCode;
    }

    public String inputCourseName() {

        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        return courseName;
    }

    public int inputCreditHour() {

        System.out.print("Enter credit Hours: ");
        int creditHour = scanner.nextInt();
        scanner.nextLine();
        return creditHour;
    }

    public double inputFees() {

        System.out.print("Enter Fees: ");
        double fees = scanner.nextDouble();
        scanner.nextLine();
        return fees;
    }

    public String inputSemester() {

        System.out.print("Enter Semester(Y1S1): ");
        String semester = scanner.nextLine();
        return semester;
    }

    public Course inputCourseDetails() {

        String CourseCode = inputCourseCode();
        String CourseName = inputCourseName();
        int creditHour = inputCreditHour();
        double fees = inputFees();
        System.out.println();
        return new Course(CourseCode, CourseName, creditHour, fees);
    }

    public void listAllProgrammes(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                                   List of All Programmes\n "
                + "\n===================================================================================");
        System.out.printf("%-15s %-40s %10s\n", "Programme Id", "Programme Name", "Duration(Year)");
        System.out.print("\n-----------------------------------------------------------------------------------");

        System.out.println(outputStr);
        System.out.println("\n===================================================================================");

        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public String printProgrammeDetails(Programme programme) {
        String outputStr = "";
        outputStr += "Programme Id: " + programme.getProgrammeId() + "\n";
        outputStr += "Programme Name: " + programme.getProgrammeName() + "\n";
        outputStr += "Duration: " + programme.getDuration() + "\n";
        outputStr += "-----------------------------------------------------------------------------------\n";
        return outputStr;
    }

    public String inputProgrammeId() {
        String programmeId;
        while (true) {
            System.out.print("Enter programme id (e.g., RSW): ");
            programmeId = scanner.nextLine().trim().toUpperCase();
            if (tutGrpUI.isValidProgrammeId(programmeId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid programme id.");
            }
        }
        return programmeId;
    }

    public void listAllFaculties(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                                   List OF All Faculty\n "
                + "\n===================================================================================");
        System.out.printf("%-10s %-50s %-60s\n", "Faculty Code", "Faculty Name", "Counter Location");
        System.out.print("\n-----------------------------------------------------------------------------------");

        System.out.println(outputStr);
        System.out.println("\n===================================================================================");

        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public String printFacultyDetails(Faculty faculty) {
        String outputStr = "";
        outputStr += "Faculty Id: " + faculty.getFacultyId() + "\n";
        outputStr += "Faculty Name: " + faculty.getFacultyName() + "\n";
        outputStr += "Location: " + faculty.getLocation() + "\n";
        outputStr += "-----------------------------------------------------------------------------------\n";
        return outputStr;
    }

    public String inputFacultyId() {
        System.out.print("Enter Faculty Id: ");
        String facultyId = scanner.nextLine();
        return facultyId;
    }

    public String inputFacultyName() {
        System.out.print("Enter Faculty Name: ");
        String facultyName = scanner.nextLine();
        return facultyName;
    }

    public String inputLocation() {
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();
        return location;
    }

    public Faculty inputFacultyDetails() {
        String facultyId = inputFacultyId();
        String facultyName = inputFacultyName();
        String location = inputLocation();
        System.out.println();
        return new Faculty(facultyId, facultyName, location);
    }

    public void listAllCoursesProgrammes(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                        List of Course For Each Programmes\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");
    }

    public void listAllProgrammesCourses(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                        List of Programmes For Each Course\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");
    }

    public void listCoursesForAPgm(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                        List of Courses For A Programme\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");

        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public void listPgmsForACourse(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                        List of Programmes For A Course\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");

    }

    public void listCoursesFaculty(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n                        List of courses for different faculties\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");
    }

    public void courseSummary(String outputStr) {
        System.out.println("\n==============================================================================================================="
                + "\n\n                                   Course Summary Report\n "
                + "\n===============================================================================================================");
        System.out.printf("%-10s %-40s %12s %15s %10s\n", "Course Id", "Course Name", "Credit Hours", "Fee(RM)", "Programmes/Faculties Offered");
        System.out.print("\n--------------------------------------------------------------------------------------------------------------");

        System.out.println(outputStr);
        System.out.println("\n================================================================================================================\n");

        if ("".equals(outputStr)) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public void feeCourseByPgm(String outputStr) {
        System.out.println("\n==================================================================================="
                + "\n\n             Fee Analysis: Highest and Lowest Fee Courses by Programme Report\n "
                + "\n===================================================================================");
        System.out.println(outputStr);
        System.out.println("\n===================================================================================\n");
    }

    public int inputConAddCourse() {
        System.out.println("\nDo you want to continue add this course to the programme list as well? ");
        System.out.println("1. Yes");
        System.out.println("2. No, Back to Course Management Menu");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public int inputConAddProgramme() {
        System.out.println("\n\nDo you want to continue add this programme to the course list as well? ");
        System.out.println("1. Yes");
        System.out.println("2. No, Back to Course Management Menu");

        System.out.print("\nSelect Option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public int inputAmendPart() {
        System.out.println("\nWhich Part u want to amend? ");
        System.out.println("1. Course Id");
        System.out.println("2. Course Name");
        System.out.println("3. Credit Hours");
        System.out.println("4. Fees");

        System.out.print("\nSelect Option: ");
        int amendPart = scanner.nextInt();
        scanner.nextLine();
        return amendPart;
    }

}
