package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class CtrlVistaLlistarDocsExpressio {

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
    private Button Continue;

    @FXML
    private TextArea QueryText;

    @FXML
    private CheckBox alfabetica;

    @FXML
    private CheckBox creacio;

    @FXML
    private CheckBox modificacio;

    @FXML
    private ListView<String> Docs = new ListView<>();

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    int ordre = -1;

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
        String query = QueryText.getText();

    }
}
