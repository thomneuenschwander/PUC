
import java.util.Locale;
import java.util.Scanner;
import java.io.*;

class Q08 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		try {
			RandomAccessFile arq = new RandomAccessFile("C:\\Users\\Pichau\\eclipse-workspace\\JAVA_TP\\src\\Arquivos\\q7Arq1.txt", "rw");
			double num;
			for(int i = 0;i<n;i++) {
				num = sc.nextDouble();
				arq.writeDouble(num);
			}
			
			for(int i = n - 1; i >= 0; i--) {
				arq.seek(i*8);
				num = arq.readDouble();
				if(num == (int)num) {
					System.out.println((int)num);
				}else {
					String numFormatado = String.format("%.3f", num);
					System.out.println(numFormatado);
				}
			}
			arq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}

//try {
//	FileOutputStream fOutS = new FileOutputStream("C:\\Users\\Pichau\\eclipse-workspace\\JAVA_TP\\src\\Arquivos\\q7Arq1.txt");
//	DataOutputStream dataOutS= new DataOutputStream(fOutS);
//	
//	double num;
//	for(int i = 0; i < n; i++) {
//		num = sc.nextDouble();
//		dataOutS.writeDouble(num);
//	}
//	dataOutS.close(); //Fecha a leitura do arquivo
//	
//	FileInputStream  fInS = new FileInputStream("C:\\Users\\Pichau\\eclipse-workspace\\JAVA_TP\\src\\Arquivos\\q7Arq1.txt");
//	DataInputStream dataInS = new DataInputStream(fInS);
//	for(int i = 0; i < n; i++) {
//		long pos = (n - i - 1) * 8; // O vezes 8 é pq double é 8 bytes
//		dataInS.skip(pos);
//		num = dataInS.readDouble();
//		if(num == (int)num) {
//			System.out.println((int)num);
//		}else {
//			System.out.printf("%.15g\n", num);
//		}
//	}
//	dataInS.close();
//}catch(IOException e) {
//	e.printStackTrace();
//}

//ESSA FORMA NAO FAZ A ESCRITA BINÁRIA
//		Scanner sc = new Scanner(System.in);
//		
//		String arquivo = "C:\\Users\\Pichau\\eclipse-workspace\\JAVA_TP\\src\\Arquivos\\q7Arq1.txt";
//		
//		System.out.println("Digite N: ");
//		int n = sc.nextInt();
//		
//		Arq.openWrite(arquivo);
//		
//		for(int i = 0; i < n; i++) {
//			System.out.println("Digite um numero: ");
//			int numero = sc.nextInt();
//			Arq.println(numero);
//		}
//		Arq.close();
//		try {
//			RandomAccessFile file = new RandomAccessFile(arquivo, "r");
//			long arqTam = file.length();
//			for(long i = arqTam - 1; i >= 0; i--) {
//				file.seek(i);
//				System.out.println(file.readByte());
//			}
//			
//			file.close();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//		Arq.close();
//		sc.close();
//long fileSize = file.length();
//file.seek(fileSize - 1);
//
//for (long pointer = fileSize - 1; pointer >= 0; pointer--) {
//	file.seek(pointer);
//	char c = (char) file.readByte();
//	System.out.print(c);
//}
