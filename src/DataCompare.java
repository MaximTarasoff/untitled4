import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DataCompare {

    public static void main(String[] args) {
        final String file = "C:\\Users\\m.tarasov\\IdeaProjects\\untitled4\\src\\files\\Student.txt";
        String line = null;
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            FileReader fReader = new FileReader(file);
            BufferedReader fileBuff = new BufferedReader(fReader);
            while ((line = fileBuff.readLine()) != null) {
                fileContents.add(line);
            }
            fileBuff.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(fileContents.contains("Mark Sagal"));
    }


}
