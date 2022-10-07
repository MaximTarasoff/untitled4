package data;

import java.util.Arrays;

public class DataMyArray {
     public String[][] arrayOfWords = new String[60][2];
     public int count;

     public void copyToNewArray(String[][] arrayOfWords){
          DataMyArray newArray = new DataMyArray();
          System.out.println(Arrays.deepToString(Arrays.copyOf(arrayOfWords, count)));
     }
}
