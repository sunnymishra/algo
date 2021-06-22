package geeksForGeeksChallenge.linkedlist;

import org.junit.Test;
import org.junit.Assert;

class Node{
	int data;
	Node next;
	Node bottom;
	
	Node(int data){this.data=data;};
	
	public String toString(){
		return ""+this.data;
	}
	
}
public class _5_FlattenList{
	@Test
	public void flatten1(){
		Node head1=new Node(5);
		Node node1_2=new Node(17);
		head1.bottom=node1_2;
		
		Node head2=new Node(8);
		
		Node head3=new Node(15);
		Node node3_2=new Node(16);
		Node node3_3=new Node(18);
		head3.bottom=node3_2;
		node3_2.bottom=node3_3;
		
		Node head4=new Node(20);
		
		Node head5=new Node(22);
		Node node5_2=new Node(23);
		head5.bottom=node5_2;
		
		head1.next=head2;head2.next=head3;head3.next=head4;head4.next=head5;
		
		Assert.assertEquals("5->8->15->16->17->18->20->22->23->", print(flatten(head1)));
	}
	@Test
	public void flatten2(){
		Node head1=new Node(5);
		Node node1_2=new Node(7);
		Node node1_3=new Node(8);
		Node node1_4=new Node(30);
		head1.bottom=node1_2;
		node1_2.bottom=node1_3;
		node1_3.bottom=node1_4;
		
		Node head2=new Node(10);
		Node node2_2=new Node(20);
		head2.bottom=node2_2;
		
		Node head3=new Node(19);
		Node node3_2=new Node(22);
		Node node3_3=new Node(50);
		head3.bottom=node3_2;
		node3_2.bottom=node3_3;
		
		Node head4=new Node(28);
		Node node4_2=new Node(35);
		Node node4_3=new Node(40);
		Node node4_4=new Node(45);
		head4.bottom=node4_2;
		node4_2.bottom=node4_3;
		node4_3.bottom=node4_4;
		
		head1.next=head2;head2.next=head3;head3.next=head4;
		
		Assert.assertEquals("5->7->8->10->19->20->22->28->30->35->40->45->50->", print(flatten(head1)));
	}
	private String print(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head+"->");
			head=head.next;
		}
		return sb.toString();
	}
	private String printDown(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head+"->");
			head=head.bottom;
		}
		return sb.toString();
	}
	public Node flatten(Node root){
		Node current=root;
		while(current.next!=null){
			Node bottomNode=merge(current, current.next);
			current=current.next;
		}
		// Adjusting bottom pointer to next pointer.
		current=root;
		while(current.bottom!=null){
			current.next=current.bottom;
			current=current.bottom;
			
		}
		return root;

	}
	
	private Node merge(Node head1, Node head2){
		Node prev=head1;
		while(head1!=null && head2!=null){
			while(head1!=null && head1.data < head2.data){
				prev=head1;
				head1=head1.bottom;
			}
			if(head1==null){
				prev.bottom=head2;
				break;
			}else{
				prev.bottom=head2;
				Node temp=head2.bottom;
				head2.bottom=head1;
				prev=head2;
				head2=temp;
				
			}
		}
		while(prev.bottom!=null){
			prev=prev.bottom;
		}
		return prev;
	}
	
				
			//System.out.println("current="+current+" current.next="+current.next + " printDown="+printDown(root));
			
	
	

}