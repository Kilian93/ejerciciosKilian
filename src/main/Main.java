package main;

import java.util.ArrayList;
import java.util.HashMap;

import control.Ejercicios;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

public class Main {
	public static void main(String[] args) {
		Jugador prueba1 = new Jugador();
		Partido prueba2 = new Partido();
		Equipo prueba3 = new Equipo();
		Ejercicios prueba4 = new Ejercicios();

		// prueba1.listaJugadoresPorNombres("ficheros/jugadores.txt", "#");
		// HashMap<String,ArrayList<Integer>> recogerMapaResultados=
		// prueba2.resultadoEquipos("ficheros/partidos.txt", "#");
		// HashMap<String,Integer> recogerClasificacion =
		// prueba3.clasificacionEquipo("ficheros/Partidos.txt", "#");
		// HashMap<String,Integer>recogerClasificacionOrdenada=prueba3.clasificacionOrdenada(recogerClasificacion);
		// prueba3.crearFicherosObjetoEquipos(listaEquipos);
		// prueba4.crearFicheroObjetoEquipos();
		ArrayList<Equipo> listaEquipos= prueba3.crearListaEquipo("ficheros/equipos.txt", "#");
		System.out.println("Fin de Programa");

	}

}
