import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Guy implements Movable {
    protected int step = 0;
    protected String name;
    protected int hp;
    protected int attack;
    protected int defence;
    protected int max_hp;
    protected Position pos;
    protected int lv;
    protected int exp;
    protected int exhaustion = 0;
    protected boolean active = true;
    public Guy(String name, int hp, int max_hp, int attack, int defence, Position pos, int lv){
        this.attack = attack;
        this.defence = defence;
        this.name = name;
        this.hp = hp;
        this.max_hp = max_hp;
        this.pos = pos;
        this.lv = lv;
        this.exp = 0;
    }



    @Override
    public boolean canMoveTo(int x, int y, Field f) throws ImmovableException {
        if(f.gameField[x][y].getTerrain() == 1)
        {
            throw new ImmovableException("Вы не можете посетить это здание - у Вас нет ключей!");
        }
        else if(f.gameField[x][y].getTerrain() == 4)
        {
            throw new ImmovableException("");
        }
        else return true;
    }
    @Override
    public void moveToPos(int x, int y, Field f) throws ImmovableException {
        /*try{
            canMoveTo(x, y, f);
        }
        catch (ImmovableException e){
            System.out.println(e.getMessage());
        }
        finally {
            if(canMoveTo(x, y, f))
                this.pos = new Position(x, y);
        }*/
        if(canMoveTo(x, y, f))
            this.pos = new Position(x, y);
    }
    public Position getPos() {
        return pos;
    }
    private boolean isTileAccessible(Tile tile) {
        return tile.getTerrain() != 1 && tile.getTerrain() != 4;
    }
    private double getMoveCost(Tile tile) {
        switch (tile.getTerrain()) {
            case 0:
                return 1.0;
            case 2:
                return 0.5;
            case 3:
                return 1.5;
            case 5:
                return 2.0;
            default:
                return Double.MAX_VALUE;
        }
    }
    public ArrayList<Position> path = new ArrayList<>();
    public void getPath(Position[][] previous, int x0, int y0, int x, int y) {

        Position current = new Position(x, y);

        while (current != null) {
            path.add(0, current);
            int x1 = current.getX();
            int y1 = current.getY();
            if (x1 == x0 && y1 == y0) {
                break;
            }
            current = previous[x1][y1];
        }
        return;
    }
    @Override
    public double getShortestWay(Field field, int x0, int y0, int x, int y){
        path.clear();
        if (x0 < 0 || x0 >= 5 || y0 < 0 || y0 >= 5 ||
                x < 0 || x >= 5 || y < 0 || y >= 5) {
            return -1;
        }
        if (!isTileAccessible(field.gameField[x0][y0]) ||
                !isTileAccessible(field.gameField[x][y])) {
            System.out.println(x0 + " " + y0 + " " + x + " " + y);
            return -1;
        }
        double[][] distances = new double[5][5];
        boolean[][] visited = new boolean[5][5];
        Position[][] previous = new Position[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                distances[i][j] = Double.MAX_VALUE;
                previous[i][j] = null;
            }
        }
        distances[x0][y0] = 0;

        PriorityQueue<DijkstraTile> queue = new PriorityQueue<>(Comparator.comparingDouble(DijkstraTile -> DijkstraTile.getDistance()));
        queue.add(new DijkstraTile(x0, y0, 0));

        int[][] neighbourDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            DijkstraTile current = queue.poll();
            int x1 = current.getX();
            int y1 = current.getY();

            if (x1 == x && y1 == y) {
                getPath(previous, x0, y0, x, y);
                return distances[x1][y1];
            }

            if (visited[x1][y1]) {
                continue;
            }
            visited[x1][y1] = true;

            for (int[] direction : neighbourDirections) {
                int backX = x1 + direction[0];
                int backY = y1 + direction[1];

                // Проверяем границы поля
                if (backX >= 0 && backX < 5 && backY >= 0 && backY < 5) {
                    Tile neighbourTile = field.gameField[backX][backY];

                    // Проверяем, можно ли посетить соседнюю клетку
                    if (isTileAccessible(neighbourTile)) {
                        double moveCost = getMoveCost(neighbourTile);
                        double newDistance = distances[x1][y1] + moveCost;

                        // Если нашли более короткий путь
                        if (newDistance < distances[backX][backY]) {
                            distances[backX][backY] = newDistance;
                            previous[backX][backY] = new Position(x1, y1);
                            queue.add(new DijkstraTile(backX, backY, newDistance));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void attackEnemy(Guy enemy){
        System.out.println("* " + name + " атакует " + enemy.name + "a!");
        enemy.hp = Math.max(0, enemy.hp - (attack*lv-enemy.defence*lv));
        if(enemy.hp == 0)
        {
            enemy.active = false;
            System.out.println("* " + name + " убивает " + enemy.name + "а!");
        }
        exp += 5;
        if(exp >= lv * 10){
            exp -= lv*10;
            lv++;
            System.out.println("* " + name + " повышает свой уровень до " + lv + "-го!");
        }

    }

    public void act(Guy enemy){

    }

    public void check(Guy enemy){
        if(enemy.getClass() == Spy.class){
            System.out.println("* ШТИРЛИЦ - " + enemy.lv + " УР, " + enemy.hp + " ОЗ, " + enemy.attack + " АТК " + enemy.defence + " ЗАЩ.");
            System.out.println("* ШТАНДАРТЕНФЮРЕР СС. ПЕРВОКЛАССНЫЙ СОВЕТСКИЙ РАЗВЕДЧИК, РЕШИВШИЙ ВЗЯТЬ ВСЁ В СВОИ РУКИ.");
        }
        if(enemy.getClass() == Soldier.class){
            System.out.println("* ФАШИСТ - " + enemy.lv + " УР, " + enemy.hp + " ОЗ, " + enemy.attack + " АТК " + enemy.defence + " ЗАЩ.");
            System.out.println("* РЯДОВОЙ СОЛДАТ ГЕРМАНСКОГО РЕЙХА. ВРАГ ЧЕЛОВЕЧЕСТВА, КОТОРОМУ ПРОМЫЛИ МОЗГИ ЕМУ ПОДОБНЫЕ.");
        }
    }
}
