package com.example.blood_bank;

public class bloodbankClass {
    String Id,BloodBank,Email,PhoneNo,WhatsApp,Address;

    public bloodbankClass() {
    }

    public bloodbankClass(String id,String BloodBank, String email, String PhoneNo, String whatsApp, String address) {
        BloodBank = BloodBank;
        Email = email;
        PhoneNo = PhoneNo;
        WhatsApp = whatsApp;
        Address = address;
        Id = id;
    }


    public String getBloodBank() {
        return BloodBank;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setBloodBank(String BloodBank) {
        BloodBank = BloodBank;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        PhoneNo = PhoneNo;
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
}
