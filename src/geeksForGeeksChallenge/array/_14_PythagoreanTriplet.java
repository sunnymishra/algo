package geeksForGeeksChallenge.array;

import org.junit.Assert;
import org.junit.Test;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class _14_PythagoreanTriplet{
	@Test
	public void checkTriplet1(){
		Assert.assertTrue(checkTriplet1(new int[]{3, 2, 4, 6, 5}, 5));
		
		Assert.assertFalse(checkTriplet1(new int[]{3, 8, 5}, 3));
		
		Assert.assertFalse(checkTriplet1(new int[]{4, 49, 1, 59, 19, 81, 97, 99, 82, 90, 99, 10, 58, 73, 23}, 15));
	}
	
	@Test
	public void checkTriplet2(){
		Assert.assertTrue(checkTriplet2(new int[]{3, 2, 4, 6, 5}, 5));
		
		Assert.assertFalse(checkTriplet2(new int[]{3, 8, 5}, 3));
		
		Assert.assertFalse(checkTriplet2(new int[]{4, 49, 1, 59, 19, 81, 97, 99, 82, 90, 99, 10, 58, 73, 23}, 15));
	}
	public boolean checkTriplet1(int[] arr, int n) {
		if(arr==null || arr.length!=n || n<3) return false;
		
		Set<Integer> set = new HashSet<>();
		for(int i=0;i<n;i++){
			set.add((int)Math.pow(arr[i],2));
		}
		
		for(int i=0;i<n-1; i++){
			for(int j=i+1; j<n; j++){
				int sumSqr = (int)Math.pow(arr[i],2) + (int)Math.pow(arr[j],2);
				if(set.contains(sumSqr)){
					return true;
				}
			}
			
		}
		return false;
	}
	
	public boolean checkTriplet2(int[] arr, int n) {
		if(arr==null || arr.length!=n || n<3) return false;
		
		Set<Integer> set = new HashSet<>();
		for(int i=0;i<n;i++){
			// Square all array elements
			arr[i]=(int)Math.pow(arr[i],2);
		}
		// Sort squared elements of the array O(nlogn)
		Arrays.sort(arr);
		
		for(int i=n-1;i>0; i--){
			int left=0, right=i-1;
			while(left<right){

				int sumSqr = arr[left] + arr[right];
				if(sumSqr == arr[i]){
					return true;
				}else if(sumSqr < arr[i]){
					left++;
				}else if(sumSqr > arr[i]){
					right--;
				}
			}
			
		}
		return false;
	}

	
}