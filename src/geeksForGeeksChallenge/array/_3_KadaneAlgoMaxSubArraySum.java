package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class _3_KadaneAlgoMaxSubArraySum{
	@Test
	public void maxSubArraySum(){
		Assert.assertEquals( -1, maxSubArraySum(new int[]{-3,-2,-1,-4}, 4));
		
		
		
	}
	
	private int maxSubArraySum(int arr[], int n){
        int maxSoFar=Integer.MIN_VALUE;
		int maxEndingHere=0;
		
		for(int i=0; i<n; i++){
			maxEndingHere+=arr[i];
			
			if(maxSoFar < maxEndingHere){
				maxSoFar=maxEndingHere;
			}
			if(maxEndingHere<0){
				maxEndingHere=0;
			}
		}
		return maxSoFar;
    }
	

	
	
	
	
	
	
}