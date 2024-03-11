package deck;

import java.util.ArrayList;

import card.Card;

public abstract class Deck {
	
	protected ArrayList<Card> cards;
	private ArrayList<Card> discardPile;
	
	public Deck() {
		cards = new ArrayList<Card>();
		discardPile = new ArrayList<Card>();
	}
	
	public abstract void createDeck();

	public void shuffleDeck() {
		
		Card cardTemp;
		int randomIndex;
		
		for (int i=0; i<cards.size(); i++) {
			randomIndex = (int)(Math.random() * cards.size());
			
			cardTemp = cards.get(randomIndex);
			
			cards.set(randomIndex, cards.get(i));
			
			cards.set(i, cardTemp);
		}
	}

	public void addDiscard(Card card) {		
		discardPile.add(card);
	}

	public void addDiscard(Card[] discards) {		
		for (int i=0; i<discards.length;i++) {
			discardPile.add(discards[i]);
		}
	}

	public void restack() {
		
		while (discardPile.size() > 0) {
			cards.add(discardPile.remove(0));
		}
		
		shuffleDeck();
	}
	
	public Card getCard(int index) {
		
		return cards.get(index);
	}
	
	public Card removeCard(int index) {
		
		return cards.remove(index);
	}

	public Card[] getCards() {
		
		Card[] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		return tempCards;
	}

	public Card[] getDiscardPile() {
		Card[] tempDiscard = new Card[discardPile.size()];
		discardPile.toArray(tempDiscard);
		return tempDiscard;
	}
	
	public String toString() {
		
		String cardString = "Deck:\n";
		
		for(int i=0; i<cards.size();i++) {
			if (i!= 0 && i%13 == 0) {
				
				cardString+="\n";
			}
			
			cardString += cards.get(i) + " ";
		}
		
		cardString += "\nDiscard Pile:\n";
		
		for(int i=0; i<discardPile.size();i++) {
			if (i!= 0 && i%13 == 0) {
				
				cardString+= "\n";
		
			}
			
			cardString += discardPile.get(i) + " ";

		}
		
		return cardString;
	}
		
}