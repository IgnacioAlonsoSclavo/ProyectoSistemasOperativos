package Clases.Funcionamiento;

import Clases.Principales.MisilObjetivo;
import java.util.LinkedList;

public abstract class EstrategiaPrioridad {

    public static final int TIEMPO_RECARGA = 3;

    private LinkedList<MisilObjetivo> amenazasPendientes;

    public EstrategiaPrioridad(LinkedList<MisilObjetivo> amenazasPendientes) {
        this.amenazasPendientes = amenazasPendientes;
    }

    public LinkedList<MisilObjetivo> getAmenazasPendientes() {
        return amenazasPendientes;
    }

    public boolean hayTiempoDeRecargar(MisilObjetivo misil) {
        return TIEMPO_RECARGA < misil.GetTiempoImpacto() && !amenazasPendientes.isEmpty();
        //Consideramos si da el tiempo de recargar y hay otro misil pendiente
    }

    public int getMaxCola() {
        return Integer.MAX_VALUE;
    }

    public abstract double calcular(MisilObjetivo misil);

    public abstract String getNombre();
}