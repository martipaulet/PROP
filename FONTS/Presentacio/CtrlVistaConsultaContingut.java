package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlVistaConsultaContingut {

    @FXML
    private Button CarregarGuardarDocument;

    @FXML
    private Button AltaDocument;

    @FXML
    private Button BaixaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzaConsulta;

    @FXML
    private Button LlistarDocuments;

    @FXML
    private Button GestioExpressionsBooleanes;

    @FXML
    private Button MostrarAutors;

    @FXML
    private Button MostrarTitolsAutor;

    @FXML
    private Button ContingutDoc;

    @FXML
    private ListView<String> Autors = new ListView<>();

    @FXML
    private ListView<String> Titols = new ListView<>();

    @FXML
    private ListView<String> Contingut = new ListView<>();

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    public CtrlVistaConsultaContingut() throws Exception {
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
    void pressRealitzaConsulta(javafx.event.ActionEvent event) throws IOException {
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
    void pressContingutDoc(javafx.event.ActionEvent event) throws Exception {
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
            String co = ctrlPres.obteContingutPres(Autors.getSelectionModel().getSelectedItem(), Titols.getSelectionModel().getSelectedItem());
            ObservableList<String> c = FXCollections.observableArrayList();
            c.add(co);
            Contingut.setItems(c);
        }
    }


}