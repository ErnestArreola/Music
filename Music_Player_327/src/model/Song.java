/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author LILI
 */
public class Song {
    private  final SimpleStringProperty songID;
    private  final SimpleStringProperty artist;
    private final SimpleStringProperty songTitle;
    private final SimpleStringProperty songDuration;
    
    public Song(String id, String artist, String Title, String duration){
        this.songID = new SimpleStringProperty(id);
        this.artist = new SimpleStringProperty(artist);
        this.songTitle = new SimpleStringProperty(Title);
        this.songDuration = new SimpleStringProperty(duration);
    }
    
    public String getArtist(){
        return artist.get();
    }
    public String getSongID(){
        return songID.get();
    }
    public String getSongTitle(){
        return songTitle.get();
    }
    public String getSongDuration(){
        return songDuration.get();
    }
    public void setArtist(String artist){
        this.artist.set(artist);
    }
    public void setSongID(String id){
        this.songID.set(id);
    }
    public void setSongTitle(String title){
        this.songTitle.set(title);
    }
    public void setSongDuration(String duration){
        this.songDuration.set(duration);
    }
    
}
