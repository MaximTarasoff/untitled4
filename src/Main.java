import data.DataMyArray;
import data.DataStringsName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    static DataMyArray array = new DataMyArray(); //создать двойной массив 2хбесконечноть в одну часть занести названия (id, Trminal ID и тд) в другую числовые значения
    //public static int count = 0;
    public static void main(String[] args) {
        DataStringsName dataStringName = new DataStringsName();

        fileReadMethod(dataStringName);
        array.copyToNewArray(array.arrayOfWords);

    }

    private static void fileReadMethod(DataStringsName dataStringName) {
        String textForSearch;
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

                listOfWordsSearch(dataStringName, textForSearch);
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
    }

    private static void listOfWordsSearch(DataStringsName dataStringName, String textForSearch) {
        otriutOrOutrin(textForSearch, dataStringName.terminalIdNumber);
        indexIdSearchPlusNumberWriting(dataStringName.idNumber, textForSearch,array.arrayOfWords);
        indexIdSearchPlusNumberWriting(dataStringName.terminalIdNumber, textForSearch, array.arrayOfWords);
        indexIdSearchPlusNumberWriting(dataStringName.ticketNumber, textForSearch, array.arrayOfWords);
        indexIdSearchPlusNumberWriting(dataStringName.betNumber, textForSearch, array.arrayOfWords);
    }

    // Метод находит слово в строке и возвращает его значение (например нашел слово id= и возвращает его значение в логах "28")
    private static void indexIdSearchPlusNumberWriting(String idNumber, String textForSearch, String[][] array) {
        int indexID = textForSearch.indexOf(idNumber);
        if (indexID != - 1) {
            System.out.print(idNumber + ": ");
            wordSearch(textForSearch, indexID, idNumber,array);
        }
    }

    // метод пишет  данные какого запроса мы получим далее (OTRIN or OTROUT)
    static void otriutOrOutrin(String textForSearch, String idNumber) {
        int indexID = textForSearch.indexOf(idNumber);
        StringBuilder wordForEaquels = new StringBuilder();
        String in = "OTRIN";
        String out = "OTROUT";
        int indexIn = textForSearch.indexOf(in);
        int indexOut = textForSearch.indexOf(out);
        int transactionFlag = textForSearch.indexOf("TRANSACTION_FLAGS");

        if (indexIn != - 1) {

            for(int i = indexIn; i < textForSearch.length(); i++) {
                if(i == indexID - 1){
                    break;
                }
                wordForEaquels.append(textForSearch.charAt(i));
            }
            System.out.println(wordForEaquels);
        }
        if (indexOut != - 1) {
            for(int i = indexOut; i < textForSearch.length(); i++) {
                if (textForSearch.charAt(i) == '&'){
                    break;
                }
                wordForEaquels.append(textForSearch.charAt(i));
            }
            System.out.println(wordForEaquels);
        }
    }


    public static void wordSearch(String textForSearch, int indexID, String idNumber, String[][] arrayOfWords){
        StringBuilder wordForEquals = new StringBuilder();

        for(int i = indexID + idNumber.length(); i < textForSearch.length(); i++) {
            if (textForSearch.charAt(i) == '&' || textForSearch.charAt(i) == '|'){
                break;
            }
            // для параметров с кавычками
            if(textForSearch.charAt(i) == '"'){
            continue;
            } else {
            wordForEquals.append(textForSearch.charAt(i));
            }
        }
        System.out.println(wordForEquals);
        arrayOfWords[array.count][1] = String.valueOf(wordForEquals);
        arrayOfWords[array.count++][0] = idNumber;
    }
}