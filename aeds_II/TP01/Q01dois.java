import java.util.Scanner;
public class Q01dois {

	
	public static boolean isPalindromo(String str) {
		String string = str;
//		String string = str.replaceAll(" ", "");
//		string = string.replaceAll("-", "");
		int j = string.length() - 1;
		for(int i = 0; i < string.length()/2;i++) {
			if(string.charAt(i) != string.charAt(j)) {
				return false;
			}
			j--;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		while(!isFim(str)) {
			 str = sc.nextLine();
				if(!isFim(str)) {
					if(isPalindromo(str)) {
						System.out.println("SIM");
					}else {
						System.out.println("NAO");
					}
				}
		}
		
		sc.close();
	}
	
	
	public static boolean isFim(String str) {
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
