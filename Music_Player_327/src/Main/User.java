/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music_player_327;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;

/**
 *
 * @author sovathana
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Playlist> playlists;
    
    public User(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.playlists = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void addPlaylist(String name){
        playlists.add(new Playlist(name));
    }
    
    public void addSong(String playlistName, String songId){
        
        int i = 0;
        for(Playlist playlist : playlists){
            if(playlist.toString().equals(playlistName)){
                break;
            }
            i++;
        }
        playlists.get(i).addSong(songId);
    }
    
    
    
    public boolean checkPassword(String password){
        return this.password.equals(password);
    }  
    
    
    
}
