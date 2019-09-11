/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import music_player_327.User;

/**
 * FXML Controller class
 *
 * @author sovathana
 */
public class CreateController implements Initializable {

    @FXML
    private JFXTextField txtFirstname;
    
    @FXML
    private JFXTextField txtLastname;
    
    @FXML
    private JFXTextField txtEmail;
    
    @FXML
    private JFXPasswordField txtPassword;
    
    @FXML
    private JFXButton btnSignup;
    
    @FXML
    private JFXButton btnCancel;
    
    @FXML
    private Label alertLabel;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
 
    
    
    @FXML
    private void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == btnSignup) {
            if (createAccount().equals("Success")) {
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        if(event.getSource() == btnCancel) {
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }
    }
    
    
    private String createAccount() throws IOException {
        
        String validation = validateInfo();
        
        if (!validation.equals("Error")){
            txtEmail.setUnFocusColor(Color.BLACK);
            
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

            User newUser = new User(txtFirstname.getText(),txtLastname.getText(),
                    txtEmail.getText(),txtPassword.getText());
            
            try {
                bufWriter = new BufferedWriter(new FileWriter("src/files/users.json"));
                userList.add(newUser);
                
                String jsonList = gson.toJson(userList);
                bufWriter.write(jsonList);
                bufWriter.close();
                
            } catch (IOException ex) {
                Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
            }


            return("Success");
        } 
        
        return("Error");
    }
    
    
    private boolean emailExist(String email) {

        
        Gson gson = new Gson();
        BufferedReader bufReader;
        
        try {
            bufReader = new BufferedReader(new FileReader("src/files/users.json"));
            bufReader.mark(10); 
            if(bufReader.read() == -1){
                return false;
            }
            else{
                bufReader.reset();
                User[] userArr = gson.fromJson(bufReader, User[].class);
                bufReader.close();
                for(User user : userArr){
                    if (user.getEmail().equalsIgnoreCase(email)){                    
                        return true;
                    }
                }   
            } 
              
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    private String validateInfo(){
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String email  = txtEmail.getText();
        String password = txtPassword.getText();
        

                if (firstName.equals("")) {
                    System.err.println("Please enter a first name --///");
                    txtFirstname.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("Please enter a first name!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
                } else if (lastName.equals("")) {
                    System.err.println("Please enter a last name --///");
                    txtLastname.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("Please enter a last name!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
                } else if (password.equals("")) {
                    System.err.println("Please enter a password --///");
                    txtPassword.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("Please enter a password!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
                } else if (password.length() < 6) {
                    System.err.println("Password has to be 6 or more characters --///");
                    txtPassword.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("Password has to be 6 or more characters!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
                } else if (email.equals("")) {
                    System.err.println("Please enter a an email address --///");
                    txtEmail.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("Please enter an email address!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
                } else if (emailExist(email)) {
                    System.err.println("There is already an account associated with this email!");
                    txtEmail.setUnFocusColor(Color.TOMATO);
                    alertLabel.setText("There is already an account associated with this email!");
                    alertLabel.setTextFill(Color.TOMATO);
                    return "Error";
               }
        return "Success";
    }
    
    
}
