package org.example;

import Clases.Funcionamiento.*;
import Clases.Principales.*;
import Clases.Principales.Zonas.*;
import Interfaces.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class App {

    public static void main(String[] args) throws Exception {

        Map<String, Zonas> zonas = new HashMap<>();

        zonas.put("Hospital Central", new Hospital("Hospital Central"));
        zonas.put("Hospital Norte", new Hospital("Hospital Norte"));
        zonas.put("Zona Militar Base A", new ZonaMilitar("Zona Militar Base A"));
        zonas.put("Zona Residencial Sur", new ZonaResidencial("Zona Residencial Sur"));
        zonas.put("Central Electrica Norte", new CentralElectrica("Central Electrica Norte"));
        zonas.put("Central de Agua Sur", new CentralAgua("Central de Agua Sur"));
        zonas.put("Aeropuerto Internacional", new Aeropuerto("Aeropuerto Internacional"));
        zonas.put("Instituto Educativo Central", new InstitutoEducativo("Instituto Educativo Central"));
        zonas.put("Datacenter Principal", new Datacenter("Datacenter Principal"));
        zonas.put("Estacion de Omnibus Central", new EstacionOmnibus("Estacion de Omnibus Central"));
        zonas.put("Estacion de Bomberos Norte", new EstacionBomberos("Estacion de Bomberos Norte"));
        zonas.put("Zonas Verdes del Este", new ZonasVerdes("Zonas Verdes del Este"));
        zonas.put("Sitio Gubernamental", new SitioGubernamental("Sitio Gubernamental"));
        zonas.put("Zona Industrial Oeste", new ZonaIndustrial("Zona Industrial Oeste"));

        String archivoCsv = args.length > 1 ? args[1] : "misiles.csv";
        LinkedList<MisilObjetivo> futurosMisiles = CargadorMisiles.cargarDesdeCSV(archivoCsv, zonas);

        int cantidadDefensas = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        LinkedList<MisilObjetivo> amenazasPendientes = new LinkedList<>();

        RelojSistema reloj = new RelojSistema();
        new HiloTiempo(reloj).start();

        Semaphore defensasDisponibles = new Semaphore(cantidadDefensas);

        List<Defensa> defensas = new ArrayList<>();
        List<Thread> hilosDefensa = new ArrayList<>();
        for (int i = 1; i <= cantidadDefensas; i++) {
            Defensa d = new Defensa(i, defensasDisponibles, reloj);
            defensas.add(d);
            Thread t = new Thread(d, "Defensa-" + i);
            hilosDefensa.add(t);
            t.start();
        }

        CalculadorPrioridad calculador = new CalculadorPrioridad(amenazasPendientes, defensasDisponibles);

        GestionSistema gestion = new GestionSistema(amenazasPendientes, futurosMisiles, defensas, calculador, reloj);
        gestion.setDaemon(true);
        gestion.start();

        for (Thread t : hilosDefensa) {
            t.join();
        }

        imprimirLog(new ArrayList<>(defensas), new ArrayList<>(zonas.values()), gestion.getHistorialEstrategias());
    }

    // ── Colores ANSI ──────────────────────────────────────────
    private static final String R  = "[0m";
    private static final String B  = "[1m";
    private static final String RD = "[31m";
    private static final String GR = "[32m";
    private static final String YL = "[33m";
    private static final String CY = "[36m";

    private static final int ANCHO = 64;

    private static int visLen(String s) {
        return s.replaceAll("\\[[;\\d]*m", "").length();
    }

    private static void fila(String contenido) {
        int pad = ANCHO - visLen(contenido);
        System.out.println("║" + contenido + " ".repeat(Math.max(0, pad)) + "║");
    }

    private static void filaCentrada(String contenido) {
        int vis = visLen(contenido);
        int izq = (ANCHO - vis) / 2;
        int der = ANCHO - vis - izq;
        System.out.println("║" + " ".repeat(izq) + contenido + " ".repeat(der) + "║");
    }

    private static void separador(char izq, char relleno, char der) {
        System.out.println(izq + String.valueOf(relleno).repeat(ANCHO) + der);
    }

    public static void imprimirLog(List<Defensas> defensas, List<Zonas> zonas, List<String> historialEstrategias) {

        int totalInterceptados = defensas.stream().mapToInt(Defensas::getCantidadInterceptados).sum();
        int maxInterceptados   = defensas.stream().mapToInt(Defensas::getCantidadInterceptados).max().orElse(1);
        long impactadas        = zonas.stream().filter(Zonas::GetImpactado).count();
        long intactas          = zonas.size() - impactadas;

        // ── HEADER ────────────────────────────────────────────
        separador('╔', '═', '╗');
        filaCentrada(B + CY + "SISTEMA DE DEFENSA ANTIMISIL" + R);
        separador('╠', '═', '╣');

        // ── ESTRATEGIAS ───────────────────────────────────────
        filaCentrada(B + YL + "◈  ESTRATEGIAS" + R);
        separador('╠', '═', '╣');

        for (String entrada : historialEstrategias) {
            boolean esTransicion = entrada.contains("[TRANSICIÓN]");
            String color = esTransicion ? YL : CY;
            fila("  " + color + entrada + R);
        }

        // ── DEFENSAS ──────────────────────────────────────────
        separador('╠', '═', '╣');
        filaCentrada(B + YL + "◈  DEFENSAS" + R);
        separador('╠', '═', '╣');

        for (Defensas d : defensas) {
            int n      = d.getCantidadInterceptados();
            int llenos = maxInterceptados > 0 ? (n * 12 / maxInterceptados) : 0;
            String barra = GR + "█".repeat(llenos) + R + "░".repeat(12 - llenos);
            fila(String.format("  Defensa %-2d  →  %3d interceptados   %s", d.getId(), n, barra));
        }

        // ── ZONAS ─────────────────────────────────────────────
        separador('╠', '═', '╣');
        filaCentrada(B + YL + "◈  ZONAS" + R);
        separador('╠', '═', '╣');

        fila(String.format("  %-14s  %-28s  %s  %s", "TIPO", "ZONA", "CRIT", "ESTADO"));
        separador('╟', '─', '╢');

        zonas.stream()
            .sorted(Comparator.comparing(Zonas::GetImpactado).reversed()
                .thenComparingInt(z -> -z.GetCriticidad()))
            .forEach(z -> {
                String color  = z.GetImpactado() ? RD : GR;
                String estado = z.GetImpactado() ? "IMPACTADA" : "INTACTA  ";
                fila(String.format("  %-14s  %-28s   %d    %s",
                    z.GetTipoZona(), z.GetNombreZona(), z.GetCriticidad(),
                    color + B + estado + R));
            });

        // ── RESUMEN ───────────────────────────────────────────
        separador('╠', '═', '╣');
        filaCentrada(B + YL + "◈  RESUMEN" + R);
        separador('╠', '═', '╣');

        fila("  Misiles interceptados  :  " + B + GR + totalInterceptados + R);
        fila("  Zonas intactas         :  " + B + GR + intactas + " / " + zonas.size() + R);
        fila("  Zonas impactadas       :  " + B + RD + impactadas + " / " + zonas.size() + R);
        separador('╚', '═', '╝');
    }
}
