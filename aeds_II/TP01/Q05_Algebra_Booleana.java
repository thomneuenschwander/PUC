
import java.util.InputMismatchException;
import java.util.Scanner;

public class Q05_Algebra_Booleana {
    

    public static boolean CMPString(String str1, String str2){

        boolean resp = true;

        if(str1.length() == str2.length()){
            for(int i = 0; i < str1.length(); i++){
                if(str1.charAt(i) != str2.charAt(i)){
                    resp = false;
                    i = str1.length();
                }
            }
            return resp;
        }
        else
        {
            resp = false;
            return resp;
        }
    }

    public static int ASCIIToInt(int value) {
        int resp = value - 48;
        return resp;
    }

    public static String replaceStr(String str, String regex, String replacement) {
        str = str.replaceAll(regex, replacement);
        return str;
    }

    public static int findFirstParentesis(String str){
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ')'){
                return i;
            }
        }
        return str.length();
    }

    public static int NOT(String str){
        int op1 = ASCIIToInt(str.charAt(4));

        return (op1 == 0) ? 1 : 0;
    }

    public static int findClosingParenthesis(String expression) {
        int openCount = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                openCount--;
                if (openCount == 0) {
                    return i;
                }
            }
        }
        return expression.length();
    }

    public static int commaCount(String str){
        int cont = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ','){
                cont++;
            }
        }
        return cont;
    }
    
    public static int countOpeningParenthesis(String str){
        int cont = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                cont++;
            }
        }
        return cont;
    }

    public static int avaliarSubStr(String str) {

        if(str.startsWith("and(") && str.length() == 10){
            int op1 = Integer.parseInt(str.substring(4,5));
            int op2 = Integer.parseInt(str.substring(6,7));
            int op3 = Integer.parseInt(str.substring(8,9));
            return (op1 == 1 && op2 == 1 && op3 == 1) ? 1 : 0;
        }

        if(str.startsWith("or(") && str.length() == 10){
            int op1 = Integer.parseInt(str.substring(3,4));
            int op2 = Integer.parseInt(str.substring(5,6));
            int op3 = Integer.parseInt(str.substring(7,8));
            return (op1 == 0 && op2 == 0 && op3 == 0) ? 0 : 1;
        }

        int openParenCount = 0;
        int commaIndex = -1;
        int size;

        if(str.startsWith("or(")){
            size = 3;
        } else {
            size = 4;
        }
        
        for (int i = size; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                openParenCount++;
            } else if (c == ')') {
                openParenCount--;
            } else if (c == ',' && openParenCount == 0) {
                commaIndex = i;
                break;
            }
        }

        if(commaIndex != -1){
            if(commaCount(str) > 1 || countOpeningParenthesis(str) > 1){
                if(str.startsWith("and(")){
                    String subStr1 = str.substring(4, commaIndex);
                    String subStr2 = str.substring(commaIndex+1, str.length()-1);
                    int op1 = avaliarSubStr(subStr1);
                    int op2 = avaliarSubStr(subStr2);
                    return (op1 == 1 && op2 == 1) ? 1 : 0;
                } else if(str.startsWith("not(")){
                    int indexFechamento = findClosingParenthesis(str);
                    int value = avaliarSubStr(str.substring(4, indexFechamento));
                    return (value == 1) ? 0 : 1;
                } else if(str.startsWith("or(")){
                    String subStr1 = str.substring(3, commaIndex);
                    String subStr2 = str.substring(commaIndex+1, str.length()-1);
                    int op1 = avaliarSubStr(subStr1);
                    int op2 = avaliarSubStr(subStr2);
                    return (op1 == 0 && op2 == 0) ? 0 : 1;
                } else {
                    int valor = Integer.parseInt(str);
                    return valor;
                }
            } else {
                if(str.startsWith("and(")){
                    int op1 = Integer.parseInt(str.substring(4,5));
                    int op2 = Integer.parseInt(str.substring(6,7));
                    return (op1 == 1 && op2 == 1) ? 1 : 0;
                } else if(str.startsWith("not(")){
                    int indexFechamento = findClosingParenthesis(str);
                    int value = avaliarSubStr(str.substring(4, indexFechamento));
                    return (value == 1) ? 0 : 1;
                } else if(str.startsWith("or(")){
                    int op1 = Integer.parseInt(str.substring(3,4));
                    int op2 = Integer.parseInt(str.substring(5,6));
                    return (op1 == 1 && op2 == 1 || op1 == 1 && op2 == 0 || op1 == 0 && op2 == 1) ? 1 : 0;
                } else {
                    int valor = Integer.parseInt(str);
                    return valor;
                }
            }
        } else if(str.startsWith("not(")){
            int indexFechamento = findClosingParenthesis(str);
            int op = avaliarSubStr(str.substring(4, indexFechamento));
            return (op == 0) ? 1 : 0;
        } else {
            int valor = Integer.parseInt(str);
            return valor;
        }
    }

    public static void BooleanLogic(String str) {
        int size = ASCIIToInt((int) str.charAt(0));
        int[] booleans = new int[size];

        int x = 0;
        for (int i = 0; i < size + x; i += 2) {
            booleans[x] = ASCIIToInt((int) str.charAt(i + 2));
            x++;
        }

        int start = x * 2 + 1;
        str = str.substring(start, str.length());

        for (int i = 0; i < size; i++) {
            int asciIndex = 65 + i;
            char aux1 = (char) asciIndex;
            String aux2 = Character.toString(aux1), aux3 = Integer.toString(booleans[i]);
            str = replaceStr(str, aux2, aux3);
        }

        str = replaceStr(str, "\\s", "");

        System.out.println(avaliarSubStr(str));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        String s = "0";
        while(x == 0){
            try {
                String str = sc.nextLine();
                if(CMPString(str, s)){
                    break;
                }
                BooleanLogic(str);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, forneça uma entrada válida.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado. Continuando para a próxima entrada.");
            }
        }
        sc.close();
    }
}