package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;


public class _1_PrintLeftView{
	
	@Test
	public void leftView1(){
		Node root=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		Node node5=new Node(5);
		Node node6=new Node(6);
		Node node7=new Node(7);
		Node node8=new Node(8);
		root.left=node2;
		root.right=node3;
		node2.left=node4;
		node2.right=node5;
		node3.left=node6;
		node3.right=node7;
		node4.right=node8;

		Assert.assertEquals(new ArrayList(Arrays.asList(1,2,4,8)), leftView(root));
	}
	
	@Test
	public void leftView2(){
		Node root=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		Node node5=new Node(5);
		Node node6=new Node(6);
		Node node7=new Node(7);
		Node node8=new Node(8);
		Node node9=new Node(9);
		Node node10=new Node(10);
		root.left=node2;
		root.right=node3;
		node2.left=node4;
		node2.right=node5;
		node5.left=node6;
		node5.right=node7;
		node6.right=node8;
		node7.left=node9;
		node9.right=node10;

		Assert.assertEquals(new ArrayList(Arrays.asList(1,2,4,6,8,10)), leftView(root));
	}
	
	public ArrayList<Integer> leftView(Node root){
		ArrayList<Integer> result = new ArrayList<Integer>();
		_leftView(root, result, 0, -1);
		
		return result;
		
	}
	
	private int _leftView(Node current, ArrayList<Integer> result, int currentDepth, int maxDepthSoFar){
		if(current==null){
			return maxDepthSoFar;
		}
		if(currentDepth>maxDepthSoFar){
			result.add(current.data);
		}
		
		maxDepthSoFar = Math.max(currentDepth, maxDepthSoFar);
		
		maxDepthSoFar = _leftView(current.left, result, currentDepth+1, maxDepthSoFar);
		maxDepthSoFar = _leftView(current.right, result, currentDepth+1, maxDepthSoFar);
		
		return maxDepthSoFar;
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
