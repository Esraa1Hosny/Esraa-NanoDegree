package com.example.hp.esraahosnynanodegreeprocohorte.Call;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.hp.esraahosnynanodegreeprocohorte.R;


public class CallActivity extends AppCompatActivity {

    ImageView imageback, imagephone;
    TextView number,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        //intent of phone number
        Bundle extras = getIntent().getExtras();
        final String getPhone1 = extras.getString("number");
        number =(TextView)findViewById(R.id.number);
        number.setText(getPhone1);

        String getName1 = extras.getString("name");
        name =(TextView)findViewById(R.id.name);
        name.setText(getName1);


        imagephone = (ImageView) findViewById(R.id.image2);
        imageback = (ImageView) findViewById(R.id.image);
        String url = "http://www.clker.com/cliparts/5/7/4/8/13099629981030824019profile.svg.hi.png";
        Glide.with(CallActivity.this).load(url).into(imageback);
        imageback.setAlpha(127);  //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque.


        imagephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: "+ number.getText().toString()));
                Log.v(getPhone1,"right");
                startActivity(intent);
            }
        });

    }
}
