package adv.q15;

import java.io.File;
import java.util.Date;

public class ModifiedTime {
    public static void main(String[] args) {

        File file = new File("src/adv/q15/Modifying.txt");
        Date date = new Date(file.lastModified());

        System.out.println("This file was last modified on: "+date+ "\n");
    }
}
