/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;
import Main.Playlist;
import Main.User;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author LILI
 */
public class PlaylistDispatcher {
    public PlaylistDispatcher()
    {
        
    }
   
    /**
     *
     * @param currentUser
     * @return 
     */
    public ArrayList<Playlist> getPlaylist(User currentUser) throws IOException {
        ArrayList<Playlist> playlists = currentUser.getPlaylist();
        return playlists;
    }
    
    public String createPlaylist(User currentUser, String playlistName) throws IOException{
        Playlist newPlaylist = new Playlist();
        newPlaylist.setName(playlistName);
        currentUser.addPlaylist(newPlaylist);
        return null;
    }
    public String deletePlaylist(User currentUser, String playListName)throws IOException{
        currentUser.removePlaylist(playListName);
        return null;
    }
    public String addSongPlaylist(User currentUser, String title, String album, String artist,String playlist)throws IOException{
        currentUser.getPlaylist(playlist.toString()).addSong(title, album, artist);
        return null;
    }
}
