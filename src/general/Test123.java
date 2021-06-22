package general;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;



public class Test123 {
	@Test
	public void testBitWise() {
		int x = -5;
		int y = 7;
		long z = x;
		z = (z << 32) | y;
		System.out.println("BEFORE \nx="+x + " y="+y + " z="+z);
		System.out.println("x="+Integer.toBinaryString(x) + " y="+Integer.toBinaryString(y)+ " z=" +Long.toBinaryString(z));
		
		
		int a = (int) z >> 32;
		System.out.println(" z=" +Long.toBinaryString(z));
		
		int b = (int) z & 0xffffffff;
		System.out.println("AFTER \na="+a + " b="+b + " z="+z);
		System.out.println("a="+Integer.toBinaryString(a) + " b="+Integer.toBinaryString(b)+ " z=" +Long.toBinaryString(z));
		
		int m=65535;
		System.out.println("m="+Integer.toBinaryString(m));
		
		System.out.println("0xf="+Integer.toBinaryString(0xf) + " 0xff="+Integer.toBinaryString(0xff)+ " 0xfff="+Integer.toBinaryString(0xfff)+ " 0xffff="+Integer.toBinaryString(0xffff));
		
		System.out.println("240="+Integer.toBinaryString(240) + " 0xf0="+Integer.toBinaryString(0xf0));
		
		
	}
	
}
