import java.util.Scanner;
class Q10 {

	public static boolean isPalindromoRecursico(String str, int left, int right) {
		if(left >= right/2) {
			return true; //quando para de verificar. Caso de parada
		}else if (str.charAt(left) != str.charAt(right)) {
			return false;
		}
		return isPalindromoRecursico(str, left + 1, right - 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		while(!Fim(str)) {
			str = sc.nextLine();
			if(!Fim(str)) {
				if(isPalindromoRecursico(str, 0, str.length() - 1)) {
					System.out.println("SIM");
				}else {
					System.out.println("NAO");
				}
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
