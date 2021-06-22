package geeksForGeeksChallenge.linkedlist;

import org.junit.Test;
import org.junit.Assert;

class Node{
	int data;
	Node next;
	Node(int data){this.data=data;};
	public String toString(){
		return ""+this.data;
	}
	
}
public class _2_ReverseLinkedlist{
	private Node init(){
		Node head=new Node(10);
		head.next=new Node(20);
		head.next.next=new Node(30);
		head.next.next.next=new Node(40);
		head.next.next.next.next=new Node(50);
		head.next.next.next.next.next=new Node(60);
		return head;
	}
	private String print(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head+"->");
			head=head.next;
		}
		return sb.toString();
	}
	@Test
	public void reverseList(){
		Node head=init();
		Node newHead=reverseList(head);
		Assert.assertEquals("60->50->40->30->20->10->", print(newHead));
		
		head=init();
		head=head.next;	// Making Odd no. of nodes
		newHead=reverseList(head);
		Assert.assertEquals("60->50->40->30->20->", print(newHead));
		
		newHead=reverseList(new Node(10));
		Assert.assertEquals("10->", print(newHead));
		
		head=new Node(10);
		head.next=new Node(20);
		newHead=reverseList(head);
		Assert.assertEquals("20->10->", print(newHead));
		
		Assert.assertEquals(null, reverseList(null));
		
	}
	
	public Node reverseList(Node head){
		if(head==null) return head;
		
		Node prev=null;
		Node curr=head;
		Node next=curr;
		while(curr!=null){
			next=curr.next;
			curr.next=prev;
			prev=curr;
			curr=next;
		}
		head=prev;
		return head;
		
	}
	
	
	
	
}
		
		