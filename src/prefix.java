import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
ID: kennyk61
LANG: JAVA
PROG: prefix
*/
public class prefix {
	static HashSet<String> primitives;
	static String sequence;
	static int[] length;
	
	static void findLongestPrefix(){
		for(int i = sequence.length()-1; i>=0; i--){
			label:
			for(String p: primitives){
				for(int j = 0; j<p.length() && i+j<sequence.length(); j++){
					if(sequence.charAt(i+j)!=p.charAt(j)){
						continue label;
					}
					if(j==p.length()-1){
						int pLen = p.length();
						int tmp = pLen + i < sequence.length() ? pLen + length[i+pLen] : pLen;
						length[i] = Math.max(tmp, length[i]);
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
		BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String line = null;
		primitives = new HashSet<String>();
		while(!(line = br.readLine()).equals(".")){
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				primitives.add(st.nextToken());
			}
		}
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine())!=null){
			sb.append(line);
		}
		sequence = sb.toString();
		length = new int[sequence.length()];
		findLongestPrefix();
		out.println(length[0]);
		out.close();
		System.exit(0);
	}

}
