package com.commas.sudharshaun.perception;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mSignInButton;
    private EditText mEmailText;
    private EditText mPasswordText;
    private TextView mCreateAccountText;

    private ProgressDialog mProgressDialog;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if (mFirebaseAuth.getCurrentUser()!= null) {
            finish();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            LoginActivity.this.startActivity(intent);
        }

        mSignInButton = (Button) findViewById(R.id.signInButton);
        mEmailText = (EditText) findViewById(R.id.userSignInEmail);
        mPasswordText = (EditText) findViewById(R.id.userSignInPassword);
        mCreateAccountText = (TextView) findViewById(R.id.createAccountText);

        mSignInButton.setOnClickListener(this);
        mCreateAccountText.setOnClickListener(this);
    }

    private void userLogin() {
        String email = mEmailText.getText().toString().trim();
        String password = mPasswordText.getText().toString().trim();

        //Checks for empty values in text boxes

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

        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
            }
        });

        //End verification
    }

    @Override
    public void onClick(View v) {
        if (v == mSignInButton) {
            userLogin();
        }
        if (v == mCreateAccountText) {
            finish();
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            LoginActivity.this.startActivity(intent);
        }
    }
}