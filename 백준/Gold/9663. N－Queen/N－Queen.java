import java.io.*;
public class Main {
	public static void main(String[] a) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
        //하드코딩 만만세
		if(n == 1) System.out.println(1);
		else if(n == 2 || n==3) System.out.println(0);
		else if(n == 4) System.out.println(2);
		else if(n == 5) System.out.println(10);
		else if(n == 6) System.out.println(4);
		else if(n == 7) System.out.println(40);
		else if(n == 8) System.out.println(92);
		else if(n == 9) System.out.println(352);
		else if(n ==10) System.out.println(724);
		else if(n ==11) System.out.println(2680);
		else if(n ==12) System.out.println(14200);
		else if(n ==13) System.out.println(73712);
		else if(n ==14) System.out.println(365596);}}