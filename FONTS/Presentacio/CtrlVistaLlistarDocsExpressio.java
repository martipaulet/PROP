package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class CtrlVistaLlistarDocsExpressio {

    private static CtrlVistaLlistarDocsExpressio instance;

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
    private TextArea QueryText;

    @FXML
    private CheckBox alfabetica;

    @FXML
    private CheckBox creacio;

    @FXML
    private CheckBox modificacio;


    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private static String q;

    private static Integer ordre;

    public CtrlVistaLlistarDocsExpressio() {
    }

    public static CtrlVistaLlistarDocsExpressio getInstance() {
        if (instance == null) instance = new CtrlVistaLlistarDocsExpressio();
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
    void pressContinue (javafx.event.ActionEvent event) throws Exception {
        if (QueryText.getText() == "") {
            ctrlPres.mostraError("Has d'omplir la casella de la query amb una expressio booleana");
        }
        else if (!alfabetica.isSelected() && !creacio.isSelected() && !modificacio.isSelected()) {
            ordre = -1;
            ctrlPres.mostraError("Has de triar un metode d'ordenacio");
        }
        else{
            q = QueryText.getText();
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
