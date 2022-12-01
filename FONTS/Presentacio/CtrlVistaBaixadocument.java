package Presentacio;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;

public class CtrlVistaBaixadocument {

    @FXML
    private TextArea TextAutor;

    @FXML
    private TextArea TextTitol;

    @FXML
    private Button Continue;

    @FXML
    private Button AltaDocument;

    @FXML
    private Button ModificaDocument;

    @FXML
    private Button RealitzarConsulta;

    @FXML
    private Button LlistarDocuments;

    @FXML
    private Button GestioExpressionsBooleanes;

    private CtrlPresentacio ctrlPres = CtrlPresentacio.getInstance();

    @FXML
    void pressContinue(javafx.event.ActionEvent event) throws IOException {
        String a = TextAutor.getText();
        String t = TextTitol.getText();
        ctrlPres.baiaxaDocumentPres(a,t);
        //Cridar ctrlPresentacio i aquest cridar a la de domini per baixa document amb els arguments de autor i titol
    }

}
