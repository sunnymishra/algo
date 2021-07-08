package geeksForGeeksChallenge.hashing;

import java.util.*;
import org.junit.*;

public class _1_RelativeSort2Array{
	@Test
	public void test123(){
		int[] arr = {1,1,2,2,2,3,3,4,5,5,6,7,7};
		System.out.println("arr:"+Arrays.toString(arr)+"\nidx of 1: "+Arrays.binarySearch(arr, 1));
		System.out.println("idx of 2: "+Arrays.binarySearch(arr, 2));
		System.out.println("idx of 3: "+Arrays.binarySearch(arr, 3));
		System.out.println("idx of 4: "+Arrays.binarySearch(arr, 4));
		System.out.println("idx of 5: "+Arrays.binarySearch(arr, 5));
		System.out.println("idx of 6: "+Arrays.binarySearch(arr, 6));
		System.out.println("idx of 7: "+Arrays.binarySearch(arr, 7));
		
		System.out.println("\nidx of 2: "+binarySearch(arr, 2));
		System.out.println("idx of 3: "+binarySearch(arr, 3));
		System.out.println("idx of 4: "+binarySearch(arr, 4));
		System.out.println("idx of 5: "+binarySearch(arr, 5));
		System.out.println("idx of 6: "+binarySearch(arr, 6));
		System.out.println("idx of 7: "+binarySearch(arr, 7));
		
	}
	
	@Test
	public void sortA1ByA2_1_1(){
		int[]a1 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
		int[] a2 = {2, 1, 8, 3};
		System.out.println("\nBefore:"+ Arrays.toString(a1));
		int[] result = sortA1ByA2_1(a1, a1.length, a2, a2.length);
		System.out.println("After:"+Arrays.toString(result));
		Assert.assertArrayEquals(new int[]{2,2,1,1,8,8,3,5,7,9,6}, result);
	}
	////////////////////// Approach 2 below: ///////////////////////
	@Test
	public void sortA1ByA2_2_1(){
		int[]a1 = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8};
		int[] a2 = {2, 1, 8, 3};
		System.out.println("\nBefore:"+ Arrays.toString(a1));
		int[] result = sortA1ByA2_2(a1, a1.length, a2, a2.length);
		System.out.println("After:"+Arrays.toString(result));
		//Assert.assertArrayEquals(new int[]{2,2,1,1,8,8,3,5,7,9,6}, result);
	}
	@Test
	public void sortA1ByA2_2_2(){
		int[]a1 = {8,6,2,4,5,4,0};
		int[] a2 = {8,0,4};
		System.out.println("\nBefore:"+ Arrays.toString(a1));
		int[] result = sortA1ByA2_2(a1, a1.length, a2, a2.length);
		System.out.println("After:"+Arrays.toString(result));
		Assert.assertArrayEquals(new int[]{8,0,4,4,2,5,6}, result);
	}
	/**
		Approach 1: albeit assumption here is that no need to sort the elements in A1 which are not present in A2.
		In Approach 2, we will remove this constraint, which is also part of the actual problem statement i.e. After A2 array elements are taken care, do sort all the elements in A1 which is not in A2.
	
	**/
	public static int[] sortA1ByA2_1(int A1[], int N, int A2[], int M){
		HashMap<Integer, Integer> map=new HashMap<>();
		HashSet<Integer> reference=new HashSet<>();
		
		if(A1==null || A1.length!=N || A2==null || A2.length!=M || N<M){
			return null;
		}
		for(int elem : A2){
			reference.add(elem);
		}
		for(int elem : A1){
			if(map.containsKey(elem)){
				map.put(elem, map.get(elem)+1);
			}else{
				map.put(elem, 1);
			}
		}
		int idx=N;
		for(int i=N-1; i>=0; i--){
			if(!reference.contains(A1[i])){
				idx--;
				swap(A1, i, idx);
			}
		}
		//System.out.println("Intermed:"+Arrays.toString(A1));
		//System.out.println("Map:"+map);
		//System.out.println("reference:"+reference);
		int startIdx=0;
		for(int i=0; i<M; i++){
			int elem = A2[i];
			int len=map.get(elem);
			for(int j=startIdx; j<startIdx+len; j++){
				//System.out.print("Before A1[j]:"+A1[j]);
				A1[j]=elem;
				//System.out.println(" After A1[j]:"+A1[j]);
			}
			//System.out.println("elem:"+elem+" len:"+len+" startIdx:"+startIdx+ " A1:"+Arrays.toString(A1));
			startIdx+=len;
		}
		return A1;
	}
	private static void swap(int[] arr, int idx1, int idx2){
		int temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
	/**
		Approach 2: Using Binary Search. public static int[] sortA1ByA2_1(int A1[], int N, int A2[], int M){
	**/
	public static int[] sortA1ByA2_2(int A1[], int N, int A2[], int M){
		if(A1==null || A1.length!=N || A2==null || A2.length!=M || N<M){
			return null;
		}
		// Step 1: Sort array so Binary search can be done
		int[] A1Copy=new int[N];
		System.arraycopy(A1, 0, A1Copy, 0, N);
		Arrays.sort(A1Copy);
		
		Set<Integer> set=new LinkedHashSet<>();
		for(int i=0; i<M; i++){
			// LinkedHashSet Removes Duplicates in A2, also maintains Insertion order of Array A2
			set.add(A2[i]);
		}
		// Step 2: Binary Search every elem of A2, in A1 array.
		// If found, put those elem at start of array A1.
		int idx=0;
		System.out.println("A1Copy->"+Arrays.toString(A1Copy));
		for(int val: set){
			int i=binarySearch(A1Copy, val);
			System.out.println("val->"+val + ", idx->"+i);
			if(i<0){
				// Ignore any element in A2, which is not present in A1
				continue;
			}
			// Swap this binarySearched value into original array A1
			A1[idx++]=A1Copy[i++];
			// Check if anymore duplicates of A1Copy[i] are there in A1Copy array
			while(i<N && A1Copy[i]==A1Copy[i-1]){
				A1[idx++]=A1Copy[i++];
			}
			
		}
		System.out.println("]");
		for(int j=0; j<N; j++){
			if(!set.contains(A1Copy[j])){
				A1[idx++]=A1Copy[j];
			}
		}
		return A1;
	}
	private static int binarySearch(int[] arr, int val){
		if(arr==null || arr.length==0) return -1;
		return _binarySearch(arr, val, 0, arr.length-1);
	}
	private static int _binarySearch(int[] arr, int val, int l, int r){
		if(l>r) return -1;
		
		int resultIdx=-1;
		
		int mid=l+ (r-l)/2;
		if(arr[mid] > val){
			resultIdx=_binarySearch(arr, val, l, mid-1);
		}else if(arr[mid] < val){
			resultIdx=_binarySearch(arr, val, mid+1, r);
		}else{
			if(mid>0 && arr[mid]==arr[mid-1]){
				resultIdx=_binarySearch(arr, val, l, mid-1);
			}else{
				resultIdx=mid;
			}
			
		}
		return resultIdx;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}