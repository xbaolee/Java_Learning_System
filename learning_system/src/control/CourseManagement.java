/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import entity.*;
import boundary.CourseManagementUI;
import utility.MessageUI;
import dao.*;

/**
 *
 * @author lee xue bao
 */
public class CourseManagement {

    CourseManagementInitializer cm = new CourseManagementInitializer();

    CircularLinkedList<Course> courseList = cm.initializeCourses();
    CircularLinkedList<Programme> programmeList = cm.initializeProgrammes();
    CircularLinkedList<Faculty> facultyList = cm.initializeFaculties();

    private final CourseManagementDAO courseManagementDAO = new CourseManagementDAO();
    private final CourseManagementUI courseManagementUI = new CourseManagementUI();

    public CourseManagement() {
        courseManagementDAO.saveToCourseFile(courseList);
        courseManagementDAO.saveToProgrammeFile(programmeList);
        courseManagementDAO.saveToFacultyFile(facultyList);

        courseList = courseManagementDAO.retrieveFromCourseFile();
        programmeList = courseManagementDAO.retrieveFromProgrammeFile();
        facultyList = courseManagementDAO.retrieveFromFacultyFile();

    }

    public void runCourseManagement() {

        int choice = 0;
        do {

            choice = courseManagementUI.getCourseMenuChoice();
            switch (choice) {
                case 0:
                    MessageUI.displayExitMessage();
                    break;

                case 1:
                    addNewFaculty();
                    courseManagementUI.listAllFaculties(getAllFaculties());
                    break;
                case 2:
                    courseManagementUI.listAllFaculties(getAllFaculties());
                    String delFId = courseManagementUI.inputFacultyId();
                    removeFaculty(delFId);
                    courseManagementUI.listAllFaculties(getAllFaculties());

                    break;

                case 3:
                    displayPListForAllCourse();
                    String addPToCCode = courseManagementUI.inputCourseCode();
                    String addPId = courseManagementUI.inputProgrammeId();

                    addProgrammeToCourse(addPToCCode, addPId);
                     addCourseToProgramme(addPToCCode, addPId);
                    displayPListForACourse(addPToCCode);
                    break;

                case 4:
                    displayPListForAllCourse();
                    String removePFromCCode = courseManagementUI.inputCourseCode();
                    String removePId = courseManagementUI.inputProgrammeId();

                    removeProgrammeFromCourse(removePFromCCode, removePId);
                    removeCourseFromProgramme(removePFromCCode, removePId);

                    displayPListForACourse(removePFromCCode);
                    break;

                case 5:
                    displayCListForAllPgm();
                    String addCToPId = courseManagementUI.inputProgrammeId();
                    String addCCode = courseManagementUI.inputCourseCode();
                    
                    addCourseToProgramme(addCCode, addCToPId);
                    addProgrammeToCourse(addCCode, addCToPId);
                    displayCListForAPgm(addCToPId);

                    break;

                case 6:
                    displayCListForAllPgm();
                    String removeCFromPId = courseManagementUI.inputProgrammeId();
                    String removeCCode = courseManagementUI.inputCourseCode();
                   
                    removeCourseFromProgramme(removeCCode, removeCFromPId);
                    removeProgrammeFromCourse(removeCCode, removeCFromPId);

                    displayCListForAPgm(removeCFromPId);

                    break;

                case 7:
                    String semester = courseManagementUI.inputSemester();
                    CircularLinkedList<Course> searchCourse = searchCourseInsemster(semester);
                    getSearchCourse(searchCourse);
                    courseManagementUI.searchCourses(getSearchCourse(searchCourse));

                    break;
                case 8:
                    String updateCToPId = courseManagementUI.inputProgrammeId();
                    String updateCCode = courseManagementUI.inputCourseCode();

                    amendCourseForProgramme(updateCCode, updateCToPId);
                    displayCListForAPgm(updateCToPId);
                    break;
                case 9:
                    courseManagementUI.listAllCourses(getAllCourses());
                    break;
                case 10:
                    courseManagementUI.listAllProgrammes(getAllProgrammes());
                    break;
                case 11:
                    courseManagementUI.listAllFaculties(getAllFaculties());
                    break;

                case 12:
                    displayCListFaculties();
                    break;
                case 13:
                    String disCForAPgm = courseManagementUI.inputProgrammeId();
                    displayCListForAPgm(disCForAPgm);
                    break;

                case 14:
                    String disPForACourse = courseManagementUI.inputCourseCode();
                    displayPListForACourse(disPForACourse);
                    break;

                case 15:
                    displayCListForAllPgm();
                    break;

                case 16:
                    displayPListForAllCourse();
                    break;

                case 17:
                    courseManagementUI.courseSummary(getCourseSummary());

                    break;

                case 18:
                    courseManagementUI.feeCourseByPgm(getFeeCourseByPgm());

                    break;

                default:
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public String getAllCourses() {
        String outputStr = "";
        for (int i = 0; i <= courseList.getNumberOfEntries() - 1; i++) {
            outputStr += courseList.getEntry(i) + "\n";
        }

        return outputStr;
    }

    public void removeCourse(String courseCode) {
        Course course = findCourseById(courseCode);

        if (course != null) {
            courseList.getIndex(course);
            courseList.remove(courseList.getIndex(course));
            courseManagementDAO.saveToCourseFile(courseList);

            utility.MessageUI.displaySuccessRemovedMessage();
        } else {
            utility.MessageUI.displayNotFoundMessage();
            utility.MessageUI.displayAddFirstMessage();
        }
    }

    public Course findCourseById(String courseCode) {
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course course = (Course) courseList.getEntry(i);
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null; // Course not found
    }

    public CircularLinkedList<Course> searchCourseInsemster(String semester) {
        CircularLinkedList<Course> searchCourse = new CircularLinkedList<>();
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            Course course = (Course) courseList.getEntry(i);
            if (course.getSemester() != null) {
                if (course.getSemester().equalsIgnoreCase(semester)) {
                    searchCourse.add(course);
                }
            }

        }
        return searchCourse;
    }

    public String getSearchCourse(CircularLinkedList<Course> searchCourse) {
        String outputStr = "";
        for (int i = 0; i <= searchCourse.getNumberOfEntries() - 1; i++) {
            outputStr += searchCourse.getEntry(i).displayCourseSemester() + "\n";
        }

        return outputStr;
    }

    public String getAllProgrammes() {
        String outputStr = "";
        for (int i = 0; i <= programmeList.getNumberOfEntries() - 1; i++) {
            outputStr += programmeList.getEntry(i) + "\n";

        }

        return outputStr;
    }

    public void removeProgramme(String programmeId) {

        Programme programme = findProgrammeById(programmeId);

        if (programme != null) {
            programmeList.getIndex(programme);
            programmeList.remove(programmeList.getIndex(programme));
            courseManagementDAO.saveToProgrammeFile(programmeList);

            utility.MessageUI.displaySuccessRemovedMessage();

        } else {

            utility.MessageUI.displayNotFoundMessage();
            utility.MessageUI.displayAddFirstMessage();
        }

    }

    public Programme findProgrammeById(String programmeId) {
        for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
            Programme programme = (Programme) programmeList.getEntry(i);
            if (programme.getProgrammeId().equals(programmeId)) {
                return programme;
            }
        }
        return null; // Program not found
    }

    public String getAllFaculties() {
        String outputStr = "";
        for (int i = 0; i < facultyList.getNumberOfEntries(); i++) {
            outputStr += facultyList.getEntry(i) + "\n";
        }

        return outputStr;
    }

    public void addNewFaculty() {
        Faculty newFaculty = courseManagementUI.inputFacultyDetails();

        Faculty facultyInList = findFacultyById(newFaculty.getFacultyId());
        if (facultyInList != null) {
            utility.MessageUI.displayFoundMessage();
        } else {
            utility.MessageUI.displaySuccessAddedMessage();
            facultyList.add(newFaculty);
            courseManagementDAO.saveToFacultyFile(facultyList);
        }
    }

    public void removeFaculty(String facultyId) {
        Faculty faculty = findFacultyById(facultyId);

        if (faculty != null) {
            facultyList.getIndex(faculty);
            facultyList.remove(facultyList.getIndex(faculty));
            courseManagementDAO.saveToFacultyFile(facultyList);

            utility.MessageUI.displaySuccessRemovedMessage();
        } else {
            utility.MessageUI.displayNotFoundMessage();
            utility.MessageUI.displayAddFirstMessage();
        }
    }

    private Faculty findFacultyById(String facultyId) {
        for (int i = 0; i < facultyList.getNumberOfEntries(); i++) {
            Faculty faculty = facultyList.getEntry(i);
            if (faculty.getFacultyId().equals(facultyId)) {
                return faculty;
            }
        }
        return null; // Faculty not found
    }

    public void displayPListForAllCourse() {
        if (!"".equals(getPListForAllCourse())) {
            courseManagementUI.listAllProgrammesCourses(getPListForAllCourse());

        }

    }

    public String getPListForAllCourse() { // not yet add programm to course, not show
        String outputStr = "";

        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            outputStr += "\n";
            Course targetCourse = (Course) courseList.getEntry(i);

            CircularLinkedList<Programme> programmeListInCourse = targetCourse.getProgrammes();

            outputStr += courseManagementUI.printCourseDetails(targetCourse);
            if (programmeListInCourse.getNumberOfEntries() > 0) {
                for (int j = 0; j < programmeListInCourse.getNumberOfEntries(); j++) {

                    Programme programme = (Programme) programmeListInCourse.getEntry(j);

                    outputStr += programme;
                    //outputStr += "\n-----------------------------------------------------------------------------------\n";
                }

            } else {
                outputStr += "\n\n data not found";
            }
            outputStr += "\n\n***********************************************************************************\n";

        }

//        if (!outputStr.contains(",")) {
//            outputStr = "";
//        }
//        outputStr = outputStr.trim();
//        if (outputStr.endsWith(",")) {
//            outputStr = outputStr.substring(0, outputStr.length() - 1);
//        }
        return outputStr;
    }

