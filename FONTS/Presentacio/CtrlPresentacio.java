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
    private HashMap<String,String> pathVistes;
    private static CtrlPresentacio cp;

    private Stage stageActual;

    public CtrlPresentacio() {
        pathVistes = new HashMap<>();
        iniVistes();
        CtrlDomini cd = CtrlDomini.getInstance();
    }

    private void iniVistes(){
        String[] nomVistes = {"menuPrincipal","AltaDocument","BaixaDocument","ConsultaAutorsXPrefix",
                "ConsultaContingut","ConsultaTítolsXAutor","LlistarDocuments","ModificaDocumentInicial",
                "RealitzaConsulta"};
        for (String s : nomVistes){
            pathVistes.put(s,"Presentacio/"+s+".fxml");
        }
    }

    public static CtrlPresentacio getInstance() {
        if (cp == null) cp = new CtrlPresentacio();
        return cp;
    }

    public void setPrimary(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menuPrincipal.fxml"));
        stageActual = primaryStage;
        stageActual.setScene(new Scene(root));
        stageActual.show();
    }

    public void inicialitzarVistes() {

    }

    public void altaDocument() {

    }
}
