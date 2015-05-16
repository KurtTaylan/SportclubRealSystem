/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taylan.core;

import com.taylan.persistence.DAO.Personaltrainers;
import com.taylan.persistence.util.HibernateUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *
 * Personal Trainers Controller
 */
public class PersonalTrainersController extends AnchorPane implements Initializable {

    ObservableList<Personaltrainers> personData;

    @FXML
    TableView<Personaltrainers> personalTrainers;
    @FXML
    TableColumn<Personaltrainers, Integer> id;
    @FXML
    TableColumn<Personaltrainers, String> name;
    @FXML
    TableColumn<Personaltrainers, Integer> experience;
    @FXML
    TableColumn<Personaltrainers, String> email;
    @FXML
    TableColumn<Personaltrainers, String> contact;
    @FXML
    Button menu;

    private MainApp application;

    public ObservableList<Personaltrainers> getPersonData() {
        return personData;
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

    public void initialize(URL location, ResourceBundle resources) {

       // System.out.println("Data transfer Prepare!");

        id.setCellValueFactory(new PropertyValueFactory<Personaltrainers, Integer>("idpersonalTrainers"));
        name.setCellValueFactory(new PropertyValueFactory<Personaltrainers, String>("name"));
        experience.setCellValueFactory(new PropertyValueFactory<Personaltrainers, Integer>("experience"));
        email.setCellValueFactory(new PropertyValueFactory<Personaltrainers, String>("email"));
        contact.setCellValueFactory(new PropertyValueFactory<Personaltrainers, String>("contact"));

       // System.out.println("Data transfer Ready!");
        displayRow();

       // System.out.println("Data is Ready to SHOW !!");

        // Add observable list data to the table
        personalTrainers.setItems(personData);

       // System.out.println("Data SHOWED succeed!");

    }

    @FXML
    public void processMenu(ActionEvent action) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }

        application.gotoMenu();
    }

     /* Method to DISPLAY personal Trainers on the DATABASE  */
    public void displayRow() {
        Session session = null;
        Transaction tx = null ;
        personData = FXCollections.observableArrayList();
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx=session.beginTransaction();
            tx.setTimeout(5);
            
            List<Personaltrainers> ls = session.getNamedQuery("loadPersonalTrainers").list();
            for (Personaltrainers trainer : ls) {
                personData.add(trainer);
            }
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

}
