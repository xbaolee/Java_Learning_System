package control;

import adt.*;
import boundary.StudentRegistrationManagementUI;
import boundary.TutorialGroupManagementUI;
import dao.*;
import entity.*;
import utility.MessageUI;

/**
 *
 * @author Jia Hao
 */
public class TutorialGroupManagement {

    private final StudentRegistrationManagementUI studentUI = new StudentRegistrationManagementUI();
    private final StudentRegistrationManagement studentReg = new StudentRegistrationManagement();
    private final CourseManagement courseManagement = new CourseManagement();

    ProgrammeInitializer programmeInitializer = new ProgrammeInitializer();
    private ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();
    private ArrayList<StudentInfo> studentList = new ArrayList<>();
    private ArrayList<Programme> programmeList = programmeInitializer.initializeProgramme();
    private final TutorialGroupManagementUI tutorialGroupUI = new TutorialGroupManagementUI();
    private final ProgrammeDAO programmeDAO = new ProgrammeDAO();
    private final TutorialGroupDAO tutorialGroupDAO = new TutorialGroupDAO();
    private final StudentDAO studentDAO = new StudentDAO();

    public TutorialGroupManagement() {
        programmeDAO.saveToFile(programmeList);
        tutorialGroupDAO.saveToFile(tutorialGroupList);
        studentDAO.saveToFile(studentList);

        programmeList = programmeDAO.retrieveFromFile();
        tutorialGroupList = tutorialGroupDAO.retrieveFromFile();
        studentList = studentDAO.retrieveFromFile();
    }

    public void runTutorialGroupManagement() {
        int choice;
        do {
            choice = tutorialGroupUI.getMenuChoice();

            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;
                case 1:
                    tutorialGroupUI.printAddTutorialGroup();
                    addNewTutorialGroup();
                    break;
                case 2:
                    tutorialGroupUI.printRemoveTutorialGroup();
                    removeTutorialGroup();
                    break;
                case 3:
                    tutorialGroupUI.printListAllTutorialGroups();
                    listAllTutorialGroupsByProgramCode();
                    break;
                case 4:
                    tutorialGroupUI.printMergeTutorialGroup();
                    mergeTutorialGroup();
                    break;
                case 5:
                    tutorialGroupUI.printAddStudent();
                    addNewStudent();
                    break;
                case 6:
                    tutorialGroupUI.printRemoveStudent();
                    removeStudent();
                    break;
                case 7:
                    tutorialGroupUI.printChangeTutorialGroup();
                    changeTutorialGroup();
                    break;
                case 8:
                    tutorialGroupUI.printListAllStudentsTutorialGroup();
                    listAllStudentsInTutorialGroup();
                    break;
                case 9:
                    tutorialGroupUI.printListAllStudentsProgramme();
                    listAllStudentsInProgramme();
                    break;
                case 10:
                    tutorialGroupUI.printGenerateTutorialGroupSummaryReport();
                    generateTutorialGroupSummaryReport();
                    break;
                case 11:
                    tutorialGroupUI.printGenerateStudentSummaryReport();
                    generateStudentSummaryReport();
                    break;

                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    //-----------------------------programmes--------------------------------
    public String getAllProgrammes() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i < programmeList.totalNumberOfObject() + 1; i++) {
            outputStr.append(programmeList.getObject(i)).append("\n");
        }
        return outputStr.toString();
    }

