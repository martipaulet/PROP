package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CtrlVistaConsultaContingut {

    @FXML
    private Button AltaDocument;

    @FXML
    private Button BaixaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzaConsulta;

    @FXML
    private Button LlistarDocuments;

    @FXML
    private Button GestioExpressionsBooleanes;

    @FXML
    private TextArea AutorText;

    @FXML
    private TextArea TitolText;

    @FXML
    private Button Continue;

    @FXML
    private ListView<String> Contingut = new ListView<>();

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
    void pressRealitzaConsulta(javafx.event.ActionEvent event) throws IOException {
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
        if (Objects.equals(nom_autor, "") || Objects.equals(titol, "")) {
            ctrlPres.mostraError("Ni autor ni titol no poden ser null");
        }
        else{
            String aux = ctrlPres.obteContingutPres(nom_autor,titol);
            ObservableList<String> a = FXCollections.observableArrayList();
            a.add(aux);
            Contingut.setItems(a);
        }

    }
}