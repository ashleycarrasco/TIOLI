package gamedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import card.Card;
import hand.PokerHand;
import players.Player;


public class GameData {
	
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	public GameData() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tioli", "root", "Ac552658@09");
			System.out.println("Connection established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertHand(Player player) {
		String sqlStatement;
		Card[] cards = player.getHand().getCards();
		String handID = player.getId() + new Date().getTime();
		
		for(int i=0; i<cards.length;i++) {
			sqlStatement = 
			"INSERT INTO hands (hand_id, card_num, player_id, face, suit) " + 
			"VALUES(" +
			"'" + handID + "', " +
			(i+1) + ", " + 
			"'" + player.getId() + "', " + 
			"'" + cards[i].getFace() + "', " +
			"'" + cards[i].getSuit() + "'" + 
			");";
			
			try {
				statement.executeUpdate(sqlStatement);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void updateBank(Player player) {
		//PA 6.2
		String sqlStatement;
		sqlStatement = "UPDATE player ";
		sqlStatement += "\nSET bank = " +
		player.getBank() + " ";
		sqlStatement += "\nWHERE player_id = " + 
		"'" + player.getId() + "';";
		
		System.out.println(sqlStatement);
		
		try {
			statement.executeUpdate(sqlStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertResults(Player player, int amountWon) {
		
		String gameID = player.getId() + new Date().getTime();
		String sqlStatement;
		PokerHand tempPlayer = (PokerHand) player.getHand();
		sqlStatement = "INSERT INTO game_results (game_id, player_id, hand_descr, amount_won, player_bank)" +
		"\nVALUES(" +  "'game" + gameID + "', " +
		"'" + player.getId() + "', " +
		"'" + tempPlayer.getHandDescr() + "', " +
		"'" + amountWon + "', " + 
		"'" + player.getBank() + "'" +
		");";
		
		System.out.println(sqlStatement);
		
		try {
			statement.executeUpdate(sqlStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet getPlayers() {
		try {
			resultSet = statement.executeQuery("SELECT * FROM player");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public Player getRandomPlayer() {
		Player player = null;
		
		try {
			ResultSet playerData = getPlayers();
			
			playerData.last();
			int size = playerData.getRow() - 1;

			int randomPlayer = (int)(Math.random() * size) + 1;
			playerData.absolute(randomPlayer);

			player = new Player(playerData.getString(2), playerData.getString(1), playerData.getInt(3), new PokerHand()); 
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return player; 
	}
	

	public ResultSet getReportData(Player player) {
		try {
			resultSet = statement.executeQuery("SELECT * FROM game_results WHERE player_id = '" + player.getId() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultSet;		
	}
	

	public void close() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
