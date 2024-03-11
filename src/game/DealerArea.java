package game;

import card.Card;
import hand.Hand;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import players.Player;

public class DealerArea extends HBox {
	
	private Player dealer;
	
	private HBox cardBack = new HBox();
	private HBox tioliHolder = new HBox();
	private HBox discardHolder = new HBox();

	
	public DealerArea(Player dealer) { 
		
		this.dealer = dealer;
		createCardBackArea(); 
		
		createTioliHolder();
		
		createDiscardHolder();
		
		addObjectsToTheHBox();

	}
	
	private void createCardBackArea() {
		
		cardBack.setAlignment(Pos.CENTER);
		
		styleCardHolders(cardBack);
		
		setCardBack("red");

	}
	
	private void createTioliHolder() {
		
		tioliHolder.setAlignment(Pos.CENTER);
		
		styleCardHolders(tioliHolder);
		
	}
	
	private void createDiscardHolder() {
		
		discardHolder.setAlignment(Pos.CENTER);
		
		styleCardHolders(discardHolder);
		
	}
	
	private void addObjectsToTheHBox() {
		this.getChildren().addAll(cardBack, tioliHolder, discardHolder);
		this.setAlignment(Pos.CENTER);
	}
	
	private void styleCardHolders(Pane paneToStyle) {
		String cssLayout = 
				"-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n" +
                "-fx-border-style: solid;\n";

		paneToStyle.setStyle(cssLayout);
		paneToStyle.setPrefWidth(100);
		paneToStyle.setPrefHeight(120);
		paneToStyle.setMaxHeight(USE_PREF_SIZE);
		paneToStyle.setMaxWidth(USE_PREF_SIZE);
	}
	
	public void setCardBack(String color) {
		
		cardBack.getChildren().clear();
		String imageUrl = "file:images/card/" + color + ".png";
		cardBack.getChildren().add(new ImageView(imageUrl));
	}
	
	protected void showTioliCard() {
		
		clearTioliHolder();
		
		Hand dealerHand = dealer.getHand();
		Card[] dealerCard = dealerHand.getCards();
		String imageUrl = dealerCard[0].getCardImage();

		tioliHolder.getChildren().add(new ImageView(imageUrl));
		
	}
	
	protected void showDiscardedCard(Card card) {
		
		clearDiscardHolder();

		String imageUrl = card.getCardImage();
		
		discardHolder.getChildren().add(new ImageView(imageUrl));
	}
	
	protected void clearTioliHolder() {
		
		tioliHolder.getChildren().clear();
		
	}
	
	protected void clearDiscardHolder() {
		
		discardHolder.getChildren().clear();
		
	}
	
}