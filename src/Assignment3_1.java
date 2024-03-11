
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import deck.StandardDeck;
import hand.BlackjackHand;
import hand.Hand;
import players.Dealer;
import players.Player;

public class Assignment3_1 {

	public static void main(String[] args) {
		
		Dealer dealer = new Dealer(new StandardDeck(), new BlackjackHand());
		
		Player[] players = {
				new Player("SmokeyJoe", "00064", new BlackjackHand()),
				new Player("HitMe!", "67456", 3000, new BlackjackHand()),
				new Player("ForeverFolding", "90821", new BlackjackHand()),
				new Player("BlackjackQueen", "77892", new BlackjackHand())
		};
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<players.length; j++) {
				dealer.dealCard(players[j]);	
			}
			dealer.dealCard(dealer);
		}
		
		for(int i = 0; i<players.length; i++) {
		Hand tempHand = players[i].getHand();
		tempHand.evaluateHand();
		
		System.out.println("\n" + players[i].getName() + "'s Hand: " + tempHand + "\tHandscore: " + tempHand.getHandScore());
		}
		Hand tempHand2 = dealer.getHand();
		tempHand2.evaluateHand();
		
		
		
		System.out.println("\n" + dealer.getName() + "'s Hand: " + tempHand2 + "\tHandscore: " +tempHand2.getHandScore());
		
		File file = new File("files/blackjackdata.csv");
		if(file.exists()) {
			file.delete();
		}
		
		try {
			PrintWriter output = new PrintWriter(file);
			output.print("PlayerID,PlayerName,PlayerBank,Blackjack?,HandScore\n");
			
			for(int i=0; i<players.length;i++) {
				
				BlackjackHand tempHand = (BlackjackHand) players[i].getHand();
				
				output.print(players[i].getId());
				output.print(",");
				output.print(players[i].getName());
				output.print(",");
				output.print(players[i].getBank());
				output.print(",");
				
				if (tempHand.isBlackjack()) {
					output.print("Y");
				} else {
					output.print("N");
				}
				
				output.print(",");
				output.print(tempHand.getHandScore());
				output.print("\n");
				
			}
			
			BlackjackHand tempHand = (BlackjackHand) dealer.getHand();
			output.print(dealer.getId());
			output.print(",");
			output.print(dealer.getName());
			output.print(",");
			output.print(dealer.getBank());
			output.print(",");
			
			if (tempHand.isBlackjack()) {
				output.print("Y");
			} else {
				output.print("N");
			}
			
			output.print(",");
			output.print(tempHand.getHandScore());
			output.print("\n");
			
			output.close();
		
		} catch (IOException ex){
			System.out.println("Cannot open file for writing");
		}
		String inputData;
		
		try(Scanner input = new Scanner(file)) {
			System.out.print("\n");
			
			while(input.hasNext()) {
				inputData = input.nextLine();
				String [] dataArray = inputData.split(",");
				
				System.out.printf("%-15s%-20s%-15s%-10s%15s\n", dataArray[0],dataArray[1],dataArray[2],dataArray[3],dataArray[4]);
			}
		} catch (IOException ex){
			System.out.println("Error opening file for reading");
		}
			
	}
}
		

	
		

