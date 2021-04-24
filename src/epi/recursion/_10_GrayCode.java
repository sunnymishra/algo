package recursion;

import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class _10_GrayCode{
	@Test
	public void generateGrayCode(){
		
		List<Integer> result1 = generateGrayCode1(3);
		for(Integer i : result1)
			System.out.println(Integer.toBinaryString(i));
		
		List<Integer> result2 = generateGrayCode2(3);
		for(Integer i : result2)
			System.out.println(Integer.toBinaryString(i));
	}

	/**
		What is GrayScale sequence? Given an input value n, generate all Integers whose bit size should be equal to n and the Bit patterns of resulting Integers from 0 to 2^(n-1), such that each successive patterns differ by one bit.
		
		Time complexity of below solution: O(2^n), since Time complexity T(n) satisfies T(n) = T(n-1) + O(2"-1).
		
		Reference: https://www.geeksforgeeks.org/generate-n-bit-gray-codes/
	*/
	public List<Integer> generateGrayCode1(int bitLength) {
		if (bitLength == 0) {
			return new ArrayList<>(Arrays.asList(0));
		}
		// bit-index (bitLength - 1) begin with 0
		List<Integer> grayCodeNumBitsMinus1 = generateGrayCode1(bitLength - 1);
		// add a 1 at bit-index (bitLength - 1) to all entries in
		int leadingBitOne = 1 << (bitLength - 1);
		
		// Process in reverse order to achieve reflection of grayCodeNumBitsMinusl.
		for (int i = grayCodeNumBitsMinus1.size() - 1; i >= 0; --i) {
			grayCodeNumBitsMinus1.add(leadingBitOne | grayCodeNumBitsMinus1.get(i));
		}
		return grayCodeNumBitsMinus1;
	}
	
	
	/**
		This is iterative solution therefore this solution is much better in terms of Constant Space complexity
		
		For 3 BITs integers, GrayCode sequence will be -> 
		000, 001, 011, 010, 110, 111, 101, 100
		Thus: 	n=0 G(0)=000 , n=1 G(1)=001 , n=2 G(2)=011 , n=3 G(3)=010 , 
				n=4 G(4)=110 , n=5 G(5)=111 , n=6 G(6)=101 , n=7 G(7)=100
		From above sequence 1 relationship can be established between n and G(n)
		i.e. i-th bit of G(n) equals 1 only when i-th bit of n equals 1 and i+1-th bit equals 0 or the other way around (i-th bit equals 0 and i+1-th bit equals 1). 
		In bitwise manipulation technique, this can be written as G(n) = n ^ (n >> 1)
	
		Reference: https://cp-algorithms.com/algebra/gray-code.html
		
		If we repeat above method for 2^n times, then we can easily generate all Graycode sequences for Bit size n. Since at max we can have 2^n possible Integers with size n bit.
		
		Therefore,
		Time complexity = O(2^n) exponential
		Space complexity = O(1) constant
	*/
	private List<Integer> generateGrayCode2(int bitLength){
		int resultLength = 1 << bitLength; // This is 2 ^ bitLength
		List<Integer> result = new ArrayList<>();
		for(int i=0; i<resultLength; i++){
			int grayCode=(i>>1) ^ i;
			result.add(grayCode);
		}
		return result;
	}
	
}