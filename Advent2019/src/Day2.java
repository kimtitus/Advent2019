import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day2 {
	public static String[] lstStr;
	public static String[] lstStr2;
	public static String[] lstStrMaster;
	public static int intCurrentPosition = 0;
	public static String strOperation;
	
	public static int intNoun = 1;
	public static int intVerb = 1;
	
	public static void main(String[] args) throws IOException {
		
		 Scanner input = new Scanner(new File("Day2File.txt"));
		 String strLine = input.nextLine();
		 lstStr = strLine.split(",");
		 lstStr2 = strLine.split(",");
		 lstStrMaster = strLine.split(",");
		 lstStr[1] = "12";
		 lstStr[2] = "2";
		 while(!isFinished()) {
			doOperation(lstStr);
		 }
		 System.out.println("[0] Part 1 = " + lstStr[0]);
		 
		 while(!isFinished2()) {
			intCurrentPosition = 0;
			lstStr2 = lstStrMaster.clone();
		
			
			 doOperation2();
			
			 doIncrement();
			
		 
		}
		 System.out.println("[0] Part 2 = " + lstStr2[0]);
		 System.out.println("[1] Part 2 = " + lstStr2[1]);
		 System.out.println("[2] Part 2 = " + lstStr2[2]);
		 System.out.println("Final Answer Part2 = " + (100 * Integer.valueOf(lstStr2[1]) + Integer.valueOf(lstStr2[2])));
	}
	
	
	public static boolean isFinished() {
		
		return lstStr[intCurrentPosition].equals("99");
	}
	
	public static void doOperation(String[] lst) {
		if (lst[intCurrentPosition].equals("1")) {
			strOperation = "add";
		} else {
			strOperation = "multiply";
		}
		intCurrentPosition++;
		int intVal1 = getNextPosition(lst);
		int intVal2 = getNextPosition(lst);
		int intPosition = getPositionToAdd(lst);
		int intValue = strOperation == "add" ? intVal1 + intVal2 : intVal1 * intVal2;
		//System.out.println("intPosition = " + intPosition);
		lst[intPosition] = String.valueOf(intValue);
		
		
	}
	
	public static int getNextPosition(String[] lst) {
		int intPositionValue = Integer.valueOf(lst[intCurrentPosition]);
		intCurrentPosition++;
		return Integer.valueOf(lst[intPositionValue]);
	}
	
	public static int getPositionToAdd(String[] lst) {
		
		int intPosition = Integer.valueOf(lst[intCurrentPosition]);
		intCurrentPosition++;
		return intPosition;
	}
	
	public static boolean isFinished2() {
		
		return lstStr2[0].equals("19690720");
	}
	
	public static void doOperation2() {
		lstStr2[1] = String.valueOf(intNoun);
		lstStr2[2] = String.valueOf(intVerb);
	    while(!isFinished()) {
			doOperation(lstStr2);
		 }
		
	}
	
	public static void doIncrement() {
		if( intNoun != 99) {
			intNoun++;
		}else if(intVerb != 99) {
			intNoun = 1;
			intVerb++;
		}
	}
	
	
}

//PART 1
//Check if program is finished
//Check the current position to see what operation must be done
//Do the operation
//Get the following 3 digits

/*
PART 2
Check if the program is finished --> Total must equal 19690720
Max each digit can be is 99
increase noun 1 - 99 while incrementing verb by 1
*/