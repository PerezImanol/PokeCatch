package factories;

import controller.AccountManageableDBimplementation;
import interfaces.AccountManageable;

public class AccountManageableFactory {
    private static AccountManageable obj = new AccountManageableDBimplementation();

    public static AccountManageable getAccountManageable() {
        return obj;
    }

}
