package recursion;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class _6_Subset{
	@Test
	public void testSubsetOfSizeK(){
		subsetOfSizeK(Arrays.asList(1,2,3,4,5), 3);
	}
	
	/*
		This is a classic high school Combination problem. Choose a set of size k from a larger set of size n.
		The math solution for this Combination problem is nC2 or n! / ((n-r)! x r! )
		
		Thus in every level of the Recursion Stack we pick 1 element from the input array and put in the toBeUsed array. And then go to the next level of the Recursion stack. We keep doing this until the size of toBeUsed array becomes equal to Desired setSize. At this point we simply display the content of toBeUsed. Then we have to return to the previous Stack level and remove the last element added to the tobeUsed Array. We keep doing this until the FOR loop reaches the end and then we return from the current Recursion Stack level to the previous Stack level.		
		
		Time complexity T(n) = O(n x nC2) , here n is added to above math solution of nC2, since the program spends n extra time in the FOR loop inside each Stack level.
		Space complexity = O(n) since we are using recursion and the max depth of the Recursion tree = n, where n is size of input
		
	*/
	public void subsetOfSizeK(List<Integer> input, int setSize){
		if(input==null || input.size()==0 || setSize <=0 || setSize > input.size()){
			return;
		}
		_subsetOfSizeK(input, new ArrayList<Integer>(), setSize, 0);
	}
	private void _subsetOfSizeK(List<Integer> input, List<Integer> toBeUsed, int setSize, int idx){
		if(setSize==toBeUsed.size()){
			List<Integer> set = new ArrayList<>();
			for(int j=0; j<toBeUsed.size(); j++){
				set.add(toBeUsed.get(j));
			}
			System.out.println(set);
			return;
		}
		for(int i=idx; i<input.size(); i++){
			toBeUsed.add(input.get(i));
			_subsetOfSizeK(input, toBeUsed, setSize, (i+1));
			toBeUsed.remove(toBeUsed.size()-1);
		}
	}
}