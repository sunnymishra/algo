package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;


public class _6_MaxOfAllSubarrays{
	
	@Test
	public void max_of_subarrays(){
		int[] input= new int[]{3,4,2,1,5,1,1,2,3,1,7,3,8};
		Assert.assertEquals(new ArrayList(Arrays.asList(4,4,5,5,5,2,3,3,7,7,8)), max_of_subarrays(input, input.length, 3));
		
		input= new int[]{1,2,3,1,4,5,2,3,6};
		Assert.assertEquals(new ArrayList(Arrays.asList(3,3,4,5,5,5,6)), max_of_subarrays(input, input.length, 3));
		
		
	}
	
	// IMP: Store Index instead of the Value in the Deque, else Sliding window won't work.
	public ArrayList<Integer> max_of_subarrays(int arr[], int n, int k){
		ArrayList<Integer> result = new ArrayList<>();
		if(arr==null || arr.length!=n || n==0) return result;
		Deque<Integer> deque = new LinkedList<>();
		
		// Add initial Max elements in the Deque for size 'k'
		int i=0;
		for(; i<k; i++){
			while(!deque.isEmpty() && arr[deque.peekLast()]<=arr[i]){
				// This will ensure sorted order is maintained for 1st k elements in Deque
				deque.removeLast();
			}
			deque.addLast(i);
		}
		
		for(; i<n; i++){
			result.add(arr[deque.peekFirst()]);
			
			if(deque.peekFirst() <= (i-k)){
				deque.removeFirst();
			}
			while(!deque.isEmpty() && arr[deque.peekLast()]<=arr[i]){
				// This will ensure sorted order is maintained for 1st k elements in Deque
				deque.removeLast();
			}
			deque.addLast(i);
			
		}
		result.add(arr[deque.peekFirst()]);
		
		return result;
		
		
	}
	
	
	
	
	
	
	
	
}
	
	