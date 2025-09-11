public class Room {
    private int number;
    private boolean free = true;
    private boolean vip;

    private Guest settler;

    public Room(int number){
        this.number = number;
        this.vip = (number % 15 == 0);
    }

    public void SetGuest(Guest guest){
        this.settler = guest;
    }
    public boolean IsFree(){
        return free;
    }

    public void SetFree(boolean stat){
        free = stat;
    }

    public Guest getSettler()
    {
        return settler;
    }

}
