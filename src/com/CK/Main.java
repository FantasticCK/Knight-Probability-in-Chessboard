package com.CK;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
//        new Solution().knightProbability(3, 2, 0, 0);
        new Solution().knightProbability(8, 30, 6, 4);
    }
}

class Solution {
    private final static int[][] dir = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};

    public double knightProbability(int N, int K, int r, int c) {
        if (K == 0)
            return 1;

        double allPossibleMoves = Math.pow(8.0, K);

        BigInteger[][][] dp = new BigInteger[N][N][K + 1];
        dfs(N, r, c, K, dp);
        double res = dp[r][c][K].doubleValue() / allPossibleMoves;
        return res;

    }

    private BigInteger dfs(int N, int r, int c, int k, BigInteger[][][] dp) {
        if (k == 0) {
            if (posOnBoard(N, r, c)) {
                dp[r][c][k] = BigInteger.valueOf(1);
            } else {
                dp[r][c][k] = BigInteger.valueOf(0);
            }
            return dp[r][c][k];
        }

        if (dp[r][c][k] != null) {
            return dp[r][c][k];
        }

        BigInteger sum = BigInteger.valueOf(0);
        for (int i = 0; i < 8; i++) {
            int nextR = dir[i][0] + r, nextC = dir[i][1] + c;
            if (posOnBoard(N, nextR, nextC)) {
                sum = dfs(N, nextR, nextC, k - 1, dp).add(sum);
            }
        }
        dp[r][c][k] = sum;
        return dp[r][c][k];
    }

    private boolean posOnBoard(int N, int r, int c) {
        return r <= N - 1 && r >= 0 && c <= N - 1 && c >= 0;
    }
}


class Solution {
    private int[][]dir = new int[][]{{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1}};
    private double[][][] dp;
    public double knightProbability(int N, int K, int r, int c) {
        dp = new double[N][N][K + 1];
        return find(N,K,r,c);
    }
    public double find(int N,int K,int r,int c){
        if(r < 0 || r > N - 1 || c < 0 || c > N - 1) return 0;
        if(K == 0)  return 1;
        if(dp[r][c][K] != 0) return dp[r][c][K];
        double rate = 0;
        for(int i = 0;i < dir.length;i++)   rate += 0.125 * find(N,K - 1,r + dir[i][0],c + dir[i][1]);
        dp[r][c][K] = rate;
        return rate;
    }
}