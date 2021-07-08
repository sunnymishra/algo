package geeksForGeeksChallenge.hashing;

import java.util.*;
import org.junit.*;

public class _2_SortByFrequency{
	@Test
	public void sortByFrequency(){
		int[] elem={6,2,8,3,1};
		int[] freq={1,1,3,4,1};
		
		sortByFrequency(elem, freq);
	}
	/**
		https://practice.geeksforgeeks.org/problems/sorting-elements-of-an-array-by-frequency/0
		
		Rule: Sort in Descending order of Frequency. If Frequency is same, the smaller elem comes first.
	
	*/
	public void sortByFrequency(int[] elem, int[] freq) {
		System.out.println("");
		Comparator<Pair> cmp = (pair1, pair2) -> pair1.freqVal==pair2.freqVal?Integer.compare(pair1.elemVal, pair2.elemVal): Integer.compare(pair2.freqVal, pair1.freqVal);
		Pair[] arr=new Pair[elem.length];
		for(int i=0; i<elem.length; i++){
			Pair temp=new Pair(elem[i], freq[i]);
			arr[i]=temp;
		}
		Arrays.sort(arr, cmp);
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]);
		}
		System.out.println("");
		Queue<Pair> maxHeap = new PriorityQueue<>(cmp);
	  
		for(int i=0; i<elem.length; i++){
			Pair temp=new Pair(elem[i], freq[i]);
			maxHeap.add(temp);
		}
		while(!maxHeap.isEmpty()){
			System.out.print(maxHeap.remove());
		}
	}
	
	
	
	
}
	
class Pair{
	int elemVal;
	int freqVal;
	
	Pair(int elemVal, int freqVal){
		this.elemVal=elemVal;
		this.freqVal=freqVal;
	}
	public String toString(){
		return this.elemVal + ":"+this.freqVal+" | ";
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
