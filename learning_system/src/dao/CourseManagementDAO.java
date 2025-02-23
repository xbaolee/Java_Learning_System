/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.*;
import java.io.*;
import entity.*;

/**
 *
 * @author lee xue bao
 */
public class CourseManagementDAO {

    private String course = "course.dat"; // For security and maintainability, should not have filename hardcoded here.
    private String programme = "programme.dat"; // For security and maintainability, should not have filename hardcoded here.
    private String faculty = "faculty.dat"; // For security and maintainability, should not have filename hardcoded here.

    public void saveToCourseFile(CircularLinkedList<Course> courseList) {
        File file = new File(course);

        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (IOException e) {
            System.err.println("\nAn error occurred while writing to the FILE: " + e.getMessage());
        }

    }

    public CircularLinkedList<Course> retrieveFromCourseFile() {
        File file = new File(course);
        CircularLinkedList<Course> courseList = new CircularLinkedList();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (CircularLinkedList<Course>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file." + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }
    }

    public void saveToProgrammeFile(CircularLinkedList<Programme> programmeList) {
        File file = new File(programme);

        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(programmeList);
            ooStream.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public CircularLinkedList<Programme> retrieveFromProgrammeFile() {
        File file = new File(programme);
        CircularLinkedList<Programme> programmeList = new CircularLinkedList();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            programmeList = (CircularLinkedList<Programme>) oiStream.readObject();
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file." + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return programmeList;
        }
    }

    public void saveToFacultyFile(CircularLinkedList<Faculty> facultyList) {
        File file = new File(faculty);
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file))) {
            ooStream.writeObject(facultyList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public CircularLinkedList<Faculty> retrieveFromFacultyFile() {
        File file = new File(faculty);
        CircularLinkedList<Faculty> facultyList = new CircularLinkedList<>();
        try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
            facultyList = (CircularLinkedList<Faculty>) oiStream.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFaculty file not found.");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("\nError reading from faculty file: " + ex.getMessage());
        } finally {
            return facultyList;
        }
    }

}
