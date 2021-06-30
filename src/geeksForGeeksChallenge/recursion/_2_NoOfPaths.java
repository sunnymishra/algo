package geeksForGeeksChallenge.recursion;

import java.util.*;
import org.junit.*;

public class _2_NoOfPaths{
	@Test
	public void numberOfPaths1(){
		Assert.assertEquals(6, numberOfPaths1(3,3));
											
		Assert.assertEquals(8, numberOfPaths1(2,8));
											
		Assert.assertEquals(8, numberOfPaths1(8,2));
											
		Assert.assertEquals(1, numberOfPaths1(1,1));
											
		Assert.assertEquals(0, numberOfPaths1(0,0));
		
		Assert.assertEquals(1, numberOfPaths1(1,3));
	}
	@Test
	public void numberOfPaths2(){
		Assert.assertEquals(6, numberOfPaths2(3,3));
											
		Assert.assertEquals(8, numberOfPaths2(2,8));
											
		Assert.assertEquals(8, numberOfPaths2(8,2));
											
		Assert.assertEquals(1, numberOfPaths2(1,1));
											
		Assert.assertEquals(0, numberOfPaths2(0,0));
		
		Assert.assertEquals(1, numberOfPaths2(1,3));
	}
	@Test
	public void numberOfPaths3(){
		Assert.assertEquals(6, numberOfPaths3(3,3));
											
		Assert.assertEquals(8, numberOfPaths3(2,8));
											
		Assert.assertEquals(8, numberOfPaths3(8,2));
											
		Assert.assertEquals(1, numberOfPaths3(1,1));
											
		Assert.assertEquals(0, numberOfPaths3(0,0));
		
		Assert.assertEquals(1, numberOfPaths3(1,3));
	}
	/**
		This is recursion approach. Time=O(m x n) Space=O(m+n)
		See 1 more solution at bottom for Iterative DP approach.
	
	**/
	long numberOfPaths1(int m, int n) {
		if(m<1 || n<1) return 0;
        if(m==1 && n==1) return 1;

		return numberOfPaths1(m-1, n) + numberOfPaths1(m, n-1); 
    }
	
	
	/**
		This is Iterative DP approach Type 1. 
		Time=O(m x n) Space=O(m x n)
		See 1 more solution at bottom for DP Space optimization of O(n).
	
	**/
	long numberOfPaths2(int m, int n) {
		if(m<1 || n<1) return 0;
		int[][] dp = new int[m][n];
		
		for(int y=0; y<n; y++){
			// Initializing all 0th Row cells with value 1. Reason: No. of paths for all matrices where only 1 Row is there will be 1.
			dp[0][y]=1;
		}
		for(int x=0; x<m; x++){
			// Initializing all 0th Column cells with value 1. Reason: No. of paths for all matrices where only 1 Column is there will be 1.
			dp[x][0]=1;
		}
		for(int x=1; x<m; x++){
			for(int y=1; y<n; y++){
				dp[x][y]=dp[x-1][y] + dp[x][y-1];
			}
		}
		
		
		return dp[m-1][n-1];
		
		
		
	}
	/**
		This is Iterative DP approach Type 2.
		Time=O(m x n) Space=O(m)	
	**/
	long numberOfPaths3(int m, int n) {
		if(m<1 || n<1) return 0;
		if(m==1 || n==1) return 1;
		
		int[][] dp = new int[2][n];
		
		for(int y=0; y<n; y++){
			// Initializing all 0th Row cells with value 1. Reason: No. of paths for all matrices where only 1 Row is there will be 1.
			dp[0][y]=1;
		}
		// Initializing 0th Column cells with value 1. Reason: No. of paths for all matrices where only 1 Column is there will be 1.
		dp[1][0]=1;
		
		for(int x=1; x<m; x++){
			for(int y=1; y<n; y++){
				dp[1][y]=dp[0][y] + dp[1][y-1];
				
				// Copying 1st Row data to 0th Row
				dp[0][y]=dp[1][y];
			}
		}
		return dp[1][n-1];
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}