import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private List<Guest> guests;
    private List<Room> rooms;

    private int sz;
    public Hotel(int sz){
        this.guests = new ArrayList<>(sz);
        this.rooms = new ArrayList<>(sz);
        for(int i = 0; i < sz; i++)
        {
            rooms.add(new Room(i));
        }
        this.sz = sz;
    }

    public void reserveRoom()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите Ваше имя:");
        String name = in.nextLine();
        System.out.println("Введите Вашу фамилию:");
        String surname = in.nextLine();
        System.out.println("Введите Ваш возраст:");
        int age = in.nextInt();
        System.out.println("Введите номер комнаты: ");
        if(age < 14)
        {
            System.out.println("Вы недостаточно выросли, чтобы заселиться в нашем отеле!");
            return;
        }
        int num = in.nextInt();
        if(num < sz &&(rooms.get(num) == null || rooms.get(num).IsFree()) ){
            boolean vip = false;
            if(num % 15 == 0) vip = true;
            rooms.set(num, new Room(num));
            Guest guest = new Guest(name, surname, age, num);
            guests.add(guest);
            rooms.get(num).SetGuest(guest);
            rooms.get(num).SetFree(false);
            System.out.println("Заселение прошло успешно!");
        }
        else if(num >= sz){
            System.out.println("Такой комнаты нет в отеле.");
        }
        else{
            System.out.println("Выбранная Вами комната занята.");
        }
    }

    public void freeRoom(){
        Scanner in = new Scanner(System.in);
        System.out.println("Из какой комнаты Вы хотите выселиться?");
        int num = in.nextInt();
        if(rooms.get(num).IsFree())
        {
            System.out.println("Эта комната и так свободна.");
        }
        else{
            Guest guest = rooms.get(num).getSettler();
            guests.remove(guest);
            rooms.get(num).SetGuest(null);
            System.out.println("Выселение прошло успешно.");
        }
    }

    public void getGuests()
    {
        for(int i = 0; i < rooms.size(); i++)
        {
            Room current_room = rooms.get(i);
            if(current_room.getSettler() != null)
            {
                Guest current = current_room.getSettler();
                System.out.println(current.getName() + " " + current.getSurname() + " в возрасте " + current.getAge() + " " + current.getWordForAge(current.getAge()) + " проживает в комнате " + current.getNum() + ".");
            }
        }
    }
    public void chooseActions()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введитее 1, если хотите заселиться, 2 - если хотите выселиться, 3 - если хотите просмотреть список гостей, и 4 - если хотите выйти.");
        int x = in.nextInt();
        if(x == 1)
        {
            reserveRoom();
            chooseActions();
        }
        else if (x == 2) {
            freeRoom();
            chooseActions();
        }
        else if(x == 3){
            getGuests();
            chooseActions();
        }
        else{
            return;
        }
    }
}
