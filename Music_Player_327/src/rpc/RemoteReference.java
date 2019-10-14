/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author AppleSauce
 */
public class RemoteReference implements RemoteReferenceInterface {
    
    private CommunicationModule cModule;
    private JsonObject catalog;
    
    
    public RemoteReference(CommunicationModule cModule) throws IOException{
    this.cModule = cModule;
    catalog = getCatalog();
   
    }
    
    @Override
    public JsonObject getRemoteRef(String remoteMethod) {
    Gson gson = new Gson();
    JsonObject req = new JsonObject();
    JsonObject[] jsonRequest;
    
    
    try{
        String filePath = "src/rpc/catalog.json";
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
    
    
    private JsonObject getCatalog() throws IOException {
    JsonObject jsonRequest = new JsonObject();
    jsonRequest.addProperty("remoteMethod", "getCatalog");
    jsonRequest.addProperty("object", "RemoteRefServices");
    jsonRequest.add("param", new JsonObject());
    String ret = cModule.send(jsonRequest.toString());
    JsonParser jsonParser = new JsonParser();
    return jsonParser.parse(ret.trim()).getAsJsonObject();
    
    
    }
    
    
    
}
