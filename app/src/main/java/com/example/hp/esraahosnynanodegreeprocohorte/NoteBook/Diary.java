package com.example.hp.esraahosnynanodegreeprocohorte.NoteBook;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.esraahosnynanodegreeprocohorte.R;

import org.w3c.dom.Text;

public class Diary extends AppCompatActivity {

    Button save, open, delete;
    EditText edit1;
    TextView text1;
    SharedPreferences sp;
    public static String filename = "notes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__book);

        save = (Button) findViewById(R.id.save);
        open = (Button) findViewById(R.id.open);
        delete = (Button) findViewById(R.id.delete);
        edit1 = (EditText) findViewById(R.id.edit1);
        text1 = (TextView) findViewById(R.id.text1);

        //initialization
        sp = getSharedPreferences(filename, 0);
        // 0 - for private mode
        // Context.MODE_PRIVATE

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notes = edit1.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                //storing data
                editor.putString("notes", notes); // Storing string
                editor.commit(); // commit changes

            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp = getSharedPreferences(filename, 0);
                String returnedData = sp.getString("notes", "couldn't load the file");
                text1.setText(returnedData);
                // returns stored preference value
                // If value is not present return second param value - In this case null
                //  sp.getString(returnedData, null); // getting String

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to delete all data
                if (text1.getText().toString() == null) {
                    Toast.makeText(Diary.this, "file is already empty", Toast.LENGTH_LONG).show();

                } else {

                    text1.setText("");
                }
            }
        });


    }
}
