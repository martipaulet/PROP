package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class CtrlVistaBaixaDocument {

    @FXML
    private TextArea TextAutor;

    @FXML
    private TextArea TextTitol;

    @FXML
    private Button Continue;

    @FXML
    private Button AltaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzarConsulta;

    @FXML
    private Button LlistarDocuments;

    @FXML
    private Button GestioExpressionsBooleanes;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    @FXML
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        String a = TextAutor.getText();
        String t = TextTitol.getText();
        ctrlPres.baiaxaDocumentPres(a,t);
        ctrlPres.canviaStage("DocumentBorrat");
    }

    @FXML
    void pressAltaDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("AltaDocument");
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