import java.util.Random;
import java.util.Scanner;

public class Field {
    public Tile[][] gameField = new Tile[5][5];

    protected Guy[] charList;
    Random rand;

    public Field(){
        rand = new Random();
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                this.gameField[i][j] = new Tile(rand.nextInt(5), true);
            }
        }
    }
    public void redo(Guy[] charList){
        this.charList = charList;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++)
            {
                gameField[i][j].isEmpty = true;
                gameField[i][j].stander = ' ';
            }
        }
        if(charList[0].active) {
            this.gameField[charList[0].getPos().getX()][charList[0].getPos().getY()].stander = 'Ш';
            this.gameField[charList[0].getPos().getX()][charList[0].getPos().getY()].isEmpty = false;
        }
        if(charList[1].active) {
            this.gameField[charList[1].getPos().getX()][charList[1].getPos().getY()].stander = 'Ф';
            this.gameField[charList[1].getPos().getX()][charList[1].getPos().getY()].isEmpty = false;
        }
    }

    public Tile[][] getGameField() {
        return gameField;
    }

    public void printField(){
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(gameField[i][j].stander != ' ')
                    System.out.print(gameField[i][j].stander + " ");
                else System.out.print(gameField[i][j].getTerrain() + " ");
            }
            System.out.println();
        }
    }

    public void getTileInfo(int i, int j){
        int type = gameField[i][j].getTerrain(), owner = gameField[i][j].stander;
        if(type == 0) //Plains
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится равнина.");
        if(type == 1) //Water
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится лужа.");
        if(type == 2) //Sand
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находятся пески.");
        if(type == 3) //Hills
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится гора.");
        if(type == 4) //Swamp
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится болото.");
        if(type == 5) //
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится болото.");
        if(owner == ' ')
            System.out.println("В данный момент на ней никого нет.");
        if(owner == 'Ф')
            System.out.println("В данный момент на ней стоит " + charList[1].name + ".");
        if(owner == 'Ш')
            System.out.println("В данный момент на ней стоит " + charList[0].name + ".");
    }

    public void fieldWork(){
        System.out.println("Нажмите 1, если хотите вывести игровое поле, 2 - если хотите узнать тип местности в клетке, 3 - чтобы выйти.");
        Scanner input = new Scanner(System.in);
        int type = input.nextInt();
        if(type == 1)
        {
            printField();
            fieldWork();
        }
        if(type == 2) {
            System.out.println("Введите координаты клетки.");
            int x = input.nextInt(), y = input.nextInt();
            getTileInfo(x, y);
            fieldWork();
        }
    }
}
