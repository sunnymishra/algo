package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class DictionaryWordBreak{
	@Test
	public void dictionaryWordBreak(){
		Assert.assertTrue(dictionaryWordBreak(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathandbeyond"));
		
		Assert.assertFalse(dictionaryWordBreak(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathyouandbeyond"));
		
		Assert.assertTrue(dictionaryWordBreak(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "ilikesamsung"));
		
		Assert.assertFalse(dictionaryWordBreak(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "samsungandmangok"));
	}
	
	/**
		Problem:
		Given a dictionary(Array) of english words and an English sentence as Input, find whether if we break the Sentence into smaller parts, does all the parts belong to the input Dictionary or not.
		Example: 
		Input Dictionary:{"i", "am","happy"} Input Sentence: "happyami"
		Output: True i.e. Yes all the smaller parts of the sentence break exists in the Dictionary
		
		Input Dictionary:{"are", "they","happy"} Input Sentence: "youarehappy"
		Output: False i.e. If Input sentence is broken into smaller parts few words don't exist in the Dictionary
	*/
	public boolean dictionaryWordBreak(String[] dict, String sentence){
		if(dict==null || dict.length==0 || sentence==null || sentence.isEmpty()) return false;
		
		Set<String> dictionary=new HashSet<>();
		for(String s: dict) dictionary.add(s);
		List<String> result = new ArrayList<String>();
		return _dictionaryWordBreak(dictionary, sentence, 0);
	}
	
	private boolean _dictionaryWordBreak(Set<String> dictionary, String sentence, int leftIdx){
		if(leftIdx>=sentence.length()){
			return true;
		}
		boolean isSuccess = false;
		for(int rightIdx=leftIdx+1; rightIdx<=sentence.length(); rightIdx++){
			String word=sentence.substring(leftIdx, rightIdx);
			if(dictionary.contains(word)){
				//result.add(word);
				isSuccess = _dictionaryWordBreak(dictionary, sentence, rightIdx);
				if(isSuccess){
					break;
				}
			}
		}
		return isSuccess;
	}
	
	
	
	
}