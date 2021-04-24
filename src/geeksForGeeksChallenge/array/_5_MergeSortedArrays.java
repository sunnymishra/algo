package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _5_MergeSortedArrays{
	@Test
	public void merge1(){
		long[] arr1=new long[]{1, 3, 5, 7};
		long[] arr2=new long[]{0, 2, 6, 8, 9};
		System.out.println("Before->"+ Arrays.toString(arr1)+" "+Arrays.toString(arr2));
		merge1(arr1, arr2, arr1.length, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr1)+" "+Arrays.toString(arr2));
		
		long[] arr3=new long[]{0,5,7,9,22};
		long[] arr4=new long[]{1,11,12};
		System.out.println("Before->"+ Arrays.toString(arr3)+" "+Arrays.toString(arr3));
		merge1(arr3, arr4, arr3.length, arr4.length);
		System.out.println("After->"+ Arrays.toString(arr3)+" "+Arrays.toString(arr4));
	}
	
	@Test
	public void merge2(){
		System.out.println("\nNew Test merge2()");
		long[] arr1=new long[]{1, 3, 5, 7};
		long[] arr2=new long[]{0, 2, 6, 8, 9};
		System.out.println("Before->"+ Arrays.toString(arr1)+" "+Arrays.toString(arr2));
		merge2(arr1, arr2, arr1.length, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr1)+" "+Arrays.toString(arr2));
		
		long[] arr3=new long[]{0,5,7,9,22};
		long[] arr4=new long[]{1,11,12};
		System.out.println("Before->"+ Arrays.toString(arr3)+" "+Arrays.toString(arr3));
		merge2(arr3, arr4, arr3.length, arr4.length);
		System.out.println("After->"+ Arrays.toString(arr3)+" "+Arrays.toString(arr4));
	} 
	 
	
	public void merge1(long arr1[], long arr2[], int n, int m){
		int arr1Idx=0;
		int arr2Idx=0;
		
		for(int j=m-1; j>=0; j--){
			int i=n-1;
			//System.out.println("j="+j+" i="+i);
			long last=arr1[n-1];
			if(arr2[j] > last){
				continue;
			}
			
			for(; i>0 && arr1[i]>arr2[j]; i--){
				arr1[i]=arr1[i-1];
			}
			if(i==0 && arr1[i]>arr2[j]){
				i--;
			}
			arr1[i+1]=arr2[j];
			arr2[j]=last;
			
			//System.out.println("j="+j+" i="+i+" -> "+ Arrays.toString(arr1)+" "+Arrays.toString(arr2));
		}
	}
	
	
	public void merge2(long arr1[], long arr2[], int n, int m){
		int arr1Idx=0;
		int arr2Idx=0;
		int lastIdx=n-1;
		
		while(arr1Idx<n && arr2Idx<m){
			if(arr1[arr1Idx] < arr2[arr2Idx]){
				arr1Idx++;
			}else if(arr1[arr1Idx] > arr2[arr2Idx]){
				swap(arr1, arr2, lastIdx, arr2Idx);
				arr1Idx++;
				arr2Idx++;
				lastIdx--;
			}
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
	}
	
	private void swap(long arr1[], long arr2[], int arr1Idx, int arr2Idx){
		long temp=arr1[arr1Idx];
		arr1[arr1Idx]=arr2[arr2Idx];
		arr2[arr2Idx]=temp;
	}
	
	
}