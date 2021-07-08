package geeksForGeeksChallenge.hashing;

import java.util.*;
import org.junit.*;

public class _4_FourSum{
	@Test
	public void maxLen(){
		int[]arr={1,1,1,2,2};
		List<List<Integer>> result = threeSum(arr, 5);
		result.forEach(System.out::println);
	}
	
	public List<List<Integer>> threeSum(int[] arr, int k) {
		Arrays.sort(arr);
		System.out.println("After sort:"+Arrays.toString(arr));
		List<List<Integer>> result=new ArrayList<>();
		for(int i=0; i<arr.length; i++){
			twoSum(arr, k-arr[i], i, result);
		}
		return result;
	}
	public List<List<Integer>> twoSum(int[] arr, int k, int prevIdx, List<List<Integer>> result){
        int l = prevIdx+1;
        int r = l+1;
		
        while (l<arr.length-1 && r<arr.length) {
            if (arr[l] + arr[r] == k){
				List<Integer> interim=new ArrayList<>();
				
				interim.add(prevIdx);interim.add(l);interim.add(r);
				result.add(interim);
				if(r-l>1){
					l++;
				}else{
					r++;
				}
			}
            else if (arr[l] + arr[r] < k)
                r++;
            else // arr[i] + arr[j] > k
                l++;
        }
		
		return result;
	}
	
	
	/*
	{
		
		if(arr==null || arr.length<0) {
			return result;
		}
        
		List<Integer> interim=new ArrayList<>();
		//interim.add(0);interim.add(0);interim.add(1);interim.add(2);
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int i=0; i<arr.length; i++){
			if(map.containsKey(arr[i])){
				map.get(arr[i]).add(i);
			}else{
				List<Integer> idxList=new ArrayList<>();
				idxList.add(i);
				map.put(arr[i], idxList);
			}
		}
		for(int i=0; i<arr.length; i++){
			if(map.containsKey(k-arr[i])){
				List<Integer> idxs = map.get(k-arr[i]);
			}
		}
		
		//result.add(interim);
		return result;
    }
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}