package Clases;

public class CalculadorPrioridad {
    public static Integer Estrategia1(MisilObjetivo misil) {
        Integer prioridad = misil.getObjetivo().GetCriticidad(); //Criticidad/Tiempo
        return prioridad;
    }

    public static Integer Estrategia2(MisilObjetivo misil) {
        Integer prioridad = misil.GetTiempoImpacto(); //Menos misiles que la cantidad de defensas, una para cada misil
        return prioridad;
    }
}
