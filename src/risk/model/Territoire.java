package risk.model;

public class Territoire {
	private int numero;
	private String nom;
	private int centreX;
	private int centreY;
	
	public Territoire(int numero, String nom, int centreX, int centreY) {
		this.numero = numero;
		this.nom = nom;
		this.centreX = centreX;
		this.centreY = centreY;
	}
	
    public boolean isInTerritory(int x, int y, int tolerance) {
        int distance = (int) Math.sqrt(Math.pow(x - centreX, 2) + Math.pow(y - centreY, 2));
        return distance <= tolerance;
    }

    public int getNumber() {
        return this.numero;
    }
    
    public String getNom() {
    	return this.nom;
    }
}
