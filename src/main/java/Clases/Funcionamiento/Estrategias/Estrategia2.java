    package Clases.Funcionamiento.Estrategias;

    import Clases.Funcionamiento.EstrategiaPrioridad;
    import Clases.Principales.MisilObjetivo;
    import java.util.LinkedList;

    public class Estrategia2 extends EstrategiaPrioridad {

        public Estrategia2(LinkedList<MisilObjetivo> amenazasPendientes) {
            super(amenazasPendientes);
        }

        @Override
        public int getMaxCola() {
            return 1;
        }

        private static final double FACTOR_AGING = 0.5;

        @Override
        public double calcular(MisilObjetivo misil) {
            double maxCriticidad = getAmenazasPendientes().stream()
                .mapToDouble(m -> m.getObjetivo().GetCriticidad())
                .max()
                .orElse(misil.getObjetivo().GetCriticidad());
            double criticidadEfectiva = misil.getObjetivo().GetCriticidad() + misil.getTicksEspera() * FACTOR_AGING;
            double denominador = misil.GetTiempoImpacto() * maxCriticidad;
            if (denominador == 0) return Double.MAX_VALUE;
            return criticidadEfectiva / denominador;
        }

        @Override
        public String getNombre() {
            return "E2 - Triage (criticidad + aging)";
        }
    }