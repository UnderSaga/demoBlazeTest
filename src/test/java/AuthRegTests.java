import models.requestModels.AuthRegRequest;
import models.responseModels.AuthRegResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRegTests extends BaseTest{
    @Test(description = "Регистрация пользователя")
    public void userAlreadyExistTest(){
        AuthRegRequest regSuccessfulRequest = new AuthRegRequest("NikolayNov", "qwerty");
        AuthRegResponse successfulRegistration = API_STEPS.userRegistration(baseURL, regSuccessfulRequest, 200);
        AuthRegResponse checkSuccessfulRegistration = new AuthRegResponse(null, "This user already exist.");
        Assert.assertEquals(successfulRegistration, checkSuccessfulRegistration);
    }

    @Test(description = "Регистрация пользователя")
    public void registrationSuccessful(){
        AuthRegRequest regSuccessfulRequest = new AuthRegRequest("NikolayNov1", "qwerty");
        String successfulRegistration = API_STEPS.userRegSuccessful(baseURL, regSuccessfulRequest, 200).replaceAll("\\s", " ");
        Assert.assertEquals(successfulRegistration, "\"\" ");
    }

    @Test(description = "Авторизация пользователя")
    public void authSuccessfulTest(){
        AuthRegRequest authSuccessfulRequest = new AuthRegRequest("NikolayNov", "qwerty");
        String successfulAuthorization = API_STEPS.userAuthorization(baseURL, authSuccessfulRequest, 200).replaceAll("Auth_token:", "").replaceAll("\\s+", " ");
        Assert.assertEquals(successfulAuthorization, "\" Tmlrb2xheU5vdjE3MDM3NTg=\" ");
    }

    @Test(description = "Авторизация пользователя")
    public void authUnsuccessfulTest(){
        AuthRegRequest authSuccessfulRequest = new AuthRegRequest("NikolayNov", "qwertyp");
        AuthRegResponse successfulAuthorization = API_STEPS.userAuthorizationSuccessful(baseURL, authSuccessfulRequest, 200);
        AuthRegResponse checkSuccessfulAuthorization = new AuthRegResponse(null, "Wrong password.");
        Assert.assertEquals(successfulAuthorization, checkSuccessfulAuthorization);
    }
}
