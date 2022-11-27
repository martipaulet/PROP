package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class CtrlVistamenuPrincipal {

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

    private CtrlPresentacio ctrlPres;

    void initialize() {
        ctrlPres = CtrlPresentacio.getInstance();
    }

    @FXML
    void pressAltaDocument(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("AltaDocument");
    }

    @FXML
    void pressBaixaDocument(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("BaixaDocument");
    }

    @FXML
    void pressModificaDocument(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ModificaDocumentInicial");
    }

    @FXML
    void pressRealitzarConsulta(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("RealitzaConsulta");
    }

    @FXML
    void pressLlistarDocuments(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarDocuments");
    }

    @FXML
    void pressGestioExpressionsBooleanes(ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }
}
