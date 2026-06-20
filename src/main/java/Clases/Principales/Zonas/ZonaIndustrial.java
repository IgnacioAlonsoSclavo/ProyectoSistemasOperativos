package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class ZonaIndustrial extends Zonas {

    public ZonaIndustrial(String nombreZona) {
        super(nombreZona, Enum.TipoZona.INDUSTRIAL);
        super.setCriticidad(1);
    }

    @Override
    public Zonas GetZonaPorNombre(String nombreZona) {
        if (super.GetNombreZona().equals(nombreZona)) {
            return this;
        }
        return null;
    }
}
