import java.io.*;
import java.util.Arrays;
/*
ID: kennyk61
LANG: JAVA
PROG: subset
*/
public class subset {
	static long[][] combos;
	
	static long numWays(int n, int sum){
		if(n<0 || sum<0)	return 0;
		if(combos[n][sum]!=-1)	return combos[n][sum];
		combos[n][sum] = numWays(n-1, sum) + numWays(n-1, sum-n);
		return combos[n][sum];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		int n = Integer.parseInt(br.readLine());
		int sum = (1+n)*n/2;
		if(sum%2==1){
			out.println("0");
		}else{
			int subSetSum = sum/2;
			combos = new long[n+1][subSetSum+1];
			for(int i = 0; i<combos.length; i++){
				Arrays.fill(combos[i], -1);
			}
			combos[0][0] = 1;
			out.println(numWays(n, subSetSum)/2);
		}
		out.close();
		System.exit(0);
	}

}
