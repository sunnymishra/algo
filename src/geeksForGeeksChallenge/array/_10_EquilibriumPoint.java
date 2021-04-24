package array;

import java.util.Arrays;
import org.junit.Test;
import org.junit.Assert;

public class _10_EquilibriumPoint{
	@Test
	public void equilibriumPoint1(){
		
		Assert.assertEquals(3, equilibriumPoint(new long[]{1,3,5,2,2}, 5));
		Assert.assertEquals(1, equilibriumPoint(new long[]{1}, 1));
		Assert.assertEquals(-1, equilibriumPoint(new long[]{1,2}, 2));
		Assert.assertEquals(1, equilibriumPoint(new long[]{1,0}, 2));
	}
	
	
	
	/**
		Sorts in two traversals
	*/
	public int equilibriumPoint(long arr[], int n) {
		if(arr==null || arr.length==0 || arr.length!=n) return -1;
		int equilibriumPoint=-1;
		long sum=0;
		for(int i=0; i<n; i++){
			sum+=arr[i];
		}
		
		long sumSoFar=0;
		for(int i=0; i<n; i++){
			if(sumSoFar == (sum-arr[i]-sumSoFar)){
				equilibriumPoint= i+1;
				break;
			}
			sumSoFar+=arr[i];
		}
		return equilibriumPoint;
	}
	
}