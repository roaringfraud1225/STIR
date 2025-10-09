public interface Movable {
    boolean canMoveTo(int x, int y, Field f);

    void moveToPos(int x, int y, Field f);

}
