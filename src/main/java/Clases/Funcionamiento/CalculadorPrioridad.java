package Clases.Funcionamiento;

import Clases.Funcionamiento.Estrategias.Estrategia1;
import Clases.Funcionamiento.Estrategias.Estrategia2;
import Clases.Principales.MisilObjetivo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class CalculadorPrioridad {

    private LinkedList<MisilObjetivo> amenazasPendientes;
    private Semaphore defensasDisponibles;

    public CalculadorPrioridad(LinkedList<MisilObjetivo> amenazasPendientes, Semaphore defensasDisponibles) {
        this.amenazasPendientes = amenazasPendientes;
        this.defensasDisponibles = defensasDisponibles;
    }

    public EstrategiaPrioridad seleccionarEstrategia() {
        if (2 * defensasDisponibles.availablePermits() >= amenazasPendientes.size()) {
            return new Estrategia1(amenazasPendientes);
        } else {
            return new Estrategia2(amenazasPendientes);
        }
    }
}