package Interfaces;
import java.util.LinkedList;


import Clases.Principales.MisilObjetivo;

public interface Estrategias {
    public String GetNombreEstrategia();
    public MisilObjetivo Interceptar(LinkedList<MisilObjetivo> amenazasPendientes);
}
