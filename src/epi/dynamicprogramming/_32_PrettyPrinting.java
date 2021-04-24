package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;
import java.lang.Math;

public class _32_PrettyPrinting {
	@Test
	public void printPretty1(){
		Assert.assertEquals(8, printPretty1(5, "a b c d"));
		Assert.assertEquals(4, printPretty1(3, "aaa b ccc"));
		Assert.assertEquals(5, printPretty1(3, "aa b ccc"));
		
		Assert.assertEquals(42, printPretty1(11, "aaa bbb c d ee ff ggggggg"));
		Assert.assertEquals(82, printPretty1(36, "I have inserted a large number of new examples from the papers for the Mathematical Tripos during the last twenty years, which should be useful to Cambridge students."));
	}
	
	public int printPretty1(int lineLength, String sentence){
		if(sentence==null || sentence.isEmpty()){
			return 0;
		}
		String[] words=sentence.split(" ");
		int dirtynessRank=_printPretty1(lineLength, words, 0);
		return dirtynessRank;
	}
	public int _printPretty1(int lineLength, String[] words, int currentIdx){
		if(currentIdx>=words.length){
			return 0;
		}
		
		int dirtynessRank=Integer.MAX_VALUE;
		for(int i=0; i<lineLength; ){
			if(currentIdx>=words.length){
				break;
			}
			i+=words[currentIdx].length();
			
			if(i>lineLength){
				i-=words[currentIdx].length();
				break;
			}
			currentIdx++;
			int currentLineDirtiness = (int)Math.pow((lineLength-i),2);
			int nextLineDirtiness = _printPretty1(lineLength, words, currentIdx);
			dirtynessRank=Math.min(dirtynessRank, (currentLineDirtiness+nextLineDirtiness));			
			
			i++;  // here +1 is for 1 space after every word added
		}
		
		return dirtynessRank;
		
		
	}

	
	
	
	
	
	
	
}