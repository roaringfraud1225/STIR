public interface Movable {
    boolean canMoveTo(int x, int y, Field f) throws ImmovableException;

    void moveToPos(int x, int y, Field f) throws ImmovableException;

    double getShortestWay(Field field, int x0, int y0, int x, int y);
}
