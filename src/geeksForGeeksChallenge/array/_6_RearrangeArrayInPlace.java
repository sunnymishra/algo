package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _6_RearrangeArrayInPlace{
	@Test
	public void rearrange(){
		int[] arr1=new int[]{1,2,3,4,5,6};
		System.out.println("Before->"+ Arrays.toString(arr1));
		rearrange(arr1, arr1.length);
		System.out.println("After->"+ Arrays.toString(arr1));
		
				
		int[] arr2=new int[]{10,20,30,40,50,60,70,80,90,100,110};
		System.out.println("Before->"+ Arrays.toString(arr2));
		rearrange(arr2, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr2));
		
	}
	
	
	public void rearrange(int arr[], int n){
		int val=arr[n-1]+1;
		int minIdx=0;
		int maxIdx=n-1;
	
		for(int i=0; i<n; i++){
			if(i%2==0){
				arr[i] = (arr[i] % val) + (val * (arr[maxIdx] % val));
				maxIdx--;
			}else{
				arr[i] = (arr[i] % val) + (val * (arr[minIdx] % val));
				minIdx++;
			}
		}
		for(int i=0; i<n; i++){
			arr[i]=arr[i]/val;
		}
	
	}
	
	
	
	
}