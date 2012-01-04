import java.io.*;
/*
ID: kennyk61
LANG: JAVA
PROG: sort3
*/
public class sort3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		int n = Integer.parseInt(br.readLine());
		int [] sequence = new int[n];
		int num1 = 0;
		int num2 = 0;
		for(int i = 0; i<n; i++){
			sequence[i] = Integer.parseInt(br.readLine());
			if(sequence[i]==1){
				num1++;
			}else if(sequence[i]==2){
				num2++;
			}
		}
		int swap21 = 0;
		int swap31 = 0;
		int swap12 = 0;
		int swap32 = 0;
		int swap13 = 0;
		int swap23 = 0;
		for(int i = 0; i<n; i++){
			if(i<num1){
				if(sequence[i]==2){
					swap21++;
				}else if(sequence[i]==3){
					swap31++;
				}
			}else if(i<num1+num2){
				if(sequence[i]==1){
					swap12++;
				}else if(sequence[i]==3){
					swap32++;
				}
			}else{
				if(sequence[i]==1){
					swap13++;
				}else if(sequence[i]==2){
					swap23++;
				}
			}
		}
		
		int numSwaps = Math.min(swap12, swap21) + Math.min(swap13, swap31) + 
			Math.min(swap23, swap32) + Math.abs(swap12 - swap21)*2;
		out.println(numSwaps);
		out.close();
		System.exit(0);
	}

}
