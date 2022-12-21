package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class CtrlVistaLlistarDocsExpressio {

    private static CtrlVistaLlistarDocsExpressio instance;

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

    @FXML
    private Button Continue;

    @FXML
    private Button MostrarQuerys;

    @FXML
    private ListView<String> Querys = new ListView<>();

    @FXML
    private CheckBox alfabetica;

    @FXML
    private CheckBox creacio;

    @FXML
    private CheckBox modificacio;


    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private static String q;

    private static Integer ordre;

    public CtrlVistaLlistarDocsExpressio() throws Exception {
    }

    public static CtrlVistaLlistarDocsExpressio getInstance() throws Exception {
        if (instance == null) instance = new CtrlVistaLlistarDocsExpressio();
        return instance;
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

    @FXML
    void pressalfabetica() {
        if (alfabetica.isSelected()) {
            creacio.setSelected(false);
            modificacio.setSelected(false);
            ordre = 0;
        }
    }

    @FXML
    void presscreacio() {
        if (creacio.isSelected()) {
            alfabetica.setSelected(false);
            modificacio.setSelected(false);
            ordre = 1;
        }
    }

    @FXML
    void pressmodificacio() {
        if (modificacio.isSelected()) {
            alfabetica.setSelected(false);
            creacio.setSelected(false);
            ordre = 2;
        }
    }

    @FXML
    void pressMostrarQuerys(javafx.event.ActionEvent event) throws Exception {
        List<String> q = ctrlPres.getQuerysPres();
        if (q.size() == 0) {
            ctrlPres.mostraError("No hi ha cap Query guardada al sistema");
        }
        else {
            ObservableList<String> aux = FXCollections.observableArrayList(q);
            Querys.setItems(aux);
            Querys.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
    }

    @FXML
    void pressContinue (javafx.event.ActionEvent event) throws Exception {
        if (Querys.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar una Query de la llista");
        }
        else if (!alfabetica.isSelected() && !creacio.isSelected() && !modificacio.isSelected()) {
            ordre = -1;
            ctrlPres.mostraError("Has de triar un metode d'ordenacio");
        }
        else{
            q = Querys.getSelectionModel().getSelectedItem();
            ctrlPres.canviaStage("LlistarDocsExpressioOutput");
        }
    }

    public String getQuery(){
        return q;
    }

    public Integer getOrdre(){
        return ordre;
    }
}