    public void displayCListForAllPgm() {
        courseManagementUI.listAllProgrammesCourses(getCListForAllPgms());

        if ("".equals(getCListForAllPgms())) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public String getCListForAllPgms() { // not yet add programm to programme, not show
        String outputStr = "";

        for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
            outputStr += "\n";
            Programme targetProgramme = (Programme) programmeList.getEntry(i);

            CircularLinkedList<Course> courseListInProgramme = targetProgramme.getCourses();

            outputStr += courseManagementUI.printProgrammeDetails(targetProgramme);
            if (courseListInProgramme.getNumberOfEntries() > 0) {
                for (int j = 0; j < courseListInProgramme.getNumberOfEntries(); j++) {

                    Course course = (Course) courseListInProgramme.getEntry(j);

                    outputStr += course;
                    //outputStr += "\n-----------------------------------------------------------------------------------\n";
                }

            }

            outputStr += "\n\n***********************************************************************************\n";

        }
        return outputStr;
    }

    public void displayPListForACourse(String addPToCCode) {

        if (!"".equals(getPListForACourse(addPToCCode))) {
            courseManagementUI.listPgmsForACourse(getPListForACourse(addPToCCode));

        }

    }

    public String getPListForACourse(String courseCode) {

        String outputStr = "";

        Course targetCourse = findCourseById(courseCode);

        if (targetCourse != null) {

            outputStr += "\n";

            CircularLinkedList<Programme> programmeListInCourse = targetCourse.getProgrammes();

            outputStr += courseManagementUI.printCourseDetails(targetCourse);
            if (programmeListInCourse.getNumberOfEntries() > 0) {
                for (int j = 0; j < programmeListInCourse.getNumberOfEntries(); j++) {

                    Programme programme = (Programme) programmeListInCourse.getEntry(j);

                    outputStr += programme;
                    //outputStr += "\n-----------------------------------------------------------------------------------\n";
                }

            }
            outputStr += "\n\n***********************************************************************************\n";

        } else {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new course first.");

        }
        return outputStr;
    }

