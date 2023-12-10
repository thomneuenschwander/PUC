import java.util.Scanner;

class Q03 {

	public static String cesar(String str) {
		char[] arrString = new char [str.length()];
		
		for(int i = 0; i < str.length(); i++) {
			arrString[i] = alterarCaracter(str.charAt(i));
		}
		
		return new String (arrString);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = null;
		while(rodar(str)) {
			str = sc.nextLine();
			if(rodar(str)) {
				System.out.println(cesar(str));
			}
		}	
		sc.close();
	}
	public static char alterarCaracter(char c) {
		if(c <= 122) {
			return (char) (c + 3);
		}else {
			return c;
		}
	}
	public static boolean rodar(String str) {
		if(str == null || str == "") {
			return true;
		}
		if(str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') {
			return false;
		}else {
			return true;
		}
	}

}
