package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _18_TeamScoreSequence{
	@Test
	public void teamScoreSequence(){
		Assert.assertEquals(5, teamScoreSequence2(new int[]{1,2}, 2, 1));
		
	}
	
	/**
		Reference: https://stackoverflow.com/a/13714149/1316967
		If there is a basketball match and the 2 team's final scores are (TeamA=3, TeamB=2), then what are the possible ways in which teams which started with 0, 0 could have reached that final score? Given that in Basketball any team is allowed to score among following 3 points at any time : 1, 2
		
	**/
	public int teamScoreSequence1(int[] points, int teamAScore, int teamBScore){
		if(points==null || points.length==0){
			return 0;
		}
		int combinations= _teamScoreSequence1(points, teamAScore, teamBScore);
		System.out.println("No. of ways: "+ combinations);
		return combinations;
	}
	
	private int _teamScoreSequence1(int[] points, int teamAScore, int teamBScore){
		if(teamAScore<0 || teamBScore<0){
			return 0;
		}
		if(teamAScore==0 && teamBScore==0){
			return 1;
		}
		int sum=0;
		for(int i=0; i<points.length; i++){
			int score1 = _teamScoreSequence1(points, teamAScore-points[i], teamBScore); // Team A score combinations
			int score2 = _teamScoreSequence1(points, teamAScore, teamBScore-points[i]); // Team B score combinations
			sum=sum + score1+score2;
		}
		return sum;
	}
	
	
	public int teamScoreSequence2(int[] points, int teamAScore, int teamBScore){
		if(points==null || points.length==0){
			return 0;
		}
		System.out.println("teamAScore: "+ teamAScore + " teamBScore: "+teamBScore);
		int[][]cache=new int[teamAScore][teamBScore];
		
		int combinations= _teamScoreSequence2(points, teamAScore, teamBScore, cache);
		for(int i=0;i<teamAScore;i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return combinations;
	}
	
	private int _teamScoreSequence2(int[] points, int teamAScore, int teamBScore, int[][]cache){
		if(teamAScore<1 || teamBScore<1){
			return 0;
		}
		if(teamAScore==0 && teamBScore==0){
			return 1;
		}
		if(cache[teamAScore-1][teamBScore-1]!=0){
			return cache[teamAScore-1][teamBScore-1];
		}
		int sum=0;
		for(int i=0; i<points.length; i++){
			int score1 = _teamScoreSequence2(points, teamAScore-points[i], teamBScore, cache); // Team A score combinations
			int score2 = _teamScoreSequence2(points, teamAScore, teamBScore-points[i], cache); // Team B score combinations
			sum=sum + score1+score2;
		}
		cache[teamAScore-1][teamBScore-1]=sum;
		return sum;
	}
	
}