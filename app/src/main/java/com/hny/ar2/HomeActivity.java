package com.hny.ar2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if(10 <= phoneNo.getText().toString().length()) {
                    phoneNoString = phoneNo.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), otpVerificationActivity.class);
                    intent.putExtra("phone", phoneNoString);
                    startActivity(intent);
                }
                else {
                    if(0 == phoneNo.getText().toString().length()){
                        Toast.makeText(HomeActivity.this,"Please enter mobile number",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(HomeActivity.this,"Please enter correct mobile number",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }
}