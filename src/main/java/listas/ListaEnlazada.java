package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 *    Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test). 
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 *    java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return cantidad ==0;

    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return cantidad;

    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        //               y actualice la cabeza y la cantidad.
        //1. crea el nodo

        Nodo<T> nuevoNodo = new Nodo<T>(dato);

        //2. CONECTAR EL NUEVO NODO A CABEZA

        nuevoNodo.setSiguiente(this.cabeza);
        //3. Cambiar la cabeza
        this.cabeza= nuevoNodo;
        //4. aumentar la cantidad
        this.cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        //               si no, recorra hasta el último nodo y enlace el nuevo.
        if (estaVacia()) {
            agregarAlInicio(dato);
            return; 
        }

        // se comienza desde la cabeza
        Nodo <T> puntero = this.cabeza;
        //se encuentra el ultimo nodo 
        while(puntero.getSiguiente() != null){
            puntero = puntero.getSiguiente();
        }
        //se crea el nuevo nodo y se lo enlaza al ultimo nodo
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        puntero.setSiguiente(nuevoNodo);
        //se actualiza la cantidad
        this.cantidad++;

    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {
        if(estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        return cabeza.getDato();
 
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        if (estaVacia()){
            throw new NoSuchElementException("La lista está vacía");

        }
        Nodo<T> puntero = cabeza;
        while (puntero.getSiguiente() != null){
            puntero = puntero.getSiguiente();
        }
        return puntero.getDato();

    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {//indice se detiene cuando alcanza el nodo en la posicion deseada
        //el indice no puede ser negativo ni mayor o igual a la cantidad de elementos
        if (indice< 0 || indice >= cantidad){
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        Nodo <T> puntero = cabeza;//comienza desde la cabeza

        for (int i = 0; i < indice; i++){
            puntero = puntero.getSiguiente();
        }
        return puntero.getDato();
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        //recorrer desde la cabeza
        Nodo<T> puntero = cabeza;
        int indice = 0;

        while (puntero != null) {
            // se compara el dato del nodo con el dato buscado
            if (Objects.equals(puntero.getDato(), dato)){
                return indice;
            }

            //si no coincie va al otro nodo
            puntero =puntero.getSiguiente();

            //actualiza el indice
            indice++;

        }
        return -1; // no se encontró el dato

    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
            if (indice<0 || indice>= cantidad){
                throw new IndexOutOfBoundsException("Indice fuera de rango");
            }
            if (indice == 0 ){
                agregarAlInicio(dato);
                return;
            }
            Nodo <T> anterior = cabeza;

            for (int i = 0; i < indice-1; i++){
                anterior = anterior.getSiguiente();
            }
            //crear el nodo que se inserta
            Nodo<T> nuevoNodo = new Nodo<T>(dato);
            // se crea el nuevo nodo con el nodo que esta despues del anterior
            nuevoNodo.setSiguiente(anterior.getSiguiente());
            // se enlaza el nodo anterior con el nuevo nodo
            anterior.setSiguiente(nuevoNodo);
            cantidad++;//aumenta la cantida ya que se agrego uno nuevo elemento


    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        if (indice < 0 || indice >= cantidad){
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }

        Nodo<T> puntero = cabeza;
        for (int i =0; i< indice; i++){
            puntero = puntero.getSiguiente();
        }
        T datoAnterior = puntero.getDato();//guardamos el dato que tien el nodo anterior 
        puntero.setDato(dato);// remplazamos el dato del nodo con el nuevo dato
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        if(estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        //se guarda el dato antes de que se modifique la cabeza
        T datoEliminado =   cabeza.getDato();
        //se mueve el nodo que estaba en la cabeza al segundo nodo, que ahora será la nueva cabeza
        cabeza = cabeza.getSiguiente();
        cantidad--; //se disminuye la cantidad
        return datoEliminado;
    }

    /**
     * Elimina y retorna el último elemento.
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        if (estaVacia()){
            throw new NoSuchElementException("La lista está vacía");
        }
        if (cantidad == 1){
            return eliminarPrimero();
        }
        Nodo <T> puntero = cabeza;
        while (puntero.getSiguiente().getSiguiente() != null) {
            puntero = puntero.getSiguiente();
        }
        // antes de desconectar el último nodo, lo guardamos
        T datoEliminado = puntero.getSiguiente().getDato();
        puntero.setSiguiente(null); // desconectamos el último nodo
        cantidad--; // disminuimos la cantidad
        return datoEliminado;
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {

        if (indice< 0|| indice>=cantidad){
            throw new IndexOutOfBoundsException("Indice fuera de rango");
        }
        if (indice == 0){
            return eliminarPrimero();
        }
        if (indice == cantidad - 1){
            return eliminarUltimo();
        }
        Nodo<T> puntero = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            puntero = puntero.getSiguiente();
        }
        T datoEliminado = puntero.getSiguiente().getDato();
        puntero.setSiguiente(puntero.getSiguiente().getSiguiente());
        cantidad--;
        return datoEliminado;
    }

    /**
     * Elimina la primera aparición del dato.
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {

        //se busca la aparicion del primer valor 
        int indice = indiceDe(dato);
        if (indice == -1) {
            return false; // el dato no estaba en la lista
        }
        eliminar(indice);
        return true; // se eliminó el dato
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        cabeza = null;
        cantidad = 0;

    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Nodo<T> puntero = cabeza;
        while (puntero != null) {
            sb.append(puntero.getDato());
            if (puntero.getSiguiente() != null) {
                sb.append(", ");
            }
            puntero = puntero.getSiguiente();
        }
        sb.append("]");
        return sb.toString();
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {

            Nodo<T> anterior = null;
            Nodo<T> actual = cabeza;
            Nodo<T> siguiente;

            while (actual != null) {
                siguiente = actual.getSiguiente();

                actual.setSiguiente(anterior);

                anterior = actual;
                actual = siguiente;
            }

            cabeza = anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        Nodo<T> lento = cabeza;
        Nodo<T> rapido = cabeza;

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }

        return lento.getDato();
    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            Nodo<T> anterior = actual;
            Nodo<T> siguiente = actual.getSiguiente();

            while (siguiente != null) {
                if (Objects.equals(actual.getDato(), siguiente.getDato())) {
                    // Eliminar el nodo repetido.
                    anterior.setSiguiente(siguiente.getSiguiente());
                    cantidad--;

                    // Avanzar al siguiente nodo sin mover anterior.
                    siguiente = anterior.getSiguiente();
                } else {
                    anterior = siguiente;
                    siguiente = siguiente.getSiguiente();
                }
            }

            actual = actual.getSiguiente();
        }
    }
}
