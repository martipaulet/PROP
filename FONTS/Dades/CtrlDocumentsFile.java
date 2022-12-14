package Dades;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.util.Vector;


//classe encarregada de guardar i llegir dades sobre documents del fitxer document.json
public class CtrlDocumentsFile {

    private static CtrlDocumentsFile instance;

    public static CtrlDocumentsFile getInstance() {
        if (instance == null) instance = new CtrlDocumentsFile();
        return instance;
    }

    //Post: Es guarden les dades de la matriu docs, al fitxer documents.json situat al directori DATA
    public void guardaDocuments(Vector<Vector<String>> docs) {  //cada fila es un document amb les columnes -> autor, titol, contingut, datacreacio, dataUltimaModificacio
        JSONArray listadocs = new JSONArray();

        try { //buidem contingut anterior
            BufferedWriter bw = new BufferedWriter(new FileWriter("DATA/documentsFile.json"));
            bw.write("");
            bw.close();
        } catch (IOException e) {}


        for (Vector<String> doc : docs) {
            //Objecte document iessim
            JSONObject document = new JSONObject();
            document.put("autor", doc.get(0));
            document.put("titol", doc.get(1));
            document.put("contingut", doc.get(2));
            document.put("dataCreacio", doc.get(3));
            document.put("dataUltimaModificacio", doc.get(4));
            //referenciem que es un document al json
            JSONObject datosdocument = new JSONObject();
            datosdocument.put("document", document);
            //afegim l'array de documents
            listadocs.add(datosdocument);
        }
        try(FileWriter file = new FileWriter("DATA/documentsFile.json")) {
            file.write(listadocs.toJSONString());
            file.flush();
        }catch (IOException e) {
        }
    }

    //Post: retorna les dades dels documents guardats en el fitxer documents.json del directori DATA
    public Vector<Vector<String>> carregaDocuments() {
        JSONParser jsonParser = new JSONParser();
        Vector<Vector<String>> Cjtdocs = new Vector<>();

        try (FileReader file = new FileReader("DATA/documentsFile.json")) {
            Object obj = jsonParser.parse(file);
            JSONArray documents = (JSONArray) obj;

            for (Object docI : documents) {
                Vector<String> v = retornaObj((JSONObject) docI);
                Cjtdocs.add(v);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (ParseException e) {
        }
        return Cjtdocs;
    }


    private static Vector<String> retornaObj (JSONObject jsonObject) {
        Vector<String> v = new Vector<>();
        JSONObject docI = (JSONObject) jsonObject.get("document");
        String autor = (String) docI.get("autor");
        String titol = (String) docI.get("titol");
        String contingut = (String) docI.get("contingut");
        String dataCreacio = (String) docI.get("dataCreacio");
        String dataUltimaModificacio = (String) docI.get("dataUltimaModificacio");
        v.add(autor);
        //System.out.println(autor);
        v.add(titol);
        //System.out.println(titol);
        v.add(contingut);
        //System.out.println(contingut);
        v.add(dataCreacio);
        //System.out.println(dataCreacio);
        v.add(dataUltimaModificacio);
        //System.out.println(dataUltimaModificacio);
        return v;
    }

}
