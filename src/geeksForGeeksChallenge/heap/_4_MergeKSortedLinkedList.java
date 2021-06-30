package geeksForGeeksChallenge.heap;

import org.junit.*;
import java.util.*;


/**
	Problem link: https://practice.geeksforgeeks.org/contest/coding-try-outs-amazon/problems
	
	Method expects input to have a String containing only Lower case english alphabets. Method returns TRUE if it is possible to rearrange the chars in such a way that no two adjacent chars are same/duplicate/repeated. Returns false otherwise.
*/
class Node{
	int data;
	Node next;
	Node(int data){
		this.data=data;
	}
}
public class _4_MergeKSortedLinkedList{
	@Test
	public void mergeKList(){
		Node nodeA10=new Node(10);
		Node nodeA20=new Node(20);
		Node nodeA40=new Node(40);
		Node nodeA50=new Node(50);
		Node nodeA55=new Node(55);
		
		Node nodeB5=new Node(5);
		Node nodeB6=new Node(6);
		Node nodeB7=new Node(7);
		Node nodeB8=new Node(8);
		
		Node nodeC15=new Node(15);
		Node nodeC18=new Node(18);
		Node nodeC45=new Node(45);
		Node nodeC47=new Node(47);
		
		nodeA10.next=nodeA20;
		nodeA20.next=nodeA40;
		nodeA40.next=nodeA50;
		nodeA50.next=nodeA55;

		nodeB5.next=nodeB6;
		nodeB6.next=nodeB7;
		nodeB7.next=nodeB8;

		nodeC15.next=nodeC18;	
		nodeC18.next=nodeC45;	
		nodeC45.next=nodeC47;	

		Node[] arr = new Node[]{nodeA10, nodeB5, nodeC15};
		Node resultHead = mergeKList(arr, arr.length);
		System.out.println("resultHead="+print(resultHead));
		
		Assert.assertEquals("5->6->7->8->10->15->18->20->40->45->47->50->55->", print(resultHead));
	}
	private String print(Node head){
		if(head==null) return "";
		
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head.data).append("->");
			head=head.next;
		}
		return sb.toString();
	}
	
	
	Node mergeKList(Node[]arr, int K){
		if(arr==null || arr.length!=K) return null;
		
		Queue<Node> minHeap = new PriorityQueue<>((node1, node2) -> Integer.compare(node1.data, node2.data));
		Node resultHead=new Node(-1);
		Node resultNode=resultHead;
		Set<Integer> emptyListMarker=new HashSet<>();
		
		while(true){
			for(int i=0; i<K; i++){
				if(arr[i]==null){
					emptyListMarker.add(i);
					continue;
				}
				
				Node currNode = arr[i];
				arr[i]=arr[i].next;
				currNode.next=null;
				
				if(!minHeap.isEmpty() && currNode.data <= minHeap.peek().data){
					//System.out.println("arr[i].data="+currNode.data + " minHeap.peek().data="+minHeap.peek().data);
					resultNode.next=currNode;
					resultNode=resultNode.next;
					
					i--;
					continue;
				}else{
					minHeap.add(currNode);
				}
				
				if(minHeap.size()>K){
					resultNode.next=minHeap.remove();
					resultNode=resultNode.next;
				}
			}
			if(emptyListMarker.size()==K){
				break;
			}
			//System.out.println("emptyListCount="+emptyListMarker.size()+ " resultHead="+print(resultHead));
		}
		while(!minHeap.isEmpty()){
			resultNode.next=minHeap.remove();
			resultNode=resultNode.next;
		}
		return resultHead.next;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}