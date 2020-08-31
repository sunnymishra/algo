package recursion;

import org.junit.Test;
import org.junit.Assert;
import java.lang.Math;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
	Tower of Hanoi is a classic Math puzzle and is a good example to understand how Recursion works.
	Reference: https://www.geeksforgeeks.org/c-program-for-tower-of-hanoi/
	
	Asymptotic analysis:
	Time complexity for Recursion algorithm = O(2^n) where n=no. of rings. THis is exponential complexity
	Reference: https://www.geeksforgeeks.org/time-complexity-analysis-tower-hanoi-recursion/
	
	Space complexity=O(n) where n is no. of rings. This is Linear complexity
	Reference: http://www.iitk.ac.in/esc101/08Jan/lecnotes/lecture32.pdf
	
	It will be easier to understand the Space and Time complexity for a Recursion solution if I draw a Tree structure usina Paper and Pen. Every Level deep the Tree grows, the total no. of level = Space complexity. Every single node in the Tree = Time complexity. THis is the best visual way to come up with Space & Time complexities.
	In the above Time complexity link I noticed the solution is using rigorous Math and used Geometric progression to solve to (2^n) but atleast basic idea can still be had by drawing the tree structure to understand visually.
*/
public class TowerOfHanoi{ 

	@Test
	public void testTowerofHanoiRecur(){
		List<Deque<Integer>> towers = new ArrayList<>();
		int numRings=5;
		int NUM_TOWERS=5;
		
		for (int i = 0; i < NUM_TOWERS ; i++) {
			towers.add(new LinkedList <Integer>());
		}
		// Initialize towers.
		for (int i = numRings; i >= 1 ; --i) {
			towers.get(0).addFirst(i);
		}
		
		System.out.println("\nINPUT:");
		for(int i=0; i<towers.size(); i++){
			System.out.print("Tower no. "+ i + ": "+ towers.get(i) + "\t||\t");
		}
		System.out.println("\n\nSTEPS:");
		_towerOfHanoiRecur(numRings, towers, 0, 1, 2);
		
		System.out.println("\nOUTPUT:");
		for(int i=0; i<towers.size(); i++){
			System.out.print("Tower no. "+ i + ": "+ towers.get(i) + "\t||\t");
		}
	}

	private static void _towerOfHanoiRecur(int numRings, List<Deque<Integer>> towers, int fromTower , int toTower, int intermediateTower) {
		if (numRings > 0) {
			_towerOfHanoiRecur(numRings - 1, towers, fromTower, intermediateTower, toTower);
			towers.get(toTower).addFirst(towers.get(fromTower).removeFirst());
			System.out.println("Move from peg " + fromTower + " to peg " + toTower);
			_towerOfHanoiRecur(numRings - 1, towers, intermediateTower, toTower, fromTower);
		}

	}
}