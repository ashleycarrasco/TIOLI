import game.TioliGame;
import gamedata.GameData;
import hand.PokerHand;
import javafx.application.Application;
import javafx.stage.Stage;
import players.Player;

public class PlayGame extends Application{
	
	private GameData gamebaseObj = new GameData();

	public void start(Stage primaryStage) {
		//Player player = new Player("FastFreddy", "9765467", 1450, new PokerHand());
		
		//new TioliGame(player);
		
		Player randomPlayer = gamebaseObj.getRandomPlayer();
		new TioliGame(randomPlayer);
		
	
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
