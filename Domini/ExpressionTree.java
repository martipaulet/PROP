package Domini;

import CtrlDomini.CtrlDomini;

import javax.print.Doc;
import java.util.*;

public class ExpressionTree {
    Node root;
    public ExpressionTree(String query) {
        List<String> postfix = adapt(query);
        root = expressionTree(postfix);
    }

    public void modifica(String query) {
        List<String> postfix = adapt(query);
        root = expressionTree(postfix);
    }


    //Clase Node per implementar l'arbre
    private static class Node {
        String data;
        Node left, right;

        //Crear nou node
        private Node(String s) {
            data = s;
            left = right = null;
        }

        private Node(String s, Node left) {
            data = s;
            this.left = left;
            this.right = null;
        }

        private Node(String data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private String getData() {
            return data;
        }

        private boolean esFulla() {
            return left == null && right == null;
        }
    }


    //pre: la query segueix sintaxis correcta seguint l'exemple de l'enunciat: {p1 p2 p3} & ("hola adéu" | pep) & !joan
    //modifica la query introduida en un Vector suprimint espais i "" i separant paraules de signes (  (,),{,},!,|,&  ) despres pasa en notacio post-fix
    public List<String> adapt(String query) {
        List<String> result = new ArrayList<>();
        int i = 0;
        boolean curlyOpened = false;
        while (i < query.length()) {
            char ch = query.charAt(i);
            //(,),{,},!,|,&
            if (isOperator(ch)) {
                boolean in = false;
                // { }
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
            // ' '
            else if (isBlank(ch)) {
                ++i;
            }
            // "
            else if (isQuotes(ch)) {
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
            //primera lletra de paraula que no començi amb "
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
        List<String> result2 = infixToPostfix(result);
        return result2;
    }

    //Metodes Auxiliars

    public boolean isOperator(String s) {
        return Objects.equals(s, "{") || Objects.equals(s, "}") || Objects.equals(s, "(") || Objects.equals(s, ")") || Objects.equals(s, "&") ||
                Objects.equals(s, "|") || Objects.equals(s, "!");
    }

    public boolean isOperator(char s) {
        return s == '{' || s == '}' || s == '(' || s == ')' || s == '&' || s == '|' || s == '!';
    }

    public boolean isCurlyBrace(char s) {
        return s == '{' || s == '}';
    }

    public boolean isBlank(char s) {
        return s == ' ';
    }

    public boolean isQuotes(char s) {
        return s == '"';
    }

    static int preced(String s) {
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

    public List<String> infixToPostfix(List<String>infix) {
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


    //Construeix l'Expression Tree
    public Node expressionTree(List<String> ListQuery) {
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

    public ConjuntDocuments calculate(ConjuntDocuments total) {
        Set<Frase> frases = calculateIm(root, total);
        Vector<Document> vd = new Vector<>();
        for (Frase f : frases) {
            Document d = total.getDocument(f.getTitolDoc(),f.getAutorDoc());
            vd.add(d);
        }
        ConjuntDocuments cd = new ConjuntDocuments(vd);
        return cd;
    }

    private Set<Frase> calculateIm(Node n, ConjuntDocuments total) {
        if (n != null) {
            Set<Frase> frases;
            if (n.esFulla()) {
                frases = total.obteFrasesContenen(n.data);
                return frases;
            }
            else {
                frases = operaSets(calculateIm(n.left, total),calculateIm(n.right, total),n.data,total);
            }
        }
        return null;
    }

    private Set<Frase> operaSets(Set<Frase> s1, Set<Frase> s2, String op, ConjuntDocuments total) {

        Set<Frase> result = new HashSet<>();
        //hacer COMPLEMENTARIO de m1
        if (s2 == null && Objects.equals(op, "!")) {
            result = total.VecToSet();
            result.removeAll(s2);
        }
        //hacer INTERSECCION m1 i m2
        else if (Objects.equals(op, "&")) {
            result = s1;
            result.retainAll(s2);
        }
        //hacer UNION m1 i m2
        else if (Objects.equals(op, "|")) {
            result = s1;
            result.addAll(s2);
        }
        return result;
    }





    /*
    // Imprimir arbre en postordre
    static void postorder(Node root)
    {
        if (root == null) {
            return;
        }
        postorder(root.left);
        System.out.print(root.data + " ");
        postorder(root.right);
    }
    
    // Driver code
    public static void main(String[] args) {
        String s = "{p1 p2 p3} & (\"hola adéu\" | pep) & !joan";
        List<String> query = adapt(s);
        System.out.println("Query Modificada: ");
        for (String value : query) {
            System.out.print(value + ",");
        }
        System.out.println("");
        System.out.println("Arbre (en postordre): ");
        Node root = expressionTree(query);
        postorder(root);
    }

     */

}
