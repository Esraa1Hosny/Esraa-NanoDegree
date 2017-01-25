package com.example.hp.esraahosnynanodegreeprocohorte.SMS;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.esraahosnynanodegreeprocohorte.Intro.FirebaseHelper;
import com.example.hp.esraahosnynanodegreeprocohorte.Intro.MainActivity;
import com.example.hp.esraahosnynanodegreeprocohorte.Intro.contactAdapter;
import com.example.hp.esraahosnynanodegreeprocohorte.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;


public class EmotionsActivity extends AppCompatActivity {

    GridView emotionsGrid;
    EmotionsAdapter emotionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotions);

        emotionsGrid = (GridView) findViewById(R.id.emotionsGrid);


        emotionsAdapter = new EmotionsAdapter(this);
        emotionsGrid.setAdapter(emotionsAdapter);
        emotionsAdapter.notifyDataSetChanged();

        emotionsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(EmotionsActivity.this, SMSActivity.class);
                intent.putExtra(emotionsGrid.toString(),"image");
                startActivity(intent);

            }
        });
    }

        public class EmotionsAdapter extends BaseAdapter {
        Context context;

            public Integer[] emojis = new Integer[]{
                    R.drawable.clown,R.drawable.devil,
                    R.drawable.boom,R.drawable.haha,
                    R.drawable.anger,R.drawable.kiss,
                    R.drawable.adore,R.drawable.alien,
                    R.drawable.wink,R.drawable.unhappy,
                    R.drawable.tongue,R.drawable.suprised,
                    R.drawable.smile,R.drawable.red,
                    R.drawable.love,R.drawable.confuse,
                    R.drawable.evilgrin,R.drawable.baby,
                    R.drawable.batman,R.drawable.bigsmile,
                    R.drawable.amazing,R.drawable.angel,
                    R.drawable.crazy,R.drawable.cool,
                    R.drawable.cry,R.drawable.edd,
                    R.drawable.exciting,R.drawable.happy,

            };
        //constructor
        public EmotionsAdapter(Context context) {
            this.context = context;
        }
        @Override
        public int getCount() {
            return emojis.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(100,100));
            imageView.setPadding(10,10,10,10);
           // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(emojis[position]);
            return imageView;
        }
    }
}
