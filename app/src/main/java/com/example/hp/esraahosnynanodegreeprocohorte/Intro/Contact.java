package com.example.hp.esraahosnynanodegreeprocohorte.Intro;

import android.net.Uri;

/**
 * Created by HP on 09/01/2017.
 */
public class Contact {
    //attributes
    String name;
    String phone;
    String email;
    String address;

    // Uri imageUri;
    //default constructor
    public Contact() {
    }

    public Contact(String name, String phone, String email, String address, Uri imageUri) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        //  this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
  /*  public Uri getImageURI() {return imageUri;}
    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }*/


}
