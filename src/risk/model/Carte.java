package risk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Carte {
	
	private String territoire;
    private String typeRegiment;
    
    public Carte(String territoire, String typeRegiment) {
        this.territoire = territoire;
        this.typeRegiment = typeRegiment;
    }
    
    public String getTerritoire() {
        return territoire;
    }
    
    public String getTypeRegiment() {
        return typeRegiment;
    }
    
    public String toString() {
        return "|RISK| Territoire : " + this.territoire + ", Type de Régiment : " + this.typeRegiment;
    }

}
