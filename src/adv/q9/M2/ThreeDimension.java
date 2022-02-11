package adv.q9.M2;

import adv.q9.M1.TwoDimension;

public class ThreeDimension extends TwoDimension {
    private int z;
    public ThreeDimension() {
        super();
    }

    public ThreeDimension(int a, int b, int c) {
        super(a,b);
        this.z = c;
    }

    @Override
    public String toString() {
        return "ThreeDim{" +
                "z=" + z +
                '}';
    }
}
