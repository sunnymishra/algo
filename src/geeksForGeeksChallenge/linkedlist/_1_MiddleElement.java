package geeksForGeeksChallenge.linkedlist;

import org.junit.Test;
import org.junit.Assert;


public class _1_MiddleElement{
	private Node init(){
		Node head=new Node(10);
		head.next=new Node(20);
		head.next.next=new Node(30);
		head.next.next.next=new Node(40);
		head.next.next.next.next=new Node(50);
		head.next.next.next.next.next=new Node(60);
		return head;
	}
	private void print(Node head){
		while(head!=null){
			System.out.print(head+"->");
			head=head.next;
		}
		System.out.println("");
	}
	@Test
	public void getMiddle1(){
		Node head=init();
		Assert.assertEquals(40, getMiddle(head));
		
	}
	@Test
	public void getMiddle2(){
		Node head=init();
		head=head.next;	// Making Odd no. of nodes
		Assert.assertEquals(40, getMiddle(head));
		
		Assert.assertEquals(10, getMiddle(new Node(10)));
		Node head1=new Node(10);
		head1.next=new Node(20);
		Assert.assertEquals(20, getMiddle(head1));
		
		Assert.assertEquals(-1, getMiddle(null));
		
	}
	
	public int getMiddle(Node head){
		//print(head);
		if(head==null) return -1;
		Node slow=head;
		Node fast=head;
		
		while(fast!=null && fast.next!=null){
			
			fast=fast.next.next;
			slow=slow.next;
		}
		
		return slow.data;
		
		
	}
	
	
	
	
	
	
}

class Node{
	int data;
	Node next;
	Node(int data){this.data=data;};
	public String toString(){
		return ""+this.data;
	}
	
}