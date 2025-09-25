import java.util.Scanner;
public class Main {

    public static void actCharacter(Guy[] charList, Field f){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите 1, чтобы перемещаться, 2 - чтобы взаимодействовать с полем, 3 - чтобы выйти.");
        int k = scan.nextInt();
        if(k == 1)
        {
            System.out.println("Введите координаты конечной клетки.");
            int x = scan.nextInt(), y = scan.nextInt();
            if(f.gameField[x][y].isEmpty)
            {
                charList[0].moveToPos(x, y);
                f.redo(charList);
            }
            else{
                System.out.println("На клетке стоит " + charList[1].name + "!");
                Battle b = new Battle(charList[0], charList[1], f);
                if(b.flag) {
                    charList[1].active = false;
                    charList[0].moveToPos(x, y);
                }
                else charList[0].active = false;
                f.redo(charList);
            }
            actCharacter(charList, f);
        }
        if(k == 2)
        {
            f.fieldWork();
            actCharacter(charList, f);
        }
    }
    public static void main(String[] args) {
        Guy[] charList = new Guy[10];
        charList[0] = new Spy(100, 100, 7, 9, new Position(0, 0), 1);
        charList[1] = new Soldier(150,150, 8, 10, new Position(2, 3), 1);
        Field f = new Field();
        f.redo(charList);
        actCharacter(charList, f);
    }
}