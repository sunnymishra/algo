package array;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import org.junit.Assert;

public class _11_MinPlatforms{
	@Test
	public void findPlatform(){
		Assert.assertEquals(1, findPlatform(new int[]{900, 1100, 1235}, new int[]{1000, 1200, 1240}, 3));
		
		Assert.assertEquals(3, findPlatform(new int[]{900, 940, 950, 1100, 1500, 1800}, new int[]{910, 1200, 1120, 1130, 1900, 2000}, 6));
		
		Assert.assertEquals(2, findPlatform(new int[]{900, 1100}, new int[]{1100, 1200}, 2));
	
	}
	
	public int findPlatform(int arr[], int dep[], int n){
		List<Data> list=new ArrayList<>(2*n);
        for(int i=0; i<n; i++){
			list.add(new Data(arr[i], true));
			list.add(new Data(dep[i], false));
		}
		
		Collections.sort(list, (Data datum1, Data datum2) -> datum1.timestamp==datum2.timestamp ? (datum1.isArrival?-1:1) : Integer.compare(datum1.timestamp, datum2.timestamp));
		
		int maxSimultaneousTrainCount=0;
		int simultaneousTrainCount=0;
		for(int i=0; i<list.size(); i++){
			if(list.get(i).isArrival){
				simultaneousTrainCount++;
			}else{
				simultaneousTrainCount--;
			}
			maxSimultaneousTrainCount=Integer.max(maxSimultaneousTrainCount, simultaneousTrainCount);
		}
		return maxSimultaneousTrainCount;
	}
	
	class Data{
		public int timestamp;
		public boolean isArrival;
		
		public Data(int timestamp, boolean isArrival){
			this.timestamp=timestamp;
			this.isArrival=isArrival;
		}
	}
	
}