package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CtrlVistaBaixaDocument {

    @FXML
    private Button MostrarAutors;

    @FXML
    private Button MostrarTitolsAutor;

    @FXML
    private Button BaixaDoc;

    @FXML
    private ListView<String> Autors = new ListView<>();

    @FXML
    private ListView<String> Titols = new ListView<>();

    @FXML
    private Button CarregarGuardarDocument;

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

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    public CtrlVistaBaixaDocument() throws Exception {
    }

    @FXML
    void pressMostrarAutors(javafx.event.ActionEvent event) throws Exception {
        ArrayList<String> a = ctrlPres.getAutorsPres();
        if (a.size() == 0) {
            ctrlPres.mostraError("No hi ha cap Autor guardat al sistema");
        }
        else {
            ObservableList<String> aux = FXCollections.observableArrayList(a);
            Autors.setItems(aux);
            Autors.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
    }

    @FXML
    void pressMostrarTitolsAutor(javafx.event.ActionEvent event) throws Exception {
        if (Autors.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar un Autor de la llista");
        }
        else {
            String autor = Autors.getSelectionModel().getSelectedItem();
            List<String> t = ctrlPres.titolsAutorPres(autor);
            if (t.size() == 0) {
                ctrlPres.mostraError("No hi ha cap Titol d'aquest Autor guardat al sistema");
            }
            else {
                ObservableList<String> aux = FXCollections.observableList(t);
                Titols.setItems(aux);
                Titols.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }
        }
    }

    @FXML
    void pressBaixaDoc(javafx.event.ActionEvent event) throws Exception {
        if(Autors.getSelectionModel().isEmpty()){
            ctrlPres.mostraError("Selecciona Autor i titol");
        }
        else if (Titols.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar un Titol de l'Autor seleccionat");
        }
        else if (!ctrlPres.existeixDocument(Autors.getSelectionModel().getSelectedItem(), Titols.getSelectionModel().getSelectedItem())){
            ctrlPres.mostraError("L'Autor i el Titol seleccionat no corresponen");
        }
        else {
            ctrlPres.baiaxaDocumentPres(Autors.getSelectionModel().getSelectedItem(), Titols.getSelectionModel().getSelectedItem());
            ctrlPres.canviaStage("DocumentBorrat");
        }
    }

    @FXML
    void pressCarregarGuardarDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("CarregarGuardarDocument");
    }

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

}
