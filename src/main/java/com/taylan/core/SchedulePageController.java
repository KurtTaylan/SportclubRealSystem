package com.taylan.core;

import com.taylan.persistence.DAO.RecommendedExercises;
import com.taylan.persistence.DAO.SchedulePool;
import com.taylan.persistence.DAO.UserInfo;
import com.taylan.persistence.DAO.UserSchedule;
import com.taylan.persistence.util.HibernateUtil;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private RadioButton male, female, fatLoss, transform, bodyTraining,
            beginner, intermediate, advanced;
    @FXML
    private TextArea monday2, tuesday2, wednesday2, thursday2, friday2, saturday2, sunday2;
    @FXML
    private TextField chest_ex_1, chest_ex_2, chest_ex_3, chest_ex_4, chest_ex_5,
            back_ex_1, back_ex_2, back_ex_3, back_ex_4, back_ex_5,
            shoulder_ex_1, shoulder_ex_2, shoulder_ex_3, shoulder_ex_4, shoulder_ex_5,
            arms_ex_1, arms_ex_2, arms_ex_3, arms_ex_4, arms_ex_5,
            abs_ex_1, abs_ex_2, abs_ex_3, abs_ex_4, abs_ex_5,
            legs_ex_1, legs_ex_2, legs_ex_3, legs_ex_4, legs_ex_5,
            gender_textField, levell_textField, perpose_textField;

    private String search_gender, search_purpose, search_levell;
    private SchedulePool schedulePool;
    private UserSchedule userSchedule;
    private UserInfo userInfo;
    private boolean atOwnSchedule;

    List<RecommendedExercises> exercisesList;

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

    }

    /*                      DROP AND DROP SESSION                             */
    public void dragAndDropSource(final TextField source) {
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

    public void dragAndDropTarget(final TextArea target) {
        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node 
                 * and if it has a string data */
                if (event.getGestureSource() != target
                        && event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.ANY);
                }
                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target
                        && event.getDragboard().hasString()) {

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
                    target.setText(target.getText() + "\n" + db.getString());
                }
                /* let the source know whether the string was successfully 
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

    }
    /*                      OTHER ABILITIES                                    */

    public void radioCheck(MouseEvent event) {
        if (male.isSelected() && female.isSelected()) {
            female.setSelected(false);
            male.setSelected(false);
        }

        if (fatLoss.isSelected() && transform.isSelected()) {
            fatLoss.setSelected(false);
            transform.setSelected(false);
        } else if (fatLoss.isSelected() && bodyTraining.isSelected()) {
            fatLoss.setSelected(false);
            bodyTraining.setSelected(false);
        } else if (bodyTraining.isSelected() && transform.isSelected()) {
            bodyTraining.setSelected(false);
            transform.setSelected(false);
        }

        if (beginner.isSelected() && intermediate.isSelected()) {
            beginner.setSelected(false);
            intermediate.setSelected(false);
        } else if (intermediate.isSelected() && advanced.isSelected()) {
            intermediate.setSelected(false);
            advanced.setSelected(false);
        } else if (advanced.isSelected() && beginner.isSelected()) {
            advanced.setSelected(false);
            beginner.setSelected(false);
        }

    }

    @FXML
    public void saveAsSchedule(ActionEvent action) {
        UserSchedule saveOrUpdateSchedule = takeSchedule();

        setAtOwnSchedule(true);
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            if (saveOrUpdateSchedule == null) {
                saveOrUpdateSchedule = new UserSchedule();
                saveOrUpdateSchedule.setMo_schedule(monday2.getText());
                saveOrUpdateSchedule.setTu_schedule(tuesday2.getText());
                saveOrUpdateSchedule.setWe_schedule(wednesday2.getText());
                saveOrUpdateSchedule.setTh_schedule(thursday2.getText());
                saveOrUpdateSchedule.setFr_schedule(friday2.getText());
                saveOrUpdateSchedule.setSa_schedule(saturday2.getText());
                saveOrUpdateSchedule.setSu_schedule(sunday2.getText());

                saveOrUpdateSchedule.setUserInfo(getApplication().getLoggedUser());

                session.save(saveOrUpdateSchedule);
            } else {

                saveOrUpdateSchedule.setMo_schedule(monday2.getText());
                saveOrUpdateSchedule.setTu_schedule(tuesday2.getText());
                saveOrUpdateSchedule.setWe_schedule(wednesday2.getText());
                saveOrUpdateSchedule.setTh_schedule(thursday2.getText());
                saveOrUpdateSchedule.setFr_schedule(friday2.getText());
                saveOrUpdateSchedule.setSa_schedule(saturday2.getText());
                saveOrUpdateSchedule.setSu_schedule(sunday2.getText());

                session.update(saveOrUpdateSchedule);
            }

            tx.commit();

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @FXML
    public void backToMenu(ActionEvent action) {
        if (getApplication() == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        getApplication().gotoMenu();
    }

    @FXML
    public void filterForSearch(ActionEvent action) {
        setAtOwnSchedule(false);
        /*  gender */
        if (male.isSelected()) {
            setSearch_gender("male");
        } else if (female.isSelected()) {
            setSearch_gender("female");
        }
        /* Purpose */
        if (fatLoss.isSelected()) {
            setSearch_purpose("fatLoss");
        } else if (transform.isSelected()) {
            setSearch_purpose("transform");
        } else if (bodyTraining.isSelected()) {
            setSearch_purpose("bodyTraining");
        }
        /* Levell*/
        if (beginner.isSelected()) {
            setSearch_levell("beginner");
        } else if (intermediate.isSelected()) {
            setSearch_levell("intermediate");
        } else if (advanced.isSelected()) {
            setSearch_levell("advanced");
        }

        /* After we get filter we gonna search according to filter */
        searchProcess();

        gender_textField.setText(getSearch_gender());
        perpose_textField.setText(getSearch_purpose());
        levell_textField.setText(getSearch_levell());

    }

    public void searchProcess() {
        Session session = null;
        Transaction tx = null;

        /* HIBERNATE SESSION CREATION*/
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            /* TABLE VIEW PART START*/
            /* MAKING SEARCH QUERY FOR SPECIFIC SCHEDULE FROM SCHEDULE POOL */
            String hql = "FROM SchedulePool s WHERE s.gender = :gender AND "
                    + "s.perpose = :perpose AND level=:level";
            Query query = session.createQuery(hql);
            query.setParameter("gender", getSearch_gender());
            query.setParameter("perpose", getSearch_purpose());
            query.setParameter("level", getSearch_levell());

            setSchedulePool((SchedulePool) query.uniqueResult());

            monday2.setText(getSchedulePool().getMoSchedule());
            tuesday2.setText(getSchedulePool().getTuSchedule());
            wednesday2.setText(getSchedulePool().getWeSchedule());
            thursday2.setText(getSchedulePool().getThSchedule());
            friday2.setText(getSchedulePool().getFchedule());
            saturday2.setText(getSchedulePool().getSaSchedule());
            sunday2.setText(getSchedulePool().getSuSchedule());

            /* TABLE VIEW PART END */
            /* RECOMMENDED EXERCISES PART START */
            /* Take recommended Exercises whichs are belong to certain schedule */
            Set<RecommendedExercises> exercisesSList = getSchedulePool().getRecommendedExercises();

            for (Iterator iterator = exercisesSList.iterator(); iterator.hasNext();) {

                for (int i = 0; i <= 4; i++) {

                    RecommendedExercises re = (RecommendedExercises) iterator.next();

                    /* SET recommended exercise and DEFINE THEM AS A SOURCE GUSTURES*/
                    if (i == 0) {
                        chest_ex_1.setText(re.getChest());
                        dragAndDropSource(chest_ex_1);
                        back_ex_1.setText(re.getBack());
                        dragAndDropSource(back_ex_1);
                        shoulder_ex_1.setText(re.getShoulder());
                        dragAndDropSource(shoulder_ex_1);
                        arms_ex_1.setText(re.getArms());
                        dragAndDropSource(arms_ex_1);
                        abs_ex_1.setText(re.getAbs());
                        dragAndDropSource(abs_ex_1);
                        legs_ex_1.setText(re.getLeg());
                        dragAndDropSource(legs_ex_1);

                    } else if (i == 1) {
                        chest_ex_2.setText(re.getChest());
                        dragAndDropSource(chest_ex_2);
                        back_ex_2.setText(re.getBack());
                        dragAndDropSource(back_ex_2);
                        shoulder_ex_2.setText(re.getShoulder());
                        dragAndDropSource(shoulder_ex_2);
                        arms_ex_2.setText(re.getArms());
                        dragAndDropSource(arms_ex_2);
                        abs_ex_2.setText(re.getAbs());
                        dragAndDropSource(abs_ex_2);
                        legs_ex_2.setText(re.getLeg());
                        dragAndDropSource(legs_ex_2);

                    } else if (i == 2) {
                        chest_ex_3.setText(re.getChest());
                        dragAndDropSource(chest_ex_3);
                        back_ex_3.setText(re.getBack());
                        dragAndDropSource(back_ex_3);
                        shoulder_ex_3.setText(re.getShoulder());
                        dragAndDropSource(shoulder_ex_3);
                        arms_ex_3.setText(re.getArms());
                        dragAndDropSource(arms_ex_3);
                        abs_ex_3.setText(re.getAbs());
                        dragAndDropSource(abs_ex_3);
                        legs_ex_3.setText(re.getLeg());
                        dragAndDropSource(legs_ex_3);

                    } else if (i == 3) {
                        chest_ex_4.setText(re.getChest());
                        dragAndDropSource(chest_ex_4);
                        back_ex_4.setText(re.getBack());
                        dragAndDropSource(back_ex_4);
                        shoulder_ex_4.setText(re.getShoulder());
                        dragAndDropSource(shoulder_ex_4);
                        arms_ex_4.setText(re.getArms());
                        dragAndDropSource(arms_ex_4);
                        abs_ex_4.setText(re.getAbs());
                        dragAndDropSource(abs_ex_4);
                        legs_ex_4.setText(re.getLeg());
                        dragAndDropSource(legs_ex_4);

                    } else if (i == 4) {
                        chest_ex_5.setText(re.getChest());
                        dragAndDropSource(chest_ex_5);
                        back_ex_5.setText(re.getBack());
                        dragAndDropSource(back_ex_5);
                        shoulder_ex_5.setText(re.getShoulder());
                        dragAndDropSource(shoulder_ex_5);
                        arms_ex_5.setText(re.getArms());
                        dragAndDropSource(arms_ex_5);
                        abs_ex_5.setText(re.getAbs());
                        dragAndDropSource(abs_ex_5);
                        legs_ex_5.setText(re.getLeg());
                        dragAndDropSource(legs_ex_5);
                    }
                }
                /* define target nodes */
                dragAndDropTarget(monday2);
                dragAndDropTarget(tuesday2);
                dragAndDropTarget(wednesday2);
                dragAndDropTarget(thursday2);
                dragAndDropTarget(friday2);
                dragAndDropTarget(saturday2);
                dragAndDropTarget(sunday2);
            }
            /* RECOMMENDED EXERCISES PART END */

            tx.commit();
        } catch (HibernateException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @FXML
    public void deleteOwnSchedule(ActionEvent action) {
        UserSchedule dltschedule = takeSchedule();
        if (dltschedule != null) {
            setAtOwnSchedule(false);
            Session session = null;
            Transaction tx = null;

            try {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                tx.setTimeout(5);

                session.delete(dltschedule);

                cleanPage();

                tx.commit();

            } catch (RuntimeException e) {
                try {
                    tx.rollback();
                } catch (RuntimeException rbe) {
                    rbe.printStackTrace();
                }
                throw e;
            } finally {
                if (session != null) {
                    session.close();

                }
            }
        }
    }

    public void cleanPage() {

        monday2.setText("");
        tuesday2.setText("");
        wednesday2.setText("");
        thursday2.setText("");
        friday2.setText("");
        saturday2.setText("");
        sunday2.setText("");
        chest_ex_1.setText("");
        back_ex_1.setText("");
        shoulder_ex_1.setText("");
        arms_ex_1.setText("");
        abs_ex_1.setText("");
        legs_ex_1.setText("");
        chest_ex_2.setText("");
        back_ex_2.setText("");
        shoulder_ex_2.setText("");
        arms_ex_2.setText("");
        abs_ex_2.setText("");
        legs_ex_2.setText("");
        chest_ex_3.setText("");
        back_ex_3.setText("");
        shoulder_ex_3.setText("");
        arms_ex_3.setText("");
        abs_ex_3.setText("");
        legs_ex_3.setText("");
        chest_ex_4.setText("");
        back_ex_4.setText("");
        shoulder_ex_4.setText("");
        arms_ex_4.setText("");
        abs_ex_4.setText("");
        legs_ex_4.setText("");
        chest_ex_5.setText("");
        back_ex_5.setText("");
        shoulder_ex_5.setText("");
        arms_ex_5.setText("");
        abs_ex_5.setText("");
        legs_ex_5.setText("");
        gender_textField.setText("");
        levell_textField.setText("");
        perpose_textField.setText("");

        setAtOwnSchedule(false);

    }

    @FXML
    public void showOwnSchedule(ActionEvent action) {
        cleanPage();
        setAtOwnSchedule(true);
        UserSchedule nowSchedule = takeSchedule();

        if (nowSchedule != null && isAtOwnSchedule()) {
            monday2.setText(nowSchedule.getMo_schedule());
            tuesday2.setText(nowSchedule.getTu_schedule());
            wednesday2.setText(nowSchedule.getWe_schedule());
            thursday2.setText(nowSchedule.getTh_schedule());
            friday2.setText(nowSchedule.getFr_schedule());
            saturday2.setText(nowSchedule.getSa_schedule());
            sunday2.setText(nowSchedule.getSu_schedule());
        }
    }

    public UserSchedule takeSchedule() {
        Session session = null;
        Transaction tx = null;
        UserSchedule newSchedule = new UserSchedule();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            String hql = "FROM UserSchedule us WHERE us.userInfo = :userInfo ";
            Query query = session.createQuery(hql);
            query.setParameter("userInfo", getApplication().getLoggedUser());
            newSchedule = (UserSchedule) query.uniqueResult();
            if (newSchedule == null) {
                newSchedule = null;
            }

            tx.commit();

        } catch (RuntimeException e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                rbe.printStackTrace();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();

            }

        }
        return newSchedule;

    }

    /*                      GETTERS AND SETTERS                                */
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
     * @return the search_levell
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
        UserSchedule welcomeSchedule = takeSchedule();
        if (welcomeSchedule != null) {
            setAtOwnSchedule(true);

            monday2.setText(welcomeSchedule.getMo_schedule());
            tuesday2.setText(welcomeSchedule.getTu_schedule());
            wednesday2.setText(welcomeSchedule.getWe_schedule());
            thursday2.setText(welcomeSchedule.getTh_schedule());
            friday2.setText(welcomeSchedule.getFr_schedule());
            saturday2.setText(welcomeSchedule.getSa_schedule());
            sunday2.setText(welcomeSchedule.getSu_schedule());
        } else {
            setAtOwnSchedule(false);
        }
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

    /**
     * @return the userSchedule
     */
    public UserSchedule getUserSchedule() {
        return userSchedule;
    }

    /**
     * @param userSchedule the userSchedule to set
     */
    public void setUserSchedule(UserSchedule userSchedule) {
        this.userSchedule = userSchedule;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * @return the atOwnSchedule
     */
    public boolean isAtOwnSchedule() {
        return atOwnSchedule;
    }

    /**
     * @param atOwnSchedule the atOwnSchedule to set
     */
    public void setAtOwnSchedule(boolean atOwnSchedule) {
        this.atOwnSchedule = atOwnSchedule;
    }

}
