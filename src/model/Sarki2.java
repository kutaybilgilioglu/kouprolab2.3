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
 */
public class Sarki2 {
    
    String sarki_adi;
    Date sarki_tarih;
    String sanatci,album,tur,sure;
    int dinlenme_sayisi;

    public Sarki2(String sarki_adi, Date sarki_tarih, String sanatci, String album, String tur, String sure, int dinlenme_sayisi) {
        this.sarki_adi = sarki_adi;
        this.sarki_tarih = sarki_tarih;
        this.sanatci = sanatci;
        this.album = album;
        this.tur = tur;
        this.sure = sure;
        this.dinlenme_sayisi = dinlenme_sayisi;
    }

    public String getSarki_adi() {
        return sarki_adi;
    }

    public void setSarki_adi(String sarki_adi) {
        this.sarki_adi = sarki_adi;
    }

    public Date getSarki_tarih() {
        return sarki_tarih;
    }

    public void setSarki_tarih(Date sarki_tarih) {
        this.sarki_tarih = sarki_tarih;
    }

    public String getSanatci() {
        return sanatci;
    }

    public void setSanatci(String sanatci) {
        this.sanatci = sanatci;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }

    public int getDinlenme_sayisi() {
        return dinlenme_sayisi;
    }

    public void setDinlenme_sayisi(int dinlenme_sayisi) {
        this.dinlenme_sayisi = dinlenme_sayisi;
    }
    
}
