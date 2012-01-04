import java.io.*;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: holstein
*/
public class holstein {
	static int v;
	static int g;
	static int[] req;
	static int[][] scoops;
	static String minScoopOrder;
	static int minNumScoops = Integer.MAX_VALUE;

	static boolean enoughVit(int[] curAmount){
		for(int i = 0; i<v; i++){
			if(curAmount[i]<req[i]){
				return false;
			}
		}
		return true;
	}
	

	static void dfs(int[] curAmount, int numScoops, int scoop, StringBuffer scoopOrder){
		if(numScoops > minNumScoops)	return;
		
		if(enoughVit(curAmount)){
			if(numScoops<minNumScoops){
				scoopOrder.deleteCharAt(scoopOrder.length()-1);
				minScoopOrder = new String(scoopOrder);
				minNumScoops = numScoops;
			}
			return;
		}
		if(scoop == g)	return;
		
		scoopOrder.append(scoop+1).append(" ");
		for(int i = 0; i<v; i++){
			curAmount[i] += scoops[scoop][i];
		}
		for(int i = scoop+1; i<=g; i++){
			dfs(curAmount.clone(), numScoops+1, i, new StringBuffer(scoopOrder));
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		v = Integer.parseInt(br.readLine());
		req = new int[v];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<v; i++){
			req[i] = Integer.parseInt(st.nextToken());
		}
		g = Integer.parseInt(br.readLine());
		scoops = new int[g][v];
		for(int i = 0; i<g; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<v; j++){
				scoops[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		for(int i = 0; i<g; i++){
			dfs(new int[v], 0, i, new StringBuffer());
		}
	
		out.println(minNumScoops + " " + minScoopOrder);
		out.close();
		System.exit(0);
	}
}
