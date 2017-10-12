
public class CardDealer {

	public static void main(String[] args) {
		DeckOfCards deck = new DeckOfCards();
		
		System.out.println("Unshuffled new deck: \n");
		System.out.println(deck.toString());
	
		
		deck.shuffle();
		System.out.println("Shuffled deck: \n");
		System.out.println(deck.toString());
		
		
		while(deck.numCardsRemaining() > 0 ){
			Card card1 = deck.draw();
			Card card2 = deck.draw();
				
			System.out.println("Drawing cards...");
			System.out.println("Player 1: " + card1);
			System.out.println("Player 2: " + card2);
			int winner = card1.compareTo(card2);
			if(winner == 0)
			{
				System.out.println("You tied\n");
			}
			if(winner == 1)
			{
				System.out.println("Player 1 wins!\n");
			}
			if(winner == -1)
			{
				System.out.println("Player 2 wins!\n");
			}
			
		 }
			
			deck.restoreDeck();
			System.out.println("Restored deck: \n");
			System.out.println(deck);
			
			
		
			
		}
		
		
		
		
		

	}

