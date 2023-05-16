package api.account;

import api.ApiBase;
import api.models.RegistrationDto;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class RegistrationApi extends ApiBase {

    Response response;
    RegistrationDto dto;
    Faker faker = new Faker();


    public RegistrationDto randomDataBodyForRegisterUser() {
        dto = new RegistrationDto();
        dto.setUserName(faker.name().username());
        dto.setPassword("yA*UeeuA2pU3");
        return dto;
    }

    public Response registerUser(Integer code) {
        String endpoint = "/Account/v1/User";
        response = postRequest(endpoint, code, randomDataBodyForRegisterUser());
        return response;
    }

}
