import java.io.*;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: concom
*/




public class concom {
	static int[][] ownership;
	static boolean[][] control;
	static int numCom = 100;

	static void addControl(int i, int j){
		if(control[i][j])	return;
		control[i][j] = true;
		
		for(int k = 1; k<=numCom; k++){
			ownership[i][k] += ownership[j][k];
			if(ownership[i][k] > 50){
				addControl(i, k);
			}
		}
		
		for(int k = 1; k<=numCom; k++){
			if(control[k][i]){
				addControl(k, j);
			}
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		int n = Integer.parseInt(br.readLine());
		ownership = new int[numCom+1][numCom+1];
		control = new boolean[numCom+1][numCom+1];
		StringTokenizer st;
		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int per = Integer.parseInt(st.nextToken());
			ownership[c1][c2] = per;
			for(int j = 1; j<=numCom; j++){
				if(control[j][c1]){
					ownership[j][c2] += per;
				}
			}
			for(int j = 1; j<=numCom; j++){
				if(ownership[j][c2] > 50){
					addControl(j, c2);
				}
			}
		}

		for(int i = 1; i<=numCom; i++){
			for(int j = 1; j<=numCom; j++){
				if(control[i][j] && i!=j)
					out.println(i + " " + j);
			}
		}
		
		out.close();
		System.exit(0);
	}

}
