package com.hny.ar2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class otpVerificationActivity extends AppCompatActivity {

    private Button verifybtn1;
    private ProgressBar progressBar;
    EditText otpField1,otpField2,otpField3,otpField4,otpField5,otpField6;
    private String verificationCodeEnterBySystem;
    String phoneNo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);
        Objects.requireNonNull(getSupportActionBar()).hide();

        verifybtn1 =findViewById(R.id.otpverificationbtn);
        otpField1 = findViewById(R.id.otpfield1);
        otpField2 = findViewById(R.id.otpfield2);
        otpField3 = findViewById(R.id.otpfield3);
        otpField4 = findViewById(R.id.otpfield4);
        otpField5 = findViewById(R.id.otpfield5);
        otpField6 = findViewById(R.id.otpfield6);
        progressBar = findViewById(R.id.waitingotp);

        phoneNo2=getIntent().getStringExtra("phone");


        progressBar.setVisibility(View.GONE);

        noOtpMove();
        sendVerificationCodeToUser(phoneNo2);

        verifybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otpbyfire = otpField1.getText().toString()+otpField2.getText().toString()+
                        otpField3.getText().toString()+otpField4.getText().toString()+
                        otpField5.getText().toString()+otpField6.getText().toString();
                if(otpbyfire.isEmpty() || otpbyfire.length()<6){

                    Toast.makeText(getApplicationContext(),"Wrong",Toast.LENGTH_SHORT).show();

                }else {
                    progressBar.setVisibility(View.VISIBLE);
                    verifyCode(otpbyfire);
                }

            }
        });

    }

    private void sendVerificationCodeToUser(String phoneNo2) {


        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNo2,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);


    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(verificationId,token);
            verificationCodeEnterBySystem = verificationId;
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {

            String code = credential.getSmsCode();

            if(code!=null){
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }
        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(getApplicationContext(),e.getMessage()+" First",Toast.LENGTH_SHORT).show();

        }
    };

    private void verifyCode(String codeByUser){

        PhoneAuthCredential credential =PhoneAuthProvider.getCredential(verificationCodeEnterBySystem,codeByUser);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebase = FirebaseAuth.getInstance();
        firebase.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),arClass.class);
                            intent.putExtra("phone1",phoneNo2);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage()+" Second.",Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void noOtpMove() {

        otpField1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otpField2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpField2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otpField3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpField3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otpField4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpField4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otpField5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpField5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    otpField6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
