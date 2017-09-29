import objectdraw.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * The program first demonstrates 36 facedown cards on the screen. The cards will come in pairs, where each 
   card in a pair contains the same symbol. When the user clicks on a card, the symbol is displayed. When 
   the user clicks on a second card, its symbol is also displayed. If the cards have the same symbol, on the
   next click both cards are re-moved from the display. If the cards have different symbols, they are both 
   turned facedown again. The user’s goal is to remove all cards from the display in as few clicks as possible
   by remembering where they have seen the symbols.
 * When the user clicks on the "cheatButton" button, the symbols of all cards will appear. When the user
   clicks on the "New game" button, a new game starts with a new collection of shuffled and facedown cards.
 * This class extends WindowController. It contains begin method and the event handlers.
 * 
 * @author Anh Chau Pham
 * @version April 14th 2016
 */

public class Concentration extends WindowController implements ActionListener
{
    //The collection of cards
    private CardCollection cards = new CardCollection();
    
    //The card that is selected
    private Card selectedCard = null;
    
    //The first selected card in a pair
    private Card firstCard;
    
    //The second selected card in a pair
    private Card secondCard;
    
    //The cheat button.
    private JButton cheatButton;
    
    //The new game button.
    private JButton newgameButton;
    
    /**
     * The program starts with a display of the cards and the two buttons.
     */
    public void begin() 
    {    
        //Resize the window
        resize(410, 500);
        
        //Display the cards
        displayCards();
        
        //Display the buttons
        displayButtons();
    }
    
    /**
     * Create the display of the cards. 
     * There are 36 cards (6 columns, 6 rows). Each card contains a symbol and the symbols 
       in the cards are randomly shuffled.
     */
    public void displayCards() 
    {
        //An array of symbols
        char [] symbols = new char[36];
        
        //The first type of symbol in the array of symbols
        char Symbol = '\u03B1';
        
        //The random generator to shuffle the symbols randomly
        RandomIntGenerator symbolGenerator = new RandomIntGenerator (0,35);
        
        //To create random symbols
          int random = 0;

        //Create different symbols in an increasing order. Each symbol appears twice.
        for (int cardSymbols =0; cardSymbols <36; cardSymbols ++) 
        {
            symbols[cardSymbols] = Symbol;
            if (cardSymbols %2 != 0 ) 
            {
                Symbol++;
            }
        }
            
        //Randomly shuffle the symbols by replacing their places 100 times.
        for (int r=0; r < 100; r++ ) 
        {
            //Use the random integer generator to pick 2 random array indexes
            int index1 = symbolGenerator.nextValue();
            int index2 = symbolGenerator.nextValue();
        
            //assign the symbol at the first index into a local variable
            char temp = symbols [index1];
        
            //assign the symbol at the second index into the first index
            symbols [index1] = symbols [index2];
        
            //assign the local variable into the second index
            symbols [index2] = temp;
        }

        //Create the display of the 36 cards. There are 6 rows of cards and 6 cards in a single row.
        for (int rows = 0; rows <6; rows++) 
        {
            for (int cardsInRow = 0; cardsInRow < 6; cardsInRow++) 
            {
                Card newCard = new Card (symbols[random], (30 + rows*60),(30 + cardsInRow*60), canvas);
                cards.addCard(newCard, canvas);
                random++;
            }
        }       
    }
    
    /**
     * Display the two buttons.
     */
    public void displayButtons() 
    {
        
        //Construct the cheat button
        cheatButton = new JButton ("cheatButton");
    
        //Construct the new game button
        newgameButton = new JButton ("New game");
    
        //Construct the panel for the button
        JPanel panel = new JPanel();
    
        //Add the panel to the north side of the window
        add (panel, BorderLayout.NORTH);
    
        //Add the cheat button to the panel
        panel.add(cheatButton);
    
        //Add the new game button to the panel
        panel.add(newgameButton);
    
        //Add the listener to the cheat button
        cheatButton.addActionListener(this);
    
        //Add the listener to the new game button
        newgameButton.addActionListener(this);
    }

    /**
     * When the user clicks on the "cheatButton" button, the symbols of all cards will appear.
     */   
    public void actionPerformed (ActionEvent evt) 
    {
        if (evt.getSource() == cheatButton) 
        {
            cards.showCard();
        }
    }

    /**
     * When the user clicks on a card, the symbol is displayed. 
     * When the user clicks on a second card, its symbol is also displayed.
     * If the cards have the same symbol, on the next click both cards are re-moved from the display. 
     * If the cards have different symbols, they are both turned facedown again. 
     */
    public void onMouseClick (Location point) 
    {
        //The selected card contains the point of the mouse when clicked on
        selectedCard = cards.getCardAt(point);
    
        //If the user clicks on the first card in a pair, its symbol appears.
        if ( selectedCard != null && firstCard == null && secondCard == null ) 
        { 
            selectedCard.showSymbol();
            firstCard = selectedCard;
            
        }
        
        // If the user clicks on the second card in a pair, its symbol appears.
        else if(selectedCard !=null && firstCard !=null && secondCard == null)
        {
            selectedCard.showSymbol();
            secondCard = selectedCard;
             
        }
            
        //After clicking on two cards in a pair, events happen accordingly
        else if( firstCard !=null && secondCard !=null ) 
        {
            //If the symbols of the two cards are different, they will turn facedown
            if (firstCard.getSymbol() != secondCard.getSymbol()) 
            {
                firstCard.hideSymbol();
                secondCard.hideSymbol();
            }
            // If the symbols of the two cards are the same, they will behave accordingly
            else if (firstCard.getSymbol() == secondCard.getSymbol() )
            {
                //If the two cards are different, they will both be removed from the canvas
                if ( firstCard != secondCard) 
                {
                    firstCard.removeFromCanvas();
                    secondCard.removeFromCanvas();
                }
             
                //BONUS PART: If the user clicks on the same card three times, it will turn facedown
                //at the third click
                if (firstCard == secondCard) 
                {
                    firstCard.hideSymbol();
                }
            }
                
                //Reset the value for the two cards in a pair
                firstCard = null;
                secondCard = null;    
            }
        }   
    }
                
    
    
    
