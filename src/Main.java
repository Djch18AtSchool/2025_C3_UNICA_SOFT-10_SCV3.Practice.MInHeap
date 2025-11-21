import domain.MinHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Sistema de Min-Heap en Java ===");

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Insertar elemento (insert)");
            System.out.println("2. Eliminar mínimo (deleteMin)");
            System.out.println("3. Ver mínimo (peek)");
            System.out.println("4. Construir montículo desde arreglo (heapify)");
            System.out.println("5. Mostrar estado del montículo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            String input = scanner.nextLine();

            try {
                switch (input) {
                    case "1":
                        System.out.print("Ingrese el número a insertar: ");
                        int valToInsert = Integer.parseInt(scanner.nextLine());
                        minHeap.insert(valToInsert);
                        System.out.println("Insertado: " + valToInsert);
                        break;

                    case "2":
                        if (minHeap.isEmpty()) {
                            System.out.println("Error: El montículo está vacío.");
                        } else {
                            int removed = minHeap.deleteMin();
                            System.out.println("Elemento eliminado (raíz): " + removed);
                        }
                        break;

                    case "3":
                        if (minHeap.isEmpty()) {
                            System.out.println("Error: El montículo está vacío.");
                        } else {
                            System.out.println("Elemento en la raíz (mínimo): " + minHeap.peek());
                        }
                        break;

                    case "4":
                        System.out.println("Ingrese números separados por coma (ej: 4,10,3,5,1):");
                        String rawArray = scanner.nextLine();
                        List<Integer> list = new ArrayList<>();
                        String[] tokens = rawArray.split(",");

                        for (String token : tokens) {
                            list.add(Integer.parseInt(token.trim()));
                        }

                        minHeap.heapify(list);
                        System.out.println("Arreglo convertido en Min-Heap exitosamente.");
                        break;

                    case "5":
                        minHeap.printHeap();
                        break;

                    case "6":
                        running = false;
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}