package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class MinWeightPathTriangle{
	@Test
	public void minWeightPathTriangle(){
		List<List<Integer>> triangle = new ArrayList<>();
		triangle.add(Arrays.asList(2));
		triangle.add(Arrays.asList(4,4));
		triangle.add(Arrays.asList(8,5,6));
		triangle.add(Arrays.asList(4,2,6,2));
		triangle.add(Arrays.asList(1,5,2,3,4));
		Assert.assertEquals(15, minWeightPathTriangle(triangle));
		
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
	public int minWeightPathTriangle(List<List<Integer>> triangle){
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
			minWeight= Integer.min(minWeight, _minWeightPathTriangle(triangle, triangle.size()-1, i, cache));
		}
		for(int i=0; i<triangle.size(); i++){
			System.out.println(cache.get(i));
		}
		return minWeight;
	}
	private int _minWeightPathTriangle(List<List<Integer>> triangle, int rowIdx, int colIdx, List<List<Integer>> cache){
		
		if(rowIdx==0){
			return triangle.get(0).get(0);
		}
		if(colIdx<0){
			colIdx=0;
		}
		if(cache.get(rowIdx).get(colIdx)!=0) return cache.get(rowIdx).get(colIdx);
		
		int minWeightLeft=Integer.MAX_VALUE, minWeightRight=Integer.MAX_VALUE;
		
		if(colIdx>0){
			minWeightLeft=_minWeightPathTriangle(triangle, rowIdx-1, colIdx-1, cache);
		}
		if(colIdx<triangle.get(rowIdx).size()-1){
			minWeightRight=_minWeightPathTriangle(triangle, rowIdx-1, colIdx, cache);
		}
		cache.get(rowIdx).set(colIdx, Integer.min(minWeightLeft, minWeightRight) + triangle.get(rowIdx).get(colIdx));
		return cache.get(rowIdx).get(colIdx);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}