package com.taylan.core;

import com.taylan.Model.User;
import com.taylan.persistence.util.HibernateUtil;
import com.taylan.security.Authenticator;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import org.hibernate.Query;
import org.hibernate.Session;

import com.taylan.persistence.*;

/**
 *
 * @author Taylan Kurt   <taylankurt34@gmail.com>
 *
 */
public class MainApp extends Application {

    private Stage stage;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {          //throws Exception
        try {
            stage = primaryStage;
            stage.setTitle("SportClub System");
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);

            gotoLogin();
            stage.show();

        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

       //Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
      //  Scene scene = new Scene(root);
        // scene.getStylesheets().add("/styles/Styles.css");
        /*stage.setTitle("Sport Club DesktopSystem");
         stage.setScene(scene);
         stage.show();*/
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void userLogout() {
        loggedUser = null;
        gotoLogin();
    }

    public boolean userLogging(String userId, String password) {
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoMenu();
            return true;
        } else {
            return false;
        }
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("/fxml/login.fxml");
            login.setApplication(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
         Session session = HibernateUtil.getSessionFactory().openSession();
   	 
         session.beginTransaction();
         List<Personaltrainers> ls= session.getNamedQuery("loadPersonalTrainers").list();
         for (Personaltrainers l : ls) {
         System.out.println(l.getName());
         }
         */
    }

    public void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("/fxml/profile.fxml");
            profile.setApplication(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoMenu() {
        try {
            MenuController menu = (MenuController) replaceSceneContent("/fxml/menu.fxml");
            menu.setApplication(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openTrainerPage() {
        try {
            PersonalTrainersController pt = (PersonalTrainersController) replaceSceneContent("/fxml/personalTrainersPage.fxml");
            pt.setApplication(this);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}