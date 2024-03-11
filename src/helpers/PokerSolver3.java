package helpers;

import java.util.Arrays;
import card.Card;
import hand.PokerHand;

public class PokerSolver3 {
	private static String[] cardFaces = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	private static String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
	private static int[] handFace;
	private static int[] handSuit;
	private static int handScore = 0;
	
	public static void evaluateHand(PokerHand hand) {

		int handSize = hand.getCards().length;

		handFace = new int[handSize];
		handSuit = new int[handSize];
		handScore = 0;
		
		getHandData(hand.getCards());

		if(!checkGroups(hand)) {
			boolean straight = checkStraits(hand, handFace);
			boolean flush = checkFlush(hand, handSuit);
			
			if(straight && flush) {
				straightFlush(hand, handFace);
			} else if (!straight && !flush){ 
				setHighCard(hand, handFace);
			}
		}
	}
		
	public static int[] evaluatePokerGame(PokerHand...hands) {
		int[] rankings = new int[hands.length];
		int[] handRanks = new int[hands.length];
		int[] handScores = new int[hands.length];
		int[] highCard = new int[hands.length];
		
		for(int i=0; i<hands.length; i++) {
			evaluateHand(hands[i]);
		}
		
		for(int i=0; i<rankings.length; i++) {
			rankings[i] = i;
			handRanks[i] = hands[i].getHandRank();
			handScores[i] = hands[i].getHandScore();
			highCard[i] = hands[i].getHighCard();
		}

		sortByHandRank(rankings, handRanks, handScores, highCard);
		
		sortByHandScore(rankings, handRanks, handScores, highCard);
		
		sortByHighCard(rankings, handRanks, handScores, highCard);
		
		
		int[] results = new int[rankings.length];
		
		results[rankings[0]] = 1;
		for(int i=1; i<handRanks.length; i++) {
			if(handRanks[i] == handRanks[i-1]) {  
				if(handScores[i] == handScores[i-1]) { 
					if(highCard[i] == highCard[i-1]) { 
						results[rankings[i-1]] = 2;
						results[rankings[i]] = 2;
					} else {
						break; 
					}
				} else {
					break;  
				}
			} else {
				break; 
			}
		}
		
		return results;
		
	}
	
	private static void sortByHighCard(int[] rankings, int[] handRanks, int[] handScore, int[] highCard) {
		int tempRanking;
		int tempHandRank;
		int tempScore;
		int tempHighCard;
		
		for(int maxElement = highCard.length-1; maxElement>0; maxElement--) {
			for(int i=1; i<=maxElement; i++) {
				if(handRanks[i] == handRanks[i-1]) {
					if(handScore[i] == handScore[i-1]) {
					
						if(highCard[i] > highCard[i-1]) {

							tempScore = handScore[i];
							tempHandRank = handRanks[i];
							tempRanking = rankings[i];
							tempHighCard = highCard[i];
							
							handScore[i] = handScore[i-1];
							handRanks[i] = handRanks[i-1];
							rankings[i] = rankings[i-1];
							highCard[i] = highCard[i-1];
							
							handScore[i-1] = tempScore;
							handRanks[i-1] = tempHandRank;
							rankings[i-1] = tempRanking;
							highCard[i-1] = tempHighCard;
						}
					}
				}
			}
		}
	}

	private static void sortByHandScore(int[] rankings, int[] handRanks, int[] handScore, int[] highCard) {
		int tempRanking;
		int tempHandRank;
		int tempScore;
		int tempHighCard;
		
		for(int maxElement = handScore.length-1; maxElement>0; maxElement--) {
			for(int i=1; i<=maxElement; i++) {
				if(handRanks[i] == handRanks[i-1]) {
					
					if(handScore[i] > handScore[i-1]) {

						tempScore = handScore[i];
						tempHandRank = handRanks[i];
						tempRanking = rankings[i];
						tempHighCard = highCard[i];

						handScore[i] = handScore[i-1];
						handRanks[i] = handRanks[i-1];
						rankings[i] = rankings[i-1];
						highCard[i] = highCard[i-1];
						
						handScore[i-1] = tempScore;
						handRanks[i-1] = tempHandRank;
						rankings[i-1] = tempRanking;
						highCard[i-1] = tempHighCard;
					}
				}
			}
		}		
	}

	private static void sortByHandRank(int[] rankings, int[] handRanks, int[] handScores, int[] highCard) {
	    //Insertion Sort
		int unsortedRanking;
		int unsortedHandRank;
		int unsortedHandScore;
		int unsortedHighCard;
		
		int scan;
		
		for(int i=1; i<handRanks.length; i++) {
			unsortedHandRank = handRanks[i];
			unsortedRanking = rankings[i];
			unsortedHandScore = handScores[i];
			unsortedHighCard = highCard[i];

	        scan = i;

	        while(scan > 0 && handRanks[scan - 1] < unsortedHandRank) {
	        	handRanks[scan] = handRanks[scan-1];
	        	rankings[scan] = rankings[scan-1];
	        	handScores[scan] = handScores[scan-1];
	        	highCard[scan] = highCard[scan-1];

	            scan--;
	        }
	        
	        handRanks[scan] = unsortedHandRank;
	        rankings[scan] = unsortedRanking;
	        handScores[scan] = unsortedHandScore;
	        highCard[scan] = unsortedHighCard;
		}
	}
		
