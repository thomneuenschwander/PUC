import java.util.Scanner;
public class SequenciaEspelho {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 3;
		for(int q = 0; q < n; q++) {
			int esq = sc.nextInt();
			int dir = sc.nextInt();
			int start = esq;
			String strF = "";
			while(start <= dir) {
				strF += Integer.toString(start);
				start++;
			}
			int tam = strF.length();
			for(int i = 0; i < tam; i++) {
				char f = strF.charAt(i);
				System.out.printf("%c", f);
			}
			for(int i = tam-1; i >= 0; i--) {
				char f = strF.charAt(i);
				System.out.printf("%c", f);
			}
System.out.printf("\n");
		}
		sc.close();
	}

}
