package arbolbinario;

/**
 * Nodo del árbol binario: guarda un dato entero y las referencias
 * a su hijo izquierdo y a su hijo derecho.
 */
public class Nodo {

    int dato;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}