    public void displayCListForAPgm(String addCToPId) {
        if (!"".equals(getCListForAPgm(addCToPId))) {
            courseManagementUI.listCoursesForAPgm(getCListForAPgm(addCToPId));
        }
    }

    public String getCListForAPgm(String programmeId) {

        String outputStr = "";

        Programme targetProgramme = findProgrammeById(programmeId);

        if (targetProgramme != null) {

            outputStr += "\n";

            CircularLinkedList<Course> courseListInProgramme = targetProgramme.getCourses();

            outputStr += courseManagementUI.printProgrammeDetails(targetProgramme);
            if (courseListInProgramme.getNumberOfEntries() > 0) {
                for (int j = 0; j < courseListInProgramme.getNumberOfEntries(); j++) {

                    Course course = (Course) courseListInProgramme.getEntry(j);

                    outputStr += course;
                    //outputStr += "\n-----------------------------------------------------------------------------------\n";
                }

            }
            outputStr += "\n\n***********************************************************************************\n";

        } else {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new programme first.");

        }
        return outputStr;
    }

    public void displayCListFaculties() {

        courseManagementUI.listCoursesFaculty(getCListFaculties());
        if ("".equals(getCListFaculties())) {
            utility.MessageUI.displayNotFoundMessage();
        }
    }

