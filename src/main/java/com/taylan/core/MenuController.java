package com.taylan.core;

import com.taylan.persistence.DAO.UserInfo;
import com.taylan.persistence.util.HibernateUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *
 * Menu Controller
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
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
        }
        Session session = null;
        Transaction tx = null;
        UserInfo userInfo = application.getLoggedUser();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            session.delete(userInfo);

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
            application.userLogout();
        }

    }

    public void processSchdule(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.gotoSchedule();
    }

    public void processProfile(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.gotoProfile();
    }

    public void processTrainers(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.openTrainerPage();
    }

    public void processLogout(ActionEvent event) {
        if (application == null) {
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        application.userLogout();
    }

}
