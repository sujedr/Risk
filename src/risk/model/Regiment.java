package risk.model;

public class Regiment {
	
	private String typeRegiment;
	private int nbRegiment;

	public Regiment(String type, int nbRegiment) {
		this.typeRegiment = type;
		this.nbRegiment = nbRegiment;
	}

	public String getType() {
		return typeRegiment;
	}

	public void setType(String type) {
		this.typeRegiment = typeRegiment;
	}

	public int getNbRegiment() {
		return nbRegiment;
	}

	public void setNbRegiment(int nbRegiment) {
		this.nbRegiment = nbRegiment;
	}

}