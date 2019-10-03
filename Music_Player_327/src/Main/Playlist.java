/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import model.Songs;

/**
 *
 * @author sovathana
 */
public class Playlist {
    private String name;
    private List<playlistSongs> songs;
    
//    public Playlist(String name){
//        this.name = name;
//        songs = new ArrayList<>();
//    }
//    
    public void addSong(playlistSongs song) {
         if(songs == null) {
        songs = new ArrayList();
        }
        songs.add(song);
    }
//    
//    public ArrayList<String> getAllSongs(){
//        return songs;
//    }
    
    public String getAllSongTitles(){
        return songs.toString();
    }
    
    public List<playlistSongs> getSongs(){
    return this.songs;
    }
    
    
    public void setName(String n){
    this.name = n;
    }
    
    
    @Override
    public String toString(){
        return name;
    }
    
    public String getName() {
    return name;
    }
    
    public void addSong(String title, String album, String artist){
                 if(songs == null) {
        songs = new ArrayList();
        }
        songs.add(new playlistSongs(title, album, artist));
        
    }
    
    public void rename(String newName){
        name = newName;
    }
    
//    public playlistSongs getTitle(){
//        return songs;
//    }
//    
    
    
    public class playlistSongs {
        private String title;
        private String album;
        private String artist;
        
           public playlistSongs(String title, String album, String artist ){
            this.title = title;
            this.album = album;
            this.artist = artist;
            }

        
        
        public String getTitle(){
        return title;
        }
        
       public void setTitle(String til){
       this.title = til;
       }
       
        public void setAlbum(String al){
        this.album = al;
        }
        
        public void setArtist(String ar){
        this.artist = ar;
        }
        
        public String getAlbum(){
        return album;
        }
        public String getArtist(){
            return artist;
        }
    }
     
}
