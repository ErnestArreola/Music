/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author AppleSauce
 */
public class Something {
    
    public  Artists artist;
    public  Releases release;

    
    public static class Releases {
        public Releases(){}
        
        public String numbers;
        
        
        public String getNumbers(){
        return numbers;
        }
        
  
    }
    
    public static class Artists {
        public Artists(){}
        public String name;
    
    }
    
    
    
    public Something () {
        
    }
    
}
