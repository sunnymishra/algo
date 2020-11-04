package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class _28_MinWeightPathTriangle{
	@Test
	public void minWeightPathTriangle1(){
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(4,4));
		triangle.add(Arrays.asList(8,5,6));
		triangle.add(Arrays.asList(4,2,6,2));
		triangle.add(Arrays.asList(1,5,2,3,4));
		Assert.assertEquals(15, minWeightPathTriangle1(triangle));
	}
	@Test
	public void minWeightPathTriangle2(){
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(4,4));
		triangle.add(Arrays.asList(8,5,6));
		triangle.add(Arrays.asList(4,2,6,2));
		triangle.add(Arrays.asList(1,5,2,3,4));
		Assert.assertEquals(15, minWeightPathTriangle2(triangle));
	}
	
	/**
		Problem:
		Write a program that takes as input a triangle of numbers and returns the weight 
		of a minimum weight path from top to bottom row. Here the triangle 1st row - 1 element, 2nd row - 2 elements, 
		3rd row - 3 elements, and so on. While traversing the triangle from top to bottom, you are only allowed to go to the adjacent cell in the below row of the current cell i.e. At any cell (i, j) - row i and column j, the adjacent cells in the below row are (i+1, j) and (i+1, j+1).
	
		Solution logic:
		Using DP we can use Recursion approach to traverse from bottom to top for every cell in the bottom most row. In each of these recursive calls, we keep reducing the row and move to the adjacent columns
		going upwards i.e. column j and column j-1 in the above row. Also if we draw a recursion tree we will 
		notice that several cells are repeated in the tree. To avoid repetition we will use Memoization i.e. 
		store intermediate result in a Cache (in this code an ArrayList).
		
		Asymptotic analysis:
		Time complexity without memoization: O(2^n) because at every Level in the Recursion stack tree, we have 2 branches.
		Space complexity without memoization: O(n) where n is the Height of the Triangle
		
		Time complexity with memoization: O(n^2) because time spent at every cell is max O(1) constant time. and there are 1+2+3+4....n = n x (n+1)/2 elements. Therefore Time complexity = O(n^2) where n = Height of the Triangle.
		Space complexity with memoization: O(n^2) where n = Height of the Triangle. Because the Cache ArrayList stores that many no. of records in it.
		
	*/
	public int minWeightPathTriangle1(List<List<Integer>> triangle){
		if(triangle==null || triangle.size()==0) return 0;
		
		int minWeight=Integer.MAX_VALUE;
		int bottomRowLength = triangle.get(triangle.size()-1).size();

		List<List<Integer>> cache=new ArrayList<>();
		for(int i=0; i<triangle.size(); i++){
			cache.add(new ArrayList<Integer>());
			for(int j=0; j<triangle.get(i).size(); j++){
				cache.get(i).add(0);
			}
		}		
		for(int i=0; i<bottomRowLength; i++){
			minWeight= Integer.min(minWeight, _minWeightPathTriangle1(triangle, triangle.size()-1, i, cache));
		}
		// Printing the Cache on the screen
		for(int i=0; i<triangle.size(); i++){
			System.out.println(cache.get(i));
		}
		return minWeight;
	}
	private int _minWeightPathTriangle1(List<List<Integer>> triangle, int rowIdx, int colIdx, List<List<Integer>> cache){
		
		if(rowIdx==0){
			return triangle.get(0).get(0);
		}
		if(colIdx<0){
			colIdx=0;
		}
		if(cache.get(rowIdx).get(colIdx)!=0) return cache.get(rowIdx).get(colIdx);
		
		int minWeightLeft=Integer.MAX_VALUE, minWeightRight=Integer.MAX_VALUE;
		
		if(colIdx>0){
			minWeightLeft=_minWeightPathTriangle1(triangle, rowIdx-1, colIdx-1, cache);
		}
		if(colIdx<triangle.get(rowIdx).size()-1){
			minWeightRight=_minWeightPathTriangle1(triangle, rowIdx-1, colIdx, cache);
		}
		cache.get(rowIdx).set(colIdx, Integer.min(minWeightLeft, minWeightRight) + triangle.get(rowIdx).get(colIdx));
		return cache.get(rowIdx).get(colIdx);
		
	}
	
	/**
		Solution logic:
		store intermediate result in a Cache (in this code an ArrayList).
		This method minWeightPathTriangle2() uses the Iterative approach to solve the same problem above in minWeightPathTriangle1(). In this approach we traverse from top to bottom of the Triangle. In each cell, we fetch the value of above 2 adjacent cells i.e. if I am in cell (i,j) then I will fetch values from the Cache for (i-1, j-1) & (i-1, j) cells in the above row. We take the minimum of these 2 above Cell values and add current Triangle cell's value into it. This becomes the current cell's min value, so we store this in the Cache and move to the next cell and keep repeating the process.
		Once we are done filling all the cells in the Cache, then we know the bottom-most row in the cache contains the min answer. So we iterate throught all the values in the bottom-most row of Cache and return the min value from it.
		
		Asymptotic analysis:
		Time complexity with memoization(cache): O(n) where n=No. of cells in the Triangle. 
		NOTE: In the above solution minWeightPathTriangle1() n=Height of the Tree, so Time complexity=O(n^2). In the current solution minWeightPathTriangle2(), because time spent at every cell is max O(1) constant time. and there are n elements in the Triangle, therefore Time complexity = O(n) where n = No. of elements in the Triangle.
		Space complexity with memoization(cache): O(n) where n = No. of cells in the Triangle. Because the Cache ArrayList stores that many no. of cells in it.
	*/
	public int minWeightPathTriangle2(List<List<Integer>> triangle){
		if(triangle==null || triangle.size()==0) return 0;
		
		List<List<Integer>> cache=new ArrayList<>();
		int minWeightCurrent=0;
		for(int i=0; i<triangle.size(); i++){
			cache.add(new ArrayList<Integer>());
			for(int j=0; j<triangle.get(i).size(); j++){
				int minWeightLeft=Integer.MAX_VALUE;
				int minWeightRight=Integer.MAX_VALUE;
				minWeightCurrent=triangle.get(i).get(j);
				if(i>0){
					if(j>0){
						minWeightLeft=cache.get(i-1).get(j-1);
					}
					if(j<triangle.get(i).size()-1){
						minWeightRight=cache.get(i-1).get(j);
					}
					minWeightCurrent += Integer.min(minWeightLeft, minWeightRight);
				}
				cache.get(i).add(minWeightCurrent);
			}
		}
		// We know the min value is in the Last row of the Cache. So find the min among bottom-most row elements
		for(int i=0; i<cache.get(cache.size()-1).size(); i++){
			minWeightCurrent=Integer.min(minWeightCurrent, cache.get(cache.size()-1).get(i));
		}
		
		// Printing the Cache on the screen
		for(int i=0; i<cache.size(); i++){
			System.out.println(cache.get(i));
		}
		return minWeightCurrent;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}