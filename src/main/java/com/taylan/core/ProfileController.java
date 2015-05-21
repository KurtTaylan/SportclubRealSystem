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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Taylan Kurt  <taylankurt34@gmail.com>
 * 
 *   Profile Controller
 */

public class ProfileController extends AnchorPane implements Initializable {
    
    @FXML
    private TextField username,password,name,surname,gender,age,address,
                      contact,email;
    @FXML 
    private Button save,logout,menu;
    @FXML 
    private Label success;
    
    private MainApp application;
    
    private UserInfo loggedUser;
    
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    public void setApplication(MainApp application){
        this.application = application;
        setLoggedUser(application.getLoggedUser());
        
        Session session = null;
        Transaction tx = null ;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            tx.setTimeout(5);
            
            /* SET PROFILE AREAS ACCORDING TO USERNAME */
            
            name.setText(getLoggedUser().getName());
            surname.setText(getLoggedUser().getSurName());
            gender.setText(getLoggedUser().getGender());
            age.setText(getLoggedUser().getAge());
            address.setText(getLoggedUser().getAddress());
            contact.setText(getLoggedUser().getContact());
            email.setText(getLoggedUser().getEmail());
            username.setText(getLoggedUser().getUsernamee());
            password.setText(getLoggedUser().getPasswordd());
            
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
       
        success.setOpacity(0);
    }
    
    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        
        application.userLogout();
    }
    
    public void saveProfile(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            animateMessage();
            return;
        }
        Session session = null;
        Transaction tx = null ;
       
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            tx.setTimeout(5);
            
            /* GET INFORMATION FROM AREAS AND SET TO DATABASE */
            getLoggedUser().setName(name.getText());
            getLoggedUser().setSurName(surname.getText());
            getLoggedUser().setAge(age.getText());
            getLoggedUser().setAddress(address.getText());
            getLoggedUser().setEmail(email.getText());
            getLoggedUser().setContact(contact.getText());
            getLoggedUser().setAddress(address.getText());
            getLoggedUser().setGender(gender.getText());
            getLoggedUser().setUsernamee(username.getText());
            getLoggedUser().setPasswordd(password.getText());
            
            session.update(getLoggedUser());
            
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
           animateMessage();
        }
        
    }
    
    public void menuProfile(ActionEvent event){
       if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        
        application.gotoMenu();
        
    }
    
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    /**
     * @return the loggedUser
     */
    public UserInfo getLoggedUser() {
        return loggedUser;
    }

    /**
     * @param loggedUser the loggedUser to set
     */
    public void setLoggedUser(UserInfo loggedUser) {
        this.loggedUser = loggedUser;
    }
    
}
