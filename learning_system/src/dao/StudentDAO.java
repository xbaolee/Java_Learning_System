package dao;

import adt.*;
import entity.*;
import java.io.*;

/**
 *
 * @author Jia Hao
 */
public class StudentDAO {

    private String fileName = "student.dat";

    public void saveToFile(ArrayList<StudentInfo> studentList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(studentList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ArrayList<StudentInfo> retrieveFromFile() {
        File file = new File(fileName);
        ArrayList<StudentInfo> studentList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            studentList = (ArrayList<StudentInfo>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return studentList;
    }
}
