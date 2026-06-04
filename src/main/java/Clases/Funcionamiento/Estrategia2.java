    package Clases.Funcionamiento;

    import Clases.Funcionamiento.EstrategiaPrioridad;
    import Clases.Principales.Defensa;
    import Clases.Principales.MisilObjetivo;

    import java.util.Comparator;
    import java.util.LinkedList;
    import java.util.concurrent.Semaphore;

    public class Estrategia2 extends EstrategiaPrioridad {

        private Semaphore defensasDisponibles;

        public Estrategia2(LinkedList<MisilObjetivo> amenazasPendientes,
                           Semaphore defensasDisponibles) {
            super(amenazasPendientes);
            this.defensasDisponibles = defensasDisponibles;
        }

        @Override
        public double calcular(MisilObjetivo misil) {
            if (hayTiempoDeRecargar(misil)) {
                MisilObjetivo misilMasCritico = getAmenazasPendientes().getFirst();
                try {
                    defensasDisponibles.acquire();
                    misilMasCritico.setEstado(false);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    defensasDisponibles.release();
                }
            }
            return (double) misil.getObjetivo().GetCriticidad() / misil.GetTiempoImpacto();
        }
    }