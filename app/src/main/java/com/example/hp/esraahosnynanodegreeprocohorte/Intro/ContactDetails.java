package com.example.hp.esraahosnynanodegreeprocohorte.Intro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hp.esraahosnynanodegreeprocohorte.Call.CallActivity;
import com.example.hp.esraahosnynanodegreeprocohorte.Email.Login;
import com.example.hp.esraahosnynanodegreeprocohorte.NoteBook.Diary;
import com.example.hp.esraahosnynanodegreeprocohorte.R;


public class ContactDetails extends AppCompatActivity {

    TextView textname, textphone, textemail, textaddress;
    ImageView  imagephone, imageemail, imagePerson, imagenote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        textname = (TextView) findViewById(R.id.textname);
        textphone = (TextView) findViewById(R.id.textphone);
        textemail = (TextView) findViewById(R.id.textemail);
        textaddress = (TextView) findViewById(R.id.textaddress);


        imagephone = (ImageView) findViewById(R.id.imagephone);
        imageemail = (ImageView) findViewById(R.id.imageemail);
        imagenote = (ImageView) findViewById(R.id.imagenote);

        imagePerson = (ImageView) findViewById(R.id.imageView6);


        Bundle extras = getIntent().getExtras();
        String getName = extras.getString("name");
        String getPhone = extras.getString("phone");
        String getEmail = extras.getString("email");
        String getAddress = extras.getString("address");
        // ImageView image = this.getIntent().getParcelableExtra("BitmapImage");

        textname.setText("Name : " + getName);
        textphone.setText("Phone :" + getPhone);
        textemail.setText("Email : " + getEmail);
        textaddress.setText("Address : " + getAddress);


        //calling of user communication options
        phoneClick();
        emailClick();
        noteClick();


    }

    // if user wants to make a phone call
    public void phoneClick() {
        imagephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_call = new Intent(ContactDetails.this, CallActivity.class);
                //this phone number intent to transfer to CallActivity
                // Intent intent = new Intent(ContactDetails.this, CallActivity.class);
                intent_call.putExtra("name", textname.getText().toString());
                intent_call.putExtra("number", textphone.getText().toString());
                // startActivity(intent);
                startActivity(intent_call);
            }
        });
    }

    //if user wants to email fis/her friend
    public void emailClick() {
        imageemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetails.this, Login.class);
                startActivity(intent);
            }
        });
    }



    public void noteClick() {
        imagenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactDetails.this, Diary.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.contact_id) {
            Intent intent = new Intent(ContactDetails.this,MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
