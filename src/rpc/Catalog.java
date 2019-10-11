/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;

/**
 *
 * @author sovathana
 */
public class Catalog {
    
    
    private HashMap<String,JsonObject> remoteMethods = new HashMap<>();
    
    
    
    public Catalog(){
        
        JsonObject getSongChunk = new JsonObject();
        getSongChunk.addProperty("remoteMethod", "getSongChunk");
        getSongChunk.addProperty("objectName", "SongServices");
        remoteMethods.put("getSongChunk", getSongChunk);
        
        JsonObject getFileSize = new JsonObject();
        getFileSize.addProperty("remoteMethod", "getFileSize");
        getFileSize.addProperty("objectName", "SongServices");
        remoteMethods.put("getFileSize", getFileSize);
        
        JsonObject getPlaylist = new JsonObject();
        getPlaylist.addProperty("remoteMethod", "getPlaylist");
        getPlaylist.addProperty("objectName", "PlaylistServices");
        remoteMethods.put("getPlaylist", getPlaylist);
        
        JsonObject createPlaylist = new JsonObject();
        createPlaylist.addProperty("remoteMethod", "createPlaylist");
        createPlaylist.addProperty("objectName", "PlaylistServices");
        remoteMethods.put("createPlaylist", createPlaylist);
        
        JsonObject deletePlaylist = new JsonObject();
        deletePlaylist.addProperty("remoteMethod", "deletePlaylist");
        deletePlaylist.addProperty("objectName", "PlaylistServices");
        remoteMethods.put("deletePlaylist", deletePlaylist);
        
        JsonObject addSongPlaylist = new JsonObject();
        addSongPlaylist.addProperty("remoteMethod", "addSongPlaylist");
        addSongPlaylist.addProperty("objectName", "PlaylistServices");
        remoteMethods.put("addSongPlaylist", addSongPlaylist);
        
        

        JsonObject loginUser = new JsonObject();
        loginUser.addProperty("remoteMethod", "loginUser");
        loginUser.addProperty("objectName", "LoginServices");
        remoteMethods.put("loginUser", loginUser);
        
        
        
        JsonObject createAccount= new JsonObject();
        createAccount.addProperty("remoteMethod", "createAccount");
        createAccount.addProperty("objectName", "RegisterServices");
        remoteMethods.put("createAccount", createAccount);
        
    }
    
        
        
        
    public JsonObject getRemoteMethod(String methodName, String[] param, String semantic){

        JsonObject remoteMethod = remoteMethods.get(methodName);
        JsonObject jsonParam = new JsonObject();
        String returnType = "";
        
        
        //add parameter to json object of the remote method being retrieved
        switch(methodName){
            case "getSongChunk":
                jsonParam.addProperty("songID", param[0]);
                jsonParam.addProperty("dataType", "Long");
                jsonParam.addProperty("fragment", param[1]);  
                jsonParam.addProperty("dataType", "Long");
                returnType = "String";
                break;
            case "getFileSize":
                jsonParam.addProperty("songID", param[0]); 
                jsonParam.addProperty("dataType", "Long");
                returnType = "Integer";
                break;
            case "getPlaylist":
                jsonParam.addProperty("currentUser", param[0]);
                jsonParam.addProperty("dataType", "User");
                returnType = "ArrayList<Playlist>";
                break;
            case "createPlaylist":
                jsonParam.addProperty("currentUser", param[0]);
                jsonParam.addProperty("dataType", "User");
                jsonParam.addProperty("playlistName", param[1]);
                jsonParam.addProperty("dataType", "String");
                returnType = "String";
                break;
            case "deletePlaylist":
                jsonParam.addProperty("currentUser", param[0]);
                jsonParam.addProperty("dataType", "User");
                jsonParam.addProperty("playlistName", param[1]);
                jsonParam.addProperty("dataType", "String");
                returnType = "String";
                break;
            case "addSongPlaylist":
                jsonParam.addProperty("currentUser", param[0]);
                jsonParam.addProperty("dataType", "User");
                jsonParam.addProperty("title", param[1]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("album", param[2]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("artist", param[3]);
                jsonParam.addProperty("dataType", "String");
                 jsonParam.addProperty("playlistName", param[4]);
                jsonParam.addProperty("dataType", "String");
                returnType = "String";
                break;
            case "loginUser":
                jsonParam.addProperty("username", param[0]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("password", param[1]);
                jsonParam.addProperty("dataType", "String");
                returnType = "String";
                break;
            case "createAccount":
                jsonParam.addProperty("firstname", param[0]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("lastname", param[1]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("email", param[2]);
                jsonParam.addProperty("dataType", "String");
                jsonParam.addProperty("password", param[3]);
                jsonParam.addProperty("dataType", "String");
                returnType = "String";
                break;
            default:
                System.out.println(methodName + " does not exist.");
                return null; 
        }
        
        remoteMethod.add("param", jsonParam);
        remoteMethod.addProperty("return", returnType);
        remoteMethod.addProperty("semantic", semantic);
        return remoteMethod;
    }

    
    
    
    
    
    
    
    
}
