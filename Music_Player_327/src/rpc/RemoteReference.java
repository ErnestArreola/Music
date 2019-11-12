/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author AppleSauce
 */
public class RemoteReference implements RemoteReferenceInterface {
    
    private CommunicationModule cModule;
    private JsonObject catalog = null;
//    private AssetManager am;
    
    
    public RemoteReference(CommunicationModule cModule) throws IOException {
    this.cModule = cModule;
    //catalog = getCatalog();
//    System.out.println(catalog.toString());
   
    }
    
    public String getCata() throws IOException{
        
        
        return new String(Files.readAllBytes(Paths.get("src/rpc/Catalog.json")), StandardCharsets.UTF_8);

    }
    
    @Override
    public JsonObject getRemoteRef(String remoteMethod) {
        Gson gson = new Gson();
    //Gson gson = new Gson().setLenient();
    JsonObject req = new JsonObject();
    JsonObject[] jsonRequest;
    JsonReader reader = null;

    
    
    try{
        System.out.println("Remote Reference");
        String filePath = ("src/rpc/Catalog.json");
//               String filePath = getCata();
//         reader = new JsonReader(new StringReader(filePath));
//        reader.setLenient(true);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
       jsonRequest = gson.fromJson(br, JsonObject[].class);
        
        for(JsonObject object: jsonRequest) {
            
            if(object.get("remoteMethod").getAsString().equals(remoteMethod)) {
            req = object;
            break;
            }
        
        }
       
    
    
    }catch(Exception e) {
        e.printStackTrace();
    }
    return req;
    }
    
//    
//    private JsonObject getCatalog() throws IOException {
//    JsonObject jsonRequest = new JsonObject();
//    jsonRequest.addProperty("remoteMethod", "getCatalog");
//    jsonRequest.addProperty("object", "RemoteRefServices");
//    jsonRequest.add("param", new JsonObject());
//    
//    String ret = cModule.send(jsonRequest);
//    
//    JsonParser jsonParser = new JsonParser();
//    
//    // System.out.println(jsonParser.parse(ret.trim()).getAsJsonObject() + "3");
//
////    
////    return jsonParser.parse(ret.trim()).getAsJsonObject();
////    
//     JsonObject me = null;
//    return me;
//    
//    }
    
    
    
}
