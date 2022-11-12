package Domini;
import java.util.*;

public class Document {
    private String autor_;
    private String titol_;
    private String contingut_;
    private ArrayList<Frase> frases_ = new ArrayList<>();

    private Date dataCreacio_;
    private Date dataUltimaModificacio_;

    private HashMap<String, Integer> paraules_ = new HashMap<>();


    public Document (String autor, String titol, String contingut) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        dataCreacio_ = new Date();
        dataUltimaModificacio_ = dataCreacio_;
        setParaules();
    }

    public Document (String autor, String titol, String contingut, Date dataC, Date dataM) {
        autor_ = autor;
        titol_ = titol;
        contingut_ = contingut;
        setFrases();
        dataCreacio_ = dataC;
        dataUltimaModificacio_ = dataM;
        setParaules();
    }

    public String getAutor() {
        return autor_;
    }

    public void setAutor(String autor) {
        autor_ = autor;
    }

    public String getTitol() {
        return titol_;
    }

    public String getContingut() {
        return contingut_;
    }

    public ArrayList<Frase> getFrases() {
        return frases_;
    }

    private void setFrases() {
        frases_ = new ArrayList<>();
        String[] frases = contingut_.split("[.!?]"); //".!?"
        for (String frase : frases) {
            frases_.add(new Frase(frase,titol_,autor_));
        }
    }

    public void actualitzaDocument(String nouContingut) {
        contingut_ = nouContingut;
        dataUltimaModificacio_ = new Date();
        setFrases();
        setParaules();
    }

    public boolean conteFrase(String s) {
        boolean conte = false;
        for (int i = 0; i < frases_.size() && !conte; ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) conte = true;
        }
        return conte;
    }

    public Vector<Frase> getFrasesParaula(String s) {
        Vector<Frase> vf = new Vector<>();
        for (int i = 0; i < frases_.size(); ++i) {
            Frase f = frases_.get(i);
            if (f.conteQuery(s)) vf.add(f);
        }
        if (vf.size() == 0) System.out.print("La paraula "+ s + " no apareix en cap frase del document\r\n"); //Excepcio
        return vf;
    }

    public Set<Frase> getFrasesToSet() {
        return new HashSet<>(frases_);
    }

    public Date getDataCreacio() {
        return dataCreacio_;
    }

    public Date getDataUltimaModificacio() {
        return dataUltimaModificacio_;
    }

    public HashMap<String, Integer> getParaules() {
        return paraules_;
    }

    //Implementacio tf
    private void setParaules() {
        paraules_ = new HashMap<>();
        String[] paraules = contingut_.split("(?U)\\W+");
        for (String paraula : paraules) {
            paraula = paraula.toLowerCase();
            if(!StopWords.contains(paraula)){
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

    public void imprimir() {
        System.out.println(autor_);
        System.out.println(titol_);
        System.out.println(contingut_);
        System.out.println(dataCreacio_);
        System.out.println(dataUltimaModificacio_);
        System.out.println(paraules_);
    }

    public void mostraDocument() {

    }

    private ArrayList<String> StopWords = new ArrayList<>(Arrays.asList("últim", "última", "últimes", "últims", "a", "abans", "això", "al",
            "algun", "alguna", "algunes", "alguns", "allà", "allí", "allò", "als", "altra", "altre", "altres", "amb",
            "aprop", "aquí", "aquell", "aquella", "aquelles", "aquells", "aquest", "aquesta", "aquestes", "aquests",
            "cada", "catorze", "cent", "cert", "certa", "certes", "certs", "cinc", "com", "cosa", "d", "darrer",
            "darrera", "darreres", "darrers", "davant", "de", "del", "dels", "després", "deu", "dinou", "disset",
            "divuit", "dos", "dotze", "durant", "el", "ell", "ella", "elles", "ells", "els", "en", "encara", "et",
            "extra", "fins", "hi", "hem", "i", "jo", "l", "la", "les", "li", "llur", "lo", "los", "més", "m'", "ma", "massa",
            "mateix", "mateixa", "mateixes", "mateixos", "mes", "meu", "meva", "mig", "molt", "molta", "moltes", "molts",
            "mon", "mons", "n", "na", "ni", "no", "nosaltres", "nostra", "nostre", "nou", "ns", "o", "on", "onze",
            "pel", "per", "però", "perquè", "perque", "poc", "poca", "pocs", "poques", "primer", "primera", "primeres",
            "primers", "prop", "què", "qual", "quals", "qualsevol", "qualssevol", "quan", "quant", "quanta", "quantes",
            "quants", "quatre", "que", "qui", "quin", "quina", "quines", "quins", "quinze", "res", "s", "sa", "segon",
            "segona", "segones", "segons", "sense", "ses", "set", "setze", "seu", "seus", "seva", "seves", "sino", "sis",
            "sobre", "son", "sons", "sota", "t", "ta", "tal", "tals", "tan", "tant", "tanta", "tantes", "tants", "tes",
            "teu", "teus", "teva", "teves", "ton", "tons", "tot", "tota", "totes", "tots", "tres", "tretze", "tu", "un",
            "una", "unes", "uns", "vint", "vos", "vosaltres", "vosté", "vostés", "vostra", "vostre", "vuit",
            "a", "actualmente", "adelante", "además", "afirmó", "agregó", "ahora", "ahí", "al", "algo", "alguna",
            "algunas", "alguno", "algunos", "algún", "alrededor", "ambos", "ante", "anterior", "antes", "apenas",
            "aproximadamente", "aquí", "aseguró", "así", "aunque", "ayer", "añadió", "aún", "bajo", "bien", "buen",
            "buena", "buenas", "bueno", "buenos", "cada", "casi", "cerca", "cierto", "cinco", "comentó", "como", "con",
            "conocer", "considera", "consideró", "contra", "cosas", "creo", "cual", "cuales", "cualquier", "cuando",
            "cuanto", "cuatro", "cuenta", "cómo", "da", "dado", "dan", "dar", "de", "debe", "deben", "debido", "decir",
            "dejó", "del", "demás", "dentro", "desde", "después", "dice", "dicen", "dicho", "dieron", "diferente",
            "diferentes", "dijeron", "dijo", "dio", "donde", "dos", "durante", "e", "ejemplo", "el", "ella", "ellas",
            "ello", "ellos", "embargo", "en", "encuentra", "entonces", "entre", "era", "eran", "es", "esa", "esas",
            "ese", "eso", "esos", "esta", "estaba", "estaban", "estamos", "estar", "estará", "estas", "este", "esto",
            "estos", "estoy", "estuvo", "está", "están", "ex", "existe", "existen", "explicó", "expresó", "fin", "fue",
            "fuera", "fueron", "gran", "grandes", "ha", "haber", "habrá", "había", "habían", "hace", "hacen", "hacer",
            "hacerlo", "hacia", "haciendo", "han", "hasta", "hay", "haya", "he", "hecho", "hemos", "hicieron", "hizo",
            "hoy", "hubo", "igual", "incluso", "indicó", "informó", "junto", "la", "lado", "las", "le", "les", "llegó",
            "lleva", "llevar", "lo", "los", "luego", "lugar", "manera", "manifestó", "mayor", "me", "mediante", "mejor",
            "mencionó", "menos", "mi", "mientras", "misma", "mismas", "mismo", "mismos", "momento", "mucha", "muchas",
            "mucho", "muchos", "muy", "más", "nada", "nadie", "ni", "ninguna", "ningunas", "ninguno", "ningunos",
            "ningún", "no", "nos", "nosotras", "nosotros", "nuestra", "nuestras", "nuestro", "nuestros", "nueva",
            "nuevas", "nuevo", "nuevos", "nunca", "o", "ocho", "otra", "otras", "otro", "otros", "para", "parece",
            "parte", "partir", "pasada", "pasado", "pero", "pesar", "poca", "pocas", "poco", "pocos", "podemos",
            "podrá", "podrán", "podría", "podrían", "poner", "por", "porque", "posible", "primer", "primera",
            "primero", "primeros", "principalmente", "propia", "propias", "propio", "propios", "próximo", "próximos",
            "pudo", "pueda", "puede", "pueden", "pues", "que", "quedó", "queremos", "quien", "quienes", "quiere",
            "quién", "qué", "realizado", "realizar", "realizó", "respecto", "se", "sea", "sean", "segunda",
            "segundo", "según", "seis", "ser", "será", "serán", "sería", "señaló", "si", "sido", "siempre",
            "siendo", "siete", "sigue", "siguiente", "sin", "sino", "sobre", "sola", "solamente", "solas",
            "solo", "solos", "son", "su", "sus", "sí", "sólo", "tal", "también", "tampoco", "tan", "tanto",
            "tendrá", "tendrán", "tenemos", "tener", "tenga", "tengo", "tenido", "tenía", "tercera", "tiene",
            "tienen", "toda", "todas", "todavía", "todo", "todos", "total", "tras", "trata", "través", "tres",
            "tuvo", "un", "una", "unas", "uno", "unos", "usted", "va", "vamos", "van", "varias", "varios", "veces",
            "ver", "vez", "y", "ya", "yo", "él", "ésta", "éstas", "éste", "éstos", "última", "últimas", "último", "últimos",
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



}
