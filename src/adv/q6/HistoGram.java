package adv.q6;

import java.io.*;

public class HistoGram {
    public static void main(String[] args) throws IOException {

        File file = new File("src/adv/q6/Scoreboard.txt");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str;

        int r0to9 = 0, r10to19 = 0,
                r20to29 = 0, r30to39 = 0,
                r40to49 = 0, r50to59 = 0,
                r60to69 = 0, r70to79 = 0,
                r80to89 = 0, r90to99 = 0;

        while ((str = bufferedReader.readLine()) != null) {

            int i = Integer.parseInt(str);
            if (i >= 0 && i <= 9)
                r0to9++;
            else if (i >= 10 && i <= 19)
                r10to19++;

            else if (i >= 20 && i <= 29)
                r20to29++;
            else if (i >= 30 && i <= 39)
                r30to39++;
            else if (i >= 40 && i <= 49)
                r40to49++;
            else if (i >= 50 && i <= 59)
                r50to59++;
            else if (i >= 60 && i <= 69)
                r60to69++;
            else if (i >= 70 && i <= 79)
                r70to79++;
            else if (i >= 80 && i <= 89)
                r80to89++;
            else if (i >= 90 && i <= 99)
                r90to99++;
        }

        System.out.println("00 to 09 : ");
        for (int x = 0; x < r0to9; x++)
            System.out.println("\n");

        System.out.println("10 to 19 : ");
        for (int x = 0; x < r10to19; x++)
            System.out.println("*");
        System.out.println("\n");

        System.out.print("20 to 29 : ");
        for(int x = 0; x < r20to29; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("30 to 39 : ");
        for(int x = 0; x < r30to39; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("40 to 49 : ");
        for(int x = 0; x < r40to49; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("50 to 59 : ");
        for(int x = 0; x < r50to59; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("60 to 69 : ");
        for(int x = 0; x < r60to69; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("70 to 79 : ");
        for(int x = 0; x < r70to79; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("80 to 89 : ");
        for(int x = 0; x < r80to89; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("90 to 99 : ");
        for(int x = 0; x < r90to99; x++)
            System.out.print("*");
        System.out.print("\n");

        System.out.print("100: *");
    }
}
