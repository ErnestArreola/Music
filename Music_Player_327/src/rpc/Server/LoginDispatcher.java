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

/**
 *
 * @author LILI
 */
public class LoginDispatcher {
    static final int FRAGMENT_SIZE = 8192; 
    public LoginDispatcher()
    {
        
    }

    /**
     * Check the user info is correct to login
     * @param username : Username
     * @param password : Password
     * @return Success for user exist, false for user not exist
     */
    public String LoginUser(String username, String password) throws IOException{
        Gson gson = new Gson();
        System.out.println("HEY YOU");
        List<User> userList = null;
        BufferedReader bufReader;
        //get the User lists first
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
//                Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        //get the user object with username
        //check user exist in User list or not
        //check user's password right or not
        for(User user : userList) {
            if(user.getEmail().equals(username) && user.checkPassword(password)) {
                return "Success";
            }
        }
        return "false";
    }
}
