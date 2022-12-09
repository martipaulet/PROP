package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlVistaModificarDocumentInicial {


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
    private Button MostrarDocuments;

    @FXML
    private Button MostrarContingut;

    @FXML
    private Button Continue;

    @FXML
    private TextArea AutorText;

    @FXML
    private TextArea TitolText;

    private final CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

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
        String nomAutor = AutorText.getText();
        String titol = TitolText.getText();
        if (Objects.equals(nomAutor, "") || Objects.equals(titol, "")) {
            ctrlPres.mostraError("Els camps autor i títol no poden ser nuls.");
        }
        else {
            if (ctrlPres.existeixDocument(nomAutor, titol)) {
                String contingut = ctrlPres.obteContingutPres(nomAutor, titol);
                CtrlVistaModificarDocument.setContingut(titol, nomAutor);
                ctrlPres.canviaStage("ModificaDocument");
            }
            else ctrlPres.mostraError("No existeix aquest document.");
        }
    }
}
