package hand;

import card.Card;
import helpers.PokerSolver3;

public class PokerHand extends Hand {
	
	private String handDescr = "";

	public String getHandDescr() {
		return handDescr;
	}

	public void setHandDescr(String handDescr) {
		this.handDescr = handDescr;
	}

	public PokerHand() {
		
	}

	public PokerHand(Card[] dealtCards) {
		super(dealtCards);
		
	}
	
	@Override
	public void evaluateHand() {
		PokerSolver3.evaluateHand(this);
		
	}
	
}
