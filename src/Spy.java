import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Spy extends Guy{

    private final String[] responses = new String[] {"коммунизм - это будущее!", "нацизм - это античеловеческая идеология!", "идеи Ленина вечны!", "нацисты - враги Германии!", "коммунисты борятся с нацистскими угнетателями!"};
    public Spy(int hp, int max_hp, int attack, int defence, Position pos, int lv) {
        super("Диверсант", hp, max_hp, attack, defence, pos, lv);
        this.weapon = new Weapon(5, "Пистолет Токарева");
        this.armor = new Armor(5, "Бронежилет");
        this.inventory = new ArrayDeque<>();
    }

    @Override
    public void special_ability(Field f){
        int[][] neighbourDirections = {{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : neighbourDirections) {
            int x = this.pos.getX() + direction[0], y = this.pos.getY() + direction[1];
            if(x < 0 || x >= 5 || y < 0 || y >= 5){
                continue;
            }
            if(f.gameField[x][y].terrain == 1){
                System.out.println(this.name + " взламывает здание на клетке (" + (x+1) + "," + (y+1) + ")! Теперь к нему имеется доступ.");
                f.gameField[x][y].terrain = 6;
                return;
            }
        }
        System.out.println(this.name + " не обнаружил рядом с собой что-то, что можно взломать.");
    }

    @Override
    public void act(Guy enemy){
        enemy.exhaustion = Math.min(100, enemy.exhaustion + lv * 20);
        if(enemy.exhaustion == 100)
        {
            enemy.active = false;
            System.out.println("* " + name + " вербует " + enemy.name + "а!");
        }
        else{
            Random rand = new Random();

            System.out.println("* " + name + " убеждает " + enemy.name + "а в том, что " + responses[rand.nextInt(5)]);
            System.out.println("* " + enemy.name + " верит ему всё больше...");
        }
        exp += 5;
        if(exp >= lv * 10){
            exp -= lv*10;
            lv++;
            System.out.println("* " + name + " повышает свой уровень до " + lv + "-го!");
        }
    }
}
