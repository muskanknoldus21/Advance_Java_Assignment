package adv.q9.M;


import adv.q9.M2.ThreeDimension;
import adv.q9.M1.TwoDimension;

public class MAIN {
    public static void main(String[] args) {
        TwoDimension ref;
        System.out.println(ref = new TwoDimension(2,4));
        System.out.println(ref = new ThreeDimension(3,9,12));
    }
}
