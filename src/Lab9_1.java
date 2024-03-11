import players.Player;

public class Lab9_1 {
	
	public static void main(String[] args) {
		Player player1 = new Player();
		Player player2 = new Player("Bob", "123456");
		Player player3 = new Player("Adam", "54321", 3000);
		
		System.out.println(player1.toString());
		System.out.println(player2);
		System.out.println(player3);
	}
}
