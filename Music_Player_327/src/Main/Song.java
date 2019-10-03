/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music_player_327;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LILI
 */
public class Song extends RecursiveTreeObject<Song>{
    private SimpleStringProperty songID;
    private SimpleStringProperty artist;
    private SimpleStringProperty songTitle;
    private SimpleStringProperty songDuration;
    
    public Song(String id, String artist, String Title, String duration){
        this.songID = new SimpleStringProperty(id);
        this.artist = new SimpleStringProperty(artist);
        this.songTitle = new SimpleStringProperty(Title);
        this.songDuration = new SimpleStringProperty(duration);
    }
    
    public String getArtist(){
        return artist.get();
    }
    public String getID(){
        return songID.get();
    }
    public String getTitle(){
        return songTitle.get();
    }
    public String getDuration(){
        return songDuration.get();
    }
    public void setArtist(String artist){
        this.artist.set(artist);
    }
    public void setID(String id){
        this.songID.set(id);
    }
    public void setTitle(String title){
        this.songTitle.set(title);
    }
    public void setDuration(String duration){
        this.songDuration.set(duration);
    }
    
}
