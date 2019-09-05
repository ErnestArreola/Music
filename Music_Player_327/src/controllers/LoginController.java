/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

/**
 *
 * @author AppleSauce
 */
public class LoginController implements Initializable {

    static public int me;
    
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

        if (event.getSource() == btnSignin) {
            if (logIn().equals("Success")) {
                try {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/HomeUI.fxml")));
                    HomeUIController setter = new HomeUIController();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
//                    setter.setUser(session);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
//                if (event.getSource() == btnSignup) {
//                try {
//                    Node node = (Node) event.getSource();
//                    Stage stage = (Stage) node.getScene().getWindow();
//                    stage.close();
//                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/CreateAccount.fxml")));
//                    stage.setScene(scene);
//                    stage.setResizable(false);
//                    stage.show();
//                } catch (IOException ex) {
//                    System.err.println(ex.getMessage());
//                }
//            }
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }



    private String logIn() {

        String username = txtUsername.getText();
        String password = txtPassword.getText();


        try {

            if (username == "5050") {
                labelErrors.setTextFill(Color.TOMATO);
                labelErrors.setText("Invalid Username/Password");
                System.err.println("Wrong Login --///");
                return "Error";

            } else {
                labelErrors.setTextFill(Color.GREEN);
                labelErrors.setText("Login Successful...");
                System.out.println("Successfull Login");
                String query = "SELECT userID FROM loginnew WHERE username = ? and password = ?";

//                User temp = new User();
//                temp.setId(resultSet.getInt("userID"));
//                 session = temp;
                return "Success";
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        } 

    }

}
