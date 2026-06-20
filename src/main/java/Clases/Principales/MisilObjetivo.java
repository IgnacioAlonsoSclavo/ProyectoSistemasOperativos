package Clases.Principales;
import Interfaces.Zonas;

public class MisilObjetivo {
    private final int ID_MISIL;
    private Zonas objetivo;
    private int tiempoImpacto;
    private final int TIEMPO_APARICION;
    private volatile boolean estado = false;
    private int ticksEspera = 0;

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

    public int GetTiempoAparicion() {
        return this.TIEMPO_APARICION;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getTicksEspera() {
        return ticksEspera;
    }

    public void incrementarTicksEspera() {
        ticksEspera++;
    }

    public void decrementarTiempoImpacto() {
        if (this.tiempoImpacto > 0) {
            this.tiempoImpacto--;
            if (this.tiempoImpacto == 0) {
                impactar();
            }
        }
    }

    public void impactar(){
        this.estado = true;
        this.objetivo.SetImpactado();
    }
}
