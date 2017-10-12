import java.util.Arrays;
import java.util.Random;


public class DeckOfCards implements DeckOfCardsInterface {
	private Card[] deck;
	private int nextCardIndex;
	

	public DeckOfCards()
	{
		restoreDeck();	
	}
	
	@Override
	/**
	 * Shuffles the deck by randomly exchanging each card with another card.
	 */
	public void shuffle()
		{
		    Random generator = new Random();
		 
		    // attempt to swap each card with a random card in the deck
		    // this isn't a perfect shuffle but it is a simple one
		    // a better shuffle requires a more complex algorithm
		 
			for (int i=0; i< deck.length; i++) {
		        int j = generator.nextInt(deck.length);
		        Card temp = deck[i];
		        deck[i] = deck[j];
		        deck[j] = temp;
		    }
		 
		    //don't forget to reset any variables you're using
		    // to keep track of dealt and remaining cards
		    nextCardIndex = 0;
		    
		}
		

	@Override
	public Card draw() {
		
		if(nextCardIndex >= deck.length)
		{
			return null;
		}
		
		else
		{
			Card drawnCard = deck[nextCardIndex];
			nextCardIndex++;
			return drawnCard;
		}
	}

	@Override
	public void restoreDeck() {
		
		{
			int index = 0;
			deck = new Card[52];
			for(Suit s : Suit.values())
			{
				for(FaceValue face : FaceValue.values())
				{
					deck[index] = new Card(s,face);
					index++;
				}
			}	
		}
	}

	@Override
	public int numCardsRemaining() {
		int cardsRemaining = (deck.length - nextCardIndex);
		return cardsRemaining;
	}

	@Override
	public int numCardsDealt() {
		int dealtCards = nextCardIndex + 1;
		return dealtCards;
	}

	@Override
	public Card[] dealtCards() {
		Card[] dealt = new Card[nextCardIndex];
		
		 for(int i = 0;i < nextCardIndex; i++)
		 {
			 dealt[i] = deck[i];
		 }
		return dealt;
	}

	@Override
	public Card[] remainingCards() {
		Card[] remaining = new Card[52 - nextCardIndex];
		
		for(int i = nextCardIndex; i < 52; i++)
		{
			remaining[i] = deck[i];
		}
		return remaining;
	}
	
	public String toString() {
		String str = " ";
		int printSpace = 0;
		
		for(Card i : deck)
		{
			str += i + " ";
			
			printSpace++;
			
			if(printSpace % 4 == 0)
			{
				printSpace = 0;
				str += "\n";
			}
		}
		
		return str;
		
	}
	
	
	

	
	
}
