/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music_player_327;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.stream.JsonReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/**
 *
 * @author LILI
 */
public class JsonReadFile {
    private String jsonFile;
    private boolean readFromFile;
    public JsonReadFile(){
        
    }
    public JsonReadFile(String file,boolean readFromFile){
        this.jsonFile = file;
        this.readFromFile = readFromFile;
    }
    public ArrayList<Song> Parser(){
        JsonReadFile jsonFile = new JsonReadFile("src/files/music.json",true);
        ArrayList<Song> Songs = new ArrayList<Song>();
//        List Songs = new ArrayList<>();
        try(JsonReader jsonReader = jsonFile.getJsonReader()){
            Gson myGson = new Gson();
            JsonParser jsonParser = new JsonParser();
            JsonArray songArray =  jsonParser.parse(jsonReader).getAsJsonArray();
            for ( JsonElement songOb : songArray ){
                Data songObj = myGson.fromJson(songOb, Data.class);
//                Songs.add(songObj);
                Artist artist = songObj.getArtist();
//                Release r = songObj.getRelease();
                oneSong OneSong = songObj.getSong();
//                System.out.println(artist.getName());
//                System.out.println(OneSong);
                
//                JsonElement json = myGson.fromJson(songOb, JsonElement.class);
//
//                String jsonInString = myGson.toJson(songObj);
//			
//                System.out.println(jsonInString);
                Song s = new Song(OneSong.getID(), artist.getName(),OneSong.getTitle(),String.valueOf(OneSong.getDuration()));
                Songs.add(s);
            }
            return Songs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Release {

        public int id;
        public String name;
        public Release(){}
        
    }

    private class Artist {

//        public float terms_freq;
//        public String terms;
        public String name;
//        public double familiarity;
//        public double longitude;
//        public String id;
//        public String location;
//        public double latitude;
//        public String similar;
//        public double hottnesss;
        
        public Artist(){}
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
    
    private class oneSong {
//        public double key;
//        public double mode_confidence;
//        public double artist_mbtags_count;
//        public double key_confidence;
//        public double tatums_start;
//        public double year;
        public double duration;
//        public double hotttnesss;
//        public double beats_start;
//        public double time_signature_confidence;
        public String title;
//        public double bars_confidence;
        public String id;
//        public double bars_start;
//        public String artist_mbtags;
//        public double start_of_fade_out;
//        public double tempo;
//        public double end_of_fade_in;
//        public double beats_confidence;
//        public double tatums_confidence;
//        public double mode;
//        public double time_signature;
//        public double loudness;
        
        public oneSong(){}
        
        public String getTitle(){
            return title;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getID(){
            return id;
        }
        public void setID(String id){
            this.id = id;
        }
        public double getDuration(){
            return duration;
        }
        public void setTitle(double duration){
            this.duration = duration;
        }
        
    }
   
    public class Data{
        private Release release;
        private Artist artist;
        private oneSong song;
        
        public Data(){}
        public Release getRelease(){
            return release;
        }
        public Artist getArtist(){
            return artist;
        }
        public oneSong getSong(){
            return song;
        }
        public void setRelease(Release release){
            this.release = release;
        }
        public void setArtist(Artist artist){
            this.artist = artist;
        }
        public void setSong(oneSong song){
            this.song = song;
        }
        
    }

    private JsonReader getJsonReader() throws FileNotFoundException {
       JsonReader reader = null;
       if (readFromFile){
            reader = new JsonReader(
                new InputStreamReader(new FileInputStream(this.jsonFile)));
        }
    return reader;
    }
}
