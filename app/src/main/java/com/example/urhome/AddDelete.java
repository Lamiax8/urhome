package com.example.urhome;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddDelete extends AppCompatActivity {

    Button ap_add , home;
    EditText ap_name, ap_price , ap_location ,ap_rooms;
    ListView lv_ap;
    ArrayAdapter apartArrayAdapter;
    DatabaseHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_delete);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // on create, give value

        home=findViewById(R.id.btn_home);
        ap_add = findViewById(R.id.ap_add);
        ap_name=findViewById(R.id.ap_name);
        ap_price = findViewById(R.id.ap_price);
        ap_location=findViewById(R.id.ap_location);
        ap_rooms = findViewById(R.id.ap_rooms);
        lv_ap = findViewById(R.id.lv_ap);

        database = new DatabaseHelper(AddDelete.this);
        //clear(database);//
        ShowApartOnListView(database);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentL = new Intent( AddDelete.this, MainActivity.class);
                startActivity(intentL);
            }
        });




        ap_add.setOnClickListener( new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           // create model
                                           apart apartMod = null;

                                           // create model

                                           String ApName = ap_name.getText().toString();
                                           String ApPrice = ap_price.getText().toString();
                                           String ApLocation = ap_location.getText().toString();
                                           String ApRooms = ap_rooms.getText().toString();

                                           if (ApName.equals("") || ApPrice.equals("") || ApLocation.equals("") || ApRooms.equals("")) {

                                               Toast.makeText(AddDelete.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                                           } else {
                                               apartMod = new apart(-1, ap_name.getText().toString(), Integer.parseInt(ap_price.getText().toString()), ap_location.getText().toString(), Integer.parseInt(ap_rooms.getText().toString()),"NOT RENTED YET");
                                               Toast.makeText(AddDelete.this, apartMod.toString(), Toast.LENGTH_SHORT).show();


                                               DatabaseHelper database = new DatabaseHelper(AddDelete.this);
                                               boolean b = database.addOne(apartMod);
                                               String s;
                                               if (b) {
                                                   s = "ADDED SUCCESSFULLY";

                                               } else {
                                                   s = "ADD FAILED";
                                               }
                                               Toast.makeText(AddDelete.this, s, Toast.LENGTH_SHORT).show();
                                           }

                                           ShowApartOnListView(database);

                                           database = new DatabaseHelper(AddDelete.this);
                                           ShowApartOnListView(database);

                                       }

                                   }
                );

                        lv_ap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                apart ClickedApart = (apart) adapterView.getItemAtPosition(i);
                database.DeleteOne(ClickedApart);
                ShowApartOnListView(database);
                Toast.makeText(AddDelete.this,   ClickedApart.toString() + " is DELETED", Toast.LENGTH_SHORT).show();
            }
        });






    }

    private void ShowApartOnListView(DatabaseHelper database) {
        apartArrayAdapter = new ArrayAdapter<>(AddDelete.this, android.R.layout.simple_list_item_1, database.getEveryone());
        lv_ap.setAdapter(apartArrayAdapter);
    }



}