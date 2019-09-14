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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.lang.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javazoom.jl.decoder.JavaLayerException;
import model.Album;
import model.Artist;
import model.Music;
import model.Something;
import model.Song;


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
    private HBox Box_Playlist;

    @FXML
    private ScrollPane Settings_Pane;
    
    @FXML
    private ScrollPane Search_Pane;

    @FXML
    private Pane Home_Panel;
    
     private User currentUser;
     
     private String file = "";
     
     private FileInputStream input = null;
     
      private MusicPlayer player = null;
      
    @FXML 
    private TextField tableFilter;
    
    
    @FXML
     private TableView<Song> musicLibrary;
    
    @FXML private TableColumn<Song, String> songID;
    @FXML private TableColumn<Song, String> artist;
    @FXML private TableColumn<Song, String> songTitle;
    @FXML private TableColumn<Song, String> songDuration;
    

    private final ObservableList<Song> dataList = FXCollections.observableArrayList();
     
     
        FileReader files = null;
    @FXML
    private ListView<?> playlist_view;
    @FXML
    private FontAwesomeIconView playpauseIcon;
    
   
    
    
    @FXML
    void handlePlayClick(MouseEvent event) {
       if(playpauseIcon.getGlyphName().equals("PLAY") && file != "" ) {
       playpauseIcon.setIcon(FontAwesomeIcon.PAUSE);
       playMusic();
       }
       else if(playpauseIcon.getGlyphName().equals("PAUSE")){
               playpauseIcon.setIcon(FontAwesomeIcon.PLAY);
               pauseMusic();
       }

    }



    @FXML
    void handleSearch(KeyEvent event) {
        if (search.getText() == null || search.getText().trim().isEmpty()) {
            Home_Panel.toFront();
        
        }
//        else if(search.getText() != null){
//            Search_Pane.toFront();
//        }

    }
    

   
    @FXML
    void handleSearches(KeyEvent event) {
         if (search.getText() == null || search.getText().trim().isEmpty()) {
            Home_Panel.toFront();
        
        }
         else if(search.getText() != null){
            Search_Pane.toFront();
        }

    }

       
           // private MusicPlayer player = new MusicPlayer();
            
             Thread thread = new Thread();
             
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
         Scrollpane.setVbarPolicy(ScrollBarPolicy.NEVER);   
        callMe();
        callSearch();

//            bufReader.mark(10);   
//            if(bufReader.read() != -1){
//                bufReader.reset();
//                Type listType = new TypeToken<List<Music>>() {}.getType();
//                userList = gson.fromJson(bufReader, listType); 
//                            System.out.println("CAMILLA!");
//            }
//            else{
//                System.err.println("No user account was created!");
//            }
//            bufReader.close();

    

    }    
    
    public void setCurrentUser(User current) {
          this.currentUser =  current;
                   Account_Name.setText(currentUser.getFirstName());

    }
    
    void callMe() {        
        try{
        BufferedReader bufReader;
        bufReader = new BufferedReader(new FileReader("src/files/newjson.json"));
        bufReader.mark(10);
        
       Type REVIEW_TYPE = new TypeToken<ArrayList<Music>>(){}.getType();        
//             BufferedReader br = new BufferedReader(new FileReader("src/files/music.json"));
        List<Music> userList  = new Gson().fromJson(bufReader, REVIEW_TYPE);
        System.out.println(userList);
        }catch (Exception ex){}
}
    

    
    @FXML
    void handleSubMenu(ActionEvent event) {
        //Settings_Pane.toFront();
       // callMusicPlayer();
               try {
              input = new FileInputStream(file); 
              player = new MusicPlayer(input);
          
	     }
	     catch (JavaLayerException exception) 
         {
	       exception.printStackTrace();
	     }
         catch (IOException exception)
         {
             exception.printStackTrace();
         }

       
    }
    
    @FXML
    void handelMenu(MouseEvent event) {
        if(event.getSource() == Menu_Settings) {
      }

    }
    
    
        
    @FXML
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
        
        if (Event.getSource() == Box_Playlist) {
            currentUser.addPlaylist("Ok");
        }
     
    }
        
        void playMusic() {
         try {

             player.play();
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         
      
        }
        
        
         void pauseMusic() {
         try {

             player.pause();
         }
         catch (Exception e) {
             e.printStackTrace();
         }
         
                 }
        
        
// final Runnable doHelloWorld = new Runnable() {
//     public void run() {
//         System.out.println("Hello World on " + Thread.currentThread());
//     }
// };
//
// Thread appThread = new Thread() {
//     public void run() {
//         try {
//             FileInputStream input = new FileInputStream("imperial.mp3");
//             MusicPlayer player = new MusicPlayer(input);
//             player.play();
//
//         }
//         catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
// };
// 
 
 void callSearch() {
            // TODO
            
        songID.setCellValueFactory(new PropertyValueFactory<>("songID"));       
        artist.setCellValueFactory(new PropertyValueFactory<>("artist"));        
        songTitle.setCellValueFactory(new PropertyValueFactory<>("songTitle"));        
        songDuration.setCellValueFactory(new PropertyValueFactory<>("songDuration"));             
        
        
        Song s1 = new Song("111", "AMIT", "Ti", "344");
        Song s2 = new Song( "115", "Peter", "eee", "221");
        Song s3 = new Song( "113", "SAM", "www", "eeee");
        Song s4 = new Song("117", "Jhon", "ffff", "444");                   
           
        dataList.addAll(s1,s2,s3, s4);
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Song> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(song -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (song.getArtist().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (song.getSongTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (song.getSongID().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Song> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(musicLibrary.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		musicLibrary.setItems(sortedData);
  
}
 
}