    //------------------------------tutorial--------------------------------
    public void addNewTutorialGroup() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            TutorialGroup newTutorialGroup = tutorialGroupUI.inputTutorialGroupDetails();
            programme.addTutorialGroup(newTutorialGroup);
            programmeDAO.saveToFile(programmeList);
            tutorialGroupDAO.saveToFile(tutorialGroupList);
            MessageUI.addTutorialGroupSuccessMessage();
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public void removeTutorialGroup() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String groupId = tutorialGroupUI.inputGroupId();
            TutorialGroup tutorialGroup = findTutorialGroupById(programme, groupId);
            if (tutorialGroup != null) {
                programme.removeTutorialGroup(tutorialGroup);
                programmeDAO.saveToFile(programmeList);
                tutorialGroupDAO.saveToFile(tutorialGroupList);
                MessageUI.removeTutorialGroupSuccessMessage();
            } else {
                MessageUI.tutorialGroupNotFoundMessage();
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    private Programme findProgrammeById(String programmeId) {
        for (int i = 1; i < programmeList.totalNumberOfObject() + 1; i++) {
            Programme programme = (Programme) programmeList.getObject(i);
            if (programme.getProgrammeId().equals(programmeId)) {
                return programme;
            }
        }
        return null;
    }

    private TutorialGroup findTutorialGroupById(Programme programme, String groupId) {
        for (int i = 1; i < programme.getTutorialGroupList().totalNumberOfObject() + 1; i++) {
            TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(i);
            if (tutorialGroup.getGroupId().equals(groupId)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    private TutorialGroup findTutorialGroupById(String groupId) {
        for (int i = 1; i < programmeList.totalNumberOfObject() + 1; i++) {
            Programme programme = programmeList.getObject(i);
            for (int j = 1; j < programme.getTutorialGroupList().totalNumberOfObject() + 1; j++) {
                TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(j);
                if (tutorialGroup.getGroupId().equals(groupId)) {
                    return tutorialGroup;
                }
            }
        }
        return null;
    }

    private StudentInfo findStudentById(Programme programme, String studentId) {
        for (int i = 1; i < programme.getTutorialGroupList().totalNumberOfObject() + 1; i++) {
            TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(i);
            for (int j = 1; j < tutorialGroup.getStudentList().totalNumberOfObject() + 1; j++) {
                StudentInfo student = tutorialGroup.getStudentList().getObject(j);
                if (student.getStudentId().equals(studentId)) {
                    return student;
                }
            }
        }
        return null;
    }

    private StudentInfo findStudentById(String studentId) {
        for (int i = 1; i < programmeList.totalNumberOfObject() + 1; i++) {
            Programme programme = programmeList.getObject(i);
            for (int j = 1; j < programme.getTutorialGroupList().totalNumberOfObject() + 1; j++) {
                TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(j);
                for (int k = 1; k < tutorialGroup.getStudentList().totalNumberOfObject(); k++) {
                    StudentInfo student = tutorialGroup.getStudentList().getObject(k);
                    if (student.getStudentId().equals(studentId)) {
                        return student;
                    }
                }
            }
        }
        return null;
    }

    public void listAllTutorialGroupsByProgramCode() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String groupId = programme.listAllTutorialGroupsByProgramme();
            if (!groupId.isEmpty()) {
                tutorialGroupUI.listAllTutorialGroupsByProgramme(groupId);
            } else {
                tutorialGroupUI.displayMessage("No tutorial group found for programme " + programmeId);
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public void mergeTutorialGroup() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String groupId1 = tutorialGroupUI.inputGroupIdToRetain();
            String groupId2 = tutorialGroupUI.inputGroupIdToRemove();
            TutorialGroup group1 = findTutorialGroupById(programme, groupId1);
            TutorialGroup group2 = findTutorialGroupById(programme, groupId2);
            if (group1 != null && group2 != null) {
                ArrayList<StudentInfo> studentsFromGroup2 = group2.getStudentList();
                for (int i = 1; i < studentsFromGroup2.totalNumberOfObject() + 1; i++) {
                    StudentInfo student = studentsFromGroup2.getObject(i);
                    group1.addStudent(student);
                }
                programme.removeTutorialGroup(group2);
                programmeDAO.saveToFile(programmeList);
                MessageUI.mergeTutorialGroupsSuccessMessage();
            } else {
                MessageUI.tutorialGroupNotFoundMessage();
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public String getAllTutorialGroup() {
        String outputStr = "";
        for (int i = 1; i <= tutorialGroupList.totalNumberOfObject(); i++) {
            outputStr += tutorialGroupList.getObject(i) + "\n";
        }
        return outputStr;
    }

    public int getTotalTutorialGroup() {
        int noOfTut = 0;
        for (int i = 1; i <= programmeList.totalNumberOfObject(); i++) {
            Programme programme = programmeList.getObject(noOfTut);
            noOfTut += programme.getTutorialGroupList().totalNumberOfObject();
        }
        return noOfTut;
    }

    //-----------------------------student-----------------------------
    public void addNewStudent() {
        String groupId = tutorialGroupUI.inputGroupId();
        TutorialGroup tutorialGroup = findTutorialGroupById(groupId);
        if (tutorialGroup != null) {
            String newStudentId = studentUI.inputStudentId();

            Student newStudent = findStudentInListById(newStudentId);
            if (newStudent != null) {
                tutorialGroup.addStudent(newStudent.getStudentInfo());
                programmeDAO.saveToFile(programmeList);
                MessageUI.addStudentSuccessMessage();
            } else {
                MessageUI.studentNotFoundMessage();
            }
        } else {
            MessageUI.tutorialGroupNotFoundMessage();
        }
    }

    public Student findStudentInListById(String studentId) {
        for (int i = 0; i < studentReg.studentList.totalNumberOfObject(); i++) {
            Student course = studentReg.studentList.getObject(i);
            if (course.getStudentInfo().getStudentId().equals(studentId)) {
                return course;
            }
        }
        return null;
    }

    public void removeStudent() { //may need to input programme id
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String groupId = tutorialGroupUI.inputGroupId();
            TutorialGroup tutorialGroup = findTutorialGroupById(groupId);
            if (tutorialGroup != null) {
                String studentId = tutorialGroupUI.inputStudentId();
                StudentInfo student = findStudentById(programme, studentId);
                if (student != null) {
                    tutorialGroup.removeStudent(student);
                    programmeDAO.saveToFile(programmeList);
                    MessageUI.removeStudentSuccessMessage();
                } else {
                    MessageUI.studentNotFoundMessage();
                }
            } else {
                MessageUI.tutorialGroupNotFoundMessage();
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public void changeTutorialGroup() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String studentId = tutorialGroupUI.inputStudentId();
            StudentInfo student = findStudentById(programme, studentId);
            if (student != null) {
                String currentGroupId = tutorialGroupUI.inputCurrentGroupId();
                String newGroupId = tutorialGroupUI.inputNewGroupId();
                TutorialGroup currentTutorialGroup = findTutorialGroupById(programme, currentGroupId);
                TutorialGroup newTutorialGroup = findTutorialGroupById(programme, newGroupId);
                if (currentTutorialGroup != null && newTutorialGroup != null) {
                    currentTutorialGroup.removeStudent(student);
                    newTutorialGroup.addStudent(student);
                    programmeDAO.saveToFile(programmeList);
                    MessageUI.changeTutorialGroupSuccessMessage();
                } else {
                    MessageUI.tutorialGroupNotFoundMessage();
                }
            } else {
                MessageUI.studentNotFoundMessage();
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public void listAllStudentsInTutorialGroup() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String groupId = tutorialGroupUI.inputGroupId();
            TutorialGroup tutorialGroup = findTutorialGroupById(programme, groupId);
            if (tutorialGroup != null) {
                String studentId = tutorialGroup.listAllStudents();
                if (!studentId.isEmpty()) {
                    tutorialGroupUI.listAllStudentsByTutorialGroup(studentId);
                } else {
                    tutorialGroupUI.displayMessage("No student found for tutorial group " + groupId);
                }
            } else {
                MessageUI.tutorialGroupNotFoundMessage();
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    public void listAllStudentsInProgramme() {
        tutorialGroupUI.listAllProgrammes(getAllProgrammes());
        String programmeId = tutorialGroupUI.inputProgrammeId();
        Programme programme = findProgrammeById(programmeId);
        if (programme != null) {
            String studentId = programme.listAllStudentsByProgramme();
            if (!studentId.isEmpty()) {
                tutorialGroupUI.listAllStudentsByProgramme(studentId);
            } else {
                tutorialGroupUI.displayMessage("No student found for programme " + programmeId);
            }
        } else {
            MessageUI.programmeNotFoundMessage();
        }
    }

    //--------------------------------report-------------------------------
    public void generateTutorialGroupSummaryReport() {
        tutorialGroupUI.printReportHeaeder1();
        System.out.println("Programme ID  |   Programme Name                  |   Tutorial Groups                        |   Total Tutorial Groups");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        Programme programmeWithMaxGroups = null;
        int maxGroups = Integer.MIN_VALUE;
        Programme programmeWithMinGroups = null;
        int minGroups = Integer.MAX_VALUE;

        for (int i = 1; i <= programmeList.totalNumberOfObject(); i++) {
            Programme programme = programmeList.getObject(i);
            String programmeId = programme.getProgrammeId();
            String programmeName = programme.getProgrammeName();
            StringBuilder tutorialGroups = new StringBuilder();
            int totalTutorialGroups = programme.getTutorialGroupList().totalNumberOfObject();

            if (totalTutorialGroups > maxGroups) {
                maxGroups = totalTutorialGroups;
                programmeWithMaxGroups = programme;
            }

            if (totalTutorialGroups > 0 && totalTutorialGroups < minGroups) {
                minGroups = totalTutorialGroups;
                programmeWithMinGroups = programme;
            }

            for (int j = 1; j <= totalTutorialGroups; j++) {
                TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(j);
                String tutorialGroupId = tutorialGroup.getGroupId();
                tutorialGroups.append(tutorialGroupId);
                if (j < totalTutorialGroups) {
                    tutorialGroups.append(", ");
                }
            }

            System.out.printf("%-14s|   %-32s|   %-39s|   %d\n", programmeId, programmeName, tutorialGroups.toString(), totalTutorialGroups);
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        if (programmeWithMaxGroups != null) {
            System.out.println("\nProgramme with the most tutorial groups: " + programmeWithMaxGroups.getProgrammeName() + " (" + maxGroups + ")\n");
        }

        if (programmeWithMinGroups != null) {
            System.out.println("Programme with the least tutorial groups (excluding zero): " + programmeWithMinGroups.getProgrammeName() + " (" + minGroups + ")\n");
        }
        tutorialGroupUI.printReportFooter1();
    }

    public void generateStudentSummaryReport() {
        tutorialGroupUI.printReportHeaeder2();
        System.out.println("Student ID  |   Student Name                  |   Tutorial Group          |   Programme");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        for (int i = 1; i <= programmeList.totalNumberOfObject(); i++) {
            Programme programme = programmeList.getObject(i);

            for (int j = 1; j <= programme.getTutorialGroupList().totalNumberOfObject(); j++) {
                TutorialGroup tutorialGroup = programme.getTutorialGroupList().getObject(j);

                for (int k = 1; k <= tutorialGroup.getStudentList().totalNumberOfObject(); k++) {
                    StudentInfo student = tutorialGroup.getStudentList().getObject(k);

                    System.out.printf("%-12s|   %-30s|   %-24s|   %s\n",
                            student.getStudentId(),
                            student.getName(),
                            tutorialGroup.getGroupId(),
                            programme.getProgrammeName());
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        tutorialGroupUI.printReportFooter2();
    }

}
