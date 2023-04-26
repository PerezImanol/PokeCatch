package factories;

import controller.SimulableDBimplementation;
import interfaces.Simulable;

public class SimulableFactory {
    private static Simulable obj = new SimulableDBimplementation();
    
    public static Simulable getSimulable() {
        return obj;
    }
}
