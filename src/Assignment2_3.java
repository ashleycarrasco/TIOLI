import card.Card;
import deck.PinochleDeck;
import deck.StandardDeck;

public class Assignment2_3 {

	public static void main(String[] args) {
		StandardDeck standardCards = new StandardDeck();
		
		standardCards.createDeck();
		standardCards.shuffleDeck();
		
		System.out.println("Standard Deck: " + standardCards);
		
		PinochleDeck pinochleCards = new PinochleDeck();
		pinochleCards.createDeck();
		pinochleCards.shuffleDeck();
		
		System.out.println("Pinochle Deck: ");
		System.out.print(pinochleCards);
		
		System.out.println("\nSum of standard cards and pinochle cards: " + Card.getCardsCreated());
		System.out.println("Number of pinochle cards created: " + pinochleCards.getCards().length);
		System.out.println("Number of standard cards created: " + standardCards.getCards().length);

	}

}
