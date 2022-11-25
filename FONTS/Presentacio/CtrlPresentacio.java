package Presentacio;
import Domini.Controladors.CtrlDomini;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import  java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CtrlPresentacio {
    private CtrlDomini cd;
    private static CtrlPresentacio cp;

    public CtrlPresentacio() {
        cd = new CtrlDomini();
    }

    public static CtrlPresentacio getInstance() {
        if (cp == null) cp = new CtrlPresentacio();
        return cp;
    }

    public void setPrimary(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        Scene s = new Scene(root);
        primaryStage.setScene(s);
        primaryStage.show();
    }

    public void inicialitzarVistes() {

    }

    public void altaDocument() {

    }
}