    public String getCListFaculties() {

        String outputStr = "";

        for (int i = 0; i < facultyList.getNumberOfEntries(); i++) {

            outputStr += "\n";
            Faculty targetFaculty = (Faculty) facultyList.getEntry(i);
            CircularLinkedList<Course> courseListInFaculty = targetFaculty.getCourses();

            outputStr += courseManagementUI.printFacultyDetails(targetFaculty);
            if (courseListInFaculty.getNumberOfEntries() > 0) {
                for (int j = 0; j < courseListInFaculty.getNumberOfEntries(); j++) {

                    Course course = (Course) courseListInFaculty.getEntry(j);

                    outputStr += course;
                    //outputStr += "\n-----------------------------------------------------------------------------------\n";
                }

            } else {
                outputStr += "\n\n data not found";
            }
            outputStr += "\n\n***********************************************************************************\n";

        }

        return outputStr;
    }

    public void addProgrammeToCourse(String courseCode, String programmeId) {
        Programme programme = findProgrammeById(programmeId);
        Course course = findCourseById(courseCode);

        if (programme != null && course != null) {
            CircularLinkedList<Programme> programmeListInCourse = course.getProgrammes();

            // Check if the programme is already in the course
            boolean programmeFound = false;
            for (int i = 0; i < programmeListInCourse.getNumberOfEntries(); i++) {
                Programme programmeInCourse = programmeListInCourse.getEntry(i);
                if (programmeInCourse.getProgrammeId().compareTo(programmeId) == 0) {
                    programmeFound = true;
                    break;
                }
            }

            if (programmeFound) {
                utility.MessageUI.displayFoundMessage();
            } else {
                // Add the programme to the course
                course.addProgramme(programme);
                courseManagementDAO.saveToCourseFile(courseList);
                utility.MessageUI.displayProSuccessAddedMessage();


            }
        } else if (programme == null && course != null) {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new programme first.");
        }
    }

