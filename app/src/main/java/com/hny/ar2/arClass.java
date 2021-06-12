package com.hny.ar2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class arClass extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_class);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

         String userNameString = getIntent().getStringExtra("phone");
         String phoneNoString= getIntent().getStringExtra("users");

        userHelper userHelper = new userHelper(userNameString,phoneNoString);
        reference.child(phoneNoString).setValue(userHelper);


    }



}