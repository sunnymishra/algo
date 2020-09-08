package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;

public class Fibonacci{
	@Test
	public void fibonacci1(){
		Assert.assertEquals(0, fibonacci1(0));
		Assert.assertEquals(1, fibonacci1(1));
		Assert.assertEquals(1, fibonacci1(2));
		Assert.assertEquals(2, fibonacci1(3));
		Assert.assertEquals(3, fibonacci1(4));
		Assert.assertEquals(5, fibonacci1(5));
		Assert.assertEquals(8, fibonacci1(6));
		Assert.assertEquals(13, fibonacci1(7));
	}
	@Test
	public void fibonacci2(){
		Assert.assertEquals(0, fibonacci2(0));
		Assert.assertEquals(1, fibonacci2(1));
		Assert.assertEquals(1, fibonacci2(2));
		Assert.assertEquals(2, fibonacci2(3));
		Assert.assertEquals(3, fibonacci2(4));
		Assert.assertEquals(5, fibonacci2(5));
		Assert.assertEquals(8, fibonacci2(6));
		Assert.assertEquals(13, fibonacci2(7));
	}
	@Test
	public void fibonacci3(){
		Assert.assertEquals(0, fibonacci3(0));
		Assert.assertEquals(1, fibonacci3(1));
		Assert.assertEquals(1, fibonacci3(2));
		Assert.assertEquals(2, fibonacci3(3));
		Assert.assertEquals(3, fibonacci3(4));
		Assert.assertEquals(5, fibonacci3(5));
		Assert.assertEquals(8, fibonacci3(6));
		Assert.assertEquals(13, fibonacci3(7));
	}
	
	Map<Integer, Integer> cache = new HashMap<>();
	
	/**
		In mid-school Math we have studied that Fibonacci series is:
		0,1,1,2,3,8,13..... infinity. If you look at this series then Starting 2 no. are fixed to be 0 & 1
		and every other no. has the formula -> n = n-1 + n-2 
		i.e Value At index n = value at index (n-1) + value at index (n-2)
		
		Dynamic programming is 2 step process:
		Step 1: Find a "Recurring sub-problem" or find a "Recurrence relation": 
		Using above logic, we quickly realize that we can use this Math formula into a Recursion tree, where 
		at any particular Recursion stack level n, the Fibonacci value = Fibo(n-1) + Fibo(n-2). Since for every level of the Recursion stack, this formula remains the same.
		Therefore Recurrence relation: Fibo(n) = Fibo(n-1) + Fibo(n-2)
		
		On Pen and Paper this Recursion tree will have 2 to the Power of n (2^n) nodes. 
		Time complexity= O(2^n)
		Space complexity= O(n) , due to Recursion stacks also occupying memory
		Note: This is a Tail recursion and can be simply converted into a Iterative solution by using FOR loop instead of Recursion stack. In that scenario
		Time complexity= O(2^n)
		Space complexity= O(1), since no Recursion stack, therefore constant Space.
		Reference: https://medium.com/@syedtousifahmed/fibonacci-iterative-vs-recursive-5182d7783055
		
		Here, using Iterative solution, we have reduced Space complexity, but Time complexity is still an expensive Exponential solution. Therefore we need further optimization, which is next step of Dynamic programming. 
		
		Step 2: Memoization(Store in Cache) the intermediate result: 
		If you draw this into a Recursion tree using Pen and Paper, you will notice that a lot of Nodes in the Tree are repeating across branches. To optimize this part, we start storing the Node's values in some Cache (HashMap). This means at any Recursion stack level, before going to the next Recursion stack level, we first check if the value of (n) already exists in the Cache, then get it from Cache, if not found in Cache, then go to the next Recursion stack level. Once a value is found at any Recursion stack level, store that into the Cache.
		
		Using Memoization step, we have reduced the Time complexity from O(2^n) to O(n). If you can't understang why now suddenly we have reduced Time complexity to O(n), then draw the Recursion tree of this solution and count the no. of Nodes you are visiting. You will notice that now we are only vising n nodes, because we are ignoring all the duplicate nodes which are already visited, thanks to Memoization i.e. storing the intermediate nodes or already visited Nodes values in a Cache or a HashMap.
		
		Time complexity=O(n)
		Space complexity= O(n), In the below Recursive solution
		Space complexity= O(1), If I convert the below Recursive solution to Iterative solution using FOR loop
	*/
	public int fibonacci1(int length){
		if(length==1) return 1;
		if(length==0) return 0;
		
		if(cache.containsKey(length)){
			return cache.get(length);
		}
		
		int sum = fibonacci1(length-1) + fibonacci1(length-2);
		cache.put(length, sum);
		return sum;
	}
	
	/**
		This algorithm uses the same fundamentals of the above Recursive approach fibonacci1(). However this time we are using an Iterative solution i.e. a FOR loop. This way we can save on the extra Space occupied by the Recursion stacks O(n) in the above solution.
		
		NOTE: 
		IN the Recursive solution we did a TOP-Down approach i.e. we started from n and reached 0
		IN the Iterative solution we did a Bottom-Up approach i.e. we started from 0 and reached n
		
		Time complexity=O(n), since for loop runs for n times
		Space complexity= O(n) , since HashMap will store at max n values
	*/
	public int fibonacci2(int length){
		if(length<2) return length;

		cache.clear();
		cache.put(0, 0);
		cache.put(1, 1);
		
		int value = 0;
		for(int i=2; i<=length; i++){
			value = cache.get(i-1) + cache.get(i-2);
			cache.put(i, value); // Memoization step: always store intermediate result in a cache
		}

		return cache.get(length);
	}
	
	/**
		This algorithm uses the same fundamentals of the above Iterative approach of fibonacci2(). We are doing further optimization inside the FOR loop itself. If you notice the fibonacci2() method above, at any point inside the FOR loop, at max we are fetching from the Cache (HashMap), the last 2 values only. This means instead of storing all the previous n values in the HashMap, if we could just keep 2 static int variables, then also we could solve the problem. This way we can save on the extra Space occupied by the Cache O(n) in the above solution.
		
		NOTE: 
		IN the Recursive solution fibonacci1() we did a TOP-Down approach i.e. we started from n and reached 0
		IN the Iterative solution fibonacci2() & fibonacci3() we did a Bottom-Up approach i.e. we started from 0 and reached n
		
		Time complexity=O(n), since the FOR loop runs for n times
		Space complexity= O(1), Constant space occupied, since we are not using a HashMap to store n values, instead we are only using 2 variables int first and int second.
	*/
	public int fibonacci3(int length){
		if(length<2) return length;

		int first=0;
		int second=1;
		
		int currentVal = 0;
		for(int i=2; i<=length; i++){
			currentVal = first + second;
			first=second;
			second=currentVal;
		}

		return currentVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}