    public void removeProgrammeFromCourse(String courseCode, String programmeId) {
        Programme programme = findProgrammeById(programmeId);
        Course course = findCourseById(courseCode);

        if (programme != null && course != null) {
            CircularLinkedList<Programme> programmeListInCourse = course.getProgrammes();
            CircularLinkedList<Course> courseListInProgramme = programme.getCourses();

            boolean programmeFound = false;
            int programmeIndex = 0;

            for (int i = 0; i < programmeListInCourse.getNumberOfEntries(); i++) {

                Programme programmeInCourse = programmeListInCourse.getEntry(i);

                if (programmeInCourse.getProgrammeId().compareTo(programmeId) == 0) {
                    programmeFound = true;
                    programmeIndex = i;
                    break;
                }
            }

            if (programmeFound) {

                programmeListInCourse.remove(programmeIndex);
                courseManagementDAO.saveToCourseFile(courseList);
                utility.MessageUI.displayProSuccessRemoveMessage();

            } else {

                utility.MessageUI.displayNotFoundMessage();
                utility.MessageUI.displayAddFirstMessage();
            }

        } else if (programme == null && course != null) {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new programme first.");
        }
    }

    public void addCourseToProgramme(String courseCode, String programmeId) {
        Programme programme = findProgrammeById(programmeId);
        Course course = findCourseById(courseCode);

        if (programme != null && course != null) {
            CircularLinkedList<Course> courseListInProgramme = programme.getCourses();

            // Check if the programme is already in the course
            boolean courseFound = false;
            for (int i = 0; i < courseListInProgramme.getNumberOfEntries(); i++) {
                Course courseInProgramme = courseListInProgramme.getEntry(i);
                if (courseInProgramme.getCourseCode().compareTo(courseCode) == 0) {
                    courseFound = true;
                    break;
                }
            }

            if (courseFound) {

                utility.MessageUI.displayFoundMessage();
            } else {
                // Add the programme to the course
                programme.addCourse(course);
                courseManagementDAO.saveToProgrammeFile(programmeList);
                utility.MessageUI.displayCourseSuccessAddedMessage();

                
            }
        } else if (programme != null && course == null) {

            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new course first.");
        }
    }

    
    
    
    
    
    
    public void removeCourseFromProgramme(String courseCode, String programmeId) {
        Programme programme = findProgrammeById(programmeId);
        Course course = findCourseById(courseCode);

        if (programme != null && course != null) {
            CircularLinkedList<Course> courseListInProgramme = programme.getCourses();

            boolean courseFound = false;
            int courseIndex = 0;
            for (int i = 0; i < courseListInProgramme.getNumberOfEntries(); i++) {

                Course courseInProgramme = courseListInProgramme.getEntry(i);
                if (courseInProgramme.getCourseCode().compareTo(courseCode) == 0) {

                    courseFound = true;
                    courseIndex = i;
                    break;
                }
            }
            if (courseFound) {
                courseListInProgramme.remove(courseIndex);
                courseManagementDAO.saveToProgrammeFile(programmeList);
                utility.MessageUI.displayCourseSuccessRemovedMessage();

            } else {
                utility.MessageUI.displayNotFoundMessage();
                utility.MessageUI.displayAddFirstMessage();
            }

        } else if (programme != null && course == null) {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new course first.");
        }
    }

