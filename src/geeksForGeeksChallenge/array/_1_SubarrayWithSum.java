package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class _1_SubarrayWithSum{
	@Test
	public void findSubarrayWithSum6(){
		Assert.assertEquals( Arrays.asList(new Integer[]{4, 4}), subarraySum2(new int[]{4,7,3,2}, 4, 2));
	}
	@Test
	public void findSubarrayWithSum5(){
		Assert.assertEquals( Arrays.asList(new Integer[]{2, 4}), subarraySum2(new int[]{2, 1,2,3}, 4, 6));
		Assert.assertEquals( Arrays.asList(new Integer[]{2, 6}), subarraySum2(new int[]{100, 95, 104, 12, 123, 134}, 6, 468));
	}
	@Test
	public void findSubarrayWithSum4(){
		Assert.assertEquals( Arrays.asList(new Integer[]{38, 42}), subarraySum2(new int[]{135, 101, 170, 125, 79, 159, 163, 65, 106, 146, 82, 28, 162, 92, 196, 143, 28, 37, 192, 5, 103, 154, 93, 183, 22, 117, 119, 96, 48, 127, 172, 139, 70, 113, 68, 100, 36, 95, 104, 12, 123, 134}, 42, 468));
	}
//	@Test
//	public void findSubarrayWithSum0(){
////		System.out.print("Total Memory="+ Runtime.getRuntime().freeMemory() + " Integer.MAX_VALUE-2="+(Integer.MAX_VALUE-2));
//		int[] arr = new int[Integer.MAX_VALUE/2];
//		Arrays.fill(arr, 0);
//		Assert.assertEquals( Arrays.asList(new Integer[]{-1}), subarraySum2(arr, Integer.MAX_VALUE/2, 1));
//	}
	@Test
	public void findSubarrayWithSum3(){
		Assert.assertEquals( Arrays.asList(new Integer[]{3,5}), subarraySum1(new int[]{1, 4, 20, 3, 10, 5}, 6, 33));
		Assert.assertEquals( Arrays.asList(new Integer[]{-1}), subarraySum1(new int[]{1, 4}, 2, 0));
	}
	
	
	@Test
	public void findSubarrayWithSum1(){
		Assert.assertEquals( Arrays.asList(new Integer[]{2,4}), subarraySum1(new int[]{1,2,3,7,5}, 5, 12));
	}
	@Test
	public void findSubarrayWithSum2(){
		Assert.assertEquals( Arrays.asList(new Integer[]{2,4}), subarraySum2(new int[]{1,2,3,7,5}, 5, 12));
	}
	
	public List<Integer> subarraySum1(int[] arr, int n, int s) {
		if(s<1 || arr==null || arr.length!=n){
			return new ArrayList<>(Arrays.asList(-1));
		}
		int startIdx=-1,endIdx=-1;
		for(int i=0; i<n; i++){
			int sum=0;
			for(int j=i; j<n; j++){
				sum+=arr[j];
				
				if(sum==s){
					startIdx=i+1; endIdx=j+1;
					break;
				}else if(sum>s){
					break;
				}
			}
			if(sum==s) break;
			sum-=arr[i];
		}
		if(startIdx==-1 || endIdx==-1){
			return new ArrayList<Integer>(Arrays.asList(-1));
		}

		return new ArrayList<Integer>(Arrays.asList(startIdx, endIdx));
	}
	
	
	public ArrayList<Integer> subarraySum2(int[] arr, int n, int s) {
		// 1,2,3,7,5
		if(s<0 || arr==null || arr.length!=n || n<=0){
			return new ArrayList<Integer>(Arrays.asList(-1));
		}
		int startIdx=0, endIdx=1,sum=arr[0];
		for(; endIdx<n; endIdx++){
			if(sum==s) break;
			
			sum+=arr[endIdx];
			
			while(sum>s && startIdx<endIdx){
				sum-=arr[startIdx++];
			}
			
		}
		
		if(sum!=s){
			return new ArrayList<Integer>(Arrays.asList(-1));
		}
		
		return new ArrayList<Integer>(Arrays.asList(++startIdx, endIdx));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}