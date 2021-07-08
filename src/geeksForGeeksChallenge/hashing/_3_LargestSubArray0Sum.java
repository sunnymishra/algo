package geeksForGeeksChallenge.hashing;

import java.util.*;
import org.junit.*;

public class _3_LargestSubArray0Sum{
	@Test
	public void maxLen(){
		int[] arr = {15,-2,2,-8,1,7,10,23};
		Assert.assertEquals(5, maxLen(arr, arr.length));
		
		int[] arr2 = {15,-2,2,-8,1,7,-1,1,10,23};
		Assert.assertEquals(7, maxLen(arr2, arr2.length));
		
	}
	
	int maxLen(int arr[], int n){
		if(arr==null || arr.length==0 || arr.length!=n){
			return -1;
		}
		System.out.println("Arr:"+Arrays.toString(arr));
		Map<Integer, Integer> sumIdxMap=new HashMap<>();
		
		int maxLen=0;
		int sum=0;
		
		for(int i=0; i<n; i++){
			sum+=arr[i];
			if(sumIdxMap.containsKey(sum)){
				int prevIdx=sumIdxMap.get(sum);
				maxLen=Math.max(maxLen, i-prevIdx);
			}else{
				sumIdxMap.put(sum, i);
			}
		}
		return maxLen;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}