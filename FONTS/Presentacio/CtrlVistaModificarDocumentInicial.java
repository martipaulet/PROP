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


    private static CtrlVistaModificarDocumentInicial instance;

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
    private Button GestioExpressionsBooleanes;

    @FXML
    private Button Continue;

    @FXML
    private TextArea AutorText;

    @FXML
    private TextArea TitolText;

    private final CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    private static String nomAutor;

    private static String titol;


    public CtrlVistaModificarDocumentInicial() throws Exception {
    }

    public static CtrlVistaModificarDocumentInicial getInstance() throws Exception {
        if (instance == null) instance = new CtrlVistaModificarDocumentInicial();
        return instance;
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
    void pressLlistarDocuments(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("LlistarDocuments");
    }

    @FXML
    void pressGestioExpressionsBooleanes(javafx.event.ActionEvent event) throws IOException {
        ctrlPres.canviaStage("GestioExpressionsBooleanes");
    }

    @FXML
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        nomAutor = AutorText.getText();
        titol = TitolText.getText();
        if (Objects.equals(nomAutor, "") || Objects.equals(titol, "")) {
            ctrlPres.mostraError("Els camps autor i títol no poden ser nuls.");
        }
        else {
            if (ctrlPres.existeixDocument(nomAutor, titol)) {
                ctrlPres.canviaStage("ModificaDocument");
            }
            else ctrlPres.mostraError("No existeix aquest document.");
        }
    }

    public String getAutor(){ return nomAutor;}

    public String getTitol(){ return titol;}
}
