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
        pos = new Position(x, y);
    }

    public void attackEnemy(Guy enemy){
        enemy.hp = Math.max(0, enemy.hp - (attack*lv-enemy.defence));
        if(enemy.hp == 0)
        {
            enemy.active = false;
            System.out.println(name + " убивает " + enemy.name + "а!");
        }
        exp += 5;
        if(exp >= lv * 10){
            exp -= lv*10;
            lv++;
            System.out.println(name + " повышает свой уровень до " + lv + "-го!");
        }

    }

    public void act(Guy enemy){

    }
}
