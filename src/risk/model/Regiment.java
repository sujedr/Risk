package risk.model;

public class Regiment {
	
	private String type;
	private int degat;
	private int nombre;
	
	public Regiment(String type, int degat, int nombre) {
		this.type = type;
		this.degat = degat;
		this.nombre = nombre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDegat() {
		return degat;
	}

	public void setDegat(int degat) {
		this.degat = degat;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

}