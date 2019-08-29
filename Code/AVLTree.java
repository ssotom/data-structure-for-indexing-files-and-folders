/* 
  Title: AVL Tree 
  Author: Antonio081014 
  Date: 2013 Code 
  version: 1.0 
  Availability: https://gist.github.com/antonio081014/5939018 
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Antonio081014, adaptado por: Santiago Soto Marulanda, Jamerson Stive Correa, Andrés Sanchez Castrillón
 * @version 27/10/2017
 */
public class AVLTree<T extends Comparable<T>> {
    Node<T> root;
    
    /**
     * Este es el constructor del ÁrbolAVL 
     */
    public AVLTree() {
        root = null;
    }

    /**
     * Este metodo retorna la profundidad un nodo
     * @return la profundidad un nodo
     */
    private int depth(Node<T> node) {
        if (node == null)
            return 0;
        return node.getDepth();
        // 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
    }

    /**
     * Este metodo se encarga de insertar nodos en el Árbol
     * @param data: es el dato que se le asignara al nodo a insertar
     * @see insert()
     * @return el árbol con el nodo nuevo insertado
     */
    public Node<T> insert(T data) {
        root = insert(root, data);
        switch (balanceNumber(root)) {
         case 1:
            root = rotateLeft(root);
            break;
         case -1:
            root = rotateRight(root);
            break;
         default:
            break;
        }
        return root;
    }

    /**
     * Este metodo es un ayudante de insert y se encarga de realizar 
     * el proceso de inserción en el Árbol
     * @param node: es el nodo que se va a insertar en el Árbol
     * @param data: es el dato que tendrá el nodo insertado
     * @see balanceNumber()
     * @see rotateLeft()
     * @see rotateRight()
     * @return la inserción realizada al Árbol
     */
    public Node<T> insert(Node<T> node, T data) {
        if (node == null)
            return new Node<T>(data);
        if (node.getData().compareTo(data) > 0) {
            node = new Node<T>(node.getData(), insert(node.getLeft(), data),
                    node.getRight(),node.getSame());
                    // node.setLeft(insert(node.getLeft(), data));
        } else if (node.getData().compareTo(data) < 0) {
            // node.setRight(insert(node.getRight(), data));
            node = new Node<T>(node.getData(), node.getLeft(), insert(
                    node.getRight(), data),node.getSame());
        }
        else if (node.getData().compareTo(data) == 0){
            if(node.getSame() == null)
                node.setSame(new Node(data));
            else
                insert(node.getSame(),data);
        }
        // After insert the new node, check and rebalance the current node if
        // necessary.
        switch (balanceNumber(node)) {
         case 1:
            node = rotateLeft(node);
            break;
         case -1:
            node = rotateRight(node);
            break;
         default:
            return node;
        }
        return node;
    }

    /**
     * Este método se encarga de balancear el Árbol de forma
     * que el número de nodos a la derecha sea el mismo a la izquierda
     * @param node: es el nodo al que se le realiza la rotación
     * @see depth()
     * @return un cero después de ejecutarse
     */
    private int balanceNumber(Node<T> node) {
        int L = depth(node.getLeft());
        int R = depth(node.getRight());
        if (L - R >= 2)
            return -1;
        else if (L - R <= -2)
            return 1;
        return 0;
    }

    /**
     * Este metodo se encarga de rotar nodos hacia la izquierda
     * @param node: es el nodo al que se le realizara la rotación
     * @return la rotación a la izquierda realizada al Árbol
     */
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> q = node;
        Node<T> p = q.getRight();
        Node<T> c = q.getLeft();
        Node<T> a = p.getLeft();
        Node<T> b = p.getRight();
        q = new Node<T>(q.getData(), c, a, q.getSame());
        p = new Node<T>(p.getData(), q, b, p.getSame());
        return p;
    }
    
    /**
     * Este metodo se encarga de rotar nodos hacia la derecha
     * @param node: es el nodo a rotar
     * @return la rotación a la izquierda realizada al Árbol
     */
    private Node<T> rotateRight(Node<T> node) {
        Node<T> q = node;
        Node<T> p = q.getLeft();
        Node<T> c = q.getRight();
        Node<T> s = q.getSame();
        Node<T> a = p.getLeft();
        Node<T> b = p.getRight();
        Node<T> d = p.getSame();
        q = new Node<T>(q.getData(), b, c, q.getSame());
        p = new Node<T>(p.getData(), a, q, p.getSame());
        return p;
    }
    
    /**
     * Este metodo se encarga de realizar la búqueda de un dato en el Árbol
     * @param data: es el dato que se quiere buscar en el Árbol
     */
    public void search(T data) {
        Node<T> local = root;
        int cont = 0;
        long startTime = System.currentTimeMillis();

        while (local != null) {
            if (local.getData().compareTo(data) == 0){
                System.out.println(local.getData());
                cont++;
                if(local.getSame() !=null )
                    local = local.getSame();
                else
                {
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    System.out.println(cont + " resultados (" + estimatedTime +" ms)");
                    return;
                }
            }
            else if (local.getData().compareTo(data) > 0)
                local = local.getLeft();
            else
                local = local.getRight();
        }//fin del ciclo
        System.out.println("No se encontro ningun archivo");
    }
    
    /**
     * Este metodo convierte la raiz a un String 
    */
    public String toString() {
        return root.toString();
    }
    
    /**
     * Este metodo se encarga de dibujar el Árbol 
    */
    public void PrintTree() {
        root.level = 0;
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.println(node);
            int level = node.level;
            Node<T> left = node.getLeft();
            Node<T> right = node.getRight();
            if (left != null) {
                left.level = level + 1;
                queue.add(left);
            }
            if (right != null) {
                right.level = level + 1;
                queue.add(right);
            }
        }//fin ciclo
    }
}