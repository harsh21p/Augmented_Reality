package com.hny.ar2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;


public class HomeActivity extends AppCompatActivity {

    private String phoneNoString;
    private Button verify;
    private TextInputLayout phoneNo;
    private TextInputLayout userName;
    private String userNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();

        verify = findViewById(R.id.verifybtn);
        phoneNo = findViewById(R.id.phonenoinput);
        userName = findViewById(R.id.username);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userNameString = userName.getEditText().getText().toString();
                phoneNoString = phoneNo.getEditText().getText().toString();

//                Intent intent=new Intent(getApplicationContext(),otpVerificationActivity.class);
//                intent.putExtra("phoneNo1",phoneNoString);
//                intent.putExtra("user",userNameString);
//                startActivity(intent);

                Intent intent=new Intent(getApplicationContext(),arClass.class);
                intent.putExtra("phone",phoneNoString);
                intent.putExtra("users",userNameString);
                startActivity(intent);

            }
        });



    }
}