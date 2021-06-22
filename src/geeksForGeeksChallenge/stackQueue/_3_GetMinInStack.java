package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;
import java.util.Stack;
import java.util.Arrays;


public class _3_GetMinInStack{
	
	@Test
	public void nextLargerElement(){
		GfG gfg=new GfG();
		Assert.assertEquals(-1, gfg.pop());
		Assert.assertEquals(-1, gfg.getMin());
		gfg.push(3);
		Assert.assertEquals(3, gfg.getMin());
		Assert.assertEquals(3, gfg.pop());
		Assert.assertEquals(-1, gfg.getMin());
		gfg.push(3);
		gfg.push(5);
		Assert.assertEquals(3, gfg.getMin());
		Assert.assertEquals(5, gfg.pop());
		Assert.assertEquals(3, gfg.getMin());
		Assert.assertEquals(3, gfg.pop());
		Assert.assertEquals(-1, gfg.getMin());
		gfg.push(3);
		gfg.push(5);
		Assert.assertEquals(3, gfg.getMin());
		
		gfg.push(2);
		Assert.assertEquals(2, gfg.getMin());
		gfg.push(1);
		Assert.assertEquals(1, gfg.getMin());
		gfg.push(1);
		Assert.assertEquals(1, gfg.getMin());
		gfg.push(-1);
		Assert.assertEquals(-1, gfg.getMin());
		
		Assert.assertEquals(-1, gfg.pop());
		Assert.assertEquals(1, gfg.getMin());
		Assert.assertEquals(1, gfg.pop());
		Assert.assertEquals(1, gfg.getMin());
		Assert.assertEquals(1, gfg.pop());
		Assert.assertEquals(2, gfg.getMin());
		Assert.assertEquals(2, gfg.pop());
		Assert.assertEquals(3, gfg.getMin());
		Assert.assertEquals(5, gfg.pop());
		Assert.assertEquals(3, gfg.getMin());
		Assert.assertEquals(3, gfg.pop());
		Assert.assertEquals(-1, gfg.getMin());
	}
	
	
	
}

class GfG
{
    int minEle=-1;
    Stack<Integer> s=new Stack<>();

    /*returns minimum element from stack*/
    int getMin()
    {
		return minEle;
    }
    
    /*returns poped element from stack*/
    int pop()
    {
		int val=-1;
		if(s.isEmpty()){
			return -1;
		}else if(s.peek() < minEle){
			int oldMinEle = minEle;
			minEle=2*minEle - s.pop();
			return oldMinEle;
		}else{
			val= s.pop();
		}
		if(s.isEmpty()){
			minEle=-1;
		}
		return val;
    }

    /*push element x into the stack*/
    void push(int x)
    {
		if(s.isEmpty()){
			s.push(x);
			minEle=x;
		}else if(x < minEle){
			s.push(2*x - minEle);
			minEle = x;
		}else{
			s.push(x);
		}
    }
	
	
	
	
	
	
}

