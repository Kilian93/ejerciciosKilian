package control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

import modelo.Equipo;

public class Ejercicios {
	public void crearFicheroObjetoEquipos() {
		
			BufferedReader fichero;
			try {
				fichero = new BufferedReader(new FileReader("ficheros/equipos.txt"));
				FileOutputStream salida = new FileOutputStream("ficheros/equipos.obj");
				ObjectOutputStream ob = new ObjectOutputStream(salida);
				String registro;
				while ((registro = fichero.readLine()) != null) {
					String[] campos = registro.split("#");
					Equipo equipo = new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]);
					equipo.setGolesContra(0);
					equipo.setGolesFavor(0);
					equipo.setEmpate(0);
					equipo.setVictoria(0);
					equipo.setDerrota(0);
					equipo.setPuntos(0);
					ob.writeObject(equipo);
				}
				fichero.close();
				System.out.println("Fin de la lectura del fichero");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}

}
