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
import model.caz;
import model.pop;
import model.rock;

/**
 * FXML Controller class
 *
 * @author akuta
 */
public class KullanıcıArayüzController implements Initializable {

    @FXML
    private TableView<pop> pop_table;
    @FXML
    private TableColumn<pop, String> pop_name_col;
    @FXML
    private TableColumn<pop, String> pop_album_col;
    @FXML
    private TableColumn<pop, String> pop_tarih_col;
    @FXML
    private TableColumn<pop, String> pop_izlenme_col;
    @FXML
    private TableColumn<pop, String> pop_edit_col;
    @FXML
  
    private TableColumn<rock, String> rock_name_col;
    @FXML
    private TableColumn<rock, String> rock_album_col;
    @FXML
    private TableColumn<rock, String> rock_tarih_col;
    @FXML
    private TableColumn<rock, String> rock_izlenme_col;
    @FXML
    private TableColumn<rock, String> rock_edit_col;
    @FXML
   
    private TableColumn<caz, String> caz_name_col;
    @FXML
    private TableColumn<caz, String> caz_album_col;
    @FXML
    private TableColumn<caz, String> caz_tarih_col;
    @FXML
    private TableColumn<caz, String> caz_izlenme_col;
    @FXML
    private TableColumn<caz, String> caz_edit_col;
    @FXML
    private Button tum_sarkilar;
    @FXML
    private Button cikis;
    @FXML
    private Button yenile;
    @FXML
    private Button kullanicilar;
     String query=null;
    Connection connect=null;
    PreparedStatement statement=null;
    ResultSet result=null;
    Sarki sarki=null;
    
