import java.io.*;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: hamming
*/
public class hamming {
	static int n, b, d, count; 
	static int []output;

	static boolean satisfyHamming(int num){
		String curStr;
		int xor;
		for(int i = 0; i<count; i++){
			xor = output[i] ^ num;
			curStr = Integer.toBinaryString(xor);
			int num1 = 0;
			for(int j = 0; j<curStr.length(); j++){
				if(curStr.charAt(j)=='1'){
					num1++;
				}
			}
			if(num1<d){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		output = new int[n];
		count = 1;
		int num = 1;
		while(count<n){
			if(satisfyHamming(num)){
				output[count] = num;
				count++;
			}
			num++;
		}
		
		for(int i = 1; i<=output.length; i++){
			out.print(output[i-1]);
			if(i%10==0 || i==output.length){
				out.println();
			}else{
				out.print(" ");
			}
		}
		out.close();
		System.exit(0);
	}
}
