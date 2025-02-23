package utility;

/**
 *
 * @author Lee Xue Bao(course), thyehan(student), jiahao(tutorial group)
 */
public class MessageUI {

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid choice. Please enter again.");
    }

    public static void displayExitMessage() {
        System.out.println("\nExiting system");
    }

    public static void displayExitMainMessage() {
        System.out.printf("%43s", "THANK YOU FOR USING OUR SYSTEM");
        System.out.printf("\n===========================================================\n");
    }

    public static void displayErrorMessage() {
        System.out.println("\nInvalid choice. Please enter again.");
    }

    public static void clearScreen() {
        System.out.println(System.lineSeparator().repeat(3));
    }

    public static void displaySuccessMessage() {
        System.out.println("\nAction successful");
    }

    public static void displayNotFoundMessage() {
        System.out.println("\ndata not found.");
    }

    public static void displayFoundMessage() {
        System.out.println("\ndata already existed. Please input another data.");
    }

    public static void displaySuccessAddedMessage() {
        System.out.println("\ndata added successfully.");
    }

    public static void displaySuccessUpdatedMessage() {
        System.out.println("\nData update Successfully.");
    }

    public static void displaySuccessRemovedMessage() {
        System.out.println("\nData remove Successfully.");
    }

    public static void displayFailedRemovedMessage() {
        System.out.println("\nData failed to remove.");
    }

    public static void displayAddFirstMessage() {
        System.out.println("Please add the data into the database first.");
    }

    public static void displayCheckInputMessage() {
        System.out.println("Please check your Input");
    }

    public static void displayCourseSuccessAddedMessage() {
        System.out.println("\nCourse added to Programme successfully. (Automatically added to both side)");
    }

    public static void displayCourseSuccessRemovedMessage() {
        System.out.println("\nCourse removed from Programme successfully (Automatically removed from both side)");
    }

    public static void displayProSuccessAddedMessage() {
        System.out.println("\nProgramme added to Course auccessfully. (Automatically added to both side)");
    }

    public static void displayProSuccessRemoveMessage() {
        System.out.println("\nProgramme removed from Course successfully (Automatically removed from both side)");
    }

    public static void displayCourseAutoAddedMessage() {
        System.out.println("\nCourse automatically added to the programme.");
    }

    public static void displayProAutoAddedMessage() {
        System.out.println("\nProgramme automatically added to the course.");
    }

    public static void displayAddCourseFirstMessage() {
        System.out.println("Please add the course into the database first.");
    }

    public static void removeStudentSuccessMessage() {
        System.out.println("\nStudent removed successfully.");
    }

    public static void studentNotFoundMessage() {
        System.out.println("\nStudent not found.");
    }

    public static void removeCourseSuccessMessage() {
        System.out.println("\nCourse removed from the course.");
    }

    public static void courseNotFoundMessage() {
        System.out.println("\nCourse not found in the course.");
    }

    public static void updateStudentSuccessMessage() {
        System.out.println("\nStudent details amended successfully.");
    }

    public static void addStudentSuccessMessage() {
        System.out.println("\nStudent added successfully.");
    }

    public static void programmeNotFoundMessage() {
        System.out.println("Programme not found.");
    }

    public static void addTutorialGroupSuccessMessage() {
        System.out.println("Tutorial group added successfully.");
    }

    public static void removeTutorialGroupSuccessMessage() {
        System.out.println("Tutorial group removed successfully.");
    }

    public static void changeTutorialGroupSuccessMessage() {
        System.out.println("Tutorial group changed for the student successfully.");
    }

    public static void mergeTutorialGroupsSuccessMessage() {
        System.out.println("Tutorial group merged successfully.");
    }

    public static void tutorialGroupNotFoundMessage() {
        System.out.println("Tutorial group not found.");
    }
}
