package factories;

import controller.DataBattleShowableDBimplementation;
import interfaces.DataBattleShowable;

public class DataBattleFactory {
    private static DataBattleShowable obj = new DataBattleShowableDBimplementation();

    public static DataBattleShowable getDataBattleShowable(){
        return obj;
    }
}
