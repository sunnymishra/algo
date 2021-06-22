package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

/**
	This solution takes extra O(n) space due to the SubTreeResp class object created at each node. We can avoid this extra O(n) space using below solution
	https://www.cdn.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
*/
class SubTreeResp{
	int height;
	int diameter;
	
	SubTreeResp(int height, int diameter){
		this.height=height;
		this.diameter=diameter;
	}
}
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


public class _12_TreeDiameter{
	@Test
	public void diameter1(){
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
		
		node1.left=node2;
		node1.right=node3;
		node2.left=node4;
		node2.right=node5;
		node3.right=node6;
		node4.right=node7;
		node5.right=node8;
		node7.left=node9;
		node8.left=node10;
		node8.right=node11;
		node9.left=node12;
		node9.right=node13;
		node11.right=node14;
		
		Assert.assertEquals(9, diameter(node1));
	}
	//@Test
	public void diameter2(){
		Node node_9=new Node(-9);
		Node node6=new Node(6);
		Node node_10=new Node(-10);
		
		node_9.left=node6;
		node_9.right=node_10;
		
		
		Assert.assertEquals(-13, diameter(node_9));
		
	}
	/**
		This solution takes extra O(n) space due to the SubTreeResp class object created at each node. We can avoid this extra O(n) space using below solution
		https://www.cdn.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/
	*/
	public int diameter(Node root){
		if(root==null){
			return 0;
		}
		SubTreeResp subTreeResp = _diameter(root);
		return subTreeResp.diameter;
	}
	private SubTreeResp _diameter(Node node){
		if(node==null){
			return null;
		}
		SubTreeResp leftSubTree = _diameter(node.left);
		SubTreeResp rightSubTree = _diameter(node.right);
		
		int leftHeight=leftSubTree!=null?leftSubTree.height:0;
		int leftDiameter=leftSubTree!=null?leftSubTree.diameter:0;
		
		int rightHeight=rightSubTree!=null?rightSubTree.height:0;
		int rightDiameter=rightSubTree!=null?rightSubTree.diameter:0;
		
		int currHeight=Math.max(leftHeight, rightHeight) + 1;

		int currDiameter=Math.max(leftDiameter, rightDiameter);	
		currDiameter=Math.max(currDiameter, (leftHeight + rightHeight + 1));			
		
		return new SubTreeResp(currHeight, currDiameter);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}