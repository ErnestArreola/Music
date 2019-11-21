package rpc.Server;

/**
* SongDispatcher is the main responsible for obtaining the songs 
*
* @author  Oscar Morales-Ponce
* @version 0.15
* @since   02-11-2019 
*/

import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.Base64;
import java.io.FileNotFoundException;
import chord.DFS;
import chord.RemoteInputFileStream;


public class SongDispatcher
{
    static final int FRAGMENT_SIZE = 8192; 
    DFS dfs;
    
    
    public SongDispatcher(DFS dfs)
    {
        this.dfs = dfs;
    }
    
    /* 
    * getSongChunk: Gets a chunk of a given song
    * @param key: Song ID. Each song has a unique ID 
    * @param fragment: The chunk corresponds to 
    * [fragment * FRAGMENT_SIZE, FRAGMENT_SIZE]
    */
    public String getSongChunk(Long key, Long fragment) throws FileNotFoundException, IOException
    {
        byte buf[] = new byte[FRAGMENT_SIZE];          
        
        try{
            RemoteInputFileStream rifs = dfs.read(key + ".mp3", 0);
            rifs.connect();
            rifs.skip(fragment * FRAGMENT_SIZE );
            rifs.read(buf, 0, FRAGMENT_SIZE);
            rifs.close();
        }catch(Exception e){
            System.out.println(e);
        }
             
        
        
//        File file = new File("src/rpc/server/" + key);
//        FileInputStream inputStream = new FileInputStream(file);
//        inputStream.skip(fragment * FRAGMENT_SIZE);
//        inputStream.read(buf);
//        inputStream.close(); 


        // Encode in base64 so it can be transmitted 
         return Base64.getEncoder().encodeToString(buf);
    }
    
    /* 
    * getFileSize: Gets a size of the file
    * @param key: Song ID. Each song has a unique ID 
     */
    public Integer getFileSize(Long key) throws FileNotFoundException, IOException
    {
        
        Integer total = 0;
        
        try{
            RemoteInputFileStream rifs = dfs.read(key + ".mp3",0);
            rifs.connect();
            total = rifs.available();
            rifs.close();
        }catch(Exception e){
        }
        
        
//        File file = new File("src/rpc/server/" + key);
//        if(file == null) {
//        	System.out.println("This file is null");
//        	
//        }
//        Integer total =  (int)file.length();
        
        return total;
    }
    
}



