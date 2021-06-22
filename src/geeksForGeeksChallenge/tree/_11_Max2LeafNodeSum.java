package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

class SumPair{
	int sumSoFar;
	int subTreeSum;
	
	SumPair(int sumSoFar, int subTreeSum){
		this.sumSoFar=sumSoFar;
		this.subTreeSum=subTreeSum;
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


public class _11_Max2LeafNodeSum{
	//@Test
	public void maxPathSum1(){
		Node node_15=new Node(-15);
		Node node5=new Node(5);
		Node node6=new Node(6);
		Node node_8=new Node(-8);
		Node node1=new Node(1);
		Node node3=new Node(3);
		Node node9=new Node(9);
		Node node2=new Node(2);
		Node node_3=new Node(-3);

		Node node0=new Node(0);
		Node node4=new Node(4);
		Node node_1=new Node(-1);
		Node node10=new Node(10);
		
		node_15.left=node5;
		node_15.right=node6;
		node5.left=node_8;
		node5.right=node1;
		node6.left=node3;
		node6.right=node9;
		node_8.left=node2;
		node_8.right=node_3;
		node9.right=node0;
		node0.left=node4;
		node0.right=node_1;
		node_1.left=node10;
		
		
		Assert.assertEquals(27, maxPathSum(node_15));
	}
	@Test
	public void maxPathSum2(){
		Node node_9=new Node(-9);
		Node node6=new Node(6);
		Node node_10=new Node(-10);
		
		node_9.left=node6;
		node_9.right=node_10;
		
		
		Assert.assertEquals(-13, maxPathSum(node_9));
		
	}
	public int maxPathSum(Node root){
		if(root==null){
			return 0;
		}
		SumPair sumPair = _maxPathSum(root);
		return sumPair.subTreeSum;
	}
	private SumPair _maxPathSum(Node node){
		if(node==null){
			return null;
		}
		SumPair leftSumPair = _maxPathSum(node.left);
		SumPair rightSumPair = _maxPathSum(node.right);
		
		int leftSumSoFar=0;
		int leftSubTreeSum=0;
		int currSumSoFar=0;
		
		int rightSumSoFar=0;
		int rightSubTreeSum=0;
		int currSubTreeSum=0;
		
		if(leftSumPair!=null){
			leftSumSoFar=leftSumPair.sumSoFar;
			leftSubTreeSum=leftSumPair.subTreeSum;
		}
		if(rightSumPair!=null){
			rightSumSoFar=rightSumPair.sumSoFar;
			rightSubTreeSum=rightSumPair.subTreeSum;
		}
		
		currSumSoFar=Math.max(leftSumSoFar, rightSumSoFar) + node.data;
		
		if(node.left==null || node.right==null){
			currSubTreeSum=leftSumSoFar + rightSumSoFar + node.data;
		}else{
			currSubTreeSum=Math.max(leftSubTreeSum, rightSubTreeSum);	
			currSubTreeSum=Math.max(currSubTreeSum, (leftSumSoFar + rightSumSoFar + node.data));	
		}
		
		
		return new SumPair(currSumSoFar, currSubTreeSum);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}