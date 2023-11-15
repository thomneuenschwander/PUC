package warUp;
import java.util.Scanner;

public class FilaDoRecreioWarmUp02 {
    
    public static int HowManySwaps(int[] arr, int tam){
        int resp = 0;
        for(int i = 0; i < tam-1; i++){
            for(int j = (i+1); j < tam; j++){
                if(arr[i] < arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    resp += 2;
                }
            }
        }
        resp = tam - resp;
        return resp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int cases = 0; cases < n; cases++){
            int m = sc.nextInt();
            int[] fila = new int[m];
            for(int i = 0; i < m; i++){
                fila[i] = sc.nextInt();
            }
            int resp = HowManySwaps(fila, m);
            System.out.println(resp);
        }
        

        sc.close();
    }
}