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
		return ""+this.data;
	}
}


public class _10_TreeHeight{
	@Test
	public void findSpiral3(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(2);
				
		node1.left=node2;
		node1.right=node3;
		
		Assert.assertEquals(2, height(node1));

	}
	
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
		Node node11=new Node(11);
		Node node12=new Node(12);
		Node node13=new Node(13);
		Node node14=new Node(14);
		Node node15=new Node(15);
		
		node7.left=node2;
		node7.right=node9;
		node2.left=node1;
		node2.right=node4;
		node9.left=node8;
		node4.left=node3;
		node4.right=node6;
		node6.left=node5;
		
		node8.right=node10;
		node10.right=node11;
		node11.left=node12;
		node11.right=node13;
		node13.left=node14;
		node14.left=node15;


		Assert.assertEquals(8, height(node7));
	}
	
	public int height(Node node){
		if(node==null) return 0;
		
		return _height(node);
		
	}
	private int _height(Node node){
		if(node==null) return 0;
		
		return Math.max(_height(node.left), _height(node.right)) + 1;
		
		
		
		
		
		
		
		
		
		
		
		
		
	}











	
	
}