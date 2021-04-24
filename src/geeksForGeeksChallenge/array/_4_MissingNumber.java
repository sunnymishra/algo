package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class _4_MissingNumber{
	@Test
	public void missingNumber1(){
		Assert.assertEquals( 4, missingNumber1(new int[]{1,2,5,3}, 5));
		Assert.assertEquals( 5, missingNumber1(new int[]{6,1,3,2,4}, 6));
		Assert.assertEquals( 9, missingNumber1(new int[]{1,2,3,4,5,6,7,8,10}, 10));
		
	}
	
	@Test
	public void missingNumber2(){
		Assert.assertEquals( 4, missingNumber2(new int[]{1,2,5,3}, 5));
		Assert.assertEquals( 5, missingNumber2(new int[]{6,1,3,2,4}, 6));
		Assert.assertEquals( 9, missingNumber2(new int[]{1,2,3,4,5,6,7,8,10}, 10));
		
		
		
	}
	
	/**
		This solution may cause Integer overflow
	*/
	private int missingNumber1(int arr[], int n) {
        if(arr==null || arr.length==0 || arr.length!=n-1){
			return -1;
		}
		int tot=n * (n+1) / 2;
		for(int i=0;i<arr.length; i++){
			tot -= arr[i];
		}
		System.out.println("Here");
		return tot;
    }
	
	/**
		This solution will not cause Integer Overflow
	*/
	private int missingNumber2(int arr[], int n) {
        if(arr==null || arr.length==0 || arr.length!=n-1){
			return -1;
		}
		int tot=0;
		for(int i=0;i<arr.length; i++){
			tot -= arr[i];
			tot += i+1;
		}
		tot += n;
		
		return tot;
    }
	

	
	
	
	
	
	
}