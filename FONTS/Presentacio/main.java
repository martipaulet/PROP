package Presentacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;


public class main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        CtrlPresentacio cp = CtrlPresentacio.getInstance();
        cp.setPrimary(primaryStage);
        cp.canviaStage("menuPrincipal");
    }
    @Override
    public void stop() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Guardar");
        alert.setContentText("Vols guardar els canvis?");
        Optional<ButtonType> res = alert.showAndWait();
        if(res.isPresent()) {
            if (res.get().equals(ButtonType.OK)) {
                CtrlPresentacio cp = CtrlPresentacio.getInstance();
                cp.guardar();
            }

        }
    }


}
