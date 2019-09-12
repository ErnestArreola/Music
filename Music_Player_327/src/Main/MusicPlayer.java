/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author AppleSauce
 */
public class MusicPlayer {
   
      /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
   public void mp3play(String file) {
        try {
            // It uses CECS327InputStream as InputStream to play the song 
             InputStream is = new CECS327InputStream(file);
             Player mp3player = new Player(is);
             mp3player.play();
             
             if (mp3player.isComplete()) {
             mp3player.close();
		}
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
     
 
}