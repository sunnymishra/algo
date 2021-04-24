package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class _2_CountTriplet{
	@Test
	public void countTriplet1(){
		Assert.assertEquals( 2, countTriplet1(new int[]{1, 5, 3, 2}, 4));
		
		Assert.assertEquals( 0, countTriplet1(new int[]{2, 3, 4}, 3));
		
		Assert.assertEquals( 2, countTriplet1(new int[]{5, 32, 1, 7, 10, 50, 19, 21, 2}, 9));
		
		
		
	}
	@Test
	public void countTriplet2(){
		//Assert.assertEquals( 2, countTriplet2(new int[]{1, 5, 3, 2}, 4));
		
		//Assert.assertEquals( 0, countTriplet2(new int[]{2, 3, 4}, 3));
		
		//Assert.assertEquals( 2, countTriplet2(new int[]{5, 32, 1, 7, 10, 50, 19, 21, 2}, 9));
		
		//Assert.assertEquals( 3, countTriplet2(new int[]{12, 8, 2, 11, 5, 14, 10}, 7));
		
		Assert.assertEquals( 3, countTriplet2(new int[]{14, 3, 6, 8, 11, 16}, 6	));
		
	}
	/*
	@Test
	public void isSumExist(){
		Assert.assertTrue(isSumExist(new int[]{1, 5, 3, 2}, 4, 3));
		Assert.assertTrue(isSumExist(new int[]{1, 5, 3, 2}, 4, 5));
		
		Assert.assertFalse(isSumExist(new int[]{1, 5, 3, 2}, 4, 2));
	}
	*/
	/**
		Below solution takes O(n) Space
	*/
	int countTriplet1(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a:arr){
            map.putIfAbsent(a, 0);
			map.put(a, map.get(a)+1);
		}
		int tot=0;
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++){
				int sum=arr[i]+arr[j];
				if(map.containsKey(sum)){
					//System.out.println("arr[i]="+arr[i]+" arr[j]="+arr[j]+ " sum="+sum);
					tot++;
				}

				
			}
		}
		
		return tot;
    }
	
	/**
		Below solution takes O(1) Space
	*/
	int countTriplet2(int arr[], int n) {
        Arrays.sort(arr);
		int tot=0;
		for(int i=0; i<n; i++){
			tot = tot + findMatchingSum(arr, n, arr[i]);

		}
		
		return tot;
    }
	

	private int findMatchingSum(int[] arr, int arrLen, int sum){
		int i=0, j=arrLen-1;
		int tot=0;
		while(i<j){
			if(arr[i]==sum) i++;
			if(arr[j]==sum) j--;
			if(i>=j) break;
			
			if(arr[i]+arr[j]==sum){
				System.out.println("arr[i]="+arr[i]+" arr[j]="+arr[j]+ " sum="+sum);
				tot++;
				i++;
				j--;
			} else if(arr[i]+arr[j] > sum){
				j--;
			} else{
				i++;
			}
			
		}
		return tot;
	}
	
	
	
	
	
	
	
	
}