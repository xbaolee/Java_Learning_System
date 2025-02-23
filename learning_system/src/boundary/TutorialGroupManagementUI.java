package boundary;

import java.util.Scanner;
import entity.*;
import utility.*;
import java.util.InputMismatchException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Jia Hao
 */
public class TutorialGroupManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                               TUTORIAL GROUP MANAGEMENT MENU");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                                           MAIN MENU                                         \n");
        System.out.println("1. Add a Tutorial Group to a Programme");
        System.out.println("2. Remove a Tutorial Group from a Programme");
        System.out.println("3. List All Tutorial Groups for a Programme");
        System.out.println("4. Merge Tutorial Groups Based on Criteria");
        System.out.println("5. Add Students to a Tutorial Group");
        System.out.println("6. Remove a Student from a Tutorial Group");
        System.out.println("7. Change the Tutorial Group for a Student");
        System.out.println("8. List All Students in a Tutorial Group");
        System.out.println("9. List All Students in a Programme");
        System.out.println("10. Generate Tutorial Group Summary Report");
        System.out.println("11. Generate Student Summary Report");
        System.out.println("0. Quit\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.print("Please enter your choice: ");
        int choice = -1;
        try {
            choice = scanner.nextInt();
            if (choice < 0 || choice > 11) {
                System.out.println("Invalid choice. Please enter a number between 0 and 10.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
        }

        scanner.nextLine();
        System.out.println();

        return choice;
    }

    public void printAddTutorialGroup() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                            Add a Tutorial Group to a Programme                             ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printRemoveTutorialGroup() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                          Remove a Tutorial Group from a Programme                          ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printListAllTutorialGroups() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                         List All Tutorial Groups for a Programme                           ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printMergeTutorialGroup() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                        Merge Tutorial Groups Based on Criteria                             ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printAddStudent() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                              Add Students to a Tutorial Group                              ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printRemoveStudent() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                          Remove a Student from a Tutorial Group                            ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printListAllStudentsTutorialGroup() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                           List All Students in a Tutorial Group                            ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printListAllStudentsProgramme() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                             List All Students in a Programme                               ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printChangeTutorialGroup() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                          Change the Tutorial Group for a Student                           ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printGenerateTutorialGroupSummaryReport() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                           Generate Tutorial Group Summary Report                           ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void printGenerateStudentSummaryReport() {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                              Generate Student Summary Report                               ");
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    //-------------------------Programme-----------------------------
    public boolean isValidProgrammeId(String programmeId) {
        return programmeId.matches("[A-Za-z]{2,3}");
    }

    public String inputProgrammeId() {
        String programmeId;
        while (true) {
            System.out.print("Enter programme id (e.g., RSW): ");
            programmeId = scanner.nextLine().trim().toUpperCase();
            if (isValidProgrammeId(programmeId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid programme id.");
            }
        }
        return programmeId;
    }

    public void listAllProgrammes(String outputStr) {
        System.out.println("\n-----------------------------------------------------------------------------------"
                + "\n                                     PROGRAMME LIST "
                + "\n-----------------------------------------------------------------------------------");
        System.out.printf("%-15s %-45s %10s\n", "Programme Id", "Programme Name", "Duration");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println(outputStr
                + "\n-----------------------------------------------------------------------------------");
    }

    //--------------------------Tutorial-----------------------------
    private boolean isValidGroupId(String groupId) {
        return groupId.matches("[A-Za-z]{2,3}\\d[A-Za-z]\\d[A-Za-z]\\d{1,2}");
    }

    public String inputGroupId() {
        String groupId;
        while (true) {
            System.out.print("Enter tutorial group id (e.g., RSW2S2G3): ");
            groupId = scanner.nextLine().trim().toUpperCase();
            if (isValidGroupId(groupId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid tutorial group id.");
            }
        }
        return groupId;
    }

    public TutorialGroup inputTutorialGroupDetails() {
        String groupID = inputGroupId();
        System.out.println();
        return new TutorialGroup(groupID);
    }

    public String inputGroupIdToRetain() {
        String groupId;
        while (true) {
            System.out.print("Enter tutorial group id (e.g., RSW2S2G3) to merge with another group: ");
            groupId = scanner.nextLine().trim().toUpperCase();
            if (isValidGroupId(groupId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid tutorial group id.");
            }
        }
        return groupId;
    }

    public String inputGroupIdToRemove() {
        String groupId;
        while (true) {
            System.out.print("Enter tutorial group id (e.g., RSW2S2G3) to be removed after merging with another group: ");
            groupId = scanner.nextLine().trim().toUpperCase();
            if (isValidGroupId(groupId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid tutorial group id.");
            }
        }
        return groupId;
    }

    public String inputCurrentGroupId() {
        String groupId;
        while (true) {
            System.out.print("Enter current tutorial group id (e.g., RSW2S2G3): ");
            groupId = scanner.nextLine().trim().toUpperCase();
            if (isValidGroupId(groupId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid tutorial group id.");
            }
        }
        return groupId;
    }

    public String inputNewGroupId() {
        String groupId;
        while (true) {
            System.out.print("Enter new tutorial group id (e.g., RSW2S2G3): ");
            groupId = scanner.nextLine().trim().toUpperCase();
            if (isValidGroupId(groupId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid tutorial group id.");
            }
        }
        return groupId;
    }

    public void listAllTutorialGroupsByProgramme(String outputStr) {
        System.out.println("\n-----------------------------------------------------------------------------------"
                + "\n                                TUTORIAL GROUP LIST"
                + "\n-----------------------------------------------------------------------------------");
        System.out.printf("%10s\n", "Tutorial Group");
        System.out.println(outputStr);
    }

    //--------------------------Student------------------------------
    private boolean isValidStudentId(String studentId) {
        return studentId.matches("^\\d{2}[A-Z]{3}\\d{5}$");
    }

    public StudentInfo inputStudentDetails() {
        String studentID = inputStudentId();
        String studentName = inputStudentName();
        System.out.println();
        return new StudentInfo(studentID, studentName);
    }

    public String inputStudentId() {
        String studentId;
        while (true) {
            System.out.print("Enter student Id (e.g., 23WMR12123): ");
            studentId = scanner.nextLine().trim().toUpperCase();
            if (isValidStudentId(studentId)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter a valid student id.");
            }
        }
        return studentId;
    }

    public String inputStudentName() {
        String studentName;
        while (true) {
            System.out.print("Enter student name: ");
            studentName = scanner.nextLine().trim().toUpperCase();
            if (!studentName.isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be empty!");
            }
        }
        return studentName;
    }

    public void listAllStudentsByProgramme(String outputStr) {
        System.out.println("\n-----------------------------------------------------------------------------------"
                + "\n                                         STUDENT LIST"
                + "\n-----------------------------------------------------------------------------------");
        System.out.printf("%12s %-40s\n", "Student ID |", "Student Name");
        System.out.printf(outputStr);
    }

    public void listAllStudentsByTutorialGroup(String outputStr) {
        System.out.println("\n-----------------------------------------------------------------------------------"
                + "\n                                         STUDENT LIST"
                + "\n-----------------------------------------------------------------------------------");
        System.out.printf("%12s %-40s\n", "Student ID |", "Student Name");
        System.out.println(outputStr);
    }

    //----------------------------Report----------------------------
    public void printReportHeaeder1() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' hh:mm a");
        String generatedAt = dateFormat.format(currentDate);

        System.out.println("=========================================================================================================================");
        System.out.println("                            TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY                                   ");
        System.out.println("                                        TUTORIAL GROUP MANAGEMENT SUBSYSTEM                                            \n");
        System.out.println("                                           TUTORIAL GROUP SUMMARY REPORT     ");
        System.out.println("                                    ------------------------------------------\n");
        System.out.println("Generated at: " + generatedAt);
        System.out.println("");
    }

    public void printReportHeaeder2() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' hh:mm a");
        String generatedAt = dateFormat.format(currentDate);

        System.out.println("=========================================================================================================================");
        System.out.println("                            TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY                                   ");
        System.out.println("                                        TUTORIAL GROUP MANAGEMENT SUBSYSTEM                                            \n");
        System.out.println("                                              STUDENT SUMMARY REPORT     ");
        System.out.println("                                    ------------------------------------------\n");
        System.out.println("Generated at: " + generatedAt);
        System.out.println("");
    }

    public void printReportFooter1() {
        System.out.println("\n                                      END OF THE TUTORIAL GROUP SUMMARY REPORT                                           ");
        System.out.println("=========================================================================================================================");
        System.out.println("");
        waitForEnter();
    }

    public void printReportFooter2() {
        System.out.println("\n                                          END OF THE STUDENT SUMMARY REPORT                                              ");
        System.out.println("=========================================================================================================================");
        System.out.println("");
        waitForEnter();
    }

    public void printReport1(String outputStr) {
        MessageUI.clearScreen();
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("                            TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                                            TUTORIAL GROUP MANAGEMENT SUBSYSTEM\n");
        System.out.println("                                            TUTORIAL GROUP SUMMARY REPORT     ");
        System.out.println("                                    ---------------------------------------------\n");
        System.out.println("Generated at: ");
        System.out.println();
        System.out.printf("%-15s %-40s %10s %11s\n", "Programme Id", "Programme Name", "Duration", "Number of Tutorial Group");
        System.out.println(outputStr);
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void waitForEnter() {
        System.out.print("Press <ENTER> key to continue...");
        scanner.nextLine();
    }
}
