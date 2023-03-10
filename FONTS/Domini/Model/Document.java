package Domini.Model;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Document {


    //---ATRIBUTS---


    private final String autor_; //nom de l'autor del document.
    private final String titol_; //nom del titol del document.
    private String contingut_; //contingut del document.
    private ArrayList<Frase> frases_ = new ArrayList<>(); //conjunt de frases del document.
    private String dataCreacio_; //data de creacio del document.
    private String dataUltimaModificacio_; //data d'ultima modificacio del document
    private HashMap<String, Integer> paraules_ = new HashMap<>(); //mapa amb les paraules -> nom de cops que apareix la paraula en el document.


    //---CONSTRUCTORES---


    //Post: es crea una instancia de Document amb autor_ = autor, titol_ = titol i contingut_ = contingut.
    //      se li assigna al document la dataCreacio = dataUltimaModificacio.
    //      es separa el contingut del document per frases i es compta quants cops apareix cada paraula en el document.
    public Document (String autor, String titol, String contingut) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        setData();
        setParaules();
    }

    //Post: es crea una instancia de Document amb autor_ = autor, titol_ = titol i contingut_ = contingut.
    //      se li assigna al document la datCreacio = dataC i dataUltimaModificacio = dataM.
    //      es separa el contingut del document per frases i es compta quants cops apareix cada paraula en el document.
    public Document (String autor, String titol, String contingut, String dataC, String dataM) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        dataCreacio_ = dataC;
        dataUltimaModificacio_ = dataM;
        setParaules();
    }


    //---GETTERS/SETTERS---


    //Post: retorna l'autor_ del document.
    public String getAutor() {
        return autor_;
    }

    //Post: retorna el titol_ del document.
    public String getTitol() {
        return titol_;
    }

    //Post: retorna el contingut del document.
    public String getContingut() {
        return contingut_;
    }

    //Post: retorna la dataCreacio_ en tipus Date del document.
    public Date getDataCreacio() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d = null;
        try {
            d = formatter.parse(dataCreacio_);
        } catch (ParseException e) {
        }
        return d;
    }

    //Post: retorna la dataUltimaModificacio_ en tipus Date del document.
    public Date getDataUltimaModificacio() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date d = null;
        try {
            d = formatter.parse(dataUltimaModificacio_);
        } catch (ParseException e) {
        }
        return d;
    }
    //Post: retorna la dataCreacio_ del document.
    public String getDataCreacioString() {
        return dataCreacio_;
    }

    public String getDataUltimaModificacioString() {
        return dataUltimaModificacio_;
    }

    //Post: retorna el conjunt de frases del document en una llista.
    public ArrayList<Frase> getFrases() {
        return frases_;
    }

    //Post: retorna el mapa amb paraules del document -> nombre de cops que apareixen en el document.
    public HashMap<String, Integer> getParaules() {
        return paraules_;
    }

    //Post: actualitza el contingut_ = nouContingut i actualitza la dataUltimaModificacio.
    //      actualitza tambe el contingut del document per frases i es compta quants cops apareix cada paraula en el document.
    public void actualitzaDocument(String nouContingut) {
        contingut_ = nouContingut;
        actualitzaData();
        setFrases();
        setParaules();
    }

    //Post: retorna el conjunt de frases del document en un Set.
    public Set<Frase> getFrasesToSet() {
        return new HashSet<>(frases_);
    }


    //---CONSULTORES---


    //Post: retorna el conjunt de frases que contenen l'string s del document.
    public Vector<Frase> getFrasesParaula(String s) throws Exception{
        Vector<Frase> vf = new Vector<>();
        for (int i = 0; i < frases_.size(); ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) vf.add(f);
        }
        if (vf.size() == 0) throw new Exception("La paraula "+ s + " no apareix en cap frase del document\r\n");
        return vf;
    }


    //Pre: l'string s es una paraula o una sequencia de paraules.
    //Post: retona true si en el document esta l'string s en qualsevol de les seves frases. False altrament.
    public boolean fraseConteString(String s) {
        boolean conte = false;
        for (int i = 0; i < frases_.size() && !conte; ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) conte = true;
        }
        return conte;
    }


    //---ESCRIPTURA---


    //Post: mostra per pantalla els atributs del document.
    public void imprimir() {
        System.out.println(autor_);
        System.out.println(titol_);
        System.out.println(dataCreacio_);
        System.out.println(dataUltimaModificacio_);
    }




    //---METODES PRIVATS---

    private void actualitzaData() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(d);
        dataUltimaModificacio_ = strDate;
    }

    private void setData() {
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(d);
        dataCreacio_ = strDate;
        dataUltimaModificacio_ = strDate;
    }


    //Post: Compta quants cops apareix cada paraula en el document.
    private void setParaules() {
        paraules_ = new HashMap<>();
        String contingutSenseAccents = Normalizer.normalize(contingut_, Normalizer.Form.NFKD);
        contingutSenseAccents = Normalizer.normalize(contingutSenseAccents, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        String[] paraules = contingutSenseAccents.split("(?U)\\W+");
        for (String paraula : paraules) {
            paraula = paraula.toLowerCase();
            if(!StopWords().contains(paraula)){
                if (paraules_.containsKey(paraula)) {
                    int vegades = paraules_.get(paraula);
                    paraules_.put(paraula, ++vegades);
                }
                else {
                    paraules_.put(paraula, 1);
                }
            }
        }
    }

    //Post: separa el contingut del document per frases. Separa les frases per [. ? !].
    private void setFrases() {
        frases_ = new ArrayList<>();
        String contingutSenseAccents = Normalizer.normalize(contingut_, Normalizer.Form.NFKD);
        contingutSenseAccents = Normalizer.normalize(contingutSenseAccents, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
        String[] frases = contingutSenseAccents.split("(?U)[.!?] "); //".!?"
        for (String frase : frases) {
            frases_.add(new Frase(frase,titol_,autor_));
        }
    }

    //Post: retorna la llista d'StopWords.
    private ArrayList<String> StopWords() {
         ArrayList<String> a = new ArrayList<>(Arrays.asList("??ltim", "??ltima", "??ltimes", "??ltims", "a", "abans", "aix??", "al",
                "algun", "alguna", "algunes", "alguns", "all??", "all??", "all??", "als", "altra", "altre", "altres", "amb",
                "aprop", "aqu??", "aquell", "aquella", "aquelles", "aquells", "aquest", "aquesta", "aquestes", "aquests",
                "cada", "catorze", "cent", "cert", "certa", "certes", "certs", "cinc", "com", "cosa", "d", "darrer",
                "darrera", "darreres", "darrers", "davant", "de", "del", "dels", "despr??s", "deu", "dinou", "disset",
                "divuit", "dos", "dotze", "durant", "el", "ell", "ella", "elles", "ells", "els", "en", "encara", "et",
                "extra", "fins", "hi", "hem", "i", "jo", "l", "la", "les", "li", "llur", "lo", "los", "m??s", "m'", "ma", "massa",
                "mateix", "mateixa", "mateixes", "mateixos", "mes", "meu", "meva", "mig", "molt", "molta", "moltes", "molts",
                "mon", "mons", "n", "na", "ni", "no", "nosaltres", "nostra", "nostre", "nou", "ns", "o", "on", "onze",
                "pel", "per", "per??", "perqu??", "perque", "poc", "poca", "pocs", "poques", "primer", "primera", "primeres",
                "primers", "prop", "qu??", "qual", "quals", "qualsevol", "qualssevol", "quan", "quant", "quanta", "quantes",
                "quants", "quatre", "que", "qui", "quin", "quina", "quines", "quins", "quinze", "res", "s", "sa", "segon",
                "segona", "segones", "segons", "sense", "ses", "set", "setze", "seu", "seus", "seva", "seves", "sino", "sis",
                "sobre", "son", "sons", "sota", "t", "ta", "tal", "tals", "tan", "tant", "tanta", "tantes", "tants", "tes",
                "teu", "teus", "teva", "teves", "ton", "tons", "tot", "tota", "totes", "tots", "tres", "tretze", "tu", "un",
                "una", "unes", "uns", "vint", "vos", "vosaltres", "vost??", "vost??s", "vostra", "vostre", "vuit",
                "a", "actualmente", "adelante", "adem??s", "afirm??", "agreg??", "ahora", "ah??", "al", "algo", "alguna",
                "algunas", "alguno", "algunos", "alg??n", "alrededor", "ambos", "ante", "anterior", "antes", "apenas",
                "aproximadamente", "aqu??", "asegur??", "as??", "aunque", "ayer", "a??adi??", "a??n", "bajo", "bien", "buen",
                "buena", "buenas", "bueno", "buenos", "cada", "casi", "cerca", "cierto", "cinco", "coment??", "como", "con",
                "conocer", "considera", "consider??", "contra", "cosas", "creo", "cual", "cuales", "cualquier", "cuando",
                "cuanto", "cuatro", "cuenta", "c??mo", "da", "dado", "dan", "dar", "de", "debe", "deben", "debido", "decir",
                "dej??", "del", "dem??s", "dentro", "desde", "despu??s", "dice", "dicen", "dicho", "dieron", "diferente",
                "diferentes", "dijeron", "dijo", "dio", "donde", "dos", "durante", "e", "ejemplo", "el", "ella", "ellas",
                "ello", "ellos", "embargo", "en", "encuentra", "entonces", "entre", "era", "eran", "es", "esa", "esas",
                "ese", "eso", "esos", "esta", "estaba", "estaban", "estamos", "estar", "estar??", "estas", "este", "esto",
                "estos", "estoy", "estuvo", "est??", "est??n", "ex", "existe", "existen", "explic??", "expres??", "fin", "fue",
                "fuera", "fueron", "gran", "grandes", "ha", "haber", "habr??", "hab??a", "hab??an", "hace", "hacen", "hacer",
                "hacerlo", "hacia", "haciendo", "han", "hasta", "hay", "haya", "he", "hecho", "hemos", "hicieron", "hizo",
                "hoy", "hubo", "igual", "incluso", "indic??", "inform??", "junto", "la", "lado", "las", "le", "les", "lleg??",
                "lleva", "llevar", "lo", "los", "luego", "lugar", "manera", "manifest??", "mayor", "me", "mediante", "mejor",
                "mencion??", "menos", "mi", "mientras", "misma", "mismas", "mismo", "mismos", "momento", "mucha", "muchas",
                "mucho", "muchos", "muy", "m??s", "nada", "nadie", "ni", "ninguna", "ningunas", "ninguno", "ningunos",
                "ning??n", "no", "nos", "nosotras", "nosotros", "nuestra", "nuestras", "nuestro", "nuestros", "nueva",
                "nuevas", "nuevo", "nuevos", "nunca", "o", "ocho", "otra", "otras", "otro", "otros", "para", "parece",
                "parte", "partir", "pasada", "pasado", "pero", "pesar", "poca", "pocas", "poco", "pocos", "podemos",
                "podr??", "podr??n", "podr??a", "podr??an", "poner", "por", "porque", "posible", "primer", "primera",
                "primero", "primeros", "principalmente", "propia", "propias", "propio", "propios", "pr??ximo", "pr??ximos",
                "pudo", "pueda", "puede", "pueden", "pues", "que", "qued??", "queremos", "quien", "quienes", "quiere",
                "qui??n", "qu??", "realizado", "realizar", "realiz??", "respecto", "se", "sea", "sean", "segunda",
                "segundo", "seg??n", "seis", "ser", "ser??", "ser??n", "ser??a", "se??al??", "si", "sido", "siempre",
                "siendo", "siete", "sigue", "siguiente", "sin", "sino", "sobre", "sola", "solamente", "solas",
                "solo", "solos", "son", "su", "sus", "s??", "s??lo", "tal", "tambi??n", "tampoco", "tan", "tanto",
                "tendr??", "tendr??n", "tenemos", "tener", "tenga", "tengo", "tenido", "ten??a", "tercera", "tiene",
                "tienen", "toda", "todas", "todav??a", "todo", "todos", "total", "tras", "trata", "trav??s", "tres",
                "tuvo", "un", "una", "unas", "uno", "unos", "usted", "va", "vamos", "van", "varias", "varios", "veces",
                "ver", "vez", "y", "ya", "yo", "??l", "??sta", "??stas", "??ste", "??stos", "??ltima", "??ltimas", "??ltimo", "??ltimos",
                "a", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards",
                "again", "against", "ain't", "all", "allow", "allows", "almost", "alone", "along", "already", "also",
                "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow",
                "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate",
                "are", "aren't", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away",
                "awfully", "b", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand",
                "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both",
                "brief", "but", "by", "c", "c'mon", "c's", "came", "can", "can't", "cannot", "cant", "cause", "causes",
                "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently",
                "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldn't",
                "course", "currently", "d", "definitely", "described", "despite", "did", "didn't", "different", "do",
                "does", "doesn't", "doing", "don't", "done", "down", "downwards", "during", "e", "each", "edu", "eg",
                "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever",
                "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "f",
                "far", "few", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly",
                "forth", "four", "from", "further", "furthermore", "g", "get", "gets", "getting", "given", "gives", "go",
                "goes", "going", "gone", "got", "gotten", "greetings", "h", "had", "hadn't", "happens", "hardly", "has",
                "hasn't", "have", "haven't", "having", "he", "he's", "hello", "help", "hence", "her", "here", "here's",
                "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither",
                "hopefully", "how", "howbeit", "however", "i", "i'd", "i'll", "i'm", "i've", "ie", "if", "ignored",
                "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar",
                "instead", "into", "inward", "is", "isn't", "it", "it'd", "it'll", "it's", "its", "itself", "j", "just",
                "k", "keep", "keeps", "kept", "know", "knows", "known", "l", "last", "lately", "later", "latter",
                "latterly", "least", "less", "lest", "let", "let's", "like", "liked", "likely", "little", "look",
                "looking", "looks", "ltd", "m", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely",
                "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "n", "name", "namely",
                "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next",
                "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now",
                "nowhere", "o", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one",
                "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves",
                "out", "outside", "over", "overall", "own", "p", "particular", "particularly", "per", "perhaps",
                "placed", "please", "plus", "possible", "presumably", "probably", "provides", "q", "que", "quite",
                "qv", "r", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards",
                "relatively", "respectively", "right", "s", "said", "same", "saw", "say", "saying", "says",
                "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self",
                "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she",
                "should", "shouldn't", "since", "six", "so", "some", "somebody", "somehow", "someone",
                "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified",
                "specify", "specifying", "still", "sub", "such", "sup", "sure", "t", "t's", "take", "taken",
                "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "that's", "thats", "the", "their",
                "theirs", "them", "themselves", "then", "thence", "there", "there's", "thereafter", "thereby", "therefore",
                "therein", "theres", "thereupon", "these", "they", "they'd", "they'll", "they're", "they've", "think",
                "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru",
                "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying",
                "twice", "two", "u", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon",
                "us", "use", "used", "useful", "uses", "using", "usually", "uucp", "v", "value", "various", "very", "via",
                "viz", "vs", "w", "want", "wants", "was", "wasn't", "way", "we", "we'd", "we'll", "we're", "we've",
                "welcome", "well", "went", "were", "weren't", "what", "what's", "whatever", "when", "whence", "whenever",
                "where", "where's", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether",
                "which", "while", "whither", "who", "who's", "whoever", "whole", "whom", "whose", "why", "will", "willing",
                "wish", "with", "within", "without", "won't", "wonder", "would", "would", "wouldn't", "x", "y", "yes", "yet",
                "you", "you'd", "you'll", "you're", "you've", "your", "yours", "yourself", "yourselves", "z", "zero"
        ));
        return a;
    }


}
