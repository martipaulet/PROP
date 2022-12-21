package Domini.Model;

import java.util.*;


public class ExpressionTree {


    //---ATRIBUTS---


    private Node root;  //Node arrel de l'arbre.


    //---CONSTRUCTORA---


    //Post: es crea una instancia d'expressionTree a partir una query donada.
    public ExpressionTree(String query) {
        List<String> postfix = adapt(query);
        root = expressionTree(postfix);
    }


    //---CLASSE PRIVADA NODE---


    //Clase Node per implementar l'arbre
    private static class Node {
        public String data;  //valor del node, pot ser una paraula, una sequencia de paraules o un operador[!,&,|].
        public Node left, right; //subnode esquerra i subnode dret.

        //Constructora del node
        //Post: crea una instancia de node posant data = s i amb subnodes buits.
        private Node(String s) {
            data = s;
            left = right = null;
        }

        //Constructora del node
        //Post: crea una instancia de node posant data = s, subnode esquerra = left i subnode dreta buit.
        private Node(String s, Node left) {
            data = s;
            this.left = left;
            this.right = null;
        }

        //Constructora del node
        //Post: crea una instancia de node posant data = s, subnode esquerra = left i subnode dreta = right.
        private Node(String data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        //Consultora de node
        //Post: retorna true si el node es fulla, per tant si te subnode esq i subnode dreta buits. False altrament.
        private boolean esFulla() {
            return (left == null) && (right == null);
        }
    }
    //FI CLASSE NODE.


    //---MODIFICADORA---


    //Pre: la query indicada no existeix en el sistema.
    //Post: es modifica l'expressionTree del p.i a partir de la query donada.
    public void modifica(String query) {
        List<String> postfix = adapt(query);
        root = expressionTree(postfix);
    }


    //---CALCUL---


    //Pre: se li passa com a parametre el conjunt de documents total del sistema.
    //Post: retorna un conjunt de documents format pels documents que tenen almenys una frase que compleix la query representada en l'expressionTree.
    public ConjuntDocuments calculate(ConjuntDocuments total) throws Exception{
        Set<Frase> frases = calculateIm(root, total);
        LinkedHashMap<Pair,Document> vd = new LinkedHashMap<>();
        for (Frase f : frases) {
            Document d = total.getDocument(f.getAutorDoc(),f.getTitolDoc());
            Pair p = new Pair(d.getAutor(),d.getTitol());
            vd.put(p,d);
        }
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        return cd;
    }


    //---METODES PRIVATS---


    //Pre: la query segueix sintaxis correcta seguint l'exemple de l'enunciat: {p1 p2 p3} & ("hola adéu" | pep) & !joan
    //Post: modifica la query introduida en una Llista de string suprimint espais i "" i separant paraules de signes [(,),{,},!,|,&] despres la pasa en notacio post-fix.
    private List<String> adapt(String query) {
        List<String> result = new ArrayList<>();
        int i = 0;
        boolean curlyOpened = false;
        while (i < query.length()) {
            //avaluar query char per char.
            char ch = query.charAt(i);
            //si es operador afegir a la llista. Si a mes a mes es { o } substituir per ( o ) respectivament.
            if (isOperator(ch)) {
                boolean in = false;
                if (isCurlyBrace(ch)) {
                    in = true;
                    curlyOpened = !curlyOpened;
                    if (curlyOpened) result.add("(");
                    else result.add(")");
                }
                //transforma ch en string
                if (!in) result.add(""+ch);
                ++i;
            }
            else if (isBlank(ch)) ++i;  //si es un espai no fer res.
            else if (isQuotes(ch)) {    //si son " suprimir i afegir la sequencia de paraules com a un string simple a la llista.
                ++i;
                if (i < query.length()) ch = query.charAt(i);
                StringBuilder s = new StringBuilder();
                while (!isQuotes(ch) && i < query.length()) {
                    s.append(ch);
                    ++i;
                    if (i < query.length()) ch = query.charAt(i);
                }
                ++i;
                result.add(String.valueOf(s));
            }
            //si es una paraula simple (no es sequencia de paraules) afegir a la llista.
            //si a mes a mes la paraula estava entre {} posar operador & a la llista just despres. (ja que {p1 p2 p3} = p1 & p2 & p3 ).
            else {
                StringBuilder s = new StringBuilder();
                s.append(ch);
                ++i;
                if (i < query.length()) ch = query.charAt(i);
                while (!isBlank(ch) && !isOperator(ch) && i < query.length()) {
                    s.append(ch);
                    ++i;
                    if (i < query.length()) ch = query.charAt(i);
                }
                result.add(String.valueOf(s));
                if (curlyOpened) {
                    if (i < query.length() && query.charAt(i) != '}') result.add("&");
                }
            }
        }
        //pasar la llista un cop acabat el bucle a notacio postfix per fer mes simple l'arbre despres.
        List<String> result2 = infixToPostfix(result);
        return result2;
    }

