package dao;

import adt.*;
import entity.*;
import java.io.*;

/**
 *
 * @author Jia Hao
 */
public class ProgrammeDAO {

    private String fileName = "programme.dat";

    public void saveToFile(ArrayList<Programme> programmeList) {
        File file = new File(fileName);
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

    public ArrayList<Programme> retrieveFromFile() {
        File file = new File(fileName);
        ArrayList<Programme> programmeList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            programmeList = (ArrayList<Programme>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return programmeList;
    }
}
