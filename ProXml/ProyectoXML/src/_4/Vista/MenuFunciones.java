package _4.Vista;

import _5.Backend.Repositorio.EntrenamientoDao;
import _2.Logica.Funciones;
import _2.Logica.TablaEstadisticas;
import _3.Utilidad.ScannerMaster;
import java.util.Scanner;

public class MenuFunciones {
    private EntrenamientoDao entrenamientoDao = new EntrenamientoDao();
    private Funciones funciones = new Funciones(entrenamientoDao);
    Scanner scn = ScannerMaster.getScn();

    public void mostrarMenu(){
        int opcion;
        do {
            System.out.println("\n=== Gestión de Funciones ===");
            System.out.println("1. Mostrar suma de las duraciones");
            System.out.println("2. Mostrar media de las duraciones");
            System.out.println("3. Generar Estadísticas");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opción: ");

            opcion = scn.nextInt();
            scn.nextLine();

            switch (opcion){
                case 1 -> mostrarSumaDuraciones();
                case 2 -> mostrarMediaDuraciones();
                case 3 -> generarEstadisticas();
                case 4 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 4);
    }

    private void generarEstadisticas(){
        System.out.println("Generando estadísticas en formato CSV...");
        TablaEstadisticas tablaEstadisticas = new TablaEstadisticas(entrenamientoDao);
        tablaEstadisticas.generarTablas();

    }
    private void mostrarSumaDuraciones(){
        int sumaDuraciones = funciones.sumarDuracionEntrenamientos();
        System.out.println("La suma total de las duraciones es: " + sumaDuraciones + " minutos.");
    }

    private void mostrarMediaDuraciones() {
        double mediaDuraciones = funciones.calcularMediaDuracionEntrenamientos();
        System.out.println("La duración promedio de los entrenamientos es: " + mediaDuraciones + " minutos.");
    }
}
