/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author akuta
 */
public class FXMLController implements Initializable {
    public Button GirisBtn;
    public Button KayitBtn;
    public TextField kullanici_adi_txt;
    public PasswordField sifre_txt;
    public Label regist_txt;
    public Label login_txt;
    @FXML
    public TextField kullanici_adi_kayıt;
    @FXML
    public TextField email_kayit;
    @FXML
    public TextField ulke_kayit;
    @FXML
    public PasswordField Sifre_kayit;
    @FXML
    public Button kaydolBtn;
    @FXML
    public CheckBox Premium_kayit;
    @FXML
    public Button Giris_ekranBtn;
    public Stage stage;
    public Scene scene;
    public Parent root;
    public int Premium=0;
    public static int admin;
    public static int log_id;
    
    

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    public void KayitAction(ActionEvent event) throws SQLException, IOException {
        root=FXMLLoader.load(getClass().getResource("kayit_ekrani.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    public void girisaction(ActionEvent event) throws SQLException, IOException {
        String nickname=kullanici_adi_txt.getText();
        String pass=sifre_txt.getText();
        boolean log=NewFXMain.login(nickname, pass);
        if(log==true){
            if(admin==1){
            root=FXMLLoader.load(getClass().getResource("adminekran.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
            }
            else{
                root=FXMLLoader.load(getClass().getResource("kullaniciArayuz.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
                
       
            }
        }
        else{
        login_txt.setText("Kullanıcı adı veya şifre yanlış");
        System.out.println("başarısız");
        }
    }


    @FXML
    public void kaydolBtnAct(ActionEvent event) throws SQLException {
        String nickname=kullanici_adi_kayıt.getText();
        String e_mail=email_kayit.getText();
        String pass=Sifre_kayit.getText();
        String ulke=ulke_kayit.getText();
        NewFXMain.Insert(nickname,e_mail, pass,Premium,ulke);
        regist_txt.setText("kayıt başarılı");
    }

    @FXML
    public void Premium_kayitAct(ActionEvent event) {
        if(Premium==0)
            Premium=1;
        else Premium=0;
        System.out.println(Premium);
    }

    @FXML
    public void Giris_ekranBtnAct(ActionEvent event) throws IOException {
        root=FXMLLoader.load(getClass().getResource("FXML.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


   
    
}
