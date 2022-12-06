package Presentacio;
import Domini.Controladors.CtrlDomini;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Document;
import Domini.Model.Pair;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import  java.util.*;
import java.net.URL;

import javafx.stage.Stage;
import javafx.scene.control.Alert;



public class CtrlPresentacio {

    private CtrlDomini cd = CtrlDomini.getInstance();
    private HashMap<String,String> pathVistes;
    private static CtrlPresentacio cp;

    private Stage stageActual;

    public CtrlPresentacio() {
        pathVistes = new HashMap<>();
        iniVistes();
    }

    private void iniVistes(){
        String[] nomVistes = {"menuPrincipal","AltaDocument","BaixaDocument","ConsultaAutorsXPrefix",
                "ConsultaContingut","ConsultaTítolsXAutor","LlistarDocuments","ModificaDocumentInicial",
                "RealitzaConsulta", "DocumentCreat", "DocumentBorrat","ConsultaTitolsXAutor","ConsultaAutorsXPrefix",
                "ConsultaContingut", "LlistarSemblants", "LlistarDocsExpressio", "LlistarSemblantsOutput"};
        for (String s : nomVistes){
            pathVistes.put(s,"FONTS/Presentacio/"+s+".fxml");
        }
    }

    public static CtrlPresentacio getInstance() {
        if (cp == null) cp = new CtrlPresentacio();
        return cp;
    }

    //Inicialitza escena principal
    public void setPrimary(Stage primaryStage) throws Exception {
        stageActual = primaryStage;
        stageActual.setResizable(false);
        stageActual.setTitle("Gestor de fitxers");

    }

    public void canviaStage(String nomVista) throws IOException {
        try {
            URL url = new File(pathVistes.get(nomVista)).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stageActual.setScene(new Scene(root));
            stageActual.show();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            mostraError("No s'ha pogut canviar de pantalla, mira l'error en terminal");
        } catch (IOException e) {
            e.printStackTrace();
            mostraError("No s'ha pogut canviar de pantalla, mira l'error en terminal");
        }
    }

    //Mostra error per la pantalla
    public void mostraError(String mError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("ERROR: " + mError);
        alert.showAndWait();
    }

    public void inicialitzarVistes() {

    }

    public void altaDocumentPres(String nom_autor, String titol, String contingut) throws Exception {
        cd.altaDocument(nom_autor, titol, contingut);
    }

    public void baiaxaDocumentPres(String nom_autor, String titol) throws Exception {
        cd.baixaDocument(nom_autor, titol);
    }

    public List<String> titolsAutorPres(String nom_autor) throws Exception {
        return cd.titolsAutor(nom_autor);
    }

    public ArrayList<String> prefixAutorPres(String prefix) throws Exception {
        return cd.prefixAutor(prefix);
    }

    public String obteContingutPres(String nom_autor, String titol) throws Exception {
        return cd.obteContingut(nom_autor,titol);
    }

    public ArrayList<String> DocumentsSemblantsPres(String autor, String titol, Integer K, Integer mode, Integer ordre) throws Exception {
        ConjuntDocuments cjd = cd.DocumentsSemblants(autor,titol,K,mode);
        ConjuntDocuments c = cd.ordenaDocuments(cjd,ordre);
        return canviCjtDocToArrayList(c);
    }

   /* public List<String> consultaBooleanaPres(String query, int ordre) throws Exception {
        ConjuntDocuments cjd = cd.ConsultaBooleana(query);
        ConjuntDocuments cjdfi = cd.ordenaDocuments(cjd, ordre);

    }*/


    private ArrayList<String> canviCjtDocToArrayList(ConjuntDocuments c){
        ArrayList<String> ret = new ArrayList<>();
        HashMap<Pair, Document> doc = c.getMap();
        for ( Pair key: doc.keySet()){
            String s = key.getAutor()+", "+key.getTitol();
            ret.add(s);
        }

        return ret;
    }
}
