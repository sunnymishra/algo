package geeksForGeeksChallenge.tree;

import org.junit.Test;
import org.junit.Assert;
import java.util.*;
import java.util.stream.Collectors;


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
class NodeLocation{
	int verticalDist;
	int horizonDist;
	
	NodeLocation(int horizonDist, int verticalDist){
		this.horizonDist=horizonDist;
		this.verticalDist=verticalDist;

	}
	public String toString(){
		return " horizonDist="+this.horizonDist + " verticalDist="+this.verticalDist;
	}
}

public class _4_VerticalTreeTraversal{
	@Test
	public void test123(){
		int x=1002;
		int y=2123;
		long sum = (((long)x) << 32) | (y & 0xffffffffL);
		System.out.println("x="+x+" y="+y+ " sum="+sum);
		x = (int)(sum >> 32);
		y = (int)sum;
		System.out.println("x="+x+" y="+y+ " sum="+sum);
	}
	
	@Test
	public void verticalOrder1(){
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
		node2.left=node4;
		node2.right=node5;
		node3.left=node6;
		node3.right=node7;
		node6.right=node8;
		node7.right=node9;
		
		ArrayList<Integer> result = verticalOrder(node1);		
		System.out.println("Result:"+result);
		
		Assert.assertEquals(new ArrayList(Arrays.asList(4,2,1,5,6,3,8,7,9)), result);
		
	}
	@Test
	public void verticalOrder2(){
		Node node1=new Node(1);
		Node node2=new Node(2);
		Node node3=new Node(3);
		Node node4=new Node(4);
		Node node5=new Node(5);
		Node node6=new Node(6);

		node1.right=node2;
		node2.left=node3;
		node2.right=node4;
		node3.right=node5;
		node5.right=node6;
		
		ArrayList<Integer> result = verticalOrder(node1);		
		System.out.println("Result:"+result);
		
		Assert.assertEquals(new ArrayList(Arrays.asList(1,3,2,5,4,6)), result);
		
	}
	
	public ArrayList<Integer> verticalOrder(Node root){
		if(root==null) return new ArrayList<>();
		
		Comparator<NodeLocation> cmp = (node1, node2) -> {
			if(node1.horizonDist<node2.horizonDist) return -1;
			else if(node1.horizonDist==node2.horizonDist && node1.verticalDist==node2.verticalDist) return 1;
			else if(node1.horizonDist==node2.horizonDist && node1.verticalDist<node2.verticalDist) return -1;
			else return 1;
		};
		Map<NodeLocation, List<Integer>> map=new TreeMap<>(cmp);
		_dfsTraversal(root, 0,0, map);
		
		//System.out.println("DFS MAP Result: "+map);
		//list = _quickSort(list);
		ArrayList<Integer> result = new ArrayList<>();
		map.forEach((key, value) -> result.addAll(value));
		//System.out.println("QuickSort Result: "+result);
		return result;
	}
	private void _dfsTraversal(Node node, int horizonDist, int verticalDist, Map<NodeLocation, List<Integer>> map){
		if(node==null) return;
		
		NodeLocation currentNodeLocation = new NodeLocation(horizonDist, verticalDist);
		if(map.containsKey(currentNodeLocation)){
			map.get(currentNodeLocation).add(node.data);
		}else{
			List<Integer> list=new ArrayList<>();
			list.add(node.data);
			map.put(currentNodeLocation, list);
		}
		_dfsTraversal(node.left, horizonDist-1, verticalDist+1, map);
		_dfsTraversal(node.right, horizonDist+1, verticalDist+1, map);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}