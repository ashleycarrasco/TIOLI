package game;

import card.Card;
import deck.StandardDeck;
import gamedata.GameData;
import gamedata.GameFile;
import gameobjects.CardSelector;
import gameobjects.GameOptions;
import gameobjects.GameTimer;
import gameobjects.PayoutTable;
import gameobjects.ScoreBoard;
import gameobjects.Wager;
import hand.PokerHand;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import players.Dealer;
import players.Player;
import reports.GameReport;

public class TioliGame {
	
	private BorderPane gameScreen = new BorderPane();
		
	private HBox topSection = new HBox(10);
	private HBox bottomSection = new HBox(10);
	private VBox leftSection = new VBox(10);
	private VBox rightSection = new VBox(10);
	private VBox centerSection = new VBox(10);
		
	private Dealer dealer;
	private Player player;
		
	private PlayerArea playerArea;
	private DealerArea dealerArea;
	
	private HBox header;
	
	private PayoutTable payoutTable;
	private Wager wager;
	private ScoreBoard scoreBoard;
	private GameTimer timerObj;
	
	
	private Button btnDeal = new Button("Deal");
	private Button btnTake = new Button("Take It");
	private Button btnLeave = new Button("Leave It");
	private Button btnExit = new Button("Exit");
	
	private Button btnReport = new Button("Report");
	
	private VBox takeLeaveButtonPane = new VBox(10);
	
	private GameOptions gameOptions;
	
	private CardSelector cardSelector = new CardSelector(5);
	
	private int maxTioliCards = 5;
	private int tioliCardsDealt = 0;
	
	
	public TioliGame(Player player) {
		this.player = player;
		
		dealer = new Dealer(new StandardDeck(), new PokerHand());
		
		dealerArea = new DealerArea(dealer);
		playerArea = new PlayerArea(this.player);
		
		
		createGameObjects();
				
		createHeader();
				
		addObjectsToTopSection();
		addObjectsToBottomSection();
		addObjectsToLeftSection();
		addObjectsToRightSection();
		addObjectsToCenterSection();
		
		addTakeLeaveButtons();
				
		addObjectsToGameScreen();
		
		cardSelector.setDisable(true);
		
		btnTake.setDisable(true);
		btnLeave.setDisable(true);
		
		showGame();
				
	}
	
	private void createGameObjects() {
		
		
		payoutTable = new PayoutTable();
		wager = new Wager(player, 10);
		scoreBoard = new ScoreBoard(player);
		timerObj = new GameTimer(30,btnLeave);
		gameOptions = new GameOptions(dealerArea, timerObj);
		
	}
	
	//=====================================================================
	//=====================================================================
	//Constructor Methods
	
	private void createHeader() {
		Text headerText = new Text("Welcome " + player.getName() + "!");
		headerText.setFont(Font.font("Arial", 25));
		header = new HBox(headerText);
		
	}
	
	private void addObjectsToTopSection() {
		topSection.getChildren().addAll(header);
		topSection.setAlignment(Pos.CENTER);
		topSection.setPadding(new Insets(20,10,80,10));
	}
	
	private void addObjectsToBottomSection() {
		
	}
	
	private void addObjectsToLeftSection() {
		leftSection.getChildren().add(gameOptions);
	}
	
	private void addObjectsToRightSection() {
		
		rightSection.getChildren().addAll(payoutTable, wager, scoreBoard);
		rightSection.getChildren().addAll(btnExit,btnReport);
		
		rightSection.setAlignment(Pos.TOP_CENTER);
		
		btnReport.setOnAction(e -> {
			new GameReport(player);
			
		});
		
		
	}
	private void addObjectsToCenterSection() {
		centerSection.getChildren().addAll(timerObj, dealerArea, playerArea);
	}
	
	private void addTakeLeaveButtons() {
		takeLeaveButtonPane.getChildren().addAll(btnTake, btnLeave);
		takeLeaveButtonPane.setAlignment(Pos.CENTER);
		dealerArea.getChildren().add(takeLeaveButtonPane);
		playerArea.getChildren().add(cardSelector);
		cardSelector.setPadding(new Insets(0,0,0,90));
		
		
		playerArea.getChildren().add(btnDeal);
		
		btnTake.setOnAction(e -> takeIt());
		btnLeave.setOnAction(e -> leaveIt());
		btnDeal.setOnAction(e -> startDeal());
		btnExit.setOnAction(e -> {
			Platform.exit();
			System.exit(0);
		});
	}
	
