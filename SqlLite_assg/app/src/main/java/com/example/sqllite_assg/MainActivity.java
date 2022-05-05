package com.example.sqllite_assg;

import androidx.appcompat.app.AppCompatActivity;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listview);
        ArrayList<String> course_names = new ArrayList<>(), course_links = new ArrayList<>();

        try{

            SQLiteDatabase sql_lite_db = this.openOrCreateDatabase("coursesdb", MODE_PRIVATE, null);
            Cursor cursor = sql_lite_db.rawQuery("SELECT * FROM courses", null);
            int name_index = cursor.getColumnIndex("name");
            int link_index = cursor.getColumnIndex("link");
            cursor.moveToFirst();
            int i = 0;
            while (i < cursor.getCount()) {
                course_names.add(cursor.getString(name_index));
                course_links.add(cursor.getString(link_index));
                cursor.moveToNext();
                i++;
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, course_names);
            list.setAdapter(adapter);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}