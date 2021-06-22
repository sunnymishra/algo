package geeksForGeeksChallenge.array;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;

public class _15_StockBuySell{
	
	@Test
	public void stockBuySell1() {
		System.out.println("\n\n");
        ArrayList<ArrayList<Integer>> result = stockBuySell(new int[]{100,180,260,310,40,535,695}, 7);
		for(int i=0; i<result.size();i++)
			System.out.println(result.get(i));
    }
	
	@Test
	public void stockBuySell2() {
		System.out.println("\n\n");
        ArrayList<ArrayList<Integer>> result = stockBuySell(new int[]{4,2,2,2,4}, 5);
		for(int i=0; i<result.size();i++)
			System.out.println(result.get(i));
    }
	
	public ArrayList<ArrayList<Integer>> stockBuySell(int A[], int n) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		if(A.length == 0 || A.length == 1 || A.length != n) return list;
				
		int idx=0;
		while( idx<n-1 ){
			// Find local idx
			
			while(idx<n-2 && A[idx+1] <= A[idx]){
				idx++;
			}
			int localMinIdx=idx;
			
			if(idx==n-1) break;
			
			idx++;
			
			// Find local maxima
			while(idx<n-1 && A[idx+1] >= A[idx]){
				idx++;
			}
			int localMaxIdx=idx;
			
			if(localMinIdx==localMaxIdx) continue;
			
			ArrayList<Integer> tempList = new ArrayList();
			tempList.add(localMinIdx);
			tempList.add(localMaxIdx);
			
			list.add(tempList);
			
			idx++;
		}
		return list;
    }
	
}