import java.util.Scanner;

public class Q12 {
	public static final int KEY = 3;
	
	public static String cesar(String str, int esq, int dir, String novaStr) {
		char caracter = str.charAt(esq);
		if(caracter <= 122) {
			novaStr += ((char)(caracter+KEY));
		}else {
			novaStr += caracter;
		}
		if(esq >= dir) {
			return novaStr;
		}
		return cesar(str, esq+1,dir,novaStr);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		while(!fim(str)) {
			str = sc.nextLine();
			if(!fim(str)) {
				String novaStr = "";
				System.out.println(cesar(str, 0, str.length() - 1, novaStr));	
			}
		}
		sc.close();
	}
	public static boolean fim(String str) {
		char[] FIM = {'F', 'I', 'M'};
		if(str.length()!=3 || str == null || str == "") {
			return false;
		}else {
			for(int i = 0; i < str.length(); i++) {
				if(str.charAt(i) != FIM[i]) {
					return false;
				}
			}
		}
		return true;
	}

}
