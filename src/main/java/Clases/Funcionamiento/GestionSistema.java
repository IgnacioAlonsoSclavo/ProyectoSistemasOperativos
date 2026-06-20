package Clases.Funcionamiento;

import Clases.Principales.Defensa;
import Clases.Principales.MisilObjetivo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GestionSistema extends Thread {

    private final RelojSistema reloj;
    private final LinkedList<MisilObjetivo> amenazasPendientes;
    private final LinkedList<MisilObjetivo> futurosMisiles;
    private final List<Defensa> defensas;
    private final CalculadorPrioridad calculador;
    private int maxColaAnterior = Integer.MAX_VALUE;
    private String ultimaEstrategia = "";
    private final List<String> historialEstrategias = new ArrayList<>();

    public List<String> getHistorialEstrategias() {
        return historialEstrategias;
    }

    public GestionSistema(LinkedList<MisilObjetivo> amenazasPendientes,
                          LinkedList<MisilObjetivo> futurosMisiles,
                          List<Defensa> defensas,
                          CalculadorPrioridad calculador,
                          RelojSistema reloj) {
        this.amenazasPendientes = amenazasPendientes;
        this.futurosMisiles = futurosMisiles;
        this.defensas = defensas;
        this.calculador = calculador;
        this.reloj = reloj;
    }

    @Override
    public void run() {
        try {
            while (true) {
                reloj.esperarTick();
                agregarMisilesAparecidos();
                actualizarTiempos();
                if (reloj.getTick() % 2 == 0) {
                    dispatch();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void agregarMisilesAparecidos() {
        synchronized (amenazasPendientes) {
            Iterator<MisilObjetivo> it = futurosMisiles.iterator();
            while (it.hasNext()) {
                MisilObjetivo misil = it.next();
                if (misil.GetTiempoAparicion() <= reloj.getTick()) {
                    amenazasPendientes.add(misil);
                    it.remove();
                }
            }
        }
    }

    private void actualizarTiempos() {
        synchronized (amenazasPendientes) {
            Iterator<MisilObjetivo> it = amenazasPendientes.iterator();
            while (it.hasNext()) {
                MisilObjetivo misil = it.next();
                misil.decrementarTiempoImpacto();
                misil.incrementarTicksEspera();
                if (misil.GetEstado()) {
                    it.remove();
                }
            }
        }
    }

    private void dispatch() {
        boolean todoTerminado = false;

        synchronized (amenazasPendientes) {
            if (amenazasPendientes.isEmpty() && futurosMisiles.isEmpty()) {
                todoTerminado = true;
            } else if (!amenazasPendientes.isEmpty()) {
                EstrategiaPrioridad estrategia = calculador.seleccionarEstrategia();
                int maxCola = estrategia.getMaxCola();

                String nombre = estrategia.getNombre();
                if (!nombre.equals(ultimaEstrategia)) {
                    boolean esTransicion = !ultimaEstrategia.isEmpty();
                    historialEstrategias.add(String.format("Tick %3d  →  %s%s",
                        reloj.getTick(), nombre, esTransicion ? "  [TRANSICIÓN]" : ""));
                    ultimaEstrategia = nombre;
                }

                if (maxCola < maxColaAnterior) {
                    for (Defensa d : defensas) {
                        amenazasPendientes.addAll(d.vaciarCola());
                    }
                }
                maxColaAnterior = maxCola;

                while (!amenazasPendientes.isEmpty()) {
                    MisilObjetivo misilElegido = amenazasPendientes.stream()
                            .max(Comparator.comparingDouble(estrategia::calcular)
                                    .thenComparingInt(m -> m.getObjetivo().GetCriticidad()))
                            .orElse(null);

                    Defensa defensaElegida = defensas.stream()
                            .filter(d -> d.getCantidadEnCola() < maxCola)
                            .min(Comparator.comparingInt(Defensa::getCantidadEnCola)
                                    .thenComparingInt(d -> d.isOcupada() ? 1 : 0))
                            .orElse(null);

                    if (misilElegido == null || defensaElegida == null) break;

                    amenazasPendientes.remove(misilElegido);
                    defensaElegida.encolarMisil(misilElegido);
                }
            }
        }

        if (todoTerminado) {
            defensas.forEach(Defensa::detener);
        }
    }
}
