import java.io.*;
/*
ID: kennyk61
LANG: JAVA
PROG: zerosum
*/
public class zerosum {
	static int n;
	static char[] operations;
	static PrintWriter out;
	
	static void calculate(){
		int sign = 1;
		int term = 1;
		int sum = 0;
		for(int i = 0; i<operations.length; i++){
			if(operations[i]!=' '){
				sum += sign*term;
				term = i+2;
				sign = operations[i]=='+' ? 1 : -1;
			}else{
				term = term*10 + i+2;
			}
		}
		sum += sign*term;
		if(sum==0){
			print();
		}
	}
	
	static void print(){
		StringBuilder sb = new StringBuilder().append(1);
		for(int i = 0; i<operations.length; i++){
			sb.append(operations[i]).append(i+2);
		}
		out.println(sb);
	}
	
	static void tryAll(int val){
		build(val, ' ');
		build(val, '+');
		build(val, '-');
	}
	
	static void build(int count, char operation){
		operations[count-2] = operation;
		if(count==n){
			calculate();
			return;
		}
		tryAll(count + 1);	
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("zerosum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		n = Integer.parseInt(br.readLine());
		operations = new char[n-1];
		tryAll(2);
		out.close();
		System.exit(0);
	}

}
