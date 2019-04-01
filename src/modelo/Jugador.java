package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Jugador extends Persona {
	private int idJugador;
	private int dorsal;
	private int idEquipo;

	public Jugador() {
	}

	public Jugador(int idJugador, String nombre, int dorsal, int idEquipo) {
		super(nombre);
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.idEquipo = idEquipo;
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	@Override
	public String toString() {
		return "Nombre jugador: " + getNombre() + " [idJugador=" + idJugador + ", dorsal=" + dorsal + ", idEquipo="
				+ idEquipo + "]";
	}

	public ArrayList<Jugador> crearListaJugadores (String rutaFichero, String delimitador){
		try {
			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				//romper la cadena registro
				/*for (int i = 0; i < campos.length; i++)
					System.out.print(campos[i] + " , ");
				System.out.println("");*/
				
				jugadores.add(new Jugador(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3])));
			}
			fichero.close();
			System.out.println("Fin de la lectura del fichero");
			return jugadores;
		} catch (FileNotFoundException e) {
			System.out.println("fichero no encontrado");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		
		return null;
		
	}
	
	// metodo para crear lista de jugadores por nombre

	public void listaJugadoresPorNombres(String rutaFichero, String delimitador) {
		try {
			ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			int contador = 1;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split(delimitador);
				jugadores.add(new Jugador(Integer.parseInt(campos[0]), campos[1], Integer.parseInt(campos[2]),
						Integer.parseInt(campos[3])));

			}
			fichero.close();
			//en este apartado podemos ordenarlos por el campo que queramos
			/*Collections.sort(jugadores, new Comparator<Persona>() {
				public int compare(Persona obj1, Persona obj2) {
					return obj1.getNombre().compareTo(obj2.getNombre());
				}
			});*/
			
			Collections.sort(jugadores, new Comparator<Jugador>() {
				public int compare(Jugador ju1, Jugador ju2) {
					
					if (ju1.getDorsal() < ju2.getDorsal()) {
						return 1;
					} else if (ju1.getDorsal() > ju2.getDorsal())
						return -1;
					else
						return 0;
				}
			});

			for (int i = 0; i < jugadores.size(); i++) {
				System.out.println(contador + ": " + jugadores.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
	}
	
	//metodo para ordenar lista de jugadores 
	
	public ArrayList<Jugador> ordenarListaJugadores() {
		ArrayList<Jugador> lista = crearListaJugadores("ficheros/jugadores2.txt", "#");
		lista.sort(new Comparator<Jugador>() {

			/*
			 * @Override public int compare(Equipo eq1, Equipo eq2) {
			 * 
			 * return eq1.getNombreLargo().compareTo(eq2.getNombreLargo()); }
			 */

			public int compare(Jugador ju1, Jugador ju2) {

				if (ju1.getDorsal() < ju2.getDorsal()) {
					return 1;
				} else if (ju1.getDorsal() > ju2.getDorsal())
					return -1;
				else
					return 0;
			}

		});

		return lista;
	}
	
	//mapa para asignar los jugadores a sus equipos correspondientes 
	
	

}
