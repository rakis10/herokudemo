package com.example.demo.models;

import org.springframework.data.mongodb.core.mapping.Document;
import eu.bitwalker.useragentutils.Version;

import java.util.Date;

@Document
public class Transaction {

    String email;
    Date datum;
    String ipAdresa;
    String pozadovanaTransakcia;
    String krajina ;
    String operatingSystem;
    String browser;
    String browserVersion;



    public Transaction(
           String ipAdresa,
           String pozadovanaTransakcia,
           Date datum,
           String krajina,
           String operatingSystem,
           String browser,
           String browserVersion,
           String email
    ){
        this.ipAdresa = ipAdresa;
        this.pozadovanaTransakcia = pozadovanaTransakcia;
        this.datum = datum;
        this.krajina = krajina;
        this.operatingSystem = operatingSystem;
        this.browser = browser;
        this.browserVersion = browserVersion;
        this.email = email;
    }

    public String getKrajina() {
        return krajina;
    }

    public void setKrajina(String krajina) {
        this.krajina = krajina;
    }


    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getIpAdresa() {
        return ipAdresa;
    }

    public void setIpAdresa(String ipAdresa) {
        this.ipAdresa = ipAdresa;
    }

    public String getPozadovanaTransakcia() {
        return pozadovanaTransakcia;
    }

    public void setPozadovanaTransakcia(String pozadovanaTransakcia) {
        this.pozadovanaTransakcia = pozadovanaTransakcia;
    }




}
