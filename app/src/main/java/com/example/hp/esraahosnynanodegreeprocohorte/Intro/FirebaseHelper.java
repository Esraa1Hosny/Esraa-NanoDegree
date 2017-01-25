package com.example.hp.esraahosnynanodegreeprocohorte.Intro;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by HP on 14/01/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved;
    ArrayList<Contact> contacts = new ArrayList<>();

    /*
 PASS DATABASE REFRENCE
  */
    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean save(Contact info) {
        if (info == null) {
            saved = false;
        } else {
            try {
                db.child("contacts").push().setValue(info);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot) {
        contacts.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Contact c = ds.getValue(Contact.class);
            contacts.add(c);
        }
    }


    //RETRIEVE
    public ArrayList<Contact> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return contacts;
    }

}

