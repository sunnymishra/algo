package geeksForGeeksChallenge.array;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

public class _16_ZigZagArray{
	
	@Test
	public void zigZag() {
		int[] arr = new int[]{4, 3, 7, 8, 6, 2, 1};
		zigZag(arr, arr.length);
		Assert.assertArrayEquals(arr, new int[]{3, 7, 4, 8, 2, 6, 1});
	}
	
	@Test
	public void zigZag2() {
		int[] arr = new int[]{4, 3, 2, 8, 6, 2, 1};
		zigZag(arr, arr.length);
		Assert.assertArrayEquals(arr, new int[]{3, 7, 4, 8, 2, 6, 1});
	}
	
	
	public void zigZag(int arr[], int n) {
		if(arr.length<2 || arr.length!=n) return;
		
		boolean isLesser=true;
		
		int idx=1;
		while(idx<n){
			if(isLesser){
				if( arr[idx] < arr[idx-1]){
					swap(arr, idx, idx-1);
				}
			}else{
				if( arr[idx] > arr[idx-1]){
					swap(arr, idx, idx-1);
				}
			}
			isLesser=!isLesser;
			idx++;
		}

		
		
	}
	
	private void swap(int[]arr, int idx1, int idx2){
		int temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
}