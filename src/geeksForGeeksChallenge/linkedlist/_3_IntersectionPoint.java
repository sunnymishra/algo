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
public class _3_IntersectionPoint{
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
	public void intersectPoint(){
		Node head_commonLinkedList=new Node(9);
		head_commonLinkedList.next=new Node(15);
		
		Node head1=new Node(3);
		head1.next=new Node(6);
		head1.next.next=head_commonLinkedList;
		
		Node head2=new Node(10);
		head2.next=head_commonLinkedList;
	
		Assert.assertEquals(9, intersectPoint(head1, head2));
		//////////////
		head1=new Node(3);
		head2=new Node(4);
		Assert.assertEquals(-1, intersectPoint(head1, head2));
		
		head1.next=head_commonLinkedList;
		head2.next=head_commonLinkedList;
		Assert.assertEquals(9, intersectPoint(head1, head2));
		
		head1.next=null;
		head2.next=new Node(16);
		Assert.assertEquals(-1, intersectPoint(head1, head2));
		
	}
	
	public int intersectPoint(Node head1, Node head2){
		if(head1==null || head2==null) return -1;
		
		Node temp1=head1;
		int list1Len=0;
		while(temp1!=null){
			list1Len++;
			temp1=temp1.next;
		}
		
		Node temp2=head2;
		int list2Len=0;
		while(temp2!=null){
			list2Len++;
			temp2=temp2.next;
		}
		int longer=Math.max(list1Len, list2Len);
		int shorter=Math.min(list1Len, list2Len);
		Node longerHead=list1Len>list2Len?head1:head2;
		Node shorterHead=list2Len<list1Len?head2:head1;
		
		int extraLen=longer-shorter;
		while(extraLen>0){
			longerHead=longerHead.next;
			extraLen--;
		}
		//System.out.println("longerHead="+longerHead + " shorterHead="+shorterHead);
		while(longerHead!=null && shorterHead!=null && longerHead.data!=shorterHead.data){
			longerHead=longerHead.next;
			shorterHead=shorterHead.next;
		}
		int result = longerHead==null?-1:longerHead.data;
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}