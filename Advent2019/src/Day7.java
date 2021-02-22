import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day7 {
	public static List<String> lstMasterInput = new ArrayList<>();
	public static List<String> lstSeq = new ArrayList<>();
	
	public static Boolean boolBreak = false;
	
	public int intPositionCounter;
	public int intNoun;
	public int intVerb;
	public List<String>lstInput;
	
	
	/*
	 * PART 1 Variables
	 */
	public int intAmpOutput;
	public static int intMaxThruster = 0;
	
	
	/*
	 * PART 2 Variables
	 */
	public Day7 objAmpPair;
	public static boolean boolEndOfInput = false;
	public static List<Integer> lstTempAmpOutPut = new ArrayList<>();
	
	public List<Integer> lstOutPut;
	public Day7() {
		lstOutPut = new ArrayList<>();
		intPositionCounter = intAmpOutput = 0;
		intVerb = 1;
		intNoun = 1;
	}
	


	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(new File("Day7File.txt"));
		String strLine = input.nextLine();
		lstMasterInput = Arrays.asList(strLine.split(","));

		Day7 objAmp1 = new Day7();
		Day7 objAmp2 = new Day7();
		Day7 objAmp3 = new Day7();
		Day7 objAmp4 = new Day7();
		Day7 objAmp5 = new Day7();
		resetLstInput(objAmp1, objAmp2, objAmp3, objAmp4, objAmp5, lstMasterInput);
		objAmp1.objAmpPair = objAmp2;
		objAmp2.objAmpPair = objAmp3;
		objAmp3.objAmpPair = objAmp4;
		objAmp4.objAmpPair = objAmp5;
		objAmp5.objAmpPair = objAmp1;
		lstSeq = setSequenceList(lstSeq, 0, 5);
		for (String s : lstSeq) {
			setAmpInput(objAmp1, objAmp2, objAmp3, objAmp4, objAmp5, s);
			objAmp1.lstOutPut.add(0);
			while (!isInput99(objAmp1)) {
				setOpCodeResult(objAmp1);
			}
			while (!isInput99(objAmp2)) {
				setOpCodeResult(objAmp2);
			}
			while (!isInput99(objAmp3)) {
				setOpCodeResult(objAmp3);
			}
			while (!isInput99(objAmp4)) {
				setOpCodeResult(objAmp4);
			}
			while (!isInput99(objAmp5)) {
				setOpCodeResult(objAmp5);
			}
			resetLstInput(objAmp1, objAmp2, objAmp3, objAmp4, objAmp5, lstMasterInput);
			objAmp1.lstOutPut.clear();
			objAmp2.lstOutPut.clear();
			objAmp3.lstOutPut.clear();
			objAmp4.lstOutPut.clear();
			objAmp5.lstOutPut.clear();
			objAmp1.intPositionCounter = 0;
			objAmp2.intPositionCounter = 0;
			objAmp3.intPositionCounter = 0;
			objAmp4.intPositionCounter = 0;
			objAmp5.intPositionCounter = 0;
			intMaxThruster = objAmp5.intAmpOutput > intMaxThruster ? objAmp5.intAmpOutput : intMaxThruster;
			
		}
		System.out.println("intMaxThrustert = " + intMaxThruster);
		
		
		lstSeq = setSequenceList(lstSeq, 5, 10);
		for (String s : lstSeq) {
			lstTempAmpOutPut.clear();
			do{
				resetLstInput(objAmp1, objAmp2, objAmp3, objAmp4, objAmp5, lstMasterInput);
				objAmp1.intPositionCounter = 0;
				objAmp2.intPositionCounter = 0;
				objAmp3.intPositionCounter = 0;
				objAmp4.intPositionCounter = 0;
				objAmp5.intPositionCounter = 0;
				
				objAmp1.lstOutPut.clear();
				setAmpInput(objAmp1, objAmp2, objAmp3, objAmp4, objAmp5, s);
				objAmp1.lstOutPut.add(0);
				objAmp1.lstOutPut.addAll(lstTempAmpOutPut);
				while (!isInput99(objAmp1)) {
					setOpCodeResult(objAmp1);
					if (boolEndOfInput == true) {
						break;
					}
				}
				boolEndOfInput= false;
				while (!isInput99(objAmp2)) {
					setOpCodeResult(objAmp2);
					if (boolEndOfInput == true) {
						break;
					}
				}
				boolEndOfInput= false;
				while (!isInput99(objAmp3)) {
					setOpCodeResult(objAmp3);
					if (boolEndOfInput == true) {
						break;
					}
				}
				boolEndOfInput= false;
				while (!isInput99(objAmp4)) {
					setOpCodeResult(objAmp4);
					if (boolEndOfInput == true) {
						break;
					}
				}
				boolEndOfInput= false;
				while (!isInput99(objAmp5)) {
					setOpCodeResult(objAmp5);
					if (boolEndOfInput == true) {
						break;
					}
				}
				lstTempAmpOutPut = new ArrayList<>(objAmp1.lstOutPut);
				boolEndOfInput= false;
			}while (!isInput99(objAmp5)); 
		}
		for (Integer i : objAmp1.lstOutPut) {
			intMaxThruster = i > intMaxThruster ? i : intMaxThruster;
		}
		System.out.println("intMaxThrustert = " + intMaxThruster);
	
	}
	
	public static void resetLstInput(Day7 objAmp1, Day7 objAmp2, Day7 objAmp3, Day7 objAmp4, Day7 objAmp5, List<String> lstMasterInput) {
		objAmp1.lstInput = new ArrayList<>(lstMasterInput);
		objAmp2.lstInput = new ArrayList<>(lstMasterInput);
		objAmp3.lstInput = new ArrayList<>(lstMasterInput);
		objAmp4.lstInput = new ArrayList<>(lstMasterInput);
		objAmp5.lstInput = new ArrayList<>(lstMasterInput);
	}

	private static List<String> setSequenceList(List<String> lstSeq, int intMin, int intMax){
		for( int i = intMin; i < intMax; i ++) {
			for (int j = intMin; j < intMax; j++ ) {
				if (i != j) {
					//System.out.println("s = " + i + "" + j);
					for (int k = intMin; k < intMax; k++) {
						if (k != i && k != j) {
							//System.out.println("s = " + i + "" + j + k);
							for (int l = intMin; l < intMax; l++) {
								if (l != i && l != j && l != k) {
									for (int m = intMin; m < intMax; m++) {
										if ( m != i && m != j && m != k && m != l) {
											lstSeq.add("" + i + j + k + l + m);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return lstSeq;
	}

	private static void setAmpInput( Day7 amp1, Day7 amp2, Day7 amp3, Day7 amp4, Day7 amp5, String strSequence) {
		amp1.lstOutPut.add(Integer.valueOf(strSequence.substring(0,1)));
		amp2.lstOutPut.add(Integer.valueOf(strSequence.substring(1,2)));
		amp3.lstOutPut.add(Integer.valueOf(strSequence.substring(2,3)));
		amp4.lstOutPut.add(Integer.valueOf(strSequence.substring(3,4)));
		amp5.lstOutPut.add(Integer.valueOf(strSequence.substring(4,5)));
	}

	private static Boolean isInput99(Day7 objDay7) {
		return objDay7.lstInput.get(objDay7.intPositionCounter).equals("99")  ? true : false;
	}

	private static int getIndexValue(int intPosition ,List<String> lstInput) {
		return Integer.valueOf(lstInput.get(intPosition));
	}


	private static void setOpCodeResult (Day7 objDay7) {
		int intInstruction = Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter));
		List<Integer> lstCode = breakDownInstruction(intInstruction);
		int intOpCode = lstCode.get(0);
		int intPosition2;
		int intPosition3;
		int intPosition4;
		lstCode.remove(0);
		lstCode.remove(0);
		List<Integer> lstParameterMode = new ArrayList<Integer>(lstCode);
		objDay7.intPositionCounter++;
		if (intOpCode == 1 || intOpCode == 2) {
			intPosition2 =  getPosition2(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition3 = getPosition3(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition4 = Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter));
			int intResult = intOpCode == 1 ? intPosition2 + intPosition3 : intPosition2 * intPosition3;
			objDay7.lstInput.set(intPosition4, String.valueOf(intResult));
		} else if (intOpCode == 3) {
			intPosition2 = Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter));
			int intInput = objDay7.lstOutPut.size() > 0 ? objDay7.lstOutPut.get(0) : -1;
			if (intInput == -1) {
				boolEndOfInput = true;
			}else {
				objDay7.lstOutPut.remove(0);
			}
			objDay7.lstInput.set(intPosition2, String.valueOf(intInput));
		} else if (intOpCode == 4) {
			intPosition2 = getPosition2(lstParameterMode, objDay7);
			objDay7.intAmpOutput = intPosition2;
			objDay7.objAmpPair.lstOutPut.add(intPosition2);
		} else if (intOpCode == 5) {
			intPosition2 =  getPosition2(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition3 = getPosition3(lstParameterMode, objDay7);
			objDay7.intPositionCounter--;
			objDay7.intPositionCounter = intPosition2 != 0 ? intPosition3 -1  : objDay7.intPositionCounter + 1;
		} else if (intOpCode == 6) {
			intPosition2 =  getPosition2(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition3 = getPosition3(lstParameterMode, objDay7);
			objDay7.intPositionCounter--;
			objDay7.intPositionCounter = intPosition2 == 0 ? intPosition3 - 1 : objDay7.intPositionCounter + 1 ;
		} else if (intOpCode == 7) {
			intPosition2 =  getPosition2(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition3 =  getPosition3(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition4 = (Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter)));
			objDay7.lstInput.set(intPosition4, intPosition2 < intPosition3 ? "1" : "0" );
		} else if (intOpCode == 8) {
			intPosition2 =  getPosition2(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition3 =  getPosition3(lstParameterMode, objDay7);
			objDay7.intPositionCounter++;
			intPosition4 = (Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter)));
			objDay7.lstInput.set(intPosition4, intPosition2 == intPosition3 ? "1" : "0" );

		}
		objDay7.intPositionCounter++;
	}
	
	private static int getPosition2(List<Integer> lstParameterMode, Day7 objDay7) {
		int intPosition2 = 0;intPosition2 = lstParameterMode.get(0).equals(0) ? getIndexValue(Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter)),objDay7.lstInput) : Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter));
		return intPosition2;
	}

	
	private static int getPosition3(List<Integer> lstParameterMode, Day7 objDay7) {
		int intPosition3 = 0;
		intPosition3 = lstParameterMode.get(1).equals(0) ? getIndexValue(Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter)),objDay7.lstInput) : Integer.valueOf(objDay7.lstInput.get(objDay7.intPositionCounter));
		return intPosition3;
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
		return lstCode;

	}

}
