package Dades;

import Domini.DataInterfaces.CtrlDocuments;
import Domini.Model.Document;
import Domini.Model.Pair;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.util.HashMap;


//classe encarregada de guardar i llegir dades sobre documents del fitxer document.json
public class CtrlDocumentsFile implements CtrlDocuments {

    //Post: Es guarden les dades del mapa m rebudes, al fitxer documents.json situat al directori DATA
    public void saveDocuments(HashMap<Pair, Document> m) {

    }

    //Post: retorna les dades dels documents guardats en el fitxer documents.json del directori DATA
    public HashMap<Pair, Document> loadDocuments() {
        JSONParser jsP = new JSONParser();
        HashMap<Pair, Document> documents = new HashMap<>();
        return documents;
    }

}
