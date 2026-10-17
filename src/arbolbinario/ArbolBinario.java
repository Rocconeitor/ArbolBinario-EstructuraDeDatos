package arbolbinario;

/**
 * Árbol binario de búsqueda (ABB).
 * Regla: los valores menores van a la izquierda y los mayores a la derecha.
 * La inserción, la búsqueda, la eliminación y los tres recorridos
 * se resuelven con métodos recursivos.
 */
public class ArbolBinario {

    private Nodo raiz;
    private int cantidad;

    public ArbolBinario() {
        raiz = null;
        cantidad = 0;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int getCantidad() {
        return cantidad;
    }

    // ---------------------- INSERCIÓN ----------------------

    /** Inserta un valor. Devuelve false si el valor ya existía. */
    public boolean insertar(int valor) {
        if (buscar(valor)) {
            return false;               // no se admiten repetidos
        }
        raiz = insertarRec(raiz, valor);
        cantidad++;
        return true;
    }

    private Nodo insertarRec(Nodo actual, int valor) {
        if (actual == null) {           // caso base: lugar libre encontrado
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        return actual;
    }

    // ---------------------- BÚSQUEDA ----------------------

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Nodo actual, int valor) {
        if (actual == null) {
            return false;
        }
        if (valor == actual.dato) {
            return true;
        }
        return valor < actual.dato
                ? buscarRec(actual.izquierdo, valor)
                : buscarRec(actual.derecho, valor);
    }

    // ---------------------- ELIMINACIÓN ----------------------

    /** Elimina un valor. Devuelve false si no estaba en el árbol. */
    public boolean eliminar(int valor) {
        if (!buscar(valor)) {
            return false;
        }
        raiz = eliminarRec(raiz, valor);
        cantidad--;
        return true;
    }

    private Nodo eliminarRec(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRec(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRec(actual.derecho, valor);
        } else {
            // Caso 1: nodo hoja
            if (actual.izquierdo == null && actual.derecho == null) {
                return null;
            }
            // Caso 2: un solo hijo, el hijo ocupa su lugar
            if (actual.izquierdo == null) {
                return actual.derecho;
            }
            if (actual.derecho == null) {
                return actual.izquierdo;
            }
            // Caso 3: dos hijos, se reemplaza por el sucesor inorden
            // (el menor del subárbol derecho) y luego se borra ese sucesor
            int sucesor = menorValor(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRec(actual.derecho, sucesor);
        }
        return actual;
    }

    private int menorValor(Nodo actual) {
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    // ---------------------- RECORRIDOS ----------------------

    /** Inorden: izquierdo - raíz - derecho (sale ordenado de menor a mayor). */
    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inordenRec(raiz, sb);
        return sb.toString().trim();
    }

    private void inordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            inordenRec(actual.izquierdo, sb);
            sb.append(actual.dato).append(" ");
            inordenRec(actual.derecho, sb);
        }
    }

    /** Preorden: raíz - izquierdo - derecho (sirve para copiar el árbol). */
    public String preorden() {
        StringBuilder sb = new StringBuilder();
        preordenRec(raiz, sb);
        return sb.toString().trim();
    }

    private void preordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            sb.append(actual.dato).append(" ");
            preordenRec(actual.izquierdo, sb);
            preordenRec(actual.derecho, sb);
        }
    }

    /** Posorden: izquierdo - derecho - raíz (sirve para liberar o borrar el árbol). */
    public String posorden() {
        StringBuilder sb = new StringBuilder();
        posordenRec(raiz, sb);
        return sb.toString().trim();
    }

    private void posordenRec(Nodo actual, StringBuilder sb) {
        if (actual != null) {
            posordenRec(actual.izquierdo, sb);
            posordenRec(actual.derecho, sb);
            sb.append(actual.dato).append(" ");
        }
    }

    // ---------------------- VISUALIZACIÓN ----------------------

    /** Dibuja el árbol de lado: la raíz a la izquierda y los hijos derechos arriba. */
    public String dibujar() {
        StringBuilder sb = new StringBuilder();
        dibujarRec(raiz, 0, sb);
        return sb.toString();
    }

    private void dibujarRec(Nodo actual, int nivel, StringBuilder sb) {
        if (actual != null) {
            dibujarRec(actual.derecho, nivel + 1, sb);
            sb.append("      ".repeat(nivel)).append(actual.dato).append("\n");
            dibujarRec(actual.izquierdo, nivel + 1, sb);
        }
    }
}