    //Post: retorna la llista introduida en notacio infix a notacio postfix.
    private List<String> infixToPostfix(List<String>infix) {
        Stack<String> st = new Stack<>();
        st.push("#");
        List<String> postfix = new ArrayList<>();

        for (String s : infix) {
            if (!isOperator(s)) {
                postfix.add(s);
            }
            else if (Objects.equals(s, "(")) {
                st.push(s);
            }
            else if (Objects.equals(s, ")")) {
                while (!Objects.equals(st.peek(), "#") && !Objects.equals(st.peek(), "(")) {
                    postfix.add(st.peek());
                    st.pop();
                }
                st.pop();
            }
            else {
                if (preced(s) > preced(st.peek())) st.push(s);
                else {
                    while (!Objects.equals(st.peek(), "#") && preced(s) <= preced(st.peek())) {
                        postfix.add(st.peek());
                        st.pop();
                    }
                    st.push(s);
                }
            }
        }
        while (!Objects.equals(st.peek(), "#")) {
            postfix.add(st.peek());
            st.pop();
        }
        return postfix;
    }

    //Post: retorna true si String s es [{,},(,),&,!,|]. False altrament.
    private boolean isOperator(String s) {
        return Objects.equals(s, "{") || Objects.equals(s, "}") || Objects.equals(s, "(") || Objects.equals(s, ")") || Objects.equals(s, "&") ||
                Objects.equals(s, "|") || Objects.equals(s, "!");
    }

    //Post: retorna true si char s es [{,},(,),&,!,|]. False altrament.
    private boolean isOperator(char s) {
        return s == '{' || s == '}' || s == '(' || s == ')' || s == '&' || s == '|' || s == '!';
    }

    //Post: retorna true si char s es [{,}]. False altrament.
    private boolean isCurlyBrace(char s) {
        return s == '{' || s == '}';
    }

    //Post: retorna true si char s es un espai ' '. False altrament.
    private boolean isBlank(char s) {
        return s == ' ';
    }

    //Post: retorna true si char s es ["]. False altrament.
    private boolean isQuotes(char s) {
        return s == '"';
    }

    //Post: retorna el valor de precedencia dels operadors. Quant mes gran es el valor abans va.
    private int preced(String s) {
        if(Objects.equals(s, "!")) {
            return 3;
        }
        else if(Objects.equals(s, "&")) {
            return 2;
        }
        else if(Objects.equals(s, "|")) {
            return 1;
        }
        else if(Objects.equals(s, ")")) {
            return 0;
        }
        else return -1;
    }

    //Pre: la llista que conte la query esta en notacio postfix.
    //Post: Construeix l'ExpressionTree a partir de la llista en notacio postfix. Retorna el node arrel de l'arbre resultant.
    private Node expressionTree(List<String> ListQuery) {
        //cas base
        if (ListQuery == null || ListQuery.size() == 0) {
            return null;
        }
        //stack per guardar els nodes
        Stack<Node> st = new Stack<>();

        for (String s : ListQuery) {
            // si es operator i d'aritat 2
            if (isOperator(s)) {
                if (!Objects.equals(s, "!")) {
                    // pop 2 nodes del stack
                    Node x = st.pop();
                    Node y = st.pop();
                    // Construir nou bintree amb root=operador i el qual left i right apunten als nodes x,y
                    Node node = new Node(s, y, x);
                    // push node al stack
                    st.add(node);
                }
                else {
                    Node x = st.pop();
                    Node node = new Node(s,x);
                    st.add(node);
                }
            }
            // si s es operand, crear nou bintree el qual te root=operand i sense fills
            else {
                st.add(new Node(s));
            }
        }
        return st.peek();
    }

    //Pre: Conjunt documents total es el conjunt de documents del sistema.
    //Post: retorna un set de frases que compleixen la query representada en l'expresionTree.
    private Set<Frase> calculateIm(Node n, ConjuntDocuments total) throws Exception{
        if (n != null) {
            Set<Frase> frases = new HashSet<>();
            if (n.esFulla()) {
                frases = total.obteFrasesContenen(n.data);
                return frases;
            }
            else {
                frases = operaSets(calculateIm(n.left, total),calculateIm(n.right, total),n.data,total);
                return frases;
            }
        }
        return null;
    }

    //Pre: op pot ser [|,&,!] i Conjunt documents total es el conjunt de documents del sistema.
    //Post: retorna l'operacio del conjunt de frases en funcio de l'operador.
    private Set<Frase> operaSets(Set<Frase> s1, Set<Frase> s2, String op, ConjuntDocuments total) {

        Set<Frase> result = new HashSet<>();
        //fer complementari de s1
        if (s2 == null && Objects.equals(op, "!")) {
            result = total.MapToSet();
            if (s1 != null) result.removeAll(s1);
        }
        //fer interseccio de s1 i s2
        else if (Objects.equals(op, "&")) {
            result = s1;
            if (s2 != null) result.retainAll(s2);
        }
        //fer unio de s1 i s2
        else if (Objects.equals(op, "|")) {
            result = s1;
            if (s2 != null) result.addAll(s2);
        }
        return result;
    }

}
