package com.example.ahmed.ox;


import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public final class Utility {
    Utility(){}

    public static boolean isWinner(String [][]mBoard) {
        // Check Diagonals
        if (mBoard[0][0] == mBoard[1][1]
                && mBoard[0][0] == mBoard[2][2] && mBoard[0][0] != "")
            return true;

        if (mBoard[2][0] == mBoard[1][1]
                && mBoard[2][0] == mBoard[0][2] && mBoard[2][0] != "")
            return true;

        for (int i = 0; i < 3; i++) {
            // Check Rows
            if (mBoard[i][0] == mBoard[i][1]
                    && mBoard[i][1] == mBoard[i][2] && mBoard[i][0] != "")
                return true;

            // Check Columns
            if (mBoard[0][i] == mBoard[1][i]
                    && mBoard[1][i] == mBoard[2][i] && mBoard[0][i] != "")
                return true;
        }

        return false;
    }

    public static String [][] clear(String [][]board){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                board[i][j]="";
            }
        }
        return board;
    }
    public static View findView(int row, int colm, Activity context){
        TextView textView=null;
        switch (row) {
            case 0:
                switch (colm) {
                    case 0:
                        textView =(TextView) context.findViewById(R.id.cell11);
                        break;
                    case 1:
                        textView = (TextView) context.findViewById(R.id.cell12);
                        break;
                    case 2:
                        textView = (TextView) context.findViewById(R.id.cell13);
                        break;
                }
                break;
            case 1:
                switch (colm) {
                    case 0:
                        textView = (TextView) context.findViewById(R.id.cell21);
                        break;
                    case 1:
                        textView = (TextView) context.findViewById(R.id.cell22);
                        break;
                    case 2:
                        textView = (TextView) context.findViewById(R.id.cell23);
                        break;
                }
                break;
            case 2:
                switch (colm) {
                    case 0:
                        textView = (TextView) context.findViewById(R.id.cell31);
                        break;
                    case 1:
                        textView = (TextView) context.findViewById(R.id.cell32);
                        break;
                    case 2:
                        textView = (TextView) context.findViewById(R.id.cell33);
                        break;
                }
                break;
        }
        return textView;
    }
    public static int []  findCell(int id){
        int row=0,colm=0;
        switch (id){
            case R.id.cell11:
                row = 0; colm = 0; break;
            case R.id.cell12:
                row = 0; colm = 1; break;
            case R.id.cell13:
                row = 0; colm = 2; break;
            case R.id.cell21:
                row = 1; colm = 0; break;
            case R.id.cell22:
                row = 1; colm = 1; break;
            case R.id.cell23:
                row = 1; colm = 2; break;
            case R.id.cell31:
                row = 2; colm = 0; break;
            case R.id.cell32:
                row = 2; colm = 1; break;
            case R.id.cell33:
                row = 2; colm = 2; break;

        }
         int[] rId={row,colm};
        return rId;
    }
}
