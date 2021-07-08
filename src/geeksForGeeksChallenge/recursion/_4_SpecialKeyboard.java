package geeksForGeeksChallenge.recursion;

import java.util.*;
import org.junit.*;

enum Keys{
	PRINT(1), COPYALL(2), PASTE(1);
	
	int noOfKeys;
	
	Keys(int val){
		noOfKeys=val;
	}
	int getNoOfKeys(){return noOfKeys;}
}
public class _4_SpecialKeyboard{
	@Test
	public void optimalKeys(){
		Assert.assertEquals(3, optimalKeys1(3));
		
		Assert.assertEquals(4, optimalKeys1(4));
		
		Assert.assertEquals(9, optimalKeys1(7));
		System.out.println("\n");
		for (int N = 1; N <= 20; N++)
            System.out.println("Maximum Number of A's with "+N+" keystrokes is " + optimalKeys1(N));
	}
	
	@Test
	public void optimalKeys2(){
		Assert.assertEquals(3, optimalKeys2(3));
		
		Assert.assertEquals(4, optimalKeys2(4));
		
		Assert.assertEquals(9, optimalKeys2(7));
		System.out.println("\n");
		for (int N = 1; N <= 20; N++)
            System.out.println("Maximum Number of A's with "+N+" keystrokes is " + optimalKeys2(N));
	}
	
	/**
		Solution 1: Not very optimal. Here we are using brute force recursion. 
		Space complexity = O(n) where n is no. of keyboard clicks given as Input.
		Time complexity = O(4 ^ n) where n is height of tree and n.
		
		See Solution 2 at bottom using Dynamic programming
	
	**/
	static int optimalKeys1(int N){
		if(N<=0) return 0;
		return _optimalKeys1(N, 0, 0, Keys.PRINT, Keys.PRINT);

	}
	static int _optimalKeys1(int n, int charLen, int copyCharLen, Keys prevKey, Keys currKey){
		if(n<=0 || (currKey==Keys.PASTE && prevKey==Keys.PRINT)){
			return charLen;
		}

		switch (currKey){
			case PRINT: 
				charLen+=1;
				break;
			case COPYALL: 
				copyCharLen=charLen;
				charLen+=0;
				break;
			case PASTE: charLen=charLen+copyCharLen;
			default:
		}
		
		int result=charLen;
		for(Keys newKey: Keys.values()){
			result=Math.max(result, _optimalKeys1((n- currKey.getNoOfKeys()), charLen, copyCharLen, currKey, newKey));
		}
		return result;
	}
	
	
	/**
		Solution 2: optimal Dynamic programming.
		Here is the intuition: 
		For 1 <= N <= 6, MaxChar = N. You can do this on paper and pen and observe.
		For N >= 7 : 
			Given N, we have to find a Breakingpoint K where after K, we only do Ctrl+A,Ctrl+C,Ctrl+V or only Ctrl+V.
			Here, let's call No. of such Ctrl+V clicks = x.
			
			The relation between n , k , x is this:  
				N = k + 2 + x  <- Equation1
				
				Explanation: k is the no. of keys already pressed to reach kth index and the result of kth index cannot be changed. After kth index we are only going to press Ctrl+A,Ctrl+C once therefore 2. Then after that we are only going to press x no. of keys i.e. x no. of Ctrl+V.
			
			The relation between f(N), f(k), x is this:
				f(N) = f(k) + f(k)*x
				f(N) = f(k) * (1 + x) <- Equation2
				
				Explanation: After Kth index, we are going to press Ctrl+V 'x' no. of times. So Whatever is the charLength at Kth index, let's call it f(k), it will be multiplied by 'x' no. of times. So charLength at Nth index will be sum of charLength at Kth index and charLength of hitting Ctrl+V 'x' no. of times.
			
			FinalEquation: We will substitute Equation1 into Equation2:
				N = k + 2 + x  			<- Equation1
				f(N) = f(k) * (1 + x) 	<- Equation2
				
				f(N) = f(k) * (N-k-1) 	<- FinalEquation
				
			Boundaries of K is below:
				1 <= k <= N-3			<- Bounds of K
				
				Explanation: Since K is the breakingPoint after which we only get to see Ctrl+A,Ctrl+C,Ctrl+V, therefore the last 3 keystrokes of N are already occupied by Ctrl+A,Ctrl+C,Ctrl+V. Hence K can never cross N-3

			We will use this FinalEquation and Bounds of K in below Algorithm for every value of N.
		
		Time complexity = O(n ^ 2)
		Space complexity = O(n)
	
	**/
	static int optimalKeys2(int N){
		if(N<=0) return 0;
		int[] dp=new int[N+1];
		
		dp[1]=1;
		if(N>1) dp[2]=2;
		if(N>2) dp[3]=3;
		if(N>3) dp[4]=4;
		if(N>4) dp[5]=5;
		if(N>5) dp[6]=6;
		
		for(int n=7; n<=N; n++){
			int maxChars=0;
			for(int k=n-3; k>=1; k--){
				int charsAtK=dp[k] * (n-k-1);
				maxChars=Math.max(maxChars, charsAtK);
				
			}
			dp[n]=maxChars;
		}
		return dp[N];
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}