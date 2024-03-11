package game;

import card.Card;
import hand.Hand;
import hand.PokerHand;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import players.Player;

public class PlayerArea extends VBox{
	
	private Player player;
	private HBox playerCardArea = new HBox(10);
	private Text handResult = new Text(" ");
	

	
	public PlayerArea(Player player) {  
		this.player = player;
		
		createCardArea(); 
		createHandResults();
		addObjectsToTheVBox();

	}
	
	private void createCardArea() {
		
		playerCardArea.setAlignment(Pos.CENTER);
		playerCardArea.setPadding(new Insets(5,5,5,5));
		
		styleCardHolder();

	}
	
	private void createHandResults() {
		
		handResult.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 32));
		
		handResult.setFill(Color.GREEN);
		
		VBox.setMargin(handResult, new Insets(0,0,15,0));
	}

	private void addObjectsToTheVBox() {
		
		this.getChildren().addAll(handResult, playerCardArea);
				
		this.setAlignment(Pos.BOTTOM_CENTER);
		
	}
	
	private void styleCardHolder() {
		String cssLayout = 
				"-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-border-style: solid;\n";

		playerCardArea.setStyle(cssLayout);
		playerCardArea.setPrefWidth(425);
		playerCardArea.setPrefHeight(130);
		playerCardArea.setMaxHeight(USE_PREF_SIZE);
		playerCardArea.setMaxWidth(USE_PREF_SIZE);
	}
	
	protected void showCards() {
		
		clearPlayerHand();
		
		Hand playerHand = player.getHand();
		Card[] playerCard = playerHand.getCards();
		
		for (int i =0; i<playerCard.length; i++) {
			String imageUrl = playerCard[i].getCardImage();
			
			playerCardArea.getChildren().add(new ImageView(imageUrl));
		}
		
	}

	protected void clearPlayerHand() {
		
		playerCardArea.getChildren().clear();
		
	}

	protected void showHandDescr() {
		Hand playerHand = player.getHand();
		PokerHand pokerHand = (PokerHand)playerHand;
		
		handResult.setText(pokerHand.getHandDescr());
	}

}