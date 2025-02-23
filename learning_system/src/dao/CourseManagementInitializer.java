/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.*;
import adt.*;

/**
 *
 * @author lee xue bao
 */
public class CourseManagementInitializer {
    //  Method to return a collection of with hard-coded entity values

    public CircularLinkedList<Course> initializeCourses() {
        CircularLinkedList<Course> courseList = new CircularLinkedList();

        CircularLinkedList<Programme> programmeList = new CircularLinkedList();
        CircularLinkedList<Programme> programmeList2 = new CircularLinkedList();
        CircularLinkedList<Programme> programmeList3 = new CircularLinkedList();
        CircularLinkedList<Programme> programmeList4 = new CircularLinkedList();

        programmeList.add(new Programme("BA", "Business Administration", 4));
        programmeList.add(new Programme("AF", "Accounting and Finance", 5));

        programmeList2.add(new Programme("DS", "Data Science", 3));
        programmeList2.add(new Programme("RSW", "Software Engineering", 3));

        programmeList3.add(new Programme("DS", "Data Science", 3));
        programmeList3.add(new Programme("RSW", "Software Engineering", 3));
        programmeList3.add(new Programme("AF", "Accounting and Finance", 5));

        CircularLinkedList<Faculty> facultyList = new CircularLinkedList();
        CircularLinkedList<Faculty> facultyList2 = new CircularLinkedList();

        facultyList.add(new Faculty("FOCS", "Faculty Of Computer and Information Technology", "Level 2, Bangunan A"));
        facultyList.add(new Faculty("FCCI", "Faculty Of Commnication and Creative Industries", "Level 3, Bangunan B"));

        facultyList2.add(new Faculty("FAS", "Faculty Of Arts and Social Sciences", "Level 5, Bangunan A"));
        facultyList2.add(new Faculty("FES", "Faculty Of Environmental Sciences", "Level 6, Bangunan C"));

        courseList.add(new Course("SREW1232", "Software Requirement Engineering", 3, 3000, "Y1S1", programmeList2));
        courseList.add(new Course("AFRE1112", "Accounting and Finance", 3, 3460, "Y1S1", programmeList));
        courseList.add(new Course("ENGE1323", "English Literacture", 2, 2500, "Y1S1", programmeList3));
        courseList.add(new Course("DBMS3433", "Database Management System", 3, 3500, facultyList));
        courseList.add(new Course("OOPW1232", "Object-Oriented Programming", 3, 2860, facultyList2));
        courseList.add(new Course("AIWW1345", "Artificial Intelligence", 3, 3350));
        courseList.add(new Course("CSDS3211", "Introduction to Computer Science", 4, 3800));
        courseList.add(new Course("DSAF2221", "Data Structure Algorithm", 3, 4000));
        courseList.add(new Course("JOUR1112", "Journalism", 3, 3000));
        courseList.add(new Course("CSDS3211", "Introduction to Computer Science", 4, 3800));
        courseList.add(new Course("CMNS464", "Communication Studies", 3, 3000));
        courseList.add(new Course("VISD3354", "Visual Arts", 3, 3000, "Y1S1", programmeList4));
        courseList.add(new Course("PSYD4466", "Psychology", 3, 3000));
        courseList.add(new Course("SOCA1142", "Sociology", 3, 3000));

        return courseList;
    }

