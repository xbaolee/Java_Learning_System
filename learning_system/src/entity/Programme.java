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
 * @author Lee Xue Bao(course), JiaHao(tutorial group)
 */
public class Programme implements Comparable<Programme>, Serializable {

    private String programmeId;
    private String programmeName;
    private int duration;
    private CircularLinkedList<Course> courses;
    private ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();

    public Programme() {
        tutorialGroupList = new ArrayList<>();
    }

    public Programme(String programmeId, String programmeName, int duration) {
        this.programmeId = programmeId;
        this.programmeName = programmeName;
        this.duration = duration;
        this.courses = new CircularLinkedList<>();
        this.tutorialGroupList = new ArrayList<>();
    }

    public Programme(String programmeId, String programmeName, int duration, ArrayList<TutorialGroup> tutorialGroupList) {
        this.programmeId = programmeId;
        this.programmeName = programmeName;
        this.duration = duration;
        this.courses = new CircularLinkedList<>();
        this.tutorialGroupList = new ArrayList<>();

    }

    public Programme(String programmeId, String programmeName, int duration, CircularLinkedList<Course> courses) {
        this.programmeId = programmeId;
        this.programmeName = programmeName;
        this.duration = duration;
        this.courses = courses;
        this.tutorialGroupList = new ArrayList<>();

    }

    public CircularLinkedList<Course> getCourses() {
        return courses;
    }

    public String getProgrammeId() {
        return programmeId;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Programme programme = (Programme) obj;
        return Objects.equals(programmeId, programme.programmeId);
    }

    public void setProgrammeId(String programmeId) {
        this.programmeId = programmeId;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(int position) {
        this.courses.remove(position);
    }

    public void updateCourse(int position, Course newCourse) {
        this.courses.replace(position, newCourse);
    }

    public String displayProgrammeCourse() {
        return String.format("\n%-15s %-40s %10d %-15s", programmeId, programmeName, duration, courses);
    }

    public String displayProgrammeInfo() {
        return String.format("\n%-15s %-40s %10d", programmeId, programmeName, duration);
    }

    public ArrayList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroupList.addObject(tutorialGroup);
    }

    public void removeTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroupList.remove(tutorialGroup);
    }

    public String listAllTutorialGroupsByProgramme() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i < tutorialGroupList.totalNumberOfObject() + 1; i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getObject(i);
            outputStr.append(tutorialGroup.toString()).append("\n");
        }
        return outputStr.toString();
    }

    public String listAllStudentsByProgramme() {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 1; i < tutorialGroupList.totalNumberOfObject() + 1; i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getObject(i);
            ArrayList<StudentInfo> studentList = tutorialGroup.getStudentList();
            for (int j = 1; j < studentList.totalNumberOfObject() + 1; j++) {
                StudentInfo student = studentList.getObject(j);
                outputStr.append(student.toString()).append("\n");
            }
        }
        return outputStr.toString();
    }

    @Override
    public int compareTo(Programme other) {
        return this.programmeId.compareTo(other.programmeId);
    }

    @Override
    public String toString() {
        return String.format("\n%-15s %-40s %10d", programmeId, programmeName, duration);
    }

}
