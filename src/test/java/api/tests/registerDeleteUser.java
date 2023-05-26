package api.tests;

import api.helper.AccountHelper;
import org.testng.annotations.Test;

import java.util.UUID;

public class registerDeleteUser {
    AccountHelper accountHelper = new AccountHelper();

    @Test
    public void registerDeleteUserCycle() {
           String userId = accountHelper.registerNewUser();
         //  accountHelper.registerUser(201);
        accountHelper.deleteExistedUser(userId);
    }
}


