package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class CtrlVistaRealitzarConsulta {


    @FXML
    private Button AltaDocument;

    @FXML
    private Button BaixaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button LlistarDocuments;

    @FXML
    private Button GestioExpressionsBooleanes;

    @FXML
    private Button ConsultaTitolsAutor;

    @FXML
    private Button ConsultaAutorsPrefix;

    @FXML
    private Button ConsultaContingutDocument;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();



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
    void pressLlistarDocuments(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarDocuments");
    }

    @FXML
    void pressGestioExpressionsBooleanes(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }

    @FXML
    void pressConsultaTitolsAutor(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ConsultaTitolsAutor");
    }

    @FXML
    void pressConsultaAutorsPrefix(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ConsultaAutorsPrefix");
    }

    @FXML
    void pressConsultaContingutDocument(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("ConsultaContingutDocument");
    }
}
