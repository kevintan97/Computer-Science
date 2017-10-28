package ce152;
import java.util.Scanner;

public class Exercise3 {

		public static void main(String[] args){
			// exercise3a();
			exercise3b();
		}
	
		public static void exercise3a() {
			while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter an integer from 0 to 30 or -1 to exit");
			int n = input.nextInt();
				if (n<-1 || n>30) throw new IllegalArgumentException("Invalid input:" + n);
				else if (n == -1) break;
				else System.out.printf("The value of n'th Catalan number is " + "%1d", catalan(n));
				System.out.printf("%n");
				exercise3a();
				break;  
			}
		}
		
		public static void exercise3b() {
			while (true) {
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter an integer from 0 to 30 or -1 to exit");
			try {
			int n = input.nextInt();
				if (n<-1 || n>30)
					{ throw new IllegalArgumentException("Invalid input:" + n);
				}
				else if (n == -1) break;
				else System.out.printf("The value of n'th Catalan number is " + "%1d", catalan(n));
				System.out.printf("%n");
				exercise3b();
				break;  
			}
			catch(IllegalArgumentException e) {
				System.out.println("Integer is out of range. Please insert a valid integer.");
			}
		}
		}
		public static long catalan(int n) {
			if (n == 0) return 1;
			else return 2 * (2*n-1) * catalan(n-1) / (n+1);
		}

	
}