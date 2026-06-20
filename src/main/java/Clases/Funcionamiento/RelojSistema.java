package Clases.Funcionamiento;

public class RelojSistema {

    private int tick = 0;

    public synchronized void avanzarTick() {
        tick++;
        notifyAll();
    }

    public synchronized int getTick() {
        return tick;
    }

    public synchronized void esperarTick() throws InterruptedException {
        int tickActual = tick;
        while (tick == tickActual) {
            wait();
        }
    }

    public synchronized void esperarTicks(int cantidad) throws InterruptedException {
        int tickObjetivo = tick + cantidad;
        while (tick < tickObjetivo) {
            wait();
        }
    }
}
