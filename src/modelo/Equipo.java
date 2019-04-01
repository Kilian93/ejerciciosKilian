package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Equipo {
	private int idEquipo;
	private String nombreCorto;
	private String nombreEquipo;
	private int Puntos;
	private int GolesFavor;
	private int GolesContra;
	private int Victoria;
	private int Empate;
	private int Derrota;

	public Equipo() {

	}

	public Equipo(int idEquipo, String nombreCorto, String nombreEquipo) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCorto = nombreCorto;
		this.nombreEquipo = nombreEquipo;
	}

	public Equipo(int idEquipo, String nombreCorto, String nombreEquipo, int puntos, int golesFavor, int golesContra,
			int victoria, int empate, int derrota) {
		super();
		this.idEquipo = idEquipo;
		this.nombreCorto = nombreCorto;
		this.nombreEquipo = nombreEquipo;
		Puntos = puntos;
		GolesFavor = golesFavor;
		GolesContra = golesContra;
		Victoria = victoria;
		Empate = empate;
		Derrota = derrota;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getPuntos() {
		return Puntos;
	}

	public void setPuntos(int puntos) {
		Puntos = puntos;
	}

	public int getGolesFavor() {
		return GolesFavor;
	}

	public void setGolesFavor(int golesFavor) {
		GolesFavor = golesFavor;
	}

	public int getGolesContra() {
		return GolesContra;
	}

	public void setGolesContra(int golesContra) {
		GolesContra = golesContra;
	}

	public int getVictoria() {
		return Victoria;
	}

	public void setVictoria(int victoria) {
		Victoria = victoria;
	}

	public int getEmpate() {
		return Empate;
	}

	public void setEmpate(int empate) {
		Empate = empate;
	}

	public int getDerrota() {
		return Derrota;
	}

	public void setDerrota(int derrota) {
		Derrota = derrota;
	}

	// metodo para crear una lista de equipos
	public ArrayList<Equipo> crearListaEquipo(String rutaFichero, String delimitador) {
		try {
			ArrayList<Equipo> equipos = new ArrayList<Equipo>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				// Romper la cadena registro
				String[] campos = registro.split(delimitador);
			/*	for (int i = 0; i < campos.length; i++)
					System.out.print(campos[i] + " , ");
				System.out.println("");*/
				// Incluir cada elemento del array como elementos del ArrayList de Equipo
				equipos.add(new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]));

			}
			fichero.close();
			return equipos;
		} catch (NumberFormatException e) {
			// Ignorar lineas vacias
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

		return null;

	}

	// metodo para crear el mapa de equipos

	public HashMap<String, Equipo> crearMapaEquipos(String rutaFichero, String delimitador) {
		try {
			HashMap<String, Equipo> mapaEquipos = new HashMap<String, Equipo>();
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split(delimitador);

				mapaEquipos.put(campos[1], new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]));

			}
			fichero.close();
			return mapaEquipos;
		} catch (NumberFormatException e) {

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
		return null;

	}

	public HashMap<String, Integer> clasificacionEquipo(String rutaFichero, String delimitador) {
		HashMap<String, ArrayList<Integer>> resultadosEquipos = new Partido().resultadoEquipos(rutaFichero,
				delimitador);
		HashMap<String, Integer> clasificacionEquipos = new HashMap<String, Integer>();
		// Set<String> clavesMapa = resultadosEquipos.keySet();
		for (String clave : resultadosEquipos.keySet()) {
			clasificacionEquipos.put(clave,
					(resultadosEquipos.get(clave).get(0) * 3) + resultadosEquipos.get(clave).get(1));
		}

		return clasificacionEquipos;

	}

	public HashMap<String, Integer> clasificacionOrdenada(HashMap<String, Integer> map) {
		List<Entry<String, Integer>> listaOrdenada = new LinkedList<>(map.entrySet());
		// Defined Custom Comparator here
		listaOrdenada.sort(new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		HashMap<String, Integer> mapaOrdenado = new LinkedHashMap<String, Integer>();
		for (Iterator<Entry<String, Integer>> it = listaOrdenada.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			mapaOrdenado.put(entry.getKey(), entry.getValue());
		}
		return mapaOrdenado;
	}

	// Metodo para crear un fichero objeto

	public void crearFicherosObjetoEquipos(ArrayList<Equipo> listaEquipos) {
		ArrayList<Equipo> equipos = listaEquipos;

		try {
			FileOutputStream salida = new FileOutputStream("ficheros/objetosEquipos.obj");
			ObjectOutputStream objetos = new ObjectOutputStream(salida);
			// Recorre equipos.txt, creando objetos equipo y grabándolos en objetos.

			for (int i = 0; i < equipos.size(); i++) {
				objetos.writeObject(equipos.get(i));
				if (i == 10) {
					objetos.writeObject("Me he colado y no soy un equipo");
				}
			}

			objetos.writeObject("No soy un equipo"); // Añadimos es objeto para evaluaciones posteriores
			// Querremos comprobar si todos los dato del fichero que creamos contiene solo
			// equipos y
			// en el caso de que no, cómo controlarlo.

			System.out.println("El objeto fue creado satisfactoriamente en el fichero");
			objetos.close();

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado.");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}
	}

	public void crearFicheroObjetoEquipos() {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader("ficheros/equipos.txt"));
			FileOutputStream salida = new FileOutputStream("ficheros/equipos.obj");
			ObjectOutputStream objetos = new ObjectOutputStream(salida);
			String registro;
			while ((registro = fichero.readLine()) != null) {
				String[] campos = registro.split("#");
				Equipo equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
				objetos.writeObject(equipo);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	//metodo para crear un Equipo
	
	

	// metodo para leer fichero objetos

	public void leerFicheroObjetos(String rutaFichero) {
		try {
			ObjectInputStream objetos = new ObjectInputStream(new FileInputStream(rutaFichero));
			while (true) {
				Equipo equipos = (Equipo) objetos.readObject();
				System.out.println(equipos.getNombreEquipo());
			}
		} catch (FileNotFoundException e) {
			System.out.println("fichero no encontrado");
		} catch (ClassNotFoundException e) {
			System.out.println("clase no encontrada");
		} catch (IOException e) {
			System.out.println("IO Excepcion");
		}

	}

	/*public void leerObjetosEquipos() {
		ObjectInputStream objetos = null;
		try {
			objetos = new ObjectInputStream(new FileInputStream("ficheros/equipos.obj"));

			while (true) {
				Equipo equipo = (Equipo) objetos.readObject();
				System.out.println(equipo.getNombreEquipo());
			}

		} catch (FileNotFoundException e) {
			System.out.println("error1");
		} catch (IOException e) {
			System.out.println("Fin de la lectura");
			try {
				objetos.close();
			} catch (IOException e1) {

			}
		} catch (ClassNotFoundException e) {
			System.out.println("clase no encontrada");
		} catch (java.lang.ClassCastException e) {
			System.out.println("Casting imposible");
		}

	}*/
}
