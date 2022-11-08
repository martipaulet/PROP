package Domini;

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


    //Estructura arbre
    private static class Node {
        String data;
        Node left, right;

        //Crear nou node
        private Node (String s) {
            data = s;
            left = right = null;
        }

        private String getData() {
            return data;
        }
    }


    //pre: la query segueix sintaxis correcta seguint l'exemple de l'enunciat: {p1 p2 p3} & ("hola adéu" | pep) & !joan
    //modifica la query introduida en un Vector suprimint espais i "" i separant paraules de signes (  (,),{,},!,|,&  ) despres pasa en notacio post-fix
    public static List<String> adapt(String query) {

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
        return result;
    }

    //Metodes Auxiliars

    public static boolean isOperator(String s) {
        return Objects.equals(s, "{") || Objects.equals(s, "}") || Objects.equals(s, "(") || Objects.equals(s, ")") || Objects.equals(s, "&") ||
                Objects.equals(s, "|") || Objects.equals(s, "!");
    }

    public static boolean isOperator(char s) {
        return s == '{' || s == '}' || s == '(' || s == ')' || s == '&' || s == '|' || s == '!';
    }

    public static boolean isCurlyBrace(char s) {
        return s == '{' || s == '}';
    }

    public static boolean isBlank(char s) {
        return s == ' ';
    }

    public static boolean isQuotes(char s) {
        return s == '"';
    }

    int preced(String s) {
        if(Objects.equals(s, "!")) {
            return 3;
        }
        else if(Objects.equals(s, "&")) {
            return 2;
        }
        else if(Objects.equals(s, "|")) {
            return 1;
        }
        else return -1;
    }
    /*
    public List<String> infixToPostfix(List<String>infix) {

    }


     */


    //Construeix l'Expression Tree
    public static Node expressionTree(List<String> ListQuery) {

        Stack<Node> st = new Stack<>();
        Stack<String> sts = new Stack<>();
        Node t = null;
        Node t1 = null, t2 = null;

        //donar prioritats (indexar per ascii code)
        int[] p = new int[128];
        p['!'] = 3;
        p['&'] = 2;
        p['|'] = 1;
        p[')'] = 0;

        for (String s : ListQuery) {
            if (s == "(") {
                // Push "(" o al stack de string
                sts.add(s);
                System.out.println("pila "+s);
            }

            // Push strings al stack de node
            else if (!isOperator(s)) {
                t = new Node(s);
                st.add(t);
                System.out.println("node "+s);
            }

            //Si es un operator llavors es un string format per 1char
            else if (isOperator(s) && p[s.charAt(0)] > 0) {
                System.out.println("pila "+s);

                // Si un operator de menor o igual associativitat apareix
                while (!sts.isEmpty() && sts.peek() != "("
                        && ((p[(sts.peek().charAt(0))] >= p[s.charAt(0)]))) {

                    // Obtindre i eliminar el top del stack de string
                    // from the character stack
                    t = new Node(sts.peek());
                    sts.pop();

                    // Get and remove the top element
                    // from the node stack
                    if (t.getData() != "!") {
                        t1 = st.peek();
                        st.pop();
                    }

                    // Get and remove the currently top
                    // element from the node stack
                    t2 = st.peek();
                    st.pop();


                    // Actualitzar arbre
                    t.left = t2;
                    if (t.getData() != "!") t.right = t1;

                    // Push node al stack de node
                    st.add(t);
                }

                // Push s al stack de string
                sts.push(s);

            } else if (s == ")") {
                System.out.println("pila"+s);
                while (!sts.isEmpty() && sts.peek() != "(") {
                    t = new Node(sts.peek());
                    sts.pop();
                    if (t.getData() != "!") {
                        t1 = st.peek();
                        st.pop();
                    }

                    t2 = st.peek();
                    st.pop();
                    t.left = t2;
                    if (t.getData() != "!") t.right = t1;
                    st.add(t);
                }
                sts.pop();
            }
        }
        t = st.peek();
        return t;
    }


    // Imprimir arbre en postordre
    static void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.data);
        }
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
}
