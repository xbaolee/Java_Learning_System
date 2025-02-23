/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author lee xue bao
 */
public class Faculty implements Comparable<Faculty>, Serializable {

    private String facultyId;
    private String facultyName;
    private String location;
    private CircularLinkedList<Course> courses;

    public Faculty(String facultyId, String facultyName, String location) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.location = location;
        this.courses = new CircularLinkedList<>();

    }

    public Faculty(String facultyId, String facultyName, String location, CircularLinkedList<Course> course) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.location = location;
        this.courses = course;

    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyID(String facultyId) {
        this.facultyId = facultyId;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getLocation() {
        return location;
    }

    public CircularLinkedList<Course> getCourses() {
        return courses;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCourses(CircularLinkedList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Faculty faculty = (Faculty) obj;
        return Objects.equals(facultyId, faculty.facultyId);

    }

    public String displayCourseProgramme() {
        return String.format("\n%-10s %-40s %12s %12s", facultyId, facultyName, location, courses);
    }

    @Override
    public int compareTo(Faculty other) {
        return this.facultyId.compareTo(other.facultyId);
    }

    @Override
    public String toString() {
        return String.format("\n%-10s %-50s %-60s", facultyId, facultyName, location);
    }
}
