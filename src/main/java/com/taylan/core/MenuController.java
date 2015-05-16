/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taylan.core;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 * 
 *          Menu Controller
 */
public class MenuController extends AnchorPane implements Initializable {

    @FXML
    Button deleteAccount;
    @FXML
    Button schdule;
    @FXML
    Button profile;
    @FXML
    Button trainers;
    @FXML
    Button logOut;
    
    private MainApp application;
    
    /**
     * @return the application
     */
    public MainApp getApplication() {
        return application;
    }
    
    public void setApplication(MainApp application) {
        this.application = application;
    }
    
    
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void processDeleteAccount(ActionEvent event) {
   
    
    }
    
    public void processSchdule(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        } 
        application.gotoSchedule();
    }
    
    public void processProfile(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.gotoProfile();
    }
    
    public void processTrainers(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        } 
        application.openTrainerPage();
    }   
    
    
    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.userLogout();
    }
    
    
    
}
