/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Music;
import chord.DFS;
import chord.RemoteInputFileStream;
import java.util.List;

/**
 *
 * @author AppleSauce
 */


public class MusicDispatcher {
    Gson gson = new Gson();
    ArrayList<Music> song = null;
    ArrayList<Music> searchResult = new ArrayList();
    BufferedReader br = null;
    DFS dfs;
    
    public MusicDispatcher (DFS dfs)  {
        this.dfs = dfs;
        
        
        
        
        try (Reader reader = new FileReader("src/files/music.json")) {
            song = gson.fromJson(reader, new TypeToken<ArrayList<Music>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }


              
    }
 
    
    public String getBrowseMusic(String count) {
    ArrayList<Music> browseResult = new ArrayList();
    for(int i= 0; i < Integer.valueOf(count); i++) {
    browseResult.add(song.get(i));
   
    }
    return gson.toJson(browseResult);
    }
    
    

    
    public String getSearchMusic(String search) {
        System.out.println(search);
            ArrayList<Music> searchResult = new ArrayList();
            for (Music entry : song) {
            if (searchResult.size() > 20)
                break;
            if (entry.getSong().getTitle().toLowerCase().contains(search.toLowerCase()))
                searchResult.add(entry);
            // Check if artist was found
            if (entry.getArtist().getName().toLowerCase().contains(search.toLowerCase()))
               searchResult.add(entry);
            // Check if album was found
            if (entry.getAlbum().getName().toLowerCase().contains(search.toLowerCase()))
                searchResult.add(entry);
            // Check if genre was found
        }
            for (Music result: searchResult) {
            	System.out.println(result.getSong());
            }
            return gson.toJson(searchResult);

    }
    
    
    private ArrayList<Music> getSongList (int pageNum) {
    	
    	RemoteInputFileStream rifs;
        try {
            rifs = dfs.read("src/files/music.json", 0);									// ======================> need to read from right file
            rifs.connect();
            String result = "";
            while (rifs.available() > 0)
                    result += (char) rifs.read();

            rifs.close();
            song = gson.fromJson(result, new TypeToken<ArrayList<Music>>(){}.getType());  
            return song;
        } catch (Exception ex) {
            Logger.getLogger(MusicDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;  
    }
    
    
}
