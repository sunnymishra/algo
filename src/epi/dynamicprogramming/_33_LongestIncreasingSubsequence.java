package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class _33_LongestIncreasingSubsequence{
	@Test
	public void findLongestIncreasingSubsequence1(){

		Assert.assertEquals(4, findLongestIncreasingSubsequence1(new int[]{3,4,-1,0,6,2,3}));
		Assert.assertEquals(4, findLongestIncreasingSubsequence1(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9}));
	}
	
	@Test
	public void findLongestIncreasingSubsequence2(){
		Assert.assertEquals(4, findLongestIncreasingSubsequence2(new int[]{3,4,-1,0,6,2,3}));
		Assert.assertEquals(4, findLongestIncreasingSubsequence2(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9}));
	}
	
	public int findLongestIncreasingSubsequence1(int[] arr){
		if(arr==null || arr.length==0) return 0;
		return _findLongestIncreasingSubsequence1(arr, arr.length, new int[]{0,0})[1];
		
	}
	
	/**
		Space: O(n), since max depth of Recusion will be on the Last for the last index n
		Time: O(n^2), since we are calling Recusion function 2 for loops of max size n, so time=1+2+3+4....(n-1)+n=n^2
		This approach takes any index p and runs a for loop from 0 to p-1 and checks for all possible such index q where the value is arr[q] <= arr[p]. Then it takes the highest subsequence value at ending at all those index q and adds +1 to it and this is the subsequence value of new index p. Now the trick is that to get the higher subsequence value of each of the subarray ending at those index q (each q because we are inside a FOR loop), we are calling Recusion function for each of those values q.
		NOTE: In below Recursive approach we are also passing a resultArr because we want to maintain a global state of longestSoFar for all indexes in the input array.
	
	**/
	private int[] _findLongestIncreasingSubsequence1(int[] arr, int arrLength, int[] resultArr){
		if(arrLength==1){
			resultArr[0]=1;
			return resultArr;
		}

		int longest=0;
		for(int i=1; i<arrLength; i++){
			int[] tempLongest=_findLongestIncreasingSubsequence1(arr, i, resultArr);
			
			if(arr[i-1] <= arr[arrLength-1]){
				longest=Math.max(longest, tempLongest[0]);
			}
		}
		resultArr[1]=Math.max(resultArr[1], 1+longest);
		resultArr[0]=1+longest;
		return resultArr;
	}
	
	/**
		Space: O(n), since we are using an extra array of size n for memoization
		Time: O(n^2), since we are doing 2 for loops of max size n, so time=1+2+3+4....(n-1)+n=n^2
		Below solution is exactly same as above Recursive approach. Only difference is that in above approach wherever we call recursion method, we replace that with calling dp array and getting values from it.
	
	**/
	public int findLongestIncreasingSubsequence2(int[] arr){
		if(arr==null || arr.length==0) return 0;

		int[] dp = new int[arr.length];
		dp[0]=1;

		for(int i=1; i<arr.length; i++){
			int longest=0;
			for(int j=0; j<i; j++){
				if(arr[j] <= arr[i]){
					longest=Math.max(longest, dp[j]);
				}
			}
			dp[i]=1+longest;
		}
		System.out.println("\narr[]: "+Arrays.toString(arr));
		System.out.println("\n dp[]: "+Arrays.toString(dp));
		List<Integer> list=Arrays.stream(dp).boxed().collect(Collectors.toList());
		return (int) Collections.max(list); //note: return dp[arr.length-1] can give wrong answer so max(dp) will be right approach.
		
	}
	
}