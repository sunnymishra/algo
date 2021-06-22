package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class _1_ParenthesisChecker{
	
	@Test
	public void ispar(){
		/*
		Assert.assertTrue(ispar("[()]{[()]()}"));
		Assert.assertTrue(ispar("{([])}"));
		Assert.assertTrue(ispar("{}"));
		Assert.assertFalse(ispar("{()"));
		*/
		Assert.assertFalse(ispar("[({[([{}])]})}"));
	}

	private boolean ispar(String x){
		boolean isParen=false;
		Stack<Character> stack=new Stack<>();
		
		for(int i=0;i<x.length();i++){
			char c=x.charAt(i);
			if(left.contains(c)){
				stack.add(c);
				continue;
			}
			if(stack.isEmpty()){
				return false;
			}
			if(!isPair(stack.pop(), c)){
				return false;
			}
		}
		if(stack.isEmpty()){
			isParen=true;
		}
		return isParen;

	}
	
	private Set<Character> left=new HashSet(Arrays.asList(new Character[]{'(','[','{'}));
	
	private boolean isPair(char left, char right){
		if(left=='(' && right==')' || left=='[' && right==']' || 
			left=='{' && right=='}' )
			return true;
		else
			return false;
	}
	
}


	
	
