package com.example.smartdoctor;

public class Patient {
    public String fname,lname,email;

    public Patient() {
    }

    public Patient(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
