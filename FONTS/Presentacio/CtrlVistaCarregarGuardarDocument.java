package Presentacio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class CtrlVistaCarregarGuardarDocument {

    @FXML
    private Button CarregarGuardarDocument;

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
    private Button ImportarDocument;

    @FXML
    private Button ExportarDocument;

    @FXML
    private CheckBox TXT;

    @FXML
    private CheckBox XML;

    @FXML
    private Button MostrarDoc;

    @FXML
    private ListView<String> Documents;

    private Integer mode;


    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    public CtrlVistaCarregarGuardarDocument() throws Exception {
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
    void pressTXT() {
        if (TXT.isSelected()) {
            XML.setSelected(false);
            mode = 0;
        }
    }

    @FXML
    void pressXML() {
        if (XML.isSelected()) {
            TXT.setSelected(false);
            mode = 1;
        }
    }

    @FXML
    void pressMostrarDoc(){
        Documents.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        ArrayList<String> doc = ctrlPres.DocSistema();
        if (doc.size() == 0) ctrlPres.mostraError("No hi ha cap document al gestor");
        ObservableList<String> aux = FXCollections.observableArrayList(doc);
        Documents.setItems(aux);
    }

    @FXML
    void pressImportarDocument(javafx.event.ActionEvent event) throws Exception {

        FileChooser fc = new FileChooser();
        fc.setTitle("Selecciona un fitxer TXT o XML");
        fc.getExtensionFilters().addAll( new ExtensionFilter("Documento de texto","*.txt") );
        fc.getExtensionFilters().addAll( new ExtensionFilter("Archivo XML","*.xml") );
        File seleccionat = fc.showOpenDialog(null);

        if (seleccionat != null) {
            String nomFitxer = seleccionat.getName();
            String extension = nomFitxer.substring(nomFitxer.lastIndexOf(".")+1);
            if(extension.equals("txt")) {
                ctrlPres.ImportarDocTXT(seleccionat.getAbsolutePath());
                ctrlPres.canviaStage("DocumentImportat");
            }
            else if (extension.equals("xml")){
                //FALTA IMPORTAR DOC XML
                ctrlPres.canviaStage("DocumentImportat");
            }
            else{
                ctrlPres.mostraError("El fitxer no es XML ni TXT");
            }

        }
        else{
            ctrlPres.mostraError("No s'ha seleccionat cap fitxer");
        }


    }

    @FXML
    void pressExportarDocument(ActionEvent event) throws Exception {
        if (!TXT.isSelected() && !XML.isSelected() ) {
            mode = -1;
            ctrlPres.mostraError("Has de triar el tipus de fitxer amb el que vols exportar");
        }
        else if ( Documents.getSelectionModel().isEmpty() ){
            ctrlPres.mostraError("Has de pitjar el boto de Mostrar Documents i has de seleccionar un document abans de pitjar el boto d'exportar");
        }
        else{
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Selecciona un directori on vols extreure el fitxer");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int ret = fc.showSaveDialog(null);

            if (ret == JFileChooser.APPROVE_OPTION) {
                File seleccionat = fc.getSelectedFile();
                if(mode == 0) {
                    ctrlPres.ExportarDocTXT(seleccionat.getAbsolutePath(), Documents.getSelectionModel().getSelectedItem() );
                    ctrlPres.canviaStage("DocumentExportat");
                }
                else if (mode == 1){
                    //FALTA EXPORTAR DOC XML
                    ctrlPres.canviaStage("DocumentExportat");
                }
                else{
                    mode = -1;
                    ctrlPres.mostraError("No has seleccionat el tipus de fitxer");
                }
            }

            else{
                ctrlPres.mostraError("No s'ha seleccionat cap directori");
            }

            //FALTA IMPLEMENTACIO EXPORTAR DOCUMENT
        }

    }



}
