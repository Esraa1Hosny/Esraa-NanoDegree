package com.example.hp.esraahosnynanodegreeprocohorte.Intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.hp.esraahosnynanodegreeprocohorte.R;

public class Splash extends AppCompatActivity {


     ImageView imageView;
     Button button;
     TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        imageView = (ImageView) findViewById(R.id.logo);
        button = (Button) findViewById(R.id.skip);
        textView = (TextView) findViewById(R.id.editText);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
