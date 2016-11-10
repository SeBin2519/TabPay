package com.pelkan.tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Douch extends AppCompatActivity implements View.OnClickListener{
    ViewFlipper vFlipper;
    TextView textresult;
    Button butCheck,butCancel,butStart,butStop;
    EditText editPerson,editPrice;
    int person=0,price=0;
    int douchMoney=0;
    int change=0;
    int c;
    int perIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.douch);

        editPerson = (EditText) findViewById(R.id.edit_person);
        editPrice = (EditText) findViewById(R.id.edit_price);
        textresult=(TextView)findViewById(R.id.text_result);
        vFlipper =(ViewFlipper)findViewById(R.id.view_flip);
        butCheck =(Button)findViewById(R.id.but_douch_check);
        butCheck.setOnClickListener(this);
        butCancel =(Button)findViewById(R.id.but_douch_delete);
        butCancel.setOnClickListener(this);
        butStart =(Button)findViewById(R.id.but_start);
        butStart.setOnClickListener(this);
        butStop =(Button)findViewById(R.id.but_stop);
        butStop.setOnClickListener(this);

        //person=Integer.parseInt(editPerson.getText().toString());
        //price=Integer.parseInt(editPrice.getText().toString());
        vFlipper.setVisibility(View.INVISIBLE);
        butStart.setVisibility(View.INVISIBLE);
        butStop.setVisibility(View.INVISIBLE);
    }

   public void startRoulette(int person,int price){
       this.person=person;
       this.price=price;

       c=(price/person)%100;
       douchMoney=(price/person)-c;
       change=price-(person*douchMoney);

        if(change==0) {///남은 돈이 없다면
            textresult.setText("한 사람 당 "+douchMoney+"원을 내야합니다!");
            vFlipper.setVisibility(View.INVISIBLE);
            butStart.setVisibility(View.INVISIBLE);
            butStop.setVisibility(View.INVISIBLE);
        }
        else {
            vFlipper.setVisibility(View.VISIBLE);
            butStart.setVisibility(View.VISIBLE);
            butStop.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        String epMon=editPerson.getText().toString();
        String erMon=editPrice.getText().toString();

        switch(v.getId()){
            case R.id.but_douch_check:
                if(editPerson.equals("") || editPerson==null|| editPrice.equals("") || editPrice==null) Toast.makeText(this,"제대로 된 값을 입력해 주세요!", Toast.LENGTH_LONG).show();
                else{
                    person= Integer.parseInt(epMon);
                    price= Integer.parseInt(erMon);
                    startRoulette(person, price);
                }
                break;
            case R.id.but_douch_delete:
                editPerson.setText("");
                editPrice.setText("");
                vFlipper.setVisibility(View.INVISIBLE);
                butStart.setVisibility(View.INVISIBLE);
                butStop.setVisibility(View.INVISIBLE);
                break;
            case R.id.but_start:
                vFlipper.setFlipInterval(50);//viewFlipper에 넘겨지는 간격 설정 => 1000: 1초
                perIndex = (int)(Math.random()*person)+1;
                vFlipper.startFlipping();
                break;
            case R.id.but_stop:
                vFlipper.stopFlipping();
                textresult.setText("1인 지불 금액: "+douchMoney+"원 "+"\t"+"잔돈: "+change+"원 "+"\n"+"잔돈은 "+perIndex+"번째 사람이 내주세요!");
        }
    }
}
