package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CtrlVistaModificarExpressio {

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
    private Button MostrarQuerys;

    @FXML
    private ListView<String> Querys = new ListView<>();

    @FXML
    private TextArea QueryModificadaText;

    @FXML
    private Button Continue;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    public CtrlVistaModificarExpressio() throws Exception {
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
    void pressMostrarQuerys(javafx.event.ActionEvent event) throws Exception {
        List<String> q = ctrlPres.getQuerysPres();
        if (q.size() == 0) {
            ctrlPres.mostraError("No hi ha cap Query guardada al sistema");
        } else {
            ObservableList<String> aux = FXCollections.observableArrayList(q);
            Querys.setItems(aux);
            Querys.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        }
    }

    @FXML
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        String queryModificada = QueryModificadaText.getText();
        if (Querys.getSelectionModel().isEmpty()) {
            ctrlPres.mostraError("Has de seleccionar una Query de la llista");
        }
        else if (Objects.equals(queryModificada, ""))  {
            ctrlPres.mostraError("La query modificada no pot estar buida");
        }
        else {
            if (ctrlPres.existeixQueryPres(queryModificada)) ctrlPres.mostraError("La query modificada ja existeix al sistema");
            else {
                ctrlPres.modificaExpressioPres(Querys.getSelectionModel().getSelectedItem(), queryModificada);
                ctrlPres.canviaStage("ExpressioModificada");
            }
        }

    }
}
