/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music_player_327;

import java.util.ArrayList;

/**
 *
 * @author sovathana
 */
public class Playlist {
    private String name;
    private ArrayList<String> songs;
    
    public Playlist(String name){
        this.name = name;
        songs = new ArrayList<>();
    }
    
    public void addSong(String songId){
        songs.add(songId);
    }
    
    public ArrayList<String> getAllSongs(){
        return songs;
    }
    
    public String getAllSongTitles(){
        return songs.toString();
    }
    
    
    @Override
    public String toString(){
        return name;
    }
     
}
