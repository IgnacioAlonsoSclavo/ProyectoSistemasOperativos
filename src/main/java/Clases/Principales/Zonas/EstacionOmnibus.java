package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class EstacionOmnibus extends Zonas {

    public EstacionOmnibus(String nombreZona) {
        super(nombreZona, Enum.TipoZona.CIVIL);
        super.setCriticidad(0);
    }

    @Override
    public Zonas GetZonaPorNombre(String nombreZona) {
        if (super.GetNombreZona().equals(nombreZona)) {
            return this;
        }
        return null;
    }
}
