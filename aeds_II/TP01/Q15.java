import java.util.Scanner;

public class Q15 {
	public static boolean isOnlyConsoante(String str, int esq, int dir) {
		str = str.toLowerCase();
		if(str.charAt(esq) == 'a' || str.charAt(esq) == 'e' || str.charAt(esq) == 'i'|| str.charAt(esq) == 'o'|| str.charAt(esq) == 'u' ||
				str.charAt(esq) == '0' || str.charAt(esq) == '1'|| str.charAt(esq) == '2'|| str.charAt(esq) == '3'|| str.charAt(esq) == '4'|| str.charAt(esq) == '5'
				|| str.charAt(esq) == '6'|| str.charAt(esq) == '7'|| str.charAt(esq) == '8'|| str.charAt(esq) == '9') {
			return false;
		}else if(esq >= dir) {
			return true;
		}else {
			return isOnlyConsoante(str, esq+1, dir-1);
		}
	}
	
	public static boolean isOnlyVogal(String str, int esq, int dir, int count) {
		str = str.toLowerCase();
		if(str.charAt(esq) == 'a' || str.charAt(esq) == 'e' || str.charAt(esq) == 'i' || str.charAt(esq) == 'o' || str.charAt(esq) == 'u') {
			count++;
		}
		if(count >= str.length() && esq >= dir) {
			return true;
		}else if(count < str.length() && esq >= dir) {
			return false;
		}else {
			return isOnlyVogal(str,esq+1,dir,count);
		}
	}
	
	public static boolean doesCorrespondsIntegrer(String str, int esq, int dir) {
		if(esq >= dir) {
			return true;
		}else if(str.charAt(esq) != '0' && str.charAt(esq) != '1' && str.charAt(esq) != '2' && str.charAt(esq) != '3' && str.charAt(esq) != '4'
				&& str.charAt(esq) != '5' && str.charAt(esq) != '6' && str.charAt(esq) != '7' && str.charAt(esq) != '8' && str.charAt(esq) != '9') {
			return false;
		}else {
			return doesCorrespondsIntegrer(str, esq+1, dir);
		}
	}
//	public static boolean doesCorrespondsReal(String str, int esq, int dir, int countComma, int countTamNum) {
//		if(str.charAt(esq) == '.' || str.charAt(esq) == ',') {
//			countComma++;
//		}
//		if(str.charAt(esq) == '.' || str.charAt(esq) == ',' || str.charAt(esq) == '0'|| str.charAt(esq) == '1'|| str.charAt(esq) == '2'|| str.charAt(esq) == '3'|| str.charAt(esq) == '4'
//				|| str.charAt(esq) == '5'|| str.charAt(esq) == '6'|| str.charAt(esq) == '7'|| str.charAt(esq) == '8'|| str.charAt(esq) == '9') {
//			countTamNum++;
//		}
//		if(countTamNum == dir+1 && countComma == 1 && esq >= dir) {
//			return true;
//		}else if(countTamNum < str.length() && countComma != 1 && esq >= dir) {
//			return false;
//		}else {
//			return doesCorrespondsReal(str, esq+1,dir, countComma, countTamNum);
//		}
//	}
	public static boolean doesCorrespondsReal(String str, int esq, int dir, int countComma, int countTamNum) {
		if(esq >= dir) {
			return countComma == 1 && countTamNum >= 1;
		}
		char ch = str.charAt(esq);
		if(ch == '.' || ch == ',') {
			countComma++;
		}else if(Character.isDigit(ch)) {
			countTamNum++;
		}else if(!Character.isWhitespace(ch)) {
			return false;
		}
		return doesCorrespondsReal(str, esq+1, dir, countComma, countTamNum);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String string = "";
		while(!string.equals("FIM")) {
			string = sc.nextLine();
			
			String x1 = "";
			String x2 = "";
			String x3 = "";
			String x4 = "";
			
			if(isOnlyVogal(string, 0 , string.length()-1, 0)) {
				x1 = "SIM";
			}else {
				x1 = "NAO";
			}
			
			if(isOnlyConsoante(string,0,string.length()-1)) {
				x2 = "SIM";
			}else {
				x2 = "NAO";
			}
			
			if(doesCorrespondsIntegrer(string,0,string.length()-1)) {
				x3 = "SIM";
			}else {
				x3 = "NAO";
			}
			
			if(doesCorrespondsReal(string,0,string.length()-1, 0, 0)) {
				x4 = "SIM";
			}else {
				x4 = "NAO";
			}
			System.out.println(x1 +" "+ x2 +" "+ x3 +" "+ x4);
		}
		sc.close();
	}
}
