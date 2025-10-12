public interface Movable {
    boolean canMoveTo(int x, int y, Field f) throws ImmovableException;

    void moveToPos(int x, int y, Field f) throws ImmovableException;

}
