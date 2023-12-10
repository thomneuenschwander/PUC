import java.util.Scanner;

class Q06 {
	
	public static boolean isOnlyConsoante(String string) {
		String str = string.toLowerCase();
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i'|| str.charAt(i) == 'o'|| str.charAt(i) == 'u') {
				return false;
			}
			if(str.charAt(i) == '0' || str.charAt(i) == '1'|| str.charAt(i) == '2'|| str.charAt(i) == '3'|| str.charAt(i) == '4'|| str.charAt(i) == '5'
					|| str.charAt(i) == '6'|| str.charAt(i) == '7'|| str.charAt(i) == '8'|| str.charAt(i) == '9') {
				return false;
			}
				
		}
		return true;
	}
	
	public static boolean isOnlyVogal(String string) {
		String str = string.toLowerCase();
		int count = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u') {
				count++;
			}
		}
		if(count == str.length()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean doesCorrespondsIntegrer(String string) {
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) != '0' && string.charAt(i) != '1' && string.charAt(i) != '2' && string.charAt(i) != '3' && string.charAt(i) != '4'
					&& string.charAt(i) != '5' && string.charAt(i) != '6' && string.charAt(i) != '7' && string.charAt(i) != '8' && string.charAt(i) != '9') {
				return false;
			}
		}
		
		return true;
	}
	public static boolean doesCorrespondsReal(String string) {
		//Contar quantas virgulas ou pontos tem
		int n = 0;
		int tamNum = 0;
		for(int i = 0; i < string.length(); i++) {
			if(string.charAt(i) == '.' || string.charAt(i) == ',') {
				n++;
			}
			if(string.charAt(i) == '.' || string.charAt(i) == ',' || string.charAt(i) == '0'|| string.charAt(i) == '1'|| string.charAt(i) == '2'|| string.charAt(i) == '3'|| string.charAt(i) == '4'
					|| string.charAt(i) == '5'|| string.charAt(i) == '6'|| string.charAt(i) == '7'|| string.charAt(i) == '8'|| string.charAt(i) == '9') {
				tamNum++;
			}
		}
		if(tamNum == string.length() && n == 1) {
			return true;
		}else {	
			return false;
		}
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
			
			if(isOnlyVogal(string)) {
				x1 = "SIM";
			}else {
				x1 = "NAO";
			}
			
			if(isOnlyConsoante(string)) {
				x2 = "SIM";
			}else {
				x2 = "NAO";
			}
			
			if(doesCorrespondsIntegrer(string)) {
				x3 = "SIM";
			}else {
				x3 = "NAO";
			}
			
			if(doesCorrespondsReal(string)) {
				x4 = "SIM";
			}else {
				x4 = "NAO";
			}
			System.out.println(x1 +" "+ x2 +" "+ x3 +" "+ x4);
		}
		sc.close();
	}
}
