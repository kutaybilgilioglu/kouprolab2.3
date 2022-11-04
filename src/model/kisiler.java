/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author akuta
 */
public class kisiler {
    int kullanici_id;
    String kullanici_adi;

    public kisiler(int kullanici_id, String kullanici_adi) {
        this.kullanici_id = kullanici_id;
        this.kullanici_adi = kullanici_adi;
    }

    public int getKullanici_id() {
        return kullanici_id;
    }

    public void setKullanici_id(int kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }
    
}
