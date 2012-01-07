import java.io.*;
import java.util.Set;
/*
ID: kennyk61
LANG: JAVA
PROG: ttwo
*/
import java.util.TreeSet;

class state implements Comparable<state>{
	int[][] positions;
	state(int[][] positions){
		this.positions = new int[2][3];
		for(int i = 0; i<2; i++){
			System.arraycopy(positions[i], 0, this.positions[i], 0, 3);
		}
	}

	@Override
	public int compareTo(state s) {
		for(int i = 0; i<2; i++){
			for(int j = 0; j<3; j++){
				if(positions[i][j]!=s.positions[i][j]){
					return positions[i][j] - s.positions[i][j];
				}
			}
		}
		return 0;
	}
}


public class ttwo {
	static int n = 10;
	static char[][] forest = new char[n][n];
	static int[][] positions = new int[2][3];	//row: 0: F, 1: C; col: 0: i, 1: j, 2: dir
	static int di[] = {-1, 0, 1, 0};
	static int dj[] = {0, 1, 0, -1};
	static Set<state> states;
	
	static int move(){
		for(int t = 0; t<160000; t++){
			if(states.contains(new state(positions))){
				System.out.println("contain: t = " + t);
				return 0;
			}
			if(positions[0][0]==positions[1][0] && positions[0][1]==positions[1][1]){
				return t;
			}
			states.add(new state(positions));
			for(int k = 0; k<2; k++){
				int i = positions[k][0] + di[positions[k][2]];
				int j = positions[k][1] + dj[positions[k][2]];
				if(i<0 || i>=n || j<0 || j>=n || forest[i][j]=='*'){
					positions[k][2] = (positions[k][2] + 1)%4;					
				}else{
					positions[k][0] = i;
					positions[k][1] = j;					
				}
			}
		}	
		return 0;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		states = new TreeSet<state>();

		String line;
		for(int i = 0; i<n; i++){
			line = br.readLine();
			for(int j = 0; j<n; j++){
				char ch = line.charAt(j);
				if(ch!='*'){
					forest[i][j] = '.';
					if(ch=='F'){
						positions[0][0] = i;
						positions[0][1] = j;
					}else if(ch=='C'){
						positions[1][0] = i;
						positions[1][1] = j;
					}
				}else{
					forest[i][j] = ch;
				}
			}
		}
		out.println(move());
		out.close();
		System.exit(0);
	}
}
