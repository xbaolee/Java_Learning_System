package dao;

import adt.*;
import entity.*;
import java.io.*;

/**
 *
 * @author Jia Hao
 */
public class TutorialGroupDAO {

    private String fileName = "tutorialGroup.dat";

    public void saveToFile(ArrayList<TutorialGroup> tutorialGroupList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorialGroupList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ArrayList<TutorialGroup> retrieveFromFile() {
        File file = new File(fileName);
        ArrayList<TutorialGroup> tutorialGroupList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            tutorialGroupList = (ArrayList<TutorialGroup>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        }
        return tutorialGroupList;
    }
}