	private static void getHandData(Card[] cards) {
		String faceString;
		
		//Break the cards down into face and suit
		for(int i=0; i<cards.length; i++) {
			//change the face to a card value
			faceString = cards[i].getFace();
			switch(faceString) {
				case "2":
				case "3":
				case "4":
				case "5":
				case "6":
				case "7":
				case "8":
				case "9":
					handFace[i] = Integer.parseInt(faceString);
					break;
				case "T":
					handFace[i] = 10;
					break;
				case "J":
					handFace[i] = 11;					
					break;
				case "Q":
					handFace[i] = 12;
					break;
				case "K":
					handFace[i] = 13;
					break;
				case "A":
					handFace[i] = 14;
					break;				
			}
						
			handSuit[i] = cards[i].getSuitIndex();
		}
		
		Arrays.sort(handFace);
		Arrays.sort(handSuit);			
	}

	private static boolean checkGroups(PokerHand hand) {
		int[] pairCounts = {1,1};
		int[] pairFace = {0, 0};
		
		boolean pairFound = false;
		boolean foundFirstGroup = false;
		boolean foundGroup = true;
		
		int groupNum = 0; 
		
		int checkRank = 0;
		String suffix = "";
		
		for(int i=1; i<handFace.length; i++) {
			if(handFace[i] == handFace[i-1]) {
				if(foundFirstGroup) {
					if(!pairFound) {
						groupNum++;
					}
				}
				
				foundFirstGroup = true;
				pairFound = true;
				
				pairCounts[groupNum]++;
				pairFace[groupNum] = handFace[i];
			} else { 
				pairFound = false;
			}
		}

		if(pairCounts[0] > 1) {
			
			checkRank = (pairCounts[0] == 4 || pairCounts[1] == 4) ? 8 : checkRank;

			checkRank = (checkRank == 0 && (pairCounts[0] + pairCounts[1]) == 5) ? 7 : checkRank;
			
			checkRank = (checkRank == 0 && pairCounts[0] == 3) ? 4 : checkRank;
			
			checkRank = (pairCounts[0] == 2 && pairCounts[1] == 2) ? 3 : checkRank;
			
			checkRank = (checkRank == 0 && pairCounts[0] == 2) ? 2 : checkRank;

		}

		hand.setHandRank(checkRank);
		
		if(pairFace[0] > 0) {
			suffix = cardFaces[pairFace[0] - 1] + "s";
			handScore += pairFace[0] * pairCounts[0];
			
			if(pairFace[1] > 0) {
				suffix = (pairFace[0] > pairFace[1])
						? suffix + " & " + cardFaces[pairFace[1] - 1] + "s"
						: cardFaces[pairFace[1] - 1] + "s & " +  suffix;
				
				handScore += pairFace[1] * pairCounts[1];
			}
		}
		
		switch (checkRank) {
			case 2: 
				hand.setHandDescr("Pair " + suffix);
				break;
			case 3: 
				hand.setHandDescr("Two Pair " + suffix);
				break;
			case 4: 
				hand.setHandDescr("Three of a Kind " + suffix);
				break;
			case 7: 
				hand.setHandDescr("Full House " + suffix);
				break;
			case 8: 
				hand.setHandDescr("Four of a Kind " + suffix);
				break;
			default: 
				foundGroup = false;
		}
		
		hand.setHandScore(handScore);
		
		int highCard = handFace[handFace.length-1]; 
		for(int i=(handFace.length-1); i>0; i--) {
			for(int j=0; j<pairFace.length; j++) {
				if(handFace[i] == pairFace[j]) {
					highCard = handFace[i-1];
				}
			}			
		}

		hand.setHighCard(highCard);

		return foundGroup;
	}

	private static boolean checkStraits(PokerHand hand, int[] handFace) {
		boolean straight = true;
		boolean hasAce;
		boolean aceTwo = false;
		
		hasAce = (handFace[handFace.length-1] == 14) ? true : false;
		
		for(int i=0; i<handFace.length; i++) {
			if(i==0 && handFace[i]==2 && hasAce) {
				aceTwo = true;
				continue;
			} else if(i==handFace.length-1 && aceTwo) {
				continue;
			} else if(i != 0) {
				if(handFace[i] - handFace[i-1] == 1) {
					continue;
				} else {
					straight = false;
					break;
				}
			}
		}
		
		if(straight) {
			for(int i=0; i<handFace.length; i++) {
				handScore += handFace[i];
			}
			hand.setHandDescr("Straight");
			hand.setHandRank(5);
			hand.setHandScore(handScore);
		}
		
		return straight;
	}

	private static boolean checkFlush(PokerHand hand, int[] handSuit) {
		boolean flush = true;
		
		for(int i=1; i<handSuit.length; i++) {
			if(handSuit[i] == handSuit[i-1]) {
				continue;
			} else {
				flush = false;
				break;
			}
		}
		
		if(flush) {
			hand.setHandDescr("Flush " + suits[handSuit[0]]);
			hand.setHandRank(6);
			hand.setHandScore(0);
		}
		
		return flush;
	}

	private static void straightFlush(PokerHand hand, int[] handFace) {
		hand.setHandDescr("Straight Flush");
		hand.setHandRank(9);
		
		if(handFace[0] == 10 && handFace[handFace.length - 1] == 14) {
			hand.setHandDescr("Royal Flush");
			hand.setHandRank(10);
		}
		
	}

	private static void setHighCard(PokerHand hand, int[] handFace) {		
		hand.setHandDescr("High Card " + cardFaces[handFace[handFace.length-1]-1]);
		hand.setHandRank(1);
		hand.setHandScore(handFace[handFace.length-1]);
		hand.setHighCard(handFace[handFace.length-2]);
	}
}
