package geeksForGeeksChallenge.recursion;

import java.util.*;
import org.junit.*;

public class _3_CombinationSumPart2{

	@Test
	public void combinationSumSet1_test1(){
		List<List<Integer>> result = combinationSumSet1(new int[]{2,7,3,1}, 4, 3);
		result.forEach((sublist)-> {System.out.print(sublist+", ");});
		
	}
	@Test
	public void combinationSumSet1_test2(){
		List<List<Integer>> result = combinationSumSet1(new int[]{4,8,2,6,6,8}, 6, 8);
		result.forEach((sublist)-> {System.out.print(sublist+", ");});
		// Expected: ( 2 2 2 2 ) ( 2 2 4 ) ( 2 6 ) ( 4 4 ) ( 8 )
	}
	
	@Test
	public void combinationSum2_test1(){
		List<List<Integer>> result = combinationSumSet2(new int[]{10, 1, 2, 7, 6, 1, 5}, 7, 8);
		result.forEach((sublist)-> {System.out.print(sublist+", ");});
		
	}
	//@Test
	public void combinationSum2_test2(){
		List<List<Integer>> result = combinationSumSet1(new int[]{6,5,6,3,3,4,3,2,2,9,9}, 11, 24);
		result.forEach((sublist)-> {System.out.print(sublist+", ");});
		
	}
	
	/**
		Set 1: This is Set1 of Combination Sum where a.) we remove Duplicates in Input Array, and then b.) we allow any Element to repeat itself in order to achieve the given Sum.
		
		Set 1: https://www.geeksforgeeks.org/all-unique-combinations-whose-sum-equals-to-k/
		
		Look for Set 2 at bottom of this file
	**/
	List<List<Integer>> combinationSumSet1(int A[], int N, int B){
		if(A==null || A.length!=N || N==0) return null;
		
		// Sort the Array and Remove duplicates
		List<Integer> list = sortAndRemoveDuplicates(A);

		List<List<Integer>> result = new ArrayList<>();
		System.out.println("\n\nInput="+list + " Sum="+ B);
		_combinationSumSet1(list, list.size(), B, 0, result, new ArrayList<Integer>());
		return result;
	}
	private List<Integer> sortAndRemoveDuplicates(int A[]){
		Arrays.sort(A);
		int prev=A[0];
		List<Integer> list = new ArrayList<>();
		list.add(prev);
		for(int i=1; i<A.length; i++){
			if(A[i]!=prev){
				prev=A[i];
				list.add(prev);
			}
		}
		return list;
	}

	private void _combinationSumSet1(List<Integer> list, int N, int sum, int idx, List<List<Integer>> result, List<Integer> bufferList){
		if(sum==0){
			List<Integer> temp = new ArrayList(bufferList);
			result.add(temp);
			return;
		}
		if(idx==N || sum<0){
			return;
		}
		for(int i=idx; i<N; i++){
			// Backtracking
			bufferList.add(list.get(i));
			_combinationSumSet1(list, N, sum-list.get(i), i, result, bufferList);
			bufferList.remove(bufferList.size()-1);
		}
	}
	
	
	
	/**
		Set 2: Here a.) we do allow the Duplicates to stay in the Input Array, but, b.) we don't allow Elements to repeat itself.
		
		Set 2: https://www.geeksforgeeks.org/combinational-sum/
		
		Look for 1 more solution "Set 1" at the top of this file which is opposite of Set 2
	**/
	List<List<Integer>> combinationSumSet2(int A[], int N, int B){
		if(A==null || A.length!=N || N==0) return null;
		
		// Sort the Array and Remove duplicates
		List<Integer> list = new ArrayList<Integer>(A.length);
		for(int i : A){
			list.add(i);
		}
		Collections.sort(list);

		List<List<Integer>> result = new ArrayList<>();
		System.out.println("\nInput="+list + " Sum="+ B);
		_combinationSumSet2(list, list.size(), B, 0, result, new ArrayList<Integer>());
		return result;
	}
	private void _combinationSumSet2(List<Integer> list, int N, int sum, int idx, List<List<Integer>> result, List<Integer> bufferList){
		if(sum==0){
			List<Integer> temp = new ArrayList(bufferList);
			result.add(temp);
			return;
		}
		if(idx==N || sum<0){
			return;
		}
		for(int i=idx; i<N; i++){
			if(i>idx && list.get(i)==list.get(i-1)){
				continue;
			}
			// Backtracking
			bufferList.add(list.get(i));
			_combinationSumSet2(list, N, sum-list.get(i), i+1, result, bufferList);
			bufferList.remove(bufferList.size()-1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}