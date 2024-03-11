package hand;

import card.Card;
import helpers.BlackjackSolver;

public class BlackjackHand extends Hand {
	
	private boolean blackjack = false;
	private boolean charlie = false;

	public BlackjackHand() {		
		super();
		
		
	}

	public BlackjackHand(Card[] dealtCards) {
		super(dealtCards);
		
	}
	@Override
	public void evaluateHand() {
		
		int score = BlackjackSolver.handScore(this);
		super.setHandScore(score);
		
		this.blackjack = BlackjackSolver.hasBlackjack(this);
		this.charlie = BlackjackSolver.hasCharlie(this);
	}

	public boolean isBlackjack() {
		return blackjack;
	}

	public boolean isCharlie() {
		return charlie;
	}
}
