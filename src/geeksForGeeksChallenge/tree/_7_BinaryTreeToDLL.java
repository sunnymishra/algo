package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;


class Node{
	Node left;
	Node right;
	int data;
	
	Node(int data){
		this.data=data;
	}
	public String toString(){
		return (this.left==null?"null":this.left.data) + "<-" + this.data + "->" + (this.right==null?"null":this.right.data) + " | ";
	}
}
class DLLStartEnd{
	Node startNode;
	Node endNode;
	
	DLLStartEnd(Node startNode, Node endNode){
		this.startNode=startNode;
		this.endNode=endNode;
	}
}

public class _7_BinaryTreeToDLL{
	@Test
	public void findSpiral(){
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

		Node dllStartNode = bToDLL(node1);
		
		System.out.println(printDll(dllStartNode));
		
	}
	private String printDll(Node node){
		StringBuilder sb  = new StringBuilder();
		while(node!=null){
			sb.append(node);
			node=node.right;
		}
		return sb.toString();
	}
	
	public Node bToDLL(Node root){
		if(root==null) return root;
		
		DLLStartEnd dll = _bToDLL(root);
		
		return dll.startNode;
		
	}
	
	private DLLStartEnd _bToDLL(Node current){
		if(current==null) return null;
		
		DLLStartEnd leftChild=_bToDLL(current.left);
		DLLStartEnd rightChild=_bToDLL(current.right);
		
		Node currentDLLStart=current;
		Node currentDLLEnd=current;
		
		if(leftChild!=null){
			leftChild.endNode.right=current;
			current.left=leftChild.endNode;
			currentDLLStart=leftChild.startNode;
		}
		if(rightChild!=null){
			rightChild.startNode.left=current;
			current.right=rightChild.startNode;
			currentDLLEnd=rightChild.endNode;
		}
		return new DLLStartEnd(currentDLLStart, currentDLLEnd);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}