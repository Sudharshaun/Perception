package com.commas.sudharshaun.perception;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

//TODO : VARIABLE DECLARATIONS
    ImageView mImageViewfondus;
    FloatingActionButton mNextButton;
    FloatingActionButton mCloseButton;
    FloatingActionButton mNewButton;
    EditText mEditTextBox1;
    EditText mEditTextBox2;
    EditText mEditTextBox3;
    int imageArray[]={R.drawable.download, R.drawable.image1, R.drawable.image2};

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//TODO: INITIALIZATION
        mImageViewfondus = (ImageView) findViewById(R.id.imageView3);
        mNextButton = (FloatingActionButton) findViewById(R.id.floatingButtonNext);
        mCloseButton = (FloatingActionButton) findViewById(R.id.floatingButtonClose);
        mNewButton = (FloatingActionButton) findViewById(R.id.floatingButtonNew);
        mEditTextBox1 = (EditText) findViewById(R.id.textBox1);
        mEditTextBox2 = (EditText) findViewById(R.id.textBox2);
        mEditTextBox3 = (EditText) findViewById(R.id.textBox3);

        mEditTextBox1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditTextBox1, InputMethodManager.SHOW_IMPLICIT);

//TODO: DATABASE INITIALIZATION
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("UserData");

//TODO: EVENTS FOR BUTTONS
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataStore();
                //Log.d("Perception", "Next button works!");
                Random randomImageGenerator = new Random();
                int num = randomImageGenerator.nextInt(2);
                Log.d("Perception", "Generated number is" +num);

                mImageViewfondus.setImageResource(imageArray[num]);

                mEditTextBox1.getText().clear();
                mEditTextBox2.getText().clear();
                mEditTextBox3.getText().clear();
            }
        });

        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Perception", "Close button works!");
            }
        });

        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    //TODO: GENERATE METHOD TO SAVE EDIT TEXT DATA
    public void dataStore() {
        String text1 = mEditTextBox1.getText().toString().trim();
        String text2 = mEditTextBox2.getText().toString().trim();
        String text3 = mEditTextBox3.getText().toString().trim();

        if (!TextUtils.isEmpty(text1) || !TextUtils.isEmpty(text2) || !TextUtils.isEmpty(text3)){

            String id = mDatabaseReference.push().getKey();
            UserData userData = new UserData(id, text1, text2, text3);
            mDatabaseReference.child(id).setValue(userData);
            Toast.makeText(this, "Values entered to FIrebase", Toast.LENGTH_SHORT).show();;

        }
    }
}
