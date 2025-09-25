import java.util.Random;
import java.util.Scanner;

public class Battle {
    protected Guy attacker;
    protected Guy defender;

    public boolean flag;

    public void whoWon(){
        if(attacker.active){
            System.out.println(attacker.name + " победил!");
            flag = true;
        }
        else{
            System.out.println(attacker.name + " проиграл!");
            flag = false;
        }
    }
    public Battle(Guy attacker, Guy defender, Field field){
        this.attacker = attacker;
        this.defender = defender;
        System.out.println("НАЧИНАТСЯ БИТВА - " + attacker.name + " против " + defender.name + "а!");
        while(attacker.active && defender.active){
            System.out.println("Ход " + attacker.name + "a!");
            Scanner scan = new Scanner(System.in);
            System.out.println("Нажмите 1, если хотите атаковать, 2, если хотите действовать, 3, если хотите проверить врага, 4, если хотите проверить себя.");
            int action = scan.nextInt();
            if(action == 1){
                attacker.attackEnemy(defender);
            }
            if(action == 2){
                attacker.act(defender);
            }
            if(action == 3)
            {
                attacker.check(defender);
            }
            if(action == 4)
            {
                attacker.check(attacker);
            }
            if(defender.active){
                System.out.println("Ход " + defender.name + "a!");
                Random rand = new Random();
                int enemyAct = rand.nextInt(2);
                if(enemyAct == 0)
                {
                    defender.attackEnemy(attacker);
                }
                else{
                    defender.act(attacker);
                }
            }
            System.out.println();
        }
        whoWon();
    }

}
