package adv.q2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBook {
    public static void main(String[] args) {
        String name, num;

        TreeMap<String, String> phoneBook;
        phoneBook = new TreeMap<>();

        File homeDirectory = new File(System.getProperty("user.home"));
        String phoneBook1 = ".phoneBookDemo";
        File dataFile = new File(homeDirectory, phoneBook1);


        if (!dataFile.exists()) {
            System.out.println("No data found. ");
            System.out.println("Creating new data. ");
            System.out.println("Name of File: " + dataFile.getAbsolutePath());

        } else {
            System.out.println("Reading the data from phone book ");
            try {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNextLine()) {
                    String phoneEntry = sc.nextLine();
                    int separatorPosition = phoneEntry.indexOf('%');
                    if (separatorPosition == -1)
                        throw new IOException("This file is not present in phonebook data file. ");
                    name = phoneEntry.substring(0, separatorPosition);
                    num = phoneEntry.substring(separatorPosition + 1);
                    phoneBook.put(name, num);
                }
            } catch (IOException e) {
                System.out.println("Error in phone book data file. ");
                System.out.println("File name: " + dataFile.getAbsolutePath());
                System.out.println("We can't continue");
                System.exit(1);
            }
        }

        Scanner in = new Scanner(System.in);
        boolean change = false;
        mainLoop:
        while (true) {
            System.out.println("Select the option you want to perform: ");
            System.out.println(" 1.  Add new phone number");
            System.out.println(" 2 . Search a Phone Number");
            System.out.println(" 3 . Remove an entry from your contact list");
            System.out.println(" 4 . List the entire contact list");
            System.out.println(" 5 . Exit");
            System.out.println("Enter the action number (1-5): ");

            int c;
            if (in.hasNextLine()) {
                c = in.nextInt();
                in.nextLine();

            } else {
                System.out.println("Inncorrect!! Please enter a valid number. ");
                in.nextLine();
                continue;
            }

            switch (c) {
                case 1:
                    System.out.println("Enter the name: ");
                    name = in.nextLine().trim().toLowerCase();

                    if (name.length() == 0)
                        System.out.println("Name cannot be empty. ");
                    else if (name.indexOf('%') >= 0)
                        System.out.println("Name cannot contain any character \"%\".");
                    else {
                        System.out.println("Enter a phone number: ");
                        num = in.nextLine().trim();

                        if (num.length() == 0)
                            System.out.println("Phone number cannot be blank. ");

                        else {
                            phoneBook.put(name, num);
                            change = true;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter the name of the contact: ");
                    name = in.nextLine().trim().toLowerCase();
                    num = phoneBook.get(name);

                    if (num == null)
                        System.out.println("No number found for the searched contact " + name);
                    else
                        System.out.println("Number found " + name + ":  " + num);
                    break;

                case 3:
                    System.out.println("Enter the name you want to delete details of: ");
                    name = in.nextLine().trim().toLowerCase();
                    num = phoneBook.get(name);

                    if (num == null)
                        System.out.println("No matching result found " + name);
                    else {
                        phoneBook.remove(name);
                        change = true;
                        System.out.println("Contact details successfully deleted " + name);
                    }
                    break;

                case 4:
                    System.out.println("Your contact list contains following entries: ");
                    for (Map.Entry<String, String> entry : phoneBook.entrySet())
                        System.out.println(" " + entry.getKey() + ": " + entry.getValue());
                    break;

                case 5:
                    System.out.println("Exit Program");
                    break mainLoop;
                default:
                    System.out.println("Wrong Action");
            }
        }

        if (change) {
            System.out.println("Saving phone directory changes to file " + dataFile.getAbsolutePath() + "...");
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter(dataFile));
            } catch (IOException e) {
                System.out.println("Arrr.... Can't open file for this output. ");
                return;
            }
            for (Map.Entry<String, String> entry : phoneBook.entrySet())
                out.println(entry.getKey() + "%" + entry.getValue());
            out.close();
            ;
            if (out.checkError())
                System.out.println("Some error while writing. ");
            else
                System.out.println("Hurreyy!! It's done.");
        }
    }
}





