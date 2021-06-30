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
		Assert.assertEquals(3, optimalKeys(3));
		
		Assert.assertEquals(4, optimalKeys(4));
		
		Assert.assertEquals(9, optimalKeys(7));
		
		for (int N = 1; N <= 20; N++)
            System.out.println("Maximum Number of A's with "+N+" keystrokes is " + optimalKeys(N));
	}
	
	static int optimalKeys(int N){
		return _optimalKeys(N, 0, 0, Keys.PRINT, Keys.PRINT);

	}
	static int _optimalKeys(int n, int charLen, int copyCharLen, Keys prevKey, Keys currKey){
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
			result=Math.max(result, _optimalKeys((n- currKey.getNoOfKeys()), charLen, copyCharLen, currKey, newKey));
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}