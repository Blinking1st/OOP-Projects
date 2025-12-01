/**
 * 
 * This class represents a single Card. The card is a traditional playing card -
 * A, 2, 3, ... K with suits clubs, diamonds, spades, or hearts. Jokers are not
 * represented.
 * 
 * Face values are represented by the numbers 2 through 14 where 11 is a jack,
 * 12 is a queen, 13 is a king, and 14 is an ace. Suits are represented by 1 for
 * spades, 2 for hearts, 3 for clubs, and 4 for diamonds. Ordinal value is such
 * that number cards are worth their face value, picture cards are worth 10 and
 * Ace is worth 11.
 */

public class Card implements CardInterface, Comparable {

	// instance variables
	private int suit;
	private int rank;

	/**
    * Constructor creates a card with a random rank from 2 - 13 and random suit from 1-4
	 *@param none
    */
	public Card() {
		rank = (int) (Math.random() * 13) + 2;
		suit = (int) (Math.random() * 4) + 1;
	}

	/**
    *Constructor creates a card utilizing another card abject
	 *@param other another object of the Card class 
	 *
    */

	public Card(Object other) {
		if (other != null && other instanceof Card) {
			this.setRank((((Card) other).getRank()));
			this.setSuit(((Card) other).getSuit());
		}

	}

	/**
    *Constructor creates a card about utilizing a suit and rank variable
	 *@param suit value of the suit variable
    *@param rank value of rank variable
    */
	public Card(int suit, int rank) {
		this.setSuit(suit);
		this.setRank(rank);

	}

	/**
    *Method returns rank of Card
	 *@param none
    *@return rank value of rank variable
    */
	public int getRank() {
		return rank;
	}

	/**
    *Method returns suit of the card
	 *@param none
    *@return suit value of suit
    */
	public int getSuit() {
		return suit;
	}

	/**
   *Method sets the rank of the card;Throws exception when rank is invalid
	 *@param rank value of rank 	 
    *@return none
    */
	public void setRank(int rank) {
		if (rank >= TWO && rank <= ACE)
			this.rank = rank;
		else
			throw new InvalidCardException("Attempt to set a card to an invalid rank");
	}

	/**
	 *Method sets the value of the suit; Throws exception when suit is invalid
    *@param suit value of suit
    *@return none
    */
	public void setSuit(int suit) {
		if (suit >= SPADES && suit <= DIAMONDS)
			this.suit = suit;
		else
			throw new InvalidCardException("Attempt to set card to an invalid suit");
	}

	/**
	 *Method checks if 2 cards have the same suit value, returns true or false
    *@param other Other instance of a card class
    *@return true/false returns true or false
    */
	public boolean sameSuit(Card other) {
		if (other != null) {
			if (other.getSuit() == this.getSuit())
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 *Method compares a card object to another card object
    *@param other instance of a card object
    *@return returns the the difference between 2 cards rank
    */
	public int compareTo(Object other) {
		if (other == null || !(other instanceof Card)) {
			return Integer.MAX_VALUE;
		} else {
			return this.rank - ((Card) other).rank;
		}
	}

	/**
	 *Method prints out the rank of a card in the form of a string
    *@param none
    *@return returns all the ranks as a string
    */

	public String getRankAsString() {
		switch (rank) {
		case TWO:
			return "Two";
		case THREE:
			return "Three";
		case FOUR:
			return "Four";
		case FIVE:
			return "Five";
		case SIX:
			return "Six";
		case SEVEN:
			return "Seven";
		case EIGHT:
			return "Eight";
		case NINE:
			return "Nine";
		case TEN:
			return "Ten";
		case ACE:
			return "Ace";
		case KING:
			return "King";
		case QUEEN:
			return "Queen";
		case JACK:
			return "Jack";
		default:
			return Integer.toString(rank);
		}
	}
   /**
	 *Method presents the suit value of the card in string form
    *@param none
    *@return returns suit values of the card
    */
	public String getSuitAsString() {
		switch (suit) {
		case CLUBS:
			return "Clubs";
		case DIAMONDS:
			return "Diamonds";
		case HEARTS:
			return "Hearts";
		default:
			return "Spades";

		}
	}

	/**
	 *Method prints out all values of a card in string form
    *@param none
    @return retString values of card in string form 
    */
	public String toString() {
		String retString = "rank: " + this.getRank() + " suit: " + this.getSuit();
		return retString;
	}

	@Override
	public int compareTo(Card other) {
		// TODO Auto-generated method stub
		return 0;
	}
}
