import card.Card;
import deck.Deck;
import deck.PinochleDeck;
import hand.BlackjackHand;
import players.Dealer;
import players.Player;

public class Assignment2_2 {

	public static void main(String[] args) {
		Dealer dealer = new Dealer(new Deck());
		Dealer dealer2 = new Dealer(new PinochleDeck());
		
		
		System.out.println(dealer.getDeck());
		System.out.println(dealer2.getDeck());
		
		System.out.println("Sum of the standard cards and pinochle cards: " + Card.getCardsCreated());
		System.out.println("Size of the standard deck: " + dealer.getDeck().getCards().length);
		System.out.println("Size of the pinochle deck: " + dealer2.getDeck().getCards().length);
		
	
	Dealer eunice = new Dealer(new Deck(), new BlackjackHand());
	Player fred = new Player("Fred", "4567", new BlackjackHand());
	
	for (int i=0; i<4; i++) {
		if (i%2==0) {
			eunice.dealCard(fred);
		}else {
			eunice.dealCard(eunice);
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
	
	System.out.print(eunice.getName() + "'s hand score: " + eunice.getHand().getHandScore());
	
		}
	}
