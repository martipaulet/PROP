package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;

import javax.swing.*;
import java.io.IOException;

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

    private Integer mode;


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
    void pressExportarDocument(javafx.event.ActionEvent event) throws Exception {
        if (!TXT.isSelected() && !XML.isSelected() ) {
            mode = -1;
            ctrlPres.mostraError("Has de triar el tipus de fitxer amb el que vols exportar");
        }
        else{
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Selecciona un directori on vols extreure el fitxer");
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int ret = fc.showSaveDialog(null);

            if (ret == JFileChooser.APPROVE_OPTION) {
                File seleccionat = fc.getSelectedFile();
                if(mode == 0) {
                    ctrlPres.ExportarDocTXT(seleccionat.getAbsolutePath());
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
