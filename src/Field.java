import java.util.Random;
import java.util.Scanner;

public class Field {
    protected Tile[][] gameField = new Tile[20][20];
    Random rand;
    public Field(){
        rand = new Random();
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                this.gameField[i][j] = new Tile(rand.nextInt(5));
            }
        }
    }

    public void printField(){
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                System.out.print(gameField[i][j].getTerrain() + " ");
            }
            System.out.println();
        }
    }

    public void getTileInfo(int i, int j){
        int type = gameField[i][j].getTerrain();
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
