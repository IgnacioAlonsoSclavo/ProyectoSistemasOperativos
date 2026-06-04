package Clases.Principales;
import Interfaces.Zonas;


public class
Hospital extends Zonas{
    private String nombreZona;
    private Integer criticidad;
    private Boolean impactado;

    public Hospital(String nombreZona, Integer criticidad) {
        super(nombreZona, criticidad); // Llama al constructor de la clase base Zonas
        this.impactado = false; // Asumimos que el hospital no está impactado inicialmente
    }

    public String GetNombreZona() {
        return this.nombreZona;
    }

    public Integer GetCriticidad() {
        return this.criticidad;
    }

    public Boolean GetImpactado() {
        return this.impactado;
    }

    public Boolean SetImpactado(Zonas zona) {
        if (zona instanceof Hospital) {
            Hospital hospital = (Hospital) zona;
            hospital.impactado = !hospital.impactado; // Cambia el estado de impacto del hospital
            return hospital.impactado; // Retorna el nuevo estado
        }
        return null; // Si no es un hospital, retorna null o lanza una excepción
    }


    //public Enum GetTipoZona() {
        //return Enum.TipoZona.HOSPITAL; // Asumiendo que tienes un enum para tipos de zonas
    //}

    @Override
    public Zonas GetZonaPorNombre(String nombreZona) {
        if (this.nombreZona.equals(nombreZona)) {
            return this; // Retorna la instancia actual si el nombre coincide
        }
        return null; // Si no coincide, retorna null o lanza una excepción
    }


    public void ValueOf(String nombreZona) {
        throw new UnsupportedOperationException("Unimplemented method 'ValueOf'");
    }
}
