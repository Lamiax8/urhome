package com.example.urhome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ViewApartActivity extends AppCompatActivity {
     DatabaseHelper databaseHelper =  new DatabaseHelper(this);
     Button rentButton;
     String result = "";
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_apart);
        rentButton = findViewById(R.id.rentBtn);
        apart apart = (com.example.urhome.apart) getIntent().getSerializableExtra("apart");
        ((TextView) findViewById(R.id.apart_item_name)).setText(apart.getName());
        ((TextView) findViewById(R.id.apart_item_price)).setText(String.valueOf(apart.getPrice()));
        ((TextView) findViewById(R.id.apart_item_location)).setText(apart.getLocation());
        ((TextView) findViewById(R.id.apart_item_rooms_count)).setText(String.valueOf(apart.getRooms()));
        result = apart.getRentEmail();
       if(!apart.getRentEmail().equals("NOT RENTED YET")) {
          rentButton.setText("return");
       }

        rentButton.setOnClickListener(view -> {
            if(!apart.getRentEmail().equals("NOT RENTED YET")) {
                apart.setRentEmail("NOT RENTED YET");
                result = "NOT RENTED YET";
                if(databaseHelper.updateApartment(apart.getId(), apart)){
                    rentButton.setText("rent");
                }
            }else{
                apart.setRentEmail(MyApplication.getInstance().getUserEmail());
                result = MyApplication.getInstance().getUserEmail();
                if(databaseHelper.updateApartment(apart.getId(), apart)){
                    rentButton.setText("return");
                }

            }


        });


    }

    @Override
    public void onBackPressed() {
        //Intent intent = new Intent(ViewApartActivity.this, MainActivity.class);
        Intent data = new Intent();
        data.putExtra("result", result);
        setResult(Activity.RESULT_OK, data);
        finish();
        super.onBackPressed();
    }
}