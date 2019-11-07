/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.Server;
import Main.Playlist;
import Main.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LILI
 */
public class PlaylistDispatcher {
    
    Gson gson;
    public PlaylistDispatcher()
    {
       gson = new Gson();
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
    
    
    
    
    public String createPlaylist(String currentUser, String playlistName) throws IOException{

        Playlist newPlaylist = new Playlist();
        newPlaylist.setName(playlistName);
        
        User cUser = gson.fromJson(currentUser, User.class);
        
        List<User> userList = null;
        BufferedReader bufReader;
        //get the User lists first
        try {
            bufReader = new BufferedReader(new FileReader("src/files/users.json"));
            bufReader.mark(10);   
            if(bufReader.read() != -1){
                bufReader.reset();
                Type listType = new TypeToken<List<User>>() {}.getType();
                userList = gson.fromJson(bufReader, listType); 
                
                for(int i = 0; i<userList.size(); i++){
                    if(userList.get(i).getEmail().equals(cUser.getEmail())){
                        userList.get(i).addPlaylist(newPlaylist);
                    }
                }
                
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/files/users.json"));
                bufWriter.write(gson.toJson(userList));
                bufWriter.close();   
        
            }
            else{
                userList = new ArrayList<User>();
            }
            bufReader.close();
        } catch (FileNotFoundException ex) {
//                Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        
        
        
        
        
        
        
//        String jsonList = gson.toJson(userList);
//        bufWriter.write(jsonList);
//        bufWriter.close();
        
        return null;
    }
    
    
    
    
    public String deletePlaylist(String currentUser, String playlistName)throws IOException{
        
        
        User cUser = gson.fromJson(currentUser, User.class);
        
        List<User> userList = null;
        BufferedReader bufReader;
        //get the User lists first
        try {
            bufReader = new BufferedReader(new FileReader("src/files/users.json"));
            bufReader.mark(10);   
            if(bufReader.read() != -1){
                bufReader.reset();
                Type listType = new TypeToken<List<User>>() {}.getType();
                userList = gson.fromJson(bufReader, listType); 
                
                for(int i = 0; i<userList.size(); i++){
                    if(userList.get(i).getEmail().equals(cUser.getEmail())){
                        userList.get(i).removePlaylist(playlistName);
                    }
                }
                
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/files/users.json"));
                bufWriter.write(gson.toJson(userList));
                bufWriter.close();   
        
            }
            else{
                userList = new ArrayList<User>();
            }
            bufReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
       
        return null;
    }
    
    
    
    public String addSongPlaylist(String currentUser, String title, String album, String artist,String playlistName)throws IOException{
        
        System.out.println("@@@@@@@@@@@@ addSong is called!");
        User cUser = gson.fromJson(currentUser, User.class);
        
        List<User> userList = null;
        BufferedReader bufReader;
        //get the User lists first
        try {
            bufReader = new BufferedReader(new FileReader("src/files/users.json"));
            bufReader.mark(10);   
            if(bufReader.read() != -1){
                bufReader.reset();
                Type listType = new TypeToken<List<User>>() {}.getType();
                userList = gson.fromJson(bufReader, listType); 
                
                for(int i = 0; i<userList.size(); i++){
                    if(userList.get(i).getEmail().equals(cUser.getEmail())){
                        userList.get(i).getPlaylist(playlistName).addSong(title, album, artist);
                        
                    }
                }
                
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/files/users.json"));
                bufWriter.write(gson.toJson(userList));
                bufWriter.close();   
        
            }
            else{
                userList = new ArrayList<User>();
            }
            bufReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        

        
        
//        currentUser.getPlaylist(playlist.toString()).addSong(title, album, artist);
        return null;
    }
}
