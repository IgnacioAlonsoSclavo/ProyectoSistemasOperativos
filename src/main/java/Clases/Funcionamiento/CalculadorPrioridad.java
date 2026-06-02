package Clases.Funcionamiento;

import Clases.Principales.MisilObjetivo;

/*
Esta clase se encarga de ver cuales son los niveles de prioridad de un misil.
Solo se debe implementar segun la Estrategia 2
Se puede calcular de dos maneras.
Segun criticidad de zona sobre tiempo.
Segun tiempo de recarga.
Si tiempo de recarga es menor al tiempo de imapacto, y existe otro misil el cual no esta siendo
interceptado, entonces atacara a ese misil

Creo que necesitamos una lista ordenada en funcion de los tiempos de impacto
 */
public class CalculadorPrioridad {

    public static double Estrategia1(MisilObjetivo misil) {
        Integer prioridad = misil.getObjetivo().GetCriticidad()/misil.GetTiempoImpacto(); //Criticidad/Tiempo
        return prioridad;
    }

    //3 simula el tiempo de recarga
    public static Integer Estrategia2(MisilObjetivo misil, Amenazas amenaza) {
        if(3 < misil.GetTiempoImpacto()){
            if(!amenaza.getAmenazasPendientes().isEmpty()){

            }
        }
        Integer prioridad = misil.GetTiempoImpacto(); //Tiempo de recarga menor al tiempo de impacto
        return prioridad;
    }
}
