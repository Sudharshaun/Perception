package com.commas.sudharshaun.perception;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mSignUpButton;
    private EditText mUserNameText;
    private EditText mEmailIdText;
    private EditText mPasswordText;
    private ProgressDialog mProgressDialog;

    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(this);

        mSignUpButton = (Button) findViewById(R.id.signUpButton);
        mUserNameText = (EditText) findViewById(R.id.userSignUpName);
        mEmailIdText = (EditText) findViewById(R.id.userSignUpEmail);
        mPasswordText = (EditText) findViewById(R.id.userSignUpPassword);

        mSignUpButton.setOnClickListener(this);

    }

    private void registerUser() {

        String username = mUserNameText.getText().toString().trim();
        String email = mEmailIdText.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();

        //Checks for empty values in text boxes
        if (TextUtils.isEmpty(username) ){
            Toast.makeText(getApplicationContext(), "Enter a User Name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter a valid Email ID", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressDialog.setMessage("Registering User");
        mProgressDialog.show();

        //End verification

        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"You have successfully Registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    SignUpActivity.this.startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Oops! Registration unsuccessful. Please try again later.", Toast.LENGTH_SHORT).show();
                }

                mProgressDialog.hide();


            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == mSignUpButton) {
            registerUser();
        }

    }


}
