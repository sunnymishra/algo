package geeksForGeeksChallenge.array;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class _17_SpiralTraverseMatrix{
	@Test
	public void spirallyTraverse(){
		int[][] matrix=new int[4][4];
		matrix[0]=new int[]{1,2,3,4};
		matrix[1]=new int[]{5,6,7,8};
		matrix[2]=new int[]{9,10,11,12};
		matrix[3]=new int[]{13,14,15,16};
		
		ArrayList<Integer> result = spirallyTraverse(matrix, matrix.length, matrix[0].length);
		System.out.println(result);
	}
	
	@Test
	public void spirallyTraverse2(){
		int[][] matrix=new int[3][4];
		matrix[0]=new int[]{1,2,3,4};
		matrix[1]=new int[]{5,6,7,8};
		matrix[2]=new int[]{9,10,11,12};
		//matrix[3]=new int[]{13,14,15,16};
		
		ArrayList<Integer> result = spirallyTraverse(matrix, matrix.length, matrix[0].length);
		System.out.println("\n\n"+result);
	}
	@Test
	public void spirallyTraverse3(){
		int[][] matrix=new int[3][4];
		matrix[0]=new int[]{1,2,3,4};
		matrix[1]=new int[]{5,6,7,8};
		matrix[2]=new int[]{9,10,11,12};
		
		
		ArrayList<Integer> result = spirallyTraverse(matrix, matrix.length, matrix[0].length);
		System.out.println("\n\n"+result);
	}
	
	@Test
	public void spirallyTraverse4(){
		int[][] matrix=new int[3][5];
		matrix[0]=new int[]{6, 6, 2, 28, 2};
		matrix[1]=new int[]{12, 26, 3, 28, 7};
		matrix[2]=new int[]{22, 25, 3, 4, 23};
		
		ArrayList<Integer> result = spirallyTraverse(matrix, matrix.length, matrix[0].length);
		System.out.println("\n\n"+result);
	}
	
	public ArrayList<Integer> spirallyTraverse(int[][] matrix, int r, int c){
		ArrayList<Integer> result= new ArrayList<>();
		
		if(matrix==null || matrix.length==0 || matrix[0].length==0 || matrix.length!=r || matrix[0].length!=c) return result;
		
		int maxRowIdx=r/2 + (r%2==0 ? 0 : 1);
		int maxColIdx=c/2 + (c%2==0 ? 0 : 1);
		
		for(int row=0, column=0; row<maxRowIdx && column<maxColIdx; row++, column++){
			traverseRight(result, matrix, row, column, (c-column-1-1));
			traverseDown(result, matrix, (c-column-1), row, (r-row-1-1));
			traverseLeft(result, matrix, (r-row-1), (c-column-1), (column+1));
			traverseUp(result, matrix, column, (r-row-1), row+1);
		}
		return result;
	}
	
	private void traverseRight(ArrayList<Integer> result, int[][] matrix, int rowIndex, int colIndex1, int colIndex2){
		for(int col=colIndex1; col<=colIndex2; col++){
			result.add(matrix[rowIndex][col]);
		}
	}
	
	private void traverseDown(ArrayList<Integer> result, int[][] matrix, int colIndex, int rowIndex1, int rowIndex2){
		for(int row=rowIndex1; row<=rowIndex2; row++){
			result.add(matrix[row][colIndex]);
		}
	}
	
	private void traverseLeft(ArrayList<Integer> result, int[][] matrix, int rowIndex, int colIndex1, int colIndex2){
		for(int col=colIndex1; col>=colIndex2; col--){
			result.add(matrix[rowIndex][col]);
		}
	}
	
	private void traverseUp(ArrayList<Integer> result, int[][] matrix, int colIndex, int rowIndex1, int rowIndex2){
		for(int row=rowIndex1; row>=rowIndex2; row--){
			result.add(matrix[row][colIndex]);
		}
	}
	
	
	
}