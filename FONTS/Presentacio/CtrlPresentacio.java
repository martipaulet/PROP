package Presentacio;

import Domini.Controladors.CtrlDomini;
import Domini.Model.ConjuntDocuments;
import Domini.Model.Document;
import Domini.Model.Pair;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


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


    public boolean ImportarDocTXT(String path) throws Exception {
        ArrayList<String> fitxer = new ArrayList<>();
        try( BufferedReader br = new BufferedReader(new FileReader(path)) ) {
            String LiniaAct;
            while ((LiniaAct = br.readLine()) != null) {
                fitxer.add(LiniaAct);
            }
        } catch (IOException e) {
            mostraError("No s'ha pogut llegir el fitxer");
            mostraError("El TXT ha de tenir la seguent escructura:\nAutor\nTitol\nContingut");
            return false;
        }
        if(fitxer.size() != 3) {
            mostraError("El TXT ha de tenir la seguent escructura:\nAutor\nTitol\nContingut");
            return false;
        }
        else{
            String nom_autor = fitxer.get(0);
            String titol = fitxer.get(1);
            if (Objects.equals(nom_autor, "") || Objects.equals(titol, "")){
                mostraError("Ni autor ni titol no poden estar buits");
                return false;
            }
            else if (existeixDocument(nom_autor, titol)){
                mostraError("El document ja existeix");
                return false;
            }
            else{
                cd.altaDocument(fitxer.get(0), fitxer.get(1), fitxer.get(2));
                return true;
            }
        }
    }

    public boolean ImportarDocXML(String path) throws Exception {
        try {
            ArrayList<String> tot = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            org.w3c.dom.Document doc = builder.parse(path);

            NodeList llistaDocument = doc.getElementsByTagName("Document");
            Node n = llistaDocument.item(0);
            if(n == null) {
                mostraError("El XML ha de tenir la seguent escructura:\n<Document>\n   <Autor>nom_autor</Autor>\n    <Titol>titol</Titol>\n    <Contingut>aqui va el contingut</Contingut>\n</Document>");
                return false;
            }
            if (n.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element) n;
                NodeList fills = e.getChildNodes();
                for (int i = 0; i < fills.getLength(); ++i){
                    Node fill = fills.item(i);
                    if(fill.getNodeType() == Node.ELEMENT_NODE){
                        if ( (i == 1 && fill.getNodeName().equals("Autor")) || (i == 3 && fill.getNodeName().equals("Titol")) || (i == 5 && fill.getNodeName().equals("Contingut"))){
                            tot.add(fill.getTextContent());
                        }
                        else{
                            mostraError("El XML ha de tenir la seguent escructura:\n<Document>\n   <Autor>nom_autor</Autor>\n    <Titol>titol</Titol>\n    <Contingut>aqui va el contingut</Contingut>\n</Document>");
                            return false;
                        }

                    }
                }
                if(tot.size() != 3) {
                    mostraError("El XML ha de tenir la seguent escructura:\n<Document>\n   <Autor>nom_autor</Autor>\n    <Titol>titol</Titol>\n    <Contingut>aqui va el contingut</Contingut>\n</Document>");                    return false;
                }
                else{
                    String nom_autor = tot.get(0);
                    String titol = tot.get(1);
                    if (Objects.equals(nom_autor, "") || Objects.equals(titol, "")){
                        mostraError("Ni autor ni titol no poden estar buits");
                        return false;
                    }
                    else if (existeixDocument(nom_autor, titol)){
                        mostraError("El document ja existeix");
                        return false;
                    }
                    else{
                        cd.altaDocument(tot.get(0), tot.get(1), tot.get(2));
                        return true;
                    }
                }
            }
            else{
                mostraError("El XML ha de tenir la seguent escructura:\n<Document>\n   <Autor>nom_autor</Autor>\n    <Titol>titol</Titol>\n    <Contingut>aqui va el contingut</Contingut>\n</Document>");
                return false;
            }
        }
        catch(ParserConfigurationException ex){
            System.out.print(ex.getMessage());
            mostraError("No trobo el fitxer XML, torna-ho a provar");
            return false;
        }
    }


    public void ExportarDocTXT(String path,String s) throws Exception {
        String[] autor_titol = s.split("\n");
        //Element 0 = autor
        //Element 1 = titol
        ArrayList<String> al = new ArrayList<>(List.of(autor_titol));

        String Contingut = cd.obteContingut(al.get(0),al.get(1));
        String tot = al.get(0)+"\n"+al.get(1)+"\n"+Contingut;
        try {
            File file = new File(path + "/" + al.get(1) + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(tot);
            bw.close();
        }
        catch (Exception e){
            mostraError("No s'ha pogut exportar correctament, torna-ho a intentar");
        }
    }

    public void ExportarDocXML(String path,String s) throws Exception {
        String[] autor_titol = s.split("\n");
        //Element 0 = autor
        //Element 1 = titol
        ArrayList<String> al = new ArrayList<>(List.of(autor_titol));

        String Contingut = cd.obteContingut(al.get(0),al.get(1));
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            org.w3c.dom.Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Document");
            doc.appendChild(rootElement);

            Element elemento1 = doc.createElement("Autor");
            elemento1.setTextContent(al.get(0));
            rootElement.appendChild(elemento1);

            Element elemento2 = doc.createElement("Titol");
            elemento2.setTextContent(al.get(1));
            rootElement.appendChild(elemento2);

            Element elemento3 = doc.createElement("Contingut");
            elemento3.setTextContent(Contingut);
            rootElement.appendChild(elemento3);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path + "/" + al.get(1) + ".xml"));

            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            mostraError("No s'ha pogut exportar, torna-ho a provar");
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            mostraError("No s'ha pogut exportar, torna-ho a provar");
            tfe.printStackTrace();
        }
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

    /*public void carregar() throws Exception {
        cd.carregaDades();
    }*/
}
