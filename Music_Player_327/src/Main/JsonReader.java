///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controllers;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.io.File;
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.Arrays;
//import java.util.ArrayList;
//
///**
// *
// * @author LILI
// */
//public class JsonReader {
//    public static void main(String[] args) throws IOException {
//        
////        Gson gson = new GsonBuilder().create();
////        
////        String fileName = "src/musics/music.json";
////        Path path = new File(fileName).toPath();
////        
////        try (Reader reader = Files.newBufferedReader(path, 
////                StandardCharsets.UTF_8)) {
////            
////            Songs[] users = gson.fromJson(reader, Songs[].class);
////            
////            Arrays.stream(users).forEach( e -> {
////                System.out.println(e);
////            });
////        }
//        String json = "{...json string...}";
//        Gson gson = new Gson();
//        Songs songs = gson.fromJson(json, Songs.class);
//        
//        
//    }
//}
//class Songs extends ArrayList<Songs.Container> {
////    public class Container{
////        public Object release;
////        public Object artist;
////        public Object song;
////    }
////    
////    public class Release{
////        public int id;
////        public String name;
////    }
////    public class Artist{
////        public float terms_freq;
////        public String terms;
////        public String name;
////        public double familiarity;
////        public double longitude;
////        public String id;
////        public String location;
////        public double latitude;
////        public String similar;
////        public double hottnesss;
////    }
////    public class Song{
////        public float key;
////        public double mode_confidence;
////        public double artist_mbtags_count;
////        public double key_confidence;
////        public double tatums_start;
////        public int year;
////        public double duration;
////        public double hotttnesss;
////        public double beats_start;
////        public double time_signature_confidence;
////        public String title;
////        public double bars_confidence;
////        public String id;
////        public double bars_start;
////        public String artist_mbtags;
////        public double start_of_fade_out;
////        public double tempo;
////        public double end_of_fade_in;
////        public double beats_confidence;
////        public double tatums_confidence;
////        public double mode;
////        public double time_signature;
////        public double loudness;
////        
////    }
//    
////    private final String songName;
////    private final String artist;
////    private final String songID;
////
////    public Song(String firstName, String lastName, String id) {
////        this.songName = firstName;
////        this.artist = lastName;
////        this.songID = id;
////    }
////
////    public String getArtist(){
////        return artist;
////    }
////    public String getID(){
////        return songID;
////    }
////    public String getTitle(){
////        return songName;
////    }
//}