package com.example.medicineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        SearchView searchview  = (SearchView) findViewById(R.id.search_view);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (CheckData(query)==0){
                    Intent intent = new Intent(MainActivity.this,ItemViewActivity.class);
                    intent.putExtra("name",query);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "입력한 이름은 약이 아닙니다.", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        Button btn_sn = findViewById(R.id.btn_sn);
        Button btn_sc = findViewById(R.id.btn_sc);
        btn_sn.setEnabled(false);
        btn_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ChooseActivity.class);
                startActivity(intent);
            }
        });
    }

    public int CheckData(String input){
        int checknum;
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HouseholdMedicine where mName =?",new String[] {input});
        if (cursor.moveToNext()==false){
            checknum=1;
        }
        else{
            checknum=0;
        }
        cursor.close();
        dbHelper.close();
        return checknum;
    }
}