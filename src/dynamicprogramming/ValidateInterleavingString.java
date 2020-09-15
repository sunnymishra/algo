package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class ValidateInterleavingString{
	@Test
	public void validateInterleavingString1(){
		Assert.assertTrue(validateInterleavingString1("GTAA", "ATC", "GATTACA"));
		Assert.assertTrue(validateInterleavingString1("GTAA", "ATC", "GTATAAC"));
		Assert.assertFalse(validateInterleavingString1("BTAA", "ATC", "GTATAAC"));
		Assert.assertFalse(validateInterleavingString1("GTAA", "ATC", "GATACTA"));

	}
	/**
		Problem:
		Design an algorithm that takes as input strings sl, s2 and a Test string t, and determines if t is an interleaving of s1 and s2 or not. During interleaving the order of characters in the string s1 and s2 should be maintained in the Test string t.
		Example, if Input strings s1= "gtaa" and s2="atc", then "gattaca" is an interleaved string.
		However, "gatacta" is not an interleaved string.
	
	*/
	public boolean validateInterleavingString1(String str1, String str2, String testStr){
		if(str1==null || str1.isEmpty() || str2==null || str2.isEmpty() || testStr==null || testStr.isEmpty())
			return false;
		
		//boolean[][]cache=new boolean[str1.length()][str2.length()];
		return _validateInterleavingString1(str1, str1.length()-1, str2, str2.length()-1, testStr, testStr.length()-1);
		
	}
 
	private boolean _validateInterleavingString1(String str1, int str1Idx, String str2, int str2Idx, String testStr, int testStrIdx){
		if(testStrIdx<0){
			if(str1Idx<0 && str2Idx<0)
				return true;
			else
				return false;
		}
		
		if((str1Idx<0 && str2Idx<0)
			|| (str1Idx<0 && str2Idx>=0 && testStr.charAt(testStrIdx)!=str2.charAt(str2Idx))
			|| (str2Idx<0 && str1Idx>=0 && testStr.charAt(testStrIdx)!=str1.charAt(str1Idx))
			|| (str1Idx>=0 && testStr.charAt(testStrIdx)!=str1.charAt(str1Idx) && str2Idx>=0 && testStr.charAt(testStrIdx)!=str2.charAt(str2Idx))){
			return false;
		}
		boolean str1Check = _validateInterleavingString1(str1, str1Idx-1, str2, str2Idx, testStr, testStrIdx-1);
		boolean str2Check = _validateInterleavingString1(str1,  str1Idx, str2, str2Idx-1, testStr, testStrIdx-1);
		return str1Check || str2Check;
	}
	
}