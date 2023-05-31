package com.example.medicineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemViewActivity extends AppCompatActivity {

    ImageView img;
    TextView DB_name,DB_classification,DB_characteristic,DB_detail;
    Intent getIntent;
    String input;
    public void getData() {
        int Detail_num,Category_num;
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM HouseholdMedicine where mName =?",new String[] {input});


        while (cursor.moveToNext()) {
            Detail_num = cursor.getInt(0);
            DB_name.setText(cursor.getString(1));
            Category_num = cursor.getInt(2);

            cursor = db.rawQuery("SELECT * FROM Details WHERE mNum=" + Detail_num, null);
            while (cursor.moveToNext()) {
                    DB_detail.setText(cursor.getString(1));
                    DB_characteristic.setText(cursor.getString(2));
                    byte[] bytes = cursor.getBlob(3);
                    Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    img.setImageBitmap(bm);
                }

                cursor = db.rawQuery("SELECT * FROM Category where CategoryNum=" + Category_num, null);
                while (cursor.moveToNext()) {
                    DB_classification.setText(cursor.getString(1));
                }
            }



        cursor.close();
        dbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);

        img = (ImageView) findViewById(R.id.m_img);
        DB_name = (TextView) findViewById(R.id.DB_name);
        DB_classification= (TextView) findViewById(R.id.DB_classification);
        DB_characteristic = (TextView) findViewById(R.id.DB_characteristic);
        DB_detail = (TextView) findViewById(R.id.DB_detail);

        getIntent = getIntent();
        input = getIntent.getStringExtra("name");

        getData();



    }
}