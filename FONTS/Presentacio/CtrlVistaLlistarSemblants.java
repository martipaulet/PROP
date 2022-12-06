package Presentacio;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import java.io.IOException;

public class CtrlVistaLlistarSemblants {

    private static CtrlVistaLlistarSemblants instance;
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
    private TextArea AutorText;

    @FXML
    private TextArea TitolText;

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


    public CtrlVistaLlistarSemblants() {
    }

    public static CtrlVistaLlistarSemblants getInstance() {
        if (instance == null) instance = new CtrlVistaLlistarSemblants();
        return instance;
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
    void pressContinue(ActionEvent event) throws IOException {
        if (AutorText.getText() == "") {
            ctrlPres.mostraError("Falta Autor");
        }

        else if (TitolText.getText() == "") {
            ctrlPres.mostraError("Falta Titol");
        }

        else if (nombreDocs.getText() == "" || Integer.parseInt(nombreDocs.getText())<0 || Integer.parseInt(nombreDocs.getText())>10) {
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
            a = AutorText.getText();
            t = TitolText.getText();
            k = nombreDocs.getText();
            ctrlPres.canviaStage("LlistarSemblantsOutput");
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
