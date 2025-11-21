package domain;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

    // Lista que almacena los elementos del montículo
    private ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    // --- Helper Methods (Métodos Auxiliares) ---

    // Obtiene el índice del padre: (i - 1) / 2
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    // Obtiene el índice del hijo izquierdo: 2i + 1
    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    // Obtiene el índice del hijo derecho: 2i + 2
    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    // Intercambia dos elementos en la lista dado sus índices
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    // --- Core Methods (Métodos Esenciales) ---

    /**
     * Inserts a new element into the heap and restores the heap property.
     * @param element The integer to add.
     */
    public void insert(int element) {
        // 1. Insertar al final del arreglo
        heap.add(element);
        
        // 2. Realizar upHeapify desde el último índice
        upHeapify(heap.size() - 1);
    }

    /**
     * Restores the min-heap property by bubbling up the element at the given index.
     * @param index The index of the element to bubble up.
     */
    private void upHeapify(int index) {
        // Mientras el nodo no sea la raíz Y sea menor que su padre
        while (index > 0 && heap.get(index) < heap.get(getParentIndex(index))) {
            // Intercambiar con el padre
            swap(index, getParentIndex(index));
            // Actualizar el índice actual al del padre
            index = getParentIndex(index);
        }
    }

    /**
     * Removes and returns the root (minimum element) of the heap.
     * @return The minimum element.
     * @throws IllegalStateException if the heap is empty.
     */
    public int deleteMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("El montículo está vacío (Heap is empty).");
        }

        // 1. Guardar el valor de la raíz (el mínimo)
        int min = heap.get(0);

        // 2. Mover el último elemento a la raíz
        int lastElement = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, lastElement);
            // 3. Realizar downHeapify desde la raíz para restaurar el orden
            downHeapify(0);
        }

        return min;
    }

    /**
     * Restores the min-heap property by bubbling down the element at the given index.
     * @param index The index to start bubbling down from.
     */
    private void downHeapify(int index) {
        int smallest = index;
        int leftChild = getLeftChildIndex(index);
        int rightChild = getRightChildIndex(index);

        // Verificar si el hijo izquierdo existe y es menor que el nodo actual
        if (leftChild < heap.size() && heap.get(leftChild) < heap.get(smallest)) {
            smallest = leftChild;
        }

        // Verificar si el hijo derecho existe y es menor que el más pequeño encontrado hasta ahora
        if (rightChild < heap.size() && heap.get(rightChild) < heap.get(smallest)) {
            smallest = rightChild;
        }

        // Si el más pequeño no es el nodo actual, intercambiar y continuar recursiva o iterativamente
        if (smallest != index) {
            swap(index, smallest);
            // Llamada recursiva para asegurar que el sub-árbol cumpla la propiedad
            downHeapify(smallest);
        }
    }

    /**
     * Returns the minimum element without removing it.
     * @return The root element.
     */
    public int peek() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("El montículo está vacío.");
        }
        return heap.get(0);
    }

    /**
     * Converts an unordered list into a valid min-heap.
     * @param newElements List of integers to heapify.
     */
    public void heapify(List<Integer> newElements) {
        // 1. Actualizar el contenido del arreglo actual con los nuevos datos
        this.heap = new ArrayList<>(newElements);

        // 2. Aplicar el principio de ordenamiento (Build Heap)
        // Se inicia desde el último nodo que no es hoja (índice n/2 - 1) hacia arriba
        int startIdx = (heap.size() / 2) - 1;

        for (int i = startIdx; i >= 0; i--) {
            downHeapify(i);
        }
    }

    // Método auxiliar para imprimir el estado actual (para debug o visualización)
    public void printHeap() {
        System.out.println("Estado actual del Heap: " + heap.toString());
    }
    
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}