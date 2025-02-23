package control;

/**
 *
 * @author ThyeHan
 */
import adt.*;
import boundary.*;
import dao.*;
import entity.*;
import java.time.LocalDateTime;
import utility.MessageUI;

public class StudentRegistrationManagement {

    public SortedArrayList<Student> studentList = new SortedArrayList<>();
    private final StudentRegistrationManagementUI studentUI = new StudentRegistrationManagementUI();
    private final StudentRegistrationDAO courseDAO = new StudentRegistrationDAO();
    private CourseManagement courseManagement = new CourseManagement();

    CourseManagementInitializer cm = new CourseManagementInitializer();
    CircularLinkedList<Course> courseList = cm.initializeCourses();
    CircularLinkedList<Programme> programmeList = cm.initializeProgrammes();
    CircularLinkedList<Faculty> facultyList = cm.initializeFaculties();

    private final CourseManagementDAO courseManagementDAO = new CourseManagementDAO();
    private final CourseManagementUI courseManagementUI = new CourseManagementUI();

    public StudentRegistrationManagement() {
        studentList = courseDAO.retrieveFromFile();

        courseManagementDAO.saveToCourseFile(courseList);
        courseManagementDAO.saveToProgrammeFile(programmeList);
        courseManagementDAO.saveToFacultyFile(facultyList);

        courseList = courseManagementDAO.retrieveFromCourseFile();
        programmeList = courseManagementDAO.retrieveFromProgrammeFile();
        facultyList = courseManagementDAO.retrieveFromFacultyFile();

    }

