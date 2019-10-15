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
import Main.Playlist;
import Main.Playlist.playlistSongs;
import Main.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.jfoenix.controls.JFXButton;
//import com.sun.deploy.util.SessionState.Client;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javazoom.jl.decoder.JavaLayerException;
import model.Album;
import model.Artist;
import model.Music;
import model.Song;
import model.Songs;


    import javafx.scene.control.TextInputDialog;
import rpc.Proxy;
import rpc.ProxyInterface;




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
    private JFXButton loadMoreButton;
        
        @FXML
    private Pane topTrack3;
        
                @FXML
    private Pane topTrack4;
    
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
    
    @FXML
    private Pane topTrack1;
    
        @FXML
    private Pane topTrack2;

     private User currentUser;
     
     private String file = "";
     
     private FileInputStream input = null;
     
      
     private MusicPlayer player = null;
      
    @FXML
    private ImageView album_art;


    @FXML
    private Text artist_info;

    @FXML
    private Text album_info;
      
    @FXML 
    private TextField tableFilter;
    
    List<Music> musicList = new ArrayList();
    
    @FXML
     private TableView<Music> musicLibrary;
    
    @FXML
    private TableView<Music> Browse_Table;
    
        @FXML
    private TableView<Playlist> Playlist_Table;
    
    
     @FXML
    private Label Playlist_Label;
     
      private TextInputDialog textDialog;

    
        
    @FXML private TableColumn  title = new TableColumn("title");
        
    @FXML private TableColumn  artist = new TableColumn("artist");
    
    
    @FXML private TableColumn  Title = new TableColumn("Title");
        
    @FXML private TableColumn  Artist = new TableColumn("Artist");
    
    @FXML private TableColumn  Album = new TableColumn("Album");
    
    
        @FXML private TableColumn  Titles = new TableColumn("Titles");
        
    @FXML private TableColumn  Artists = new TableColumn("Artists");
    
    @FXML private TableColumn  Albums = new TableColumn("Albums");
    
    final ContextMenu contextMenu = new ContextMenu();
    
    MenuItem cut = new MenuItem("Cut");
    
    private int user_index;
    
    private int load_more = 0;
    
    private ContextMenu ct;
    private MenuItem remove;
    private MenuItem rename;
    
        @FXML
    private ScrollPane playlist_pane;
        
           @FXML
    private ListView<String> SongsInPlaylist;
           
               @FXML
    private Text playlist_title;
               
                @FXML
    private TableView<playlistSongs> playlistSong_View;
                
                
   ProxyInterface proxy = null;

    
        Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                proxy = new Proxy();
                System.out.println(proxy);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        });
    
