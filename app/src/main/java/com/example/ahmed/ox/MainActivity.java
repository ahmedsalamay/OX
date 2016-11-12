package com.example.ahmed.ox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  String TAG = this.getClass().getSimpleName();
    private String [][] board=new String [3][3];
    private AIMiniMax mAiMiniMax=null;
    int counter=0,rowLocation=0,colmLocation=0;
    private String mMyMark="X",mOppMark="O";
    private boolean mOver=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board=Utility.clear(board);
        mAiMiniMax=new AIMiniMax(mMyMark);
    }
    public void cellClick(View view){
        TextView cell=(TextView)findViewById(view.getId());
        String cellValue=(String)cell.getText();
        if(cellValue==""&&!mOver){

            int id[]=Utility.findCell(cell.getId());
            rowLocation=id[0];
            colmLocation=id[1];
            board[rowLocation][colmLocation]=mMyMark;
            Log.e(TAG,"ROw "+rowLocation+" Col "+colmLocation+"\n" );
            cell.setText(mMyMark);
            counter++;
            Log.e(TAG,"Counter "+counter+"\n" );
            mOver=isOver(mMyMark);
            Log.e(TAG,"Over "+mOver+"\n" );
            if(!mOver)
                getAIMove(board);
            Log.e(TAG,"board "+board );
        }

    }
    private boolean isOver(String player){
        if(Utility.isWinner(board)){
            Toast.makeText(getApplicationContext(),player+"Win",Toast.LENGTH_SHORT).show();
            return true;
        }else if(counter>=9){
            Toast.makeText(getApplicationContext(),player+"Draw",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
    public void RadioClicked(View view){
        boolean checked=((RadioButton)view).isChecked();
        if(view.getId()==R.id.radioBoxY&&checked) {
            mMyMark="O";
            mOppMark="X";
        }
        if(view.getId()==R.id.radioBoxX&&checked){
            mMyMark="X";
            mOppMark="O";
        }
    }
    private void clear(){
        int IdList []={R.id.cell11,R.id.cell12,R.id.cell13, R.id.cell21, R.id.cell22,
                R.id.cell23, R.id.cell31, R.id.cell32, R.id.cell33 };
        TextView cell;
        for(int cellId:IdList){
            cell=(TextView) findViewById(cellId);
            cell.setText("");
        }
        board=Utility.clear(board);
        counter=0;
        mOver=false;

    }
public void resetClicked (View view){
    clear();
    if(mOppMark=="X")
        getAIMove(board);
}
    private void getAIMove(String [][] board) {
        Log.e(TAG,"We In GetAIMove"+board+"\n" );
        //Send the board to the AI for it to determine and return the move in an array {x,y}
        int[] move = mAiMiniMax.move(board,mMyMark);
        Log.e(TAG,"move Vlaue 0"+move[0]+"move of 1 "+move[1]+"\n" );
        TextView cell=(TextView) Utility.findView(move[0],move[1],this);

        if ( cell!=null &&cell.getText() == "") {
            board[move[0]][move[1]]=mOppMark;
            cell.setText(mOppMark);
            counter++;
            mOver = isOver(mOppMark);
        }
    }
}
