package Domini;

import java.util.Objects;
import java.util.Stack;
import java.util.Vector;

public class ExpressionTree {

    //Estructura arbre
    static class Node {
        String data;
        Node left, right;
    }

    //Crear nou node
    static Node newNode(String s) {
        Node n = new Node();
        n.data = s;
        n.left = n.right = null;
        return n;
    }

    //modifica la query introduida en un Vector suprimint espais i "" i separant paraules de signes (  (,),{,},!,|,&  ) despres pasa en notacio post-fix
    public static Vector<String> adapt(String query) {

        Vector<String> result = new Vector<String>();
        int i = 0;
        boolean curlyOpened = false;
        while (i < query.length()) {
            char ch = query.charAt(i);
            //System.out.println(ch);

            //(,),{,},!,|,&
            if (isOperator(ch)) {
                if (isCurlyBrace(ch)) {
                    curlyOpened = !curlyOpened;
                }
                //transforma ch en string
                result.add("" + ch);
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
        return Objects.equals(s, "{") || Objects.equals(s, "}") || Objects.equals(s, "(") ||
                Objects.equals(s, ")") || Objects.equals(s, "&") || Objects.equals(s, "|") || Objects.equals(s, "!");
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

    //Construeix l'Expression Tree
    public static Node expressionTree(Vector<String> vecQuery) {

        Stack<Node> st = new Stack<Node>();
        Stack<String> sts = new Stack<String>();
        Node t = null;
        Node t1, t2;

        //donar prioritats (indexar per ascii code)
        int[] p = new int[128];
        p['!'] = 3;
        p['&'] = 2;
        p['|'] = 1;
        p[')'] = p['}'] = 0;

        for (String s : vecQuery) {
            if (Objects.equals(s, "(") || Objects.equals(s, "{")) {
                // Push "(" o "{" al stack de string
                sts.add(s);
            }

            // Push strings al stack de node
            else if (!isOperator(s)) {
                t = newNode(s);
                st.add(t);
            }

            //Si es un operator llavors es un string format per 1char
            else if (isOperator(s) && p[s.charAt(0)] > 0) {
                // Si un operator de menor o igual associativitat apareix
                while (!sts.isEmpty() && (!Objects.equals(sts.peek(), "(") && !Objects.equals(sts.peek(), "{"))
                        && ((!Objects.equals(s, "!") && (isOperator(sts.peek()) && (p[(sts.peek().charAt(0))] >= p[s.charAt(0)])))
                        || (Objects.equals(s, "!")
                        && (isOperator(sts.peek()) && (p[(sts.peek().charAt(0))] > p[s.charAt(0)]))))) {

                    // Obtindre i eliminar el top del stack de string
                    // from the character stack

                    t = newNode(sts.peek());
                    sts.pop();

                    // Get and remove the top element
                    // from the node stack
                    t1 = st.peek();
                    st.pop();

                    // Get and remove the currently top
                    // element from the node stack
                    t2 = st.peek();
                    st.pop();

                    // Actualitzar arbre
                    t.left = t2;
                    t.right = t1;

                    // Push node al stack de node
                    st.add(t);
                }

                // Push s al stack de string
                sts.push(s);

            } else if (Objects.equals(s, ")") || Objects.equals(s, "}")) {
                while (!sts.isEmpty() && (!Objects.equals(sts.peek(), "(") && !Objects.equals(sts.peek(), "{"))) {
                    t = newNode(sts.peek());
                    sts.pop();
                    t1 = st.peek();
                    st.pop();
                    t2 = st.peek();
                    st.pop();
                    t.left = t2;
                    t.right = t1;
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

}



    /*
    // Driver code
    public static void main(String[] args)
    {
        String s = "{p1 p2 p3} & (\"hola adéu\" | pep) & !joan";

        Vector<String> query = adapt(s);
        System.out.println("Query Modificada: ");
        for (String value : query) {
            System.out.print(value + " , ");
        }
        System.out.println("");
        System.out.println("Arbre (en postordre): ");
        Node root = expressionTree(query);
        postorder(root);
    }
     */
