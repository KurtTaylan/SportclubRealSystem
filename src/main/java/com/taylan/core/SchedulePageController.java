package com.taylan.core;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class SchedulePageController extends AnchorPane implements Initializable {
<<<<<<< HEAD
    
    ObservableList<SchedulePool> scheduleTable;
    
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
=======

>>>>>>> parent of f1994c0... Schedule Part getting better.
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
<<<<<<< HEAD
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
    
    
    
    
    
    
    
    
    /**
     * @return the gender_textField
     */
    public TextField getGender_textField() {
        return gender_textField;
    }

    /**
     * @param gender_textField the gender_textField to set
     */
    public void setGender_textField(TextField gender_textField) {
        this.gender_textField = gender_textField;
    }

    /**
     * @return the level_textField
     */
    public TextField getLevel_textField() {
        return level_textField;
    }

    /**
     * @param perpose_textField the perpose_textField to set
     */
    public void setPerpose_textField(TextField perpose_textField) {
        this.perpose_textField = perpose_textField;
    }
    
    /**
     * @param level_textField the level_textField to set
     */
    public void setLevel_textField(TextField level_textField) {
        this.level_textField = level_textField;
    }

    /**
     * @return the perpose_textField
     */
    public TextField getPerpose_textField() {
        return perpose_textField;
    }
    /**
     * @return the chest_ex_1
     */
    public TextField getChest_ex_1() {
        return chest_ex_1;
    }

    /**
     * @param chest_ex_1 the chest_ex_1 to set
     */
    public void setChest_ex_1(TextField chest_ex_1) {
        this.chest_ex_1 = chest_ex_1;
    }

    /**
     * @return the chest_ex_2
     */
    public TextField getChest_ex_2() {
        return chest_ex_2;
    }

    /**
     * @param chest_ex_2 the chest_ex_2 to set
     */
    public void setChest_ex_2(TextField chest_ex_2) {
        this.chest_ex_2 = chest_ex_2;
    }

    /**
     * @return the chest_ex_3
     */
    public TextField getChest_ex_3() {
        return chest_ex_3;
    }

    /**
     * @param chest_ex_3 the chest_ex_3 to set
     */
    public void setChest_ex_3(TextField chest_ex_3) {
        this.chest_ex_3 = chest_ex_3;
    }

    /**
     * @return the chest_ex_4
     */
    public TextField getChest_ex_4() {
        return chest_ex_4;
    }

    /**
     * @param chest_ex_4 the chest_ex_4 to set
     */
    public void setChest_ex_4(TextField chest_ex_4) {
        this.chest_ex_4 = chest_ex_4;
    }

    /**
     * @return the chest_ex_5
     */
    public TextField getChest_ex_5() {
        return chest_ex_5;
    }

    /**
     * @param chest_ex_5 the chest_ex_5 to set
     */
    public void setChest_ex_5(TextField chest_ex_5) {
        this.chest_ex_5 = chest_ex_5;
    }

    /**
     * @return the back_ex_1
     */
    public TextField getBack_ex_1() {
        return back_ex_1;
    }

    /**
     * @param back_ex_1 the back_ex_1 to set
     */
    public void setBack_ex_1(TextField back_ex_1) {
        this.back_ex_1 = back_ex_1;
    }

    /**
     * @return the back_ex_2
     */
    public TextField getBack_ex_2() {
        return back_ex_2;
    }

    /**
     * @param back_ex_2 the back_ex_2 to set
     */
    public void setBack_ex_2(TextField back_ex_2) {
        this.back_ex_2 = back_ex_2;
    }

    /**
     * @return the back_ex_3
     */
    public TextField getBack_ex_3() {
        return back_ex_3;
    }

    /**
     * @param back_ex_3 the back_ex_3 to set
     */
    public void setBack_ex_3(TextField back_ex_3) {
        this.back_ex_3 = back_ex_3;
    }

    /**
     * @return the back_ex_4
     */
    public TextField getBack_ex_4() {
        return back_ex_4;
    }

    /**
     * @param back_ex_4 the back_ex_4 to set
     */
    public void setBack_ex_4(TextField back_ex_4) {
        this.back_ex_4 = back_ex_4;
    }

    /**
     * @return the back_ex_5
     */
    public TextField getBack_ex_5() {
        return back_ex_5;
    }

    /**
     * @param back_ex_5 the back_ex_5 to set
     */
    public void setBack_ex_5(TextField back_ex_5) {
        this.back_ex_5 = back_ex_5;
    }

    /**
     * @return the shoulder_ex_1
     */
    public TextField getShoulder_ex_1() {
        return shoulder_ex_1;
    }

    /**
     * @param shoulder_ex_1 the shoulder_ex_1 to set
     */
    public void setShoulder_ex_1(TextField shoulder_ex_1) {
        this.shoulder_ex_1 = shoulder_ex_1;
    }

    /**
     * @return the shoulder_ex_2
     */
    public TextField getShoulder_ex_2() {
        return shoulder_ex_2;
    }

    /**
     * @param shoulder_ex_2 the shoulder_ex_2 to set
     */
    public void setShoulder_ex_2(TextField shoulder_ex_2) {
        this.shoulder_ex_2 = shoulder_ex_2;
    }

    /**
     * @return the shoulder_ex_3
     */
    public TextField getShoulder_ex_3() {
        return shoulder_ex_3;
    }

    /**
     * @param shoulder_ex_3 the shoulder_ex_3 to set
     */
    public void setShoulder_ex_3(TextField shoulder_ex_3) {
        this.shoulder_ex_3 = shoulder_ex_3;
    }

    /**
     * @return the shoulder_ex_4
     */
    public TextField getShoulder_ex_4() {
        return shoulder_ex_4;
    }

    /**
     * @param shoulder_ex_4 the shoulder_ex_4 to set
     */
    public void setShoulder_ex_4(TextField shoulder_ex_4) {
        this.shoulder_ex_4 = shoulder_ex_4;
    }

    /**
     * @return the shoulder_ex_5
     */
    public TextField getShoulder_ex_5() {
        return shoulder_ex_5;
    }

    /**
     * @param shoulder_ex_5 the shoulder_ex_5 to set
     */
    public void setShoulder_ex_5(TextField shoulder_ex_5) {
        this.shoulder_ex_5 = shoulder_ex_5;
    }

    /**
     * @return the arms_ex_1
     */
    public TextField getArms_ex_1() {
        return arms_ex_1;
    }

    /**
     * @param arms_ex_1 the arms_ex_1 to set
     */
    public void setArms_ex_1(TextField arms_ex_1) {
        this.arms_ex_1 = arms_ex_1;
    }

    /**
     * @return the arms_ex_2
     */
    public TextField getArms_ex_2() {
        return arms_ex_2;
    }

    /**
     * @param arms_ex_2 the arms_ex_2 to set
     */
    public void setArms_ex_2(TextField arms_ex_2) {
        this.arms_ex_2 = arms_ex_2;
    }

    /**
     * @return the arms_ex_3
     */
    public TextField getArms_ex_3() {
        return arms_ex_3;
    }

    /**
     * @param arms_ex_3 the arms_ex_3 to set
     */
    public void setArms_ex_3(TextField arms_ex_3) {
        this.arms_ex_3 = arms_ex_3;
    }

    /**
     * @return the arms_ex_4
     */
    public TextField getArms_ex_4() {
        return arms_ex_4;
    }

    /**
     * @param arms_ex_4 the arms_ex_4 to set
     */
    public void setArms_ex_4(TextField arms_ex_4) {
        this.arms_ex_4 = arms_ex_4;
    }

    /**
     * @return the arms_ex_5
     */
    public TextField getArms_ex_5() {
        return arms_ex_5;
    }

    /**
     * @param arms_ex_5 the arms_ex_5 to set
     */
    public void setArms_ex_5(TextField arms_ex_5) {
        this.arms_ex_5 = arms_ex_5;
    }

    /**
     * @return the abs_ex_1
     */
    public TextField getAbs_ex_1() {
        return abs_ex_1;
    }

    /**
     * @param abs_ex_1 the abs_ex_1 to set
     */
    public void setAbs_ex_1(TextField abs_ex_1) {
        this.abs_ex_1 = abs_ex_1;
    }

    /**
     * @return the abs_ex_2
     */
    public TextField getAbs_ex_2() {
        return abs_ex_2;
    }

    /**
     * @param abs_ex_2 the abs_ex_2 to set
     */
    public void setAbs_ex_2(TextField abs_ex_2) {
        this.abs_ex_2 = abs_ex_2;
    }

    /**
     * @return the abs_ex_3
     */
    public TextField getAbs_ex_3() {
        return abs_ex_3;
    }

    /**
     * @param abs_ex_3 the abs_ex_3 to set
     */
    public void setAbs_ex_3(TextField abs_ex_3) {
        this.abs_ex_3 = abs_ex_3;
    }

    /**
     * @return the abs_ex_4
     */
    public TextField getAbs_ex_4() {
        return abs_ex_4;
    }

    /**
     * @param abs_ex_4 the abs_ex_4 to set
     */
    public void setAbs_ex_4(TextField abs_ex_4) {
        this.abs_ex_4 = abs_ex_4;
    }

    /**
     * @return the abs_ex_5
     */
    public TextField getAbs_ex_5() {
        return abs_ex_5;
    }

    /**
     * @param abs_ex_5 the abs_ex_5 to set
     */
    public void setAbs_ex_5(TextField abs_ex_5) {
        this.abs_ex_5 = abs_ex_5;
    }

    /**
     * @return the legs_ex_1
     */
    public TextField getLegs_ex_1() {
        return legs_ex_1;
    }

    /**
     * @param legs_ex_1 the legs_ex_1 to set
     */
    public void setLegs_ex_1(TextField legs_ex_1) {
        this.legs_ex_1 = legs_ex_1;
    }

    /**
     * @return the legs_ex_2
     */
    public TextField getLegs_ex_2() {
        return legs_ex_2;
    }

    /**
     * @param legs_ex_2 the legs_ex_2 to set
     */
    public void setLegs_ex_2(TextField legs_ex_2) {
        this.legs_ex_2 = legs_ex_2;
    }

    /**
     * @return the legs_ex_3
     */
    public TextField getLegs_ex_3() {
        return legs_ex_3;
    }

    /**
     * @param legs_ex_3 the legs_ex_3 to set
     */
    public void setLegs_ex_3(TextField legs_ex_3) {
        this.legs_ex_3 = legs_ex_3;
    }

    /**
     * @return the legs_ex_4
     */
    public TextField getLegs_ex_4() {
        return legs_ex_4;
    }

    /**
     * @param legs_ex_4 the legs_ex_4 to set
     */
    public void setLegs_ex_4(TextField legs_ex_4) {
        this.legs_ex_4 = legs_ex_4;
    }

    /**
     * @return the legs_ex_5
     */
    public TextField getLegs_ex_5() {
        return legs_ex_5;
    }

    /**
     * @param legs_ex_5 the legs_ex_5 to set
     */
    public void setLegs_ex_5(TextField legs_ex_5) {
        this.legs_ex_5 = legs_ex_5;
    }

    /**
     * @return the male
     */
    public RadioButton getMale() {
        return male;
    }

    /**
     * @param male the male to set
     */
    public void setMale(RadioButton male) {
        this.male = male;
    }

    /**
     * @return the female
     */
    public RadioButton getFemale() {
        return female;
    }

    /**
     * @param female the female to set
     */
    public void setFemale(RadioButton female) {
        this.female = female;
    }

    /**
     * @return the fatLoss
     */
    public RadioButton getFatLoss() {
        return fatLoss;
    }

    /**
     * @param fatLoss the fatLoss to set
     */
    public void setFatLoss(RadioButton fatLoss) {
        this.fatLoss = fatLoss;
    }

    /**
     * @return the transform
     */
    public RadioButton getTransform() {
        return transform;
    }

    /**
     * @param transform the transform to set
     */
    public void setTransform(RadioButton transform) {
        this.transform = transform;
    }

    /**
     * @return the bodyTraining
     */
    public RadioButton getBodyTraining() {
        return bodyTraining;
    }

    /**
     * @param bodyTraining the bodyTraining to set
     */
    public void setBodyTraining(RadioButton bodyTraining) {
        this.bodyTraining = bodyTraining;
    }

    /**
     * @return the beginner
     */
    public RadioButton getBeginner() {
        return beginner;
    }

    /**
     * @param beginner the beginner to set
     */
    public void setBeginner(RadioButton beginner) {
        this.beginner = beginner;
    }

    /**
     * @return the intermediate
     */
    public RadioButton getIntermediate() {
        return intermediate;
    }

    /**
     * @param intermediate the intermediate to set
     */
    public void setIntermediate(RadioButton intermediate) {
        this.intermediate = intermediate;
    }

    /**
     * @return the advanced
     */
    public RadioButton getAdvanced() {
        return advanced;
    }

    /**
     * @param advanced the advanced to set
     */
    public void setAdvanced(RadioButton advanced) {
        this.advanced = advanced;
    }
    
    
    
=======
    
>>>>>>> parent of f1994c0... Schedule Part getting better.
}
