package adv.q5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {

        BufferedReader reader = null;

        int charCount = 0, wordCount = 0, lineCount = 0;

        try {
            reader = new BufferedReader(new FileReader("src/adv/q5/lear.txt"));
            String presentLine = reader.readLine();

            while (presentLine != null) {

                lineCount++;

                String[] words = presentLine.split(" ");

                wordCount = wordCount + words.length;

                presentLine = reader.readLine();
            }

            System.out.println("No. of Lines    :" + lineCount);
            System.out.println("No. of Words    :" + wordCount);
            System.out.println("No. of Character    :" + charCount);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
                ;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
