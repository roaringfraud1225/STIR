import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void actCharacter(ArrayList<Guy> charList, Field f) throws ImmovableException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите 1, чтобы перемещаться, 2 - чтобы узнать тип местности в клетке, 3 - чтобы вычислить кратчайший маршрут между 2 клетками, 4 - чтобы выйти.");
        int k = scan.nextInt();
        if(k == 1)
        {
            System.out.println("Введите координаты конечной клетки.");
            int y = scan.nextInt()-1, x = scan.nextInt()-1;
            if(x > 4 || y > 4 || x < 0 || y < 0){
                System.out.println("Введены некорректные координаты.");
                f.printField();
            }
            else if(f.gameField[x][y].isEmpty)
            {
                charList.get(0).moveToPos(x, y, f);
                f.redo(charList);
                f.printField();
            }
            else{
                System.out.println("На клетке находится " + charList.get(1).name + "!");
                Battle b = new Battle(charList.get(0), charList.get(1), f);
                if(b.flag) {
                    charList.get(1).active = false;
                    charList.get(0).moveToPos(x, y, f);
                }
                else charList.get(0).active = false;
                f.redo(charList);
                f.printField();
            }

            actCharacter(charList, f);
        }
        if(k == 2)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Введите координаты клетки.");
            int x = input.nextInt()-1, y = input.nextInt()-1;
            f.getTileInfo(x, y);
            f.printField();

            actCharacter(charList, f);
        }
        if(k == 3)
        {
            int x0 = 0, y0 = 0;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++)
                {
                    if(f.gameField[i][j].stander == 'Д')
                    {
                        x0 = i;
                        y0 = j;
                        break;
                    }
                }
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Введите координаты конечной клетки.");
            int x = input.nextInt()-1, y = input.nextInt()-1;
            double distance = charList.get(0).getShortestWay(f, x0, y0, x, y);
            ArrayList<Position> path = charList.get(0).path;
            if(distance == -1)
            {
                System.out.println("Этой клетки нельзя достичь.");
            }
            else {
                System.out.println("Этой клетки можно достичь за " + distance + " хода.");
                f.printFieldWithPath(path);
            }
            f.printField();
            actCharacter(charList, f);
        }
    }
    public static void main(String[] args) throws ImmovableException {
        ArrayList<Guy> charList = new ArrayList<>();
        charList.add(new Spy(100, 100, 7, 2, new Position(0, 0), 1));
        charList.add(new Soldier(150,150, 8, 4, new Position(2, 3), 1));
        Field f = new Field();
        f.redo(charList);
        f.printField();
        actCharacter(charList, f);
    }
}