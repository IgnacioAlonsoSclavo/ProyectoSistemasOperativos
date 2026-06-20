package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class CentralAgua extends Zonas {

    public CentralAgua(String nombreZona) {
        super(nombreZona, Enum.TipoZona.CIVIL);
        super.setCriticidad(2);
    }

    @Override
    public Zonas GetZonaPorNombre(String nombreZona) {
        if (super.GetNombreZona().equals(nombreZona)) {
            return this;
        }
        return null;
    }
}
