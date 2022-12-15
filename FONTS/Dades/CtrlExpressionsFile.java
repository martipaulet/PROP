package Dades;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.util.ArrayList;
import java.util.List;

public class CtrlExpressionsFile {

    private static CtrlExpressionsFile instance;

    public static CtrlExpressionsFile getInstance() {
        if (instance == null) instance = new CtrlExpressionsFile();
        return instance;
    }

    public void guardaExpressions(List<String> l) { //cada element es una query guardada en el sistema
        JSONArray listaexpressions = new JSONArray();

        try { //buidem contingut anterior
            BufferedWriter bw = new BufferedWriter(new FileWriter("DATA/expressionsFile.json"));
            bw.write("");
            bw.close();
        } catch (IOException e) {}

        for (int i = 0; i < l.size(); ++i) {
            JSONObject expression = new JSONObject();
            expression.put("expression", l.get(i));
            //afegim l'array de expressions
            listaexpressions.add(expression);
        }
        try(FileWriter file = new FileWriter("DATA/expressionsFile.json")) {
            file.write(listaexpressions.toJSONString());
            file.flush();
        }catch (IOException e) {}
    }

    public ArrayList<String> carregaExpressions() {
        JSONParser jsonParser = new JSONParser();
        ArrayList<String> l = new ArrayList<String>();
        try (FileReader file = new FileReader("DATA/expressionsFile.json")) {
            Object obj = jsonParser.parse(file);
            JSONArray expressions = (JSONArray) obj;

            for (Object expressionI : expressions) {
                JSONObject aux = (JSONObject) expressionI;
                String expI = (String) aux.get("expression");
                System.out.println(expI);
                l.add(expI);
            }

        }catch (FileNotFoundException e) {

        }catch (IOException e) {

        }catch (ParseException e) {
        }
        return l;
    }

}
