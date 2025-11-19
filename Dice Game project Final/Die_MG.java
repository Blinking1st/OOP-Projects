import java.util.ArrayList;
import java.util.Random;

public class Die_MG
{
   private ArrayList<Integer> sides;
   private int currentFace;
   private Random rand;
   
   
   
   
       
   
      /**
         @param sideNum The amount of sides the dice will have
         @return nothing
       */
      public Die_MG(int sideNum)
         {
            this.sides = new ArrayList<>();
            for (int i = 1; i <= sideNum; i++) 
            {
               this.sides.add(i);
            }
            this.currentFace = 1;
         }
         /**
         @param sidNum The amount of sides the dice will have
         @param min the minimum amount of side the dice will have
   
         */
      public Die_MG(int sideNum, int min) 
         {
             this.sides = new ArrayList<>();
            for (int i = 0; i < sideNum; i++) 
            {
               this.sides.add(i + min - 1);
            }

            this.currentFace = min; 
         }
         /**
         @param ArrayList<Integer> valuesArray An arraylist that contains the values of the dice with the size of the arraylist being the amount of sides the dice will have
   
         */

      public Die_MG(ArrayList<Integer> valuesArray)
         {
            this.sides = new ArrayList<>(valuesArray);
            this.currentFace = 0;
            
         }
         /**
         @param none
   
         */

      public Die_MG()
         {
            this.sides = new ArrayList<>();
            this.sides.add(1);
            this.currentFace = 1;
           
              
         
         }
         
         /**
         return result a representation of the Die as <quantity of sides> 
   
         */

         public String toString()
            {
               String result = "Quantity of sides: " + sides.size() + " " + "||" + " Side values: ";
               
               for (int i = 0; i <sides.size(); i++)
               {
                  result = result + sides.get(i);
                  if (i < sides.size() - 1)
                     {
                      result = result + " ";
                     }
               }
               
              return result;  
            }
            /**
            @return sideNum the quantity of sides of the Die
   
            */

         public int getQuantityOfSides()
            {
               int sideNum = sides.size();
               return sideNum;
            
            }
            
            /**
            @return value value of the side at sideLocation
            @param sideLocation the side whos value will be returned
   
            */

         public int getValueAt(int sideLocation)
            {
               sideLocation = sideLocation -1;
                  if(sideLocation > sides.size() || sideLocation < sides.size() )
                     {
                        System.out.println("Invalid side Location"); 
                     }
               int value;
               
               value = sides.get(sideLocation);
              
               
               return value;
     
            }
            
            /**
            @return currentFace the value of the current facing
            
   
            */

            int getCurrentValue()
               {
                  
                  return currentFace;
               }
               /**
               randomly sets the current facing to a valid facing and returns the value of that facing
               
               */

            int rollDie()
               {
               Random rand = new Random();
               currentFace = rand.nextInt(sides.size());
               return sides.get(currentFace);
               
               }
               /**
                sets the current location to the facing at sideLocation. 
                @param sideLocation value that sets the currentFace variable equal to this value
               */

            void setFacing(int sideLocation)
               {
                  if ( sideLocation >= sides.size() || sideLocation <0)
                     {
                        System.out.println("Invalid range of side quantity");
                     }
                 else
                 {
                 currentFace = sideLocation;
                 } 
                  
               }
               /**
               @return true if both dice have the same value regardless of quantity of sides
               @param Die A 2nd object of the dice class
               */

            boolean compareDieValue(Die otherDice)
               {
               return this.getCurrentValue() == otherDice.getCurrentValue();                 
               }
               
               /**
               @return true if both dice have the same quantity of sides regardless of values and regardless of current value
               @param Die A 2nd object of the dice class
               */

            boolean compareQuantityOfSides(Die_MG otherDice)
               {
                  if( this.sides.size() == otherDice.sides.size())
                     {
                     return true;
                     }
                  else
                     {
                     return false;
                     }
                  
               }
               /**
               @return true if both dice have the same quantity of sides with the same values regardless of current value
               @param Die A 2nd object of the dice class
   
               */

           boolean compareDice(Die_MG otherDice)
            {
            // compares the quantity of sides
            if(this.sides.size() != otherDice.sides.size())
               {
                  return false;
               }
               
               //compares side values
              for (int i = 0; i < this.sides.size(); i++)
               {
                  if(!this.sides.get(i).equals(otherDice.sides.get(i)))
                     {
                        return false;
                     }
               }
               return true;
            
               }
               /**
               @param Die A 2nd object of the dice cla
               @return true if both dice have the same quantity of sides, same values, and same current facing value
               */

           boolean compareTo(Die_MG otherDice)
            {
                if( this.sides.size() != otherDice.sides.size())
                     {
                     return false;
                     }
                     
                 for (int i = 0; i < this.sides.size(); i++)
                  {
                     if(!this.sides.get(i).equals(otherDice.sides.get(i)))
                        {
                         return false;
                        }
                        
                  }
                  
                 if( this.getCurrentValue() != otherDice.getCurrentValue())
                        {
                           return false;
                        }
                        
                  return true;
            }
            
}