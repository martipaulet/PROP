package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class CtrlVistaLlistarSemblantsOutput {



    @FXML
    private Button AltaDocument;

    @FXML
    private Button BaixaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzarConsulta;

    @FXML
    private Button GestioExpressionsBooleanes;

    @FXML
    private ListView<String> Documents = new ListView<>();

    @FXML
    private ListView<String> Contingut = new ListView<>();

    @FXML
    private Button MostrarDocuments;

    @FXML
    private Button MostrarContingut;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private CtrlVistaLlistarSemblants CVLLS = CtrlVistaLlistarSemblants.getInstance();


    @FXML
    void pressAltaDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("AltaDocument");
    }

    @FXML
    void pressBaixaDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("BaixaDocument");
    }

    @FXML
    void pressModificaDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ModificaDocumentInicial");
    }

    @FXML
    void pressRealitzarConsulta(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("RealitzaConsulta");
    }

    @FXML
    void pressGestioExpressionsBooleanes(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }

    @FXML
    void pressMostrarDocuments(javafx.event.ActionEvent event) throws Exception {
        String a = CVLLS.getAutor();
        String t = CVLLS.getTitol();
        Integer k = CVLLS.getNombre();
        Integer m = CVLLS.getMode();
        Integer o = CVLLS.getOrdre();
        ArrayList<String> doc = ctrlPres.DocumentsSemblantsPres(a,t,k,m,o);
        ObservableList<String> aux = FXCollections.observableArrayList(doc);
        Documents.setItems(aux);
    }

    @FXML
    void pressMostrarContingut(javafx.event.ActionEvent event) throws IOException {

    }
}
