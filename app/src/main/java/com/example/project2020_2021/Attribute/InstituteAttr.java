package com.example.project2020_2021.Attribute;

public class InstituteAttr {
    String address;
    String city;
    String inscountry;
    String insemail;
    String insname;
    String inspass;
    String phoneno;
    String instype;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInscountry() {
        return inscountry;
    }

    public void setInscountry(String inscountry) {
        this.inscountry = inscountry;
    }

    public String getInsemail() {
        return insemail;
    }

    public void setInsemail(String insemail) {
        this.insemail = insemail;
    }

    public String getInsname() {
        return insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
    }

    public String getInspass() {
        return inspass;
    }

    public void setInspass(String inspass) {
        this.inspass = inspass;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getInstype() {
        return instype;
    }

    public void setInstype(String instype) {
        this.instype = instype;
    }

    public InstituteAttr(String address, String city, String inscountry, String insemail, String insname, String inspass, String phoneno, String instype) {
        this.address = address;
        this.city = city;
        this.inscountry = inscountry;
        this.insemail = insemail;
        this.insname = insname;
        this.inspass = inspass;
        this.phoneno = phoneno;
        this.instype = instype;
    }

    public InstituteAttr() {
    }
}
