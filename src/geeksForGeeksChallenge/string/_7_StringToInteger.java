package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.util.HashMap;

public class _7_StringToInteger{
	@Test
	public void atoi(){
		
		Assert.assertEquals(123, atoi("123"));
		Assert.assertEquals(0, atoi("0"));
		Assert.assertEquals(-1, atoi("1a3"));
		Assert.assertEquals(2, atoi("002"));
		Assert.assertEquals(-21, atoi("-21"));
	}
	
	public int atoi(String str){
		if(str==null || str.length()==0) return -1;
		
		int val=0;
		
		for(int i=str.length()-1; i>=0; i--){
			char currChar=str.charAt(i);
			
			if(currChar=='-' && i==0){
				
				val=val*-1;
				break;
			}
			
			if(currChar<'0' || currChar>'9') return -1;
			
			
			val = (int)Math.pow(10, str.length()-i-1) * (currChar - '0') + val;
			
			
		}
		
		return val;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}