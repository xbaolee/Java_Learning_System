package dao;

import adt.*;
import entity.Course;
import entity.Programme;

/**
 *
 * @author Jia Hao
 */
public class ProgrammeInitializer {

    public ArrayList<Programme> initializeProgramme() {
        ArrayList<Programme> programmeList = new ArrayList<>();

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

        programmeList.addObject(new Programme("DS", "Data Science", 3, courseList2));
        programmeList.addObject(new Programme("RSW", "Software Engineering", 3, courseList));
        programmeList.addObject(new Programme("BA", "Business Administration", 4, courseList3));
        programmeList.addObject(new Programme("AF", "Accounting and Finance", 5, courseList4));
        programmeList.addObject(new Programme("HRM", "Human Resource Manangement", 3));
        programmeList.addObject(new Programme("CM", "Ceative Multimedia", 4));
        programmeList.addObject(new Programme("FD", "Fashion Design", 3));

        return programmeList;
    }

}
