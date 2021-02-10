
public class TestClass {
	public static void main(String[] args) {
		String strPassword = String.valueOf((688899));
		char[] arrayChar = strPassword.toCharArray();
		char charTemp = arrayChar[0];
		Boolean boolTest = false;
		int intCharDup = 1;
		for (int i = 1 ; i < arrayChar.length ; i ++) {
			if ( charTemp != arrayChar[i] ) {
				charTemp = arrayChar[i];
				
				if(intCharDup == 2) {
					boolTest = true;
				}
				intCharDup = 1;
			}else {
				intCharDup++;
			}
				
		}
		if(intCharDup == 2) {
			boolTest = true;
		}
		System.out.println("boolTest = " + boolTest);
	}
}
