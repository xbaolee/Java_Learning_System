package dao;

/**
 *
 * @author ThyeHan
 */
import entity.*;
import java.io.*;
import adt.*;

public class StudentRegistrationDAO {

    private String fileName = "courses.dat";

    public void saveToFile(SortedArrayList<Student> courseList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SortedArrayList<Student> retrieveFromFile() {
        File file = new File(fileName);
        SortedArrayList<Student> courseList = new SortedArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (SortedArrayList<Student>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }
    }
}
