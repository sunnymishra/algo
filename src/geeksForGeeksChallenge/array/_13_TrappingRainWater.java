package geeksForGeeksChallenge.array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Stack;
import java.util.Arrays;

public class _13_TrappingRainWater {
	@Test
	public void trappingWater1(){
		Assert.assertEquals(10, trappingWater1(new int[]{3,0,0,2,0,4}, 6));
		
		Assert.assertEquals(0, trappingWater1(new int[]{6,9,9}, 3));
		
		Assert.assertEquals(10, trappingWater1(new int[]{7,4,0,9}, 4));
		
		Assert.assertEquals(6, trappingWater1(new int[]{1,4,2,1,3,5,1}, 7));
	}
	
		@Test
	public void trappingWater2(){
		Assert.assertEquals(10, trappingWater2(new int[]{3,0,0,2,0,4}, 6));
		
		Assert.assertEquals(0, trappingWater2(new int[]{6,9,9}, 3));
		
		Assert.assertEquals(10, trappingWater2(new int[]{7,4,0,9}, 4));
		
		Assert.assertEquals(6, trappingWater2(new int[]{1,4,2,1,3,5,1}, 7));
	}
	
	public int trappingWater1(int arr[], int n){
		if(arr==null || arr.length!=n) return -1;
		
		Stack<Integer> stack = new Stack<>();
		
		int tot=0;
		for(int i=0; i<n; i++){
			while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
				//Step 1: Remove prev elem and get its height
				int height=arr[stack.pop()];
				
				//Step 2: Get height and index of prev to prev elem in the stack.
				if(!stack.isEmpty()){
					int prevToPrevElemIdx = stack.peek();
					int prevToPrevElemHeight = arr[prevToPrevElemIdx];
					int distance = i - prevToPrevElemIdx - 1;
					
					// Step 3: Calculate cumulative water stored between current elem and prev to prev(all the way back to the old elem in stack whose height is greater than current elem)
					tot += (Math.min(arr[i], prevToPrevElemHeight) - height ) * distance;
				}
			}
			// Step 4: Store current elem in stack
			stack.push(i);
		}
		return tot;
	}
	
	public int trappingWater2(int arr[], int n){
		if(arr==null || arr.length!=n) return -1;
		
		
		int tot=0;
		int[] maxHeightOnRightSide=new int[n];
		int rightIdx=n;
		
		maxHeightOnRightSide[n-1]=0;
		for(int i=n-2; i>=0; i--){
			maxHeightOnRightSide[i]=Math.max(maxHeightOnRightSide[i+1], arr[i+1]);
		}
		
		int maxSoFar=arr[0];
		
		int[] maxHeightOnLeftSide=new int[n];
		
		for(int i=1; i<n; i++){
			maxSoFar = Math.max(maxSoFar, arr[i-1]);
			int currentWaterStorage = Math.min(maxSoFar, maxHeightOnRightSide[i]) - arr[i];
			tot += currentWaterStorage<=0 ? 0 : currentWaterStorage;
			maxHeightOnLeftSide[i]=maxSoFar;
		}
		return tot;
	}
	
	
}