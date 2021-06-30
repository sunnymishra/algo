package geeksForGeeksChallenge.heap;

import org.junit.*;
import java.util.*;


public class _2_HeapSort{
	@Test
	public void buildHeap(){
		int[]arr=new int[]{4, 10, 3, 5, 1};
		System.out.println("\n--------Create MaxHeap------------");
		System.out.println("Before:"+Arrays.toString(arr));
		buildHeap(arr, arr.length);
		System.out.println("After:"+Arrays.toString(arr));
		Assert.assertArrayEquals(new int[]{10,5,3,4,1}, arr);
		
		
		arr=new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
		System.out.println("Before:"+Arrays.toString(arr));
		buildHeap(arr, arr.length);
		System.out.println("After:"+Arrays.toString(arr));
		Assert.assertArrayEquals(new int[]{17,15,13,9,6,5,10,4,8,3,1}, arr);
	}
	@Test
	public void heapSort(){
		int[]arr=new int[]{4, 10, 3, 5, 1};
		System.out.println("\n--------Heap Sort------------");
		System.out.println("Before:"+Arrays.toString(arr));
		heapSort(arr, arr.length);
		System.out.println("After:"+Arrays.toString(arr));
		Assert.assertArrayEquals(new int[]{1,3,4,5,10}, arr);
		
		
		arr=new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
		System.out.println("Before:"+Arrays.toString(arr));
		heapSort(arr, arr.length);
		System.out.println("After:"+Arrays.toString(arr));
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,8,9,10,13,15,17}, arr);
	}
	
	public void heapSort(int[] arr, int n){
		if(arr==null || n==0){
			return;
		}
		buildHeap(arr, n);
		
		for(int i=n-1; i>0; i--){
			swap(arr, i, 0);
			heapify(arr, i, 0);
		}
		
		
		
	}
	public void buildHeap(int[] arr, int n){
		if(arr==null || n==0){
			return;
		}
		
		// Heapify should start from 1st Non Leaf Node, i.e. Parent of Last leaf
		int lastNonLeafNodeIdx = n/2 - 1;
		
		// Run heapify for each level, starting from Last Non Leaf Node
		for(int i=lastNonLeafNodeIdx ; i>=0; i--){
			heapify(arr, n, i);
		}
		
	}
	
	private void heapify(int[] arr, int n, int i){
		int leftChildIdx=2*i + 1;
		int rightChildIdx=2*i + 2;
		
		int largestValIdx=i;
		if(leftChildIdx<n && arr[leftChildIdx]>arr[largestValIdx]){
			largestValIdx=leftChildIdx;
		}
		if(rightChildIdx<n && arr[rightChildIdx]>arr[largestValIdx]){
			largestValIdx=rightChildIdx;
		}
		if(largestValIdx != i){
			swap(arr, i, largestValIdx);

			heapify(arr, n, largestValIdx);
		}
	}
	
	private void swap(int[] arr, int idx1, int idx2){
		int temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}