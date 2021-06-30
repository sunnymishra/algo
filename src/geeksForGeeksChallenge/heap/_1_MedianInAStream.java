package geeksForGeeksChallenge.heap;

import org.junit.*;
import java.util.*;


public class _1_MedianInAStream{
	@Test
	public void testMedian1(){
		
		Median.insertHeap(5);
		System.out.println("Median.getMedian()="+Median.getMedian());
		Assert.assertEquals(5.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(15);
		Assert.assertEquals(10.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(1);
		Assert.assertEquals(5.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(3);
		Assert.assertEquals(4.0, Median.getMedian(), 0.0);
	}
	@Test
	public void testMedian2(){
		Median.clearHeaps();
		Median.printHeaps();
		
		Median.insertHeap(100);
		Median.printHeaps();
		Assert.assertEquals(100.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(80);
		Median.printHeaps();
		Assert.assertEquals(90.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(60);
		Median.printHeaps();
		Assert.assertEquals(80.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(70);
		Median.printHeaps();
		Assert.assertEquals(75.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(90);
		Median.printHeaps();
		Assert.assertEquals(80.0, Median.getMedian(), 0.0);
		
		
		Median.insertHeap(10);
		Assert.assertEquals(75.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(25);
		Assert.assertEquals(70.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(5);
		Assert.assertEquals(65.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(62);
		Assert.assertEquals(62.0, Median.getMedian(), 0.0);
		
		
		Median.insertHeap(65);
		Assert.assertEquals(63.5, Median.getMedian(), 0.0);
		
		Median.insertHeap(68);
		Assert.assertEquals(65.0, Median.getMedian(), 0.0);
		
		Median.insertHeap(85);
		Assert.assertEquals(66.5, Median.getMedian(), 0.0);
		
		Median.insertHeap(50);
		Assert.assertEquals(65.0, Median.getMedian(), 0.0);
		
		
		Median.insertHeap(40);
		Assert.assertEquals(63.5, Median.getMedian(), 0.0);
		
		Median.insertHeap(30);
		Assert.assertEquals(62.0, Median.getMedian(), 0.0);
	}
}

class Median{
	private static Queue<Integer> leftHeap=new PriorityQueue<>((a, b) -> b-a);
	private static Queue<Integer> rightHeap=new PriorityQueue<>();
	
	public static void insertHeap(int x){
        if(leftHeap.isEmpty()){
			leftHeap.add(x);
		}else if(x < leftHeap.peek()){
			leftHeap.add(x);
			balanceHeaps();
		}else if(x > leftHeap.peek()){
			rightHeap.add(x);
			balanceHeaps();
		}
		
    }
    
    //Function to balance heaps.
    public static void balanceHeaps(){
		if(leftHeap.size()-rightHeap.size()>1){
			rightHeap.add(leftHeap.remove());
		}else if(rightHeap.size()-leftHeap.size()>1){
			leftHeap.add(rightHeap.remove());
		}
       
    }
    
    //Function to return Median.
    public static double getMedian(){
		double median=-1;
		if(leftHeap.size()==rightHeap.size()){
			median=(double)(leftHeap.peek()+rightHeap.peek())/2;
		}else if(leftHeap.size()>rightHeap.size()){
			median=leftHeap.peek();
		}else if(rightHeap.size()>leftHeap.size()){
			median=rightHeap.peek();
		}
		return median;
    }
	
	public static void clearHeaps(){
		leftHeap.clear();
		rightHeap.clear();
	}
	public static void printHeaps(){
		System.out.println("leftHeap="+leftHeap + " rightHeap="+rightHeap + "\n");
		
	}
	
	
}