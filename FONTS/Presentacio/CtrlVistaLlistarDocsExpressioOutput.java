package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtrlVistaLlistarDocsExpressioOutput {

    @FXML
    private Button AltaDocument;

    @FXML
    private Button BaixaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzarConsulta;

    @FXML
    private Button LlistarDocuments;

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
    void pressLlistarDocuments(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarDocuments");
    }

    @FXML
    void pressGestioExpressionsBooleanes(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }

    @FXML
    void pressMostrarDocuments(javafx.event.ActionEvent event) throws Exception {
        String a = CVLLS.getAutor();
        String t = CVLLS.getTitol();
        Integer k = Integer.parseInt(CVLLS.getNombre());
        Integer m = CVLLS.getMode();
        Integer o = CVLLS.getOrdre();
        ArrayList<String> doc = ctrlPres.DocumentsSemblantsPres(a,t,k,m,o);
        ObservableList<String> aux = FXCollections.observableArrayList(doc);
        Documents.setItems(aux);
        Documents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    void pressMostrarContingut(javafx.event.ActionEvent event) throws Exception {
        if (Documents.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar un document, pitjant l'autor i el titol que surten per pantalla");
        }
        else {
            String s = Documents.getSelectionModel().getSelectedItem();
            String[] autor_titol = s.split("\n");
            //Element 0 = autor
            //Element 1 = titol
            ArrayList<String> al = new ArrayList<>(List.of(autor_titol));
            String con = ctrlPres.obteContingutPres( al.get(0) , al.get(1) );
            ObservableList<String> a = FXCollections.observableArrayList();
            a.add(con);
            Contingut.setItems(a);
        }
    }
}

