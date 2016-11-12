package com.example.ahmed.ox;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AIMiniMax {
   String [][]cells=new String[3][3];
    private String mMyMark;
    private String mOppMark;

    AIMiniMax(String myMark){
        mMyMark=myMark;
        mOppMark=(mMyMark=="X")?"O":"X";
    }
    public int [] move(String[][] board,String marker){
        mMyMark = marker;
        mOppMark = (mMyMark == "X") ? "O" : "X";
        cells=board;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                Log.e("TAG","board"+ cells[i][j]+" ");
            }
        }
        int [] result=minmax(2,mMyMark);
        return new int[]{ result[1],result[2]};
    }
    private int [] minmax(int depth,String marker) {
        List<int[]> nextMovees = generateNextMoves();
        int bestScore = (marker == mMyMark) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (nextMovees.isEmpty() || depth == 0) {
            bestScore = evaluate();
        } else {
            for (int[] move : nextMovees) {
                // Try this move for the current "player"
                cells[move[0]][move[1]] = marker;
                if (marker == mMyMark) {  // mySeed (computer) is maximizing player
                    currentScore = minmax(depth - 1, mOppMark)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {  // oppSeed is minimizing player
                    currentScore = minmax(depth - 1, mMyMark)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                // Undo move
                cells[move[0]][move[1]] = "";
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }

    private List<int[]>generateNextMoves(){
        List<int[]>nextMoves=new ArrayList<int[]>();
        if(Utility.isWinner(cells))return nextMoves;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(cells[i][j]==""){
                    nextMoves.add(new int[]{i,j});
                }
            }
        }
        return nextMoves;
    }

    private int evaluate() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += evaluateLine(0, 0, 0, 1, 0, 2);  // row 0
        score += evaluateLine(1, 0, 1, 1, 1, 2);  // row 1
        score += evaluateLine(2, 0, 2, 1, 2, 2);  // row 2
        score += evaluateLine(0, 0, 1, 0, 2, 0);  // col 0
        score += evaluateLine(0, 1, 1, 1, 2, 1);  // col 1
        score += evaluateLine(0, 2, 1, 2, 2, 2);  // col 2
        score += evaluateLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += evaluateLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
        return score;
    }

    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (cells[row1][col1] == mMyMark) {
            score = 1;
        } else if (cells[row1][col1] ==  mOppMark) {
            score = -1;
        }

        // Second cell
        if (cells[row2][col2] == mMyMark) {
            if (score == 1) {   // cell1 is mySeed
                score = 10;
            } else if (score == -1) {  // cell1 is  mOppMark
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (cells[row2][col2] ==  mOppMark) {
            if (score == -1) { // cell1 is  mOppMark
                score = -10;
            } else if (score == 1) { // cell1 is mySeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (cells[row3][col3] == mMyMark) {
            if (score > 0) {  // cell1 and/or cell2 is mySeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (cells[row3][col3] ==  mOppMark) {
            if (score < 0) {  // cell1 and/or cell2 is oppSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is mySeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        return score;
    }


}
