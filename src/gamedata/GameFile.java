package gamedata;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import hand.PokerHand;
import players.Player;

public class GameFile {
	
	public static void writeData(String fileName, Player playerObj, int winAmount) {
		
		try(DataOutputStream output = new DataOutputStream(new FileOutputStream(fileName, true))) {
			
			output.writeUTF(playerObj.getId());
			output.writeUTF(playerObj.getName());
			PokerHand tempHand = (PokerHand) playerObj.getHand();
			output.writeUTF(tempHand.getHandDescr());
			output.writeInt(winAmount);
			output.writeInt(playerObj.getBank());
			
			
		} catch (IOException ex) {
			System.out.println("Error writing data");
		}
		
	}
}
