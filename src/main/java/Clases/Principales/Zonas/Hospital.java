package Clases.Principales.Zonas;
import Clases.Enum;
import Interfaces.Zonas;

public class Hospital extends Zonas {

    public Hospital(String nombreZona) {
        super(nombreZona, Enum.TipoZona.CIVIL);
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
