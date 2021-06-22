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
public class _4_DetectLoop{
	@Test
	public void intersectPoint(){
		Node head1=new Node(3);
		Node node2=new Node(6);
		Node node3=new Node(8);
		Node node4=new Node(12);
		Node node5=new Node(15);
		head1.next=node2;node2.next=node3;node3.next=node4;node4.next=node5;
		node5.next=node3;
		Assert.assertTrue(detectLoop(head1));
		///////
		node5.next=null;
		Assert.assertFalse(detectLoop(head1));
		
		head1=new Node(3);
		node2=new Node(6);
		head1.next=node2;node2.next=head1;
		Assert.assertTrue(detectLoop(head1));
		
	}
	
	public boolean detectLoop(Node head){
		Node slow=head; Node fast=head;
		
		while(fast!=null && fast.next!=null){
			fast=fast.next.next;
			slow=slow.next;
			if(fast==slow){
				System.out.println("Loop Intersection point="+fast.data);
				return true;
			}
		}
		return false;
		
		
	}
	
	
	
	
	
}