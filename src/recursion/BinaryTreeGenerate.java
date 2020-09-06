package recursion;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public class BinaryTreeGenerate{
	@Test
	public void testPalindromePartitioning(){
		generateAllBinaryTreesOfNNodes(3);
		
	}
	
	/**
		Problem: 
		Generate all combinations of a Binary tree with a given fixed no. of Nodes. A binary tree is of following structure: Current Node with a value, Current Node can have a Left child Node, Current Node can have a Right child Node. The cycle repeats for each child node also.
		
		Solution logic: 
		Since this problem is a variance of nth Catalan number problem, the general idea of all Catalan no. problem is below: For any given input n, consider i as the enumeration number which can range from 1 to n.
		For all value of i, the program will recursively create 2 subtrees from 1 to i and from i+1 to n.
		Reference: https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/
		Catalan no. Reference: https://www.geeksforgeeks.org/program-nth-catalan-number/
		
		Asymptotic analysis:
		Time complexity: nth Catalan number. Reference: https://www.geeksforgeeks.org/construct-all-possible-bsts-for-keys-1-to-n/
		
		Use Paper and Pen to Visualize a Tree to see this in action.
		
	*/
	public void generateAllBinaryTreesOfNNodes(int nodeLength){
		if(nodeLength<=0){
			return;
		}

		List<BinaryTreeNode> totalBinaryTrees = _generateAllBinaryTreesOfNNodes(3); 
        /*  Printing preorder traversal of all constructed BSTs   */
        System.out.println("Preorder traversals of all constructed BSTs are "); 
        for (int i = 0; i < totalBinaryTrees.size(); i++)  
        {  
            treeTraverse(totalBinaryTrees.get(i));  
            System.out.println(); 
        }  
	}
	
	/*
		
	*/
	public List<BinaryTreeNode> _generateAllBinaryTreesOfNNodes(int nodeLength){
		List<BinaryTreeNode> result = new ArrayList <>() ;
		if (nodeLength == 0) {
			// Empty tree
			result.add(null);
		}
		for (int numLeftTreeNodes = 0; numLeftTreeNodes < nodeLength; numLeftTreeNodes++) {
			int numRightTreeNodes = nodeLength - 1 - numLeftTreeNodes;
			// Recursively fetch Left subTree of current Recursion stack level
			List<BinaryTreeNode> leftSubtrees = _generateAllBinaryTreesOfNNodes(numLeftTreeNodes);
			// Recursively fetch RIght subTree of current Recursion stack level
			List<BinaryTreeNode> rightSubtrees = _generateAllBinaryTreesOfNNodes(numRightTreeNodes);
			
			for (BinaryTreeNode left : leftSubtrees) {
				for (BinaryTreeNode right : rightSubtrees) {
					// Add Left subTree and Right subTree to the current Node.
					// Add current Node to the result ArrayList.
					result.add(new BinaryTreeNode(new Random().nextInt(100) , left, right));
				}
			}
		}
		return result;
	}
	
	// using BFS tree traversal below
	private void treeTraverse(BinaryTreeNode root){
		if(root==null){
			return;
		}
		Queue<BinaryTreeNode> queue = new LinkedList<>();
		System.out.println("\nRoot="+root.val+",\t");
		queue.add(root);
		
		while(!queue.isEmpty()){
			
			BinaryTreeNode node = queue.remove();
			if(node.left!=null){
				System.out.print(node.val+".Left="+node.left.val+"\t");
				queue.add(node.left);
			}
			if(node.right!=null){
				System.out.print(node.val+".Right="+node.right.val+"\t");
				queue.add(node.right);
			}
			
			System.out.println("");
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