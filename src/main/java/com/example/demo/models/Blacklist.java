package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Blacklist {
    public Blacklist(String meno) {
        this.meno = meno;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    String meno;

}
