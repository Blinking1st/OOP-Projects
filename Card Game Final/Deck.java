import java.util.*;


/**
 * Represents a deck of cards
 * Has two shuffle methods
 * 
 *
 */
public class Deck{
  
  public static final int DECKSIZE = 52;
  private Card[] deck;
  private int cardsDealt = 0;
  
  public Deck() {
    this.initializeDeck();
    cardsDealt=0;
  }
  /**
	 *Method resets the amount of card dealt
    *@param none
    *@return none
    */

  private void reset() {
    cardsDealt = 0;
  }
  /**
	 *Method returns the amount of card left within the deck
    *@param none
    *@return the amount of cards ledt within the deck
    */

  public int getCardsLeft() {
    return deck.length - cardsDealt;
  }
  /**
	 *Method returns the amount of cards dealt
    *@param none
    *@return cardsDealt amount of cards dealt
    */

  public int getCardsDealt() {
    return cardsDealt;
  }
  /**
	 *Method adds cards to the deck
    *@param none
    *return returns the deck value with the dealt cards
    */

  public Card dealCard() {
    if ( getCardsLeft() <= 0 ) {
      return null;
    }
    return deck[cardsDealt++];
  }
  /**
   * Create a full deck of 52 playing cards
   * initializes an array of the cards, unshuffled, with unique cards
   * Called only in Constructor
   */
  private void initializeDeck() {
    deck = new Card[DECKSIZE];
    int pos = 0;
    
    for( int suit = Card.SPADES; suit <= Card.DIAMONDS; suit++ ) {
      for( int rank = 2; rank <= Card.ACE; rank++ ) {
        deck[pos++] = new Card( suit, rank );
      }
    }
  }
  /**
	 *Method shuffles the positions of the cards within the deck
    *@param none
    * @return none
    */

  public void randomShuffle() {
    Card[] other = deck;
    deck = new Card[deck.length];
    Random random = new Random(System.nanoTime());
    
    for( int i = 0; i < deck.length; i++ ) {
      int pos = random.nextInt(DECKSIZE);
      while( other[pos] == null ) {
        pos = random.nextInt(DECKSIZE);
      }
      deck[i] = other[pos];
      other[pos] = null;
    }
    reset();
  }
  /**
	 *Method randomly shuffles card within deck and then swaps position with another card in the deck
    *@param none
    *@return none
    */

  public void randomShuffleSwap() {
    
    Random random = new Random(System.nanoTime());
    
    for( int i = 0; i < deck.length; i++ ) {
      int pos = random.nextInt(DECKSIZE);
      Card holder = new Card(deck[i]);
      deck[i] = deck[pos];
      deck[pos] = holder;
    }
    reset();
  }
  /**
	 *Method returns values of deck in form of a string
    *@param none
    *@return retString value of deck in string form
    */

  public String toString(){
    String retString="";
    
    for (int i = 0; i<deck.length;i++){
      retString = retString + deck[i]+"\n";
      
      
    }
    return retString;
  }
}
