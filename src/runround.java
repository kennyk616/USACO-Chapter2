import java.io.*;
import java.util.HashSet;
/*
ID: kennyk61
LANG: JAVA
PROG: runround
*/
public class runround {


	
	static boolean isRunRound(long n){
		String str = Long.toString(n);
		int count = 0;
		int idx = 0;
		int length = str.length();
		boolean[] reached = new boolean[length];
		while(true){
			int digit = Character.getNumericValue(str.charAt(idx));
			if(reached[idx]){
				return false;
			}
			reached[idx] = true;
			idx += digit%length;
			if(idx>=length)	idx -= length;
			count++;
			if(count==length){
				if(idx==0){
					break;
				}else{
					return false;
				}
			}
		}
		for(int i = 0; i<length; i++){
			if(!reached[i])	return false;
		}
		
		return true;
	}
	
	static boolean satisfy(long n){
		HashSet<Character> set = new HashSet<Character>();
		String str = Long.toString(n);
		for(int i = 0; i<str.length(); i++){
			Character ch = new Character(str.charAt(i));
			if(str.charAt(i)=='0'||set.contains(ch)){
				return false;
			}
			set.add(ch);
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		long n = Long.parseLong(br.readLine());
		while(true){
			n++;
			if(!satisfy(n))	continue;
			if(isRunRound(n))	break;
			
		}
		out.println(n);
		out.close();
		System.exit(0);
	}

}
