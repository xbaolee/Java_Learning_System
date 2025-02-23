package entity;

/**
 * @author Lee Xue Bao(course), Thyehan(student)
 */
import java.io.Serializable;
import adt.*;
import java.util.Objects;

public class Course implements Comparable<Course>, Serializable {

    private String courseCode;
    private String courseName;
    private int creditHour;
    private double fees;
    private String semester;

    private int noFaculties = 0;
    private int noProgrammes = 0;
    private CircularLinkedList<Programme> programmes;
    private CircularLinkedList<Faculty> faculties;

    private String status;

    public Course() {
    }

    public Course(String courseCode, String name, String status, double fees) {
        this.courseCode = courseCode;
        this.courseName = name;
        this.status = status;
        this.fees = fees;
    }

    public Course(String courseCode, String courseName, int creditHour, double fees) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.fees = fees;
        this.programmes = new CircularLinkedList<>();
        this.faculties = new CircularLinkedList<>();

    }

    public Course(String courseCode, String courseName, int creditHour, int fees, String semester, CircularLinkedList<Programme> programmes) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.fees = fees;
        this.programmes = programmes;
        this.semester = semester;
        this.faculties = new CircularLinkedList<>();

    }

    public Course(String courseCode, String courseName, int creditHour, int fees, CircularLinkedList<Faculty> faculties) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHour = creditHour;
        this.fees = fees;
        this.programmes = new CircularLinkedList<>();
        this.faculties = faculties;

    }

    public Course(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getStatus() {
        return status;
    }

    public String getSemester() {
        return semester;
    }

    public void setNoFaculties(int noFaculties) {
        this.noFaculties = noFaculties;
    }

    public void setNoProgrammes(int noProgrammes) {
        this.noProgrammes = noProgrammes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public CircularLinkedList<Programme> getProgrammes() {
        return programmes;
    }

    public void addProgramme(Programme programme) {
        this.programmes.add(programme);
    }

    public void removeProgramme(int position) {
        this.programmes.remove(position);
    }

    public CircularLinkedList<Faculty> getFaculties() {
        return faculties;
    }

    public void addNoFaculty(Faculty faculty) {
        this.faculties.add(faculty);

    }

    public String displayCourseProgramme() {
        return String.format("\n%-10s %-40s %12d %15.2f %12s", courseCode, courseName, creditHour, fees, programmes);
    }

    public String displayCourseInfo() {
        return String.format("\n%-10s %-40s %12d %15.2f ", courseCode, courseName, creditHour, fees);
    }

    public String displayCourseSemester() {
        return String.format("\n%-10s %-40s %12d %15.2f %12s", courseCode, courseName, creditHour, fees, semester);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.creditHour != other.creditHour) {
            return false;
        }
        if (Double.doubleToLongBits(this.fees) != Double.doubleToLongBits(other.fees)) {
            return false;
        }
        if (this.noFaculties != other.noFaculties) {
            return false;
        }
        if (this.noProgrammes != other.noProgrammes) {
            return false;
        }
        if (!Objects.equals(this.courseCode, other.courseCode)) {
            return false;
        }
        if (!Objects.equals(this.courseName, other.courseName)) {
            return false;
        }
        if (!Objects.equals(this.semester, other.semester)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.programmes, other.programmes)) {
            return false;
        }
        return Objects.equals(this.faculties, other.faculties);
    }

    @Override
    public int compareTo(Course other) {
        return this.courseCode.compareTo(other.courseCode);
    }

    @Override
    public String toString() {
        return String.format("\n%-10s %-40s %12d %15.2f", courseCode, courseName, creditHour, fees);
    }
}
