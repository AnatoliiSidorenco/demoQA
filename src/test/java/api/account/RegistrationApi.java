package api.account;

import api.ApiBase;
import api.models.RegistrationDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class RegistrationApi extends ApiBase {

    Response response;
    RegistrationDto dto;
    Faker faker = new Faker();


    public RegistrationDto randomDataBodyForRegisterUser(String password) {
        dto = new RegistrationDto();
        dto.setUserName(faker.name().username());
        dto.setPassword(password);
        return dto;
    }

    public Response registerUser(Integer code, String password) {
        String endpoint = "/Account/v1/User";
        response = postRequest(endpoint, code, randomDataBodyForRegisterUser(password));
        return response;
    }

    public void deleteUser(Integer code, String userId) {
        String endpoint = "/Account/v1/User/{UUID}";
        deleteRequest(endpoint, code, userId);
    }

    public Response getUserData(Integer code, String userId) {
        String endpoint = "/Account/v1/User/{UUID}";
        response = getRequestWithParam(endpoint, code, "userId",userId);
        return response;
    }

}
