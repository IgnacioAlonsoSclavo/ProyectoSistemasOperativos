package Clases.Funcionamiento;

import Clases.Funcionamiento.EstrategiaPrioridad;
import Clases.Principales.MisilObjetivo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Estrategia1 extends EstrategiaPrioridad {

    private Semaphore defensasDisponibles;

    public Estrategia1(LinkedList<MisilObjetivo> amenazasPendientes,
                       Semaphore defensasDisponibles) {
        super(amenazasPendientes);
        this.defensasDisponibles = defensasDisponibles;
    }

    @Override
    public double calcular(MisilObjetivo misil) {
        // Busca el misil con menor tiempo de impacto
        MisilObjetivo misilMasUrgente = null;
        for (MisilObjetivo m : getAmenazasPendientes()) {
            if (misilMasUrgente == null || m.GetTiempoImpacto() < misilMasUrgente.GetTiempoImpacto()) {
                misilMasUrgente = m;
            }
        }

        // Si hay tiempo de recargar, atiende ese misil adicional
        if (hayTiempoDeRecargar(misil) && misilMasUrgente != null) {
            try {
                defensasDisponibles.acquire();
                misilMasUrgente.setEstado(false);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                defensasDisponibles.release();
            }
        }

        // Devuelve el puntaje del misil actual por tiempo de impacto
        return misil.GetTiempoImpacto();
    }
}