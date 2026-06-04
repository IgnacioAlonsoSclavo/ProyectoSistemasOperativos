package Clases.Principales;
import Interfaces.Zonas;

public class MisilObjetivo {
    private final int idMisil;
    private Zonas objetivo;
    private int tiempoImpacto;
    private final int tiempoAparicion;
    private int prioridad;
    private boolean estado = false; //cambiar a enum

    public MisilObjetivo(Zonas objetivo, int tiempoImpacto, int idMisil, int tiempoAparicion) {
        this.objetivo = objetivo;
        this.tiempoImpacto = tiempoImpacto;
        this.idMisil = idMisil;
        this.tiempoAparicion = tiempoAparicion;
    }

    public int GetIdMisil() {
        return this.idMisil;
    }

    public boolean GetEstado() {return this.estado;}

    public Zonas getObjetivo() {
        return this.objetivo;
    }

    public int GetTiempoImpacto() {
        return this.tiempoImpacto;
    }

    public int GetTiempoApariciion() {
        return this.tiempoAparicion;
    }

    public void setEstado(boolean estado) {
        estado = estado;
    }

    private void impactar(){
        this.estado = true;
    }
}
