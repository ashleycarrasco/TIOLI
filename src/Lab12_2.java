import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import deck.StandardDeck;
import hand.Hand;
import hand.PokerHand;
import players.Dealer;
import players.Player;


public class Lab12_2 {

	public static void main(String[] args) {
		
		Dealer dealer = new Dealer(new StandardDeck());
		
		Player[] players = {
				new Player("Joe", "1717", new PokerHand()),
				new Player("Mary", "2231", 3000, new PokerHand())
		};
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<players.length; j++) {
				dealer.dealCard(players[j]);
			}
		}
		//Evaluates each player's hand
		for(int i = 0; i<players.length; i++) {
			Hand tempHand = players[i].getHand();
			tempHand.evaluateHand();
			
			//Downcasted poker hand
			PokerHand tempPoker = (PokerHand) tempHand;
			System.out.println(players[i].getName() + "'s Hand: " + tempPoker + "\t" + tempPoker.getHandDescr());
			
		}
		
		File file = new File("files/playerdata.csv");
		if(file.exists()) {
			file.delete();
		}
		
		try {
			PrintWriter output = new PrintWriter(file);
			
			output.print("ID,Name,Hand Descr,Bank\n");
			for(int i=0; i<players.length;i++) {
				
				//Downcasted again
				PokerHand tempHand = (PokerHand)players[i].getHand();
				
				output.print(players[i].getId());
				output.print(",");
				output.print(players[i].getName());
				output.print(",");
				output.print(tempHand.getHandDescr());
				output.print(",");
				output.print(players[i].getBank());
				output.print("\n");
			}
			
			output.close();
			
		} catch (IOException ex) {
			System.out.println("Cannot open file for writing");
		}
		
		//Read data back in
		
		String inputData;
		
		try(Scanner input = new Scanner(file)) {
			System.out.print("\n");
			
			while(input.hasNext()) {
				inputData = input.nextLine();
				String [] dataArray = inputData.split(",");
				
				for(int i=0; i<dataArray.length;i++) {
					System.out.print(dataArray[i] + "\t");
				}
				
				System.out.print("\n");
			}
		}catch (IOException ex){
			System.out.println("Error opening file for reading");
		}
	}

}
