package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {
    RecyclerView Clist;
    ArrayList<contactModel> contactList = new ArrayList<>();
    ContactAdapter contactAdapter = new ContactAdapter(this,contactList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        androidx.appcompat.widget.SearchView searchView;
        searchView = findViewById(R.id.SearchContact);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        Clist = findViewById(R.id.Contact);
        Clist.setLayoutManager(new LinearLayoutManager(this));
        contactModel contact = new contactModel(R.drawable.img,"Harsh","8800722841","harsh.sh.3878@gmail.com");
        contactList.add(contact);
        contactList.add(new contactModel(R.drawable.vidul,"Vidul","9910494344",""));
       contactList.add(new contactModel(R.drawable.moryan,"Moryan","8882416067",""));
        contactList.add(new contactModel(R.drawable.rohit,"Rohit", "6969669969",""));
        contactList.add(new contactModel(R.drawable.deepak,"Deepak","7703842969",""));
        contactList.add(new contactModel(R.drawable.puneet,"Puneet","9301508758",""));
        contactList.add(new contactModel(R.drawable.jerry,"Vishal","9315669548",""));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Viduldk","9910494344",""));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Moryansk","8882416067"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Rohitpk", "6969669969"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Deepakdk","7703842969"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Puneetkd","9301508758"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Vishalfd","9315669548"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Vidul Arora","9910494344"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Moryan Arora","8882416067"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Rohit Sharma", "6969669969"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Deepak kahsyap","7703842969"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Puneet Chonkker","9301508758"));
        contactList.add(new contactModel(R.drawable.ic_launcher_foreground,"Vishal Vashisht","9315669548"));
        Clist.setAdapter(contactAdapter);
        //Dialog box
        Dialog dialog  = new Dialog(Recycle.this);
        dialog.setContentView(R.layout.add_contact);
        FloatingActionButton addContact = findViewById(R.id.AddContact);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        EditText name = dialog.findViewById(R.id.personName);
        EditText number = dialog.findViewById(R.id.personNumber);
       // EditText email = dialog.findViewById(R.id.personEmail);
        ImageButton save = dialog.findViewById(R.id.save);
        ImageButton discard = dialog.findViewById(R.id.notsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PersonName="";
                String PersonNumber="";
                String PersonEmail = "";
               String blank = "";
                if(!(name.getText().toString().equals(blank))){
                    PersonName =  name.getText().toString();
                }
                else{
                    Toast.makeText(Recycle.this, "Enter a valid name", Toast.LENGTH_SHORT).show();
                    return;
                }
                 if(!number.getText().toString().equals(blank)){
                    PersonNumber = number.getText().toString();
                }
               else{
                     Toast.makeText(Recycle.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                     return;
                 }
           /*    if(!email.getText().toString().equals(blank)){
                   PersonEmail = email.getText().toString();
                   contactList.add(new contactModel(R.drawable.ic_launcher_foreground,PersonName,PersonNumber));
               }*/
               contactList.add(new contactModel(R.drawable.ic_launcher_foreground,PersonName,PersonNumber));
                contactAdapter.notifyItemInserted(contactList.size()-1);
                Clist.scrollToPosition(contactList.size()-1);
                Toast.makeText(Recycle.this, "Contact Added", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } });
        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

            }
    private void filter(String s) {
        ArrayList<contactModel> filteredList = new ArrayList<>();
        for(contactModel item : contactList){
            if(item.name.toLowerCase().contains(s.toLowerCase())){
                filteredList.add(item);
            }
        }

        contactAdapter.filterList(filteredList);
    }

}

    /*          <EditText
                android:id="@+id/personEmail"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_horizontal"
                android:layout_marginTop="15dp"
                android:layout_marginBottom="15dp"
                android:background="@drawable/edittext_design"
                android:ems="13"
                android:hint="E-mail"
                android:inputType="textEmailAddress"
                android:paddingStart="15dp"
                android:textSize="20sp" />
                */