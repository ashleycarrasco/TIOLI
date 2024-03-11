package reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import gamedata.GameData;
import players.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameReport {
	int windowWidth = 1200;
	int windowHeight = 550;
	
	Stage reportStage = new Stage();

	Player player;
	
	Text titleText;
	
	BorderPane pane = new BorderPane(); 
	GridPane dataGrid = new GridPane();  
	ScrollPane scrollPane;  
	HBox titleContainer; 
	
	GameData dataBase = new GameData();
	
	ArrayList<Text> gameId = new ArrayList<Text>();
	ArrayList<Text> descr = new ArrayList<Text>();
	ArrayList<Text> winAmount = new ArrayList<Text>();
	ArrayList<Text> bank = new ArrayList<Text>();

	private Button btnExit = new Button("Exit");

	
	public GameReport(Player player) {
		this.player = player;
		
		getData();

		populateGridPane();

		addGridToScroll();

		createExitButtonListener();

		createReportTitle();
				
		addObjectsToPage();
		
		styleStuff();

		showScene();

	}
	
	private void getData() {
			
			ResultSet resultSet = dataBase.getReportData(player);
			
			gameId.add(new Text("Game ID"));
			descr.add(new Text("Hand Descr"));
			winAmount.add(new Text("Amount Won"));
			bank.add(new Text("Player Bank"));
			
				try {
					while(resultSet.next()) {
						gameId.add(new Text(resultSet.getString(1)));
						descr.add(new Text(resultSet.getString(3)));
						winAmount.add(new Text(resultSet.getString(4)));
						bank.add(new Text(resultSet.getString(5)));
					
}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		
	}
	
	private void populateGridPane() {
		
		dataGrid.add(gameId.get(0), 0, 0);
		dataGrid.add(descr.get(0), 2, 0);
		dataGrid.add(winAmount.get(0), 3, 0);
		dataGrid.add(bank.get(0), 4, 0);
		
		
		for(int i = 1; i < gameId.size(); i++) {
			dataGrid.add(gameId.get(i), 0, i);
			dataGrid.add(descr.get(i), 2, i);
			dataGrid.add(winAmount.get(i), 3, i);
			dataGrid.add(bank.get(i), 4, i);
			
			
		}
		
	}
	
	private void addGridToScroll() {
		
		scrollPane = new ScrollPane(dataGrid);
	}
	
	private void createExitButtonListener() {
		
		btnExit.setOnAction(e -> exitReport());
	}
	
	private void createReportTitle() {
		titleText = new Text(player.getName() + "'s Game Data");
	
		titleContainer = new HBox();
		titleContainer.getChildren().add(titleText);

	}
	
	private void addObjectsToPage() {
		
		pane.setTop(titleContainer);
		pane.setCenter(scrollPane);
		pane.setBottom(btnExit);

	}
	
	private void exitReport() {
		dataBase.close();
		
		reportStage.close();
	}
	
	private void styleStuff() {
		
		dataGrid.setHgap(175);
		dataGrid.setVgap(20);

		int leftRightSpace = 40;
		scrollPane.setPrefWidth(windowWidth - leftRightSpace);
		scrollPane.setMaxWidth(windowWidth - leftRightSpace);
		
		titleText.setFont(Font.font("Arial", 30));
		titleContainer.setAlignment(Pos.TOP_CENTER);
		
		BorderPane.setMargin(btnExit, new Insets(0,0,5,550));
		
		for(int i =1; i < gameId.size(); i++) {
			Text gameIdText = gameId.get(i);
			Text descrText = gameId.get(i);
			Text winAmountText = gameId.get(i);
			Text bankText = gameId.get(i);
			
			gameIdText.setFont(Font.font("Arial", 10));
			gameIdText.setFill(Color.BLACK);
			descrText.setFont(Font.font("Arial", 10));
			descrText.setFill(Color.BLACK);
			winAmountText.setFont(Font.font("Arial", 10));
			winAmountText.setFill(Color.BLACK);
			bankText.setFont(Font.font("Arial", 10));
			bankText.setFill(Color.BLACK);
		}
		
		Text gameIdHeader = gameId.get(0);
		Text descrHeader = descr.get(0);
		Text winAmountHeader = winAmount.get(0);
		Text bankHeader = bank.get(0);
		
		gameIdHeader.setFont(Font.font("Arial", 18));
		gameIdHeader.setFill(Color.RED);
		descrHeader.setFont(Font.font("Arial", 18));
		descrHeader.setFill(Color.RED);
		winAmountHeader.setFont(Font.font("Arial", 18));
		winAmountHeader.setFill(Color.RED);
		bankHeader.setFont(Font.font("Arial", 18));
		bankHeader.setFill(Color.RED);
		
		pane.setPadding(new Insets(10,0,0,0));
		scrollPane.setPadding(new Insets(8,0,0,0));
	}
		
	private void showScene() {
		
		Scene scene = new Scene(pane, windowWidth, windowHeight);
		reportStage.setTitle("Draw Poker Data For " + player.getName());
		reportStage.setScene(scene);
		reportStage.show();		
	}
}
