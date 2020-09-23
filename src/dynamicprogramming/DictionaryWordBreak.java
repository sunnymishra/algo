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
	public void dictionaryWordBreak1(){
		Assert.assertTrue(dictionaryWordBreak1(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathandbeyond"));
		
		Assert.assertFalse(dictionaryWordBreak1(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathyouandbeyond"));
		
		Assert.assertTrue(dictionaryWordBreak1(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "ilikesamsung"));
		
		Assert.assertFalse(dictionaryWordBreak1(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "samsungandmangok"));
	}
	
	@Test
	public void dictionaryWordBreak2(){
		Assert.assertTrue(dictionaryWordBreak2(new String[]{"i", "like", "had", "play", "too"}, "itooplay"));
		
		Assert.assertTrue(dictionaryWordBreak2(new String[]{"bed", "moon", "bath", "beyond", "bat", "and", "sun"}, "bedbathandbeyond"));
		
		Assert.assertFalse(dictionaryWordBreak2(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathyouandbeyond"));
		
		Assert.assertTrue(dictionaryWordBreak2(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "ilikesamsung"));
		
		Assert.assertFalse(dictionaryWordBreak2(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "samsungandmangok"));
	}
	
	@Test
	public void dictionaryWordBreak3(){
		Assert.assertTrue(dictionaryWordBreak3(new String[]{"i", "like", "had", "play", "too"}, "itooplay"));
		
		Assert.assertTrue(dictionaryWordBreak3(new String[]{"bed", "moon", "bath", "beyond", "bat", "and", "sun"}, "bedbathandbeyond"));
		
		Assert.assertFalse(dictionaryWordBreak3(new String[]{"bed", "moon", "bath", "beyond", "bat", "hand", "sun"}, "bedbathyouandbeyond"));
		
		Assert.assertTrue(dictionaryWordBreak3(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "ilikesamsung"));
		
		Assert.assertFalse(dictionaryWordBreak3(new String[]{"mobile","samsung","sam","sung", "man","mango","icecream","and","go","i","like","ice","cream"}, "samsungandmangok"));
	}
	
	/**
		Problem:
		Given a dictionary(Array) of english words and an English sentence as Input, find whether if we break the Sentence into smaller parts, does all the parts belong to the input Dictionary or not.
		Example: 
		Input Dictionary:{"i", "am","happy"} Input Sentence: "happyami"
		Output: True i.e. Yes all the smaller parts of the sentence break exists in the Dictionary
		
		Input Dictionary:{"are", "they","happy"} Input Sentence: "youarehappy"
		Output: False i.e. If Input sentence is broken into smaller parts few words don't exist in the Dictionary
		
		Note: Below function dictionaryWordBreak1() is a Recursive solution and since there is no  overlapping sub-problems if you draw a Recursion tree with Pen and Paper, therefore I can't use DP and Memoization to further Optimize this. For Optimized DP solution look at dictionaryWordBreak2() function.
	*/
	public boolean dictionaryWordBreak1(String[] dict, String sentence){
		if(dict==null || dict.length==0 || sentence==null || sentence.isEmpty()) return false;
		System.out.println("\n"+sentence);
		Set<String> dictionary=new HashSet<>();
		for(String s: dict) dictionary.add(s);
		List<String> result = new ArrayList<String>();
		String words = _dictionaryWordBreak1(dictionary, sentence, 0);
		if(words!=null){
			System.out.println(words);
			return true;
		} 
		return false;
	}
	
	private String _dictionaryWordBreak1(Set<String> dictionary, String sentence, int leftIdx){
		if(leftIdx>=sentence.length()){
			return "";
		}
		boolean isSuccess = false;
		String nextWord = null;
		for(int rightIdx=leftIdx+1; rightIdx<=sentence.length(); rightIdx++){
			//System.out.println("leftIdx:"+leftIdx+" rightIdx:"+rightIdx);
			String word=sentence.substring(leftIdx, rightIdx);
			if(dictionary.contains(word)){
				//result.add(word);
				nextWord = _dictionaryWordBreak1(dictionary, sentence, rightIdx);
				if(nextWord!=null){
					nextWord=word+" "+nextWord;
					break;
				}
			}
		}
		return nextWord;
	}
	
	/**
		This is an Iterative solution exactly similar to the above Recursive solution. However in this case
		for optimization purpose we are using Memoization by storing intermediate results, because we found
		several Overlapping sub-problems.
		Reference: https://www.geeksforgeeks.org/word-break-problem-dp-32/
		
		Time complexity:
		Outer loop runs for n times. Inner loop runs for n times. So n^2 is the total runtime. However if we also include the time taken for doing a Substring operation and Hashing it in order to do a Hashtable match in HashSet, then another 'n' time is spent inside each inner loop. So that makes it a total of n^3. 
		Note: To calculate above worst run-time, consider a scenario where every single Letter in the input Sentence is present in the Dictionary.
	
	*/
	public boolean dictionaryWordBreak2(String[] dict, String sentence){
		if(dict==null || dict.length==0 || sentence==null || sentence.isEmpty()) return false;
		
		Set<String> dictionary=new HashSet<>();
		for(String s: dict) dictionary.add(s);
		
		boolean[]cache=new boolean[sentence.length()+1];
		
		boolean isSuccess=false;
		for(int i=1; i<sentence.length(); i++){
			
			String word=sentence.substring(0, i);
			if(cache[i]==false && dictionary.contains(word)){
				cache[i]=true;
			}
			if(cache[i]==false){
				//This condition is super critical. Else algorithm won't work
				continue;
			}
			boolean innerWordSuccess=false;	// This Boolean is only for printing results. It has no other value
			for(int j=i+1; j<=sentence.length(); j++){
				word=sentence.substring(i, j);
				if(cache[j]==false && dictionary.contains(word)){
					cache[j]=true;
					innerWordSuccess=true;
				}
				if(j==sentence.length() && cache[j] == true) 
                    isSuccess= true; 
			}
			if(isSuccess) break;
			if(innerWordSuccess==false) cache[i]=false;	// This step is only for printing results. It has no other value
		}
		// Below code is for Printing result.
		String result="";
		for(int i=0;i<cache.length-1; i++){
			if(cache[i]==true){
				result+=" ";
			}
			result+=sentence.charAt(i); 
		}
		System.out.println("\n"+sentence);
		if(cache[sentence.length()]) System.out.println(result);
		System.out.println(Arrays.toString(cache));
		
		return cache[sentence.length()];
	}
	
	/**
		This is the Bottom-up approach using Memoization in which Intermediate results are stored in the 2D Array. Since the size of Cache array = n^2, so the Space Complexity=O(n^2)
		Reference: https://www.youtube.com/watch?v=WepWFGxiwRs
	*/
	public boolean dictionaryWordBreak3(String[] dict, String sentence){
		if(dict==null || dict.length==0 || sentence==null || sentence.isEmpty()) return false;
		System.out.println("\n\nInput: "+sentence);
		Set<String> dictionary=new HashSet<>();
		for(String s: dict) dictionary.add(s);
		
		int[][]cache=new int[sentence.length()][sentence.length()];
		for(int i=0; i<cache.length; i++){
			Arrays.fill(cache[i], -1);
		}
		
		for(int wordLen=1; wordLen<=sentence.length(); wordLen++){
			for(int startIdx=0; startIdx<sentence.length()-wordLen+1; startIdx++){
				int endIdx=startIdx+wordLen-1;
				String word=sentence.substring(startIdx, endIdx+1);
				if(cache[startIdx][endIdx]==-1 && dictionary.contains(word)){
					cache[startIdx][endIdx]=startIdx;
				}
				if(cache[startIdx][endIdx]!=-1){
					continue;
				}else{
					for(int midIdx=startIdx+1; midIdx<=endIdx+1; midIdx++){
						String word1=sentence.substring(startIdx, midIdx-1);
						boolean word1Success=false,word2Success=false;
						if(cache[startIdx][midIdx-1]!=-1 && cache[midIdx][endIdx]!=-1){
							cache[startIdx][endIdx]=midIdx;
							break;
						}
					}
				}
			
			}
		}
		// Printing Cache array
		for(int i=0;i<cache.length-1; i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		// Since it is Bottom up approach, as we progress through the Nested Array, the Cache array keeps filling from left to right. If the Right most cell of 0th Row is filled, then we know we got Success.
		return cache[0][cache.length-1]!=-1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}