package Presentacio;
import Domini.Controladors.CtrlDomini;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Document;
import Domini.Model.Pair;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import  java.util.*;
import java.net.URL;

import javafx.stage.Stage;
import javafx.scene.control.Alert;



public class CtrlPresentacio {

    private CtrlDomini cd = CtrlDomini.getInstance();
    private HashMap<String,String> pathVistes;
    private static CtrlPresentacio cp;

    private Stage stageActual;

    public CtrlPresentacio() throws Exception {
        pathVistes = new HashMap<>();
        iniVistes();
    }

    private void iniVistes(){
        String[] nomVistes = {"menuPrincipal","AltaDocument","BaixaDocument","ConsultaAutorsXPrefix",
                "ConsultaContingut","ConsultaTítolsXAutor","LlistarDocuments","ModificaDocumentInicial",
                "ModificaDocument", "DocumentModificat", "RealitzaConsulta", "DocumentCreat", "DocumentBorrat",
                "ConsultaTitolsXAutor","ConsultaAutorsXPrefix", "ConsultaContingut", "LlistarSemblants",
                "LlistarDocsExpressio", "LlistarSemblantsOutput", "LlistarDocsExpressioOutput",
                "GestioExpressionsBooleanes", "AltaExpressio", "BaixaExpressio", "ModificarExpressio",
                "ExpressioCreada", "ExpressioBorrada", "ExpressioModificada","CarregarGuardarDocument","DocumentImportat",
                "DocumentExportat"};
        for (String s : nomVistes){
            pathVistes.put(s,"FONTS/Presentacio/"+s+".fxml");
        }
    }

    public static CtrlPresentacio getInstance() throws Exception {
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

    public boolean existeixDocument(String nomAutor, String titol) {
        return cd.existeixDocument(nomAutor, titol);
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

    public ArrayList<String> ConsultaBooleanaPres(String query, Integer ordre) throws Exception {
        ConjuntDocuments cjd = cd.ConsultaBooleana(query);
        ConjuntDocuments c = cd.ordenaDocuments(cjd,ordre);
        return canviCjtDocToArrayList(c);
    }

    public void altaExpressioPres(String query) throws Exception {
        cd.altaExpressioBooleana(query);
    }

    public void baixaExpressioPres(String query) throws Exception {
        cd.baixaExpressioBooleana(query);
    }

    public void modificaExpressioPres(String queryAModificar, String queryModificada) throws Exception {
        cd.modificaExpressioBooleana(queryAModificar, queryModificada);
    }

    public void modificaContingut(String nomAutor, String titol, String contingut) throws Exception {
        cd.modificarDocument(contingut, nomAutor, titol);
    }

    public Integer numDocsTotalPres() {
        return cd.numDocsTotal();
    }

    public Boolean estaAutorPres(String autor) { return cd.estaAutor(autor); }

    public Boolean existeixQueryPres(String query) { return cd.existeixQuery(query); }


    //---IMPORTAR I EXPORTAR---


    public void ImportarDocTXT(String path) throws Exception {
        ArrayList<String> fitxer = new ArrayList<>();
        try( BufferedReader br = new BufferedReader(new FileReader(path)) ) {
            String LiniaAct;
            while ((LiniaAct = br.readLine()) != null) {
                fitxer.add(LiniaAct);
            }
        } catch (IOException e) {
            mostraError("No s'ha pogut llegir el fitxer");
        }
        cd.altaDocument(fitxer.get(0), fitxer.get(1), fitxer.get(2));
    }

    public void ExportarDocTXT(String path,String s) throws Exception {
        String[] autor_titol = s.split("\n");
        //Element 0 = autor
        //Element 1 = titol
        ArrayList<String> al = new ArrayList<>(List.of(autor_titol));

        String Contingut = cd.obteContingut(al.get(0),al.get(1));
        String tot = al.get(0)+"\n"+al.get(1)+"\n"+Contingut;

        File file = new File(path+"/"+al.get(1)+".txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(tot);
        bw.close();
    }

    public ArrayList<String> DocSistema(){
        return canviCjtDocToArrayList(cd.DocSistema());
    }


    //---METODES PRIVATS---

    private ArrayList<String> canviCjtDocToArrayList(ConjuntDocuments c){
        ArrayList<String> ret = new ArrayList<>();
        HashMap<Pair, Document> doc = c.getMap();
        for ( Pair key: doc.keySet()){
            String s = key.getAutor()+"\n"+key.getTitol();
            ret.add(s);
        }

        return ret;
    }

    public void guardar() {
        cd.guardaDades();
    }
}
