package recursion;

import org.junit.Test;
import org.junit.Assert;

public class _11_TreeDiameter{
	@Test
	public void testTreeDiameter(){
		BinaryTreeNode root = init();
		int diameter = treeDiameter(root);
		
		Assert.assertEquals(25, diameter);
	}
	
	private BinaryTreeNode init(){
		BinaryTreeNode node1=new BinaryTreeNode(2);
		BinaryTreeNode node2=new BinaryTreeNode(1);
		BinaryTreeNode node3=new BinaryTreeNode(4);
		BinaryTreeNode node4=new BinaryTreeNode(1);
		BinaryTreeNode node5=new BinaryTreeNode(6);
		BinaryTreeNode node6=new BinaryTreeNode(4);
		BinaryTreeNode node7=new BinaryTreeNode(4);
		BinaryTreeNode node8=new BinaryTreeNode(1);
		BinaryTreeNode node9=new BinaryTreeNode(11);
		BinaryTreeNode node10=new BinaryTreeNode(2);
		BinaryTreeNode node11=new BinaryTreeNode(3);
		
		node1.left=node2;
		node1.right=node6;
		node2.left=node3;
		node2.right=node5;
		node3.left=node4;
		node6.left=node7;
		node6.right=node8;
		node8.left=node9;
		node8.right=node10;
		node10.left=node11;
		return node1;
	}
	/**
		Problem: 
		Each node is having an Integer value. Calculate the max value of a subtree having sum of values of all the nodes in that path. This max value should be higher than sum of values of any other subtree in that main tree.
		Note: The original question asked for Max Diameter of the tree considering the weights of each Edge connecting 2 nodes. I have modified the question to instead consider the weights(value) of each Node instead.
		
		Solution logic:
		At any given Recursion stack level there are 4 observations:
		1. I can call my child left node and expect that call to return me left subtree's largest Length so far & left subtree's largest subtree so far
		2. I can call my child right node and expect that call to return me right subtree's largest Length so far & right subtree's largest subtree so far
		3. At this point since I have access to Point#1 and Point#2, therefore I can compare between those 2 values and consider the max value. In that max value I will add my own current Node's value and return to the previous Recursion stack level.
		4. If current Recursion stack level is a Leaf node then we can return current Node's value as the Largest length so far.
	
	**/
	public int treeDiameter(BinaryTreeNode node){
		Data result = _treeDiameter(node);
		return Math.max(result.longestSubTreeSoFar , (result.longestLengthSoFar+1));
	}
	private Data _treeDiameter(BinaryTreeNode node){
		if(node==null){
			return new Data(0, 0);
		}
		if(isLeaf(node)){
			return new Data(node.val, node.val);
		}
		
		Data leftData = _treeDiameter(node.left);
		Data rightData = _treeDiameter(node.right);
		
		int longestLengthSoFar=Math.max(leftData.longestLengthSoFar, rightData.longestLengthSoFar);
		int longestSubTreeSoFar=Math.max(leftData.longestSubTreeSoFar, rightData.longestSubTreeSoFar);
		longestSubTreeSoFar=Math.max(longestSubTreeSoFar, (leftData.longestLengthSoFar + rightData.longestLengthSoFar + node.val));
		return new Data((longestLengthSoFar + node.val), longestSubTreeSoFar);
	}
	
	private boolean isLeaf(BinaryTreeNode node){
		return node.left==null && node.right==null;
	}
	
	class Data{
		int longestLengthSoFar;
		int longestSubTreeSoFar;
		
		public Data(int longestLengthSoFar, int longestSubTreeSoFar){
			this.longestLengthSoFar=longestLengthSoFar;
			this.longestSubTreeSoFar=longestSubTreeSoFar;
		}
	}
	
	class BinaryTreeNode{
		int val;
		BinaryTreeNode left;
		BinaryTreeNode right;
		
		BinaryTreeNode(int val){
			this.val=val;
		}
		BinaryTreeNode(int val, BinaryTreeNode left, BinaryTreeNode right){
			this.val=val;
			this.left=left;
			this.right=right;
		}
		public String toString(){
			return this.val+"";
		}
	}
}