import java.util.Scanner;
public class Main {

    public static void actCharacter(Guy[] charList, Field f){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите 1, чтобы перемещаться, 2 - чтобы узнать тип местности в клетке, 3 - чтобы выйти.");
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
                charList[0].moveToPos(x, y, f);
                f.redo(charList);
                f.printField();
            }
            else{
                System.out.println("На клетке находится " + charList[1].name + "!");
                Battle b = new Battle(charList[0], charList[1], f);
                if(b.flag) {
                    charList[1].active = false;
                    charList[0].moveToPos(x, y, f);
                }
                else charList[0].active = false;
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
    }
    public static void main(String[] args) {
        Guy[] charList = new Guy[10];
        charList[0] = new Spy(100, 100, 7, 2, new Position(0, 0), 1);
        charList[1] = new Soldier(150,150, 8, 4, new Position(2, 3), 1);
        Field f = new Field();
        f.redo(charList);
        f.printField();
        actCharacter(charList, f);
    }
}