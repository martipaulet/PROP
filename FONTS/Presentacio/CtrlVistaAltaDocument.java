package Presentacio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class CtrlVistaAltaDocument {

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
    private TextArea AutorText;

    @FXML
    private TextArea TitolText;

    @FXML
    private TextArea ContingutText;

    @FXML
    private Button Continue;

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
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        String nom_autor = AutorText.getText();
        String titol = TitolText.getText();
        String contingut = ContingutText.getText();
        if (Objects.equals(nom_autor, "") || Objects.equals(titol, "")){
            ctrlPres.mostraError("Ni autor ni titol no poden estar buits");
        }
        else {
            if (ctrlPres.existeixDocument(nom_autor, titol)) ctrlPres.mostraError("El document ja existeix");
            else {
                ctrlPres.altaDocumentPres(nom_autor, titol, contingut);
                ctrlPres.canviaStage("DocumentCreat");
            }
        }
    }
}
