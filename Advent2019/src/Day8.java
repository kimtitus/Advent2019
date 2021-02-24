import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day8 {

	public static List<String> lstMasterInput = new ArrayList<>();


	/*
	 * PART 1 VARIABLES
	 */
	public static List<String> lstAllDigits = new ArrayList<>();
	public static Map<String,Day8> mapLayer = new HashMap<>();
	public static List<Integer> lstTotals =  new ArrayList<>();
	public String strLayerName;
	public int intTotal1;
	public int intTotal2;
	public int intTotal0;
	public List<String> lstDigits;


	/*
	 * PART 2 VARIABLES
	 */
	public static String strFinalImage = "";

	public Day8(Integer intLayerName) {
		lstDigits = new ArrayList<>();
		strLayerName = "" + intLayerName;
		intTotal1 = intTotal2 = intTotal0 = 0;
	}

	public static void main(String[] args) throws IOException {
		int intWidth = 25;
		int intTall = 6;
		executeProgram(intWidth, intTall);
		Day8 objLayer = findFewest0s(mapLayer);
		System.out.println("Answer = " + objLayer.strLayerName + " " + (objLayer.intTotal1 * objLayer.intTotal2));


		intWidth = 25;
		intTall = 6;
		executeProgram(intWidth, intTall);
		for(int i = 0; i < intTall; i++) {
			String strFirstLayer = lstAllDigits.get(i);
			String StringArray[] = strFirstLayer.split("\\B");
			List<String> lstFirstLayerInt = Arrays.asList(StringArray);
			for(int j = 0; j < lstFirstLayerInt.size(); j++) {
				String strPixel = comparePixelsString(lstFirstLayerInt.get(j), lstAllDigits, intTall + i, j, intTall);
				if (strPixel.equals("1")) {
					strPixel = " ";
				}
				else if (strPixel.equals("0")) {
					strPixel = "o";
				}
				strFinalImage = strFinalImage + strPixel;

			}
			System.out.println(strFinalImage);
			strFinalImage = "";
		}
	}

	public static String comparePixelsString (String strPixel, List<String> lstAllDigits, int intTall, int intPixelIndex, int intTallIncrement) {
		String strFinalPixel = "";
		if (strPixel.equals("2")) {
			String strTempPixel = lstAllDigits.get(intTall);

			String StringArray[] = strTempPixel.split("\\B");
			List<String> lstTemplayer = Arrays.asList(StringArray);
			strPixel = lstTemplayer.get(intPixelIndex);
			strFinalPixel = comparePixelsString(strPixel, lstAllDigits, intTall + intTallIncrement, intPixelIndex, intTallIncrement);
		}else {
			strFinalPixel = strPixel;
		}
		return strFinalPixel;
	}

	public static void executeProgram(int intWidth, int intTall) throws IOException{
		Scanner input = new Scanner(new File("Day8File.txt"));
		String strLine = input.nextLine();
		String StringArray[] = strLine.split("\\B");
		lstMasterInput = Arrays.asList(StringArray);
		input.close();
		lstAllDigits = decypherImageIntoList(intWidth, lstMasterInput, lstAllDigits);
		lstTotals = setListOfTotals(lstMasterInput,intWidth);
		mapLayer = createLayers(lstAllDigits, intTall, mapLayer);
		mapLayer = setLayerTotals(mapLayer, lstTotals,intWidth, intTall);
	}

	public static Day8 findFewest0s(Map<String,Day8> mapLayer) {
		Day8 objLayer  = mapLayer.get("1");
		for( String strLayer : mapLayer.keySet()) {
			Day8 objTemp = mapLayer.get(strLayer);
			if (objLayer.intTotal0 > objTemp.intTotal0) {
				objLayer = objTemp;
			}
		}

		return objLayer;
	}

	public static List<Integer> setListOfTotals (List<String> lstMasterInput, int intWide) {
		int intWidthCounter = 0;
		int int0s = 0;
		int int1s =0;
		int int2s = 0;
		List<Integer> lstTotals = new ArrayList<>();
		for(int i = 0; i < lstMasterInput.size(); i++) {
			if (intWidthCounter == intWide && intWide != 0) {
				lstTotals.add(int0s);
				lstTotals.add(int1s);
				lstTotals.add(int2s);
				intWidthCounter = int0s = int1s = int2s = 0;
			}
			if (lstMasterInput.get(i).equals("0")) {
				int0s++;
			}
			else if (lstMasterInput.get(i).equals("1")) {
				int1s++;
			}
			else if (lstMasterInput.get(i).equals("2")) {
				int2s++;
			}
			intWidthCounter++;
		}
		if (intWidthCounter > 0 ) {
			lstTotals.add(int0s);
			lstTotals.add(int1s);
			lstTotals.add(int2s);
		}
		return lstTotals;
	}

	public static List<String> decypherImageIntoList(int intWide, List<String> lstMasterInput, List<String> lstAllDigits) {
		String strDigit = "";
		for(int i = 0; i < lstMasterInput.size(); i++) {
			if (strDigit.length() < intWide) {
				strDigit = strDigit + lstMasterInput.get(i);
			}
			if (strDigit.length() >= intWide) {
				lstAllDigits.add(strDigit);
				strDigit = "";
			}
		}
		if (strDigit.length() >0) {
			lstAllDigits.add(strDigit);
		}

		return lstAllDigits;
	}

	public static Map<String, Day8> createLayers(List<String> lstAllDigits, int intTall, Map<String,Day8> mapLayer) {
		List<String> lstDigits = new ArrayList<>();
		int intLayerName = 1;
		for(int i = 0; i < lstAllDigits.size(); i++) {
			lstDigits.add(lstAllDigits.get(i));
			if (lstDigits.size() == intTall) {
				Day8 objLayer = new Day8(intLayerName);
				objLayer.lstDigits = new ArrayList<>(lstDigits);
				mapLayer.put(intLayerName + "", objLayer);
				intLayerName++;
				lstDigits.clear();
			}
		}
		if (lstDigits.size() > 0) {
			Day8 objLayer = new Day8(intLayerName);
			objLayer.lstDigits = new ArrayList<>(lstDigits);
			mapLayer.put(intLayerName + "", objLayer);
			lstDigits.clear();
		}

		return mapLayer;
	}

	public static Map<String,Day8> setLayerTotals(Map<String,Day8> mapLayer, List<Integer> lstTotals, int intWidth, int intTall) {
		int int0s = 0;
		int int1s = 0;
		int int2s = 0;
		List<Integer> lstTotals2 = new ArrayList<>(lstTotals);
		for(String s : mapLayer.keySet()) {
			Day8 objLayer  = mapLayer.get(s);
			for(int i = 0; i < intTall; i++) {
				if (lstTotals2.size() > 0) {
					int0s = int0s + lstTotals2.get(0);
					lstTotals2.remove(0);
				}
				if (lstTotals2.size() > 0) {
					int1s = int1s + lstTotals2.get(0);
					lstTotals2.remove(0);
				}
				if (lstTotals2.size() > 0) {
					int2s = int2s + lstTotals2.get(0);
					lstTotals2.remove(0);
				}
			}
			objLayer.intTotal0 = int0s;
			objLayer.intTotal1 = int1s;
			objLayer.intTotal2 = int2s;
			int0s = int1s = int2s = 0;
		}
		return mapLayer;
	}




}
