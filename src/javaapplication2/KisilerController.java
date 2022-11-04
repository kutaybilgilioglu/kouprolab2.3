/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import model.kisiler;

/**
 * FXML Controller class
 *
 * @author akuta
 */
public class KisilerController implements Initializable {

    @FXML
    private TableView<kisiler> kisiler_table;
    @FXML
    private TableColumn<kisiler, String> ad_col;
    @FXML
    private TableColumn<kisiler, String> edit_col;
    @FXML
    private Button geri;
    @FXML
    private TableColumn<kisiler, String> kisi_id_col;
    String query=null;
    Connection connect=null;
    PreparedStatement statement=null;
    ResultSet result=null;
    kisiler kisi=null;
    boolean pass;
    
    DbHelper db=new DbHelper();
    
    ObservableList<kisiler> kisiList=FXCollections.observableArrayList();
    static int kullanici_id;
    static boolean feedback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(KisilerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void geriAct(ActionEvent event) {
    }
    public void loadDate() throws SQLException {
        connect=db.getConnection();
        RefreshTable();
        
        ad_col.setCellValueFactory(new PropertyValueFactory<>("kullanici_adi"));
       
        
         Callback<TableColumn<kisiler, String>, TableCell<kisiler, String>> cellFoctory;
        cellFoctory = (TableColumn<kisiler, String> param) -> {
           
            
            final TableCell<kisiler, String> cell = new TableCell<kisiler, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button tkpBtn=new Button("tkp");
                        Button bakBtn=new Button("bak");

                        tkpBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        bakBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        tkpBtn.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                               
                                int index=kisiler_table.getSelectionModel().getSelectedIndex();
                                 kisi = kisiler_table.getItems().get(index);
                              
                                
                                query = "insert into proje.takip values(?,?);";
                                connect = db.getConnection();
                                statement = (PreparedStatement) connect.prepareStatement(query);
                                statement.setInt(1,FXMLController.log_id);
                                statement.setInt(2,kisi.getKullanici_id());
                                statement.executeUpdate();
                                RefreshTable();
                                
                            } catch (SQLException ex) {
                                
                            }
                            
                            
                            
                            
                            
                        });
                        bakBtn.setOnMouseClicked((MouseEvent event) -> {
                          
                            try {
                                int index=kisiler_table.getSelectionModel().getSelectedIndex();
                                kisi = kisiler_table.getItems().get(index);
                                query = "select * from proje.takip where takipci_id="+FXMLController.log_id+" AND takip_id="+kisi.getKullanici_id()+";";
                                connect = db.getConnection();
                                statement = (PreparedStatement) connect.prepareStatement(query);
                                result=statement.executeQuery();
                                
                                 pass=test(result);
                                 if(pass==true){
                                     feedback=true;
                                 Parent root=FXMLLoader.load(getClass().getResource("/javaapplication2/kullaniciArayuz.fxml"));
                                     Scene scene=new Scene(root);
                                    Stage stage=new Stage();
                                    stage.setScene(scene);
                                    stage.initStyle(StageStyle.UTILITY);
                                    stage.show();
                                 }
                                 else{
                                  Alert alert=new Alert(Alert.AlertType.ERROR);
                                    alert.setHeaderText(null);
                                    alert.setContentText("Lutfen önce takip edin.");
                                    alert.showAndWait();
                                 }
                                
                                        
                                        
                                        
                                        
                                        } catch (SQLException ex) {
                                Logger.getLogger(KisilerController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(KisilerController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            
                              
                        });

                        HBox managebtn = new HBox(tkpBtn, bakBtn);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(tkpBtn, new Insets(2, 2, 0, 3));
                        HBox.setMargin(bakBtn, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         edit_col.setCellFactory(cellFoctory);
         kisiler_table.setItems(kisiList);
         
    }
public  boolean test(ResultSet result) throws SQLException{

while(result.next()){
return true;
}
return false;
}
    private void RefreshTable() {
         try{
    kisiList.clear();
    connect=db.getConnection();
    query="Select kullanici_id,kullanici_adi from proje.kullanicilar where premium=1;";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
        kullanici_id=result.getInt("kullanici_id");
    kisiList.add(new kisiler(
            result.getInt("kullanici_id"),
            result.getString("kullanici_adi")
            
            ));
    kisiler_table.setItems(kisiList);
    }
    }catch(SQLException ex){
        Logger.getLogger(adminController.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    }
}