    public CircularLinkedList<Programme> initializeProgrammes() {
        CircularLinkedList<Programme> programmeList = new CircularLinkedList();

        CircularLinkedList<Course> courseList = new CircularLinkedList();
        CircularLinkedList<Course> courseList2 = new CircularLinkedList();
        CircularLinkedList<Course> courseList3 = new CircularLinkedList();
        CircularLinkedList<Course> courseList4 = new CircularLinkedList();

        courseList.add(new Course("SREW1232", "Software Requirement Engineering", 3, 3000));
        courseList.add(new Course("ENGE1323", "English Literacture", 2, 2500));

        courseList2.add(new Course("SREW1232", "Software Requirement Engineering", 3, 3000));
        courseList2.add(new Course("DSAF2221", "Data Structure Algorithm", 3, 4000));
        courseList2.add(new Course("ENGE1323", "English Literacture", 2, 2500));

        courseList3.add(new Course("ENGE1323", "English Literacture", 2, 2500));
        courseList3.add(new Course("AFRE1112", "Accounting and Finance", 3, 3460));
        courseList3.add(new Course("DBMS3433", "Database Management System", 3, 3500));

        courseList4.add(new Course("AFRE1112", "Accounting and Finance", 3, 3460));
        courseList4.add(new Course("ENGE1323", "English Literacture", 2, 2500));

        programmeList.add(new Programme("DS", "Data Science", 3, courseList2));
        programmeList.add(new Programme("RSW", "Software Engineering", 3, courseList));
        programmeList.add(new Programme("BA", "Business Administration", 4, courseList3));
        programmeList.add(new Programme("AF", "Accounting and Finance", 5, courseList4));
        programmeList.add(new Programme("HRM", "Human Resource Manangement", 3));
        programmeList.add(new Programme("CM", "Ceative Multimedia", 4));
        programmeList.add(new Programme("FD", "Fashion Design", 3));

        return programmeList;
    }

    public CircularLinkedList<Faculty> initializeFaculties() {
        CircularLinkedList<Faculty> facultyList = new CircularLinkedList();

        CircularLinkedList<Course> courseList = new CircularLinkedList();
        CircularLinkedList<Course> courseList2 = new CircularLinkedList();
        CircularLinkedList<Course> courseList3 = new CircularLinkedList();
        CircularLinkedList<Course> courseList4 = new CircularLinkedList();
        CircularLinkedList<Course> courseList5 = new CircularLinkedList();
        CircularLinkedList<Course> courseList6 = new CircularLinkedList();

        courseList.add(new Course("SREW1232", "Software Requirement Engineering", 3, 3000));
        courseList.add(new Course("DSAF2221", "Data Structure Algorithm", 3, 4000));
        courseList.add(new Course("DBMS3433", "Database Management System", 3, 3500));
        courseList.add(new Course("OOPW1232", "Object-Oriented Programming", 3, 2860));
        courseList.add(new Course("AIWW1345", "Artificial Intelligence", 3, 3350));

        courseList2.add(new Course("SREW1232", "Software Requirement Engineering", 3, 3000));
        courseList2.add(new Course("DSAF2221", "Data Structure Algorithm", 3, 4000));

        courseList3.add(new Course("ENGE1323", "English Literacture", 2, 2500));
        courseList3.add(new Course("AFRE1112", "Accounting and Finance", 3, 3460));
        courseList3.add(new Course("DBMS3433", "Database Management System", 3, 3500));

        courseList4.add(new Course("ENGE1323", "English Literacture", 2, 2500));
        courseList4.add(new Course("IETE1112", "Introduction to Environment Technology", 3, 3460));

        courseList5.add(new Course("ENGL1232", "English Literature", 3, 3000));
        courseList5.add(new Course("JOUR1112", "Journalism", 3, 3000));
        courseList5.add(new Course("COMM1112", "Communication Studies", 3, 3000));
        courseList5.add(new Course("VISD3354", "Visual Arts", 3, 3000));

        courseList6.add(new Course("PSYD4466", "Psychology", 3, 3000));
        courseList6.add(new Course("SOCA1142", "Sociology", 3, 3000));

        facultyList.add(new Faculty("FOCS", "Faculty Of Computer and Information Technology", "Level 2, Bangunan A", courseList));
        facultyList.add(new Faculty("FCCI", "Faculty Of Commnication and Creative Industries", "Level 3, Bangunan B", courseList5));
        facultyList.add(new Faculty("FEEE", "Faculty Of Electrical and Electronic Engineering", "Level 4, Bangunan B", courseList2));
        facultyList.add(new Faculty("FAS", "Faculty Of Arts and Social Sciences", "Level 5, Bangunan A", courseList6));
        facultyList.add(new Faculty("FES", "Faculty Of Environmental Sciences", "Level 6, Bangunan C", courseList4));
        facultyList.add(new Faculty("FEB", "Faculty Of Economics and Business", "Level 7, Bangunan C", courseList3));

        return facultyList;
    }

}
