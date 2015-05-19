package com.taylan.core;

import com.taylan.persistence.DAO.RecommendedExercises;
import com.taylan.persistence.DAO.SchedulePool;
import com.taylan.persistence.util.HibernateUtil;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author Taylan Kurt  <taylankurt34@gmail.com>
 */
public class SchedulePageController extends AnchorPane implements Initializable {

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
                        
                        gender_textField,levell_textField,perpose_textField;
    
    private String search_gender,search_purpose,search_levell;
    private SchedulePool schedulePool ;
    
    
    List<RecommendedExercises> exercisesList;
    ObservableList<SchedulePool> schedules;
    ObservableList<RecommendedExercises> recomended_exercises;
    
    @FXML
    TableView<SchedulePool> scheduleTable;
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
         initializeColumns();
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
    
    public void filterForSearch(ActionEvent action){
        
            /*  gender */
        if(male.isSelected()){
            setSearch_gender("male");
            System.out.println("Male");
        }else if(female.isSelected()){
            setSearch_gender("female");
            System.out.println("Female");
        }
       
        
            /* Purpose */
        if(fatLoss.isSelected()){
            setSearch_purpose("fatLoss"); 
            System.out.println("FatLoss");
        }else if(transform.isSelected()){
            setSearch_purpose("transform");
        }else if(bodyTraining.isSelected()){
            setSearch_purpose("bodyTraining");
        }
       
        
             /* Levell*/
        if(beginner.isSelected()){
            setSearch_levell("beginner");
            System.out.println("Beginner");
        }else if(intermediate.isSelected()){
            setSearch_levell("intermediate");
        }else if(advanced.isSelected()){
            setSearch_levell("advanced");
        }
        
        
        
              
        /* After we get filter we gonna search according to filter */
        searchProcess();
        
        gender_textField.setText(getSearch_gender());
        perpose_textField.setText(getSearch_purpose());
        levell_textField.setText(getSearch_levell());
        
        
        /* Showing results on the table */
        scheduleTable.setItems(schedules);
        
    }
    
