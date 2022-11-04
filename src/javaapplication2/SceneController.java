/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author akuta
 */
public class SceneController {
    public Stage stage;
    public Scene scene;
    public Parent root;
    public void kayit_ekrani_gecis() throws Exception{
        root=FXMLLoader.load(getClass().getResource("kayit_ekrani.fxml"));
        
    }
}
