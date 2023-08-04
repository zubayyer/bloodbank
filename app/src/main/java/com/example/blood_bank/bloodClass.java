package com.example.blood_bank;

public class bloodClass {
    String Name;

    public bloodClass() {
    }
    public bloodClass(String BloodGroup){
        this.Name = BloodGroup;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
