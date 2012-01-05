import java.io.*;
/*
ID: kennyk61
LANG: JAVA
PROG: preface
*/
public class preface {
	static int []count = new int[7];
	static char[] chars = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	static int n;
	
	static void countI(){
		int num5 = n/5;
		int remainder = n%5;
		count[0] += num5*7;
		if(remainder==1){
			count[0] += 1;
		}else if(remainder==2){
			count[0] += 3;
		}else if(remainder==3){
			count[0] +=6;
		}else if(remainder==4){
			count[0] += 7;
		}
	}
	
	static void count10s(int idx, int scale){
		int val = n/(50*scale);
		int remainder = n%(50*scale);
		count[idx] += (val*75*scale);
		if(remainder<9*scale){
			return;
		}
		if(remainder >= 9*scale && remainder<19*scale){
			count[idx] += (remainder-(9*scale-1));
		}else if(remainder < 29*scale){
			count[idx] += ((remainder-(19*scale-1))*2 + 10*scale);
		}else if(remainder < 39*scale){
			count[idx] += ((remainder-(29*scale-1))*3 + 30*scale);
		}else if(remainder <40*scale){
			count[idx] += ((remainder-(39*scale-1))*4 + 60*scale);
		}else if(remainder < 49*scale){
			count[idx] += (remainder-(40*scale-1) + 64*scale);
		}else {
			count[idx] += ((remainder-(49*scale-1))*2+73*scale);
		}
	}
	
	static void count5s(int idx, int scale){
		int num100 = n/(10*scale);
		int remainder = n%(10*scale);
		count[idx] += num100*5*scale;
		if(remainder>=4*scale && remainder<=(9*scale-1)){
			count[idx]+= (remainder-(4*scale-1));
		}else if(remainder>(9*scale-1)){
			count[idx]+= 5*scale;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		n = Integer.parseInt(br.readLine());
		countI();
		
		for(int i = 0; i<6; i++){
			if(i%2==0){
				count5s(i+1, (int)(Math.pow(10.0, i/2)));
			}else{
				count10s(i+1, (int)(Math.pow(10.0, i/2)));
			}
		}
		
		for(int i = 0; i<7; i++){
			if(count[i]>0){
//				System.out.println(chars[i] + " " + count[i]);
				out.println(chars[i] + " " + count[i]);
			}
		}
		out.close();
		System.exit(0);
	}

}
