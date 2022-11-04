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
import model.Sarki2;

/**
 * FXML Controller class
 *
 * @author akuta
 */
public class Tum_sarkilarController implements Initializable {

    @FXML
    private TableView<Sarki> sarkiTable;
    @FXML
    private TableColumn<Sarki, String> sarki_adiCol;
    @FXML
    private TableColumn<Sarki, String> sarki_tarihCol;
    @FXML
    private TableColumn<Sarki, String> sanatciCol;
    @FXML
    private TableColumn<Sarki, String> albumCol;
    @FXML
    private TableColumn<Sarki, String> turCol;
    @FXML
    private TableColumn<Sarki, String> sureCol;
    @FXML
    private TableColumn<Sarki, String> dinlenmeCol;
    @FXML
    private TableColumn<Sarki, String> editCol;
    @FXML
    private Button RefreshBtn;
    
    String query=null;
    Connection connect=null;
    PreparedStatement statement=null;
    ResultSet result=null;
    Sarki sarki=null;
    
    DbHelper db=new DbHelper();
    
    ObservableList<Sarki> SarkiList=FXCollections.observableArrayList();
    @FXML
    private Button geri;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(Tum_sarkilarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void RefreshTable() {
         try{
    SarkiList.clear();
    connect=db.getConnection();
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
    public void loadDate() throws SQLException {
        connect=db.getConnection();
        RefreshTable();
        
        
        sarki_adiCol.setCellValueFactory(new PropertyValueFactory<>("sarki_adi"));
        sarki_tarihCol.setCellValueFactory(new PropertyValueFactory<>("sarki_tarih"));
        sanatciCol.setCellValueFactory(new PropertyValueFactory<>("sanatci"));
        albumCol.setCellValueFactory(new PropertyValueFactory<>("album"));
        turCol.setCellValueFactory(new PropertyValueFactory<>("tur"));
        sureCol.setCellValueFactory(new PropertyValueFactory<>("sure"));
        dinlenmeCol.setCellValueFactory(new PropertyValueFactory<>("dinlenme_sayisi"));
         //add cell of button edit 
         Callback<TableColumn<Sarki, String>, TableCell<Sarki, String>> cellFoctory;
        cellFoctory = (TableColumn<Sarki, String> param) -> {
            // make cell containing buttons
            
            final TableCell<Sarki, String> cell = new TableCell<Sarki, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        
                        Button addBtn=new Button("+");

                       
                        addBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        
                        addBtn.setOnMouseClicked((MouseEvent event) -> {
                            
                            Sarki sarki = sarkiTable.getSelectionModel().getSelectedItem();
                            
                           
                           
                          
                            if(sarki.getTur().equals("Pop")){
                                try{
                                connect=db.getConnection();
                                query="insert into proje.pop values(?,?);";
                                
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);
                                
                                statement.setInt(1,sarki.getSarki_id());
                                statement.setInt(2,FXMLController.log_id);
                                statement.executeUpdate();
                                 RefreshTable();
                            }
                                catch(SQLException ex){
                                    
                                }}
                            else if(sarki.getTur().equals("Rock")){
                                 try{
                                connect=db.getConnection();
                                query="insert into proje.rock values(?,?);";
                                
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);
                                
                                statement.setInt(1,sarki.getSarki_id());
                                statement.setInt(2,FXMLController.log_id);
                                statement.executeUpdate();
                                 RefreshTable();
                            }
                                catch(SQLException ex){
                                    
                                }
                            }
                            else{
                                 try{
                                connect=db.getConnection();
                                query="insert into proje.caz values(?,?);";
                                
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);
                                
                                statement.setInt(1,sarki.getSarki_id());
                                statement.setInt(2,FXMLController.log_id);
                                statement.executeUpdate();
                                 RefreshTable();
                            }
                                catch(SQLException ex){
                                    
                                }
                            
                            }
                            
                            
                            
                            
                        });

                        HBox managebtn = new HBox(addBtn);
                        managebtn.setStyle("-fx-alignment:center");
                       
                        HBox.setMargin(addBtn, new Insets(2, 3, 0, 2));

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
    private void geriAct(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("/javaapplication2/kullaniciArayuz.fxml"));
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
