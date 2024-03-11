import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Assignment6_1 {

	public static void main(String[] args) {
		
		System.out.println("ID\tPlayer Name\t\tHandDescr\t\tWin Amount\tPlayer Bank");
		try(DataInputStream input = new DataInputStream(new FileInputStream("files/gamedata.dat"))) {
			while(true) {
				System.out.print(input.readUTF() + "\t");
				System.out.print(input.readUTF() + "\t\t");
				System.out.print(input.readUTF() + "\t\t");
				System.out.print(input.readInt() + "\t\t");
				System.out.println(input.readInt());
			}
			
		} catch (EOFException ex) {
			System.out.println("\nAll data has been read");
		} catch (IOException ex) {
			System.out.println("File not found");
		}
	}

}
