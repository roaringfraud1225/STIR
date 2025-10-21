public class Food extends Item{
    public int heal_rate;

    public Food(int heal_rate, String s){
        super(s);
        this.heal_rate = heal_rate;
    }
}
