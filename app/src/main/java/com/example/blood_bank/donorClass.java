package com.example.blood_bank;

public class donorClass {
String Id,Name,Email,Phone,WhatsApp,Address,Password,User,Blood,Verification;

    public donorClass() {
    }

    public donorClass(String name, String phone, String address) {
        Name = name;
        Phone = phone;
        Address = address;
    }

    public donorClass(String name, String phone, String address, String blood) {
        Name = name;
        Phone = phone;
        Address = address;
        Blood = blood;
    }

    public donorClass(String id, String name, String email, String phone, String whatsApp, String address, String password, String user, String blood, String verification) {
        Id = id;
        Name = name;
        Email = email;
        Phone = phone;
        WhatsApp = whatsApp;
        Address = address;
        Password = password;
        User = user;
        Blood = blood;
        Verification = verification;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getVerification() {
        return Verification;
    }

    public void setVerification(String verification) {
        Verification = verification;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWhatsApp() {
        return WhatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        WhatsApp = whatsApp;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
