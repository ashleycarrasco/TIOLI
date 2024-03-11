import java.util.ArrayList;

import card.Card;

import deck.Deck;
import hand.Hand;
import helpers.PokerSolver;
import players.Dealer;
import players.Player;

public class Assignment2_1 {

	public static void main(String[] args) {
		
		Dealer frank = new Dealer(new Deck());
		
		Player player1 = new Player("Jim", "000", new Hand());
		Player player2 = new Player("Toby", "111", new Hand());
		
		System.out.println("Before Deal:\n" + frank.getDeck());
		
		for (int i=0; i<10;i++) {
			if (i%2==0) {
				frank.dealCard(player1);
			}
			else frank.dealCard(player2);
		}

		System.out.println("\nAfter Deal:\n" + frank.getDeck());
		
		int tempArray[] = PokerSolver.evaluatePokerGame(player1.getHand(),player2.getHand());
		
		System.out.println("Player Hands:");
		System.out.println("Player 1: " + player1.getHand() + "\t" + player1.getHand().getHandDescr());
		System.out.println("Player 2: " + player2.getHand() + "\t" + player2.getHand().getHandDescr() + "\n");

		
		if (tempArray[0] == 1) {
			System.out.print("Player 1 won. Player 2 lost.");
		} else if (tempArray[1]==1) {
			System.out.print("Player 2 won. Player 1 lost.");
		}else {
			System.out.print("Both Player 1 and Player 2 tied.");
		
		}

		Hand tempHand1 = player1.getHand();
		Hand tempHand2 = player2.getHand();
		
		Deck tempDeck = frank.getDeck();
		
		tempHand1.discardAll(tempDeck);
		tempHand2.discardAll(tempDeck);
		
		System.out.println("\nAfter Discard:\n" + frank.getDeck());
		
		tempDeck.restack();
		
		System.out.println("\nAfter Restack:\n" + frank.getDeck());
		
	}
}
