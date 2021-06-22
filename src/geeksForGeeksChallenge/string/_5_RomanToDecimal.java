package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;

public class _5_RomanToDecimal{
	@Test
	public void romanToDecimal(){
		Assert.assertEquals(1904 ,romanToDecimal("MCMIV"));
		Assert.assertEquals(11 ,romanToDecimal("XI"));
		Assert.assertEquals(1 ,romanToDecimal("I"));
		Assert.assertEquals(200 ,romanToDecimal("CC"));
	}
	
	public int romanToDecimal(String str){
		int val=0;
		Map<Character, Integer> map = new HashMap(){{
			put('I', 1);
			put('V', 5);
			put('X', 10);
			put('L', 50);
			put('C', 100);
			put('D', 500);
			put('M', 1000);
		}};
		int total=map.get(str.charAt(str.length()-1));
		if(str.length()==1) return total;
		
		for(int i=str.length()-2; i>=0; i--){
			int currCharVal = map.get(str.charAt(i));
			int nextCharVal = map.get(str.charAt(i+1));
			total = currCharVal < nextCharVal ? total - currCharVal :  total + currCharVal;
			
			
			
		}
		return total;
	
	
	
	
	}
	
	
}
