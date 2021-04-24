package array;

import java.util.Arrays;
import org.junit.Test;
import org.junit.Assert;

public class _9_Sort3PartitionsOf_0_1_2{
	@Test
	public void sort012(){
		int[] arr=new int[]{0, 2, 1, 2, 0};
		System.out.println("Before->"+ Arrays.toString(arr));
		sort012(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr)+"\n");
		
	}
	
	@Test
	public void sort012_1(){
		int[] arr=new int[]{0, 2, 1, 1,2, 0,0};
		System.out.println("Before->"+ Arrays.toString(arr));
		sort012(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr)+"\n");
		
		int[] arr2=new int[]{0, 2, 1, 1,2, 0,0};
		System.out.println("Before->"+ Arrays.toString(arr2));
		sort012_2(arr2, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr2)+"\n");
	}
	@Test
	public void sort012_2(){
		int[] arr=new int[]{0, 1, 0};
		System.out.println("Before->"+ Arrays.toString(arr));
		sort012(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr)+"\n");
	}
	@Test
	public void sort012_3(){
		int[] arr=new int[]{2,2,1,2,1};
		System.out.println("Before->"+ Arrays.toString(arr));
		sort012(arr, arr.length);
		System.out.println("After->"+ Arrays.toString(arr)+"\n");
	}
	
	/**
		Sorts in two traversals
	*/
	public void sort012(int arr[], int n){
		if(arr==null || arr.length==0 || arr.length!=n) return;
		
		int zeroIdx=-1;
		int twoIdx=n;
		
		for(int i=0; i<n; i++){
			if(arr[i]==0){
				zeroIdx++;
				swap(arr, zeroIdx, i);
			}
			
			//System.out.println("i="+i+" zeroIdx="+zeroIdx+" twoIdx="+twoIdx+" "+ Arrays.toString(arr));
			
		}
		for(int i=n-1; i>zeroIdx; i--){
			if(arr[i]==2){
				twoIdx--;
				swap(arr, twoIdx, i);
			}
			//System.out.println("i="+i+" zeroIdx="+zeroIdx+" twoIdx="+twoIdx+" "+ Arrays.toString(arr));
			
		}
	}
	
	/**
		Sorts in single traversal
	*/
	public void sort012_2(int arr[], int n){
		if(arr==null || arr.length==0 || arr.length!=n) return;
		
		int zeroIdx=-1;
		int twoIdx=n;
		
		for(int i=0; i<twoIdx; i++){
			if(arr[i]==0){
				zeroIdx++;
				swap(arr, zeroIdx, i);
			}
			if(arr[i]==2){
				twoIdx--;
				swap(arr, twoIdx, i);
				i--;
			}
			System.out.println("i="+i+" zeroIdx="+zeroIdx+" twoIdx="+twoIdx+" "+ Arrays.toString(arr));
			
		}
		
	}
	
	private void swap(int arr[], int idx1, int idx2){
		int temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
}