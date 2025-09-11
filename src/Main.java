import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Сколько комнат в отеле?");
        int sz = in.nextInt();
        Hotel MttResort = new Hotel(sz);
        MttResort.chooseActions();
    }
}