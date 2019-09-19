/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
/**
 *
 * @author AppleSauce
 */
public class Music {
    
    private Album release;
    private Artist artist;
    private Songs song;
    
    
        public Music(){
        
        
        }
        
        public Songs getSong(){
        return song;
        }
        
        public Artist getArtist(){
        return artist;
        }
        
       

    
    
//    public static class Albums {
//        
//        public String name;
//        public Albums(){}
//
//
//}
//    
//        public static class Artists {
//        
//        public String name;
//        public Artists(){}
//
//
//}
//        
//        public static class Songs {
//        
//        public String title;
//        public Songs(){}
//
//
//}
  //  List<Song> song;
    
    
//    public String getSomething(){
//    //return release.get(0).getName();
//    }
    
}