    public void amendCourseForProgramme(String courseCode, String programmeId) {
        Programme programme = findProgrammeById(programmeId);
        Course course = findCourseById(courseCode);

        if (programme != null && course != null) {
            CircularLinkedList<Course> courseListInProgramme = programme.getCourses();
            Course courseInProgramme = null;
            boolean courseFound = false;
            int courseIndex = 0;
            for (int i = 0; i < courseListInProgramme.getNumberOfEntries(); i++) {
                courseInProgramme = courseListInProgramme.getEntry(i);
                if (courseInProgramme.getCourseCode().compareTo(courseCode) == 0) {
                    courseFound = true;
                    courseIndex = i;
                    break;
                }
            }

            if (courseFound) {
                int updatePart = courseManagementUI.inputAmendPart();
                Course newCourse = null;
                switch (updatePart) {

                    case 1: {
                        String newCourseCode = courseManagementUI.inputCourseCode();
                        newCourse = new Course(newCourseCode, courseInProgramme.getCourseName(), courseInProgramme.getCreditHour(), courseInProgramme.getFees());
                        break;
                    }

                    case 2: {
                        String newCourseName = courseManagementUI.inputCourseName();
                        newCourse = new Course(courseInProgramme.getCourseCode(), newCourseName, courseInProgramme.getCreditHour(), courseInProgramme.getFees());
                        break;
                    }

                    case 3: {
                        int newCreditHour = courseManagementUI.inputCreditHour();
                        newCourse = new Course(courseInProgramme.getCourseCode(), courseInProgramme.getCourseName(), newCreditHour, courseInProgramme.getFees());
                        break;
                    }

                    case 4: {
                        double newFees = courseManagementUI.inputFees();
                        newCourse = new Course(courseInProgramme.getCourseCode(), courseInProgramme.getCourseName(), courseInProgramme.getCreditHour(), newFees);
                        break;
                    }
                    default: {
                        utility.MessageUI.displayInvalidChoiceMessage();
                    }
                }

                programme.updateCourse(courseIndex, newCourse);
                courseManagementDAO.saveToProgrammeFile(programmeList);

                programmeList = courseManagementDAO.retrieveFromProgrammeFile();

                utility.MessageUI.displaySuccessUpdatedMessage();

            } else {
                utility.MessageUI.displayNotFoundMessage();
                utility.MessageUI.displayAddFirstMessage();
            }
        } else if (programme != null && course == null) {
            utility.MessageUI.displayNotFoundMessage();
            System.out.println(" Add the new course first.");
        }
    }

    public String getCourseSummary() {
        int highestNoFaculty = 0;
        int highestNoProgramme = 0;
        int lowestNoFaculty = 0;
        int lowestNoProgramme = 0;

        Course highestFaculty = courseList.getEntry(0);
        Course highestProgramme = courseList.getEntry(0);
        Course lowestFaculty = courseList.getEntry(0);
        Course lowestProgramme = courseList.getEntry(0);
        String outputStr = "";
        for (int i = 0; i < courseList.getNumberOfEntries(); i++) {
            outputStr += courseList.getEntry(i);
            outputStr += String.format("%12d/%1d", courseList.getEntry(i).getProgrammes().getNumberOfEntries(), courseList.getEntry(i).getFaculties().getNumberOfEntries()) + "\n";
           
            if (courseList.getEntry(i).getFaculties().getNumberOfEntries() > highestNoFaculty) {
                highestNoFaculty = courseList.getEntry(i).getFaculties().getNumberOfEntries();
                highestFaculty = courseList.getEntry(i);
            }
           
            if (courseList.getEntry(i).getFaculties().getNumberOfEntries() < lowestNoFaculty) {
                lowestNoFaculty = courseList.getEntry(i).getFaculties().getNumberOfEntries();
                lowestFaculty = courseList.getEntry(i);
            }

            if (courseList.getEntry(i).getProgrammes().getNumberOfEntries() > highestNoProgramme) {
                highestNoProgramme = courseList.getEntry(i).getProgrammes().getNumberOfEntries();
                highestProgramme = courseList.getEntry(i);
            }
           
            if (courseList.getEntry(i).getProgrammes().getNumberOfEntries() < lowestNoProgramme) {
                lowestNoProgramme = courseList.getEntry(i).getProgrammes().getNumberOfEntries();
                lowestProgramme = courseList.getEntry(i);
            }

        }

        outputStr += "\n\n Total Courses:" + courseList.getNumberOfEntries() + "\n";
        outputStr += "===============================================================================================================";
        outputStr += "\n\n Highest Faculties Offered:\n -> <" + highestFaculty.getCourseCode() + "> " + highestFaculty.getCourseName() + " [" + highestNoFaculty + " Faculties] \n";
        outputStr += "\n Lowest Faculties Offered:\n -> <" + lowestFaculty.getCourseCode() + "> " + lowestFaculty.getCourseName() + " [" + lowestNoFaculty + " Faculties]\n";

        outputStr += "\n*****************************************";
        outputStr += "\n Highest Programme Offered:\n -> <" + highestProgramme.getCourseCode() + "> " + highestProgramme.getCourseName() + " [" + highestNoProgramme + " Programme]\n";
        outputStr += "\n Lowest Programme Offered:\n -> <" + lowestProgramme.getCourseCode() + "> " + lowestProgramme.getCourseName() + " [" + lowestNoProgramme + " Programme]\n";
        outputStr += "\n\n " + String.format("%70s", "END OF THE COURSE SUMMARY REPORT");
        return outputStr;
    }

