package TP01;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

class LeituraDePaginaHTML {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name = "";
		String StrUrl = "";
		while(!name.equals("FIM")) {
			name = sc.nextLine();
			StrUrl = sc.nextLine();
			
			//inicia os contadores
			int a = 0, e = 0, i = 0, o = 0, u = 0; //a
			int aa = 0, ee = 0, ii = 0, oo = 0, uu = 0; //á
			int aaa = 0, eee = 0, iii = 0, ooo = 0, uuu = 0;//à
			int aaaa = 0, oooo = 0; //ã
			int aaaaa = 0, eeeee = 0, iiiii = 0, ooooo = 0, uuuuu = 0; //â
			int consoantes = 0;
			int brTag = 0;
			int tableTag = 0;
			
			try {
				URL url = new URL(StrUrl);
				//abrindo conexao com a URL
				URLConnection connection = url.openConnection();
				//Criando leitor para ler o html
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				//le a primeira linha
				String line = br.readLine();
				//enquanto o arquivo nao chegar ao fim
				while(line != null) {
					if(line.contains("<table")) {
						tableTag++;
					}
					if(line.contains("<br")) {
						brTag++;
					}
					
					for(int c = 0; c < line.length(); c++) {
						if(line.charAt(c) == 'a') {
							a++;
						}else if (line.charAt(c) == 'e') {
							e++;
						}else if (line.charAt(c) == 'i') {
							i++;
						}else if (line.charAt(c) == 'o') {
							o++;
						}else if (line.charAt(c) == 'u') {
							u++;
						}
						else if (line.charAt(c) == 'á') {
							aa++;
						}else if (line.charAt(c) == 'é') {
							ee++;
						}else if (line.charAt(c) == 'í') {
							ii++;
						}else if (line.charAt(c) == 'ó') {
							oo++;
						}else if (line.charAt(c) == 'ú') {
							uu++;
						}
						else if (line.charAt(c) == 'à') {
							aaa++;
						}else if (line.charAt(c) == 'è') {
							eee++;
						}else if (line.charAt(c) == 'ì') {
							iii++;
						}else if (line.charAt(c) == 'ò') {
							ooo++;
						}else if (line.charAt(c) == 'ù') {
							uuu++;
						}
						else if (line.charAt(c) == 'ã') {
							aaaa++;
						}else if (line.charAt(c) == 'õ') {
							oooo++;
						}
						else if (line.charAt(c) == 'â') {
							aaaaa++;
						}else if (line.charAt(c) == 'ê') {
							eeeee++;
						}else if (line.charAt(c) == 'î') {
							iiiii++;
						}else if (line.charAt(i) == 'ô') {
							ooooo++;
						}else if (line.charAt(c) == 'û') {
							uuuuu++;
						}
						else if (line.charAt(c) == 'b'||line.charAt(c) == 'c'||line.charAt(c) == 'd'||line.charAt(c) == 'f'||line.charAt(c) == 'g'||line.charAt(c) == 'h'
								||line.charAt(c) == 'j'||line.charAt(c) == 'k'||line.charAt(c) == 'l'||line.charAt(c) == 'm'||line.charAt(c) == 'n'||line.charAt(c) == 'p'
								||line.charAt(c) == 'q'||line.charAt(c) == 'r'||line.charAt(c) == 's'||line.charAt(c) == 't'||line.charAt(c) == 'v'||line.charAt(c) == 'w'
								||line.charAt(c) == 'x'||line.charAt(c) == 'y'||line.charAt(c) == 'z') {
							consoantes++;
						}
					}
					
					//passa para a proxima linha
					line = br.readLine();
					System.out.println(a);
				}
				
			}catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		sc.close();
	}

}
