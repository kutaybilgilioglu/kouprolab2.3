/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.sql.ResultSet;


/**
 *
 * @author akuta
 */
public class NewFXMain extends Application {
   
    
   @Override
   public void start(Stage stage) throws Exception{
       Parent root=FXMLLoader.load(getClass().getResource("FXML.fxml"));
       Scene scene=new Scene(root);
       stage.setScene(scene);
       stage.show();
   }
   
   public static void Insert(String Kullanici_adi,String e_mail,String Sifre,int Premium,String ulke) throws SQLException{
    Connection connect=null;
    DbHelper db=new DbHelper();
    PreparedStatement statement=null;
    try{
    connect=db.getConnection();
    String sql="insert into proje.kullanicilar (kullanici_adi,email,sifre,premium,ulke,admin)"+" values (?,?,?,?,?,0)";
    statement=   (PreparedStatement) connect.prepareStatement(sql);
    statement.setString(1, Kullanici_adi);
    statement.setString(2,e_mail);
    statement.setString(3,Sifre);
    statement.setInt(4,Premium);
    statement.setString(5,ulke);
    statement.executeUpdate();
    
   
    }
    catch(SQLException exception){
    db.ShowError(exception);
    }
    finally{
    statement.close();
    connect.close();
    }
   
   }
   public static ResultSet Select(String select,String from,String where ) throws SQLException{
   Connection connect=null;
    DbHelper db=new DbHelper();
    PreparedStatement statement=null;
    try{
    connect=db.getConnection();
    String sql="select "+select+" from "+from+" " +where; 
    statement=   (PreparedStatement) connect.prepareStatement(sql);
    ResultSet resultset=statement.executeQuery();
    return resultset;
    
    
    
   
    }
    catch(Exception exception){
   
    }
   
    return null;
   }
   public static boolean login(String username,String password) throws SQLException{
       ResultSet result=Select("*","proje.kullanicilar","where kullanici_adi= '"+username+"' AND Sifre= '"+password+"'");
       while(result.next()){
           FXMLController.admin=result.getInt("admin");
           FXMLController.log_id=result.getInt("kullanici_id");
       return true;
       }
       return false;
      
   }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       
    }
    
}
