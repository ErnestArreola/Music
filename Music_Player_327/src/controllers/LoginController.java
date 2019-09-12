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
import javafx.scene.Parent;

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
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnSignin;
    
    /// -- 
   
    
    
      private static final String[] DUMMY_CREDENTIALS = new String[] {
            "admin:admin", "me:me"
    };

    

    @FXML
    void doSomething(MouseEvent event) {

    }

    @FXML
    public void handleButtonAction(MouseEvent event) {

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
        // TODO

    }



    private String logIn() {

        String username = txtUsername.getText();
        String password = txtPassword.getText();
        
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
                System.err.println("No user account was created!");
            }
            bufReader.close();
            
            for(User user : userList){
                if(user.getEmail().equals(username) && user.checkPassword(password)){
                    currentUser = user;

                    
                    return "Success";
                }
            }
       
            
            
            labelErrors.setTextFill(Color.TOMATO);
            labelErrors.setText("Invalid Username/Password");
            System.err.println("Wrong Login --///");
            return "Error";

//            else {
//                labelErrors.setTextFill(Color.GREEN);
//                labelErrors.setText("Login Successful...");
//                System.out.println("Successfull Login");
//                String query = "SELECT userID FROM loginnew WHERE username = ? and password = ?";

//                User temp = new User();
//                temp.setId(resultSet.getInt("userID"));
//                 session = temp;
//                return "Success";
//            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        } 

    }
}