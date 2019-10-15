package rpc;

/**
* The Proxy implements ProxyInterface class. The class is incomplete 
* 
* @author  Oscar Morales-Ponce
* @version 0.15
* @since   2019-01-24 
*/

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpc.CommunicationModule;
import rpc.RemoteReference;


public class Proxy implements ProxyInterface {
    
    private CommunicationModule cModule = null;
    private RemoteReference remoteRef;
   

    //Dispatcher dispacher;   // This is only for test. it should use the Communication  Module
    public Proxy() throws SocketException, IOException
    {
            System.out.println("proxy constructor");
            this.cModule = new  CommunicationModule();
            
           // cModule.connect(portNumber);
            this.remoteRef = new RemoteReference(cModule);
        
    }
    
    /*
    * Executes the  remote method "remoteMethod". The method blocks until
    * it receives the reply of the message
    
    */
    
    public void tryMe(){
    System.out.println("Here");
    }
    
    public JsonObject synchExecution(String remoteMethod, String[] param)
    {
        JsonObject jsonRequest = remoteRef.getRemoteRef(remoteMethod);
        JsonObject jsonParam = jsonRequest.get("param").getAsJsonObject();

        
        
//        for (int i = 0; i < param.length; i++) {
//            
//        jsonParam.addProperty(Integer.toString(i), param[i]);
//        
//        }
 
        TreeSet<String> methodName = new TreeSet<>(jsonParam.keySet());
        int i = 0;
        
        for (String s : methodName) {
        jsonParam.addProperty(s, param[i]);
        i++;
        }
//        
        jsonRequest.add("param", jsonParam);
        
        JsonParser parser  = new JsonParser();
        System.out.println("Sending Request: " + jsonRequest.toString());
        String ret = null;
        try {
            ret = cModule.send(jsonRequest);
        } catch (IOException ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Returning response from server to input stream: " + ret);
        

       // System.out.println(parser.parse (ret));
//        JsonObject  me= null;
       
//        return me;
        return parser.parse(ret).getAsJsonObject();
            
              //  JsonElement trimmedElement = new JsonParser().parse(ret.trim());
//        	JsonObject proxyReturnAsJson = trimmedElement.getAsJsonObject();
//                System.out.println(proxyReturnAsJson.toString()  + "Alien");
            

          //String returnParam = ret.trim();
          //return 
          
         // return parser.parse(ret).getAsJsonObject();
           

        
        
//        String finalReturn = "me";
        
       // return parser.parse(finalReturn).getAsJsonObject();
        
        
  
        
//        jsonRequest.addProperty("remoteMethod", remoteMethod);
//        jsonRequest.addProperty("objectName", "SongServices");
//        
//        
//        
//        
//        
//        // It is hardcoded. Instead it should be dynamic using  RemoteRef
//       
//        
//        
//        if (remoteMethod.equals("getSongChunk"))
//        {
//            
//            jsonParam.addProperty("song", param[0]);
//            jsonParam.addProperty("fragment", param[1]);       
//        
//        }
//        if (remoteMethod.equals("getFileSize"))
//        {
//            jsonParam.addProperty("song", param[0]);        
//        }
//        jsonRequest.add("param", jsonParam);
//        
//        JsonParser parser = new JsonParser();
//        String strRet =  this.dispacher.dispatch(jsonRequest.toString());
        
//        return parser.parse(strRet).getAsJsonObject();
    }

    /*
    * Executes the  remote method remoteMethod and returns without waiting
    * for the reply. It does similar to synchExecution but does not 
    * return any value
    * 
    */
   
    
    public void asynchExecution(String remoteMethod, String[] param) 
    {
        
        
//        JsonObject jsonRequest = new JsonObject();
//        JsonObject jsonParam = new JsonObject();
//        jsonRequest.addProperty("remoteMethod", remoteMethod);
//        jsonRequest.addProperty("object", "UserServices");
//
//        jsonParam.addProperty("user", param[0]);
//        jsonRequest.add("param", jsonParam);
//
//        JsonParser parser = new JsonParser();
//        System.out.println("Sending request: " + jsonRequest.toString());
//        String ret = "";
//        try {
//             ret = cModule.send(jsonRequest);
//        } catch (IOException ex) {
//            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        String returnParam = ret.trim();
//        
//       // return parser.parse(ret).getAsJsonObject;
    }
    
   
    
    

}



