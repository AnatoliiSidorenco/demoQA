package e2e;

import api.account.RegistrationApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ui.TestBase;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;
import ui.pages.bookStorePage.BookStoreElements;
import ui.wait.Wait;

import static org.testng.AssertJUnit.assertTrue;

public class ProfileTest extends TestBase {
    RegistrationApi registrationApi;
    Response response;
    LoginPage loginPage;
    ProfilePage profilePage;
    BookStoreElements bookStoreElements;

    Wait wait;

    @Test
    public void checkSelectRowsPerPage(){
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.selectRows("100");
        profilePage.clickOnLogOutButton();
    }

    @Test
    public void checkBookIsPresentAfterCancellation () throws InterruptedException {
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.checkUsername(userName);
        profilePage.clickOnGotoStoreButton();

        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.goToBookStorePage();
        String bookName = "Learning JavaScript Design Patterns";
        bookStoreElements.listOfBooksNameOnBookStorePage(bookName).click();
        bookStoreElements.waitForLoadingAddToYourCollectionAndClick();

        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        profilePage.deleteBook();
        profilePage.clickOnCancelInModalWindow();
        profilePage.getTextOfAddedBook();
        assertTrue(profilePage.getTextOfAddedBook().contains(bookName));
    }
    @Test
    public void checkBookDeleteAfterConfirmation () throws InterruptedException {
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.checkUsername(userName);
        profilePage.clickOnGotoStoreButton();

        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.goToBookStorePage();
        String bookName = "Learning JavaScript Design Patterns";
        bookStoreElements.listOfBooksNameOnBookStorePage(bookName).click();
        bookStoreElements.waitForLoadingAddToYourCollectionAndClick();

        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        profilePage.deleteBook();
        profilePage.clickOnOkInModalWindow();
        assertTrue(profilePage.checkProfileIsEmpty());
    }


    @Test
    public void checkButtonGoToStore() {
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();


        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.clickOnButtonGoToStoreOnProfile();
        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.waitForLoadingMainHeaderElement();

    }

    @Test
    public void checkBooksArePresentAfterCancellation () throws InterruptedException {
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.checkUsername(userName);
        profilePage.clickOnGotoStoreButton();

        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.goToBookStorePage();
        String bookName = "Learning JavaScript Design Patterns";
        bookStoreElements.listOfBooksNameOnBookStorePage(bookName).click();
        bookStoreElements.waitForLoadingAddToYourCollectionAndClick();

        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        profilePage.clickOnButtonDeleteAllBooksButton();
        profilePage.clickOnCancelInModalWindow();
        assertTrue(profilePage.getTextOfAddedBook().contains("Learning JavaScript Design Patterns"));
    }

    @Test
    public void checkBooksAreDeletedAfterConfirmayion () throws InterruptedException {
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.checkUsername(userName);
        profilePage.clickOnGotoStoreButton();

        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.goToBookStorePage();
        String bookName = "Learning JavaScript Design Patterns";
        bookStoreElements.listOfBooksNameOnBookStorePage(bookName).click();
        bookStoreElements.waitForLoadingAddToYourCollectionAndClick();
        wait = new Wait(app.driver);
        wait.createAlert();
        bookStoreElements.waitForLoadingBackToBookStoreAndClick();

        String bookName2 = "Git Pocket Guide";
        bookStoreElements.listOfBooksNameOnBookStorePage(bookName2).click();
        bookStoreElements.waitForLoadingAddToYourCollectionAndClick();

        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        profilePage.clickOnButtonDeleteAllBooksButton();
        profilePage.clickOnOkInModalWindow();
        wait = new Wait(app.driver);
        wait.createAlert();
        pause(1000);
        assertTrue(profilePage.checkProfileIsEmpty());
    }

    @Test
    public void checkAccountIsDeleted () {  // тест не должен пройти, так как я удаляю Акаунт, но о чудо, он проходит!!!???
        String password = "yA*UeeuA2pU3";
        registrationApi = new RegistrationApi();
        response = registrationApi.registerUser(201, password);
        String userName = response.jsonPath().getString("username");

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();

        profilePage = new ProfilePage(app.driver);
        profilePage.waitForLoading();
        profilePage.checkUsername(userName);
        profilePage.clickOnButtonDeleteAccount();
        profilePage.clickOnOkInModalWindow();


        wait = new Wait(app.driver);
        wait.createAlert();
        pause(1000);

        loginPage = new LoginPage(app.driver);
        loginPage.waitForLoading();
        loginPage.fillLoginForm(userName, password);
        loginPage.clickLoginButton();
    }
}
