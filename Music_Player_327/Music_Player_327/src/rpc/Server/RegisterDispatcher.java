/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpc.Server;
import Main.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.*;
import java.util.Base64;
import java.io.FileNotFoundException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controllers.CreateController;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LILI
 */
public class RegisterDispatcher {
    static final int FRAGMENT_SIZE = 8192; 
    public RegisterDispatcher()
    {
        
    }
    /**
     * Create account with user info
     * @param fn : firstName
     * @param ln : lastName
     * @param emial : Email
     * @param password : Password
     * @return Success when create account
     */
    private String createAccount(String fn,String ln,String email,String password) throws IOException {
        Gson gson = new Gson();
        BufferedReader bufReader;
        List<User> userList = null;
        try {
            bufReader = new BufferedReader(new FileReader("src/files/users.json"));
            bufReader.mark(10);   
            if(bufReader.read() != -1){
                bufReader.reset();
                Type listType = new TypeToken<List<User>>() {}.getType();
                userList = gson.fromJson(bufReader, listType); 
            }
            else{
                userList = new ArrayList<User>();
            }
            bufReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        BufferedWriter bufWriter;

        User newUser = new User(fn,ln,email,password);
            
        try {
            bufWriter = new BufferedWriter(new FileWriter("src/files/users.json"));
            userList.add(newUser);
                
            String jsonList = gson.toJson(userList);
            bufWriter.write(jsonList);
            bufWriter.close();
                
        } catch (IOException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
        }
        return("Success");
    }
}
