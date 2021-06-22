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


public class _9_SymmetricTree{
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


		Assert.assertFalse(isSymmetric(node7));
	}
	@Test
	public void findSpiral2(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
				
		node1.left=node2;
		node1.right=node3;
		node3.left=node4;
		
		Assert.assertFalse(isSymmetric(node1));

	}
	@Test
	public void findSpiral3(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(2);
				
		node1.left=node2;
		node1.right=node3;
		
		Assert.assertTrue(isSymmetric(node1));

	}
	@Test
	public void findSpiral4(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(2);
		Node node4=new Node(3);
		Node node5=new Node(4);
		Node node6=new Node(4);
		Node node7=new Node(3);
		Node node8=new Node(5);
		Node node9=new Node(5);
		
		node1.left=node2;
		node1.right=node3;
		node2.left=node4;
		node2.right=node5;
		node3.left=node6;
		node3.right=node7;
		node5.left=node8;
		node6.right=node9;
		
		Assert.assertTrue(isSymmetric(node1));

	}
	
	public boolean isSymmetric(Node root){
		if(root==null) return false;
		return _isSymmetric(root, root);
	}
		
	private boolean _isSymmetric(Node node1, Node node2){	
		if(node1==null && node2==null){
			return true;
		}
		if(node1==null ^ node2==null){
			return false;
		}
		if(node1.data!=node2.data){
			return false;
		}
		
		return _isSymmetric(node1.left, node2.right) & 
				_isSymmetric(node1.right, node2.left);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}