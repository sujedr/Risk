package risk.model;

public class Regiment {
	
	private String type;
	private int nbRegiment;

	public Regiment(String type, int nbRegiment) {
		this.type = type;
		this.nbRegiment = nbRegiment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNbRegiment() {
		return nbRegiment;
	}

	public void setNbRegiment(int nbRegiment) {
		this.nbRegiment = nbRegiment;
	}

}