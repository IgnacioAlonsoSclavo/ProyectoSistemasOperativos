package Clases.Funcionamiento;

import Clases.Principales.MisilObjetivo;

import java.util.LinkedList;

public class Amenazas {
    private LinkedList<MisilObjetivo> amenazasPendientes = new LinkedList<>(); //Son las amenazas que existen, si empiezan a ser interceptadas, ya no estan en esta lista
    private LinkedList<MisilObjetivo> amenazasInterceptadas = new LinkedList<>(); //Son las amenazas que estan siendo interceptadas

    public LinkedList<MisilObjetivo> getAmenazasPendientes() {
        return amenazasPendientes;
    }

    public LinkedList<MisilObjetivo> getAmenazasInterceptadas(){
        return amenazasInterceptadas;
    }

    public void AgregarAmenaza(MisilObjetivo misil) {
        this.amenazasPendientes.add(misil);
    }

    public void EliminarAmenaza(MisilObjetivo misil) {
        this.amenazasPendientes.remove(misil);
    }
}
