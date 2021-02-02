import java.io.*;
import java.util.Scanner;

public class Day1 {
	static int intTotelFuel = 0 ;
	
	public static void main(String[] args) throws IOException {
		
		 Scanner input = new Scanner(new File("Day1File.txt"));
		 while(input.hasNext()) {
			 //System.out.println(input.nextLine());
			 int intFuel =  getFuel(input.nextLine());
			 intTotelFuel = intTotelFuel + intFuel;
			 System.out.println("intFuel = " + intFuel);
			 intTotelFuel = getFuelsFuel(intFuel);
			 
		 }
		 System.out.print("intTotelFuel = " + intTotelFuel);
	}
	
	private static int getFuel(String strFuel) {
		int intFuel =  Integer.valueOf(strFuel) / 3;
		intFuel = (int)Math.floor(intFuel) - 2;
		
		
		return intFuel;
	}
	
	private static int getFuelsFuel(int intFuel) {
		while(intFuel > 0) {
			 intFuel =  getFuel(String.valueOf(intFuel));
			 if(intFuel > 0) {
				 intTotelFuel = intTotelFuel + intFuel;
			 }
		 }
		
		return intTotelFuel;
	}
}
