package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Partido {

	private int idPartido;
	private String equipoLocal;
	private int golesLocal;
	private String equipoVisitante;
	private int golesVisitante;

	public Partido() {

	}

	public Partido(int idPartido, String equipoLocal, int golesLocal, String equipoVisitante, int golesVisitante) {
		super();
		this.idPartido = idPartido;
		this.equipoLocal = equipoLocal;
		this.golesLocal = golesLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesVisitante = golesVisitante;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	// metodo para obtener un mapa con los resultados de cada equipo

	public HashMap<String, ArrayList<Integer>> resultadoEquipos(String rutaFichero, String delimitador) {
		try {
			HashMap<String, ArrayList<Integer>> mapaResultadosEquipos = new HashMap<String, ArrayList<Integer>>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split(delimitador);
				// Creamos el mapa de equipos con un arraylist donde se recojan los valores de
				// victoria, empate y derrota
				if (!mapaResultadosEquipos.containsKey(campos[2]) || !mapaResultadosEquipos.containsKey(campos[4])) {
					for (int i = 2; i <= 4; i += 2) {
						if (!mapaResultadosEquipos.containsKey(campos[i]))
							mapaResultadosEquipos.put(campos[i], new ArrayList<Integer>(Arrays.asList(0, 0, 0)));

					}
				}

				try {
					if (Integer.parseInt(campos[3]) > Integer.parseInt(campos[5])) {
						// Victoria para el equipo local
						mapaResultadosEquipos.get(campos[2]).set(0, mapaResultadosEquipos.get(campos[2]).get(0) + 1);
						// Derrota del equipo visitante
						mapaResultadosEquipos.get(campos[4]).set(2, mapaResultadosEquipos.get(campos[4]).get(2) + 1);

					} else if (Integer.parseInt(campos[3]) < Integer.parseInt(campos[5])) {
						// Derrota del equipo local
						mapaResultadosEquipos.get(campos[2]).set(2, mapaResultadosEquipos.get(campos[2]).get(2) + 1);
						// Victoria para el equipo visitante
						mapaResultadosEquipos.get(campos[4]).set(0, mapaResultadosEquipos.get(campos[4]).get(0) + 1);

					} else if (Integer.parseInt(campos[3]) == Integer.parseInt(campos[5])) {
						// Empate por parte del equipo local
						mapaResultadosEquipos.get(campos[2]).set(1, mapaResultadosEquipos.get(campos[2]).get(1) + 1);
						// Empate por parte del equipo visitante
						mapaResultadosEquipos.get(campos[4]).set(1, mapaResultadosEquipos.get(campos[4]).get(1) + 1);
					}
				} catch (NumberFormatException e) {
					break;
				}catch (ArrayIndexOutOfBoundsException e) {
					
				}
			}
			fichero.close();
			return mapaResultadosEquipos;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Línea vacía");
		}

		return null;

	}
}
