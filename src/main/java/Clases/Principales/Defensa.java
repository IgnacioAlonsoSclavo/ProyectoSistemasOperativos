package Clases.Principales;

import Clases.Enum;

public class Defensa {//Hilo defensa
    public  Integer cantidadInterceptados = 0;
    public int idDefensa;
    public Clases.Enum.EstadoDefensa estado;// true si esta disponible, false si esta interceptando un misil

    public Defensa(int idDefensa) {
        this.idDefensa = idDefensa;
        this.estado = Enum.EstadoDefensa.DISPONIBLE;
    }

    /*public static void InterceptarMisil(MisilObjetivo misil) {
        try {
            if(estado == Enum.EstadoDefensa.DISPONIBLE) {
                //Hacemos la logica de interceptar
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
     */
}
