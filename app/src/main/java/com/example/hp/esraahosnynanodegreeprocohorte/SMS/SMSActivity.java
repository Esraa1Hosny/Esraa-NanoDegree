package com.example.hp.esraahosnynanodegreeprocohorte.SMS;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.hp.esraahosnynanodegreeprocohorte.R;
import java.util.ArrayList;

public class SMSActivity extends AppCompatActivity  {

    EditText messageedit;
    ImageView send,emotions1;

    ArrayAdapter<String> adapter;
    ListView listMessage;
    ArrayList<String> strings = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        //UI
        listMessage = (ListView) findViewById(R.id.listMessage);
        emotions1 = (ImageView)findViewById(R.id.emotions1);
        send = (ImageView) findViewById(R.id.send);
        messageedit = (EditText)findViewById(R.id.messageedit);



        // Set drawables for left, top, right, and bottom - send 0 for nothing
       messageedit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baby, 0, 0, 0);


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!messageedit.getText().toString().isEmpty()) {
                    String messages = messageedit.getText().toString();
                    Log.d(messages,"there is a message");

                    // this part i tried to pass emotions icons but i can't
                   // Bundle extras = getIntent().getExtras();
                   // Integer emo = extras.getInt("image");
                   // messageedit.setCompoundDrawablesWithIntrinsicBounds(Integer.parseInt(emo.toString()), 0, 0, 0);
                    strings.add(messages );
                    //Adapter initialization
                    adapter=new ArrayAdapter(SMSActivity.this, android.R.layout.simple_list_item_1, strings);

                    listMessage.setAdapter(adapter);
                    // And finally, update the list
                    adapter.notifyDataSetChanged();
                }
                else if(messageedit.getText().toString().isEmpty()) {
                    Toast.makeText(SMSActivity.this,"you must write message to send it",Toast.LENGTH_LONG).show();
                }
            }
        });


        emotions1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(SMSActivity.this,"emotions",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SMSActivity.this,EmotionsActivity.class);
                startActivity(intent);

            }
        });
    }


}