package Presentacio;
import Domini.Controladors.CtrlDomini;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import  java.util.*;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;



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
            pathVistes.put(s,"FONTS/Presentacio/"+s+".fxml");
        }
    }

    public static CtrlPresentacio getInstance() {
        if (cp == null) cp = new CtrlPresentacio();
        return cp;
    }

    //Inicialitza escena principal
    public void setPrimary(Stage primaryStage) throws Exception {
        stageActual = primaryStage;
        stageActual.setResizable(false);
        stageActual.setTitle("Gestor de fitxers");

    }

    public void canviaStage(String nomVista) throws IOException {
        try {
            URL url = new File(pathVistes.get(nomVista)).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stageActual.setScene(new Scene(root));
            stageActual.show();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            mostraError("No s'ha pogut canviar de pantalla, mira l'error en terminal");
        } catch (IOException e) {
            e.printStackTrace();
            mostraError("No s'ha pogut canviar de pantalla, mira l'error en terminal");
        }
    }

    //Mostra error per la pantalla
    public void mostraError(String mError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("ERROR: " + mError);
        alert.showAndWait();
    }

    public void inicialitzarVistes() {

    }

    public void altaDocument() {

    }
}
