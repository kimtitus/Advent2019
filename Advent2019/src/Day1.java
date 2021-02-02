import java.io.*;
import java.util.Scanner;

public class Day1 {
	static int intTotelFuel = 0 ;
	
	public static void main(String[] args) throws IOException {
		
		 Scanner input = new Scanner(new File("Day1File.txt"));
		 while(input.hasNext()) {
			 //System.out.println(input.nextLine());
			 intTotelFuel = intTotelFuel + getFuel(input.nextLine());
		 }
		 System.out.print("intTotelFuel = " + intTotelFuel);
	}
	
	private static int getFuel(String strFuel) {
		int intFuel =  Integer.valueOf(strFuel) / 3;
		intFuel = (int)Math.floor(intFuel) - 2;
		
		
		return intFuel;
	}
}