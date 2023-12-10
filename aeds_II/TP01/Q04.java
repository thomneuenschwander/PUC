import java.util.Random;
import java.util.Scanner;

public class Q04 {
	
	public static String alteracaoAleatoria(String string, char l1, char l2) {
		char[] vetStr = string.toCharArray();
		for(int i = 0; i < vetStr.length; i++) {
			if(vetStr[i] == l1) {
				vetStr[i] = l2;
			}
		}
		String str = new String(vetStr);
		return str;
	}

	public static void main(String[] args) {
		Random gerador = new Random();
		Scanner sc = new Scanner(System.in);
		gerador.setSeed(4);
		String str = "";
		while(!Fim(str)) {
			str = sc.nextLine();
			if(!Fim(str)) {
				char p1  = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
				char p2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
				System.out.println(alteracaoAleatoria(str, p1, p2));
			}
		}
		sc.close();
	}
	
	public static boolean Fim(String str) {
        if (str.length() != 3)
            return false;

        char[] fim = {'F', 'I', 'M'};

        for (int i = 0; i < 3; i++) {
            if (str.charAt(i) != fim[i]) {
                return false;
            }
        }

        return true;
    }

}
