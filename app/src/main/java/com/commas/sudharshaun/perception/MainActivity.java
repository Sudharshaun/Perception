package com.commas.sudharshaun.perception;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
//TODO : VARIABLE DECLARATIONS
    ImageView mImageViewfondus;
    FloatingActionButton mNextButton;
    FloatingActionButton mCloseButton;
    FloatingActionButton mNewButton;
    int imageArray[]={R.drawable.download, R.drawable.image1, R.drawable.image2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//TODO: INITIALIZATION
        mImageViewfondus = (ImageView) findViewById(R.id.imageView3);
        mNextButton = (FloatingActionButton) findViewById(R.id.floatingButtonNext);
        mCloseButton = (FloatingActionButton) findViewById(R.id.floatingButtonClose);
        mNewButton = (FloatingActionButton) findViewById(R.id.floatingButtonNew);

//TODO: EVENTS FOR BUTTONS
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Perception", "Next button works!");
                Random randomImageGenerator = new Random();
                int num = randomImageGenerator.nextInt(2);
                Log.d("Perception", "Generated number is" +num);

                mImageViewfondus.setImageResource(imageArray[num]);
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
}
