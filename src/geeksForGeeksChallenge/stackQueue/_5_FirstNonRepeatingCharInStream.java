package geeksForGeeksChallenge.stackQueue;

import org.junit.Test;
import org.junit.Assert;


public class _5_FirstNonRepeatingCharInStream{
	
	@Test
	public void listTest(){		
		List list=new List();
		Node node1=new Node('a');
		Node node2=new Node('b');
		Assert.assertEquals("null", list.print());
		
		list.add(node1);
		Assert.assertEquals("a->", list.print());
		
		list.remove(node1);
		Assert.assertEquals("null", list.print());
		
		list.remove(node1);
		Assert.assertEquals("null", list.print());
		
		list.add(node1);
		Assert.assertEquals("a->", list.print());
		
		list.remove(node2);
		Assert.assertEquals("a->", list.print());
	}
	//@Test
	public void nextLargerElement(){
		Assert.assertEquals("aaab", FirstNonRepeating("abca"));
		Assert.assertEquals("a#bb", FirstNonRepeating("aabc"));
		Assert.assertEquals("z#", FirstNonRepeating("zz"));
	}
	
	public String FirstNonRepeating(String A){
		StringBuilder sb = new StringBuilder();
		
		Node[] mappingArr=new Node[26];
		int[] repeatCountArr=new int[26];
		List nonRepeatList=new List();
		
		for(int i=0; i<A.length();i++){
			char c=A.charAt(i);
			int idx=c-'a';
			if(repeatCountArr[idx]==0){
				Node node=new Node(c);
				mappingArr[idx]=node;
				nonRepeatList.add(node);
			}else{
				Node node=mappingArr[idx];
				nonRepeatList.remove(node);
			}
			repeatCountArr[idx]++;
			
			sb.append(nonRepeatList.peek());
		}
		return sb.toString();
	}
	
	
}
class List{
	Node head=null;
	Node tail=null;
	
	boolean isEmpty(){
		return head==null;
	}
	char peek(){
		return head==null ? '#' : head.data;
	}
	String print(){
		StringBuilder sb=new StringBuilder();
		if(head==null){
			sb.append("null");
		}else{
			Node current=head;
			while(current!=null){
				sb.append(current.data).append("->");
				current=current.next;
			}
		}
		return sb.toString();
	}
	void add(Node temp){
		if(head==null){
			head=temp;
			tail=temp;
		}else{
			tail.next=temp;
			temp.prev=tail;
			tail=temp;
		}
	}
	void remove(Node node){
		//System.out.println("DeleteNode: "+node);
		//System.out.print("Before: ");
		//print();
		
		if(head==null){
			return;
		}
		if(node.prev==null && node.next==null && node!=head){
			// Already deleted Node found. No action required
			return;
		}
		if(node==head){
			Node temp=head.next;
			node.next=null;
			node.prev=null;
			
			if(head==tail){
				tail=null;
			}
			head=temp;
		}else if(node==tail){
			Node temp=tail.prev;
			tail.prev=null;
			tail=temp;
			tail.next=null;
		}else{
			//Node temp=node;
			node.prev.next=node.next;
			node.next.prev=node.prev;
			node.prev=null;
			node.next=null;
		}
		//System.out.print("After: ");
		//print();
	}
}
class Node{
	char data;
	Node next;
	Node prev;
	Node(char data){
		this.data=data;
	}
	public String toString(){
		return ""+this.data;
	}

}




