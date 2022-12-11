package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CtrlVistaGestioExpressionsBooleanes {

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
    private Button AltaExpressio;

    @FXML
    private Button BaixaExpressio;

    @FXML
    private Button ModificaExpressio;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

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
    void pressAltaExpressio(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("AltaExpressio");
    }

    @FXML
    void pressBaixaExpressio(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("BaixaExpressio");
    }

    @FXML
    void pressModificarExpressio(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ModificarExpressio");
    }
}
