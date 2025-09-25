import java.util.Random;

public class Soldier extends Guy{

    public Soldier(int hp, int max_hp, int attack, int defence, Position pos, int lv) {
        super("Фашист", hp, max_hp, attack, defence, pos, lv);
    }
    @Override
    public void act(Guy enemy){
        System.out.println("* " + name + " вскидывает руку к солнцу!");
        System.out.println("* " + enemy.name + " испытывает отвращение к нему.");
    }
}
