package players;
import card.Card;
import deck.Deck;
import hand.BlackjackHand;
import hand.Hand;

public class Dealer extends Player {
	private Deck deck;
	
	public Dealer(Deck deck) {
		this.deck = deck;
		prepareDeck();
		
	}
	
	public Dealer(Deck deck, Hand hand)  {
		super("Dealer", "dealer", 0, hand);
		this.deck = deck;
		prepareDeck();
	}
	

	private void prepareDeck() {
		deck.createDeck();
		deck.shuffleDeck();
		
	}
	
	public void dealCard(Player player) {
		
		Hand tempHand = player.getHand();
		Card tempCard = deck.removeCard(0);
		tempHand.addCard(tempCard);

		
	}
	
	public Card getCard(int index) {
		Card tempCard = deck.getCard(index);
		return tempCard;
	}
	
	public void reshuffleDeck() {
		
		Card[] tempCards = deck.getCards();
		Card[] tempDiscard = deck.getDiscardPile();
		
		int totalCards = tempCards.length + tempDiscard.length;
		int reshuffleCount = (int)(totalCards * 0.6);
		if(tempCards.length < reshuffleCount) {
			deck.restack();
		}
		
		
	}
	
	public Deck getDeck() {
		return deck;
	}

}
