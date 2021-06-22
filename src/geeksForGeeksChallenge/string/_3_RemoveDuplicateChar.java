package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;

public class _3_RemoveDuplicateChar{
	@Test
	public void trimDuplicate(){
		Assert.assertEquals("", trimDuplicate(""));
		Assert.assertEquals("a", trimDuplicate("a"));
		Assert.assertEquals("ab", trimDuplicate("ab"));
		Assert.assertEquals("b", trimDuplicate("aab"));
	}
	@Test
	public void remove(){
		Assert.assertEquals("gksforgk", remove("geeksforgeek"));
		Assert.assertEquals("acac", remove("acaaabbbacdddd"));
		Assert.assertEquals("", remove("abccbccba"));
	}
	
	public String remove(String s){
		if(s==null || s.length()==0) return "";
		String result = _remove(s, (char)0); // NOTE: (char)0 or '\0' means Empty char literal.
		return result.trim();
	}
	
	private String _remove(String str, char prevChar){
		String s = trimDuplicate(str);
		
		char currentChar=s.length()>0 ? s.charAt(0) : '\0';
		String rightStr = (s.length()>1) ? _remove(s.substring(1, s.length()), currentChar) : "";
		
		s = Character.toString(currentChar) + rightStr;
		
		return (s.length()>0 && prevChar==s.charAt(0)) ? s : trimDuplicate(s);
		
	}
	
	private String trimDuplicate(String s){
		int i=0;
		for(; i<s.length(); i++){
			boolean isDuplicateChar=false;
			char c = s.charAt(i);
			while(i < s.length()-1 && c==s.charAt(i+1)){
				i++;
				isDuplicateChar=true;
			}
			if(!isDuplicateChar && (i == s.length()-1 || (i < s.length()-1 && c!=s.charAt(i+1)))){
				// i==n-1 if Last char is Not duplicate
				// i==n if Last char is Duplicate
				break;
			}
		}
		if(i != 0){
			
			s = (i < s.length()) ? s.substring(i, s.length()) : "";
		}
		return s;
	}
	
	
	
	
	
	
	
	
	//abccbccba, 0 | abbccba, 1 | accba, 0 | aba, 0 | 
}