//    @FXML private TableColumn Music name;
//    @FXML private TableColumn<Artist, String> names;   

    private final ObservableList<Music> dataList = FXCollections.observableArrayList();
    private final ObservableList<Music> browseList = FXCollections.observableArrayList();
        private final ObservableList<Playlist> playLists = FXCollections.observableArrayList();

    private final ObservableList<playlistSongs> playList = FXCollections.observableArrayList();
    
    
    FileReader files = null;
    
    @FXML
    private ListView<Playlist> playlist_view;
    
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
//
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

                               
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         Scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
//         Scrollpane.setVbarPolicy(ScrollBarPolicy.NEVER);
        t.start();
         try{
         addMusicToList();
         callSearch();
         callBrowse();
        artist_info.setText("");
        album_info.setText("");
        addPlaylistListener();
        
        ct = new ContextMenu();
        remove = new MenuItem("Remove");
        rename = new MenuItem("Rename");
        addMusicLibraryListener();
        setDialogBox();
        
           }catch (Exception ex){
         }
    }    
    
    

    
    public void setCurrentUser(User current) {
        if(current != null) {
          this.currentUser =  current;
                   Account_Name.setText(currentUser.getFirstName());
        }
        callBuildPlaylist();
    }
    
    

    
    void callBuildPlaylist(){
        playLists.addAll(currentUser.getPlaylist());
        playlist_view.setItems(playLists);
       
        
    playlist_view.setCellFactory(param -> new ListCell<Playlist>() {
    @Override
    protected void updateItem(Playlist item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null || item.getName() == null) {
            setText(null);
        } else {
            setText(item.getName());
        }
    }
});
    }
    
        @FXML
    void loadMoreClicked(MouseEvent event) {
//        for(int x = load_more; x < (load_more+20); x++) {
//        browseList.add(musicList.get(x));
//        load_more++;
//        System.out.println(load_more);
//        Browse_Table.setItems(browseList);
//        }
    }
    
    //Takes the Music json and deserializes to Classes
    void addMusicToList() {        
        try{
        BufferedReader bufReader;
        bufReader = new BufferedReader(new FileReader("src/files/music.json"));
        bufReader.mark(10);
    
       Type REVIEW_TYPE = new TypeToken<ArrayList<Music>>(){}.getType();        
//             BufferedReader br = new BufferedReader(new FileReader("src/files/music.json"));
        musicList  = new Gson().fromJson(bufReader, REVIEW_TYPE);
        }catch (Exception ex){}
}
    

    
    @FXML
    void handleSubMenu(ActionEvent event) {
        //Settings_Pane.toFront();
       // callMusicPlayer();

       
    }
    
    @FXML
    void handlePlaylistClicks(MouseEvent event) {
        
//         playlist_view.getSelectionModel().getSelectedItem().getName();
//         playlist_title.setText(currentUser.getPlaylist().get(playlist_view.getSelectionModel().getSelectedIndex()).getName());
//         callPlaylistTable(playlist_view.getSelectionModel().getSelectedIndex());
//           playlist_pane.toFront();
//        
//      System.out.println(playlist_view.getSelectionModel().getSelectedItem().getName());
      
    }
    
    @FXML
    void handelMenu(MouseEvent event) {
        if(event.getSource() == Menu_Settings) {


      }
     
    }
    
        private void addPlaylistListener(){
        playlist_view.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(event.isPrimaryButtonDown()){
                    //showSongsInPlaylist
                    try{
                playlist_title.setText(currentUser.getPlaylist().get(playlist_view.getSelectionModel().getSelectedIndex()).getName());
                callPlaylistTable(playlist_view.getSelectionModel().getSelectedIndex());
                    playlist_pane.toFront();
                    }catch(Exception e){}
                }
                else if(event.isSecondaryButtonDown()){
                    
                    //add remove action to right click on playlist
                    remove.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent t) {
                            if(playlist_view.getSelectionModel().getSelectedItem() != null){
                            Playlist toRemove = playlist_view.getSelectionModel().getSelectedItem();
                            playlist_view.getItems().remove(playlist_view.getSelectionModel().getSelectedItem());
                            currentUser.removePlaylist(toRemove.getName());
                            Browser.toFront();
                            }
                        }
                    });
                    
                    ct.getItems().add(remove);
                    ct.show(playlist_view,event.getScreenX(),event.getScreenY());
                   
                }
               
            }
        });
     
    }
        
         private void showSongsInPlaylist(int playlistName){
        Playlist playlist = currentUser.getPlaylist().get(playlistName);
//        SongsInPlaylist.getItems().clear();
//        for(String song : playlist.getAllSongs()){
//            SongsInPlaylist.getItems().add(song);
//        }
    }
    
    //Handles the panes from brows under Top Tracks. Will play music if clicked.
     @FXML
    void handleTop(MouseEvent event)  {
         if(event.getSource() == topTrack1) {
         nowPlaying("imperial.mp3");
         }
         if(event.getSource() == topTrack2) {
         nowPlaying("imperial.mp3");
         }
         if(event.getSource() == topTrack4) {
         nowPlaying("imperial.mp3");
         }
         
    }
    
   private void nowPlaying(String fil) {
      //Null Check
       if(fil != null) {
       file = fil;
       if(file != "") {
       playpauseIcon.setIcon(FontAwesomeIcon.PAUSE);
           try {
              input = new FileInputStream(file); 
              player = new MusicPlayer(new CECS327InputStream((long)490183, proxy));
              //playMusic();
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
       else if(playpauseIcon.getGlyphName().equals("PAUSE")){
               playpauseIcon.setIcon(FontAwesomeIcon.PLAY);
               pauseMusic();
       }
       }
   
   
   }
    
    
        //Anytime you click on the sidebar (Hbox) the relevant pane will be brought up in the middle.
    @FXML
        public void handleClicks(MouseEvent Event) {
        if (Event.getSource() == Home_Box) {
            Home_Panel.setStyle("-fx-background-color : #ffffff");
            Home_Panel.toFront();
        }
//        
        
        if (Event.getSource() == Box_Browse) {
            Browser.setStyle("-fx-background-color : #ffffff");
            Browser.toFront();
        }
        
        if (Event.getSource() == Box_Playlist) {
        
            textDialog.getEditor().clear();
            Optional<String> result = textDialog.showAndWait();
            if(result.isPresent()){
                Playlist newPlaylist = new Playlist();
                newPlaylist.setName(textDialog.getEditor().getText());
                playlist_view.getItems().add(newPlaylist);
                currentUser.addPlaylist(newPlaylist);
            }
        
        }
     
    }

        
        
    @FXML
    void handleContextTop(ContextMenuEvent event) {


        
    }
    
    
    private void setDialogBox(){
        textDialog = new TextInputDialog();
        textDialog.setHeaderText("Enter playlist name:");
        return;
    }
        
    
    private void addMusicLibraryListener(){
        musicLibrary.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(event.isPrimaryButtonDown()){
                    //play song
                    Music selectedMusic = musicLibrary.getSelectionModel().getSelectedItem();
//                    System.out.println(">>> " + selectedMusic.getSong().getTitle());
                }
                else if(event.isSecondaryButtonDown()){
                    
                    ContextMenu ct2 = new ContextMenu();
                    MenuItem mi;
                    
                    for(int i = 0; i < playlist_view.getItems().size(); i++){
                        Playlist playlist = playlist_view.getItems().get(i);
                        mi = new MenuItem("Add to " + playlist.toString());
                        mi.setOnAction(new EventHandler<ActionEvent>() {
                            
                            @Override
                            public void handle(ActionEvent t) {
                                Music toAdd = musicLibrary.getSelectionModel().getSelectedItem();
                                
                                String title = toAdd.getSong().getTitle();
                                String artist = toAdd.getArtist().getName();
                                String album = toAdd.getAlbum().getName();
                                
                                System.out.println(">>> " + currentUser.getPlaylist(playlist.toString()));
                                
                                currentUser.getPlaylist(playlist.toString()).addSong(title, album, artist);
                            }
                        });
                        ct2.getItems().add(mi);
                    }       
                    ct2.show(musicLibrary,event.getScreenX(),event.getScreenY());
                }
            }
        });
    }
    
        
        
        //Calls an object of the Player Class
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
            
        title.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Music, String> c) {
            return new SimpleStringProperty(c.getValue().getSong().getTitle());                
        }
        });
        
        artist.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Music, String> d) {
            return new SimpleStringProperty(d.getValue().getArtist().getName());                
        }
        });
        
        dataList.addAll(musicList);

       // musicLibrary.setItems(dataList);

               
