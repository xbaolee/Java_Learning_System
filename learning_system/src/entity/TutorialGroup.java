/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import java.io.Serializable;

/**
 *
 * @author Jia Hao
 */
public class TutorialGroup implements Serializable {

    private String groupId;

    private ArrayList<StudentInfo> studentList;

    public TutorialGroup() {
        studentList = new ArrayList<>();
    }

    public TutorialGroup(String groupId) {
        this.groupId = groupId;
        this.studentList = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public ArrayList<StudentInfo> getStudentList() {
        return studentList;
    }

    public void setStudents(ArrayList<StudentInfo> studentList) {
        this.studentList = studentList;
    }

    public void addStudent(StudentInfo student) {
        studentList.addObject(student);
    }

    public void removeStudent(StudentInfo student) {
        studentList.remove(student);
    }

    public String listAllStudents() {
        StringBuilder outputStr = new StringBuilder();
        ArrayList<StudentInfo> studentList = getStudentList();
        for (int i = 1; i < studentList.totalNumberOfObject() + 1; i++) {
            StudentInfo student = studentList.getObject(i);
            outputStr.append(student.toString()).append("\n");
        }
        return outputStr.toString();
    }

    @Override
    public String toString() {
        return String.format("%-10s", groupId);
    }

}
