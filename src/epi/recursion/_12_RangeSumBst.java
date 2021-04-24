package recursion;

import org.junit.Assert;
import org.junit.Test;


public class _12_RangeSumBst {

	@Test
	public void testRangeSumBst() {
		BinaryTreeNode root = init();
		int sum = rangeSumBst(root, 8, 12);

		Assert.assertEquals(30, sum);
	}

	private BinaryTreeNode init() {
		BinaryTreeNode node1 = new BinaryTreeNode(10);
		BinaryTreeNode node2 = new BinaryTreeNode(7);
		BinaryTreeNode node3 = new BinaryTreeNode(5);
		BinaryTreeNode node4 = new BinaryTreeNode(8);
		BinaryTreeNode node5 = new BinaryTreeNode(20);
		BinaryTreeNode node6 = new BinaryTreeNode(12);
		BinaryTreeNode node7 = new BinaryTreeNode(15);

		node1.left = node2;
		node1.right = node5;
		node2.left = node3;
		node2.right = node4;
		node5.left = node6;
		node6.right = node7;
		return node1;
	}

	/**
	 * Problem: 
	 * Given the root node of a binary search tree, return the sum of values of all
	 * nodes with value between L and R (inclusive). The binary search tree is
	 * guaranteed to have unique values. Reference :
	 * Reference: https://leetcode.com/problems/range-sum-of-bst/
	 *
	 * Solution logic: Simple use of Binary Search tree properties i.e. 
	 * 1. every node in the left subtree of a given node will have value less than current node
	 * 2. every node in the right subtree of a given node will have value greater than current node 
	 * Using Recursion stack levels I have added the logic of either going to left or right subtree depending on left is less than current node
	 * or right is greater than current node value. In the process only add current Node's value to final sum, if current node's value is between Left & right bounds
	 * 
	 * Time complexity: O(n) since we are doing a DFS and in the worst case we have to traverse all the nodes of the BST tree at max once.
	 * Space complexity: O(n) where in the worst case of a Skewed tree (linked list) the height h = n all nodes of the Tree. Note that in a Depth first search 
	 * all the nodes can be in the Recursion stack while we move to the next level in the Stack. Thus they occupy memory.  
	 * 
	 **/
	public int rangeSumBst(BinaryTreeNode root, int left, int right) {
		_rangeSumBst(root, left, right);
		return sum;
	}
	int sum=0;
	private void _rangeSumBst(BinaryTreeNode node, int left, int right) {
		if (node != null) {
			if (left <= node.val && node.val <= right)
				sum += node.val;
			if (left < node.val)
				_rangeSumBst(node.left, left, right);
			if (node.val < right)
				_rangeSumBst(node.right, left, right);
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
