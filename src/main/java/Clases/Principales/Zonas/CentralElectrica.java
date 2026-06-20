package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class CentralElectrica extends Zonas {

    public CentralElectrica(String nombreZona) {
        super(nombreZona, Enum.TipoZona.INDUSTRIAL);
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
