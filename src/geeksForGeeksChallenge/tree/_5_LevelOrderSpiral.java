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
		return "data:"+this.data;
	}
}
public class _5_LevelOrderSpiral{
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
		node1.left=node2;
		node1.right=node3;
		node2.left=node7;
		node2.right=node6;
		node3.left=node5;
		node3.right=node4;
		node6.right=node8;
		node4.left=node9;

		Assert.assertEquals(new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9)), findSpiral(node1));
		
	}
	
	public ArrayList<Integer> findSpiral(Node root){
		if(root==null) return new ArrayList();
		
		Queue<Integer> queue=new LinkedList<>();
		Stack<Integer> stack=new Stack<>();
		ArrayList<Integer> result=new ArrayList<>();
		
		queue.add(root);
		while(!queue.isEmpty() && queue.peek()!=Integer.MAX_VALUE){
			Node current=queue.pop();
			boolean wasQueueEmpty=false;
			if(queue.isEmpty()){
				wasQueueEmpty=true;
			}
			if(current.left!=null)
				stack.push(current.left);
			if(current.right!=null)
				stack.push(current.right);
			
			if(wasQueueEmpty){
			while(!stack.isEmpty()){
				
				
			}
				
			
			}
			
			
			
		}
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}