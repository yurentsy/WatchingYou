package com.yurentsy.watchingyou.mvp.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by silan on 29.10.2018.
 */
@Entity(indices = {@Index(value = {"id"}, unique = true)})
public class Person implements Serializable {
    /*"id": 2001,
            "name": "Vladimir",
            "surname": "Putin",
            "position": "Managing director",
            "number": "305-701-2307",
            "email": "v.putin@world.io",
            "address": "98 Shinn Street",
            "urlPhoto": "https://www.worldpresidentsdb.com/images/presidents/vladimir-putin.jpg"*/
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("position")
    @Expose
    private String position;

    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("urlPhoto")
    @Expose
    private String urlPhoto;

    private boolean isWorking;

    public Person(String id, String name, String surname, String position, String number, String email, String address, String urlPhoto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.number = number;
        this.email = email;
        this.address = address;
        this.urlPhoto = urlPhoto;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPosition() {
        return position;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        return this.id.equals(((Person) obj).getId());
    }
}
