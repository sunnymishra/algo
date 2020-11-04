package recursion;

import org.junit.Test;
import java.util.List;

/**
	Question: 
	https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/

	Solution logic:
	This below code is using Recursion. Here exhaustive Backtracking is used with Branch and Bound. Exhaustive Backtracking means for every cell(x,y) visited go to next recursion stack and do the same process of every other cells. Repeat this for every single cell. This means for a given value of 'n' the time complexity = O(n^n) i.e. Exponential Time complexity.

	However Branch and Bound tightens this Solution to converge to a Time complexity of O(n!) i.e. Factorial of n. Since O(n!) < O(n^n) therefore Branch and Bound is a desirable optimization. For this I have added the isSafe() function which will check for every new position of the Queen, if it is safe to place the Queen in that cell[x,y].
	If not safe then we will move to the next column and check isSafe() again. This way we can avoid getting into unnecessary Recursion stacks by checking if a cell is even Safe to get into or not in the 1st place.
	If we reach till the last column and not a single safe column is found, then it means N Queens is not possible, and thus the program will return false and exit.
	Also, another optimization as part of Branch and Bound is that I have assumed that if a Queen is placed in a certain Row, then no point in placing any next Queen in the same Row, because 1 Queen can attack anybody else in the same Row. THis can be noted in below code where I am incrementing x+1 while calling the Recursion function 
	_nQueens(board, n, x+1);  Here x is the current row number. While going for next Queen I am increasing row num=> x+1.

	Note: There is a scope of improvement in the isSafe() function. Right now for every unique input of x,y, this function is taking Time of O(n) to do 3 FOR loops and tell whether x,y is safe or not. This can be drastically improved to constant Time complexity of O(1). FOr this we need to add extra 3 Arrays to store the cells attackable by previously placed Queens. 
	1.)Array of size n stores the Columns which are attackable by previously placed Queens, 
	2.)Array of size 2xn stores the diagonals (diagonal direction=top left to bottom right) which are attackable by previous placed Queens. Note: Use MATRIX math property all cells of this diagonal has same x+y value
	3.)Array of size 2xn stores the diagonals (diagonal direction=bottom left to top right) which are attackable by previous placed Queens. Note: Use MATRIX math property all cells of this diagonal has same x-y value. Store absolute value of this in the Array to avoid ArrayIndexOutOfBoundsException

	To Run this code, either add a "public static void main(String[] args)" and from that main() function call testNQueens(). Or Download the entire Github repository, such that there is a attached lib/junit.jar lib/hamcrest.jar file. Use command prompt to go to the Root/src folder and run the command > EpiExecutor.bat NQueens
	This will run the JUnit code in this Java file NQueens.testNQueens()

	If n=3, then we know NQueens is not possible, therefore the code will return FALSE
	If n=4 , etc, then the code will return TRUE and also print the solution Matrix.

	Asymptotic analysis:
	Time complexity = O(n!) i.e. Factorial of n.
	T(n) = n*T(n-1), which translates to nx(n-1)x(n-2).....3x2x1 = n1 = Factorial of n
	If this solution is written as a Tree using Paper and pen, it can be easily seen that each level n in the tree is spawning at max n-1 child branches, and the next level n-1 is spawning n-2 child branches. Therefore it gets easier to visualize why the time complexity is Factorial of n.

	Reference: https://www.youtube.com/watch?v=xouin83ebxE

*/
public class _1_NQueens {
	@Test
	public void testNQueens(){
		int n=6;
		System.out.println("");
		System.out.println("Is "+ n + " NQueens possible: "+ nQueens(n));
	}
	
	private boolean nQueens(int n){
		int[][] board = getNewBoard(n);
		boolean isSuccess = _nQueens(board, n, 0);
		if(isSuccess)
			printBoard(board, n);
		return isSuccess;
	}
	private boolean _nQueens(int[][] board, int n, int x){
		if(x>=n){
			return true;
		}

		for(int y=0; y<n; y++){
			if(!isSafe(board, x, y)){
				continue;
			}
			board[x][y]=1;
			boolean isSuccess = _nQueens(board, n, x+1);
			if(isSuccess){
				return true;
			}else{
				board[x][y]=0;
			}
		}
		return false;
	}
	
	private boolean isSafe(int [][] board, int rowIdx, int colIdx){
		// Validate Column
		int y=colIdx;
		for(int x=0; x<rowIdx; x++){
			if(board[x][y]==1){
				return false;
			}
		}
		// Validate Column
		int x=rowIdx;
		for(y=0; y<colIdx; y++){
			if(board[x][y]==1){
				return false;
			}
		}
		// Validate Diagonal Left
		x=rowIdx;
		y=colIdx;
		while(--x>=0 && --y>=0){
			if(board[x][y]==1){
				return false;
			}
		}
		// Validate Diagonal Right
		x=rowIdx;
		y=colIdx;
		while(--x>=0 && ++y<board.length){
			if(board[x][y]==1){
				return false;
			}
		}
		return true;
	}
	
	private void printBoard(int[][] board, int n){
		for(int x=0; x<n; x++){
			for(int y=0; y<n; y++){
				System.out.print(board[x][y]+ "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	private int[][] getNewBoard(int n){
		int[][] board=new int[n][n];
		for(int x=0; x<n; x++){
			for(int y=0; y<n; y++){
				board[x][y]=0;
			}
		}
		return board;
	}
		
}