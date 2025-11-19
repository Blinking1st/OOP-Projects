package michael;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
       Player1 player1 = new Player1("Liam","Sword",100);
    //    System.out.println(player1.getName());
    //    System.out.println(player1.getHealth());
    //    System.out.println(player1.getWeapon());

       player1.damageByGun1();
       player1.damageByGun1();
       player1.damageByGun2();
       player1.heal();

        
    }
}
