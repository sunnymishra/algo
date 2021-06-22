package geeksForGeeksChallenge.string;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class _2_LongestPalindrome{
	@Test
	public void longestPalin(){
		Assert.assertEquals(7, longestPalin("aabcbaabaaa"));
		Assert.assertEquals(5, longestPalin("abcbaabaaa"));
		Assert.assertEquals(5, longestPalin("abcbaaba"));
	}
	
	public int longestPalin(String S){
		if(S==null || S.length()==0 || S.trim().length()==0){
			System.out.println("");
			return 0;
		}
		
		int longestPalinLen=0;
		String longestPalin="";
		for(int i=0; i<S.length()-1; i++){
			int k=0;
			
			while(true){
				if((i-k)>=0 && (i+k)<S.length() && (S.charAt(i-k)==S.charAt(i+k))){
					if((k*2)+1 > longestPalinLen){
						longestPalin=S.substring(i-k, i+k+1);
					}
					longestPalinLen=Math.max(longestPalinLen, (k*2)+1);
				}else{
					break;
				}
				
				k++;
			}
			
			k=0;
			while(true){
				if((i-k)>=0 && (i+k+1)<S.length() && (S.charAt(i-k)==S.charAt(i+k+1))){
					if((k*2)+2 > longestPalinLen){
						longestPalin=S.substring(i-k, i+k+2);
					}
					longestPalinLen=Math.max(longestPalinLen, (k*2)+2);
				}else{
					break;
				}
				
				k++;
			}
			
		}
		System.out.println(longestPalin);
		return longestPalinLen;
	}
	
	private boolean isPalindrome(String s){
		int len=s.length();
		for(int i=0; i<len/2; i++){
			if(s.charAt(i)!=s.charAt(len-i-1)) return false;
		}
		return true;
	}
	
}