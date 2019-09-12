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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import Main.MusicPlayer;
import Main.CECS327InputStream;
import Main.User;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.lang.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

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
    private HBox Box_Browse;

    @FXML
    private ScrollPane Scrollpane;

    @FXML
    private TextField search;

    @FXML
    private MenuButton SettingsDropDown;

    @FXML
    private MenuItem Menu_Settings;
    
    @FXML
    private Text Account_Name;

    @FXML
    private ScrollPane Browser;

    @FXML
    private ScrollPane Settings_Pane;
    
    @FXML
    private ScrollPane Search_Pane;

    @FXML
    private Pane Home_Panel;
    
    
     private User currentUser;



    @FXML
    void handleSearch(KeyEvent event) {
        if (search.getText() == null || search.getText().trim().isEmpty()) {
            Home_Panel.toFront();
        
        }
        else if(search.getText() != null){
            Search_Pane.toFront();
        }

    }

       
            private MusicPlayer player = new MusicPlayer();
            
             Thread thread = new Thread();
             
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
         Scrollpane.setVbarPolicy(ScrollBarPolicy.NEVER);   
        // TODO
    }    
    
    public void setCurrentUser(User current) {
          this.currentUser =  current;
                   Account_Name.setText(currentUser.getFirstName());

    }
    

    
    @FXML
    void handleSubMenu(ActionEvent event) {
        //Settings_Pane.toFront();
       // callMusicPlayer();
       
       
    }
    
    @FXML
    void handelMenu(MouseEvent event) {
        if(event.getSource() == Menu_Settings) {
      }

    }
    
    
        
        public void handleClicks(MouseEvent Event) {
        if (Event.getSource() == Home_Box) {
            Home_Panel.setStyle("-fx-background-color : #ffffff");
            Home_Panel.toFront();
            System.out.println(currentUser.getFirstName());
        }
//        
        
        if (Event.getSource() == Box_Browse) {
            System.out.println("HELLO!!!!!!");
            Browser.setStyle("-fx-background-color : #ffffff");
            Browser.toFront();
        }
     
    }
        
        void callMusicPlayer() {
//        MusicPlayer player = new MusicPlayer();
//        player.mp3play("imperial.mp3");
         appThread.start();
      
        }
        
        
 final Runnable doHelloWorld = new Runnable() {
     public void run() {
         System.out.println("Hello World on " + Thread.currentThread());
     }
 };

 Thread appThread = new Thread() {
     public void run() {
         try {
             MusicPlayer player = new MusicPlayer();
             player.mp3play("imperial.mp3");
             
         }
         catch (Exception e) {
             e.printStackTrace();
         }
     }
 };
 
   
    
}
