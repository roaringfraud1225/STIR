import javafx.geometry.Pos;

import javax.management.BadAttributeValueExpException;

public class Guy {
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
    Field gameField;
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

    public void moveToPos(int x, int y){
        this.pos = new Position(x, y);
    }

    public Position getPos() {
        return pos;
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
}
