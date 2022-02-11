package adv.q1;

import java.time.LocalTime;

public class Dish {
    int dishID;
    String dishName;
    LocalTime cTime;

    Dish(int dishID, String dishName, LocalTime cTime) {

        this.dishID = dishID;
        this.dishName = dishName;
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "dishID=" + dishID +
                ", dishName='" + dishName + '\'' +
                ", Time=" + cTime +
                '}';
    }
}
