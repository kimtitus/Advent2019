import java.io.*;
import java.util.Scanner;

public class Day1 {
	static int intTotalFuelDay1 = 0 ;
	static int intTotalFuelDay2 = 0 ;
	
	public static void main(String[] args) throws IOException {
		
		 Scanner input = new Scanner(new File("Day1File.txt"));
		 while(input.hasNext()) {
			 //System.out.println(input.nextLine());
			 int intFuel =  getFuel(input.nextLine());
			 intTotalFuelDay1 = intTotalFuelDay1 + intFuel;

			 intTotalFuelDay2 = intTotalFuelDay2 + intFuel;
			 //System.out.println("intFuel = " + intFuel);
			 intTotalFuelDay2 = getFuelsFuel(intFuel);
			 
		 }
		 System.out.println("intTotalFuelDay1 = " + intTotalFuelDay1);
		 System.out.println("intTotalFuelDay2 = " + intTotalFuelDay2);
	}
	
	private static int getFuel(String strFuel) {
		int intFuel =  Integer.valueOf(strFuel) / 3;
		//System.out.println("intFuel = " + intFuel);
		intFuel = (int)Math.floor(intFuel) - 2;
		//System.out.println("intFuel = " + intFuel);
		
		return intFuel;
	}
	
	private static int getFuelsFuel(int intFuel) {
		while(intFuel > 0) {
			 intFuel =  getFuel(String.valueOf(intFuel));
			 if(intFuel > 0) {
				 intTotalFuelDay2 = intTotalFuelDay2 + intFuel;
			 }
		 }
		
		return intTotalFuelDay2; 
	}
}
