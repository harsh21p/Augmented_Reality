package com.hny.ar2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private String phoneNoString;
    private Button verify;
    private EditText phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        verify = findViewById(R.id.verifybtn);
        phoneNo = findViewById(R.id.phonenoinput);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNoString = phoneNo.getText().toString();
                Intent intent=new Intent(getApplicationContext(),otpVerificationActivity.class);
                intent.putExtra("phone",phoneNoString);
                startActivity(intent);

                //AuthBypass
//
//                Intent intent=new Intent(getApplicationContext(),arClass.class);
//                intent.putExtra("phone1",phoneNoString);
//                startActivity(intent);

            }
        });
    }
}