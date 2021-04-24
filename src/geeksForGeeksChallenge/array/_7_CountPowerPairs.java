package array;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _7_CountPowerPairs{
	@Test
	public void rearrange(){
		int[] arr1=new int[]{2, 1, 6};
		int[] arr2=new int[]{1, 5};
		
		Assert.assertEquals(3, countPairs(arr1, arr2, arr1.length, arr2.length));
		
		/*		
		int[] arr2=new int[]{10,20,30,40,50,60,70,80,90,100,110};
		System.out.println("Before->"+ Arrays.toString(arr2));
		rearrange(arr2, arr2.length);
		System.out.println("After->"+ Arrays.toString(arr2));
		*/
	}
	
	
	public long countPairs(int x[], int y[], int M, int N){
		Arrays.sort(y);
		
		System.out.println("x[]->"+ Arrays.toString(x)+ "  y[]->"+ Arrays.toString(y));
		int count=0;
		int exceptionInXArray=0;
		int exceptionInYArray=0;
		for(int i=0; i<M; i++){
			if(x[i]==0 || x[i]==1){
				exceptionInXArray++;
				continue;
			}
			
			int yIdx = Arrays.binarySearch(y, x[i]);
			if(yIdx>=0){
				count++;
			}
			//System.out.println("x[i]=->"+ x[i] + " binarySearch y="+ searchNextBiggerNo(y, x[i]));
		}
		for(int j=0; j<N; i++){
			if(y[j]==0 || y[j]==1){
				exceptionInYArray++;
			}
		}
		
		
		return count;
	}
	
	private int searchNextBiggerNo(int[] y, int val){
		int yIdx = Arrays.binarySearch(y, val);
		int searchIdx=-1;
		if(yIdx>=0 && y[yIdx]!=val){
			searchIdx= yIdx;
		}else if(yIdx>=0 && yIdx<y.length-1){
			searchIdx= yIdx+1;
		}else if(yIdx>=0 && yIdx==y.length-1){
			//
		}else if(yIdx<0 && ((-yIdx -1) >=y.length )){
			//
		}else if(yIdx<0 && ((-yIdx -1) <y.length )){
			searchIdx= (-yIdx -1);
		}
		return searchIdx;
	}
	
	
	
	
}