package recursion;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class _7_ParanthesisMatch{
	@Test
	public void paranthesisMatch(){
		
		paranthesisMatch(2);
	}
	
	/**
		Problem: 
		Given an input n, print all possible combinations where total no. of paranthesis pairs equals n. 
		Example: if n=2 Then solution set will look like -> ()() , (())
		Reference: https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
		
		Solution logic:
		The algorithm uses Recursion tree to fill in the required Left paranthesis and the Right paranthesis at the correct index in the result List. At each Recursion stack level the program has a choice to either set Left paranthesis or right paranthesis. In such scenario the IF condition states that if the Left paranthesis count is less than total Paranthesis Pairs to be created, then go ahead and add Left Paranthesis in the Result list. Also if the Right paranthesis count is less than the Left paranthesis count then go ahead and add Right Paranthesis in the Result list.
		
		Time Complexity: O(2^n) i.e. exponential time complexity.
		For every index in the Recursion stack level, there can be two choices ‘(‘ or ‘)’. So it can be said that the upperbound of time complexity is O(2^n), where n is the no. of pairs of paranthesis to be created.
		Space Complexity: O(n) i.e. the no. of levels of Recursion stack levels. Visualize a Tree to see this in action.
	*/
	public void paranthesisMatch(int paranthesisPairCount){
		// Collections.nCopies() adds n elements in the List> Here I am setting dummy value '|' in the list.
		// If we don't like using this Fixed size LIST, then better use an Array of size 2*paranthesisPairCount
		List<Character> result = new ArrayList<>(Collections.nCopies(2*paranthesisPairCount, '|'));
		paranthesisMatch(paranthesisPairCount, 0, 0, 0, result);
	}
	private void paranthesisMatch(int paranthesisPairCount, int leftParanthesisCount, int rightParanthesisCount, int 		idx, List<Character> result){
		if((leftParanthesisCount==paranthesisPairCount && rightParanthesisCount==paranthesisPairCount)){
			System.out.println(result);
			return;
		}
		
		if(leftParanthesisCount<paranthesisPairCount){
			result.set(idx, '('); // This is imp. Instead of list.add() we are using list.set() to set at required index.
			paranthesisMatch(paranthesisPairCount, leftParanthesisCount+1, rightParanthesisCount, idx+1, result);
		}
		
		if(rightParanthesisCount<leftParanthesisCount){
			result.set(idx, ')');// Instead of list.add() we are using list.set() to set at required index.
			paranthesisMatch(paranthesisPairCount, leftParanthesisCount, rightParanthesisCount+1, idx+1, result);
		}
		
	}
	

	
}