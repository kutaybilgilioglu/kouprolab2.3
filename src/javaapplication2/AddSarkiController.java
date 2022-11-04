/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Sarki;

/**
 * FXML Controller class
 *
 * @author akuta
 */
public class AddSarkiController implements Initializable {

    @FXML
    private TextField Sarki_adi;
    @FXML
    private DatePicker Tarih;
    @FXML
    private TextField Sanatci;
    @FXML
    private TextField Album;
    @FXML
    private TextField Tur;
    @FXML
    private TextField Sure;
     String query=null;
    Connection connect=null;
    PreparedStatement statement=null;
    ResultSet result=null;
    Sarki sarki=null;
    DbHelper db=new DbHelper();
    int sarkiId,dinlenme;
    private boolean update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Kaydet(MouseEvent event) throws SQLException {
        connect=db.getConnection();
        String isim=Sarki_adi.getText();
        String tarih=String.valueOf(Tarih.getValue());
        String sanatci=Sanatci.getText();
        String album=Album.getText();
        String tur=Tur.getText();
        String sure=Sure.getText();
        if(isim.isEmpty()||tarih.isEmpty()||sanatci.isEmpty()||album.isEmpty()||tur.isEmpty()||sure.isEmpty()){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Lutfen tum boslukları doldurun.");
        alert.showAndWait();
        }else{
            getQuery();
            insert();
        }
    }

    @FXML
    private void Iptal(MouseEvent event) {
    }

    private void getQuery() {
        if(update==false){
       query="insert into proje.sarki (sarki_adi,sarki_tarih,sanatci,album,tur,sure,dinlenme_sayisi) VALUES(?,?,?,?,?,?,0);";
        }
        else{
        query="update proje.sarki Set "
                +"'sarki_adi'=?,"
                +"'sarki_tarih'=?,"
                +"'sanatci'=?,"
                +"'album'=?,"
                +"'tur'=?,"
                +"'sure'=? WHERE sarki_id= '"+sarkiId+"'";
        }
    }

    private void insert() {
         try {

            statement = (PreparedStatement) connect.prepareStatement(query);
            statement.setString(1, Sarki_adi.getText());
            statement.setString(2, String.valueOf(Tarih.getValue()));
            statement.setString(3, Sanatci.getText());
            statement.setString(4, Album.getText());
            statement.setString(5, Tur.getText());
            statement.setString(6, Sure.getText());
            
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddSarkiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    void setTextField(int id,String isim,LocalDate toLocalDate,String sanatci,String album,String tur,String sure,int Dinlenme_sayisi){
        sarkiId=id;
        Sarki_adi.setText(isim);
        
        Tarih.setValue(toLocalDate);
        Sanatci.setText(sanatci);
        Album.setText(album);
        Tur.setText(tur);
        Sure.setText(sure);
        dinlenme=Dinlenme_sayisi;
    }

    void setUpdate(boolean b) {
        this.update=b;
    }
    
}
