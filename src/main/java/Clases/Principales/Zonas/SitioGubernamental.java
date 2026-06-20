package Clases.Principales.Zonas;

import Clases.Enum;
import Interfaces.Zonas;

public class SitioGubernamental extends Zonas {

    public SitioGubernamental(String nombreZona) {
        super(nombreZona, Enum.TipoZona.GUBERNAMENTAL);
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
