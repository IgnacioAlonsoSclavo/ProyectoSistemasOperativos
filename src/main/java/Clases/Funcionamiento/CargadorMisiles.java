package Clases.Funcionamiento;

import Clases.Principales.MisilObjetivo;
import Interfaces.Zonas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;

public class CargadorMisiles {

    public static LinkedList<MisilObjetivo> cargarDesdeCSV(String recurso, Map<String, Zonas> zonas) throws IOException {
        LinkedList<MisilObjetivo> misiles = new LinkedList<>();

        InputStream is = CargadorMisiles.class.getClassLoader().getResourceAsStream(recurso);
        if (is == null) throw new IOException("No se encontró el recurso: " + recurso);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.readLine(); // saltar encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] p = linea.split(",");
                int id              = Integer.parseInt(p[0].trim());
                String nombreZona   = p[1].trim();
                int tiempoImpacto   = Integer.parseInt(p[2].trim());
                int tiempoAparicion = Integer.parseInt(p[3].trim());

                Zonas zona = zonas.get(nombreZona);
                if (zona == null) throw new IOException("Zona no encontrada en el sistema: " + nombreZona);
                misiles.add(new MisilObjetivo(zona, tiempoImpacto, id, tiempoAparicion));
            }
        }
        return misiles;
    }
}
