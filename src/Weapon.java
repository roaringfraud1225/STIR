public class Weapon extends Item{
    public int attack_rate;

    public Weapon(int attack, String s){
        super(s);
        this.attack_rate = attack;
    }
}
