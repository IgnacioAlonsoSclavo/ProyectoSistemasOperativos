package Clases.Principales;

import Clases.Funcionamiento.EstrategiaPrioridad;
import Clases.Funcionamiento.RelojSistema;
import Interfaces.Defensas;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Defensa implements Runnable, Defensas {

    private int cantidadInterceptados = 0;
    public final int ID_DEFENSA;
    private Queue<MisilObjetivo> misilesInterceptar;
    private Semaphore defensasDisponibles;
    private RelojSistema reloj;
    private volatile boolean activa = true;
    private volatile boolean ocupada = false;

    public boolean isOcupada() {
        return ocupada;
    }

    public Defensa(int idDefensa, Semaphore defensasDisponibles, RelojSistema reloj) {
        this.ID_DEFENSA = idDefensa;
        this.misilesInterceptar = new ArrayDeque<>();
        this.defensasDisponibles = defensasDisponibles;
        this.reloj = reloj;
    }

    public synchronized void encolarMisil(MisilObjetivo misil) {
        misilesInterceptar.add(misil);
        notify(); // Despierta el hilo si estaba esperando
    }

    public synchronized int getCantidadEnCola() {
        return misilesInterceptar.size();
    }

    public synchronized List<MisilObjetivo> vaciarCola() {
        List<MisilObjetivo> devueltos = new ArrayList<>(misilesInterceptar);
        misilesInterceptar.clear();
        return devueltos;
    }

    @Override
    public int getId() {
        return ID_DEFENSA;
    }

    @Override
    public int getCantidadInterceptados() {
        return cantidadInterceptados;
    }

    public synchronized void detener() {
        activa = false;
        notifyAll();
    }

    @Override
    public void run() {
        while (activa) {
            MisilObjetivo misil = null;

            synchronized (this) {
                while (misilesInterceptar.isEmpty() && activa) {
                    try {
                        wait(); // Espera hasta que haya un misil
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (!misilesInterceptar.isEmpty()) {
                    misil = misilesInterceptar.poll();
                }
            }

            if (misil != null) {
                interceptar(misil);
            }
        }
    }

    private void interceptar(MisilObjetivo misil) {
        try {
            defensasDisponibles.acquire();
            ocupada = true;
            misil.setEstado(true);
            cantidadInterceptados++;
            reloj.esperarTicks(EstrategiaPrioridad.TIEMPO_RECARGA);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            ocupada = false;
            defensasDisponibles.release();
        }
    }
}