/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author AppleSauce
 */
public class HomeUIController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    
    @FXML
    private HBox Home_Box;

    @FXML
    private Pane Home_Panel;

    @FXML
    private Pane Browser;
    
    @FXML
    private HBox Box_Browse;


    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
        
        public void handleClicks(MouseEvent Event) {
        if (Event.getSource() == Home_Box) {
            Home_Panel.setStyle("-fx-background-color : #ffffff");
            Home_Panel.toFront();
        }
//        
        
        if (Event.getSource() == Box_Browse) {
            System.out.println("HELLO!!!!!!");
            Browser.setStyle("-fx-background-color : #ffffff");
            Browser.toFront();
        }
     
    }
    
    
   
    
}
