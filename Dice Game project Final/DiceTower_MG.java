import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class DiceTower_MG 
{
   private ArrayList<Die_MG> tower;
   
   
   //Creates an empty dice tower with tower array list of the Dice object
   public DiceTower_MG()
      {
         tower = new ArrayList<Die_MG>();
      }
      
      //@param newDie_MG Dice object to be added to tower 
   public void addDice(Die_MG newDie)
      {
         tower.add(newDie);
      }
      
      
      //clears dice tower of all dice objects
    public void emptyDiceTower()
      {
         tower.clear();
      }
     
     // Converts all dice within dicetower into a string
     //@return result dice tower data in form of a string
     public String toString()
      {
        String result = "DiceTower:: " + "\n";

          for (int i = 0; i < tower.size(); i++) 
            {
             result += "Die:" + tower.get(i).toString() + "\n";
        
        }
         return result;  
            
      }
      //@return getTotal returns the amount of dice within tower
     public int getDiceQuantity()
      {
         return tower.size();
      }
      
      //@return total returns sum of the current values of all dice in the dicetower
     public int getTotal()
      {
       int total = 0;
       
       for ( Die_MG dice1 : tower)
         {
         total = total + dice1.getCurrentValue();
         } 
       return total;
      }
      
      //@param diePosition the location of the desired dice within the dicetower
      //@return tower.get(diePosition) returns die onejct at given position
      public Die_MG getDie(int diePosition)
       {
         if( diePosition < 0 || diePosition >= tower.size())
            {
               System.out.println("Invalid Die position");
               return null;
            }
          else
            {
               return tower.get(diePosition);
            }
          
       }
       
       //@return valuesArray returns the current values of dice in dicetower, not the sum of the current values
       public ArrayList<Integer> getCurrentValues()
         {
            ArrayList<Integer> valuesArray = new ArrayList<Integer>();

          for (Die_MG d : tower) 
            {
               valuesArray.add(d.getCurrentValue());
            }
            
            return valuesArray;
         }
         
         // "rolls" the dice with the dice tower
         public  void roll()
            {
             for (Die_MG d : tower) 
                {
                  d.rollDie();
                }
                      
           }
           
           //@param x initial position of dice in dice tower
           //@param y position dice object will be swapped with
           private  void swapDice(int x, int y)
            {
               Collections.swap(tower,x,y);
            }
            
            
           //@param other compares the die values of both dicetowers
          public boolean compareTo(DiceTower_MG other)
            {
               for (int i = 0; i < tower.size(); i++) 
                  {
                     Die_MG thisDie = this.tower.get(i);
                     Die_MG otherDie = other.tower.get(i);

             // Check same current value
               if (thisDie.getCurrentValue() != otherDie.getCurrentValue()) 
                {
                   return false;
                }

             // Check same number of sides
               if (thisDie.getQuantityOfSides() != otherDie.getQuantityOfSides()) 
                {
                   return false;
                 }
                 
                 
                 
               }
               return true;
          }
      
      
      
      
      
     
   










}