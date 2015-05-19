/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taylan.core;

import com.taylan.persistence.DAO.UserInfo;
import com.taylan.persistence.util.HibernateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author Taylan Kurt <taylankurt34@gmail.com>
 */
public class SignupController implements Initializable {

    @FXML
    private TextField username,name,surname,age,address,contact,
                      email;
    @FXML
    private PasswordField password,check;
    @FXML
    private RadioButton male,female;
    @FXML
    private Button cancel,create;
    @FXML
    private Label error;
    
    private MainApp application;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error.setText("");
    }    
    
    public void createUser(ActionEvent event){
        if(password.getText().equals(check.getText())){
           error.setText("");
           addUser();
           gotoMenu();
        }else{
            error.setText("Password doesn't match!");
        }
    }
    public void errorDelete(MouseEvent event){
        error.setText("");
    }
    public void addUser(){
        Session session = null;
        Transaction tx = null ;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            tx.setTimeout(5);
            
            /* USER CREATION */
            UserInfo user = new UserInfo();
            user.setName(name.getText());
            user.setSurName(surname.getText());
            user.setAddress(address.getText());
            user.setContact(contact.getText());
            user.setEmail(email.getText());
            user.setUsernamee(username.getText());
            user.setPasswordd(password.getText());
            user.setAge(age.getText());
            
            if(male.isSelected()){
                user.setGender("male");
            }else if(female.isSelected()){
                user.setGender("female");
            }
            
            session.save(user);
            tx.commit();
        } catch (RuntimeException e) {
            try{
    		tx.rollback();
    	    }catch(RuntimeException rbe){
    		rbe.printStackTrace();
    	    }
    	    throw e;
            
        } finally {
            if(session!=null){
    		session.close();
    	    }
        }
        
        
    }
    
    public void gotoMenu(){
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        } 
        application.gotoMenu();
    }
    
    public void gotoLogin(ActionEvent event){
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        } 
        application.gotoLogin();
    }
    
    public void radioCheck(MouseEvent event){
        if(male.isSelected() && female.isSelected()){
            male.setSelected(false);
           female.setSelected(false);
        }
    }
    
    /**
     * @return the application
     */
    public MainApp getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(MainApp application) {
        this.application = application;
    }
    
    
    
}
