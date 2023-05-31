package com.example.medicineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton shape,color;
    Button[] button_c, button_s;
    GridLayout layout_c,layout_s;
    String l_color,l_character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);

        Button btn_sn = findViewById(R.id.btn_sn);
        Button btn_sc = findViewById(R.id.btn_sc);

        btn_sc.setEnabled(false);
        btn_sn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        layout_c = findViewById(R.id.Color);
        layout_s = findViewById(R.id.Shape);

        radioGroup = findViewById(R.id.RG);
        shape = findViewById(R.id.R_Shape);
        color = findViewById(R.id.R_Color);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.R_Color:
                        layout_c.setVisibility(View.VISIBLE);
                        layout_s.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.R_Shape:
                        layout_c.setVisibility(View.INVISIBLE);
                        layout_s.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        Button btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (l_color==null||l_character==null){
                    Toast.makeText(ChooseActivity.this,"특징 및 색상을 선택을 해주세요",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(ChooseActivity.this,ListActivity.class);
                    intent.putExtra("color",l_color);
                    intent.putExtra("character",l_character);
                    startActivity(intent);

                }

            }
        });
        /*Button btn_red = findViewById(R.id.bnt_red);
        btn_red.setOnClickListener(nineButton);*/


        int[] c_name = {R.id.bnt_red,R.id.bnt_orange,R.id.bnt_yellow,R.id.bnt_green,R.id.bnt_blue,R.id.bnt_indigo,R.id.bnt_violet,R.id.bnt_white,R.id.bnt_black};
        int[] s_name = {R.id.ellipse,R.id.circle,R.id.ball,R.id.triangle,R.id.rectangle,R.id.capsule,R.id.round_triangle,R.id.round_rectangle,R.id.pillar};

        button_c = new Button[c_name.length];
        button_s = new Button[s_name.length];

       for (int i=0;i<c_name.length;i++){
           button_c[i]=findViewById(c_name[i]);
           button_c[i].setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Button btn  = findViewById(view.getId());
                   l_color= btn.getText().toString();
                   Toast.makeText(ChooseActivity.this,l_color+"가 선태되었습니다.",Toast.LENGTH_SHORT).show();
               }
           });
       }
       for (int i=0;i<s_name.length;i++){
           button_s[i] = findViewById(s_name[i]);
           button_s[i].setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Button btn = findViewById(view.getId());
                   l_character = btn.getText().toString();
                   Toast.makeText(ChooseActivity.this,l_character+"가 선태되었습니다.",Toast.LENGTH_SHORT).show();
               }
           });
       }
    }

   /* View.OnClickListener nineButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bnt_red:
                    Toast.makeText(ChooseActivity.this,"빨강이 선태되었습니다.",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };*/
}