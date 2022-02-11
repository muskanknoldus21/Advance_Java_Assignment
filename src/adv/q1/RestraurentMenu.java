package adv.q1;

import java.time.LocalTime;
import java.util.ArrayList;

public class RestraurentMenu {

    static ArrayList<Dish> menulist;

    static {

        Dish manchurian = new Dish(1,"Manchurian", LocalTime.now());
        Dish FriedRice = new Dish(2, "Fried Rice", LocalTime.now());
        Dish vegBiryani = new Dish(3, "Veg Biryani", LocalTime.now());
        Dish dalMakhani = new Dish(4, "Dal Makhani", LocalTime.now());
        Dish ShahiPaneer= new Dish(5, "Shahi Paneer", LocalTime.now());

        menulist = new ArrayList<Dish>();

        menulist.add(manchurian);
        menulist.add(FriedRice);
        menulist.add(vegBiryani);
        menulist.add(dalMakhani);
        menulist.add(ShahiPaneer);
    }
}
