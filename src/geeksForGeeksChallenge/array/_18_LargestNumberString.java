package geeksForGeeksChallenge.array;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class _18_LargestNumberString{
	@Test
	public void printLargest(){
		//Assert.assertEquals("9534330", printLargest(new String[]{"3", "30", "34", "5", "9"}));
		
		//Assert.assertEquals("6054854654", printLargest(new String[]{"54", "546", "548", "60"}));
		
		Assert.assertEquals("98884173061350449344143328166", printLargest(new String[]{"441", "166", "493", "43", "988", "504", "328", "730", "841", "613"}));
	}
	
	@Test
	public void isGreater(){
		
		Assert.assertTrue(isGreater("3", "3"));
		
		Assert.assertTrue(isGreater("3", "30"));
		Assert.assertFalse(isGreater("30", "3"));
		
		Assert.assertTrue(isGreater("34", "30"));
		Assert.assertFalse(isGreater("30", "34"));
		
		Assert.assertTrue(isGreater("311", "31"));
		Assert.assertFalse(isGreater("31", "311"));
		
		Assert.assertTrue(isGreater("34", "319"));
		Assert.assertFalse(isGreater("319", "34"));
	}
	
	
	
	public String printLargest(String[] arr) {
		if(arr==null || arr.length==0) return "";
		
        quickSort(arr, 0, arr.length-1);
		
		//System.out.println(String.join("",arr));
		
		return String.join("",arr);
    }
	
	
	private void quickSort(String[] arr, int left, int right){
		if(left<right){
			int pivotIdx = partition(arr, left, right);
			
			quickSort(arr, left, pivotIdx-1);
			quickSort(arr, pivotIdx+1, right);
		}
	}
	
	private int partition(String[] arr, int left, int right){
		int pivotIdx=left-1;
		if(left<right){
			
			for(int i=left; i<right; i++){
				boolean isGreater = isGreater(arr[i], arr[right]);
				if(isGreater){
					pivotIdx++;
					swap(arr, pivotIdx, i);
				}
				
				/*
				if(arr[i]=="504" || arr[right]=="504" || arr[i]=="493" || arr[right]=="493"){
					System.out.println("strA="+arr[i]+ " strB="+arr[right] + " a isGreater b ="+ isGreater);
				}*/
				
				
			}
			pivotIdx++;
			swap(arr, pivotIdx, right);
		}
		return pivotIdx;
	}
	
	private boolean isGreater(String strA, String strB){
		int a = Integer.parseInt(strA);
		int b = Integer.parseInt(strB);
		
		
		if(strA.length() == strB.length()){
			
			return a>=b;
		}
		int counter=Math.min(strA.length(), strB.length());
		
		for(int i=0; i<counter; i++){
			int intA = strA.charAt(i) - '0';
			int intB = strB.charAt(i) - '0';
			
			//System.out.println("intA="+intA + " intB="+intB);
			
			if(intA < intB){
				return false;
			}
		}
		//34 319
		if(strA.charAt(counter-1)==strB.charAt(counter-1) && (strA.length()==counter && strB.charAt(counter)!='0') || 
			(strA.length()>counter && strA.charAt(counter)=='0')){
			return false;
		}
		return true;
		// 32 < 311
	}
	private void swap(String[]arr, int idx1, int idx2){
		String temp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=temp;
	}
	
	
	
}



