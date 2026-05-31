package Clases.SupongoPronto;

import java.util.LinkedList;

import Clases.MisilObjetivo;

public class Amenazas {
    private LinkedList<MisilObjetivo> amenazasPendientes = new LinkedList<>();

    public LinkedList<MisilObjetivo> getAmenazasPendientes() {
        return amenazasPendientes;
    }

    public void AgregarAmenaza(MisilObjetivo misil) {
        this.amenazasPendientes.add(misil);
    }

    public void EliminarAmenaza(MisilObjetivo misil) {
        this.amenazasPendientes.remove(misil);
    }
}
