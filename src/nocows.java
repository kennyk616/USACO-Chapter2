import java.io.*;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: nocows
*/


public class nocows {
	static int N;
	static int K;
	static int[][] combos;
	static int MOD = 9901;
	
	static void findCombos(){
		for(int j = 1; j<=K; j++){
			combos[1][j] = 1;
			for(int i = 1; i<=N; i+=2){
				for(int k = 1; k<=i-2; k++){
					combos[i][j] = (combos[i][j] + combos[k][j-1]*combos[i-1-k][j-1]) % MOD;
				}
			}
		}
		combos[N][K] = (combos[N][K] - combos[N][K-1] + MOD) % MOD;
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		combos = new int[N+1][K+1];
		
		findCombos();
		out.println(combos[N][K]);
		out.close();
		System.exit(0);
	}

}

