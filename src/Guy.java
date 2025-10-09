import javafx.geometry.Pos;

import javax.management.BadAttributeValueExpException;

public class Guy implements Movable{
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

    @Override
    public boolean canMoveTo(int x, int y, Field f){
        if(f.gameField[x][y].getTerrain() == 1)
        {
            System.out.println("На этой клетке находится здание. У Вас нет ключей от него.");
            return false;
        }
        if(f.gameField[x][y].getTerrain() == 4)
        {
            System.out.println("На этой клетке находится баррикада. Вы не можете сюда переместиться.");
            return false;
        }
        return true;
    }
    @Override
    public void moveToPos(int x, int y, Field f){
        if(canMoveTo(x, y, f))
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
