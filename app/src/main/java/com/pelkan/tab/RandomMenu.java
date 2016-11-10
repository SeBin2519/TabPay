package com.pelkan.tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class RandomMenu extends AppCompatActivity implements View.OnClickListener{
    Button but_ok,but_cancel;
    EditText editPersonNum,editPrice;
    TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random);

        but_ok=(Button)findViewById(R.id.but_random_ok);
        but_cancel=(Button)findViewById(R.id.but_random_cancel);

        editPersonNum=(EditText)findViewById(R.id.edit_personNum);
        editPrice=(EditText)findViewById(R.id.edit_price);

        textResult=(TextView)findViewById(R.id.text_result);

        but_ok.setOnClickListener(this);
        but_cancel.setOnClickListener(this);
    }
    /**
     * Called when a view has been clicked.
     *
     * @paramv The view that was clicked.
     * */

    void showResult(){
        //   2개의 EditText에 입력된 값을 반환받는다.
        Random r=new Random();
        String editPersonNumStr=editPersonNum.getText().toString();
        String editPriceStr=editPrice.getText().toString();

        int peopleNum= Integer.parseInt(editPersonNumStr);
        int price= Integer.parseInt(editPriceStr);
        int realPrice=price;

        int moneyResult[]=new int[peopleNum];
        for(int j=0;j<peopleNum;j++) {
            moneyResult[j]=0;
        }

        int i=0;
        while(realPrice!=0){
            moneyResult[i]=r.nextInt(realPrice)*realPrice+100;
            realPrice -= moneyResult[i];
            if(moneyResult[i]%100!=0){
                realPrice+=moneyResult[i]%100;
                moneyResult[i]-=moneyResult[i]%100;
            }
            i++;
        }
        for(int j=0;j<peopleNum;j++) {
            String str;
            str="No."+(j+1)+" : "+moneyResult[j];
            textResult.append(str);
        }
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()){
            case R.id.but_random_ok:
                showResult();
                break;
            case R.id.but_random_cancel:
                editPersonNum.setText("");
                editPrice.setText("");
                break;
        }
    }
}
