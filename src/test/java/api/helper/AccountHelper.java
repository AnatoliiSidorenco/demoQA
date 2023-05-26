package api.helper;

import api.account.RegistrationApi;
import io.restassured.response.Response;
import org.testng.Assert;

public class AccountHelper extends RegistrationApi {

    public String registerNewUser() {
        String password = "yA*UeeuA2pU3";
        Response actualResponse = registerUser(201,password);
        String userId = actualResponse.jsonPath().getString("userId");
        Response expectedResponse = getUserData(200, userId);
        Assert.assertEquals(actualResponse.jsonPath().getString("username"), expectedResponse.jsonPath().getString("username"), "username not equal");


        return userId;
    }

    public void deleteExistedUser(String userId) {
        deleteUser(200, userId);
        Response errorMessage = getUserData(500, userId);
        //Assert.assertEquals(errorMessage.jsonPath().getString("message"), "Error! This address doesn't exist in our DB");
    }

}
