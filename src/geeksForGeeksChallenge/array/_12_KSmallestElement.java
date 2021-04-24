package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Queue;
import java.util.Random;
import java.util.Arrays;
import java.util.PriorityQueue;


public class _12_KSmallestElement{
	@Test
	public void kthSmallest1(){
		Assert.assertEquals(7, kthSmallest1(new int[]{7, 10, 4, 3, 20, 15}, 0, 5, 3));
	}
	
	@Test
	public void kthSmallest2(){
		Assert.assertEquals(7, kthSmallest2(new int[]{7, 10, 4, 3, 20, 15}, 0, 5, 3));
	}
	
	@Test
	public void kthSmallest3(){
		Assert.assertEquals(7, kthSmallest3(new int[]{7, 10, 4, 3, 20, 15}, 0, 5, 3));
		Assert.assertEquals(15, kthSmallest3(new int[]{7, 10, 4, 20, 15}, 0, 4, 4));
	}
	
	/**
		K Smallest Element using minHeap costs O(n + k log n)
	*/
	public int kthSmallest1(int[] arr, int l, int r, int k){
		if(arr==null || arr.length==0 || l!=0 || r!=arr.length-1 || k<=0 || k>r+1){
			return -1;
		}
		Queue<Integer> minHeap = new PriorityQueue<>((Integer a1, Integer a2) -> Integer.compare(a1, a2));
		
		for(int i=0; i<=r; i++){
			// Creating a Heap of size n = O(n)
			minHeap.add(arr[i]);
		}
		//System.out.println(minHeap);
		int elem=-1;
		for(int i=0; i<k; i++){
			// Heapify called K times in a Heap of Size N = O(k log n)
			elem = minHeap.poll();
		}
		return elem;
	}
	
	/**
		K Smallest Element using maxHeap costs O(n + k log n)
	*/
	public int kthSmallest2(int[] arr, int l, int r, int k){
		if(arr==null || arr.length==0 || l!=0 || r!=arr.length-1 || k<=0 || k>r+1){
			return -1;
		}
		Queue<Integer> maxHeap = new PriorityQueue<>(k, (Integer a1, Integer a2) -> Integer.compare(a2, a1));
		
		//System.out.println(Arrays.toString(arr));
		
		for(int i=0; i<=r; i++){
			// Creating a Heap of size n = O(n)
			if(i<k){
				maxHeap.add(arr[i]);
			}else if(i >= k){
				if(arr[i] < maxHeap.peek()){
					maxHeap.poll();
					
					// Heapify called n-k times in a Heap of Size k = O(n-k log k)
					maxHeap.add(arr[i]);	
				}else{
					// ignore the new element arr[i]
				}
			}
			//System.out.println(maxHeap);
		}
		
		return maxHeap.peek();
	}
	
	/**
		Uses QuickSort with Random partitioner.
		Worst case O(n^2) Best case O(n)
	*/
	public int kthSmallest3(int[] arr, int l, int r, int k){
		quickSort(arr, l, r);
		System.out.println(Arrays.toString(arr));
		return arr[k-1];
	}
	
	private void quickSort(int[] arr, int l, int r){
		if(l<r){
			rand(arr, l, r);
			
			int pivotIdx = partition(arr, l, r);
			quickSort(arr, l, pivotIdx-1);
			quickSort(arr, pivotIdx+1, r);
		}
		
	}
	private int partition(int[] arr, int l, int r){
		int pivotIdx=l-1;

		for(int i=l; i<r; i++){
			if(arr[i]<arr[r]){
				pivotIdx++;
				
				swap(arr, pivotIdx, i);
			}
		}
		pivotIdx++;
		swap(arr, pivotIdx, r);
		return pivotIdx;
	}
	
	private void rand(int[] arr, int l, int r){
		int pivotIdx = 0;
		for(int i=0; i<20; i++){
		pivotIdx = new Random().nextInt(r-l) + l;
		System.out.println("l="+l + " r="+r + " pivotIdx="+pivotIdx);
		}
		swap(arr, pivotIdx, r);
	}
	
	private void swap(int[] arr, int idx1, int idx2){
		int temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
	
	
	
	
}