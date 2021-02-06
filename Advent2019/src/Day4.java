
import java.util.HashSet;

import java.util.Set;

public class Day4 {
	int intSpotOne;
	int intSpotTwo;
	int intSpotThree;
	int intSpotFour;
	int intSpotFive;
	int intSpotSix;
	
	public Day4 () {
		intSpotOne = 2;
		intSpotTwo = 3;
		intSpotThree = 1;
		intSpotFour = 8;
		intSpotFive = 3;
		intSpotSix = 2;
		
	}

	public static void main(String[] args) {
		Set<Integer> lstCorrectPassword = new HashSet<Integer>();
		Day4 objDay4 = new Day4();
		while (withinRange(objDay4)) {

			int intTrialPassword = getTrialPassword(objDay4);
			
			addTrialPasswordToSpots(objDay4, intTrialPassword);
			if (!isDecreasingCheck(objDay4) && isTwoAdjacent(objDay4)) {
				if (!is3Adjacent(objDay4.intSpotOne, objDay4.intSpotTwo, objDay4.intSpotThree)
						&& !is3Adjacent(objDay4.intSpotTwo, objDay4.intSpotThree, objDay4.intSpotFour)
						&& !is3Adjacent(objDay4.intSpotThree, objDay4.intSpotFour, objDay4.intSpotFive)
						&& !is3Adjacent(objDay4.intSpotFour, objDay4.intSpotFive, objDay4.intSpotSix)) {
					lstCorrectPassword.add(getPassword(objDay4));
				}else if (isOnly2Adjacent(objDay4)) {
					lstCorrectPassword.add(getPassword(objDay4));
				}
			}
			
			
		
		}
		System.out.println("lstCorrectPassword.size = " + lstCorrectPassword.size());
	}
	
	
	
	
	
	
	
	public static Boolean isDecreasingCheck(Day4 objDay4) {
		if (objDay4.intSpotOne <= objDay4.intSpotTwo
				&& objDay4.intSpotTwo <= objDay4.intSpotThree
				&& objDay4.intSpotThree <= objDay4.intSpotFour 
				&& objDay4.intSpotFour <= objDay4.intSpotFive
				&& objDay4.intSpotFive <= objDay4.intSpotSix) {
			return false;
		}
		return true;
	}
	
	public static Boolean isTwoAdjacent (Day4 objDay4) {
		
		if (objDay4.intSpotOne == objDay4.intSpotTwo
				|| objDay4.intSpotTwo == objDay4.intSpotThree
				|| objDay4.intSpotThree == objDay4.intSpotFour
				|| objDay4.intSpotFour == objDay4.intSpotFive
				|| objDay4.intSpotFive == objDay4.intSpotSix) {
			return true;
		}
		return false;
	}
	
	public static Boolean isOnly2Adjacent (Day4 objDay4) {
		
		String strPassword = String.valueOf(getPassword(objDay4));
		char[] arrayChar = strPassword.toCharArray();
		char charTemp = arrayChar[0];
		int intCharDup = 1;
		Boolean boolOnly2Adjacent = false;
		for (int i = 1 ; i < arrayChar.length ; i ++) {
			if ( charTemp != arrayChar[i] ) {
				charTemp = arrayChar[i];
				if (intCharDup == 2) {
					boolOnly2Adjacent = true;
				}
				intCharDup = 1;
			}else {
				intCharDup++;
			}
				
		}
		if(intCharDup == 2) {
			boolOnly2Adjacent = true;
		}
		return boolOnly2Adjacent;
	}
	
	public static Boolean is3Adjacent (int int1, int int2, int int3) {
		if (int1 == int2 && int2 == int3) {
			return true;
		}
		return false;
	}
	
	public static Boolean withinRange (Day4 objDay4) {
		int strPasswordInput = getPassword(objDay4);
		if (strPasswordInput >= 231832 && strPasswordInput <= 767346) {
			return true;
		}
		return false;
	}
	
	public static int getTrialPassword (Day4 objDay4) {
		int intPassword = getPassword(objDay4);
		intPassword++;
		return intPassword;
	}
	
	public static void addTrialPasswordToSpots (Day4 objDay4, int intTrialPassword) {
		String strTrialPassword = String.valueOf(intTrialPassword);
		objDay4.intSpotOne = Integer.valueOf(strTrialPassword.substring(0,1));
		objDay4.intSpotTwo =  Integer.valueOf(strTrialPassword.substring(1,2));
		objDay4.intSpotThree =  Integer.valueOf(strTrialPassword.substring(2,3));
		objDay4.intSpotFour = Integer.valueOf(strTrialPassword.substring(3,4));
		objDay4.intSpotFive = Integer.valueOf(strTrialPassword.substring(4,5));
		objDay4.intSpotSix = Integer.valueOf(strTrialPassword.substring(5,6));
	}
	
	
	public static int getPassword (Day4 objDay4) {
		String strPassword = "" + objDay4.intSpotOne 
				+ objDay4.intSpotTwo 
				+ objDay4.intSpotThree
				+ objDay4.intSpotFour 
				+ objDay4.intSpotFive 
				+ objDay4.intSpotSix;
		return Integer.valueOf(strPassword);
	}
}
	
	

	

