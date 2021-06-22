package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.Stack;
import java.util.LinkedHashMap;


public class _4_LRUCache{
	
	@Test
	public void nextLargerElement(){
		LRUCache cache=new LRUCache(2);
		Assert.assertEquals(-1, LRUCache.get(10));
		
		
		LRUCache.set(10, 100);
		Assert.assertEquals(100, LRUCache.get(10));
		
		Assert.assertEquals(-1, LRUCache.get(50));
	}
	
}
class LRUCache
{
	private static Map<Integer, Integer> cache;
    //Constructor for initializing the cache capacity with the given value.
    LRUCache(int cap)
    {
		cache=new LinkedHashMap(cap, 0.75f, true){
			protected boolean removeEldestEntry(Map.Entry eldestEntry){
				return size() > cap;
			}
		};
		
    }

    //Function to return value corresponding to the key.
    public static int get(int key)
    {
//		if(cache.containsKey(key))
//			return cache.get(key);
//		else
//			return -1;
		
		Integer val = cache.get(key);
        if(val==null) return -1;
		else return val;
		
    }

    //Function for storing key-value pair.
    public static void set(int key, int value)
    {
        cache.put(key, value);
    }
	
	
	
	
	
	
}