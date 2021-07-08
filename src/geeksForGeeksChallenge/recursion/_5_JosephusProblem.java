package geeksForGeeksChallenge.recursion;

import java.util.*;
import org.junit.*;

class Node{
	int data;
	Node next;
	
	Node(int data){
		this.data=data;
	}
	public String toString(){
		return this.data+" ";
	}
}
public class _5_JosephusProblem{
	@Test
	public void josephus1(){
		Assert.assertEquals(4, josephus(5,3));
		
		
		
		
	}
	private String print(Node head){
		StringBuilder sb = new StringBuilder();
		while(head!=null){
			sb.append(head);
			head=head.next;
		}
		return sb.toString();
	}
	
	public int josephus(int n, int k){
		Node[] pointerArr = new int[n];
		Node head=new Node(-1);
		Node node=head;
		for(int i=1;i<=n; i++){
			Node temp=new Node(i);
			node.next=temp;
			node=node.next;
			pointerArr[i-1]=temp;
		}
		head=head.next;
		
		System.out.println(print(head));
		
		return 4;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}