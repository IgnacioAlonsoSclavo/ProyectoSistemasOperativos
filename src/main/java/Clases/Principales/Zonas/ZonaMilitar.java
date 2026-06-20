package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class ZonaMilitar extends Zonas {

    public ZonaMilitar(String nombreZona) {
        super(nombreZona, Enum.TipoZona.MILITAR);
        super.setCriticidad(3);
    }

    @Override
    public Zonas GetZonaPorNombre(String nombreZona) {
        if (super.GetNombreZona().equals(nombreZona)) {
            return this;
        }
        return null;
    }
}
