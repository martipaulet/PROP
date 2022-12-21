package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlVistaLlistarSemblants {

    private static CtrlVistaLlistarSemblants instance;

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
    private Button MostrarAutors;

    @FXML
    private Button MostrarTitolsAutor;

    @FXML
    private ListView<String> Autors = new ListView<>();

    @FXML
    private ListView<String> Titols = new ListView<>();

    @FXML
    private TextArea nombreDocs;

    @FXML
    private CheckBox tf_idf;

    @FXML
    private CheckBox tf;

    @FXML
    private CheckBox alfabetica;

    @FXML
    private CheckBox creacio;

    @FXML
    private CheckBox modificacio;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private static String a;

    private static String t;

    private static String k;

    private static Integer mode;

    private static Integer ordre;


    public CtrlVistaLlistarSemblants() throws Exception {
    }

    public static CtrlVistaLlistarSemblants getInstance() throws Exception {
        if (instance == null) instance = new CtrlVistaLlistarSemblants();
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
    void pressTf_idf() {
        if (tf_idf.isSelected()) {
            tf.setSelected(false);
            mode = 0;
        }
    }

    @FXML
    void pressTf() {
        if (tf.isSelected()) {
            tf_idf.setSelected(false);
            mode = 1;
        }
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
    void pressContinue(ActionEvent event) throws IOException {
        if(Autors.getSelectionModel().isEmpty()){
            ctrlPres.mostraError("Selecciona Autor i titol");
        }

        else if (Titols.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar un Titol de l'Autor seleccionat");
        }

        else if (!ctrlPres.existeixDocument(Autors.getSelectionModel().getSelectedItem(), Titols.getSelectionModel().getSelectedItem())){
            ctrlPres.mostraError("L'Autor i el Titol seleccionat no corresponen");
        }

        else if (Objects.equals(nombreDocs.getText(), "") || Integer.parseInt(nombreDocs.getText())<0 || Integer.parseInt(nombreDocs.getText())>10) {
            ctrlPres.mostraError("Falta Nombre de documents a retornar i ha de ser un valor del 0 al 10");
        }

        else if (!tf_idf.isSelected() && !tf.isSelected() ) {
            mode = -1;
            ctrlPres.mostraError("Has de triar un metode de cerca");
        }

        else if (!alfabetica.isSelected() && !creacio.isSelected() && !modificacio.isSelected()) {
            ordre = -1;
            ctrlPres.mostraError("Has de triar un metode d'ordenacio");
        }

        else {
            a = Autors.getSelectionModel().getSelectedItem();
            t = Titols.getSelectionModel().getSelectedItem();
            k = nombreDocs.getText();
            Integer num = Integer.parseInt(k);
            if (num < 0 || num > 10) ctrlPres.mostraError("El nombre de documents a retornar ha de ser un valor entre 0 i 10");
            else if (ctrlPres.numDocsTotalPres() <= num) ctrlPres.mostraError("El nombre de documents a retornar és major als documents del sistema. K ha de ser menor a " + ctrlPres.numDocsTotalPres() + ".");
            else ctrlPres.canviaStage("LlistarSemblantsOutput");
        }
    }

    public String getAutor(){
        return a;
    }

    public String getTitol(){
        return t;
    }

    public String getNombre(){
        return k;
    }

    public Integer getMode(){
        return mode;
    }
    public Integer getOrdre(){
        return ordre;
    }

}
