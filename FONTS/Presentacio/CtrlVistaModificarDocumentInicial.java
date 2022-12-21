package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlVistaModificarDocumentInicial {


    private static CtrlVistaModificarDocumentInicial instance;

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
    private Button MostrarAutors;

    @FXML
    private Button MostrarTitolsAutor;

    @FXML
    private Button ModificaDoc;

    @FXML
    private ListView<String> Autors = new ListView<>();

    @FXML
    private ListView<String> Titols = new ListView<>();

    private final CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private static String nomAutor;

    private static String titol;


    public CtrlVistaModificarDocumentInicial() throws Exception {
    }

    public static CtrlVistaModificarDocumentInicial getInstance() throws Exception {
        if (instance == null) instance = new CtrlVistaModificarDocumentInicial();
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
    void pressMostrarAutors(javafx.event.ActionEvent event) throws Exception {
        ArrayList<String> a = ctrlPres.getAutorsPres();
        if (a.size() == 0) {
            ctrlPres.mostraError("No hi ha cap autor guardat al sistema");
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
            ctrlPres.mostraError("Has de seleccionar un autor de la llista. Però abans has de pitjar Mostrar Autors");
        }
        else {
            String autor = Autors.getSelectionModel().getSelectedItem();
            List<String> t = ctrlPres.titolsAutorPres(autor);
            if (t.size() == 0) {
                ctrlPres.mostraError("No hi ha cap titol d'aquest autor guardat al sistema");
            }
            else {
                ObservableList<String> aux = FXCollections.observableList(t);
                Titols.setItems(aux);
                Titols.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            }
        }
    }

    @FXML
    void pressModificaDoc(javafx.event.ActionEvent event) throws Exception {
        if(Autors.getSelectionModel().isEmpty()){
            ctrlPres.mostraError("No tens cap autor seleccionat, has de tornar a repetir el proces explicat al manual d'usuari");
        }
        else if (Titols.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar un Titol. Però abans has seleccionar un Autor i després pitjar el boto de Mostrar Titols Autor");
        }
        else if (!ctrlPres.existeixDocument(Autors.getSelectionModel().getSelectedItem(), Titols.getSelectionModel().getSelectedItem())){
            ctrlPres.mostraError("L'autor i el Titol seleccionat no corresponen, has de tornar a repetir el proces explicat al manual d'usuari");
        }
        else {
            nomAutor = Autors.getSelectionModel().getSelectedItem();
            titol = Titols.getSelectionModel().getSelectedItem();
            ctrlPres.canviaStage("ModificaDocument");
        }
    }


    public String getAutor(){ return nomAutor;}

    public String getTitol(){ return titol;}
}
