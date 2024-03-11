import card.Card;

public class Lab9_2 {

	public static void main(String[] args) {
		
		Card[] hand1 = new Card[5];

		hand1[0] = new Card(1, 0, 1);
		hand1[1] = new Card(16, 1, 3);
		hand1[2] = new Card(46, 3, 7);
		hand1[3] = new Card(40, 3, 1);
		hand1[4] = new Card(33, 2, 7);
		
		System.out.print("Hand 1: ");
		for(int i=0; i<hand1.length; i++) {
			System.out.print(hand1[i] + " ");
		}
		
		Card[] hand2 = new Card[5];

		hand2[0] = new Card(18, 1, 5);
		hand2[1] = new Card(4, 0, 4);
		hand2[2] = new Card(19, 1, 6);
		hand2[3] = new Card(47, 3, 8);
		hand2[4] = new Card(7, 0, 7);
		
		System.out.print("\nHand 2: ");
		for(int i=0; i<hand2.length; i++) {
			System.out.print(hand2[i] + " ");
		}
		System.out.println("\nNumber of cards dealt: " + Card.getCardsCreated());
		
	}

}
