package Interfaces;

import Clases.Enum;

public abstract class Zonas {
    private String nombreZona;
    private Boolean impactado;
    private Enum.TipoZona tipoZona;
    private int criticidad;

    public Zonas(String nombreZona, Enum.TipoZona tipoZona) {
        this.nombreZona = nombreZona;
        this.impactado = false;
        this.tipoZona = tipoZona;
    }

    public int GetCriticidad() {
        return this.criticidad;
    }

    public void setCriticidad(int criticidad) {
        this.criticidad = criticidad;
    }

    public String GetNombreZona(){
        return this.nombreZona;
    }

    public Boolean GetImpactado(){
        return this.impactado;
    }
    public Boolean SetImpactado(){
        return this.impactado = true;
    }
    public Enum.TipoZona GetTipoZona(){
        return this.tipoZona;
    }


    public Zonas GetZonaPorNombre(String nombreZona){
        if (this.nombreZona.equals(nombreZona)) {
            return this; // Retorna la instancia actual si el nombre coincide
        }
        return null; // Si no coincide, retorna null o lanza una excepción
    }
}