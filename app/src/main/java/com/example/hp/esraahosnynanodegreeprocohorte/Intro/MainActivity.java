package com.example.hp.esraahosnynanodegreeprocohorte.Intro;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.esraahosnynanodegreeprocohorte.Intro.Contact;
import com.example.hp.esraahosnynanodegreeprocohorte.Intro.ContactDetails;
import com.example.hp.esraahosnynanodegreeprocohorte.Intro.FirebaseHelper;
import com.example.hp.esraahosnynanodegreeprocohorte.Intro.contactAdapter;
import com.example.hp.esraahosnynanodegreeprocohorte.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText nameTxt, PhoneTxt, emailTxt, addressTxt;
    ImageView contactImage;
    ListView contactListView;
    Button load;
    DatabaseReference db;
    com.example.hp.esraahosnynanodegreeprocohorte.Intro.contactAdapter contactAdapter;
    FirebaseHelper firebaseHelper;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 234;
    private StorageReference mStorageRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        contactListView = (ListView) findViewById(R.id.lv);
        // initialization of firebase database
        db = FirebaseDatabase.getInstance().getReference();
        firebaseHelper = new FirebaseHelper(db);
        //ADAPTER
        contactAdapter = new contactAdapter(this, firebaseHelper.retrieve());
        contactListView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
        Button add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog();
            }
        });

        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetails.class);
                intent.putExtra("name", nameTxt.getText().toString());
                intent.putExtra("phone", PhoneTxt.getText().toString());
                intent.putExtra("email", emailTxt.getText().toString());
                intent.putExtra("address", addressTxt.getText().toString());
                // intent.putExtra("image",contactImage.setImageResource());
                // intent.putExtra("image",onActivityResult());
                startActivity(intent);
            }
        });

    }
    // dialog box containing fields of contacts
    private void inputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save Contact");
        d.setContentView(R.layout.dialog_input);
        //UI
        nameTxt = (EditText) d.findViewById(R.id.name);
        PhoneTxt = (EditText) d.findViewById(R.id.phone);
        emailTxt = (EditText) d.findViewById(R.id.email);
        addressTxt = (EditText) d.findViewById(R.id.address);
        contactImage = (ImageView) d.findViewById(R.id.image);
        final Button addBtn = (Button) d.findViewById(R.id.BtnAdd);
        load = (Button) d.findViewById(R.id.load);
        // contactImage.setImageResource( R.drawable.man_icon);


        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null) {
                    Uri file = Uri.fromFile(new File("path/to/images/*"));
                    final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("wait uploading...");
                    progressDialog.show();
                    final StorageReference riversRef = mStorageRef.child("images/rivers.jpg");

                    riversRef.putFile(filePath)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    // Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                    progressDialog.dismiss();
                                    //Uri downloadUri  = taskSnapshot.getDownloadUrl();
                                    // Picasso.with(MainActivity.this).load(downloadUri).fit().centerCrop().into(contactImage);
                                    // Alternatively way to get download URL
                                    riversRef.child("images/rivers.jpg").getDownloadUrl().getResult();
                                    Toast.makeText(getApplicationContext(), "file uploaded ^_^ ", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                                    progressDialog.setMessage(((int) progress) + "% ");
                                }
                            });
                } else {
                    //display error Toast
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                }
            }

        });


        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectImage();
                // Toast.makeText(MainActivity.this, "image here ", Toast.LENGTH_LONG).show();

            }
        });
        //SAVE
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Contacts.add(new Contact(nameTxt.getText().toString(), PhoneTxt.getText().toString(),
                // emailTxt.getText().toString(), addressTxt.getText().toString(), imageUri));

                //GET DATA
                String name = nameTxt.getText().toString();
                String phone = PhoneTxt.getText().toString();
                String email = emailTxt.getText().toString();
                String address = addressTxt.getText().toString();
                // Bitmap bitmap = imageUri.compareTo();
                //SET DATA
                Contact c = new Contact();
                c.setName(name);
                c.setPhone(phone);
                c.setEmail(email);
                c.setAddress(address);
                // c.setImageUri(bitmap);
                //c.setImageUri(imageUri);


                //SIMPLE VALIDATION
                if (name != null && name.length() > 0) {
                    //THEN SAVE
                    if (firebaseHelper.save(c)) {
                        //IF SAVED CLEAR EDITXT
                        nameTxt.setText("");
                        PhoneTxt.setText("");
                        emailTxt.setText("");
                        addressTxt.setText("");
                        contactImage.setImageDrawable(null);
                        //contactImage.invalidate();
                        //contactImage.setImageBitmap(null);
                        // contactImage = null;
                        contactAdapter = new contactAdapter(MainActivity.this, firebaseHelper.retrieve());
                        contactListView.setAdapter(contactAdapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "There is a missing field ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();

    }


    private void selectImage() {

        final CharSequence[] options = {"Camera", " Gallery"};

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Choose Image ");
        dialog.setItems(options, new DialogInterface.OnClickListener() {
            // dialog.setIcon(R.drawable.image);
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0)   // camera
                {
                    dispatchTakePictureIntent();
                } else if (item == 1)  //gallery
                {
                    /*Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);*/
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select contact image"), PICK_IMAGE_REQUEST);

                }


            }
        });
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                contactImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /* Base64 is a format of binary-to-text encoding. Essentially,
     this just means that the very binary
    of the object being encoded is turned into a really long string.
    */

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

}
