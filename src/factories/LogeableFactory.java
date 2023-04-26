package factories;

import controller.LogeableDBimplementation;
import interfaces.Logeable;

public class LogeableFactory {
    private static Logeable obj = new LogeableDBimplementation();

    public static Logeable getLogeable() {
        return obj;
    }
}
