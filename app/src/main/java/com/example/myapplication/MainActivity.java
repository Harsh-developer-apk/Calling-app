package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
LottieAnimationView view;
Spinner spinner;
AutoCompleteTextView Search;
ArrayList<String> list1 = new ArrayList<>();
ArrayList<String> list2 = new ArrayList<>();
ArrayList<String> list3 = new ArrayList<>();
Button button1;
int arr[] = {1,2,3,4,5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        ListView list = findViewById(R.id.listview);
        Intent recycle = new Intent(context,Recycle.class);
        button1 = findViewById(R.id.button1);
        spinner  =  findViewById(R.id.Spin);
        Search = findViewById(R.id.SearchBar);
        // ListView
        list1.add("Harsh");
        list1.add("Moryan");
        list1.add("Vidul");
        list1.add("Rohit");
        list1.add("Deepak");
        list1.add("Puneet");
        list1.add("Jerry");
        list1.add("Vishal");
        list1.add("Himanshu");
        list1.add("Shivam");
        list1.add("sayan");
        list1.add("chetanya");
        list1.add("SaveTheDay");
        list1.add("Destroyed");
        list1.add("Police");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(MainActivity.this, "Bsdk asli Phone Nhi hai yeh", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Spinner
        list2.add("Aadhar Card");
        list2.add("PAN Card");
        list2.add("Voter ID Card");
        list2.add("Driving License Card");

        ArrayAdapter<String> adapter2  = new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,list2);
        spinner.setAdapter(adapter2);

        // Auto Complete Text View
        list3.add("Harsh");
        list3.add("Moryan");
        list3.add("Vidul");
        list3.add("Rohit");
        list3.add("Deepak");
        list3.add("Puneet");
        list3.add("Jerry");
        list3.add("Himanshu");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,list3);
        Search.setAdapter(adapter3);
        Search.setThreshold(1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(recycle);
            }
        });
    }

}