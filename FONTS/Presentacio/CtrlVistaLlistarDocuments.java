package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CtrlVistaLlistarDocuments {

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
    private Button GestioExpressionsBooleanes;

    @FXML
    private Button LlistarSemblants;

    @FXML
    private Button LlistarDocsExpressio;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    public CtrlVistaLlistarDocuments() throws Exception {
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
    void pressGestioExpressionsBooleanes(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }

    @FXML
    void pressLlistarSemblants(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarSemblants");
    }

    @FXML
    void pressLlistarDocsExpressio(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarDocsExpressio");
    }
}
