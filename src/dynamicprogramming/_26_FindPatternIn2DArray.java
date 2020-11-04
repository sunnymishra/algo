package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class _26_FindPatternIn2DArray{
	@Test
	public void findPatternIn2DArray1(){
		int[][]arr1={{1,2},{3,4}};
		Assert.assertTrue(findPatternIn2DArray1(arr1, new int[]{1,2}));
		Assert.assertFalse(findPatternIn2DArray1(arr1, new int[]{2,3}));

		int[][]arr2={{1,2,3},{3,4,5},{5,6,7}};
		Assert.assertTrue(findPatternIn2DArray1(arr2, new int[]{1,3,4,6}));
		Assert.assertFalse(findPatternIn2DArray1(arr2, new int[]{1,2,3,4}));
		Assert.assertFalse(findPatternIn2DArray1(arr2, new int[]{6,2,1,5}));
	}
	
	public boolean findPatternIn2DArray1(int[][] mazeArr, int[]sequenceArr){
		if(mazeArr==null || mazeArr.length==0 || sequenceArr==null || sequenceArr.length==0) return false;
		
		Map<Integer, Integer> visited = new HashMap<>();
		for(int i=0; i<mazeArr.length; i++){
			for(int j=0; j<mazeArr[0].length; j++){
				boolean isMatch = _findPatternIn2DArray1(mazeArr, sequenceArr, i, j, sequenceArr.length-1, visited);
				if(isMatch){
					return isMatch;
				}
			}
		}
		return false;
	}
	
	/**
		Problem: 
		Write a program that takes as arguments a 2D array called Matrix and a ID array called Pattern, and checks whether the ID array occurs in the 2D array i.e. the Pattern exists inside the Matrix or not. The only rule to follow is that at any given cell (i,j) you cannot go to the diagonal cell i.e. (i-1,j-1), (i+1,j+1), (i-1,j+1), (i+1,j-1). You are allowed to go up=(i-1,j) or down=(i+1,j) or left=(i,j-1) or right=(i,j+1). Also the starting cell can be anywhere in the 2D Matrix Array.
		
		NOTE: You are allowed to visit a cell more than once.
		
		Solution approach:
		As the problem statement itself says we are allowed to move up,left,down,right, so at every level in the Recursion stack we will call 4 Recursion functions each time changing the i,j co-ordinates eg. up=(i-1,j) or down=(i+1,j) or left=(i,j-1) or right=(i,j+1). Also we have to use some caching which will help in following purpose :  Caching previous steps will ensure that if some cell is visited already and we know the result of going into that cell i.e. Pattern exists if I go into that cell or not, then we need not re-visit that cell again, thereby I will save lot of computation by not doing redundant steps again.
		Base condition will be that every time I am going into next Recursion stack level, I am reducing the length of the Pattern Array, so as soon as I go below 0th Index of Pattern Array I know that all my elements in Pattern array have already been matched in previous Recursion levels. This means I can break from the Recursion stack by returning TRUE.
		
		Time complexity:
		The complexity is O(n x m x k),where n and m are the dimensions of 2D Matrix array and k is the Dimension of the 1D Pattern Array. Reason: We do a constant amount of work within each call to the Recursion function, except for the 4 recursive calls. However since we are caching the intermediate results i.e. we are already storing the result of each already visited cell in the 2D matrix, this means that the number of Recursive calls is never going to be more than the number of cells in the 2D array i.e n x m. Now if you notice in below method 
		
		NOTE: If the restriction is removed and you are not allowed to go to any cell more than once, then how will you change your Caching logic? Will you add and remove an entry from the Cache (HashMap) in each Recursion stack level?
	**/
	private boolean _findPatternIn2DArray1(int[][] mazeArr, int[]sequenceArr, int currX, int currY, int sequenceIdx, Map<Integer, Integer> visited){
		if(sequenceIdx<0){
			return true;
		}
		if(currX<0 || currX>=mazeArr.length || currY<0 || currY>=mazeArr[0].length){
			return false;
		}
		int uniqueKey=currentCellIndex(currX, currY, mazeArr[0].length);
		if(visited.containsKey(uniqueKey) && visited.get(uniqueKey)==sequenceIdx){
			return false;
		}
		if(mazeArr[currX][currY] != sequenceArr[sequenceIdx]){
			return false;
		}
		
		boolean isMatch	=	
			_findPatternIn2DArray1(mazeArr, sequenceArr, currX-1, currY, sequenceIdx-1, visited) 	|| 
			_findPatternIn2DArray1(mazeArr, sequenceArr, currX, currY-1, sequenceIdx-1, visited) 	||
			_findPatternIn2DArray1(mazeArr, sequenceArr, currX+1, currY, sequenceIdx-1, visited)	||
			_findPatternIn2DArray1(mazeArr, sequenceArr, currX, currY+1, sequenceIdx-1, visited);
		if(isMatch){
			visited.put(uniqueKey, sequenceIdx);
		}
		
		return isMatch;
	}
	/**
		Some logic to convert current cell's co-ordinates (x, y) i.e. 2 integers into a single integer to easily store in HashSet/HashMap
		NOTE: If question says that the Dimention of Input 2D array is x,y and both x & y can be at max 2^31, then in that case we cannot use below approach since below method uses Multiplication and Addition which will cause Integer Overflow. In that case we have to use a custom Java Pojo class to store the i,j index visited.
	*/
	private int currentCellIndex(int xIdx, int yIdx, int totalColumns){
		int cellsAboveCurrentRow = xIdx*totalColumns;
		int cellsInCurrentRow = yIdx +1;
		return cellsAboveCurrentRow+cellsInCurrentRow;
	}
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	