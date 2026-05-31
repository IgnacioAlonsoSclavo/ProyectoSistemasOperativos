package Clases;

import java.util.LinkedList;

import Interfaces.Estrategias;

public class EstrategiaDos implements Estrategias {
    private String nombre = "Estrategia Dos";

    public String GetNombreEstrategia() {
        return this.nombre;
    }

    @Override
    public MisilObjetivo Interceptar(LinkedList<MisilObjetivo> amenazasPendientes) {
        return amenazasPendientes.stream()
            .max((m1, m2) -> CalculadorPrioridad.Estrategia2(m1).compareTo(CalculadorPrioridad.Estrategia2(m2)))
            .orElse(null);
    }
}
