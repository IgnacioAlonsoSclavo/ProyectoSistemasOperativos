package Clases.SupongoPronto;
import Interfaces.Estrategias;

import java.util.LinkedList;

import Clases.Defensa;
import Clases.EstrategiaDos;
import Clases.EstrategiaUno;
import Clases.MisilObjetivo;

public class SelectorEstrategias {
    
    public Estrategias SeleccionarEstrategias(LinkedList<MisilObjetivo> amenazasPendientes, LinkedList<Defensa> defensasDisponibles ) {
        if (amenazasPendientes.isEmpty()) {
            return new EstrategiaUno();
        }
        if (amenazasPendientes.size() < defensasDisponibles.size()) {
            return new EstrategiaDos();
        }
        return new EstrategiaUno();
    }
}
