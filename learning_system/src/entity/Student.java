package entity;

/**
 *
 * @author ThyeHan
 */
import adt.SortedArrayList;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Student implements Comparable<Student>, Serializable {

    private StudentInfo student;
    private LocalDateTime dateAdded;
    private SortedArrayList<Course> courses = new SortedArrayList<>();

    public Student() {
        courses = new SortedArrayList<>();
    }

    public Student(String studentId, String name) {
        this.student = new StudentInfo(studentId, name);
        this.dateAdded = LocalDateTime.now();
        this.courses = new SortedArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean removeCourse(Course course) {
        return courses.remove(course);
    }

    public SortedArrayList<Course> getCourses() {
        return courses;
    }

    public int getCourseCount() {
        return courses.totalNumberOfObject();
    }

    public String listAllCoursesFromStudent() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < courses.totalNumberOfObject(); i++) {
            Course course = courses.getObject(i);
            outputStr.append(course.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public StudentInfo getStudentInfo() {
        return student;
    }

    public void setStudentInfo(StudentInfo student) {
        this.student = student;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public double calculateTotalFeesPaid(Student student) {
        double totalFees = 0.0;
        for (int i = 0; i < courses.totalNumberOfObject(); i++) {
            Course course = student.getCourses().getObject(i);
            totalFees += course.getFees();
        }
        return totalFees;
    }

    @Override
    public int compareTo(Student T) {
        return this.student.getStudentId().compareTo(T.getStudentInfo().getStudentId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Student other = (Student) obj;
        return this.student.getStudentId().equals(other.getStudentInfo().getStudentId())
                || this.student.getName().equals(other.getStudentInfo().getName());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");
        String formattedDate = dateAdded.format(formatter);
        return String.format("%-51s %-25s %-15d", student.toString(), formattedDate, getCourseCount());
    }
}
