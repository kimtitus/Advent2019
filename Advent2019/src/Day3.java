import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day3 {
	public static Map<String,String> mapDirectionCrosswalk = new HashMap<>();
	public static List<String> lstIntersection = new ArrayList<>();
	public static Map<String,Integer> mapIntersectionSteps = new HashMap<>();
	public static int intLowestStep;
	
	
	String[] lstDirection;
	List<String>lstLocation = new ArrayList<>();
	Map<String,Integer> mapLocationSteps = new HashMap<>();
	Map<String,String> mapWireLocationDirection = new HashMap<>();
	int intLeft = 0;
	int intRight = 0;
	int intUp = 0;
	int intDown = 0;
	int intDirectionCounter = 0;
	int intLstSize = 0;
	int intStepsCounter = 0;
	
	public int manhattanDistance() {
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		mapDirectionCrosswalk.put("U", "intUp");
		mapDirectionCrosswalk.put("D", "intDown");
		mapDirectionCrosswalk.put("R", "intRight");
		mapDirectionCrosswalk.put("L", "intLeft");
		
		Scanner input = new Scanner(new File("Day3File.txt"));
		
		Day3 wire1 = new Day3();
		Day3 wire2 = new Day3();
		
		wire1.lstDirection = input.nextLine().split(",");
		wire1.intLstSize = wire1.lstDirection.length;
		wire2.lstDirection = input.nextLine().split(",");
		wire2.intLstSize = wire2.lstDirection.length;	
		
		while(!isListFinished(wire1)) {
			wire1 = executeSingleDirection(wire1);
		}
		
		
		while(!isListFinished(wire2)) {
			wire2 = executeSingleDirection(wire2);
		}
		
		lstIntersection = getListOfIntersection(wire1,wire2);
		
		int intSmallestvalue = getSmalletsManhattanValue(lstIntersection);
		System.out.println("intSmallestvalue = " + intSmallestvalue);
		System.out.println("mapIntersectionSteps = " + mapIntersectionSteps);
	}
	
	public static Day3 executeSingleDirection(Day3 wire) {
		String strWireDirection = wire.lstDirection[wire.intDirectionCounter];
		String strDirection = mapDirectionCrosswalk.get(strWireDirection.charAt(0) + "");
		
		int intDirectionValue = Integer.valueOf(strWireDirection.substring(1,strWireDirection.length()));
		wire.intStepsCounter = wire.intStepsCounter + intDirectionValue;
		if (strDirection.equals("intUp")) {
			wire = addLocation(wire, strDirection, intDirectionValue);
			wire.intUp = wire.intUp + intDirectionValue;
		}else if (strDirection.equals("intRight")) {
			wire = addLocation(wire, strDirection, intDirectionValue);
			wire.intRight = wire.intRight + intDirectionValue;
		}else if (strDirection.equals("intDown")) {
			wire = addLocation(wire, strDirection, intDirectionValue);
			wire.intUp = wire.intUp - intDirectionValue;
		}else if (strDirection.equals("intLeft")) {
			wire = addLocation(wire, strDirection, intDirectionValue);
			wire.intRight = wire.intRight - intDirectionValue;
		}		
		
		
		wire.intDirectionCounter++;
		//
		return wire;
		
	}
	
	public static Day3 addLocation(Day3 wire, String strDirection, int intDirectionValue) {
		String strLocation = "";
		if (strDirection.equals("intRight")) {
			strLocation = wire.intRight + "," + (wire.intRight + intDirectionValue)  + " " + wire.intUp;
		}else if (strDirection.equals("intUp")) {
			//System.out.println("FOUND");
			strLocation = wire.intRight + " " + wire.intUp + "," + (wire.intUp + intDirectionValue);
			
		}else if (strDirection.equals("intLeft")) {
			strLocation = (wire.intRight - intDirectionValue) + "," + returnLargerValue(wire.intRight,-intDirectionValue) +  " " + wire.intUp;
		}else if (strDirection.equals("intDown")) {
			strLocation = wire.intRight + " " + ((wire.intUp - intDirectionValue)) + "," + returnLargerValue(wire.intUp, -intDirectionValue);
		}
		wire.mapLocationSteps.put(strLocation, wire.intStepsCounter);;
		wire.lstLocation.add(strLocation);
		wire.mapWireLocationDirection.put(strLocation, strDirection);
		return wire;
	}
	
	public static boolean isListFinished(Day3 wire) {
		if(wire.intDirectionCounter == wire.intLstSize) {
			return true;
		}
		return false;
	}
	
	public static List<String> getListOfIntersection(Day3 wire1, Day3 wire2){
		List<String> lstIntersection = new ArrayList<>();
		for (int i = 0 ; i < wire1.lstLocation.size() ; i++) {
			for (int j = 0 ; j < wire2.lstLocation.size() ; j++) {
				String[] singleLocationSplit = wire1.lstLocation.get(i).split(" ");
				List<String> lstSingleWire1Location = new ArrayList<String>();
				lstSingleWire1Location = Arrays.asList(singleLocationSplit);
				
				singleLocationSplit = wire2.lstLocation.get(j).split(" ");
				List<String> lstSingleWire2Location = new ArrayList<String>();
				lstSingleWire2Location = Arrays.asList(singleLocationSplit);
				
				if ((!lstSingleWire1Location.get(0).contains(",") && lstSingleWire2Location.get(0).contains(",")) || (lstSingleWire1Location.get(0).contains(",") && !lstSingleWire2Location.get(0).contains(","))) {
					if(lstSingleWire1Location.get(0).contains(",")) {
						String[] wire1XCoordSplit = lstSingleWire1Location.get(0).split(",");
						String[] wire2YCoordSplit = lstSingleWire2Location.get(1).split(",");
						if (Integer.valueOf(lstSingleWire2Location.get(0)) >= Integer.valueOf(wire1XCoordSplit[0]) && Integer.valueOf(lstSingleWire2Location.get(0)) <= Integer.valueOf(wire1XCoordSplit[1])) {
							
							if (Integer.valueOf(lstSingleWire1Location.get(1)) >= Integer.valueOf(wire2YCoordSplit[0]) && Integer.valueOf(lstSingleWire1Location.get(1)) <= Integer.valueOf(wire2YCoordSplit[1])) {
							
								int intWire1Step = getIntersectionStep(wire1.mapLocationSteps.get(wire1.lstLocation.get(i)), wire1.mapWireLocationDirection.get(wire1.lstLocation.get(i)), lstSingleWire2Location, wire1XCoordSplit, wire2YCoordSplit);
								int intWire2Step = getIntersectionStep(wire2.mapLocationSteps.get(wire2.lstLocation.get(j)), wire2.mapWireLocationDirection.get(wire2.lstLocation.get(j)), lstSingleWire1Location, wire1XCoordSplit, wire2YCoordSplit);
								
								int intTotalStep = intWire1Step + intWire2Step;
								mapIntersectionSteps.put(lstSingleWire2Location.get(0) + " " +lstSingleWire1Location.get(1), intTotalStep);
								lstIntersection.add(lstSingleWire2Location.get(0) + " " +lstSingleWire1Location.get(1));
							}
						}
					}else {
						String[] wire2XCoordSplit = lstSingleWire2Location.get(0).split(",");
						String[] wire1YCoordSplit = lstSingleWire1Location.get(1).split(",");
						
						if (Integer.valueOf(lstSingleWire1Location.get(0)) >= Integer.valueOf(wire2XCoordSplit[0]) && Integer.valueOf(lstSingleWire1Location.get(0)) <= Integer.valueOf(wire2XCoordSplit[1])) {
							if (Integer.valueOf(lstSingleWire2Location.get(1)) >= Integer.valueOf(wire1YCoordSplit[0]) && Integer.valueOf(lstSingleWire2Location.get(1)) <= Integer.valueOf(wire1YCoordSplit[1])) {
							
								int intWire1Step = getIntersectionStep(wire1.mapLocationSteps.get(wire1.lstLocation.get(i)), wire1.mapWireLocationDirection.get(wire1.lstLocation.get(i)), lstSingleWire2Location, wire2XCoordSplit, wire1YCoordSplit);
								int intWire2Step = getIntersectionStep(wire2.mapLocationSteps.get(wire2.lstLocation.get(j)), wire2.mapWireLocationDirection.get(wire2.lstLocation.get(j)), lstSingleWire1Location, wire2XCoordSplit, wire1YCoordSplit);
							
								int intTotalStep = intWire1Step + intWire2Step;
								mapIntersectionSteps.put(lstSingleWire1Location.get(0) + " " +lstSingleWire2Location.get(1), intTotalStep);
								lstIntersection.add(lstSingleWire1Location.get(0) + " " +lstSingleWire2Location.get(1));
							}
						}
					}
				}
				
			}
		}
		return lstIntersection;
	}
	
	public static int returnLargerValue(int intOne, int intTwo) {
		
		return (Integer.compare(intOne, intTwo) == -1 ? intTwo : intOne); 
	}
	
	public static int getSmalletsManhattanValue(List<String> lstStr) {
		int intSmallValue = 0;
		int intTempValue;
		//lstStr.remove(0);
		for (int i = 0 ; i < lstStr.size() ; i ++) {
			String[] lstXYCoord = lstStr.get(i).split(" ");
			intTempValue = Math.abs(Integer.valueOf(lstXYCoord[0])) + Math.abs(Integer.valueOf(lstXYCoord[1]));
			if (i == 0) {
				intSmallValue = intTempValue;
			}else {
				intSmallValue = intSmallValue > intTempValue ? intTempValue : intSmallValue;
			}
		}
		
		return intSmallValue;
	}
	
	public static int getIntersectionStep(int intLocationStep, String strDirection, List<String> lstSingleWireLocation, String[] wireXCoordinate, String[] wireYCoordinate) {
		int intFinalStep = 0 ;
		if (strDirection.equals("intUp")) {
			intFinalStep = intLocationStep - Math.abs(Math.abs(Integer.valueOf(lstSingleWireLocation.get(1))) - Math.abs(Integer.valueOf(wireYCoordinate[1])));
		
		}else if (strDirection.equals("intDown")) {
			intFinalStep = intLocationStep - Math.abs(Math.abs(Integer.valueOf(lstSingleWireLocation.get(1))) - Math.abs(Integer.valueOf(wireYCoordinate[0])));
			
		}else if (strDirection.equals("intRight")){
			intFinalStep = intLocationStep - Math.abs(Math.abs(Integer.valueOf(lstSingleWireLocation.get(0))) - Math.abs(Integer.valueOf(wireXCoordinate[1])));
			
		}else if (strDirection.equals("intLeft")){
			intFinalStep = intLocationStep - Math.abs(Math.abs(Integer.valueOf(lstSingleWireLocation.get(0))) - Math.abs(Integer.valueOf(wireXCoordinate[0])));
			
		}
		return intFinalStep;
	}
	
	
}


/*
Create 2 direction List
Finished when end of list is reached
Execute direction input;
Capture location after each direction execution
compare the location of each
Compute Manhattan distance
*/