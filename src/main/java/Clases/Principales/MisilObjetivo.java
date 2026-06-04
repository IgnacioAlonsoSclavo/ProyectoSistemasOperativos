package Clases.Principales;
import Interfaces.Zonas;

public class MisilObjetivo {
    private final int ID_MISIL;
    private Zonas objetivo;
    private int tiempoImpacto;
    private final int TIEMPO_APARICION;
    private int prioridad;
    private boolean estado = false; //cambiar a enum

    public MisilObjetivo(Zonas objetivo, int tiempoImpacto, int idMisil, int tiempoAparicion) {
        this.objetivo = objetivo;
        this.tiempoImpacto = tiempoImpacto;
        this.ID_MISIL = idMisil;
        this.TIEMPO_APARICION = tiempoAparicion;
    }

    public int GetIdMisil() {
        return this.ID_MISIL;
    }

    public boolean GetEstado() {return this.estado;}

    public Zonas getObjetivo() {
        return this.objetivo;
    }

    public int GetTiempoImpacto() {
        return this.tiempoImpacto;
    }

    public int GetTiempoApariciion() {
        return this.TIEMPO_APARICION;
    }

    public void setEstado(boolean estado) {
        estado = estado;
    }

    private void impactar(){
        this.estado = true;
    }
}
