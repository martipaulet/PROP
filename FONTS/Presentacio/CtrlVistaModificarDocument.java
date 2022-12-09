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

public class CtrlVistaModificarDocument {


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
    private static TextArea ContingutText;

    private static String titol_;

    private static String nomAutor_;
    private static final CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

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
    static void setContingut(String titol, String nomAutor) throws Exception {
        titol_ = titol;
        nomAutor_ = nomAutor;
        String contingut = (ctrlPres.obteContingutPres(nomAutor, titol));
        ContingutText.setText(contingut);
    }

    @FXML
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        String contingut = ContingutText.getText();
        ctrlPres.modificaContingut(nomAutor_, titol_, contingut);
        ctrlPres.canviaStage("DocumentModificat");
    }

}
