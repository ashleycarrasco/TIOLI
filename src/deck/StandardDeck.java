package deck;

import card.Card;

public class StandardDeck extends Deck {

	public StandardDeck() {
		super();
	}
	
@Override
	public void createDeck() {
		int suitIndex;
		int cardRank;
		
		for(int i=0; i < 52; i++) {
			
			if(i < 13){
			    suitIndex = 0;
			}
			else if(i < 26){
			    suitIndex = 1;
			}
			else if(i < 39){
			    suitIndex = 2;
			}
			else {
			    suitIndex = 3;
			}
			
			cardRank = ((i) % 13) + 1;
			
			cards.add(new Card(i, suitIndex, cardRank));
		}
	}
}