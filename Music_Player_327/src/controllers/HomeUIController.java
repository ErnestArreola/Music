/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import music_player_327.Song;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import music_player_327.Song;

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
    
    @FXML
    private JFXTreeTableView tableview;
   
    ObservableList<Song> data;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TreeTableColumn ID = new TreeTableColumn("ID");        
        TreeTableColumn artist = new TreeTableColumn("artist");        
        TreeTableColumn title = new TreeTableColumn("title");
        TreeTableColumn time = new TreeTableColumn("time");
        
        tableview.getColumns().addAll(ID, artist, title,time);
        ID.prefWidthProperty().bind(tableview.widthProperty().multiply(0.25));
        artist.prefWidthProperty().bind(tableview.widthProperty().multiply(0.25));
        title.prefWidthProperty().bind(tableview.widthProperty().multiply(0.25));
        time.prefWidthProperty().bind(tableview.widthProperty().multiply(0.25));

        ID.setResizable(false);
        artist.setResizable(false);
        title.setResizable(false);
        time.setResizable(false);
        
        data = FXCollections.observableArrayList(
        new Song("111iii","Lili","title1","time1")
        );
        
        Song s1 = new Song("22221wwww", "AMIT", "Ti", "time2");
        Song s2 = new Song( "3332sssa", "Peter", "eee", "time3");
        Song s3 = new Song( "3312sss", "SAM", "www", "time4");
        Song s4 = new Song("33sssdc", "Jhon", "ffff", "time5");                   
           
        data.addAll(s1,s2,s3, s4);
        
        ID.setCellValueFactory(
            new TreeItemPropertyValueFactory<Song,String>("ID")
        );
    
        artist.setCellValueFactory(
            new TreeItemPropertyValueFactory<Song,String>("artist")
        );
    
        title.setCellValueFactory(     
             new TreeItemPropertyValueFactory<Song,String>("title")
        ); 
        time.setCellValueFactory(
            new TreeItemPropertyValueFactory<Song,String>("time")
        ); 
        
        TreeItem<Song> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);
        
        tableview.setRoot(root);
        tableview.setShowRoot(false);
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
