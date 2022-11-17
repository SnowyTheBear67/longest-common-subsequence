/* Name:
 * Lance Boza
 * COP3503 Summer 2022
 * Programming Assignment 3
 */
public class LCS {

	private String x;
	private String y;
	
	private int subProb[][];
	
	//0, 1, 2 for diagonal, vertical, horizontal
	private int direction[][];
	
	
	public LCS(String x, String y) {
		//initializes the x and y string and generates the size of the 2 2d arrays and fills row and col 0, to all 0's
		this.x = x;
		this.y = y;
		subProb = new int[x.length() + 1][y.length() + 1];
		direction = new int[x.length() + 1][y.length() + 1];
		for(int i = 0; i < subProb.length; i++) {
			subProb[0][i] = 0;
			subProb[i][0] = 0;
		}
	}
	
	
	public void lcsDynamicSol() {
		int m = x.length();
		int n = y.length();
		
		// goes through the length of both strings and finds matches between letters and builds the 2 2d arrays
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(x.charAt(i - 1) == y.charAt(j - 1)){
					subProb[i][j] = subProb[i - 1][j - 1] + 1;
					direction[i][j] =  0;
				}
				else if(subProb[i - 1][j] >= subProb[i][j - 1]) {
					subProb[i][j] = subProb[i - 1][j];
					direction[i][j] = 1;
				}
				else {
					subProb[i][j] = subProb[i][j - 1];
					direction[i][j] = 2;
				}
			}
		}
		
	}


	public String getLCS() {
		String print = "";
		int i = x.length();
		int j = y.length();
		
		//calls a recursive helper function to build the lcs string
		print = printLCS(i, j);
		
		return print;
	}
	
	public String printLCS(int i, int j) {
		
		// recursively builds the string from the 2 2d arrays created from lcsdynamicsol
		String print = "";
		if(i == 0 || j == 0) {
			return print;
		}
		
		if(direction[i][j] == 0) {
			print += printLCS(i - 1, j - 1);
			print += x.charAt(i - 1);
		}
		else if(direction[i][j] == 1) {
			print += printLCS(i - 1, j);
		}
		else {
			print += printLCS(i, j - 1);
		}
		return print;
	}
}
