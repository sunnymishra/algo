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


public class _8_LCA{
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

		node7.left=node2;
		node7.right=node9;
		node2.left=node1;
		node2.right=node4;
		node9.left=node8;
		node4.left=node3;
		node4.right=node6;
		node6.left=node5;

		Assert.assertEquals(7, LCA(node7, 2,9).data);
		Assert.assertEquals(9, LCA(node7, 8,9).data);
		Assert.assertEquals(4, LCA(node7, 3,5).data);
		Assert.assertEquals(4, LCA(node7, 3,5).data);
		
		Assert.assertEquals(7, LCA(node7, 2,10).data);
	}
	
	public Node LCA(Node root, int n1, int n2){
		Node lca = root;
		
		while(root!=null){
			if(root.data<n1 && root.data<n2){
				root=root.right;
			}else if(root.data>n1 && root.data>n2){
				root=root.left;
			}else{
				lca=root;
				break;
			}
		}
		
		return lca;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}