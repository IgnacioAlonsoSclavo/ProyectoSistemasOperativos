package Clases.Funcionamiento;
import java.util.LinkedList;

import Clases.Principales.MisilObjetivo;
import Interfaces.*;

public class EstrategiaUno implements Estrategias {
    private String nombre = "Estrategia Uno";

    public String GetNombreEstrategia() {
        return this.nombre;
    }

    @Override
    public MisilObjetivo Interceptar(LinkedList<MisilObjetivo> amenazasPendientes) {
        return amenazasPendientes.stream()
            .max((m1, m2) -> CalculadorPrioridad.Estrategia1(m1).compareTo(CalculadorPrioridad.Estrategia1(m2)))
            .orElse(null);
    }
}
