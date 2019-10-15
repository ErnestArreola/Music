package controllers;

/**
 *
 * @author AppleSauce
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Main.User;
import com.google.gson.JsonObject;
import java.net.SocketException;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import rpc.Proxy;
import rpc.ProxyInterface;
import rpc.CommunicationModule;

/**
 *
 * @author AppleSauce
 */
public class LoginController implements Initializable {

    static public int me;
    
    protected  User currentUser;

    
    @FXML
    private Button btnSignup;

    @FXML
    private Label btnForgot;

    @FXML
    private Label labelErrors;
    
    
    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnSignin;
    
    private int index_user;
    
    Proxy proxy = null;
   
    
        Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                proxy = new Proxy();
                System.out.println(proxy);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

           
//        try {
//           // proxy = new Proxy(3000);
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
        }        
    });
    
    public LoginController() throws SocketException, IOException{
    }
    
    /// -- 
   
    
    
      private static final String[] DUMMY_CREDENTIALS = new String[] {
            "admin:admin", "me:me"
    };

    

    @FXML
    void doSomething(MouseEvent event) {

    }

    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == btnSignup) {
      
            try {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Create.fxml")));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        
        else if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomeUI.fxml"));
                    Parent root = (Parent) loader.load();
                            
                    HomeUIController setLogin = loader.getController();
                    //setLogin.setCurrentUser(currentUser);
                     
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    
                    //Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/HomeUI.fxml")));

                    
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                    setLogin.setCurrentUser(currentUser);

//                    HomeUIController setCurrent = new HomeUIController();
//                    setCurrent.setCurrentUser(currentUser);
//                    setter.setUser(session);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            t.start();


    }



    private String logIn() throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
        Gson gson = new Gson(); 
        BufferedReader bufReader;
        User user = null;
       // Proxy proxy = new Proxy(3000);
        
        try { 

            JsonObject jsonResponse = proxy.synchExecution("LoginUser", new String[] {username, password});
            String response = jsonResponse.get("ret").getAsString();
            System.out.println("What ! ! ! !!  ");
            if(response.equals("false")) {
                //t.interrupt();
                return "Error";
            }
            else if (response.equals("false")){
            labelErrors.setTextFill(Color.TOMATO);
            labelErrors.setText("Invalid Username/Password");
            System.err.println("Wrong Login --///");
                       //    t.interrupt();
            return "Error";
            }
            else {
                     currentUser = user;
  
                    return "Success";
            
            }
                
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        } 

    }
};