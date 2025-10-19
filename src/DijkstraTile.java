public class DijkstraTile{
    protected int x;
    protected int y;
    protected double distance;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public DijkstraTile(int x, int y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}
