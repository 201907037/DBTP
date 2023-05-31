package com.example.medicineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView listview;
    private csVO csvo;
    private ArrayList<csVO> alist;
    private CustomAdapter adapter;
    private String color,character;


    public void searchData(){
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT mName,Picture,Characteristic FROM SearchList where Characteristic =? and Color=?",new String[] {character,color});

        while(cursor.moveToNext()){
            csvo = new csVO();
            csvo.setName(cursor.getString(0));
            byte[] bytes = cursor.getBlob(1);
            Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            csvo.setImg(bm);
            csvo.setCharacteristic(cursor.getString(2));
            alist.add(csvo);
        }
        cursor.close();
        dbHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);

        Intent intent = getIntent();
        color = intent.getStringExtra("color");
        character = intent.getStringExtra("character");



        alist = new ArrayList<csVO>();
        searchData();
        listview = findViewById(R.id.listview);
        adapter = new CustomAdapter(this);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                csVO csv = alist.get(i);
                String name = csv.getName();
                Intent intent = new Intent(ListActivity.this, ItemViewActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                //Toast.makeText(ListActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });



        Button btn_sn = findViewById(R.id.btn_sn);
        Button btn_sc = findViewById(R.id.btn_sc);
        btn_sn.setOnClickListener(onclick);
        btn_sc.setOnClickListener(onclick);
    }


    private class CustomAdapter extends BaseAdapter{
        Context context;
        public CustomAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {

            return alist.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View listview = View.inflate(context,R.layout.list_activity,null);
            ImageView listimg = listview.findViewById(R.id.list_img);
            TextView listname = listview.findViewById(R.id.list_name);
            TextView listcharacteristic = listview.findViewById(R.id.list_characteristic);

            listimg.setImageBitmap(alist.get(i).getImg());
            listname.setText(alist.get(i).getName());
            listcharacteristic.setText(alist.get(i).getCharacteristic());

            return listview;
        }
    }

    View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_sn:
                    Intent intent1 = new Intent(ListActivity.this,MainActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_sc:
                    Intent intent2 = new Intent(ListActivity.this,ChooseActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };
}