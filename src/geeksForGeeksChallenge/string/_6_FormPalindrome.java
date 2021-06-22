package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.HashMap;

public class _6_FormPalindrome{
	@Test
	public void countMin1(){
		
		Assert.assertEquals(10, countMin1("helppreanadkada"));
		Assert.assertEquals(0, countMin1("a"));
		Assert.assertEquals(0, countMin1("aa"));
		Assert.assertEquals(1, countMin1("ab"));
		Assert.assertEquals(3, countMin1("abcd"));
		Assert.assertEquals(2, countMin1("ncaba"));
		Assert.assertEquals(1, countMin1("abca"));
	}
	@Test
	public void countMin2(){
		Assert.assertEquals(10, countMin2("helppreanadkada"));
		Assert.assertEquals(0, countMin2("a"));
		Assert.assertEquals(0, countMin2("aa"));
		Assert.assertEquals(1, countMin2("ab"));
		Assert.assertEquals(3, countMin2("abcd"));
		Assert.assertEquals(2, countMin2("ncaba"));
		Assert.assertEquals(1, countMin2("abca"));
	}
	
	/** 
		Below is Recursive approach for DP solution
		Complexity = O(n^2)
	**/
	public int countMin1(String str){
		if(str==null || str.length()<=1) return 0;
		
		int[][] dp = new int[str.length()][str.length()];
		for(int i=0; i<dp.length; i++){
			Arrays.fill(dp[i], -1);
		}
		int count = _countMin2(str.toCharArray(), dp, 0, str.length()-1);
		
		for(int i=0; i<dp.length; i++){
			System.out.println(Arrays.toString(dp[i]));
		}
		return count;
	}
	
	private int _countMin2(char[] charArr, int[][]dp, int left, int right){
		if(left>right) return -1;
		if(left==right) return 0;
		if(right-left==1) return charArr[left]==charArr[right]?0:1;
		
		if(dp[left][right]!=-1){
			return dp[left][right];
		}
		int count=0;
		if(charArr[left]==charArr[right]){
			count = _countMin2(charArr, dp, left+1, right-1);
		}else{
			count = 1 + Math.min(_countMin2(charArr, dp, left+1, right),
								_countMin2(charArr, dp, left, right-1));
		}
		
		dp[left][right]=count;
		return count;
		
	}
	
	/** 
		Below is Iterative approach for DP solution
		Complexity = O(n^2)
	**/
	public int countMin2(String str){
		if(str==null || str.length()<=1) return 0;
		
		int[][] dp = new int[str.length()][str.length()];
		for(int i=0; i<dp.length; i++){
			Arrays.fill(dp[i], 0);
		}
		
		int count=-1;
		for(int len=2; len<=str.length(); len++){
			for(int startIdx=0; startIdx<str.length()-1; startIdx++){
				if(startIdx+len-2 >= str.length() || startIdx+len-1 >= str.length()){
					break;
				}
				if(str.charAt(startIdx)==str.charAt(startIdx+len-1)){
					count=dp[startIdx+1][startIdx+len-2];
				}else{
					count = 1 + Math.min(dp[startIdx+1][startIdx+len-1],
										dp[startIdx][startIdx+len-2]);
				}
				dp[startIdx][startIdx+len-1]=count;
			}
		}
		
		return count;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}