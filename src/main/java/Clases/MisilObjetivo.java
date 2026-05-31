package Clases;
import Interfaces.Zonas;

public class MisilObjetivo {
    private String nombreMisil;
    private Zonas objetivo;
    private Integer tiempoImpacto;

    public MisilObjetivo(Zonas objetivo, Integer tiempoImpacto, String nombreMisil) {
        this.objetivo = objetivo;
        this.tiempoImpacto = tiempoImpacto;
        this.nombreMisil = nombreMisil;
    }

    public String GetNombreMisil() {
        return this.nombreMisil;
    }

    public Zonas getObjetivo() {
        return this.objetivo;
    }

    public Integer GetTiempoImpacto() {
        return this.tiempoImpacto;
    }
}
