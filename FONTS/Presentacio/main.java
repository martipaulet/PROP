package Presentacio;

import javafx.application.Application;
import javafx.stage.Stage;



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


}
