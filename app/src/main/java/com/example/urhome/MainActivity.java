package com.example.urhome;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


  Button move;
  Button logout;


/*
    ListView lv_all;
    ArrayAdapter apartArrayAdapter;
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        DatabaseHelper database = new DatabaseHelper(MainActivity.this);

        ShowApartOnListView(database);
*/
        move=findViewById(R.id.btn_move);
       logout=findViewById(R.id.logout);



        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentL = new Intent( MainActivity.this, AddDelete.class);
                startActivity(intentL);
            }
        });


       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intentL = new Intent( MainActivity.this, LoginActivity.class);
               startActivity(intentL);
               finish();
               Toast.makeText(MainActivity.this, " Successfully log out " , Toast.LENGTH_SHORT).show();
           }
       });



    }
/*
    public void ShowApartOnListView(DatabaseHelper database) {
        apartArrayAdapter = new ArrayAdapter<apart>(MainActivity.this, android.R.layout.simple_list_item_1, database.getEveryone());
        lv_all.setAdapter(apartArrayAdapter);
    }
*/

}