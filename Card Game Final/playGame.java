import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/**
 *@author Michael Gamli
 *Instructor: Deepak Dewar
 *This is a simplfied, simulated version of go fish
 */
public class playGame extends Hand 
{
    
    private LinkedList<Hand> playerHands; 
    
    
    private Deck funDeck; 
    
   
    private int numPlayers; 
    
    
    private ArrayList<Integer> playerScores; 
    
    
    private int totalBooksMade = 0; 

    /**
     * Constructor to set up the game environment.
     * Initializes the deck, shuffles it, creates player hands, initializes scores to zero,
     * and deals the starting hands to all players.
     * @param numPlayers The number of players participating in the game.
     * @param handSize   The initial number of cards dealt to each player.
     */
    public playGame(int numPlayers, int handSize) 
    {
        
        super(Deck.DECKSIZE); 

        this.numPlayers = numPlayers;

        
        this.funDeck = new Deck();
        this.funDeck.randomShuffleSwap(); 

        
        this.playerHands = new LinkedList<Hand>();
        this.playerScores = new ArrayList<Integer>(); 

        
        for (int i = 0; i < numPlayers; i++) 
        {
            this.playerHands.add(new Hand(Deck.DECKSIZE));
            this.playerScores.add(0); 
        }

        
        this.dealHands(handSize);
    }

    /**
     * Deals a specific number of cards to all players.
     * @param handSize The number of cards each player should receive.
     */
    private void dealHands(int handSize) 
    {
        for (int i = 0; i < handSize; i++) 
        { 
            for (int j = 0; j < this.numPlayers; j++) 
            { 
                Hand currentHand = playerHands.get(j);
                if (funDeck.getCardsLeft() > 0) 
                {
                    Card cardToDeal = funDeck.dealCard();
                    currentHand.addCard(cardToDeal);
                }
            }
        }
    }

    /**
     * Retrieves the number of players currently in the game.
     * @return The integer number of players.
     */
    public int getNumPlayers() 
    {
        return this.numPlayers;
    }

    /**
     * Displays the contents of a specific player's hand to the console.
     * The hand is sorted by rank before being displayed.
     * @param playerNum The index of the player (0 to numPlayers - 1).
     */
    public void showHand(int playerNum) 
    {
        if (playerNum < 0 || playerNum >= this.numPlayers) 
        {
            System.out.println("Invalid player number.");
            return;
        }
        System.out.println("--- Player " + playerNum + "'s Hand ---");
        Hand hand = getHand(playerNum);
        hand.sortHandByRank(); 
        hand.showHand();
        System.out.println("--------------------------");
    }

    /**
     * Retrieves the Hand object associated with a specific player.
     * @param playerNum The index of the player.
     * @return The {@link Hand} object for the specified player.
     * @throws IllegalArgumentException if the player number is out of bounds.
     */
    public Hand getHand(int playerNum) 
    {
        if (playerNum < 0 || playerNum >= this.numPlayers) 
        {
            throw new IllegalArgumentException("Invalid player number: " + playerNum);
        }
        return playerHands.get(playerNum);
    }

