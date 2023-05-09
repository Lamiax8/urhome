package com.example.urhome;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       logout=findViewById(R.id.logout);
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intentL = new Intent( MainActivity.this, LoginActivity.class);
               startActivity(intentL);
               finish();
               Toast.makeText(MainActivity.this, " Succesfully log out " , Toast.LENGTH_SHORT).show();
           }
       });




    }










}