//        // Wrap the ObservableList in a FilteredList (initially display all data).
      FilteredList<Music> filteredData = new FilteredList<>(dataList, b -> false);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(song -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return false;
				}
				 
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (song.getSong().getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (song.getArtist().getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Music> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(musicLibrary.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		musicLibrary.setItems(sortedData);
}
 
     @FXML
    void handleTableClick(MouseEvent event) {
//      int index = Browse_Table.getItems().indexOf(Browse_Table.getSelectionModel().getSelectedItem());
//      playlistSongs me;
//      me = new playlistSongs(browseList.get(index).getSong().title, browseList.get(index).getArtist().getName(), browseList.get(index).getArtist().getName());
//     // browseList.get(index).getSong().title, browseList.get(index).getArtist(), browseList.get(index).getArtist());
//      
//        //currentUser.getPlaylist().get(0).getSongs().get(0).setAlbum();
//      // browseList.get(index).getArtist().getName();
//      
//      currentUser.getPlaylist().get(0).addSong(me);
//      currentUser.getPlaylist().get(0).getSongs();
//      
//       
//       Browse_Table.setOnMousePressed(new EventHandler<MouseEvent>() {
//   @Override
//   public void handle(MouseEvent event) {
//       if (event.isSecondaryButtonDown()) {
//           contextMenu.show(Browse_Table, event.getScreenX(), event.getScreenY());
//           
//       }
//   }
//});
    }
    
    
 
 void callBrowse(){
         Title.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Music, String> c) {
            return new SimpleStringProperty(c.getValue().getSong().getTitle());                
        }
        });
        
        Artist.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Music, String> d) {
            return new SimpleStringProperty(d.getValue().getArtist().getName());                
        }
        });
        
        Album.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<Music, String> d) {
            return new SimpleStringProperty(d.getValue().getArtist().getName());                
        }
        });
        
        
        for(int x = 0 ; x < 20; x++) {
        browseList.add(musicList.get(x));
        load_more = x;
        }
        
        
        

//        
//        
//
//         Browse_Table.setItems(browseList);


                  // browseList.addAll(musicList);

       // musicLibrary.setItems(dataList);

               
//        // Wrap the ObservableList in a FilteredList (initially display all data).
     // FilteredList<Music> filteredData = new FilteredList<>(dataList, b -> true);
		
		
//		// 3. Wrap the FilteredList in a SortedList. 
//		SortedList<Music> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
//		sortedData.comparatorProperty().bind(Browse_Table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Browse_Table.setItems(browseList);
 
 }
 
 
 
  void callPlaylistTable(int index) {
       // playlistSongs me = new playlistSongs();
        
       // System.out.println(currentUser.getPlaylist().get(index).getSongs().indexOf());
         Titles.setCellValueFactory(new Callback<CellDataFeatures<playlistSongs, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<playlistSongs, String> c) {
            return new SimpleStringProperty(c.getValue().getTitle());                
        }
        });
        
        Artists.setCellValueFactory(new Callback<CellDataFeatures<playlistSongs, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<playlistSongs, String> d) {
            return new SimpleStringProperty(d.getValue().getArtist());                
        }
        });
        
        Albums.setCellValueFactory(new Callback<CellDataFeatures<playlistSongs, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(CellDataFeatures<playlistSongs, String> d) {
            return new SimpleStringProperty(d.getValue().getAlbum());                
        }
        });
        
        
       // playList.add()
       playList.removeAll();
       
       
       playList.setAll(currentUser.getPlaylist().get(index).getSongs());
       playlistSong_View.setItems(playList);
 
 }
}
