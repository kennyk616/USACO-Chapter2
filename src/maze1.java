import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: maze1
*/

class room{
	boolean []visited;
	int []distance;
	int i, j;
	room parent = null;
	boolean[] adj;
	room(int i, int j){
		this.i = i;
		this.j = j;
		adj = new boolean[4];
		visited = new boolean[2];
		distance = new int[2];
		Arrays.fill(distance, Integer.MAX_VALUE);
	}
}



public class maze1 {
	static char[][] mazetxt;
	static room[][] maze;
	static int w, h;
	static int di[] = {-1, 0, 1, 0};
	static int dj[] = {0, 1, 0, -1};
	
	static int exits[][];
	
	static void findShortestPath(int exit){
		int exiti = exits[exit][0];
		int exitj = exits[exit][1];	
		maze[exiti][exitj].distance[exit] = 1;
		int nodesVisited = 0;
		int mazeSize = w*h;
		while(nodesVisited < mazeSize){
			int minD = Integer.MAX_VALUE;
			int curi = exiti;
			int curj = exitj;
			for(int i = 0; i<h; i++){
				for(int j = 0; j<w; j++){
					if(!maze[i][j].visited[exit] && 
							maze[i][j].distance[exit]<minD){
						minD = maze[i][j].distance[exit];
						curi = i;
						curj = j;
					}
				}
			}
			room cur = maze[curi][curj];
			cur.visited[exit] = true;
			nodesVisited++;
			
			for(int d = 0; d<4; d++){
				if(cur.adj[d]){
					room adj = maze[curi+di[d]][curj+dj[d]];
					if(cur.distance[exit]+1<adj.distance[exit]){
						adj.distance[exit] = cur.distance[exit] + 1;
						adj.parent = cur;
					}
				}
			}
		}
	}
	
	static int getLongestDistance(){
		int longestD = 0;
		for(int i = 0; i<h; i++){
			for(int j = 0; j<w; j++){
				int d = Math.min(maze[i][j].distance[0], 
						maze[i][j].distance[1]);
				if(d>longestD){
					longestD = d;
				}
				
			}
		}
		return longestD;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		mazetxt = new char[2*h+1][2*w+1];
		maze = new room[h][w];
		exits = new int[2][2];
		String line;
		for(int i = 0; i<2*h+1; i++){
			line = br.readLine();
			for(int j = 0; j<2*w+1; j++){
				mazetxt[i][j] = line.charAt(j);
			}
		}
		int numExits = 0;
		for(int i = 0; i<h; i++){
			for(int j = 0; j<w; j++){
				room room = new room(i, j);
				for(int k = 0; k<4; k++){
					int adji = i*2+1 + di[k];
					int adjj = j*2+1 + dj[k];
					if(mazetxt[adji][adjj]==' '){
						room.adj[k] = true;
						if(adji==0 || adji==2*h || adjj==0 || adjj==2*w){
							exits[numExits][0] = i;
							exits[numExits][1] = j;
							room.adj[k] = false;
							numExits++;
						}
					}
				}
				maze[i][j] = room;
			}
		}
		findShortestPath(0);
		findShortestPath(1);
		
		out.println(getLongestDistance());		
		out.close();
		System.exit(0);
	}

}
