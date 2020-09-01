package recursion;

import java.util.Arrays;

import org.junit.Test;

public class Permutation{
	@Test
	public void testAllPermutation() {
		Integer[] arr = new Integer[] {6,2,5};
		System.out.println("\nAll Permutation:\nInput: "+ Arrays.toString(arr)+"\nOutput:");
		
		allPermutation(arr);
	}
	
			
	@Test
	public void testNextPermutation() {
		Integer[] arr = new Integer[] {6,2,1,5,4,3,0};
		System.out.println("\nNext permutation:\nInput: "+ Arrays.toString(arr));
		nextPermutation1(arr);
		System.out.println("Output: "+ Arrays.toString(arr));
	}
	
	/**
		Problem:
		"AllPermutation" is a fairly straight forward problem where given an input Array of numbers, we are to find all possible permutations of that input.
		
		Solution logic:
		We use Backtracking recursion to solve this problem. At any point in the recursion stack we do 3 steps:
		Step 1: Fix an index (represented by variable "idx" in below code) in the current stack. Let a FOR loop run from that index "idx" to the last index of array and swap the 2 elements i.e. current index "idx" and the FOR loop incrementing index "i".
		
		Step 2: Now that the indexes have been swapped, call the recursion method and increment the index by 1 i.e. idx+1, so that the next recursion stack can use this new index value "idx" and use that as a frame of reference and do the swapping inside a FOR loop
		
		Step 3: Once the Recursion stack unwinds and returns back to the current stack, then unswap the 2 elements. So that we can move forward to the next element in the FOR loop and repeat the whole process again. This is the Backtracking step of reverting the changes made into the data structures before calling the next recursion stack.
		
		Asymptotic analysis:
		Time complexity: O(n!) i.e. Factorial of N solution, where n is Array size
		Since this is an exhaustive recursion this automatically makes it a O(n!). Why is this n! and not n^n? At a current level in the recursion tree if there are n possibilities, then the child level in the Recursion tree, the no. of possibilities reduce to n-1. So this becomes an Arithmetic progression =>
		n x (n-1) x (n-2) ..... 3 x 2 x 1 = n!
		Space complexity: O(n) where n is Array size. Since the Recursion stack maximum depth is the length of the array.
		It is always a good idea to use Paper and Pen to draw a Tree in order to visualize the Recursion stack in action. The depth of the tree will be Space complexity and No. of nodes ~ No. of Leaves in the tree will be the Time complexity
	*/
	public void allPermutation(Integer[] arr) {
		if(arr==null || arr.length==0){
			System.out.println("Invalid input");
			return;
		}
		_allPermutation(arr, 0);
	}

	private void _allPermutation(Integer[] arr, int idx) {
		if(idx==arr.length-1){
			System.out.println(Arrays.toString(arr));
			return;
		}
		for(int i=idx; i<arr.length; i++){
			swap(arr, idx, i);
			_allPermutation(arr, idx+1);
			swap(arr, idx, i);
		}
	}
	
	
	/**
		Problem:
		"NextPermutation" is a complex algorithm. Given an input array A=[6,2,1,5,4,3,0] , the nextPermutation will be A'=[6,2,3,0,1,4,5]. This is in "Lexicographic order"
		
		Solution logic:
		The steps to this algorithm are below:
			Step 1: From Right to left, find the Increasing series and stop at the Inflection point where the series stop to increase. For example in the array A=[6,2,1,5,4,3,0] stop at 3rd Index i.e stop at value "5"
			Step 2: On Right side of Inflection point, find the smallest element i.e. "3" which is greater than the 1st element on the left side of the Inflection point i.e. "1". Swap both elements
			Step 3: Reverse sort the series on the right side of Inflection point i.e. 3rd Index having value "5"
			
		Asymptotic analysis:
		Time complexity= O(n) where n is the size of the input Array. Reason each element in the array is visited at max 3 times due to the 3 Steps mentioned in the above Solution logic. Therefore this is a linear complexity
		T(3 x n) ~ So O(n)
		Space complexity = O(1) meaning constant space because all swaps are done in place in the input array itself and no additional Arrays or any other data structures are is used store intermediate results.
	*/
	public void nextPermutation1(Integer[] arr) {
		if(arr==null || arr.length<2) {
			return;
		}
		// Step 1
		int i=arr.length-2;
		for( ; i>=0; i--) {
			if(arr[i]<=arr[i+1]) {
				break;
			}
		}
		if(i==-1){
			i++;
		}
		
		// Step 2
		int j=arr.length-1;
		for(; j>i; j--) {
			if(arr[j]>arr[i]) {
				break;
			}
		}
		swap(arr, i, j);

		// Step 3
		int startIdx=i+1;
		int endIdx=arr.length-1;
		while(startIdx < endIdx){
			swap(arr, startIdx++, endIdx--);
		}
	}
	
	
	/**
		Lexicographically print the Next permutation of the input Array.
		Reference: https://www.youtube.com/watch?v=nYFd7VHKyWQ
	*/
	public void nextPermutation2(Integer[] arr){
		Integer[] permArr = new Integer[arr.length];
		Arrays.fill(permArr, 0);
		_nextPermutation2(arr, permArr);
	}
	private void _nextPermutation2(Integer[] arr, Integer[] permutation) {
		if(arr==null || permutation==null || arr.length==0 || permutation.length==0 || arr.length<permutation.length)
			return;
		for(int i=0; i<permutation.length; i++) {
			int nextIdx=i;
			
			while(permutation[nextIdx] >= 0) {
				swap(arr, i, permutation[nextIdx]);
				
				int tempIdx=permutation[nextIdx];
				permutation[nextIdx]=permutation[nextIdx]-permutation.length;
				nextIdx=tempIdx;
			}
		}
	}
	
	private void swap(Integer[] arr, int idxA, int idxB) {
		int temp = arr[idxA];
		arr[idxA] = arr[idxB];
		arr[idxB] = temp;
	}
}