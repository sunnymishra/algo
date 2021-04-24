package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _8_InversionCount{
	//@Test
	public void inversionCount(){
		long[] arr=new long[]{6,5,1,7,3,4};
		System.out.println("Before->"+ Arrays.toString(arr));
		long result = inversionCount(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr));
		//Assert.assertEquals(3, result);
		System.out.println("result->"+ result);
		
				
		long[] arr2=new long[]{3,4,2,1};
		System.out.println("Before->"+ Arrays.toString(arr2));
		long result2 = inversionCount(arr2, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr2));
		Assert.assertEquals(3, result2);
		
		long[] arr3=new long[]{2, 4, 1, 3, 5};
		System.out.println("Before->"+ Arrays.toString(arr3));
		long result3 = inversionCount(arr3, arr3.length);
		System.out.println("After->"+ Arrays.toString(arr3));
		Assert.assertEquals(3, result3);
		
		long[] arr4=new long[]{2, 3, 4, 5, 6};
		System.out.println("Before->"+ Arrays.toString(arr4));
		long result4 = inversionCount(arr4, arr4.length);
		System.out.println("After->"+ Arrays.toString(arr4));
		Assert.assertEquals(0, result4);
		
		long[] arr5=new long[]{10, 10, 10};
		System.out.println("Before->"+ Arrays.toString(arr5));
		long result5 = inversionCount(arr5, arr5.length);
		System.out.println("After->"+ Arrays.toString(arr5));
		Assert.assertEquals(0, result5);
	}
	
	@Test
	public void inversionCount2(){
		long[] arr=new long[]{468, 335, 1, 170, 225, 479, 359, 463, 465, 206, 146, 282, 328, 462, 492, 496, 443, 328, 437, 392, 105, 403, 154, 293, 383, 422, 217, 219, 396, 448, 227, 272, 39, 370, 413, 168, 300, 36, 395, 204, 312, 323};
		System.out.println("Before->"+ Arrays.toString(arr));
		long result = inversionCount(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr));
		//Assert.assertEquals(3, result);
		System.out.println("result->"+ result);
		
		long[] arr2=new long[]{4,3,2,1};
		System.out.println("Before->"+ Arrays.toString(arr2));
		long result2 = inversionCount(arr2, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr2));
		//Assert.assertEquals(3, result);
		System.out.println("result->"+ result2);
	}
	
	
	public long inversionCount(long[] arr, long N){
		
		return _inversionCount(arr, 0, (int)(N-1));
		
	}
	
	/**
		Using QuickSort O(n log n)
	*/
	private long _inversionCount(long[] arr, int low, int high){
		int inversionCount=0;
		if(low < high){
			int[] currentValues = partition(arr, low, high);
			int pivotIdx=currentValues[0];
			inversionCount += currentValues[1];
			
			inversionCount += _inversionCount(arr, low, pivotIdx-1);
			inversionCount += _inversionCount(arr, pivotIdx+1, high);
		}
		
		return inversionCount;
	}
	
	
	private int[] partition(long[] arr, int low, int high){
		int pivotIdx=low-1;
		int inversionCount=0;
		for(int i=low; i<high; i++){
			if(arr[i] < arr[high]){
				pivotIdx++;
				
				if(i != pivotIdx){
					swap(arr, i, pivotIdx);
					inversionCount++;
				}
			}
		}
		pivotIdx++;
		
		if(high != pivotIdx && arr[high]!=arr[pivotIdx]){
			swap(arr, pivotIdx, high);
			inversionCount++;
		}
		
		return new int[]{pivotIdx, inversionCount};
	}
	
	
	private void swap(long arr[], int idx1, int idx2){
		long temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
	
}