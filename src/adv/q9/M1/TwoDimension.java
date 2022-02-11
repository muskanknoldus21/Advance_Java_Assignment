package adv.q9.M1;

public class TwoDimension {
    private int xAxisCoordinate, yAxisCoordinate;
    public TwoDimension() {

    }

    public TwoDimension(int xPoints, int yPoints) {
        this.xAxisCoordinate = xPoints;
        this.yAxisCoordinate = yPoints;
    }

    @Override
    public String toString() {
        return "TwoDim{" +
                "xCoordinate=" + xAxisCoordinate +
                ", yCoordinate=" + yAxisCoordinate +
                '}';
    }
}
