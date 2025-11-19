import java.util.ArrayList;
import java.util.Scanner;

public class Game_MG
{
   public static void main (String[] args)
   {
      int input = -1;
      DiceTower_MG player1;
      DiceTower_MG player2;
      String response;
      int score1;
      int score2;
      int wins = 0;
      int wins2 = 0;
      Scanner in = new Scanner(System.in); 
   
      ArrayList<Integer> even = new ArrayList<Integer>();
      for(int i = 2; i <=10;i++)
      {
         if( i % 2 == 0)
         {
         even.add(i);
         }
      }
      
      ArrayList<Integer> odd = new ArrayList<Integer>();
      for(int i = 3; i <=10;i++)
      {
         if( i % 2 == 1)
         {
         odd.add(i);
         }
      }
       
      Die_MG p1 = new Die_MG(13);
      Die_MG play1 = new Die_MG(even);
      
      Die_MG p2 = new Die_MG(13);
      Die_MG play2 = new Die_MG(odd);
      
      while (input != 2)
      {
         System.out.println("Welcome to the Dice Game!!!");
         System.out.println("Select an option to continue: ");
         System.out.println("1.Simlulate the dice game");
         System.out.println("2.Quit application");
         input = in.nextInt();
         
         if (input == 1)
         {
            boolean play = true;
            while(play)
            {
            for(int i = 0; i < 3; i++)
            {
               System.out.println("Round " + (i+1) + ":");
               player1 = new DiceTower_MG();
               player1.addDice(p1);
               player1.addDice(play1);
               player1.roll();
               score1 =player1.getTotal(); 
               System.out.println("Player 1");
               System.out.println("Current Values of Both Dice: " + player1.getCurrentValues());
               System.out.println("Sum of both die current values: " + player1.getTotal());
               System.out.println();
      
               System.out.println("Player 2");
               player2= new DiceTower_MG();
               player2.addDice(p2);
               player2.addDice(play2);
               player2.roll();
               score2 =player2.getTotal(); 
               System.out.println("Current Values of Both Dice: " + player2.getCurrentValues());
               System.out.println("Sum of both die current values: " + player2.getTotal());
      
               if ( score1 > score2)
               {
                  wins++;
                  System.out.println("Player 1 wins this round");
                  System.out.println("Player 1 has " + wins + " win(s)!!!");
                  System.out.println("Player 2 has " + wins2 + " win(s)!!");
         
         
               }  
               else if ( score2 > score1)
               {
                  wins2++;
                  System.out.println("Player 2 wins this round");
                  System.out.println("Player 2 has " + wins2 + " win(s)!!!");
                  System.out.println("Player 1 has " + wins + " win(s)!!");
         
         
               }
               else if (score1 == score2)
               {
                  System.out.println();
                  System.out.println("Its a Tie!!!");
                  System.out.println("Player 1 has " + wins + " win(s)!!!");
                  System.out.println();
                  System.out.println("Player 2 has " + wins2 + " win(s)!!");
               }
         
               if (wins == 2)
               {
                  System.out.println();
                  System.out.println("Player 1 wins the game!!!");
                  System.out.println();
                  System.out.println("Thanks for playing!!");
                  wins = 0;
                  break;
                  
               }
         
               if (wins2 == 2)
               {
                  System.out.println();
                  System.out.println("Player 2 wins the game!!!");
                  System.out.println();
                  System.out.println("Thanks for playing!!");
                  wins2 = 0;
                  break;
                  
               }
             }
             while(true)
             {
               System.out.println("Do you want to play the game again? Enter Yes or No");   
               response = in.next();
               if (response.equalsIgnoreCase("Yes"))
                {
                  play = false;
                  break;
                }
               else if (response.equalsIgnoreCase("No"))
                {
                  play = false;
                  input = 2;
                  break;
                }
               else
               {
                  System.out.println("Error: Enter 'Yes' or 'No'.");
               }
            }

             
            }
         }
         if (input == 2)
         {
            System.out.println("Thank you for playing!!!");
            System.out.println("GoodBye!!!");
            in.close();
         }        
       }
  }
}
          