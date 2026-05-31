package Interfaces;

public abstract class Zonas {
    private String nombreZona;
    private Integer criticidad;
    private Boolean impactado;

    public Zonas(String nombreZona, Integer criticidad) {
        this.nombreZona = nombreZona;
        this.criticidad = criticidad;
        this.impactado = false;
    }

    public String GetNombreZona(){
        return this.nombreZona;
    }
    public Integer GetCriticidad(){
        return this.criticidad;
    }
    public Boolean GetImpactado(){
        return this.impactado;
    }
    public Boolean SetImpactado(Zonas zona){
        return this.impactado = true;
    }

    //public Enum GetTipoZona();


    public Zonas GetZonaPorNombre(String nombreZona){
        if (this.nombreZona.equals(nombreZona)) {
            return this; // Retorna la instancia actual si el nombre coincide
        }
        return null; // Si no coincide, retorna null o lanza una excepción
    }
}