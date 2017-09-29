import java.awt.*;
import objectdraw.*;

/**
 * This class defines an individual card. A card contains a green rectangle and a symbol. 
 * This class also includes  a constructor, a contains method, a getSymbol method, a showSymbol method, 
   a hideSymbol method, and a removeFromCanvas method.
 * 
 * @author Anh Chau Pham
 * @version April 14th, 2016
 */

public class Card
{
    //The body of the card
    private FilledRect card;
    
    //The symbol of the card
    private Text cardSymbol;
    
    //The size of the card
    private static final int SIZE = 50;
    
    // The character represents the symbol on the card
    private char Symbol;
    
    /**
     * Draw a single card with its characteristics.
     */
    public Card (char symbol, int left, int top, DrawingCanvas canvas)
    {
        //The body of the card
        card = new FilledRect ( left, top, SIZE, SIZE, canvas);
        
        //Set the color green for the card
        card.setColor(Color.GREEN);
        
        //The symbol on the card 
        cardSymbol = new Text (symbol, 30, 30, canvas);
        
        //Set the size for the symbol
        cardSymbol.setFontSize(30);
        
        //Move the symbol on the card to the middle of the card
        cardSymbol.moveTo( left + card.getWidth()/2 - cardSymbol.getWidth()/2,
                           top + card.getHeight()/2 - cardSymbol.getHeight()/2);
        
        //Hide the symbol initially 
        cardSymbol.hide();
        
        //Set the symbol on the card
        Symbol = symbol;
    }
    
    /**
     * Get the symbol on the card.
     */
    public char getSymbol() 
    {
        return Symbol; 
    }

    /**
     * Show the symbol on the card.
     */
    public void showSymbol()
    {
        cardSymbol.show();
    }
    
    /**
     * Hide the symbol on the card.
     */
    public void hideSymbol() 
    {
        cardSymbol.hide();
    }
    
    /**
     * Remove the card from the canvas.
     */
    public void removeFromCanvas() 
    {
        card.removeFromCanvas();
        cardSymbol.removeFromCanvas();
    }
    
    /**
     * Check if the card contain the point of the mouse.
     * @return true if point is on the card.
     * @param point the location to check for containment.
     */
    public boolean contains (Location point) 
    {
        if (card.contains (point)) {
            return true;
        }
        return false;
    }
}
    

        