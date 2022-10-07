import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;


public class Main {

    public static int count = 0;
    public static void main(String[] args) {
        
        DataMyArray array = new DataMyArray();
        //создать двойной массив 2хбесконечноть в одну часть занести названия (id, Trminal ID и тд) в другую числовые значения
        String arrayOfWords[][] = new String[60][2];
        String idNumber = "id=";
        String terminalIdNumber = "TERMINAL_ID=";
        String ticketNumber = "TICKET_NUMBER=";
        String betNumber = "BET=";

        String textForSearch;
        String endWord = null;
        FileReader example2 = null;
        BufferedReader buff = null;
        try {
            example2 = new FileReader("C:\\Users\\m.tarasov\\IdeaProjects\\untitled3\\src\\textFiles\\StolotoJavaFileForSearch.txt");
            buff = new BufferedReader(example2);
            while (true) {
                // считывается строка из файла scores.txt
                String line = buff.readLine();

                // проверка достижения конца файла
                if (line == null) break;
                textForSearch =  line;

                otriutOrOutrin(textForSearch, terminalIdNumber);
                indexIdSearchPlusNumberWriting(idNumber, textForSearch, arrayOfWords);
                indexIdSearchPlusNumberWriting(terminalIdNumber, textForSearch, arrayOfWords);
                indexIdSearchPlusNumberWriting(ticketNumber, textForSearch, arrayOfWords);
                indexIdSearchPlusNumberWriting(betNumber, textForSearch, arrayOfWords);
                System.out.println();
            } // конец цикла while
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                buff.close();
                example2.close();
            }
            catch(IOException e1){
                e1.printStackTrace();
            }
        }

        /*for (String[] arrayOfWord : arrayOfWords) {
            for (int j = 0; j < count + 1; j++) {
                System.out.print(arrayOfWord[j] + "\t");
            }
            System.out.println();
        }*/
        System.out.println(Arrays.deepToString(Arrays.copyOf(arrayOfWords, count)));

    }

    // Метод находит слово в строке и возвращает его значение (например нашел слово id= и возвращает его значение в логах "28")
    private static void indexIdSearchPlusNumberWriting(String idNumber, String textForSearch, String arrayOfWords[][]) {
        int indexID = textForSearch.indexOf(idNumber);
        if (indexID != - 1) {
            System.out.print(idNumber + ": ");
            wordSearch(textForSearch, indexID, idNumber,arrayOfWords);
        }
    }

    // метод пишет  данные какого запроса мы получим далее (OTRIN or OTROUT)
    static void otriutOrOutrin(String textForSearch, String idNumber) {
        int indexID = textForSearch.indexOf(idNumber);
        StringBuilder wordForEquaels = new StringBuilder();
        String in = "OTRIN";
        String out = "OTROUT";
        int indexIn = textForSearch.indexOf("OTRIN");
        int indexOut = textForSearch.indexOf("OTROUT");
        int transactionFlag = textForSearch.indexOf("TRANSACTION_FLAGS");

        if (indexIn != - 1) {

            for(int i = indexIn; i < textForSearch.length(); i++) {
                if(i == indexID - 1){
                    break;
                }
                wordForEquaels.append(textForSearch.charAt(i));
            }
            System.out.println(wordForEquaels);
        }
        if (indexOut != - 1) {
            for(int i = indexOut; i < textForSearch.length(); i++) {
                if (textForSearch.charAt(i) == '&'){
                    break;
                }
                wordForEquaels.append(textForSearch.charAt(i));
            }
            System.out.println(wordForEquaels);
        }
    }


    public static StringBuilder wordSearch(String textForSearch, int indexID, String idNumber, String arrayOfWords[][]){
        StringBuilder wordForEquaels = new StringBuilder();
        
        for(int i = indexID + idNumber.length(); i < textForSearch.length(); i++) {
            if (textForSearch.charAt(i) == '&' || textForSearch.charAt(i) == '|'){
                break;
            }
            // для параметров с кавычками
            if(textForSearch.charAt(i) == '"'){
            continue;
            } else {
            wordForEquaels.append(textForSearch.charAt(i));
            }
        }
        System.out.println(wordForEquaels);
        arrayOfWords[count][1] = String.valueOf(wordForEquaels);
        arrayOfWords[count++][0] = idNumber;
        return wordForEquaels;
    }
}