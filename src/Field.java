import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Field {
    public Tile[][] gameField = new Tile[5][5];

    protected ArrayList<Guy> charList;
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
    public void redo(ArrayList<Guy> charList){
        this.charList = charList;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++)
            {
                gameField[i][j].isEmpty = true;
                gameField[i][j].stander = ' ';
            }
        }
        if(charList.get(0).active) {
            this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].stander = 'Д';
            this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].isEmpty = false;
            if(this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].getTerrain() == 1 || this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].getTerrain() == 4){
                this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].terrain--;
            }
        }
        if(charList.get(1).active) {
            this.gameField[charList.get(1).getPos().getX()][charList.get(1).getPos().getY()].stander = 'Ф';
            this.gameField[charList.get(1).getPos().getX()][charList.get(1).getPos().getY()].isEmpty = false;
            if(this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].getTerrain() == 1 || this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].getTerrain() == 4){
                this.gameField[charList.get(0).getPos().getX()][charList.get(0).getPos().getY()].terrain--;
            }
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
            System.out.println("На клетке с координатами ("+ i + ", " + j+ ") находится равнина.");
        if(type == 1) //Shack
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится здание.");
        if(type == 2) //Road
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится дорога.");
        if(type == 3) //Hills
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится холм.");
        if(type == 4) //Barricade
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится баррикада.");
        if(type == 5) //
            System.out.println("На клетке с координатами ("+ i + ", " + j + ") находится болото.");
        if(owner == ' ')
            System.out.println("В данный момент на ней никого нет.");
        if(owner == 'Ф')
            System.out.println("В данный момент на ней стоит " + charList.get(1).name + ".");
        if(owner == 'Ш')
            System.out.println("В данный момент на ней стоит " + charList.get(0).name + ".");
    }
}
