import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day6 {
	
	private String strRight;
	private String strMiddle;
	private String strLeft;
	private String strName;
	private int intOrbit;
	
	public Day6 () {
		strRight = strMiddle = strLeft = strName = null;
		intOrbit = 0;
	}
	
	public static List<String> lstCourse = new ArrayList<>();
	public static Map<String,Day6> mapNameToNode = new HashMap<>();
	public static int intTotalOrbits = 0;
	public static int intCurrentOrbit = 0;
	public static String strParentSAN;
	public static List<String> lstSANOrbitals = new ArrayList<>();
	public static List<String> lstYOUOrbitals = new ArrayList<>();
	public static String strParentYOU;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("Day6File.txt"));
		while(input.hasNext()) {
			lstCourse.add(input.nextLine());
		}
		
		lstCourse = putCOMFirst(lstCourse);
		for (String s: lstCourse) {
			createNodes(s, mapNameToNode);
		}
		List<String> lstStr = Arrays.asList(lstCourse.get(0).split("\\)"));
		String strNode1Name = mapNameToNode.get(lstStr.get(0)).strName;
		
		connectTheNodes(mapNameToNode, strNode1Name);
		for(String s : mapNameToNode.keySet()) {
		Day6 Node = mapNameToNode.get(s);
		intTotalOrbits = intTotalOrbits + Node.intOrbit;
		}
		
		System.out.println("PART 1 intTotalOrbits = " + intTotalOrbits);
		strParentSAN = findNodeParent(mapNameToNode, "SAN");
		strParentYOU = findNodeParent(mapNameToNode, "YOU");
		
		lstSANOrbitals = getOrbitalPath(strParentSAN, mapNameToNode,lstSANOrbitals );
		lstYOUOrbitals = getOrbitalPath(strParentYOU, mapNameToNode,lstYOUOrbitals );
		
		System.out.println("TotalTransfer = " + findOrbitalTransfer(lstSANOrbitals, lstYOUOrbitals));
	}
	public static int findOrbitalTransfer (List<String> lstOrbital1, List<String> lstOrbital2) {
		int intTotalTransfer = 0;
		orbitalBreak:
		for( int i = 0 ; i < lstOrbital1.size(); i ++ ) {
			for (int j = 0 ; j < lstOrbital2.size() ; j++) {
				if (lstOrbital1.get(i).equals(lstOrbital2.get(j))) {
					intTotalTransfer = i + j;
					break orbitalBreak;
				}
			}
		}
		return intTotalTransfer;
	}
	public static List<String> getOrbitalPath(String strParent, Map<String,Day6> mapNameToNode, List<String> lstOrbitals) {
		lstOrbitals.add(strParent);
		while (strParent != null) {
			String strNodeParent = findNodeParent(mapNameToNode, strParent);
			
			if (strNodeParent != null) {
				lstOrbitals.add(strNodeParent);
			}
			strParent = strNodeParent;
		}
		return lstOrbitals;
	}
	
	public static String findNodeParent(Map<String,Day6> mapNameToNode, String strParentName) {
		for (String s :mapNameToNode.keySet()) {
			Day6 node = mapNameToNode.get(s);
			if ( (node.strLeft != null && node.strLeft.equals(strParentName)) 
					|| (node.strRight != null && node.strRight.equals(strParentName))
				|| (node.strMiddle != null && node.strMiddle.equals(strParentName))) {
				return node.strName;
			}
		}
		return null;
	}
	
	public static String findNodeYOU(Map<String,Day6> mapNameToNode) {
		for (String s :mapNameToNode.keySet()) {
			Day6 node = mapNameToNode.get(s);
			if ( (node.strLeft != null && node.strLeft.equals("YOU")) 
					|| (node.strRight != null && node.strRight.equals("YOU"))
				|| (node.strMiddle != null && node.strMiddle.equals("YOU"))) {
				return node.strName;
			}
		}
		return null;
	}
	
	public static List<String> putCOMFirst (List<String> lstObjects) {
		String strIndx0 = lstObjects.get(0);
		for(Integer i = 0 ; i < lstObjects.size() ; i++) {
			if (lstObjects.get(i).contains("COM")) {
				lstObjects.set(0, lstObjects.get(i));
				lstObjects.set(i, strIndx0);
				break;
			}
		}
		
		return lstObjects;
	}
	
	public static void createNodes (String strInput, Map<String,Day6> mapNameToNode) {
		List<String> lstStr = Arrays.asList(strInput.split("\\)"));
		Day6 objNode1 = getCreateNode(lstStr.get(0), mapNameToNode);
		Day6 objNode2 = getCreateNode(lstStr.get(1), mapNameToNode);
		if (objNode1.strRight == null) {
			objNode1.strRight = objNode2.strName;
		} else if (objNode1.strMiddle == null) {
			objNode1.strMiddle = objNode2.strName;
		} else if (objNode1.strLeft == null) {
			objNode1.strLeft = objNode2.strName;
		}
		
	}
	
	public static Day6 getCreateNode(String strNameNode, Map<String,Day6> mapNameToNode) {
		if (mapNameToNode.get(strNameNode) == null) {
			Day6 objNode = new Day6();
			objNode.strName = strNameNode;
			mapNameToNode.put(strNameNode, objNode);
			return objNode;
		}
		return mapNameToNode.get(strNameNode);
	}
	
	public static void connectTheNodes (Map<String, Day6> mapNameToNodes, String strNodeName) {
		
		Day6 objNode = mapNameToNodes.get(strNodeName);
		if (objNode.strRight != null) {
			Day6 objNode2 = mapNameToNodes.get(objNode.strRight);
			intCurrentOrbit = intCurrentOrbit + 1;
			objNode2.intOrbit = intCurrentOrbit;
			connectTheNodes(mapNameToNodes, objNode2.strName);
			
			
		}if (objNode.strMiddle != null) {
			intCurrentOrbit = mapNameToNodes.get(strNodeName).intOrbit;
			Day6 objNode2 = mapNameToNodes.get(objNode.strMiddle);
			intCurrentOrbit = intCurrentOrbit + 1;
			objNode2.intOrbit = intCurrentOrbit;
			connectTheNodes(mapNameToNodes, objNode2.strName);
			intCurrentOrbit = 0;
			
		}if (objNode.strLeft != null) {
			
			intCurrentOrbit = mapNameToNodes.get(strNodeName).intOrbit;
			Day6 objNode2 = mapNameToNodes.get(objNode.strLeft);
			intCurrentOrbit = intCurrentOrbit + 1;
			objNode2.intOrbit = intCurrentOrbit;
			connectTheNodes(mapNameToNodes, objNode2.strName);
			intCurrentOrbit = 0;
			
		}
	}
	
	
}
