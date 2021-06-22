package geeksForGeeksChallenge.string;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class _1_FindPermutation{
	@Test
	public void findPermutation1(){
		List<String> result = find_permutation1("ABC");
		System.out.println("\nfindPermutation1="+ result);
		
		result = find_permutation1("ABSG");
		System.out.println("\nfindPermutation1="+result);
	}
	
	@Test
	public void findPermutation2(){
		List<String> result = find_permutation2("ABC");
		System.out.println("\nfindPermutation2="+ result);
		
		result = find_permutation2("ABSG");
		System.out.println("\n\nfindPermutation2="+result);
	}

	/**
		Below solution gives permutation but in Lexicographic order
	*/
	public List<String> find_permutation1(String S) {
        List<String> result = new ArrayList<>();
		char[] arr = S.toCharArray();
		Arrays.sort(arr);
		int fact=getFactorial(arr.length);
		result.add(new String(arr));
		for(int i=0; i<fact-1; i++){
			for(int j=arr.length-1; j>0; j--){
				if(arr[j]>arr[j-1]){
					for(int k=arr.length-1; k>(j-1); k--){
						if(arr[k]>arr[j-1]){
							swap(arr, k, j-1);
							reversePartial(arr, j, arr.length-1);
							break;
						}
					}
					break;
				}
			}
			result.add(new String(arr));
			
		}
		
		return result;
    }
	
	/**
		Below solution gives permutation but not in Lexicographic order
	*/
	public List<String> find_permutation2(String S) {
        List<String> result = new ArrayList<>();
		_find_permutation(S.toCharArray(), result, 0);
		return result;
    }

	private void _find_permutation(char[] arr, List<String> result, int idx) {
		if(idx==arr.length-1){
			result.add(new String(arr));
			return;
		}
		for(int i=idx; i<arr.length; i++){
			swap(arr, idx, i);
			_find_permutation(arr, result, idx+1);
			swap(arr, idx, i);
			
		}
		
	}
	
	
		
	private int getFactorial(int n){
		int result=1;
		for(int i=n; i>0; i--){
			result*=i;
		}
		return result;
	}
	private void swap(char[] arr, int idx1, int idx2){
		char temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	/*
		Given an array, reverse only a subarray of the original array. This is inclusive of both left and right indexes
	*/
	private void reversePartial(char[]arr, int idx1, int idx2){
		while(idx1<idx2){
			swap(arr, idx1, idx2);
			idx1++;
			idx2--;
		}
	}
	
	
	
	
}
	