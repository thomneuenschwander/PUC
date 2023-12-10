
import java.util.Scanner;
class Q01 {

	
	public static boolean isPalindromo(String string) {
		//string = string.replaceAll(" ", "");
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
		for(;;) {
			String str = sc.nextLine();
			if(str.equals("FIM")) {
				break;
			}
			if(isPalindromo(str)) {
				System.out.println("SIM");
			}else {
				System.out.println("NAO");
			}
			
		}
		
	}

}