    DbHelper db=new DbHelper();
    ObservableList<pop> popList=FXCollections.observableArrayList();
    ObservableList<rock> rockList=FXCollections.observableArrayList();
    ObservableList<caz> cazList=FXCollections.observableArrayList();
    @FXML
    private TableView<rock> rock_table;
    @FXML
    private TableView<caz> caz_table;
    @FXML
    private TableColumn<pop, String> pop_id;
    @FXML
    private TableColumn<rock, String> rock_id;
    @FXML
    private TableColumn<caz, String> caz_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
        } catch (SQLException ex) {
            Logger.getLogger(KullanıcıArayüzController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void tum_sarkilarAct(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("Tum_sarkilar.fxml"));
       Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
     public void loadDate() throws SQLException {
        connect=db.getConnection();
        yenileAct();
        yenile_2();
        yenile_3();
        pop_name_col.setCellValueFactory(new PropertyValueFactory<>("Sarki_adi"));
        pop_album_col.setCellValueFactory(new PropertyValueFactory<>("album"));
        pop_tarih_col.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        pop_izlenme_col.setCellValueFactory(new PropertyValueFactory<>("izlenme"));
        rock_name_col.setCellValueFactory(new PropertyValueFactory<>("Sarki_adi"));
        rock_album_col.setCellValueFactory(new PropertyValueFactory<>("album"));
        rock_tarih_col.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        rock_izlenme_col.setCellValueFactory(new PropertyValueFactory<>("izlenme"));
        caz_name_col.setCellValueFactory(new PropertyValueFactory<>("Sarki_adi"));
        caz_album_col.setCellValueFactory(new PropertyValueFactory<>("album"));
        caz_tarih_col.setCellValueFactory(new PropertyValueFactory<>("tarih"));
        caz_izlenme_col.setCellValueFactory(new PropertyValueFactory<>("izlenme"));
        
        
        
         Callback<TableColumn<pop, String>, TableCell<pop, String>> cellFoctory;
        cellFoctory = (TableColumn<pop, String> param) -> {
           
            
            final TableCell<pop, String> cell = new TableCell<pop, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        
                        Button addBtn=new Button("|>");

                       
                        addBtn.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        
                        addBtn.setOnMouseClicked((MouseEvent event) -> {
                            
                            pop Pop = pop_table.getSelectionModel().getSelectedItem();
                      
                             try{
                                connect=db.getConnection();
                                query="update proje.sarki set dinlenme_sayisi=dinlenme_sayisi+1 where sarki_id="+Pop.getPop_id()+";";
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);                               
                                statement.setInt(1,sarki.getSarki_id());
                                statement.setInt(2,FXMLController.log_id);
                                statement.executeUpdate();
                                 yenileAct();
                                    yenile_2();
                                yenile_3();
                            }
                                catch(SQLException ex){
                                    
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
         pop_edit_col.setCellFactory(cellFoctory);
         pop_table.setItems(popList);
         Callback<TableColumn<rock, String>, TableCell<rock, String>> cellFoctory_rock;
        cellFoctory_rock = (TableColumn<rock, String> param) -> {
           
            
            final TableCell<rock, String> cell = new TableCell<rock, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {                        
                        Button addBtn_rock=new Button("|>");
                        addBtn_rock.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        
                        addBtn_rock.setOnMouseClicked((MouseEvent event) -> {
                            
                            rock Rock = rock_table.getSelectionModel().getSelectedItem();
                      
                             try{
                                connect=db.getConnection();
                                query="update proje.sarki set dinlenme_sayisi=dinlenme_sayisi+1 where sarki_id="+Rock.getRock_id()+";";
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);                               
                                
                                statement.executeUpdate();
                                 yenileAct();
                                yenile_2();
                                yenile_3();
                            }
                                catch(SQLException ex){
                                    
                                }                             
                        });
                        HBox managebtn = new HBox(addBtn_rock);
                        managebtn.setStyle("-fx-alignment:center");                      
                        HBox.setMargin(addBtn_rock, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
         rock_edit_col.setCellFactory(cellFoctory_rock);
         rock_table.setItems(rockList);
          Callback<TableColumn<caz, String>, TableCell<caz, String>> cellFoctory_caz;
        cellFoctory_caz = (TableColumn<caz, String> param) -> {
            // make cell containing buttons
            
            final TableCell<caz, String> cell = new TableCell<caz, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {                        
                        Button addBtn_caz=new Button("|>");
                        addBtn_caz.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        
                        addBtn_caz.setOnMouseClicked((MouseEvent event) -> {
                            
                            caz Caz = caz_table.getSelectionModel().getSelectedItem();
                      
                             try{
                                connect=db.getConnection();
                                query="update proje.sarki set dinlenme_sayisi=dinlenme_sayisi+1 where sarki_id="+Caz.getCaz_id()+";";
                                    statement=   (com.mysql.jdbc.PreparedStatement) connect.prepareStatement(query);                               
                                
                                statement.executeUpdate();
                                 yenileAct();
                                    yenile_2();
                                yenile_3();
                            }
                                catch(SQLException ex){
                                    
                                }                             
                        });
                        HBox managebtn = new HBox(addBtn_caz);
                        managebtn.setStyle("-fx-alignment:center");                      
                        HBox.setMargin(addBtn_caz, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
         caz_edit_col.setCellFactory(cellFoctory_caz);
         caz_table.setItems(cazList);
     }
    @FXML
    private void cikisAct(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void yenileAct() throws SQLException {
           
    popList.clear();
    if(KisilerController.feedback==true){
    KisilerController.feedback=false;
    connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.pop,proje.sarki where pop.pop_id=sarki.sarki_id AND pop.kullanici_id="+KisilerController.kullanici_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    popList.add(new pop(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    pop_table.setItems(popList);
    }
    
    }
    else{
    connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.pop,proje.sarki where pop.pop_id=sarki.sarki_id AND pop.kullanici_id="+FXMLController.log_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    popList.add(new pop(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    pop_table.setItems(popList);
    }
    }
           }
           private void yenile_2() throws SQLException{
     rockList.clear();
      if(KisilerController.feedback==true){
    KisilerController.feedback=false;
    connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.pop,proje.sarki where pop.pop_id=sarki.sarki_id AND pop.kullanici_id="+KisilerController.kullanici_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    popList.add(new pop(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    pop_table.setItems(popList);
    }
    
    }
      else{
     connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.rock,proje.sarki where rock.rock_id=sarki.sarki_id AND rock.kullanici_id="+FXMLController.log_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    rockList.add(new rock(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    rock_table.setItems(rockList);
    }
      }
           }
           public void yenile_3() throws SQLException{
               cazList.clear();
                if(KisilerController.feedback==true){
    KisilerController.feedback=false;
    connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.pop,proje.sarki where pop.pop_id=sarki.sarki_id AND pop.kullanici_id="+KisilerController.kullanici_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    popList.add(new pop(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    pop_table.setItems(popList);
    }
    
    }else{
               connect=db.getConnection();
    query="select sarki_adi,album,sarki_tarih,dinlenme_sayisi,sarki_id from proje.caz,proje.sarki where caz.caz_id=sarki.sarki_id AND caz.kullanici_id="+FXMLController.log_id+";";
    statement=(PreparedStatement) connect.prepareStatement(query);
    result=statement.executeQuery();
    while(result.next()){
    cazList.add(new caz(
            result.getString("sarki_adi"),
            result.getString("album"),
            result.getDate("sarki_tarih"),
            result.getInt("dinlenme_sayisi"),
            result.getInt("sarki_id")
           ));
    caz_table.setItems(cazList);
    }
                }
    }

    @FXML
    private void kullanicilarAct(ActionEvent event) throws IOException {
         Parent root=FXMLLoader.load(getClass().getResource("/javaapplication2/kisiler.fxml"));
                                     Scene scene=new Scene(root);
                                    Stage stage=new Stage();
                                    stage.setScene(scene);
                                    stage.initStyle(StageStyle.UTILITY);
                                    stage.show();
    }
    
}
