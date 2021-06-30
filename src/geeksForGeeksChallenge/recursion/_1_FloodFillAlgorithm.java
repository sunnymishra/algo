package geeksForGeeksChallenge.recursion;

import org.junit.*;
import java.util.*;

class Coordinates{
	int x;
	int y;
	Coordinates(int x, int y){
		this.x=x;
		this.y=y;
	}
}
public class _1_FloodFillAlgorithm{
	@Test
	public void floodFill1(){
		int [][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1, sc = 1, newColorolor = 2;
		int [][] actualResult = floodFill1(image, sr, sc, newColorolor);
		
		int [][] expectedResult = {{2,2,2},{2,2,0},{2,0,1}}; 
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	@Test
	public void floodFill2(){
		int [][] image = 
			  {{1, 1, 1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1, 0, 0},
               {1, 0, 0, 1, 1, 0, 1, 1},
               {1, 2, 2, 2, 2, 0, 1, 0},
               {1, 1, 1, 2, 2, 0, 1, 0},
               {1, 1, 1, 2, 2, 2, 2, 0},
               {1, 1, 1, 1, 1, 2, 1, 1},
               {1, 1, 1, 1, 1, 2, 2, 1},
               };
			   
		int sr = 4, sc = 4, newColorolor = 3;
		int [][] actualResult = floodFill1(image, sr, sc, newColorolor);
		
		int [][] expectedResult = 
			  {{1, 1, 1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1, 0, 0},
               {1, 0, 0, 1, 1, 0, 1, 1},
               {1, 3, 3, 3, 3, 0, 1, 0},
               {1, 1, 1, 3, 3, 0, 1, 0},
               {1, 1, 1, 3, 3, 3, 3, 0},
               {1, 1, 1, 1, 1, 3, 1, 1},
               {1, 1, 1, 1, 1, 3, 3, 1},
               };
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	
	
	@Test
	public void floodFill3(){
		int [][] image = {{1,1,1},{1,1,0},{1,0,1}};
		int sr = 1, sc = 1, newColorolor = 2;
		int [][] actualResult = floodFill2(image, sr, sc, newColorolor);
		
		int [][] expectedResult = {{2,2,2},{2,2,0},{2,0,1}}; 
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	@Test
	public void floodFill4(){
		int [][] image = 
			  {{1, 1, 1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1, 0, 0},
               {1, 0, 0, 1, 1, 0, 1, 1},
               {1, 2, 2, 2, 2, 0, 1, 0},
               {1, 1, 1, 2, 2, 0, 1, 0},
               {1, 1, 1, 2, 2, 2, 2, 0},
               {1, 1, 1, 1, 1, 2, 1, 1},
               {1, 1, 1, 1, 1, 2, 2, 1},
               };
			   
		int sr = 4, sc = 4, newColorolor = 3;
		int [][] actualResult = floodFill2(image, sr, sc, newColorolor);
		
		int [][] expectedResult = 
			  {{1, 1, 1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1, 0, 0},
               {1, 0, 0, 1, 1, 0, 1, 1},
               {1, 3, 3, 3, 3, 0, 1, 0},
               {1, 1, 1, 3, 3, 0, 1, 0},
               {1, 1, 1, 3, 3, 3, 3, 0},
               {1, 1, 1, 1, 1, 3, 1, 1},
               {1, 1, 1, 1, 1, 3, 3, 1},
               };
		Assert.assertArrayEquals(expectedResult, actualResult);
	}
	
	
	public int[][] floodFill1(int[][] image, int sr, int sc, int newColor){
		if(image==null) return null;
		
		Queue<Coordinates> queue=new LinkedList<>();
		queue.add(new Coordinates(sr, sc));
		int initColor=image[sr][sc];
		int[][] visited=new int[image.length][image[0].length];
		
		while(!queue.isEmpty()){
			Coordinates curr = queue.remove();
			
			if(curr.x - 1 >=0 && visited[curr.x-1][curr.y] == 0 && image[curr.x-1][curr.y] == initColor){
				
				queue.add(new Coordinates(curr.x-1, curr.y));
			}
			if(curr.x + 1 <image.length && visited[curr.x+1][curr.y] == 0 && image[curr.x+1][curr.y] == initColor){
				queue.add(new Coordinates(curr.x+1, curr.y));
			}
			if(curr.y - 1 >=0 && visited[curr.x][curr.y-1] == 0 && image[curr.x][curr.y-1] == initColor){
				queue.add(new Coordinates(curr.x, curr.y-1));
			}
			if(curr.y + 1 <image[0].length && visited[curr.x][curr.y+1] == 0 && image[curr.x][curr.y+1] == initColor){
				queue.add(new Coordinates(curr.x, curr.y+1));
			}
			image[curr.x][curr.y]=newColor;
			visited[curr.x][curr.y]=1;
		}
		
		return image;
	}
	
	
	
	public int[][] floodFill2(int[][] image, int sr, int sc, int newColor){
		int prevColor = image[sr][sc];
		int[][] visited=new int[image.length][image[0].length];
		_floodFill(image, sr, sc, prevColor, newColor, visited);
		return image;
	}
	
	private void _floodFill(int image[][], int x, int y, int prevColor, int newColor, 	int[][] visited){
		// Base cases
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length)
			return;
		if (image[x][y] != prevColor)
			return;
		if(visited[x][y]!=0)
			return;

		// Replace the color at (x, y)
		image[x][y] = newColor;
		visited[x][y] = 1;
	 
		// Recur for north, east, south and west
		_floodFill(image, x+1, y, prevColor, newColor, visited);
		_floodFill(image, x-1, y, prevColor, newColor, visited);
		_floodFill(image, x, y+1, prevColor, newColor, visited);
		_floodFill(image, x, y-1, prevColor, newColor, visited);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}