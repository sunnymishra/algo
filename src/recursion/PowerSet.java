package recursion;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

public class PowerSet{
	@Test
	public void testPowerSet() {
		List<Integer> inputSet = new ArrayList(Arrays.asList(new Integer[] {1,2,3}));
		System.out.println("\nInput: "+ inputSet + "\n\nOutput: ");
		generatePowerSet1(inputSet);
	}

	/*
		Problem:
		PowerSet is basic high-school math topic. A PowerSet contains all subset of a given Set of elements. Example:
		Input Array:[1,2,3] Output PowerSet: [[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]
		
		The total no. of Subsets in the PowerSet = 2^n where n is no. of input elements
		Let's prove this with some Math:
		Let n = no. of elements
		Let S = 1 of the many subsets possible
		Let P = PowerSet , then P(S)=2^n
		Proof:
		If S is a Set, then |S|=n elements. Meaning a Set can have at max n elements
		Or, S = {y1, y2, y3, ..... yn} , here yi = Any element from the input elements and 1 ≤ i ≤ n
		Important Note: in a set S , yi can be either present or not present i.e 0 or 1 , or γi E {0, 1},
		i.e. If γi = 1, the i-th element of input S is present in the subset; else if γi = 0, the i-th element of input S is absent in the subset
		This means for every element in the input Set i we can have at max 2 values i.e. 0 or 1 , meaning present or not present. Which means every element can have at max 2 values.
		Max enumeration(where n=5)= 2 x 2 x 2 x 2 x 2 = 2^5
		Thus, PowerSet(S)=2^n , where n is no. of elements in the input set/array
		Reference: https://en.wikipedia.org/wiki/Power_set
		
		Solution logic:
		Since above Math explained that to create a powerset of all the sets, we have to once consider an element and once ignore the element. Do this for all the elements. 
		This clearly looks like a recurrence relation, where in every stack of the Recursion we can repeat above statement.
		The easiest way to visualize a Recurrence relation to write a Recursion algorithm is by writing the Recursion tree using Pen and Paper. 
		For an input Array {1,2,3} , here is how the Recursion tree would look like:
												1,2,3
												  |
								--------------------------------------------------
							  +{1}	 											-{1}
					------------------------- 						---------------------------------
				+{1}+{2} 		 		+{1}-{2} 				-{1}+{2}						-{1}-{2} 
					|						|						|							|			
				--------		  	-----------------			-------------				----------------
				|		|		  	|				|			|			|				|				|
		+{1}+{2}+{3}  +{1}+{2}-{3}  +{1}-{2}+{3}  +{1}-{2}-{3} -{1}+{2}+{3} -{1}+{2}-{3} -{1}-{2}+{3} -{1}-{2}-{3}
		=[1,2,3]		=[1,2]		 =[1,3]			=[1]		=[2,3]		 =[2]			=[3]		=[]

		PowerSet=[[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3][]]			
							
		Considering above Math, 
		The time spent in the recurrent = 2^n
		Also the IF condition in the below code is the Leaf nodes in the above tree. You will notice that it takes O(n) time to collect the data of size n for each Leaf node. Since the Time spent to get all the leaf node= 2^n.
		Therefore, Time complexity T(n)=(n x 2^n) which is exponential complexity where n=no. of input elements.
		Space complexity = O(n), in above Tree n is no. of levels in the tree i.e. 3 which is also the no. of input elements.
	*/
	public void generatePowerSet1(List<Integer> inputSet) {
			
		_generatePowerSet1(inputSet, new ArrayList<Integer>(), 0);
		
	}
	private void _generatePowerSet1(List<Integer> inputSet, List<Integer> selectedElements, int currIdx) {
		if(currIdx==inputSet.size()){
			System.out.println(selectedElements);
			return;
		}
		
		// Adding current element and calling Recursion
		selectedElements.add(inputSet.get(currIdx));
		_generatePowerSet1(inputSet, selectedElements, currIdx+1);

		// Removing element and calling Recursion
		selectedElements.remove(selectedElements.size()-1);
		_generatePowerSet1(inputSet, selectedElements, currIdx+1);
	}
	
	
	/**
		This function is the Iterative solution to the same PowerSet problem. Since it is not using Recursion, therefore,
		Space Complexity will be a simple O(1)
		Time complexity is same as Recursive solution = O(2^n)
		
		Reference to the logic: https://www.geeksforgeeks.org/power-set/
	
	*/
	public void generatePowerSet2(List<Integer> inputSet) {
		int inputSize = inputSet.size();
		int outputSize = (int)Math.pow(2, inputSize); // We know that total solution Set size = 2 ^ n
		
		for(int i=0; i<outputSize; i++){
			for(int j=0; j<inputSize; j++){
				if(isBitSet(i, j)){
					System.out.print(inputSet.get(j)+",");
				}
			}
			System.out.println("");
		}
	}
	
	/**
		Bitwise operation to find whether for a given input integer, it's bit at an index i is set to 1 or 0
		If bit at desired bitIndex is set to 1 then return true else false
	*/
	private boolean isBitSet(int input, int bitIndex){
		int bitAtIndex = input & (1 << bitIndex);	
		return bitAtIndex > 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}