package Clases.Funcionamiento;
import Clases.Principales.Defensa;
import Clases.Principales.MisilObjetivo;
import Interfaces.Estrategias;

import java.util.LinkedList;

public class SelectorEstrategias {
    
    public Estrategias SeleccionarEstrategias(LinkedList<MisilObjetivo> amenazasPendientes, LinkedList<Defensa> defensasDisponibles ) {
        if (amenazasPendientes.isEmpty()) {
            //return new EstrategiaCero();
        }
        if (amenazasPendientes.size() <= defensasDisponibles.size()) {
            return new EstrategiaUno();
        }
        return new EstrategiaDos();
    }
}
