/* 
  Title: AVL tree
  Author: Antonio081014 
  Date: 2013 Code 
  version: 1.0 
  Availability: https://gist.github.com/antonio081014/5939018 
 */

/**
 * @author Antonio081014, adaptado por: Santiago Soto Marulanda, Jamerson Stive Correa, Andrés Sanchez Castrillón
 * @version 27/10/2017
*/

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    //atributos
    private T data;
    private Node<T> left;
    private Node<T> right;
    private Node<T> same;
    public int level;
    private int depth;

    /**
     * Este es el constructor por defecto de Node
     * @param data: dato del nodo
     */
    public Node(T data) {
        this(data, null, null,null);
    }
    
    /**
     * Este es el constructor de la clase Node
     * @param data: es el tipo de dato que contendra el nodo
     * @param left: es el nodo a la izquierdo del árbol
     * @param right: es el nodo a la derecha del árbol
     * @param same:
     */
    public Node(T data, Node<T> left, Node<T> right, Node<T> same) {
        super();
        this.data = data;
        this.left = left;
        this.right = right;
        this.same = same;
        if (left == null && right == null)
            setDepth(1);
        else if (left == null)
            setDepth(right.getDepth() + 1);
        else if (right == null)
            setDepth(left.getDepth() + 1);
        else
            setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
    }
    
    /**
     * Este metodo se encarga de mostrar el dato que posee un nodo
     * @return el dato que posee el nodo
     */
    public T getData() {
        return data;
    }

    /**
     * Este metodo se encarga modificar el dato que posee un nodo
     * @param data: es el nuevo dato del nodo
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Este metodo se encarga de retornar el nodo izquierdo
     * @return el nodo izquierdo
     */
    public Node<T> getLeft() {
        return left;
    }
    
    /**
     * Este metodo se encarga de modificar el nodo izquierdo
     * @param left: nuevo nodo izquierdo
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }
    
    /**
     * Este metodo se encarga deretornar el nodo de la derecha  
     * @return nodo de la derecha 
     */
    public Node<T> getRight() {
        return right;
    }
    
    /**
     * Este metodo se encarga de modificar el nodo de la derecha
     * @param right: el nuevo nodo de la derecha
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }
    
    /**
     * Este metodo se encarga de retornar el nodo igual
     * @return el nodo igual
     */
    public Node<T> getSame() {
        return same;
    }
    
    /**
     * este metodo se encarga de modificar el nodo igual
     * el mismo dato que otro nodo
     * @param same: el nuevo nodo igual
     */
    public void setSame(Node<T> same) {
        this.same = same;
    }

    /**
     * @return la profundidad
     */
    public int getDepth() {
        return depth;
    }

    /**
     * modifica el valor de la profundidad de un nodo
     * @param depth: profundidad del nodo
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    /**
     * Compara dos nodos
     * @param o: nodo a comparar
     */
    @Override
    public int compareTo(Node<T> o) {
        return this.data.compareTo(o.data);
    }
    
    /**
     * Imprime un nodo
     */
    @Override
    public String toString() {
        return "Level " + level + ": " + data;
    }

}