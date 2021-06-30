package geeksForGeeksChallenge.heap;

import org.junit.*;
import java.util.*;


/**
	Problem link: https://practice.geeksforgeeks.org/contest/coding-try-outs-amazon/problems
	
	Method expects input to have a String containing only Lower case english alphabets. Method returns TRUE if it is possible to rearrange the chars in such a way that no two adjacent chars are same/duplicate/repeated. Returns false otherwise.
*/
public class _3_RearrangeRepeatingChars{
	@Test
	public void isRearrangePossible(){
		Assert.assertTrue(isRearrangePossible("geeksforgeeks"));
		Assert.assertTrue(isRearrangePossible("bbbabaaacd"));
		Assert.assertTrue(isRearrangePossible("abababb"));
		Assert.assertFalse(isRearrangePossible("bb"));
		Assert.assertFalse(isRearrangePossible("abababbb"));
		
	}
	
	/**
		Problem link: https://practice.geeksforgeeks.org/contest/coding-try-outs-amazon/problems
		
		Method expects input to have a String containing only Lower case english alphabets. Method returns TRUE if it is possible to rearrange the chars in such a way that no two adjacent chars are same/duplicate/repeated. Returns false otherwise.
		
		NOTE: We can solve this with Simple Array instead of Heap. See 1 more solution in this file.
	*/
	public boolean isRearrangePossible2(String str){
		if(str==null || str.length()==0) return false;
		
		int[] charCountArr=new int[26];
		
		// Step 1: 	We are getting the Char count and storing in an Array.
		for(int i=0; i<str.length();i++){
			char currChar = str.charAt(i);
			int charIdx=currChar - 'a';
			
			charCountArr[charIdx]++;
		}
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		// Step 2: We are storing the Char counts in a MaxHeap.
		for(int i=0; i<charCountArr.length; i++){
			int count = charCountArr[i];
			if(count>0){
				maxHeap.add(count);
			}
		}
		
		// Step 3: We cancel each Char count with it's below char count. If in the end some count remains to be cancelled, this means Adjacent cells can have duplicates.
		int carryOverCount=maxHeap.remove();
		while(!maxHeap.isEmpty()){
			carryOverCount= Math.abs(carryOverCount - maxHeap.remove());
		}
		return carryOverCount<=1;
		
	}
	
	/**
		Problem link: https://practice.geeksforgeeks.org/contest/coding-try-outs-amazon/problems
		
		Method expects input to have a String containing only Lower case english alphabets. Method returns TRUE if it is possible to rearrange the chars in such a way that no two adjacent chars are same/duplicate/repeated. Returns false otherwise.
		
		NOTE: We can solve this with Simple Array instead of Heap. See 1 more solution in this file.
	*/
	public boolean isRearrangePossible(String str){
		if(str==null || str.length()==0) return false;
		
		int[] charCountArr=new int[26];
		
		// Step 1: 	We are getting the Char count and storing in an Array.
		for(int i=0; i<str.length();i++){
			char currChar = str.charAt(i);
			int charIdx=currChar - 'a';
			
			charCountArr[charIdx]++;
		}
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		
		// Step 2: We cancel each Char count with it's below char count. If in the end some count remains to be cancelled, this means Adjacent cells can have duplicates.
		int carryOverCount=charCountArr[0];
		for(int i=1; i<str.length();i++){
			carryOverCount= Math.abs(carryOverCount - charCountArr[i]);
		}
		return carryOverCount<=1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	