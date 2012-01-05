import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
ID: kennyk61
LANG: JAVA
PROG: lamps
*/
public class lamps {
	static int n;
	static int c;
	static int[] ons;
	static int[] offs;
	static boolean[] config = {true, true, true, true, true, true};
	static ArrayList<String> finalConfig = new ArrayList<String>();
	
	static void button1(){
		for(int i = 0; i<config.length; i++){
			config[i] = !config[i];
		}
	}
	
	static void button2(){
		for(int i = 0; i<config.length; i++){
			if(i%2==0){
				config[i] = !config[i];
			}
		}
	}
	
	static void button3(){
		for(int i = 0; i<config.length; i++){
			if(i%2==1){
				config[i] = !config[i];
			}
		}
	}
	
	static void button4(){
		config[0] = !config[0];
		config[3] = !config[3];
	}
	
	static boolean satisfy(){
		for(int i = 0; i<n; i++){
			if(ons[i]==0 && offs[i]==0){
				break;
			}
			if(ons[i]!=0){
				if(!config[(ons[i]-1)%6])	return false;
			}
			if(offs[i]!=0){
				if(config[(offs[i]-1)%6])		return false;
			}
		}
		return true;
	}
	
	static String convertToString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<config.length; i++){
			if(config[i]){
				sb.append("1");
			}else{
				sb.append("0");
			}
		}
		return sb.toString();
	}
	static void pickButton(int i){
		if(i==1){
			button1();
		}else if(i==2){
			button2();
		}else if(i==3){
			button3();
		}else if(i==4){
			button4();
		}
	}
	static void check(int i){
		pickButton(i);
		if(satisfy()){
			finalConfig.add(convertToString());
		}
		pickButton(i);
	}
	
	static void check1Move(){
		for(int i = 1; i<=4; i++){
			check(i);
		}
	}
	
	static void check2Moves(){
		if(satisfy()){
			finalConfig.add(convertToString());
		}
		for(int i = 1; i<=4; i++){
			pickButton(i);
			for(int j = i+1; j<=4; j++){
				check(j);
			}
			pickButton(i);
		}		
	}
	
	static void findFinalConfig(){
		if(c==0){
			if(satisfy()){
				finalConfig.add(convertToString());
			}
		}else if(c==1){
			check1Move();						
		}else if(c==2){
			check2Moves();
		}else if(c%2==1){
			check1Move();
			for(int i = 1; i<=2; i++){
				pickButton(i);
				for(int j = i+1; j<=3; j++){
					pickButton(j);
					for(int k = j+1; k<=4; k++){
						check(k);
					}
					pickButton(j);
				}
				pickButton(i);
			}
		}else{
			check2Moves();
			for(int i = 1; i<=4; i++){
				pickButton(i);
			}
			if(satisfy()){
				finalConfig.add(convertToString());
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		n = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());
		ons = new int[n];
		int lamp;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++){
			lamp = Integer.parseInt(st.nextToken());
			if(lamp==-1){
				break;
			}
			ons[i] = lamp;
		}
		
		offs = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++){
			lamp = Integer.parseInt(st.nextToken());
			if(lamp==-1){
				break;
			}
			offs[i] = lamp;
		}
		findFinalConfig();
		
		if(finalConfig.size()==0){
			out.println("IMPOSSIBLE");
		}else{
			Collections.sort(finalConfig);
			for(int i = 0; i<finalConfig.size(); i++){
				String str = finalConfig.get(i);
				for(int j = 0; j<n; j++){
					out.print(str.charAt(j%6));
				}
				out.println();
			}
		}		
		out.close();
		System.exit(0);
	}

}
