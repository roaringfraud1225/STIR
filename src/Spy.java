import java.util.Random;

public class Spy extends Guy{

    private String[] responses = new String[] {"коммунизм - это будущее!", "нацизм - это античеловеческая идеология", "идеи Ленина вечны!", "нацисты - враги Германии!", "коммунисты борятся с нацистскими угнетателями!"};
    public Spy(int hp, int max_hp, int attack, int defence, Position pos, int lv) {
        super("Штирлиц", hp, max_hp, attack, defence, pos, lv);
    }

    @Override
    public void act(Guy enemy){
        enemy.exhaustion = Math.max(100, enemy.exhaustion + lv * 20);
        if(enemy.exhaustion == 100)
        {
            enemy.active = false;
            System.out.println(name + " вербует " + enemy.name + "а!");
        }
        else{
            Random rand = new Random();

            System.out.println(name + " убеждает " + enemy.name + "а в том, что " + responses[rand.nextInt(5)]);
            System.out.println(enemy.name + "верит ему всё больше...");
        }
    }
}
