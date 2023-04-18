package classes;

import java.util.LinkedHashSet;

public class Professor extends Trainer {

    private String region;
    private LinkedHashSet <Pokemon> initialSelection;
    
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public LinkedHashSet<Pokemon> getInitialSelection() {
        return initialSelection;
    }
    public void setInitialSelection(LinkedHashSet<Pokemon> initialSelection) {
        this.initialSelection = initialSelection;
    }

}
