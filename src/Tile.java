public class Tile {
    protected int terrain;
    protected char stander = ' ';
    protected boolean isEmpty;

    public Tile(int terrain, boolean isEmpty){
        this.isEmpty = isEmpty;
        this.terrain = terrain;
    }

    public int getTerrain() {
        return terrain;
    }
    public int getTerrainChar() {
        return terrain;
    }

    public void setTerrain(char terrain) {
        this.terrain = terrain;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }
}