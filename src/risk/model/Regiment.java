package risk.model;

public class Regiment {
	
	private TypeRegiment typeRegiment;
	private int nbRegiment;

	public Regiment(TypeRegiment type, int nbRegiment) {
		this.typeRegiment = type;
		this.nbRegiment = nbRegiment;
	}

	public TypeRegiment getType() {
		return typeRegiment;
	}

	public void setType(TypeRegiment type) {
		this.typeRegiment = typeRegiment;
	}

	public int getNbRegiment() {
		return nbRegiment;
	}

	public void setNbRegiment(int nbRegiment) {
		this.nbRegiment = nbRegiment;
	}

}