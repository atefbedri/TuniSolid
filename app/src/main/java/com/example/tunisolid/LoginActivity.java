package com.example.tunisolid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tunisolid.ui.home.HomeFragment;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity  {

   TextView mLoginFeedbackText;
    Button signin;
    String thephone;
    EditText phone_number;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallsback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();

        mLoginFeedbackText=(TextView)findViewById(R.id.mLoginFeedbackText);
      signin=(Button)findViewById(R.id.btn_signin);
      phone_number=(EditText)findViewById(R.id.phone_number);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 thephone=phone_number.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                Intent otpIntent = new Intent(LoginActivity.this, MainActivity.class);
                otpIntent.putExtra("tele",thephone);
                startActivity(otpIntent);

                /*
                String phone=phone_number.getText().toString();
                String phone_n="+216"+phone;
                if(phone_n.isEmpty()){
                    EmptyDialog emptyDialog=new EmptyDialog();
                    emptyDialog.show(getSupportFragmentManager(),"Vérifier le numéro!");

                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    signin.setEnabled(false);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phone_n,
                            60,
                            TimeUnit.SECONDS,
                            LoginActivity.this,
                            mCallsback


                    );



                }
*/
            }
        });

        mCallsback=new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential){

                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public  void onVerificationFailed(FirebaseException e){

                mLoginFeedbackText.setText("Verification Failed, please try again.");
                mLoginFeedbackText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                signin.setEnabled(true);


            }

            @Override
            public void onCodeSent(final String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Intent otpIntent = new Intent(LoginActivity.this, OtpActivity.class);
                                otpIntent.putExtra("AuthCredentials", s);
                                startActivity(otpIntent);
                            }
                        },
                        10000);
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(mCurrentUser != null){
            sendUserToHome();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserToHome();
                            // ...
                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                mLoginFeedbackText.setVisibility(View.VISIBLE);
                                mLoginFeedbackText.setText("There was an error verifying OTP");
                            }
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        signin.setEnabled(true);
                    }
                });
    }

    private void sendUserToHome() {
        Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
    }
}
