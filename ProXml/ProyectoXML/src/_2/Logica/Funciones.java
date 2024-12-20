package _2.Logica;
import _5.Backend.Modelo.Entrenamiento;
import _5.Backend.Repositorio.EntrenamientoDao;

import java.util.ArrayList;


public class Funciones {
    private EntrenamientoDao funciones;

    public Funciones(EntrenamientoDao funciones) {
        this.funciones = funciones;
    }
    public Funciones(){

    }

    public int sumarDuracionEntrenamientos() {
        ArrayList<Entrenamiento> entrenamientos = funciones.leerTodos();
        return entrenamientos.stream().mapToInt(Entrenamiento::getDuracion).sum();
    }
    public double calcularMediaDuracionEntrenamientos() {
        ArrayList<Entrenamiento> entrenamientos = funciones.leerTodos();
        if (entrenamientos.isEmpty()) {
            System.out.println("No hay entrenamientos registrados.");
            return 0;
        }
        return entrenamientos.stream().mapToInt(Entrenamiento::getDuracion).average().orElse(0);
    }
}
