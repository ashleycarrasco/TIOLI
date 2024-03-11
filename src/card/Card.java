package card;


public class Card {
	
	 private int cardNumber;
	 private String suit;
	 private int suitIndex;
	 private String color;
	 private int cardRank;
	 private String face;
	 private int faceValue;
	 private String cardImage;
	 private static int cardsCreated;
	
	
	public Card(int cardNumber, int suitIndex, int cardRank) {
		this.cardNumber = cardNumber;
		this.suitIndex = suitIndex;
		this.cardRank = cardRank;
		
		setSuit();
		setCardValues();
		
		setCardImage();
		
		cardsCreated = cardsCreated + 1;
	}
	

	private void setSuit() {
		switch(suitIndex) {
			case 0:
				this.suit = "s";
				this.color = "b";
				break;
			case 1:
				this.suit = "h";
				this.color = "r";
				break;
			case 2:
				this.suit = "d";
				this.color = "r";
				break;
			case 3:
				this.suit = "c";
				this.color = "b";
				break;
		}	
	}

	private void setCardValues() {
				
		switch(this.cardRank) {
	    	case 1:
	    		this.face = "A";
	    		this.faceValue = cardRank;
	    		break;
	      case 2:
	      case 3:
	      case 4:
	      case 5:
	      case 6:
	      case 7:
	      case 8:
	      case 9:
	    	  this.face = Integer.toString(cardRank);
	    	  this.faceValue = cardRank;
	    	  break;
	      case 10:
	    	  this.face = "T";
	    	  this.faceValue = 10;
	    	  break;
	      case 11:
	    	  this.face = "J";
	    	  this.faceValue = 10;
	    	  break;
	      case 12:
	    	  this.face = "Q";
	    	  this.faceValue = 10;
	    	  break;
	      case 13:
	    	  this.face = "K";
	    	  this.faceValue = 10;
	    	  break;
		  case 14:
			  this.face = "A";
	    	  this.faceValue = cardRank;
	    	  break;
	    }
		
	}

	private void setCardImage() {

		this.cardImage = "file:images/card/" + Integer.toString(cardNumber+1) + ".png";
	}

	public String toString() {
		return face + suit;
		
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public String getSuit() {
		return suit;
	}
	public int getSuitIndex() {
		return suitIndex;
	}
	public String getColor() {
		return color;
	}
	public int getCardRank() {
		return cardRank;
	}
	public String getFace() {
		return face;
	}
	public int getFaceValue() {
		return faceValue;
	}
	public String getCardImage() {
		return cardImage;
	}
	public static int getCardsCreated() {
		return cardsCreated;
	}
}
