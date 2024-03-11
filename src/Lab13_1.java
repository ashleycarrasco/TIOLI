import deck.Deck;
import hand.BlackjackHand;
import hand.PokerHand;
import helpers.PokerSolver2;
import players.Dealer;
import players.Player;

public class Lab13_1 {

	public static void main(String[] args) {
		
		Dealer pokerDealer = new Dealer(new Deck(), new PokerHand());
		Player fastFreddy = new Player("Freddy", "1234", new PokerHand());
		
		
		for (int i=0; i<10; i++) {
			if (i%2==0) {
				pokerDealer.dealCard(fastFreddy);
			} else {
				pokerDealer.dealCard(pokerDealer);
			}
		}
		
		PokerHand pokerHand = (PokerHand) pokerDealer.getHand();
		PokerHand freddyHand = (PokerHand) fastFreddy.getHand();
		int[] results = PokerSolver2.evaluatePokerGame(pokerHand, freddyHand);
		
		System.out.println("Poker Hands: ");
		System.out.println(pokerDealer.getName() + "'s Hand: " + pokerDealer.getHand()+ "\t" + pokerHand.getHandDescr());
		
		System.out.println(fastFreddy.getName() + "'s Hand: " + fastFreddy.getHand()+ "\t" + freddyHand.getHandDescr());
		
		if(results[0] ==1) {
			System.out.println(pokerDealer.getName() + " won.");
		} else if (results[1] == 1) {
			System.out.println(fastFreddy.getName() + " won.");
		} else {
			System.out.println("It's a tie!");
		}
		
		//Play Blackjack
		Dealer dealer21 = new Dealer(new Deck(), new BlackjackHand());
		Player cardShark = new Player("Shark", "4567", new BlackjackHand());
		
		for (int i=0; i<4; i++) {
			if (i%2==0) {
				dealer21.dealCard(cardShark);
			}else {
				dealer21.dealCard(dealer21);
			}
		
		}
		
		System.out.println("\n\nBlackjack:");
		
		System.out.println(cardShark.getName() + "'s Hand: " + cardShark.getHand());
		
		System.out.println(dealer21.getName() + "'s Hand: " + dealer21.getHand());
		
		cardShark.getHand().evaluateHand();
		dealer21.getHand().evaluateHand();
		
	BlackjackHand tempFred = (BlackjackHand)cardShark.getHand();
		
		if(tempFred.isBlackjack()) {
			System.out.println(cardShark.getName() + " has Blackjack!");
		}else {
			System.out.println(cardShark.getName() + " does not have Blackjack.");
			}
		
		BlackjackHand tempEunice = (BlackjackHand)dealer21.getHand();
		if (tempEunice.isBlackjack()) {
			
			System.out.println(dealer21.getName() + " has Blackjack!");
		}else {
			
			System.out.println(dealer21.getName() + " does not have Blackjack.");
			}
			
		System.out.println("\n" + cardShark.getName() + "'s hand score: " + cardShark.getHand().getHandScore());
		
		System.out.print(dealer21.getName() + "'s hand score: " + dealer21.getHand().getHandScore());
	}

}
