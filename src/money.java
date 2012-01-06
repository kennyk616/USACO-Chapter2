import java.io.*;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: money
*/
public class money {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long[] numWays = new long[N+1];
		numWays[0] = 1;
		String str;
		while((str = br.readLine())!=null){
			st = new StringTokenizer(str);
			while(st.hasMoreTokens()){
				int coin = Integer.parseInt(st.nextToken());
				for(int j = coin; j<=N; j++){
					numWays[j] += numWays[j-coin];
				}
			}
		}
		
		out.println(numWays[N]);
		out.close();
		System.exit(0);
	}

}