    public void runCourseManagement() {
        int choice;
        do {

            studentUI.listAllStudents(getAllStudents());
            choice = studentUI.getMenuChoice();
            switch (choice) {
                case 0 ->
                    MessageUI.displayExitMessage();
                case 1 -> {
                    addNewStudent();
                    studentUI.displayMessage("New student added.");
                }
                case 2 -> {
                    removeStudent();
                }
                case 3 -> {
                    amendStudentDetails();
                }
                case 4 -> {
                    listAllCoursesByStudent();
                }
                case 5 ->
                    addCourseToStudent();
                case 6 ->
                    removeCourseFromStudent();
                case 7 -> {
                    calculateFeeForStudent();
                }
                case 8 ->
                    filterStudentsBasedOnCriteria();
                case 9 ->
                    // TODO: Generate 2 report

                    displayReports();
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public void displayReports() {
        int filterChoice = 0;
        do {
            filterChoice = studentUI.getReportChoice();
            switch (filterChoice) {
                case 1 -> {
                    displayStudentReports();
                }
                case 2 -> {
                    String studentId = studentUI.inputStudentId();
                    Student student = findStudentById(studentId);
                    if (student != null) {
                        displayStudentCourseDetailReport(student);
                    } else {
                        MessageUI.studentNotFoundMessage();
                    }
                }
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (filterChoice != 3);
    }

    public void filterStudentsBasedOnCriteria() {
        int filterChoice = 0;
        do {
            filterChoice = studentUI.getFilterChoice();
            switch (filterChoice) {
                case 1 -> {
                    String courseCode = studentUI.inputCourseCode();
                    SortedArrayList<Student> filteredStudent = filterStudentsByCourse(studentList, courseCode);
                    studentUI.listAllStudents(filteredStudentsToString(filteredStudent));
                }
                case 2 -> {
                    String status = studentUI.inputFilterByStatus();
                    SortedArrayList<Student> filteredTutors = filterStudentsByStatus(studentList, status);
                    studentUI.listAllStudents(filteredStudentsToString(filteredTutors));
                }
                default ->
                    MessageUI.displayInvalidChoiceMessage();
            }
        } while (filterChoice != 3);
    }

    private String filteredStudentsToString(SortedArrayList<Student> filteredStudents) {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < filteredStudents.totalNumberOfObject(); i++) {
            Student student = filteredStudents.getObject(i);
            outputStr.append(student.toString()).append("\n");
        }
        return outputStr.toString();
    }

    private SortedArrayList<Student> filterStudentsByCourse(SortedArrayList<Student> studentList, String courseCode) {
        SortedArrayList<Student> filteredStudents = new SortedArrayList<>();
        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            SortedArrayList<Course> courses = student.getCourses();
            for (int j = 0; j < courses.totalNumberOfObject(); j++) {
                Course course = courses.getObject(j);
                if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                    filteredStudents.add(student);
                    break;
                }
            }
        }
        return filteredStudents;
    }

    private SortedArrayList<Student> filterStudentsByStatus(SortedArrayList<Student> studentList, String status) {
        SortedArrayList<Student> filteredStudents = new SortedArrayList<>();
        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            SortedArrayList<Course> courses = student.getCourses();
            for (int j = 0; j < courses.totalNumberOfObject(); j++) {
                Course course = courses.getObject(j);
                if (course.getStatus().equalsIgnoreCase(status)) {
                    filteredStudents.add(student);
                }
            }
        }
        return filteredStudents;
    }

    public void calculateFeeForStudent() {
        studentUI.displayMessage("\nPlease input the student id that you want to calculate:\n");
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            double totalFees = student.calculateTotalFeesPaid(student);
            System.out.printf("The total registered course fees is RM %.2f\n\n", totalFees);
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public void addNewStudent() {
        Student newStudent = studentUI.inputStudentDetails();
        studentList.add(newStudent);
        courseDAO.saveToFile(studentList);
    }

    public void removeStudent() {
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            studentList.remove(student);
            courseDAO.saveToFile(studentList);
            MessageUI.removeStudentSuccessMessage();
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public void findStudent() {
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            studentUI.displayStudentDetails(student);
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public void amendStudentDetails() {
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            studentUI.displayMessage("### Please enter new details ###");
            Student newStudentDetails = studentUI.inputStudentDetails();
            studentList.replace(student, newStudentDetails);
            courseDAO.saveToFile(studentList);
            MessageUI.updateStudentSuccessMessage();
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public void addCourseToStudent() {
        courseManagementUI.listAllCourses(courseManagement.getAllCourses());

        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        String courseCode = studentUI.inputCourseCode();
        Course course = courseManagement.findCourseById(courseCode);

        if (course != null) {
            String status = studentUI.getStatus();
            course.setStatus(status);
        } else {
            utility.MessageUI.displayNotFoundMessage();
            utility.MessageUI.displayAddCourseFirstMessage();
        }

        if (student != null) {
            student.addCourse(course);
            courseDAO.saveToFile(studentList);
            MessageUI.addStudentSuccessMessage();
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public void removeCourseFromStudent() {
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            String courseCode = studentUI.inputCourseCode();
            Course course = findCourseByCode(student, courseCode);
            if (course != null) {
                student.getCourses().remove(course);
                courseDAO.saveToFile(studentList);
                MessageUI.removeCourseSuccessMessage();
            } else {
                MessageUI.courseNotFoundMessage();
            }
        } else {
            MessageUI.studentNotFoundMessage();
        }
    }

    public Course findCourseByCode(Student student, String courseCode) {
        for (int i = 0; i < student.getCourses().totalNumberOfObject(); i++) {
            Course course = student.getCourses().getObject(i);
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void displayStudentReports() {
        int totalStudent = studentList.totalNumberOfObject();
        Student mostCoursesStudent = getMostCoursesByStudent();
        Student fewestCoursesStudent = getFewestCoursesByStudent();
        Student recentAddedStudent = getRecentAddedStudent();
        studentUI.displayStudentReport(getAllStudents(), totalStudent, mostCoursesStudent, fewestCoursesStudent, recentAddedStudent);
    }

    public void displayStudentCourseDetailReport(Student selectedStudent) {
        int totalCourses = selectedStudent.getCourseCount();
        int totalMain = 0;
        int totalElective = 0;
        int totalResit = 0;
        int totalRepeat = 0;
        double totalFees = selectedStudent.calculateTotalFeesPaid(selectedStudent);
        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            SortedArrayList<Course> courses = student.getCourses();
            for (int j = 0; j < courses.totalNumberOfObject(); j++) {
                Course course = courses.getObject(j);
                if (course.getStatus().equalsIgnoreCase("MAIN")) {
                    totalMain += 1;
                }
                if (course.getStatus().equalsIgnoreCase("ELECTIVE")) {
                    totalElective += 1;
                }
                if (course.getStatus().equalsIgnoreCase("RESIT")) {
                    totalResit += 1;
                }
                if (course.getStatus().equalsIgnoreCase("REPEAT")) {
                    totalRepeat += 1;
                }
            }
        }
        String courses = selectedStudent.listAllCoursesFromStudent();
        studentUI.displayStudentCourseReport(courses, selectedStudent, totalCourses, totalMain, totalElective, totalResit, totalRepeat, totalFees);
    }

    // Find Course By Code can reuse in update details also
    public Student findStudentById(String studentId) {
        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student course = studentList.getObject(i);
            if (course.getStudentInfo().getStudentId().equals(studentId)) {
                return course;
            }
        }
        return null;
    }

    public String getAllStudents() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            outputStr.append(student.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public Student getMostCoursesByStudent() {
        if (studentList.isListEmpty()) {
            return null; // No courses in the list
        }

        Student mostCoursesStudent = null;
        int maxCourseCount = -1;

        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            int courseCount = student.getCourseCount();

            if (courseCount > maxCourseCount) {
                maxCourseCount = courseCount;
                mostCoursesStudent = student;
            }
        }

        return mostCoursesStudent;
    }

    public Student getFewestCoursesByStudent() {
        if (studentList.isListEmpty()) {
            return null; // No courses in the list
        }

        Student fewestCoursesStudent = null;
        int minCourseCount = Integer.MAX_VALUE; // Set to a high initial value

        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            int courseCount = student.getCourseCount();

            if (courseCount < minCourseCount) { // Check for fewer programs
                minCourseCount = courseCount;
                fewestCoursesStudent = student;
            }
        }

        return fewestCoursesStudent;
    }

    public Student getRecentAddedStudent() {
        if (studentList.isListEmpty()) {
            return null; // No courses in the list
        }

        Student recentStudent = null;
        LocalDateTime maxDateTime = LocalDateTime.MIN; // Initialize maxDateTime to a minimum value

        for (int i = 0; i < studentList.totalNumberOfObject(); i++) {
            Student student = studentList.getObject(i);
            LocalDateTime addedDateTime = student.getDateAdded(); // Assuming you have a method to parse the date string

            if (addedDateTime.isAfter(maxDateTime)) {
                maxDateTime = addedDateTime; // Update maxDateTime if a more recent date is found
                recentStudent = student; // Clear the list by creating a new one
            }
        }
        return recentStudent;
    }

    public void listAllCoursesByStudent() {
        String studentId = studentUI.inputStudentId();
        Student student = findStudentById(studentId);
        if (student != null) {
            String courses = student.listAllCoursesFromStudent();
            if (!courses.isEmpty()) {
                studentUI.listAllCoursesByStudent(courses);
            } else {
                studentUI.displayMessage("No course found for student " + studentId);
            }
        } else {
            MessageUI.courseNotFoundMessage();
        }
    }

}
