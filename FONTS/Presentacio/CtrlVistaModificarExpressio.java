package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
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
    private TextArea QueryAModificarText;

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
    void pressContinue(javafx.event.ActionEvent event) throws Exception {
        String queryAModificar = QueryAModificarText.getText();
        String queryModificada = QueryModificadaText.getText();
        if (Objects.equals(queryAModificar, "") || Objects.equals(queryModificada, ""))  {
            ctrlPres.mostraError("Cap de les dues querys pot estar buida");
        }
        else {
            if (!ctrlPres.existeixQueryPres(queryAModificar)) ctrlPres.mostraError("La query indicada per modificar no existeix al sistema");
            else if (ctrlPres.existeixQueryPres(queryModificada)) ctrlPres.mostraError("La query modificada ja existeix al sistema");
            else {
                ctrlPres.modificaExpressioPres(queryAModificar, queryModificada);
                ctrlPres.canviaStage("ExpressioModificada");
            }
        }

    }
}
