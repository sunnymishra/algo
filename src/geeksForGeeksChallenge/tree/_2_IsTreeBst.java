package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;


public class _2_IsTreeBst{
	
	@Test
	public void isBst1(){
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
		
		node9.left=node2;
		node9.right=node10;
		node2.left=node1;
		node2.right=node5;
		node10.right=node14;
		node5.left=node3;
		node5.right=node8;
		node14.left=node12;
		node3.right=node4;
		node8.left=node6;
		node12.left=node11;
		node12.right=node13;
		node6.right=node7;

		Assert.assertTrue(isBST1(node9));
		Assert.assertTrue(isBST2(node9));
	}
	
	@Test
	public void isBst2(){
		Assert.assertFalse(isBST1(null));
		Assert.assertFalse(isBST2(null));
		
		Assert.assertTrue(isBST1(new Node(10)));
		Assert.assertTrue(isBST2(new Node(10)));
		
		Node node1=new Node(1);
		Node node2=new Node(2);
		node1.left=node2;
		Assert.assertFalse(isBST1(node1));
		Assert.assertFalse(isBST2(node1));
		
		node1=new Node(1);
		node2=new Node(2);
		node1.right=node2;
		Assert.assertTrue(isBST1(node1));
		Assert.assertTrue(isBST2(node1));
	}
	
	@Test
	public void isBst3(){
		
		Node node6=new Node(6);
		Node node8=new Node(8);
		Node node12=new Node(12);
		Node node13=new Node(13);
		Node node14=new Node(14);
		Node node18=new Node(18);
		
		node6.right=node13;
		node13.left=node12;
		node13.right=node18;
		node12.left=node8;
		node12.right=node14;
		
		Assert.assertFalse(isBST1(node6));
		Assert.assertFalse(isBST2(node6));
		
		node13.data=16;
		Assert.assertTrue(isBST1(node6));
		Assert.assertTrue(isBST2(node6));
	}
	
	@Test
	public void isBst4(){
		
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node10=new Node(10);
		Node node13=new Node(13);
		Node node15=new Node(15);
		
		node2.right=node15;
		node15.left=node10;
		node10.left=node1;
		node10.right=node13;
		
		Assert.assertFalse(isBST1(node2));
		Assert.assertFalse(isBST2(node2));
		
		node1.data=5;
		Assert.assertTrue(isBST1(node2));
		Assert.assertTrue(isBST2(node2));
	}
	
	/**
		This method checks if DFS traversal results in increasing (sorted) order for every next element found in DFS traversal.
		This costs extra Space complexity of O(n) instead of usual O(h)
	*/
	public boolean isBST1(Node root){
		List list= new ArrayList<>();
		_dfs(root, list);
		
		//System.out.println("\nresult="+list);
		if(isListSorted(list)){
			return true;
		}else{
			return false;
		}
		
	}
	private void _dfs(Node node, List<Integer> list){
		if(node!=null){
			_dfs(node.left, list);
			list.add(node.data);
			_dfs(node.right, list);
			
		}
		
	}
	private boolean isListSorted(List<Integer> list){
		if(list==null || list.isEmpty()) return false;
		for(int i=0; i<list.size()-1; i++){
			if(list.get(i)>=list.get(i+1)){
				return false;
			}
		}
		return true;
	}
	
	/*
		This method is Optimized for space.
		Space complexity O(h)
	*/
	public boolean isBST2(Node root){
		if(root==null) return false;
		
		return _isBst2(root, -1, -1, false, true);
	}
	
	private boolean _isBst2(Node node, int parentVal, int superParentVal, boolean 	isCurrentNodeLeftChild, boolean isRoot){
		if(node==null){
			return true;
		}
		
		boolean isBst=false;
		if(isRoot ||
		(isCurrentNodeLeftChild && parentVal==superParentVal && node.data<parentVal) || 
		(isCurrentNodeLeftChild && parentVal!=superParentVal && node.data>superParentVal && node.data<parentVal) ||
		(!isCurrentNodeLeftChild && parentVal==superParentVal && node.data>parentVal) ||
		(!isCurrentNodeLeftChild && parentVal!=superParentVal && node.data<superParentVal && node.data>parentVal)){

			boolean isLeftBst = _isBst2(node.left, node.data, ((isRoot || !isCurrentNodeLeftChild)?node.data : parentVal), true, false);
			boolean isRightBst = _isBst2(node.right, node.data, ((isRoot || isCurrentNodeLeftChild)?node.data : parentVal), false, false);
			
			isBst=isLeftBst && isRightBst;
		}else{
			isBst=false;
		}
		
		return isBst;
		
		
	}
	
	
}
class Node{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}