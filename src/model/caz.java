/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author akuta
 * 
 */
public class caz {
    String Sarki_adi,album;
    Date tarih;
    int izlenme;
    int caz_id;

    public caz(String Sarki_adi, String album, Date tarih, int izlenme, int caz_id) {
        this.Sarki_adi = Sarki_adi;
        this.album = album;
        this.tarih = tarih;
        this.izlenme = izlenme;
        this.caz_id = caz_id;
    }

    public String getSarki_adi() {
        return Sarki_adi;
    }

    public void setSarki_adi(String Sarki_adi) {
        this.Sarki_adi = Sarki_adi;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public int getIzlenme() {
        return izlenme;
    }

    public void setIzlenme(int izlenme) {
        this.izlenme = izlenme;
    }

    public int getCaz_id() {
        return caz_id;
    }

    public void setCaz_id(int caz_id) {
        this.caz_id = caz_id;
    }
    
   
    
}
