package adv.q1;

import java.util.ArrayList;
import java.util.Scanner;

public class Restraurent {
//    private static Object dish;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        ArrayList<Dish> dishes = RestraurentMenu.menulist;

        do {
            System.out.println("*********** Muskan's Kitchen************" + "" +
                    "\nPress 1 to display Dishes." +
                    "\nPress 2 to search Dish" + "" +
                    "\nPress 0 to Exit");

            choice = sc.nextInt();
            if (choice == 1) {
               dishes.forEach((Dish dish ) -> System.out.println(dishes));

            } else if (choice == 2) {
                int id = sc.nextInt();

                if (id < 1 || id > dishes.size()) {
                    System.out.println("This dish is not available");
                } else {
                    for (int x = 0; x < dishes.size(); x++){
                        if (dishes.get(x).dishID == id) {

                            System.out.println("Dish found....");
                            System.out.println(dishes.get(x));
                        }
                    }
                }
            }
        } while (choice != 0);
    }
}
