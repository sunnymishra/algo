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
public class _6_MergeSortedLists{
	@Test
	public void flatten1(){
		Node head1=new Node(5);
		Node node1_2=new Node(17);
		head1.next=node1_2;
		
		Node head2=new Node(15);
		Node node2_2=new Node(16);
		Node node2_3=new Node(18);
		head2.next=node2_2;
		node2_2.next=node2_3;

		
		Assert.assertEquals("5->15->16->17->18->", print(sortedMerge(head1, head2)));
	}
	
	public Node sortedMerge(Node head1, Node head2) {
		if(head1==null) return head2;
		if(head2==null) return head1;
		
		//System.out.println("head1="+head1 + " head2="+head2);
		Node root=null;
		if(head1.data < head2.data){
			root=head1;
			head1.next = sortedMerge(head1.next, head2);
		}else{
			root=head2;
			head2.next=sortedMerge(head1, head2.next);
		}
		//System.out.println("head1="+head1 + " head2="+head2 + "\n");
		return root;
		
		
		
	}
	
	
	
	
	
	
	
	private String print(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head+"->");
			head=head.next;
		}
		return sb.toString();
	}
	
	
	
}