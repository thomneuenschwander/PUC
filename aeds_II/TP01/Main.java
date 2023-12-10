package TP01;
import java.util.Scanner;
class Main {

	
	public static boolean isPalindromo(String str) {
		String string = str.replaceAll(" ", " ");
		int j = string.length() - 1;
		for(int i = 0; i < string.length();i++) {
			if(string.charAt(i) != string.charAt(j)) {
				return false;
			}
			j--;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = null;
		while(str != "FIM") {
			str = sc.nextLine();
			if(isPalindromo(str)) {
				System.out.println("SIM");
			}else {
				System.out.println("NAO");
			}
		}
		sc.close();
	}

}
