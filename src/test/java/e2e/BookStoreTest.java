package e2e;

import api.account.RegistrationApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import ui.TestBase;
import ui.pages.LoginPage;
import ui.pages.ProfilePage;
import ui.pages.bookStorePage.BookStoreElements;
import ui.pages.bookStorePage.BookStoreList;

import static org.testng.AssertJUnit.assertTrue;

public class BookStoreTest extends TestBase {
    RegistrationApi registrationApi;
    Response response;
    LoginPage loginPage;
    ProfilePage profilePage;
    BookStoreElements bookStoreElements;
    BookStoreList bookStoreList;


    //todo-- Полный цикл Login - проверка - Logout  и  прверка видимости и граматика всех елементов---------------------------------------------------------
    @Test
    public void spellCheckAllElementsTest() {
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
        bookStoreElements.waitForLoadingMainHeaderElement();
        bookStoreElements.checkMainHeaderText("Book Store");
        bookStoreElements.waitForLoadingTextTypeToSearchElement();
        bookStoreElements.checkTextTypeToSearchElement();
        bookStoreElements.waitForLoadingUserNameElement();
        bookStoreElements.checkUserNameElement("User Name :");
        bookStoreElements.waitForLoadingUserName_ValueElement();
        bookStoreElements.checkUserName_ValueElement(userName);
        bookStoreElements.waitForLoadingLogoutButtonElement();
        bookStoreElements.checkLogOutButtonName("Log out");
        bookStoreElements.clickOnLogoutButton();
    }

    @Test
    public void checkElementsAreActiveTest() {
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
        bookStoreElements.waitForLoadingSearchBoxElement();
        bookStoreElements.checkSearchBoxFieldIsActive();
        bookStoreElements.waitForLoadingLogoutButtonElement();
        bookStoreElements.checkLogOutButtonIsActive();
        bookStoreElements.clickOnLogoutButton();
    }
    //todo-----------------------------------------------------------------------------------------------------


    //todo -- Ниже указаны тесты используя метод сразу перехода на страницу, что б сконцентрироваться на тестах----


    //todo ------ Проверяю возможность найти книгу по вводимому  Названию или по Автору или по Издателю--------
    @Test
    public void checkFindBookByEnteringNameInSearchBoxTest() {
        bookStoreList = new BookStoreList(app.driver);
        bookStoreList.goToBookStorePage();
        bookStoreList.waitForLoadingListOfAllBooks();
        String expectedWord = "Nicholas C. Zakas";//todo слово которое я ввожу для поиска
        bookStoreList.checkCompareBookInSearchBoxViaBookInList(expectedWord);
        pause(3000);
    }


// todo ------ Проверяю или работает сортировка по Author и по Publisher --------

    @Test
    public void checkSortingByAuthorAndPublisherTest() {
        bookStoreList = new BookStoreList(app.driver);
        bookStoreList.goToBookStorePage();

        bookStoreList.waitForLoadingAuthorElement(); // By Author
        bookStoreList.clickOnAuthor();
        assertTrue(bookStoreList.checkSortAlphabeticallyForAuthor());

        bookStoreList.waitForLoadingPublisherElement(); // By Publisher
        bookStoreList.clickOnPublisher();
        assertTrue(bookStoreList.checkSortAlphabeticallyForPublisher());
    }

    // todo ---------Проверка что у всех книгах названия активные------------

    @Test
    public void checkAllBooksLinkAreActiveTest() {
        bookStoreElements = new BookStoreElements(app.driver);
        bookStoreElements.goToBookStorePage();
        bookStoreElements.checkAllBooksLinkIsEnabled();
        pause(1000);
    }



    //todo -- Проверка что добавив книгу и нажав добавить --  книга появиться в профиле
    @Test
    public void checkIfBookAfterAddingAppearsInProfile() throws InterruptedException {
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

        bookStoreElements.alert(); // alert то есть на странице, то нету, то сайт виснет, то не хочет кликнуть на alert
                                    // Но тест проходит и я выслал Скриншот в подтверждение, вот только проверил тест и он сработал
        pause(1000);
        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        pause(1000);
        profilePage.getTextOfAddedBook();
        assertTrue(profilePage.getTextOfAddedBook().contains("Learning JavaScript Design Patterns"));
    }


    //todo -- Проверка что добавив книгу я могу вернутся назад и в Profile ничего не добавиться
    @Test
    public void checkIfBookNotAppearsInProfileAfterClickOnButtonBackToBookStore() throws InterruptedException {
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
        bookStoreElements.waitForLoadingBackToBookStoreAndClick();

        profilePage = new ProfilePage(app.driver);
        profilePage.goToProfilePage();
        profilePage.waitForLoadingAddedBooks();
        profilePage.listOfBooksInProfileIsEmpty();
      //  assertTrue(profilePage.listOfBooksInProfileIsEmpty());
    }
}
