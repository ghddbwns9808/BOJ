import java.util.Scanner;

class Main{
    public static void main(String[] a){
        Scanner sc = new Scanner(System.in);
        int result = 0;
        for(int i=0; i<5; i++){
            int b= sc.nextInt();
            result += b*b;
        }
        System.out.println(result % 10);
        
    }
}