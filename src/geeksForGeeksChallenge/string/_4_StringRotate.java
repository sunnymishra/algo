package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;

public class _4_StringRotate{
	@Test
	public void isRotated(){
		Assert.assertTrue(isRotated("b", "b"));
		
		Assert.assertTrue(isRotated("ab", "ab"));
		Assert.assertFalse(isRotated("ab", "xy"));
		
		Assert.assertTrue(isRotated("amazon", "azonam"));
		
		Assert.assertFalse(isRotated("geek", "geek"));
	}
	
	public boolean isRotated(String str1, String str2){
		if(str1==null || str2==null || str1.length()==0 || str2.length()==0 || str1.length() != str2.length()){
			return false;
		}
		if(str1.length()<3){
			if(str1.equals(str2))return true;
			else return false;
		}
		
		int i=0;
		while(i<str2.length()-2 && str1.charAt(i)==str2.charAt(i+2)){
			i++;
		}
		if(i==str1.length()-2 && str1.charAt(str1.length()-2)==str2.charAt(0) && str1.charAt(str1.length()-1)==str2.charAt(1)){
			return true;
		}
		// "amazon", "azonam"
		i=0;
		while(i<str1.length()-2 && str1.charAt(i+2)==str2.charAt(i)){
			i++;
		}
		if(i==str1.length()-2 && str1.charAt(0)==str2.charAt(str2.length()-2) && str1.charAt(1)==str2.charAt(str2.length()-1)){
			return true;
		}
		return false;
		
	}
	
	
	
	
}