	private void takeIt() {
		timerObj.stopTimer();
		int indexOfCardSelected = cardSelector.getCardSelected() - 1 ;
		Card selectedCard = player.getHand().getCard(indexOfCardSelected);
		Card tioliCard = dealer.getHand().removeCard(0);
		
		player.getHand().setCard(indexOfCardSelected, tioliCard);
		
		dealer.getDeck().addDiscard(selectedCard);
		dealerArea.clearTioliHolder();
		dealerArea.showDiscardedCard(selectedCard);
		playerArea.showCards();
		
		player.getHand().evaluateHand();
		playerArea.showHandDescr();
		
		dealDealer();
		
	}
	
	private void leaveIt() {
		timerObj.stopTimer();
		Card tioliCard = dealer.getHand().removeCard(0);
		
		dealer.getDeck().addDiscard(tioliCard);
		dealerArea.clearTioliHolder();
		dealerArea.showDiscardedCard(tioliCard);
		
		dealDealer();
	}
	private int getPayout() {
		int handRank = player.getHand().getHandRank();
		int wagerAmount = wager.getWagerAmount();
		int payoutAmount = payoutTable.getPayout(handRank, wagerAmount);
		return payoutAmount;
	}
	private void displayFinalResults(int amountWon) {
		scoreBoard.setWinAmount(amountWon);
		player.setBank(player.getBank() + amountWon);
		scoreBoard.updateBank();
	}
	private void writeDataToFile(int amountWon) {
		GameFile.writeData("files/gamedata.dat", player, amountWon);
	}
	private void saveDataInDataBase(int amountWon) {
		GameData gameData = new GameData();
		
		gameData.insertHand(player);
		gameData.updateBank(player);
		gameData.insertResults(player, amountWon);
		gameData.close();
	}

	
	private void endHand() {
		int amountWon = getPayout();
		displayFinalResults(amountWon);
		
		saveDataInDataBase(amountWon);
		
		player.getHand().discardAll(dealer.getDeck());
		btnDeal.setDisable(false);
		btnTake.setDisable(true);
		btnLeave.setDisable(true);
		cardSelector.setDisable(true);
		tioliCardsDealt = 0;
		
		writeDataToFile(amountWon);
		
		wager.setDisable(false);

	}
	
	private void addObjectsToGameScreen() {
		gameScreen.setTop(topSection);
		gameScreen.setCenter(centerSection);
		gameScreen.setRight(rightSection);
		gameScreen.setLeft(leftSection);
		
		BorderPane.setMargin(playerArea, new Insets(0,0,50,0));
	}
	
	private void showGame() {
		
		
		Scene scene = new Scene(gameScreen, 1000, 650);
		
		Stage primaryStage = new Stage();
		
		primaryStage.setTitle("Ashley's TIOLO");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	//====================================================================
	//====================================================================
	//Playing the game
	
	private void startDeal() {
		
		wager.setDisable(true);
		
		clearCards();
		
		playerArea.clearPlayerHand();
		dealerArea.clearDiscardHolder();
		
		dealPlayer();
		evaluateHand();
		
		playerArea.showCards();
		playerArea.showHandDescr();
		
		dealDealer();
		
		btnTake.setDisable(false);
		btnLeave.setDisable(false);
		
		btnDeal.setDisable(true);
		
		cardSelector.setDisable(false);
	}
	
	private void clearCards() {
		player.getHand().discardAll(dealer.getDeck());
	}
	
	private void dealPlayer() {
		for (int i = 0; i<5; i++) {
			dealer.dealCard(player);
		}
	}
	
	private void evaluateHand() {
		player.getHand().evaluateHand();
	}
	
	private void dealDealer() {
		
		if(tioliCardsDealt == maxTioliCards) {
			endHand();
		}else {
			dealer.dealCard(dealer);
			
			dealerArea.showTioliCard();
			
			tioliCardsDealt++;
			timerObj.startTimer();
		}
	}
	
}