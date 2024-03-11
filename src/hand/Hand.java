package hand;

import java.util.ArrayList;
import card.Card;
import deck.Deck;

public abstract class Hand {
	
	private ArrayList<Card> cards;
	private int handScore = 0;
	private int handRank = 0;
	private int highCard = 0;

	public Hand() {
		cards = new ArrayList<Card>();
	}
	
	public Hand(Card[] dealtCards) {
		
		cards = new ArrayList<Card>(dealtCards.length);
		
		
		for (int i=0; i<dealtCards.length;i++) {
			cards.add(dealtCards[i]);

		}
	}
	
	public void addCard(Card dealtCard) {
			cards.add(dealtCard);
		}
	
	public void addCard(Card[] dealtCards) {
		for (int i=0; i<dealtCards.length;i++) {
			
			cards.add(dealtCards[i]);
	
			}
		}

	public void setCard(int index, Card dealtCard){
    	cards.set(index,  dealtCard);
		
    }
	
	public Card getCard(int index) {
		return cards.get(index);
		
	}
	
	public Card removeCard(int index) {

		return cards.remove(index);
	}
	
	public abstract void evaluateHand();
	
	public void discard(Deck deck, int index) {
		
		Card discardedCard = cards.remove(index);
		deck.addDiscard(discardedCard);
		
	}
	
	public void discardAll(Deck deck) {
		
		while (cards.size() > 0) {
			deck.addDiscard(cards.remove(0));
			
		}
		
	}
	
	@Override
	public String toString() {

		String cardString = "";
		
		for(int i=0; i<cards.size();i++) {
			if (i!= 0 && i%13 == 0) {
				cardString+="\n";
			}
			cardString+=cards.get(i).toString() + " ";
		}
		
		return cardString;
	}

	public int getHandScore() {
		return handScore;
	}

	public void setHandScore(int handScore) {
		this.handScore = handScore;
	}

	public int getHandRank() {
		return handRank;
	}

	public void setHandRank(int handRank) {
		this.handRank = handRank;
	}

	public int getHighCard() {
		return highCard;
	}

	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}

	public Card[] getCards() {
		Card [] tempCards = new Card[cards.size()];
		cards.toArray(tempCards);
		return tempCards;
	}
}
