package geeksForGeeksChallenge.revise;

import java.util.*;
import org.junit.*;

public class _1_LRUCache{
	@Test
	public void testLRU1(){
		MyCacheNormal cache=new MyCacheNormal(3);
		
		Assert.assertEquals(-1, cache.get(1));
		
		cache.set(1,100);
		Assert.assertEquals(100, cache.get(1));
		cache.set(2,200);
		Assert.assertEquals(200, cache.get(2));
		
		Assert.assertEquals(-1, cache.get(3));
		cache.set(3,300);
		Assert.assertEquals(300, cache.get(3));
		
		cache.set(4,400);
		Assert.assertEquals(400, cache.get(4));
		Assert.assertEquals(-1, cache.get(1));	// removeEldestEntry "1"
		
		cache.set(1,150);
		Assert.assertEquals(150, cache.get(1));
		
		Assert.assertEquals(-1, cache.get(2));	// removeEldestEntry "2"
	}
	@Test
	public void testLRU2(){
		MyCacheNormal cache=new MyCacheNormal(1);
		
		Assert.assertEquals(-1, cache.get(1));
		
		cache.set(1,100);
		Assert.assertEquals(100, cache.get(1));

		cache.set(4,400);
		Assert.assertEquals(400, cache.get(4));
		Assert.assertEquals(-1, cache.get(1));	// removeEldestEntry "1"
		
		cache.set(1,150);
		Assert.assertEquals(150, cache.get(1));
		
		Assert.assertEquals(-1, cache.get(4));	// removeEldestEntry "2"
	}
	
	@Test
	public void testLRU3(){
		MyCacheSTL cache=new MyCacheSTL(3);
		
		Assert.assertEquals(-1, cache.get(1));
		
		cache.set(1,100);
		Assert.assertEquals(100, cache.get(1));
		cache.set(2,200);
		Assert.assertEquals(200, cache.get(2));
		
		Assert.assertEquals(-1, cache.get(3));
		cache.set(3,300);
		Assert.assertEquals(300, cache.get(3));
		
		cache.set(4,400);
		Assert.assertEquals(-1, cache.get(1));	// removeEldestEntry "1"
		
		cache.set(1,150);
		Assert.assertEquals(150, cache.get(1));
		
		Assert.assertEquals(-1, cache.get(2));	// removeEldestEntry "2"
	}
}

class MyCacheNormal{
	private int capacity;
	Map<Integer, Node> pointer;
	Node head;
	Node tail;
	
	MyCacheNormal(int capacity){
		this.capacity=capacity;
		pointer=new HashMap<>(capacity);
		
	}
	
	public int get(int key){
		int val=-1;
		if(pointer.containsKey(key)){
			//System.
			Node dataNode=pointer.get(key);
			val=dataNode.value;
			// Moving dataNode to front of Deque on every Fetch.
			if(pointer.size()>1){
				// Only needed to Move dataNode, if Cache size > 1
				if(dataNode==head){
					// No action required
				}else if(dataNode==tail){
					tail=dataNode.next;
					dataNode.next=null;
					head.next=dataNode;
					dataNode.prev=head;
					head=dataNode;
				}else{
					dataNode.prev.next=dataNode.next;
					dataNode.next.prev=dataNode.prev;
					dataNode.prev=head;
					dataNode.next=null;
					head.next=dataNode;
					head=dataNode;
				}
			}
		}
		return val;
	}
	public void set(int key, int value){
		if(head==null){
			Node temp=new Node(key, value);
			head=temp;
			tail=temp;
			pointer.put(key, temp);
			return;
		}
		if(pointer.containsKey(key)){
			Node dataNode=pointer.get(key);
			pointer.put(key, dataNode);
			dataNode.value=value;
			
			if(dataNode==head){
				// No action required
			}else if(dataNode==tail){
				tail=dataNode.next;
				dataNode.next=null;
				head.next=dataNode;
				dataNode.prev=head;
				head=dataNode;
			}else{
				dataNode.prev.next=dataNode.next;
				dataNode.next.prev=dataNode.prev;
				dataNode.prev=head;
				dataNode.next=null;
				head.next=dataNode;
				head=dataNode;
			}
		}else{
			Node dataNode=new Node(key, value);
			pointer.put(key, dataNode);
			
			head.next=dataNode;
			dataNode.prev=head;
			head=dataNode;
			
			if(pointer.size()>capacity){
				Node tempData=tail;
				tail=tail.next;
				tail.prev=null;
				
				pointer.remove(tempData.key);
			}
		}
			
		
	}
	public int remove(int key){
		return 1;
	}
	
}
class Node{
	int key;
	int value;
	Node prev;
	Node next;
	
	Node(int key, int value){
		this.key=key;
		this.value=value;
	}
}

/**
	Using STL to create LRU cache

**/
class MyCacheSTL{
	
	private int capacity;
	Map<Integer, Integer> cache;
	
	public MyCacheSTL(int capacity){
		this.capacity=capacity;
		cache=new LinkedHashMap(capacity, 0.75f, true){
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return this.size() > capacity;
			}
		};
		
		
	}
	public int get(int input){
		if(!cache.containsKey(input)){
			return -1;
		}
		return cache.get(input);
	}
	public void set(int a, int b){
		cache.put(a, b);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}