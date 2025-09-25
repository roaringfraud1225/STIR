public class Tile {
    protected int terrain;
    protected boolean isEmpty;

    public Tile(int terrain){
        this.isEmpty = true;
        this.terrain = terrain;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}