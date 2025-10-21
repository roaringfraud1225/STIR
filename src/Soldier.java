import java.util.ArrayDeque;
import java.util.Random;

public class Soldier extends Guy{

    protected int step = 0;
    public Soldier(int hp, int max_hp, int attack, int defence, Position pos, int lv) {
        super("Солдат", hp, max_hp, attack, defence, pos, lv);
        this.weapon = new Weapon(5, "Пистолет Вальтер Р38");
        this.armor = new Armor(4, "Каска солдата Вермахта");
        this.inventory = new ArrayDeque<>();
    }
    @Override
    public void act(Guy enemy){
        System.out.println("* " + name + " перезаряжает оружие!");
        System.out.println("* " + enemy.name + " стоит наготове.");
    }
}
