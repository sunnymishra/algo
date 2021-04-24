package general;

import java.util.Arrays;
import java.util.HashMap;

public class Test123 {
	public static void main(String[] args) {
		
		System.out.println(solution(5,8));
//		System.out.println(solution(4,10));
//		System.out.println(solution(1,2));
//		System.out.println(solution(10,5));
	}
	
	public static int solution(int N, int K) {
		if(N<1 || N>1000000 || K<1 || K>1000000000) return -1;
  
		int result = _solution(N, K);
		if(result==Integer.MAX_VALUE) return -1;
		else return result;
		
    }
	
	public static int _solution(int N, int K) {
		if(K==0) return 0;
		if(N<=0) return Integer.MAX_VALUE;
		if(K<0) return Integer.MAX_VALUE;
		
		int sol1 = _solution(N-1, K-N);
		if(sol1!=Integer.MAX_VALUE) sol1++;
		
		int sol2 = _solution(N-1, K);
		
        return Integer.min(sol1, sol2);
    }
	
	
//	public static int solution(int[] A) {
//		if(A==null || A.length==0) return -1;
//  
//        int expectedTotal=A.length*(A.length+1)/2;
//        
//        int arrayTotal1=0;
//        for(int i=0;i<A.length;i++) {
//        	arrayTotal1+=A[i];
//        }
//        int result=Math.abs(arrayTotal1 - expectedTotal);
//        
//        if(result>1000000000) result=-1;
//        return result;
//    }
	
//		public static int solution(int[] A) {
//			if(A==null || A.length==0) return -1;
//	        HashMap<Integer, Integer> mp = new HashMap<>(); 
//	  
//	        for (int i = 0; i < A.length; i++) 
//	        { 
//	            if (mp.get(A[i]) != null) 
//	            { 
//	                int x = mp.get(A[i]); 
//	                mp.put(A[i], ++x); 
//	            } 
//	            else
//	                mp.put(A[i], 1); 
//	        } 
//	        
//	        int duplicateTotal = 0;
//	        int arrayTotal = 0;
//	  
//	        for (HashMap.Entry<Integer, Integer> entry : mp.entrySet()) { 
//	        	arrayTotal+=entry.getValue();
//	            if (entry.getValue() > 1) { 
//	                duplicateTotal += (entry.getValue() - 1); 
//	            } 
//	        }
//	        int expectedTotal=A.length*(A.length+1)/2;
//	        
//	        int arrayTotal1=0;
//	        for(int i=0;i<A.length;i++) {
//	        	arrayTotal1+=A[i];
//	        }
//	        int result=Math.abs(arrayTotal1 - expectedTotal);
//	        
////	        int missingTotal=arrayTotal - expectedTotal;
////	        int result=duplicateTotal-missingTotal;
//	        if(result>1000000000) result=-1;
//	        return result;
//	    }
}
