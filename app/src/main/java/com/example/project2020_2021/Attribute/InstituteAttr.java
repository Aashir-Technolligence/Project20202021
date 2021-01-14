package com.example.project2020_2021.Attribute;

public class InstituteAttr {
    String address;
    String city;
    String country;
    String insemail;
    String inspass;
    String insname;
    String phoneno;
    String instype;
    String insimgurl;


    public InstituteAttr(String address, String city, String country, String insemail, String inspass, String insname, String phoneno, String instype, String insimgurl) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.insemail = insemail;
        this.inspass = inspass;
        this.insname = insname;
        this.phoneno = phoneno;
        this.instype = instype;
        this.insimgurl = insimgurl;
    }

    public InstituteAttr() {
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInsemail() {
        return insemail;
    }

    public void setInsemail(String insemail) {
        this.insemail = insemail;
    }

    public String getInspass() {
        return inspass;
    }

    public void setInspass(String inspass) {
        this.inspass = inspass;
    }

    public String getInsname() {
        return insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
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

    public String getInsimgurl() {
        return insimgurl;
    }

    public void setInsimgurl(String insimgurl) {
        this.insimgurl = insimgurl;
    }
}
