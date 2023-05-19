package com.example.urhome;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


  Button move;
  Button logout;
  ArrayAdapter<apart> arrayAdapter;
  List<apart> apartList;
 int clickedIndex = -1;
/*
    ListView lv_all;
    ArrayAdapter apartArrayAdapter;
*/

    private final ActivityResultLauncher<Intent> viewApartLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if(result.getData() != null){
                    apartList.get(clickedIndex).setRentEmail(result.getData().getStringExtra("result"));
                    arrayAdapter.notifyDataSetChanged();
                }
            });


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
        apartList = new DatabaseHelper(this).getEveryoneWithoutRented();
         arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,apartList);
        ListView listView = findViewById(R.id.apart_list_view);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ViewApartActivity.class);
                intent.putExtra("apart", (Serializable) apartList.get(i));
                 clickedIndex = i;
                 viewApartLauncher.launch(intent);
            }
        });


        move.setOnClickListener(v -> {
            Intent intentL = new Intent( MainActivity.this, AddDelete.class);
            startActivity(intentL);
        });


       logout.setOnClickListener(v -> {

           Intent intentL = new Intent( MainActivity.this, LoginActivity.class);
           startActivity(intentL);
           MyApplication.getInstance().removeUserEmail();
           finish();
           Toast.makeText(MainActivity.this, " Successfully log out " , Toast.LENGTH_SHORT).show();
       });



    }
/*
    public void ShowApartOnListView(DatabaseHelper database) {
        apartArrayAdapter = new ArrayAdapter<apart>(MainActivity.this, android.R.layout.simple_list_item_1, database.getEveryone());
        lv_all.setAdapter(apartArrayAdapter);
    }
*/

}