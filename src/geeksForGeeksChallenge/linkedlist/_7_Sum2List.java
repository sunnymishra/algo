package geeksForGeeksChallenge.linkedlist;

import org.junit.Test;
import org.junit.Assert;

class Node{
	int data;
	Node next;
	Node(){}
	Node(int data){this.data=data;}
	
	public String toString(){
		return ""+this.data;
	}
	
}
class Resp{
	Node partialSumNode;
	int carryOver;
	
	Resp(Node partialSumNode, int carryOver){
		this.partialSumNode=partialSumNode;
		this.carryOver=carryOver;
	}
	
}
public class _7_Sum2List{
	private String print(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head+"->");
			head=head.next;
		}
		return sb.toString();
	}
	@Test
	public void flatten1(){
		Node head1=new Node(5);
		Node node1_1=new Node(6);
		Node node1_2=new Node(3);
		head1.next=node1_1;
		node1_1.next=node1_2;
		
		Node head2=new Node(8);
		Node node2_1=new Node(4);
		Node node2_2=new Node(2);
		head2.next=node2_1;
		node2_1.next=node2_2;

		
		Assert.assertEquals("1->4->0->5->", print(addlists(head1, head2)));
	}
	@Test
	public void flatten2(){
		Node head1=new Node(9);
		Node node1_1=new Node(9);
		Node node1_2=new Node(3);
		Node node1_3=new Node(6);
		Node node1_4=new Node(3);
		head1.next=node1_1;
		node1_1.next=node1_2;
		node1_2.next=node1_3;
		node1_3.next=node1_4;
		
		Node head2=new Node(8);
		Node node2_1=new Node(4);
		Node node2_2=new Node(2);
		head2.next=node2_1;
		node2_1.next=node2_2;

		
		Assert.assertEquals("1->0->0->2->0->5->", print(addlists(head1, head2)));
	}
	
	public Node addlists(Node head1, Node head2){
		if(head1==null || head2==null) return new Node(-1);
		
		int head1Len=0;
		Node tempHead1=head1;
		while(tempHead1!=null){
			head1Len++;
			tempHead1=tempHead1.next;
		}
		int head2Len=0;
		Node tempHead2=head2;
		while(tempHead2!=null){
			head2Len++;
			tempHead2=tempHead2.next;
		} 
		// Add code for if 2 List have unequal Lengths
		Node remainingNodeHead=null;
		Node largerListHead=null;
		Node smallerListHead=null;
		if(head1Len>head2Len){
			largerListHead=head1;
			smallerListHead=head2;
		}else{
			largerListHead=head2;
			smallerListHead=head1;
		}
		
		int extraLen=Math.abs(head1Len-head2Len);
		if(extraLen>0){
			Node currentNode=largerListHead;
			while(extraLen>1){
				currentNode=currentNode.next;
				extraLen--;
			}
			Node newHead = currentNode.next;
			currentNode.next=null;
			remainingNodeHead=largerListHead;
			largerListHead=newHead;
		}
		//
		
		Resp resp = _add(largerListHead, smallerListHead);
		
		Node head=null;
		
		if(head1Len!=head2Len){
			Resp remainingNodeResp = _addRemainingNode(remainingNodeHead, resp);
			if(remainingNodeResp.carryOver!=0){
				head=new Node(remainingNodeResp.carryOver);
				head.next=remainingNodeResp.partialSumNode;
			}else{
				head=remainingNodeResp.partialSumNode;
			}
		}else{
			if(resp.carryOver!=0){
				head=new Node(resp.carryOver);
				head.next=resp.partialSumNode;
			}else{
				head=resp.partialSumNode;
			}
		}
		
		return head;
		
		
	}
	private Resp _addRemainingNode(Node head, Resp prevStepResp){
		if(head==null){
			return null;
		}
		
		Resp resp = null;
		if(head.next==null){
			resp = prevStepResp;
		}else{
			resp = _addRemainingNode(head.next, prevStepResp);
		}
		
		Node currentSum=new Node(head.data);
		if(resp!=null){
			currentSum.data+=resp.carryOver;
			currentSum.next=resp.partialSumNode;
		}
		int carryOver=currentSum.data>9?1:0;
		currentSum.data=currentSum.data%10;
		Resp currentResp = new Resp(currentSum, carryOver);
		
		//System.out.println("Remaining Nodes Sum -->> head.data="+head.data+" currentResp.partialSumNode="+currentResp.partialSumNode+ " currentResp.carryOver="+currentResp.carryOver);
		return currentResp;
		
	}
	private Resp _add(Node head1, Node head2){
		if(head1==null || head2==null){
			return null;
		}
		
		Node currentPartialSumNode=new Node(head1.data+head2.data);

		Resp resp = _add(head1.next, head2.next);
		
		if(resp!=null){
			currentPartialSumNode.data+=resp.carryOver;
			currentPartialSumNode.next=resp.partialSumNode;
		}
		
		int currentCarryOver=currentPartialSumNode.data>9?1:0;
		currentPartialSumNode.data=currentPartialSumNode.data%10;
		Resp currentResp = new Resp(currentPartialSumNode, currentCarryOver);
		//System.out.println("head1.data="+head1.data+" head2.data="+head2.data + "  currentResp.partialSumNode="+currentResp.partialSumNode+ " currentResp.carryOver="+currentResp.carryOver);
		return currentResp;
	
	}
	
	
}