import deck.Deck;
import hand.BlackjackHand;
import hand.Hand;
import helpers.PokerSolver;
import players.Dealer;
import players.Player;

public class Lab11_2 {

	public static void main(String[] args) {
		Dealer dealer = new Dealer(new Deck());

		Player player1 = new Player("Joe", "1234", new Hand());
		Player player2 = new Player("Fred", "4567", new Hand());
		
		for (int i=0; i<10;i++) {
			if (i%2==0) {
				dealer.dealCard(player1);
			}
			else dealer.dealCard(player2);
		}
		
		int [] results = PokerSolver.evaluatePokerGame(player1.getHand(),player2.getHand());
		
		System.out.println("Poker:\nPlayer Hands:");
		System.out.println(player1.getName() + "'s Hand: " + player1.getHand() + "\t" + player1.getHand().getHandDescr());
		System.out.println(player2.getName() + "'s Hand: " + player2.getHand() + "\t" + player2.getHand().getHandDescr());
		

	
	if (results[0] == 1) {
		System.out.print(player1.getName() + " wins!");
	} else if (results[1]==1) {
		System.out.print(player2.getName() + " wins!");
	}else {
		System.out.print("It's a tie!");
	
		}
	
	//Blackjack game
	
	Player eunice = new Player("Dealer", "Dealer", new BlackjackHand());
	
	Player fred = new Player("Fred", "4567", new BlackjackHand());
	
	for (int i=0; i<4; i++) {
		if (i%2==0) {
			dealer.dealCard(fred);
		}else {
			dealer.dealCard(eunice);
		}
		
	}
	
	System.out.println("\n\nBlackjack:");
	
	System.out.println(fred.getName() + "'s Hand: " + fred.getHand());
	
	System.out.println(eunice.getName() + "'s Hand: " + eunice.getHand());
	
	fred.getHand().evaluateHand();
	eunice.getHand().evaluateHand();
	
	BlackjackHand tempFred = (BlackjackHand)fred.getHand();
	
	if(tempFred.isBlackjack()) {
		System.out.println(fred.getName() + " has Blackjack!");
	}else {
		System.out.println(fred.getName() + " does not have Blackjack.");
		}
	
	BlackjackHand tempEunice = (BlackjackHand)eunice.getHand();
	if (tempEunice.isBlackjack()) {
		
		System.out.println(eunice.getName() + " has Blackjack!");
	}else {
		
		System.out.println(eunice.getName() + " does not have Blackjack.");
		}
		
	System.out.println("\n" + fred.getName() + "'s hand score: " + fred.getHand().getHandScore());
	
	System.out.println("\n" + eunice.getName() + "'s hand score: " + eunice.getHand().getHandScore());
	
	}
}
