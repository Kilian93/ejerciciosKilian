package modelo;

public class Jugador extends Persona {
	private int idJugador;
	private int dorsal;
	private int idEquipo;

	public Jugador() {
	}

	public Jugador(String nombre, int idJugador, int dorsal, int idEquipo) {
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
		return "Jugador [idJugador=" + idJugador + ", dorsal=" + dorsal + ", idEquipo=" + idEquipo + "]";
	}

	
	
	

}
