package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;

class Width{
	int leftMaxDist;
	int rightMaxDist;
	
	public Width(int leftMaxDist, int rightMaxDist){
		this.leftMaxDist=leftMaxDist;
		this.rightMaxDist=rightMaxDist;
	}
}
class Node{
	Node left;
	Node right;
	int data;
	int hd;
	
	Node(int data){
		this.data=data;
		this.hd=Integer.MAX_VALUE;
	}
	public String toString(){
		return "data:"+this.data + " hd:"+this.hd;
	}
}
public class _3_TreeBottomView{
	
	@Test
	public void bottomView1(){
		Node node3=new Node(3);
		Node node5=new Node(5);
		Node node8=new Node(8);
		Node node10=new Node(10);
		Node node14=new Node(14);
		Node node17=new Node(17);
		Node node20=new Node(20);
		Node node22=new Node(22);
		Node node25=new Node(25);
		Node node29=new Node(29);
		
		node20.left=node8;
		node20.right=node22;
		node8.left=node5;
		node8.right=node3;
		node22.right=node25;
		node3.left=node10;
		node3.right=node14;
		node25.left=node29;
		node10.left=node17;

		Assert.assertEquals(new ArrayList(Arrays.asList(17,10,3,29,25)), bottomView(node20));
	}
	
	@Test
	public void bottomView2(){
		Node node3=new Node(3);
		Node node5=new Node(5);
		Node node8=new Node(8);
		Node node10=new Node(10);
		
		node3.right=node5;
		node5.right=node8;
		//node8.right=node10;

		Assert.assertEquals(new ArrayList(Arrays.asList(3,5,8)), bottomView(node3));
		
		node8.right=node10;
		Assert.assertEquals(new ArrayList(Arrays.asList(3,5,8,10)), bottomView(node3));
	}
	
	@Test
	public void bottomView3(){
		Node node3=new Node(3);
		Node node5=new Node(5);
		Node node8=new Node(8);
		Node node10=new Node(10);
		
		node3.left=node5;
		node5.left=node8;
		Assert.assertEquals(new ArrayList(Arrays.asList(8,5,3)), bottomView(node3));
		
		node8.left=node10;
		Assert.assertEquals(new ArrayList(Arrays.asList(10,8,5,3)), bottomView(node3));
	}
	@Test
	public void bottomView4(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		
		node1.left=node2;
		node2.left=node3;
		node1.right=node4;
		Assert.assertEquals(new ArrayList(Arrays.asList(3,2,1,4)), bottomView(node1));
		
		//node8.left=node10;
		//Assert.assertEquals(new ArrayList(Arrays.asList(10,8,5,3)), bottomView(node3));
	}
	@Test
	public void bottomView5(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		
		node1.right=node2;
		node2.right=node3;
		node1.left=node4;
		Assert.assertEquals(new ArrayList(Arrays.asList(4,1,2,3)), bottomView(node1));
		
		//node8.left=node10;
		//Assert.assertEquals(new ArrayList(Arrays.asList(10,8,5,3)), bottomView(node3));
	}
	
	public ArrayList<Integer> bottomView(Node root){
		
		if(root==null) return new ArrayList<>();
		
		Width width = _fillTreeWidth(root, 0, 0, 0);
		int treeWidth = Math.abs(width.leftMaxDist) + Math.abs(width.rightMaxDist) + 1;

		ArrayList<Integer> result=new ArrayList(treeWidth);
		for(int i=0; i<treeWidth;i++){
			result.add(-1);
		}
		
		_printTree(root);
		System.out.println("treeWidth:"+treeWidth);
		
		_fillBottomView(root, result, width);
		
		return result;
		
	}
	
	private Width _fillTreeWidth(Node node, int hd, int leftMaxDist, int rightMaxDist){
		if(node==null) return new Width(0,0);
		
		if(node.hd==Integer.MAX_VALUE){
			node.hd=hd;
		}
		leftMaxDist=Math.min(leftMaxDist, node.hd);
		rightMaxDist=Math.max(rightMaxDist, node.hd);
		
		Width treeWidth1 = _fillTreeWidth(node.left, hd-1, leftMaxDist, rightMaxDist);
		Width treeWidth2 = _fillTreeWidth(node.right, hd+1, leftMaxDist, rightMaxDist);
		
		int childLeftMaxDist = Math.min(treeWidth1.leftMaxDist, treeWidth2.leftMaxDist);
		int childRightMaxDist = Math.max(treeWidth1.rightMaxDist, treeWidth2.rightMaxDist);
		
		leftMaxDist = Math.min(leftMaxDist, childLeftMaxDist);
		rightMaxDist = Math.max(rightMaxDist, childRightMaxDist);
		
		
		return new Width(leftMaxDist, rightMaxDist);
	}
	
	private void _fillBottomView(Node current, ArrayList<Integer> result, Width width){
		if(current==null){
			return;
		}

		int currentIdx = Math.abs(width.leftMaxDist) + current.hd;
		
		System.out.println(" CurrentNode:"+current+" currentIdx:"+currentIdx);
		result.set(currentIdx, current.data);
		
		_fillBottomView(current.left, result, width);
		_fillBottomView(current.right, result, width);
		
	}
	
	
	private void _printTree(Node current){
		if(current==null){
			return;
		}
		System.out.println(current);
		
		_printTree(current.left);
		_printTree(current.right);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}