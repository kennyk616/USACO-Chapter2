import java.io.*;
import java.util.TreeSet;
/*
ID: kennyk61
LANG: JAVA
PROG: frac1
*/
class fraction implements Comparable<fraction>{
	int num;
	int denom;
	double val;
	fraction(int num, int denom){
		this.num = num;
		this.denom = denom;
		val = ((double)(num))/((double)(denom));
	}
	@Override
	public int compareTo(fraction other) {
		// TODO Auto-generated method stub
		if(this.val <other.val){
			return -1;
		}else if (this.val > other.val){
			return 1;
		}
		return 0;
	}
}

public class frac1 {
	static int n;
	static TreeSet<fraction> fractions;
	/**
	 * @param args
	 */
	
	static boolean cantBeReduced(int num, int denom){
		if(num==1){
			return true;
		}
		if(num%2==0 && denom%2==0){
			return false;
		}
		for(int i = 3; i*i<=num; i+=2){
			if(num%i==0 && denom%i==0){
				return false;
			}
		}		
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		n = Integer.parseInt(br.readLine());
		out.println("0/1");
		fractions = new TreeSet<fraction>();
		for(int i = 2; i<=n; i++){
			for(int j = 1; j<i; j++){
				if(cantBeReduced(j, i)){
					fractions.add(new fraction(j, i));
				}
			}
		}
		for(fraction f:fractions){
			out.println(f.num + "/" + f.denom);
		}
		out.println("1/1");
		out.close();
		System.exit(0);
	}

}
