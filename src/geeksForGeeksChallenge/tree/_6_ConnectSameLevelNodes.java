package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;


class Node{
	Node left;
	Node right;
	Node nextRight;
	int data;
	
	Node(int data){
		this.data=data;
		this.nextRight=null;
	}
	public String toString(){
		return ""+this.data+"->"+(this.nextRight==null?"null":""+(this.nextRight.data))+" | ";
	}
}

public class _6_ConnectSameLevelNodes{
	
	private String printNodeSiblings(Node root){
		StringBuilder sb=new StringBuilder();
		_printNodeSiblings(root, sb);
		return sb.toString();
	}
	private void _printNodeSiblings(Node root, StringBuilder sb){
		if(root==null) return;
		
		sb.append(root.toString() + " ");
		_printNodeSiblings(root.left, sb);
		_printNodeSiblings(root.right, sb);
	}

	@Test
	public void connect(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		Node node5=new Node(5);
		Node node6=new Node(6);
		Node node7=new Node(7);
		Node node8=new Node(8);
		Node node9=new Node(9);
		Node node10=new Node(10);
		node1.left=node2;
		node1.right=node3;
		node2.left=node7;
		node2.right=node6;
		node3.left=node5;
		node3.right=node4;
		node7.left=node8;
		node6.right=node9;
		node4.left=node10;

		//Assert.assertEquals(new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9)), findSpiral(node1));
		System.out.println("Before:"+printNodeSiblings(node1));
		connect(node1);
		System.out.println("After:"+printNodeSiblings(node1));
	}
	
	public void connect(Node root){
		if(root==null) return;
		
		Queue<Node> queue=new LinkedList<>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			int len=queue.size();
			for(int i=0; i<len; i++){
				Node current=queue.remove();
				if(!queue.isEmpty() && i<len-1){
					current.nextRight=queue.peek();
				}
				if(current.left!=null){
					queue.add(current.left);
				}
				if(current.right!=null){
					queue.add(current.right);
				}
			}
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}