    public void initializeColumns(){
        
        monday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("moSchedule"));
        tuesday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("tuSchedule"));
        wednesday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("weSchedule"));
        thursday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("thSchedule"));
        friday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("fchedule"));
        saturday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("saSchedule"));
        sunday.setCellValueFactory(new PropertyValueFactory <SchedulePool, String> ("suSchedule"));
        
    }
    
    public void searchProcess(){
        Session session = null;
        Transaction tx = null;
        
        /* observableLists */
        schedules = FXCollections.observableArrayList();
        recomended_exercises = FXCollections.observableArrayList();
        
        /* SCHEDULE OBJECT */
        
        
        /* HIBERNATE SESSION CREATION*/
        int generalId =0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);
            
            /* TABLE VIEW PART START*/
            String hql="FROM SchedulePool s WHERE s.gender = :gender AND "
                + "s.perpose = :perpose AND level=:level";
            Query query =session.createQuery(hql);
            query.setParameter("gender",getSearch_gender());
            query.setParameter("perpose",getSearch_purpose());
            query.setParameter("level",getSearch_levell());
            
            List<SchedulePool> schedulesList = query.list();
            for (SchedulePool sc : schedulesList) {
                schedules.add(sc);
            }
            
            /* TABLE VIEW PART END */
            
             /* RECOMMENDED EXERCISES PART START */
           
            /* Take recommended Exercises whichs are belong to certain schedule */
           
            schedulePool = schedulesList.get(0);
            Set<RecommendedExercises> exercisesSList =schedulePool.getRecommendedExercises();
            
            for (Iterator iterator =exercisesSList.iterator();iterator.hasNext();) {
                
                for(int i = 0; i<=4;i++){
                RecommendedExercises re =(RecommendedExercises)iterator.next();
                
                    if(i==0){
                        chest_ex_1.setText(re.getChest());dragAndDrop(chest_ex_1,scheduleTable);
                        back_ex_1.setText(re.getBack());dragAndDrop(back_ex_1,scheduleTable);
                        shoulder_ex_1.setText(re.getShoulder());dragAndDrop(shoulder_ex_1,scheduleTable);
                        arms_ex_1.setText(re.getArms());dragAndDrop(arms_ex_1,scheduleTable);
                        abs_ex_1.setText(re.getAbs());dragAndDrop(abs_ex_1,scheduleTable);
                        legs_ex_1.setText(re.getLeg());dragAndDrop(legs_ex_1,scheduleTable);
                            
                    }else if(i==1){
                        chest_ex_2.setText(re.getChest());dragAndDrop(chest_ex_2,scheduleTable);
                        back_ex_2.setText(re.getBack());dragAndDrop(back_ex_2,scheduleTable);
                        shoulder_ex_2.setText(re.getShoulder());dragAndDrop(shoulder_ex_2,scheduleTable);
                        arms_ex_2.setText(re.getArms());dragAndDrop(arms_ex_2,scheduleTable);
                        abs_ex_2.setText(re.getAbs());dragAndDrop(abs_ex_2,scheduleTable);
                        legs_ex_2.setText(re.getLeg());dragAndDrop(legs_ex_2,scheduleTable);
                        
                            
                    }else if(i==2){
                        chest_ex_3.setText(re.getChest());dragAndDrop(chest_ex_3,scheduleTable);
                        back_ex_3.setText(re.getBack());dragAndDrop(back_ex_3,scheduleTable);
                        shoulder_ex_3.setText(re.getShoulder());dragAndDrop(shoulder_ex_3,scheduleTable);
                        arms_ex_3.setText(re.getArms());dragAndDrop(arms_ex_3,scheduleTable);
                        abs_ex_3.setText(re.getAbs());dragAndDrop(abs_ex_3,scheduleTable);
                        legs_ex_3.setText(re.getLeg());dragAndDrop(legs_ex_3,scheduleTable);
                            
                    }else if(i==3){    
                        chest_ex_4.setText(re.getChest());dragAndDrop(chest_ex_4,scheduleTable);
                        back_ex_4.setText(re.getBack());dragAndDrop(back_ex_4,scheduleTable);
                        shoulder_ex_4.setText(re.getShoulder());dragAndDrop(shoulder_ex_4,scheduleTable);
                        arms_ex_4.setText(re.getArms());dragAndDrop(arms_ex_4,scheduleTable);
                        abs_ex_4.setText(re.getAbs());dragAndDrop(abs_ex_4,scheduleTable);
                        legs_ex_4.setText(re.getLeg());dragAndDrop(legs_ex_4,scheduleTable);
                            
                    }else if(i==4){    
                        chest_ex_5.setText(re.getChest());dragAndDrop(chest_ex_5,scheduleTable);
                        back_ex_5.setText(re.getBack());dragAndDrop(back_ex_5,scheduleTable);
                        shoulder_ex_5.setText(re.getShoulder());dragAndDrop(shoulder_ex_5,scheduleTable);
                        arms_ex_5.setText(re.getArms());dragAndDrop(arms_ex_5,scheduleTable);
                        abs_ex_5.setText(re.getAbs());dragAndDrop(abs_ex_5,scheduleTable);
                        legs_ex_5.setText(re.getLeg());dragAndDrop(legs_ex_5,scheduleTable);
                            
                    }
                }
            } 
             
            /* RECOMMENDED EXERCISES PART END */
            
            tx.commit();
        } catch (HibernateException e) {
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
    
    /*                      DROP AND DROP SESSION                             */
   
    public void dragAndDrop(final TextField source,final TableView target){
        
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                db.setContent(content);

                event.consume();
            }
        });
        
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node 
                * and if it has a string data */
                if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });
        
        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                    
                }
                event.consume();
            }
        });
        
        target.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                

                event.consume();
            }
        });
    
        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                   
                   success = true;
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);
                
                event.consume();
            }
        });
        
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setText("");
                }   
                event.consume();
            }
        });
    
    }
    
    public void radioCheck(MouseEvent event){
        if(male.isSelected() && female.isSelected() ){
           female.setSelected(false);
           male.setSelected(false);
        }
        
        if(fatLoss.isSelected() && transform.isSelected()){
           fatLoss.setSelected(false);
           transform.setSelected(false);
        }else if(fatLoss.isSelected() && bodyTraining.isSelected()){
           fatLoss.setSelected(false);
           bodyTraining.setSelected(false);
        }else if(bodyTraining.isSelected() && transform.isSelected()){
           bodyTraining.setSelected(false);
           transform.setSelected(false);
        }
        
        if(beginner.isSelected() && intermediate.isSelected() ){
           beginner.setSelected(false);
           intermediate.setSelected(false);
        }else if(intermediate.isSelected() && advanced.isSelected() ){
           intermediate.setSelected(false);
           advanced.setSelected(false);
        }else if(advanced.isSelected() && beginner.isSelected() ){
           advanced.setSelected(false);
           beginner.setSelected(false);
        }
        
    }
    
    /*                      GETTING AND SETTING PART                            */
    
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
     * @return the levell_textField
     */
    public TextField getLevell_textField() {
        return levell_textField;
    }

    /**
     * @param perpose_textField the perpose_textField to set
     */
    public void setPerpose_textField(TextField perpose_textField) {
        this.perpose_textField = perpose_textField;
    }
    
    /**
     * @param levell_textField the levell_textField to set
     */
    public void setLevell_textField(TextField levell_textField) {
        this.levell_textField = levell_textField;
    }

    /**
     * @return the perpose_textField
     */
    public TextField getPerpose_textField() {
        return perpose_textField;
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

    /**
     * @return the search_gender
     */
    public String getSearch_gender() {
        return search_gender;
    }

    /**
     * @param search_gender the search_gender to set
     */
    public void setSearch_gender(String search_gender) {
        this.search_gender = search_gender;
    }

    /**
     * @return the search_purpose
     */
    public String getSearch_purpose() {
        return search_purpose;
    }

    /**
     * @param search_purpose the search_purpose to set
     */
    public void setSearch_purpose(String search_purpose) {
        this.search_purpose = search_purpose;
    }

    /**
     * @return the search_level
     */
    public String getSearch_levell() {
        return search_levell;
    }

    /**
     * @param search_levell the search_levell to set
     */
    public void setSearch_levell(String search_levell) {
        this.search_levell = search_levell;
    }

    /**
     * @return the schedulePool
     */
    public SchedulePool getSchedulePool() {
        return schedulePool;
    }

    /**
     * @param schedulePool the schedulePool to set
     */
    public void setSchedulePool(SchedulePool schedulePool) {
        this.schedulePool = schedulePool;
    }
}
