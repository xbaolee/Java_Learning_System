package entity;

import java.io.Serializable;

/**
 *
 * @author ThyeHan
 */
public class StudentInfo implements Serializable {

    private String studentId;
    private String name;

    public StudentInfo() {

    }

    public StudentInfo(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String courseCode) {
        this.studentId = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%-11s %-40s", studentId, name);
    }
}
