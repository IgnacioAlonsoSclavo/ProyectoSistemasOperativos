package Clases.Funcionamiento;

import Clases.Principales.Defensa;
import Clases.Principales.MisilObjetivo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class CalculadorPrioridad {

    private LinkedList<MisilObjetivo> amenazasPendientes;
    private Semaphore defensasDisponibles;

    public CalculadorPrioridad(LinkedList<MisilObjetivo> amenazasPendientes, int cantidadDefensas) {
        this.amenazasPendientes = amenazasPendientes;
        this.defensasDisponibles = new Semaphore(cantidadDefensas);
    }

    public double calcular(MisilObjetivo misil) {
        EstrategiaPrioridad estrategia = seleccionarEstrategia();
        return estrategia.calcular(misil);
    }

    private EstrategiaPrioridad seleccionarEstrategia() {
        if (defensasDisponibles.availablePermits() >= amenazasPendientes.size()) {
            return new Estrategia1(amenazasPendientes, defensasDisponibles);
        } else {
            return new Estrategia2(amenazasPendientes, defensasDisponibles);
        }
    }
}