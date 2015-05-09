package com.taylan.core;

import com.taylan.persistence.DAO.SchedulePool;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Taylan Kurt < taylankurt34
 * @gmail.com >
 */
public class SchedulePageController extends AnchorPane implements Initializable {
    
    ObservableList<SchedulePool> personData;
    
    @FXML 
    private Button cancel,backToMenu,search;
    @FXML 
    private RadioButton male,female,fatLoss,transform,bodyTraining,
            beginner,intermediate,advanced;
    @FXML
    private TextField   chest_ex_1,chest_ex_2,chest_ex_3,chest_ex_4,chest_ex_5,
                        back_ex_1,back_ex_2,back_ex_3,back_ex_4,back_ex_5,
                        shoulder_ex_1,shoulder_ex_2,shoulder_ex_3,shoulder_ex_4,shoulder_ex_5,
                        arms_ex_1,arms_ex_2,arms_ex_3,arms_ex_4,arms_ex_5,
                        abs_ex_1,abs_ex_2,abs_ex_3,abs_ex_4,abs_ex_5,
                        legs_ex_1,legs_ex_2,legs_ex_3,legs_ex_4,legs_ex_5,
                        
                        gender_textField,level_textField,perpose_textField;
    
    @FXML
    TableView<SchedulePool> schedules;
    @FXML
    TableColumn<SchedulePool, String> monday;
    @FXML
    TableColumn<SchedulePool, String> tuesday;
    @FXML
    TableColumn<SchedulePool, String> wednesday;
    @FXML
    TableColumn<SchedulePool, String> thursday;
    @FXML
    TableColumn<SchedulePool, String> friday;
    @FXML
    TableColumn<SchedulePool, String> saturday;
    @FXML
    TableColumn<SchedulePool, String> sunday;
    
    
    private MainApp application;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    @FXML
    public void backToMenu(ActionEvent action) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }

        application.gotoMenu();
    }
    
    
    
    
    
    
    
    
    
    
    
    public void getRecommendedExercises(ActionEvent action){}
}
