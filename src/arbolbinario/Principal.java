package arbolbinario;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programa principal: menú para ingresar una serie de elementos en el
 * árbol binario y mostrar los recorridos inorden, preorden y posorden.
 */
public class Principal {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        ArbolBinario arbol = new ArbolBinario();
        int opcion = 0;

        System.out.println("===== ÁRBOL BINARIO DE BÚSQUEDA =====");

        while (opcion != 8) {
            System.out.println();
            System.out.println("1. Ingresar una serie de elementos");
            System.out.println("2. Recorrido inorden");
            System.out.println("3. Recorrido preorden");
            System.out.println("4. Recorrido posorden");
            System.out.println("5. Buscar un elemento");
            System.out.println("6. Eliminar un elemento");
            System.out.println("7. Ver el árbol");
            System.out.println("8. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = leer.nextInt();
                leer.nextLine();

                switch (opcion) {
                    case 1:
                        ingresarSerie(leer, arbol);
                        break;
                    case 2:
                        mostrarRecorrido(arbol, "Inorden (izq - raíz - der)", arbol.inorden());
                        break;
                    case 3:
                        mostrarRecorrido(arbol, "Preorden (raíz - izq - der)", arbol.preorden());
                        break;
                    case 4:
                        mostrarRecorrido(arbol, "Posorden (izq - der - raíz)", arbol.posorden());
                        break;
                    case 5:
                        System.out.print("Valor a buscar: ");
                        int buscado = leer.nextInt();
                        leer.nextLine();
                        System.out.println(arbol.buscar(buscado)
                                ? "El " + buscado + " sí está en el árbol."
                                : "El " + buscado + " no está en el árbol.");
                        break;
                    case 6:
                        System.out.print("Valor a eliminar: ");
                        int borrar = leer.nextInt();
                        leer.nextLine();
                        if (arbol.eliminar(borrar)) {
                            System.out.println("Se eliminó el " + borrar + ".");
                            System.out.println("Inorden actual: " + arbol.inorden());
                        } else {
                            System.out.println("El " + borrar + " no existe en el árbol.");
                        }
                        break;
                    case 7:
                        if (arbol.estaVacio()) {
                            System.out.println("El árbol está vacío.");
                        } else {
                            System.out.println("Árbol (raíz a la izquierda, gire la cabeza a la izquierda):");
                            System.out.print(arbol.dibujar());
                            System.out.println("Total de nodos: " + arbol.getCantidad());
                        }
                        break;
                    case 8:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opción no válida, elija entre 1 y 8.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debe escribir un número entero.");
                leer.nextLine();   // limpia la entrada incorrecta
                opcion = 0;
            }
        }
        leer.close();
    }

    /** Lee varios números separados por espacios o comas y los inserta uno a uno. */
    private static void ingresarSerie(Scanner leer, ArbolBinario arbol) {
        System.out.print("Escriba los elementos separados por espacios o comas: ");
        String linea = leer.nextLine().trim();
        if (linea.isEmpty()) {
            System.out.println("No se ingresó ningún elemento.");
            return;
        }
        String[] partes = linea.split("[,\\s]+");
        for (String parte : partes) {
            try {
                int valor = Integer.parseInt(parte);
                if (arbol.insertar(valor)) {
                    System.out.println("  Insertado: " + valor);
                } else {
                    System.out.println("  " + valor + " ya existe, no se repite.");
                }
            } catch (NumberFormatException e) {
                System.out.println("  '" + parte + "' no es un número entero, se omite.");
            }
        }
        System.out.println("Nodos en el árbol: " + arbol.getCantidad());
    }

    private static void mostrarRecorrido(ArbolBinario arbol, String nombre, String resultado) {
        if (arbol.estaVacio()) {
            System.out.println("El árbol está vacío, primero ingrese elementos.");
        } else {
            System.out.println(nombre + ": " + resultado);
        }
    }
}
