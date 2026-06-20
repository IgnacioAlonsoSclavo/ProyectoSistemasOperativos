package Clases.Funcionamiento.Estrategias;

import Clases.Funcionamiento.EstrategiaPrioridad;
import Clases.Principales.MisilObjetivo;

import java.util.LinkedList;

public class Estrategia1 extends EstrategiaPrioridad {

    public Estrategia1(LinkedList<MisilObjetivo> amenazasPendientes) {
        super(amenazasPendientes);
    }

    @Override
    public int getMaxCola() {
        return 2;
    }

    @Override
    public double calcular(MisilObjetivo misil) {
        if (!hayTiempoDeRecargar(misil)) {
            return Double.MAX_VALUE;
        }
        return 1.0 / Math.max(misil.GetTiempoImpacto(), 1);
    }

    @Override
    public String getNombre() {
        return "E1 - Urgencia (menor tiempo de impacto)";
    }
}