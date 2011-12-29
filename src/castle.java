import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: castle
*/
class node{
	int i;
	int j;
	int component;
	HashSet<node> adjacent;
	
	node(int i, int j){
		this.i = i;
		this.j = j;
		adjacent = new HashSet<node>();
	}
	void setComponent(int c){
		component = c;
	}
	void addAdjacent(node n){
		adjacent.add(n);
	}
}

public class castle {
	static node[][] rooms;
	static ArrayList<HashSet<node>>components;
	static int m;
	static int n;
	static int breaki;
	static int breakj;
	static String dir;
	static int maxCombo;
	public static void processNode(int i, int j, int walls){
		if(walls>=8){			
			walls -=8;
		}else{
			rooms[i][j].addAdjacent(rooms[i+1][j]);
		}
		if(walls>=4){
			walls -= 4;
		}else{
			rooms[i][j].addAdjacent(rooms[i][j+1]);
		}
		if(walls >=2){
			walls -=2;
		}else{
			rooms[i][j].addAdjacent(rooms[i-1][j]);
		}
		if(walls==0){
			rooms[i][j].addAdjacent(rooms[i][j-1]);
		}		
	}
	
	private static void floodFill(int numComponents){
		HashSet<node> component = components.get(numComponents-1);
		int numVisited = 1;
		while(numVisited!=0){
			numVisited = 0;
			for(int i = 0; i<n; i++){
				for(int j = 0; j<m; j++){
					node curRoom = rooms[i][j];
					if(curRoom.component==-2){
						numVisited++;
						curRoom.component = numComponents;
						component.add(curRoom);
						for(node node : curRoom.adjacent){
							if(node.component==0)
								node.component = -2;
						}
					}
				}
			}
		}
	}
	
	public static void findComponents(int width, int height){
		int numComponents = 0;
		n = height;
		m = width;
		components = new ArrayList<HashSet<node>>();
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				if(rooms[i][j].component==0){
					numComponents++;
					rooms[i][j].component = -2;
					components.add(new HashSet<node>());
					floodFill(numComponents);
				}
			}
		}
	}
	
	private static boolean biggestCombo(int i1, int j1, int i2, int j2){
		int combo = 0;
		int comp1 = rooms[i1][j1].component;
		int comp2 = rooms[i2][j2].component;
		if(comp1==comp2)
			return false;
		
		combo = components.get(comp1-1).size() + components.get(comp2-1).size();
				
		if(combo > maxCombo){
			maxCombo = combo;
			return true;
		}else if(combo==maxCombo){
			if(j1<breakj || (j1==breakj && i1>breaki)){
				return true;
			}
		}
		return false;
	}
	
	public static void findBreak(){
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				if(i!=0){
					if(biggestCombo(i, j, i-1, j)){
						dir = "N";
						breaki = i;
						breakj = j;
					}
				}
				if(j!=m-1){
					if(biggestCombo(i, j, i, j+1)){
						dir = "E";
						breaki = i;
						breakj = j;
					}
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		rooms = new node[n][m];
		for(int i = 0; i<n; i++){
			for(int j = 0; j<m; j++){
				rooms[i][j] = new node(i, j);
			}
		}
		for(int i = 0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<m; j++){
				int walls = Integer.parseInt(st.nextToken());
				processNode(i, j, walls);
			}
		}
		findComponents(m, n);
		maxCombo = 0;
		findBreak();
		
		int maxRoom = 0;
		for(int i = 0; i<components.size(); i++){
			if(components.get(i).size()>maxRoom){
				maxRoom = components.get(i).size();
			}
		}
		out.println(components.size());
		out.println(maxRoom);
		out.println(maxCombo);
		out.println((breaki+1) + " " + (breakj+1) + " " + dir);
		out.close();
		System.exit(0);
	}
}
