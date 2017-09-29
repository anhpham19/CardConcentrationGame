import objectdraw.*;
import java.awt.*;

/**
 * Demonstrate the use of a collection of cards. The collection of cards is kept in
   an array. This class manages the entire collection of cards.
 *
 * @author Anh Chau Pham
 * @version April 14th 2016
 */
public class CardCollection 
{
    //The total number of cards
    private static final int CARD_QUANTITY = 36;
    
    //An array to hold all the cards
    private Card[] cards =  new Card[CARD_QUANTITY];
    
    //Number of cards currently in the array
    private int numCards = 0;
  
    /**
     * Create a card and put it into the next slot of the array.
     * Make sure there is enough space first.
     */
    public void addCard (Card card, DrawingCanvas canvas) 
    {
        if (numCards < cards.length) 
        {
            cards[numCards] = card;
            numCards++;
        }
    }
    
    /**
     * A method that walks through the collection of cards to show the symbol on the card.
     */
    public void showCard () 
    {
        for (int i = 0; i < numCards; i++ ) 
        {
            cards[i].showSymbol();
        }
    }
    
    /**
     * A method that walks through the collection of cards to hide the symbol on the card.
     */
    public void hideCard () 
    {
        for (int i = 0; i < numCards; i++ ) 
        {
            cards[i].hideSymbol();
        }
    }
    
    /**
     * A method that walks through the collection of cards to find the one containing the 
       point and return it.  If no card contains the point, return null.
     */ 
    public Card getCardAt (Location point)
    {
        for (int i = 0; i < numCards; i++) 
        {
            if (cards[i].contains (point)) 
            {
                return cards[i];
            }
        }
        return null;
    }
    
    /**
     * A method that walks through the collection of cards to remove the card in the
       array from the canvas.
     */ 
     public Card removeCard () 
     {
        for (int i = 0; i< numCards; i++ ) 
        {
            cards[i].removeFromCanvas();
        }
        return null;
    }
}
        
    