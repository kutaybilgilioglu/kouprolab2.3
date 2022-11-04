/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;


import model.Sarki;

/**
 *
 * @author akuta
 */
public class adminController implements Initializable{

    @FXML
    public TableView<Sarki> sarkiTable;
    public TableColumn<Sarki, String> idCol;
    @FXML
    public TableColumn<Sarki, String> sarki_adiCol;
    @FXML
    public TableColumn<Sarki, String> sarki_tarihCol;
    @FXML
    public TableColumn<Sarki, String> sanatciCol;
    @FXML
    public TableColumn<Sarki, String> albumCol;
    @FXML
    public TableColumn<Sarki, String> turCol;
    @FXML
    public TableColumn<Sarki, String> sureCol;
    @FXML
    public TableColumn<Sarki, String> dinlenmeCol;
    @FXML
    public TableColumn<Sarki, String> editCol;
    String query=null;
    Connection connect=null;
    PreparedStatement statement=null;
    ResultSet result=null;
    Sarki sarki=null;
    
    DbHelper db=new DbHelper();
    
    ObservableList<Sarki> SarkiList=FXCollections.observableArrayList();
    public Button addBtn;
    @FXML
    public Button RefreshBtn;
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            // TODO
            loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    

    public void loadDate() throws SQLException {
        connect=db.getConnection();
        RefreshTable();
        
        idCol.setCellValueFactory(new PropertyValueFactory<>("sarki_id"));
        sarki_adiCol.setCellValueFactory(new PropertyValueFactory<>("sarki_adi"));
        sarki_tarihCol.setCellValueFactory(new PropertyValueFactory<>("sarki_tarih"));
        sanatciCol.setCellValueFactory(new PropertyValueFactory<>("sanatci"));
        albumCol.setCellValueFactory(new PropertyValueFactory<>("album"));
        turCol.setCellValueFactory(new PropertyValueFactory<>("tur"));
        sureCol.setCellValueFactory(new PropertyValueFactory<>("sure"));
        dinlenmeCol.setCellValueFactory(new PropertyValueFactory<>("dinlenme_sayisi"));
        
         Callback<TableColumn<Sarki, String>, TableCell<Sarki, String>> cellFoctory;
        cellFoctory = (TableColumn<Sarki, String> param) -> {
           
            
            final TableCell<Sarki, String> cell = new TableCell<Sarki, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                   
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteBtn=new Button("-");
                        Button editBtn=new Button("e");

                        deleteBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteBtn.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                               
                                int index=sarkiTable.getSelectionModel().getSelectedIndex();
                                 sarki = sarkiTable.getItems().get(index);
                              
                                
                                query = "DELETE FROM proje.sarki WHERE sarki_id  ="+sarki.getSarki_id();
                                connect = db.getConnection();
                                statement = (PreparedStatement) connect.prepareStatement(query);
                                statement.execute();
                                RefreshTable();
                                
                            } catch (SQLException ex) {
                                
                            }
                            
                            
                            
                            
                            
                        });
                        editBtn.setOnMouseClicked((MouseEvent event) -> {
                            
                            Sarki sarki = sarkiTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/javaapplication2/addSarki.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                          LocalDate date = Instant.ofEpochMilli(sarki.getSarki_tarih().getTime())
                  .atZone(ZoneId.systemDefault())
                  .toLocalDate();
                            AddSarkiController addSarkiController = loader.getController();
                            addSarkiController.setUpdate(true);
                            addSarkiController.setTextField(sarki.getSarki_id(), sarki.getSarki_adi(), 
                                    date,sarki.getSanatci(), sarki.getAlbum(),sarki.getTur(),sarki.getSure(),sarki.getDinlenme_sayisi());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            
                            
                            
                            
                        });

                        HBox managebtn = new HBox(editBtn, deleteBtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteBtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editBtn, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         sarkiTable.setItems(SarkiList);
         
    }


   
    @FXML
    public void AddSarki(ActionEvent event) {
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/javaapplication2/addSarki.fxml"));
            Scene scene=new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void RefreshTable() {
         try{
    SarkiList.clear();
    query="Select * from proje.sarki";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    SarkiList.add(new Sarki(
            result.getInt("sarki_id"),
            result.getString("sarki_adi"),
            result.getDate("sarki_tarih"),
            result.getString("sanatci"),
            result.getString("album"),
            result.getString("tur"),
            result.getString("sure"),
            result.getInt("dinlenme_sayisi")));
    sarkiTable.setItems(SarkiList);
    }
    }catch(SQLException ex){
        Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    }
    
}
