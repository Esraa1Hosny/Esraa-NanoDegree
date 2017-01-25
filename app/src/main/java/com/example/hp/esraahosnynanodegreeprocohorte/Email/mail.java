package com.example.hp.esraahosnynanodegreeprocohorte.Email;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.esraahosnynanodegreeprocohorte.R;


public class mail extends AppCompatActivity {

    Uri imageUri = null;
    // Typeface tf1, tf2, tf3;
    TextView tv1, tv2, tv3, messagecontent;
    EditText et1, et2, et3, et4;
    ImageView send, emotions, file, image, camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        //text views
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        messagecontent = (TextView) findViewById(R.id.messagecontent);


        //edittext
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);


        //imageview
        send = (ImageView) findViewById(R.id.send);
        emotions = (ImageView) findViewById(R.id.emotions);
        file = (ImageView) findViewById(R.id.file);
        image = (ImageView) findViewById(R.id.image);
        camera = (ImageView) findViewById(R.id.camera);


        //edit fonts of words
       /* tf1 = Typeface.createFromAsset(getAssets(), "Love Letters.ttf");
        tf2 = Typeface.createFromAsset(getAssets(), "Love Letters.ttf");
        tf3 = Typeface.createFromAsset(getAssets(), "Love Letters.ttf");
        tv1.setTypeface(tf1);
        tv2.setTypeface(tf2);
        tv3.setTypeface(tf3);*/


        //we can use these lines of code after that in email validations
        String to = et1.getText().toString();
        String from = et2.getText().toString();
        String subject = et3.getText().toString();


        //camera
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


        //gallery
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select contact image"), 1);
            }
        });


       /* //file
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/


        //emotions
       /* emotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });*/

        //send
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if 3 fields are filled or empty
                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty()) {
                    Toast.makeText(mail.this, "please enter the empty fields of To , From , Subject", Toast.LENGTH_LONG).show();
                }
                //else if
                //check if email exists in email database or not
                //}
                else {
                    //send message to receiver
                    String message = et4.getText().toString();
                    messagecontent.setText(message);
                }


            }
        });
    }


    public void onActivityResult(int reqCode, int resCode, Intent data) {
        if (resCode == RESULT_OK) {
            if (reqCode == 1) {
                imageUri = data.getData();
                image.setImageURI(data.getData());
            }

        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }
}



