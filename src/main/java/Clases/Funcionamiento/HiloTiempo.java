package Clases.Funcionamiento;

public class HiloTiempo extends Thread {

    private final RelojSistema reloj;

    public HiloTiempo(RelojSistema reloj) {
        this.reloj = reloj;
        setDaemon(true);
        setName("HiloTiempo");
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Thread.sleep(50);
                reloj.avanzarTick();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
