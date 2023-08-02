package com.example.blood_bank;

import android.widget.ArrayAdapter;

import com.example.blood_bank.Register.UserLoginActivity;

public class acceptorClass {
    String Id, Name, Email, Phone, WhatsApp, Address, Password,User;
    public acceptorClass() {
    }

    public acceptorClass(String id, String name, String email, String phone, String whatsApp, String address, String password,String user) {
        Id = id;
        Name = name;
        Email = email;
        Phone = phone;
        WhatsApp = whatsApp;
        Address = address;
        Password = password;
        User = user;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        WhatsApp = whatsApp;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}