    /**
     * Checks if a specific player possesses a "book" (4 cards of the same rank).
     * @param playerNum The index of the player to check.
     * @return {@code true} if the player has 4 of a kind; {@code false} otherwise.
     */
    public boolean hasFourOfaKind(int playerNum) 
    {
        Hand h = playerHands.get(playerNum);
        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < h.getTotalCards(); i++) 
        {
            Card c = h.getCard(i);
            if(c == null) continue;
            int rank = c.getRank();
            countMap.put(rank, countMap.getOrDefault(rank, 0) + 1);

            if (countMap.get(rank) == 4) 
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if a player has at least one card of a specific rank.
     * @param playerNum The index of the player to check.
     * @param rankValue The integer rank of the card to look for.
     * @return true if the player holds the requested card;     
     */
    public boolean hasWantedCard(int playerNum, int rankValue) 
    {
        Hand h = playerHands.get(playerNum);
        for (int i = 0; i < h.getTotalCards(); i++) 
        {
            if (h.getCard(i) != null && h.getCard(i).getRank() == rankValue) 
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the score for a specific player when they complete a book.
     * Also increments the global count of total books made.
     * @param playerNum The index of the player whose score is being updated.
     */
    public void updateScore(int playerNum) 
    {
        int currentScore = playerScores.get(playerNum);
        playerScores.set(playerNum, currentScore + 1);
        totalBooksMade++;
    }

    /**
     * Prints the current scoreboard to the console.
     */
    public void showScores() 
    {
        System.out.println("\n=== Scoreboard ===");
        for (int i = 0; i < numPlayers; i++) 
        {
            System.out.println("Player " + i + ": " + playerScores.get(i) + " books");
        }
        System.out.println("==================\n");
    }

    /**
     * Checks if the game has ended and declares the winner.
     * The game ends when 13 books (the total number of ranks) have been made.
     * @return true if all 13 books are made and the game is over;      
     */
    public boolean determineWinner() 
    {
        if (totalBooksMade >= 13) {
            int maxScore = -1;
            int winner = -1;
            boolean tie = false;

            for (int i = 0; i < numPlayers; i++) 
            {
                if (playerScores.get(i) > maxScore) 
                {
                    maxScore = playerScores.get(i);
                    winner = i;
                    tie = false;
                } 
                else if (playerScores.get(i) == maxScore) 
                {
                    tie = true;
                }
            }

            System.out.println("\n*** GAME OVER ***");
            showScores();
            if (tie) 
            {
                System.out.println("It's a tie!");
            } 
            else 
            {
                System.out.println("The Winner is Player " + winner + "!");
            }
            return true;
        }
        return false;
    }

    /**
     * Helper method to scan a player's hand for books (4 of a kind),
     * remove those cards from the hand, and update the player's score.
     * @param playerNum The index of the player to check.
     */
    private void checkAndRemoveBooks(int playerNum) 
    {
        if (!hasFourOfaKind(playerNum)) return;

        Hand h = getHand(playerNum);
        HashMap<Integer, Integer> countMap = new HashMap<>();
        

        for (int i = 0; i < h.getTotalCards(); i++) 
        {
            Card c = h.getCard(i);
            if(c == null) continue;
            countMap.put(c.getRank(), countMap.getOrDefault(c.getRank(), 0) + 1);
        }


        for (Integer rank : countMap.keySet()) 
        {
            if (countMap.get(rank) == 4) 
            {
                String rankName = "Unknown";
                

                for (int i = h.getTotalCards() - 1; i >= 0; i--) 
                {
                    Card c = h.getCard(i);
                    if (c.getRank() == rank) 
                    {
                        rankName = c.getRankAsString();
                        try 
                        {
                            h.removeCard(i);
                        } 
                        catch (Exception e) 
                        {
                            System.out.println("Error removing card: " + e.getMessage());
                        }
                    }
                }
                
                System.out.println("Player " + playerNum + " made a BOOK of " + pluralize(rankName) + "!");
                updateScore(playerNum);
            }
        }
    }

    /**
     * Starts the main game loop.
     * Simulates the turns for all players (AI vs AI) until a winner is determined. 
     * and repeating turns on success.
     */
    public void playNow() 
    {
        System.out.println("Starting Go Fish with " + numPlayers + " players.");
        Random rand = new Random();
        int currentPlayer = 0;

        while (!determineWinner()) 
        {
            System.out.println("\n>>> Turn: Player " + currentPlayer);
            
            checkAndRemoveBooks(currentPlayer);
            if (determineWinner()) break;

            Hand currentHand = getHand(currentPlayer);
            
            
            if (currentHand.getTotalCards() == 0) 
            {
                if (funDeck.getCardsLeft() > 0) 
                {
                    Card drawn = funDeck.dealCard();
                    currentHand.addCard(drawn);
                    System.out.println("Player " + currentPlayer + " has no cards. Drew a card.");
                } 
                else 
                {
                    System.out.println("Player " + currentPlayer + " has no cards and deck is empty. Skipping.");
                    currentPlayer = (currentPlayer + 1) % numPlayers;
                    continue;
                }
            }

            int randomIdx = rand.nextInt(currentHand.getTotalCards());
            Card cardToAskFor = currentHand.getCard(randomIdx);
            int rankToAsk = cardToAskFor.getRank();
            String rankName = cardToAskFor.getRankAsString(); 


            int targetPlayer = (currentPlayer + 1) % numPlayers; 

            System.out.println("Player " + currentPlayer + " asks Player " + targetPlayer + 
                             " for " + pluralize(rankName) + ".");


            if (hasWantedCard(targetPlayer, rankToAsk)) 
            {
                System.out.println("Player " + targetPlayer + " has it! Transferring cards...");
                
                Hand targetHand = getHand(targetPlayer);
                int countTransferred = 0;
                

                for (int i = targetHand.getTotalCards() - 1; i >= 0; i--) 
                {
                    Card c = targetHand.getCard(i);
                    if (c.getRank() == rankToAsk) 
                    {
                        try 
                        {
                            targetHand.removeCard(i);
                            currentHand.addCard(c);
                            countTransferred++;
                        } catch (Exception e) {}
                    }
                }
                

                checkAndRemoveBooks(currentPlayer);
                

                System.out.println("Player " + currentPlayer + " goes again.");
                
            } 
            else 
            {

                System.out.println("Player " + targetPlayer + " says: 'Go Fish!'");
                
                if (funDeck.getCardsLeft() > 0) 
                {
                    Card drawn = funDeck.dealCard();
                    currentHand.addCard(drawn);
                    System.out.println("Player " + currentPlayer + " drew a " + 
                                     drawn.getRankAsString() + " of " + drawn.getSuitAsString());
                    
                    checkAndRemoveBooks(currentPlayer);


                    if (drawn.getRank() == rankToAsk) 
                    {
                        System.out.println("Player " + currentPlayer + " fished the " + drawn.getRankAsString() + "! Go again.");
                    } 
                    else 
                    {

                        currentPlayer = (currentPlayer + 1) % numPlayers;
                    }
                } 
                else 
                {
                    System.out.println("Deck is empty. Turn passes.");
                    currentPlayer = (currentPlayer + 1) % numPlayers;
                }
            }
        }
    }
    
    /**
     * A helper utility to correct grammar for card names.
     * @param name The singular name of the rank.
     * @return The pluralized string.
     */
    private String pluralize(String name) 
    {
        if (name.endsWith("x") || name.endsWith("s") || name.endsWith("ch") || name.endsWith("sh")) 
        {
            return name + "es";
        }
        return name + "s";
    }

    /**
     * Creates a new game instance and starts the game loop.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) 
    {
        playGame game = new playGame(3, 7);
        game.playNow();
    }
}