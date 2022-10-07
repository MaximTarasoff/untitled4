import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Examples {

    public static void main(String[] args) throws IOException {
        System.out.println(searchScanner());
    }
    private final static String SEARCH_TOKEN = "51010000018397126";

    public static List<String> searchScanner() throws IOException {
        Path file = Path.of("C:\\Users\\m.tarasov\\IdeaProjects\\untitled4\\src\\files\\AgentJavaFileForCheck.txt");
        List<String> found = new ArrayList<>();

        final Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine();
            if (lineFromFile.contains(SEARCH_TOKEN)) {
                // a match!
                found.add(lineFromFile);
            }
        }
        return found;
    }
}