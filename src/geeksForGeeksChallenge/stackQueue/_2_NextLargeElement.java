package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;
import java.util.Stack;
import java.util.Arrays;


public class _2_NextLargeElement{
	
	@Test
	public void nextLargerElement(){
		long[] result=nextLargerElement(new long[]{1,3,2,4}, 4);
		System.out.println(" ExpectedResult={3, 4, 4, -1}");
		Assert.assertArrayEquals(new long[]{3, 4, 4, -1}, result);
		
		result=nextLargerElement(new long[]{6, 8, 0, 1, 3}, 5);
		System.out.println(" ExpectedResult={8, -1, 1, 3, -1}");
		Assert.assertArrayEquals(new long[]{8, -1, 1, 3, -1}, result);

	}
	// 6, 8, 0, 1, 3
	public static long[] nextLargerElement(long[] arr, int n){ 
		if(arr==null || arr.length==0 || arr.length!=n) return new long[]{-1};
		
		long[] resultArr=new long[n];
		if(n==1){
			resultArr[0]=-1;
			return resultArr;
		}
		Stack<Integer> stack=new Stack<>();
		stack.push(0);
		
		int i=1;
		for(;i<n;i++){
			if(arr[i]<=arr[i-1]){
				stack.push(i);
			}else{
				while(!stack.isEmpty() && arr[stack.peek()]<=arr[i]){
					resultArr[stack.pop()]=arr[i];
				}
				stack.push(i);
			}
		}
		while(!stack.isEmpty()){
			resultArr[stack.pop()]=-1;
		}

		//System.out.print("OriginalArr="+Arrays.toString(arr) + " ActualResult="+Arrays.toString(resultArr));
		return resultArr;




	}
	
	
	
	
	
}