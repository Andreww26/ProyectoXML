package _1.Controlador;
import _5.Backend.Modelo.Entrenamiento;
import _5.Backend.Repositorio.EntrenamientoDao;
import _3.Utilidad.ScannerMaster;
import _4.Vista.MenuFunciones;

import java.util.Scanner;

public class ControladorMenu {
    private EntrenamientoDao entrenamientoDao = new EntrenamientoDao();
    private Scanner scn = ScannerMaster.getScn();


    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== Gestión de Fichero XML ===");
            System.out.println("1. Crear Entrenamiento");
            System.out.println("2. Leer Entrenamientos");
            System.out.println("3. Actualizar Entrenamiento");
            System.out.println("4. Eliminar Entrenamiento");
            System.out.println("5. Mostrar menu de funciones");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scn.nextInt();
            scn.nextLine();

            switch (opcion) {
                case 1 -> agregarEntrenamiento();
                case 2 -> leerEntrenamiento();
                case 3 -> actualizarEntrenamiento();
                case 4 -> eliminarEntrenamiento();
                case 5 -> new MenuFunciones().mostrarMenu();
                case 6 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private void agregarEntrenamiento() {
        System.out.print("Ingrese ID del entrenamiento: ");
        int id = scn.nextInt();
        scn.nextLine();

        System.out.print("Ingrese el nombre del entrenamiento: ");
        String nombre = scn.nextLine();

        System.out.print("Ingrese la duración del entrenamiento (en minutos): ");
        int duracion = scn.nextInt();
        scn.nextLine();

        System.out.print("Ingrese el nivel del entrenamiento: ");
        String nivel = scn.nextLine();

        Entrenamiento entrenamiento = new Entrenamiento(id, nombre, duracion, nivel);
        entrenamientoDao.agregarEntrenamiento(entrenamiento);
        System.out.println("Entrenamiento agregado con éxito.");

    }

    private void leerEntrenamiento() {
        entrenamientoDao.leerTodos().forEach(System.out::println);
    }

    private void actualizarEntrenamiento() {
        System.out.print("Ingrese ID del entrenamiento: ");
        int id = scn.nextInt();
        scn.nextLine();

        System.out.print("Ingrese el nuevo nombre del entrenamiento: ");
        String nombre = scn.nextLine();

        System.out.print("Ingrese la nueva duración del entrenamiento (en minutos): ");
        int duracion = scn.nextInt();
        scn.nextLine();

        System.out.print("Ingrese el nuevo nivel del entrenamiento: ");
        String nivel = scn.nextLine();

        Entrenamiento entrenamiento = new Entrenamiento(id, nombre, duracion, nivel);

        entrenamientoDao.actualizarEntrenamiento(id, entrenamiento);
        System.out.println("Entrenamiento actualizado con éxito.");
    }

    private void eliminarEntrenamiento() {
        System.out.print("Ingrese ID del entrenamiento: ");
        int id = scn.nextInt();
        entrenamientoDao.eliminarEntrenamiento(id);
        System.out.println("Entrenamiento borrado con exito.");
    }


}