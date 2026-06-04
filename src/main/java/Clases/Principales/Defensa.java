package Clases.Principales;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Defensa implements Runnable {

    public int cantidadInterceptados = 0;
    public final int idDefensa;
    private Queue<MisilObjetivo> misilesInterseptar;
    private Semaphore defensasDisponibles;
    private volatile boolean activa = true; // Para poder detener el hilo

    public Defensa(int idDefensa, Semaphore defensasDisponibles) {
        this.idDefensa = idDefensa;
        this.misilesInterseptar = new LinkedList<>();
        this.defensasDisponibles = defensasDisponibles;
    }

    public synchronized void encolarMisil(MisilObjetivo misil) {
        misilesInterseptar.add(misil);
        notify(); // Despierta el hilo si estaba esperando
    }

    public void detener() {
        activa = false;
    }

    @Override
    public void run() {
        while (activa) {
            MisilObjetivo misil = null;

            synchronized (this) {
                while (misilesInterseptar.isEmpty() && activa) {
                    try {
                        wait(); // Espera hasta que haya un misil
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (!misilesInterseptar.isEmpty()) {
                    misil = misilesInterseptar.poll();
                }
            }

            if (misil != null) {
                interseptar(misil);
            }
        }
    }

    private void interseptar(MisilObjetivo misil) {
        try {
            defensasDisponibles.acquire(); // Ocupa el permiso mientras intercepta
            misil.setEstado(false);
            cantidadInterceptados++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            defensasDisponibles.release(); // Libera el permiso al terminar
        }
    }
}