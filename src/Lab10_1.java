import players.Dealer;
import card.Card;
import deck.Deck;


public class Lab10_1 {

	public static void main(String[] args) {
		Dealer bob = new Dealer(new Deck());
		System.out.println(bob.getDeck() + "\n");
		
		Card [] card = new Card[5];
		
		for(int i = 0; i<5; i++) {
			Deck tempDeck = bob.getDeck();
			Card tempCard = tempDeck.getCard(i);
			card[i] = tempCard;
		}
		
		for(int i=0; i<card.length;i++) {
			System.out.print(card[i] + " ");
		}
		
		System.out.println("\n\nThe number of cards created: " + Card.getCardsCreated());
		
		for (int i=0; i<5;i++) {
			Deck tempDeck2 = bob.getDeck();
			Card tempCard2 = tempDeck2.getCard(i);
			String color = tempCard2.getColor();
			
			System.out.println("Color of card is: " + color);
		}
	}
}
