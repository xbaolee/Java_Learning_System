/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import control.*;
import utility.*;
import dao.*;
import java.util.Scanner;

/**
 *
 * @author lee xue bao, thyehan, jia hao
 */
public class Client {

    Scanner scanner = new Scanner(System.in);
    private CourseManagement courseManagement = new CourseManagement();
    private StudentRegistrationManagement studentRegistrationManagement = new StudentRegistrationManagement();
    private TutorialGroupManagement tutorialGroupManagement = new TutorialGroupManagement();

    public Client() {

    }

    public int getMainMenuChoice() {
        System.out.printf("===========================================================\n\n");
        System.out.printf("%47s", "UNIVERSITY APPLICATION MAIN MENU\n\n");
        System.out.printf("===========================================================\n\n");
        System.out.println("1. Student Registration Management Subsystem");
        System.out.println("2. Course Management Subsystem");
        System.out.println("3. Tutorial Group Management Subsystem");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;

    }

    public void runClient() {
        int choice = 0;
        do {
            choice = getMainMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMainMessage();
                    break;

                case 1:
                    studentRegistrationManagement.runCourseManagement();
                    break;

                case 2:
                    courseManagement.runCourseManagement();
                    break;

                case 3:
                    tutorialGroupManagement.runTutorialGroupManagement();
                    break;

                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.runClient();
    }
}