    public String getFeeCourseByPgm() { // not yet add programm to programme, not show
        String outputStr = "";
        double ttlHighestFee = 0;
        Course ttlHighestFeeCourse = null;

        double ttlLowestFee = Double.MAX_VALUE;
        Course ttlLowestFeeCourse = null;

        for (int i = 0; i < programmeList.getNumberOfEntries(); i++) {
            outputStr += "\n";
            Programme targetProgramme = (Programme) programmeList.getEntry(i);

            CircularLinkedList<Course> courseListInProgramme = targetProgramme.getCourses();

            outputStr += courseManagementUI.printProgrammeDetails(targetProgramme);
            double highestFee = 0;
            Course highestFeeCourse = null;

            double lowestFee = Double.MAX_VALUE;
            Course lowestFeeCourse = null;

            if (courseListInProgramme.getNumberOfEntries() > 0) {

                for (int j = 0; j < courseListInProgramme.getNumberOfEntries(); j++) {
                    Course course = (Course) courseListInProgramme.getEntry(j);

                    if (course.getFees() > highestFee) {
                        highestFee = course.getFees();
                        highestFeeCourse = course;
                    }

                    if (course.getFees() > ttlHighestFee) {
                        ttlHighestFee = course.getFees();
                        ttlHighestFeeCourse = course;
                    }

                    if (course.getFees() < lowestFee) {
                        lowestFee = course.getFees();
                        lowestFeeCourse = course;
                    }

                    if (course.getFees() < ttlLowestFee) {
                        ttlLowestFee = course.getFees();
                        ttlLowestFeeCourse = course;
                    }

                }

                outputStr += "\nhighest Fee:" + highestFee;
                outputStr += "\nhighest Fee Course: <" + highestFeeCourse.getCourseCode() + "> " + highestFeeCourse.getCourseName();

                outputStr += "\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

                outputStr += "\nlowest Fee:" + lowestFee;
                outputStr += "\nlowest Fee Course: <" + lowestFeeCourse.getCourseCode() + "> " + lowestFeeCourse.getCourseName();

                outputStr += "\n\n************************************************************************************\n";

            } else {
                outputStr += "no data found.";
                outputStr += "\n\n************************************************************************************\n";
            }

        }

        outputStr += "\n Total Programmes:" + programmeList.getNumberOfEntries() + "\n";
        outputStr += "\n====================================================================================\n";
        outputStr += "\n Highest Fees Course Offered in all Programmes:\n -> <" + ttlHighestFeeCourse.getCourseCode() + "> " + ttlHighestFeeCourse.getCourseName() + " [RM " + ttlHighestFee + "] \n";
        outputStr += "\n Lowest Fees Course Offered in all Programmes:\n -> <" + ttlLowestFeeCourse.getCourseCode() + "> " + ttlLowestFeeCourse.getCourseName() + " [RM" + ttlLowestFee + "] \n";

        outputStr += "\n\n " + String.format("%60s", "END OF THE FEE ANALYSIS SUMMARY REPORT");

        return outputStr;

    }


}
