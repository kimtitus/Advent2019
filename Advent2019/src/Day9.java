import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day9 {
	public static List<String> lstMasterInput = new ArrayList<>();

	/*
	 * PART 1 VARIABLES
	 */
	public static Integer intRelativeBase = 0;
	public static Map<String,String> mapExtendedMemory = new HashMap<>();


	/*
	 * PART 2 VARIABLES
	 */

	public Day9() {
		intPositionCounter = 0;
		intVerb = 1;
		intNoun = 1;
	}

	public int intPositionCounter;
	public int intNoun;
	public int intVerb;
	public List<String>lstInput;

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(new File("Day9File.txt"));
		String strLine = input.nextLine();
		lstMasterInput = Arrays.asList(strLine.split(","));
		Day9 objDay9 = new Day9();
		objDay9.lstInput = new ArrayList<String>(lstMasterInput);
		while (!isInput99(objDay9)) {
			setOpCodeResult(objDay9);
		}

	}

	private static Boolean isInput99(Day9 objDay9) {
		return objDay9.lstInput.get(objDay9.intPositionCounter).equals("99") ? true : false;
	}

	private static Long getIndexValue(int intPosition ,List<String> lstInput) {
		if (intPosition >= lstInput.size()) {
			if (mapExtendedMemory.get(String.valueOf(intPosition)) == null) {
				mapExtendedMemory.put(String.valueOf(intPosition), "0");
				return (long) 0;
			}
			return mapExtendedMemory.get(String.valueOf(intPosition)) == null ? 0 : Long.valueOf(mapExtendedMemory.get(String.valueOf(intPosition)));
		}
		return Long.valueOf(lstInput.get(intPosition));
	}

	private static void setOpCodeResult (Day9 objDay9) {
		int intInstruction = Integer.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter));
		List<Integer> lstCode = breakDownInstruction(intInstruction);
		int intOpCode = lstCode.get(0);
		Long intPosition2;
		Long intPosition3;
		Long intPosition4;
		lstCode.remove(0);
		lstCode.remove(0);
		List<Integer> lstParameterMode = new ArrayList<Integer>(lstCode);
		objDay9.intPositionCounter++;
		if (intOpCode == 1 || intOpCode == 2) {
			intPosition2 = getPosition(objDay9, lstParameterMode.get(0));
			objDay9.intPositionCounter++;
			intPosition3 = getPosition(objDay9, lstParameterMode.get(1));
			objDay9.intPositionCounter++;
			intPosition4 = getPosition4(objDay9, lstParameterMode.get(2));
			Long intResult = intOpCode == 1 ? intPosition2 + intPosition3 : intPosition2 * intPosition3;
			setPosition(objDay9, intResult, intPosition4, mapExtendedMemory);

		} 
		else if (intOpCode == 3) {
			if (lstParameterMode.get(0) != 2) {
				intPosition2 = getPosition(objDay9, lstParameterMode.get(0));
			}
			else {
				int intTemp = intRelativeBase + Integer.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter));
				intPosition2 = (long)intTemp;
			}
			Scanner objScanner = new Scanner(System.in);  

			System.out.println("Enter input");
			String strUserInput = objScanner.nextLine();
			Long intResult = Long.parseLong(strUserInput);
			setPosition(objDay9, intResult, intPosition2, mapExtendedMemory);
		} 
		else if (intOpCode == 4) {
			intPosition2 = getPosition(objDay9, lstParameterMode.get(0));
			System.out.println("opCode = 4 " + "output = " + intPosition2);
		} 
		else if (intOpCode == 5) {
			intPosition2 = getPosition(objDay9, lstParameterMode.get(0));
			objDay9.intPositionCounter++;
			intPosition3 = getPosition(objDay9, lstParameterMode.get(1));
			objDay9.intPositionCounter--;
			objDay9.intPositionCounter = intPosition2 != (long)0 ? intPosition3.intValue() -1  : objDay9.intPositionCounter + 1;
		} 
		else if (intOpCode == 6) { 
			intPosition2 = getPosition(objDay9, lstParameterMode.get(0));
			objDay9.intPositionCounter++;
			intPosition3 = getPosition(objDay9, lstParameterMode.get(1));
			objDay9.intPositionCounter--;
			objDay9.intPositionCounter = intPosition2 == 0 ? intPosition3.intValue() - 1 : objDay9.intPositionCounter + 1 ;
		} 
		else if (intOpCode == 7) {
			intPosition2 =  getPosition(objDay9, lstParameterMode.get(0));
			objDay9.intPositionCounter++;
			intPosition3 =  getPosition(objDay9, lstParameterMode.get(1));
			objDay9.intPositionCounter++;
			intPosition4 = getPosition4(objDay9, lstParameterMode.get(2));
			Long intResult = intPosition2 < intPosition3 ? (long)1 : (long)0;
			setPosition(objDay9, intResult, intPosition4, mapExtendedMemory);
		} 
		else if (intOpCode == 8) {
			intPosition2 =  getPosition(objDay9, lstParameterMode.get(0));
			objDay9.intPositionCounter++;
			intPosition3 =  getPosition(objDay9, lstParameterMode.get(1));
			objDay9.intPositionCounter++;
			intPosition4 = getPosition4(objDay9, lstParameterMode.get(2));
			Long intResult = intPosition2 == intPosition3 ? (long)1 : (long)0;
			setPosition(objDay9, intResult, intPosition4, mapExtendedMemory);
		} 
		else if (intOpCode == 9) {
			intPosition2 =  getPosition(objDay9, lstParameterMode.get(0));
			intRelativeBase = intRelativeBase + intPosition2.intValue();
		}
		objDay9.intPositionCounter++;
	}

	private static Long getPosition4(Day9 objDay9, int intParamMode) {
		Long intPosition4 = (long)0;
		if (intParamMode == 0) {
			intPosition4 = Long.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter));
		}else {
			int intTemp = intRelativeBase + Integer.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter));
			intPosition4 = Long.valueOf(intTemp);
		}

		return intPosition4;
	}

	public static void setPosition(Day9 objDay9, Long intResult, Long intPosition4, Map<String,String> mapExtendedMemory) {
		if (intPosition4 > objDay9.lstInput.size()) {
			mapExtendedMemory.put(String.valueOf(intPosition4), String.valueOf(intResult));
		}
		else {
			objDay9.lstInput.set(intPosition4.intValue(), String.valueOf(intResult));
		}

	}

	private static Long getPosition(Day9 objDay9, int intParamMode) {
		Long intPosition = (long) 0;
		if (intParamMode == 0) {
			intPosition = getIndexValue(Integer.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter)),objDay9.lstInput);

		}
		else if (intParamMode == 1) {
			intPosition = Long.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter));
		}
		else if (intParamMode ==  2) {
			intPosition = (Long.valueOf(objDay9.lstInput.get(objDay9.intPositionCounter)) + intRelativeBase);
			intPosition = getIndexValue(intPosition.intValue(),objDay9.lstInput); 

		}
		return intPosition;
	}

	private static List<Integer> breakDownInstruction(int intInstruction) {
		List<Integer> lstCode = new ArrayList<Integer>();
		lstCode.add(intInstruction % 10);
		intInstruction = intInstruction / 10;
		lstCode.add(intInstruction % 10);
		intInstruction = intInstruction / 10;
		lstCode.add(intInstruction % 10);
		intInstruction = intInstruction / 10;
		lstCode.add(intInstruction % 10);
		intInstruction = intInstruction / 10;
		lstCode.add(intInstruction % 10);

		return lstCode;

	}

}
