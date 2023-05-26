package ui.pages.bookStorePage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.pages.PageBase;
import ui.wait.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

public class BookStoreElements extends PageBase {
    public BookStoreElements(WebDriver driver) {
        super(driver);
    }

    Wait wait;

    //todo ------------------------------------------------------------
    @FindBy(className = "main-header")
    public WebElement mainHeaderElement;
    @FindBy(id = "searchBox")
    WebElement searchBoxElement;
    @FindBy(xpath = "//input[contains(@placeholder,'Type to search')]")
    WebElement typeToSearchElement;
    @FindBy(id = "userName-label")
    WebElement userNameElement;
    @FindBy(id = "userName-value")
    WebElement userName_ValueElement;
    @FindBy(id = "submit")
    public WebElement logoutButtonElement;

    //todo---------------------------------------------------------------------
    @FindBy(css = "[id='see-book-Git Pocket Guide']")
    public WebElement bookGitPocketGuideElement;
    @FindBy(css = "[id='see-book-Learning JavaScript Design Patterns']")
    public WebElement bookLearningJavaScriptElement;
    @FindBy(css = "[id='see-book-Designing Evolvable Web APIs with ASP.NET']")
    public WebElement bookDesigningEvolvableWebAPIsElement;
    @FindBy(css = "[id='see-book-Speaking JavaScript']")
    public WebElement bookSpeakingJavaScriptElement;
    @FindBy(css = "[id='see-book-You Don't Know JS']")
    public WebElement bookYouDontKnowJSElement;
    @FindBy(css = "[id='see-book-Programming JavaScript Applications']")
    public WebElement bookProgrammingJavaScriptApplicationsElement;
    @FindBy(css = "[id='see-book-Eloquent JavaScript, Second Edition']")
    public WebElement bookEloquentJavaScriptSecondEditionElement;
    @FindBy(css = "[id='see-book-Understanding ECMAScript 6']")
    public WebElement bookUnderstandingECMAScript6Element;


    @FindBy(xpath = "(//button[@id='addNewRecordButton'])[2]")
    public WebElement buttonAddToYourCollection;

    @FindBy(xpath = "(//button[@id='addNewRecordButton'])[1]")
    public WebElement buttonBackToBookStore;


    public void goToBookStorePage() { // что б сразу переходить на страницу BookStore без отработки Registration - > Login-> Profile
        driver.get("https://demoqa.com/books");
        wait = new Wait(driver);
        wait.forVisibility(searchBoxElement);
    }

    public void waitForLoadingMainHeaderElement() {
        wait = new Wait(driver);
        wait.forVisibility(mainHeaderElement);
    }

    public void checkMainHeaderText(String expectedMainHeaderText) {
        checkItemText(mainHeaderElement, expectedMainHeaderText, "mainHeaderElementText is not equal to expected");
    }


    public void waitForLoadingSearchBoxElement() {
        wait = new Wait(driver);
        wait.forVisibility(searchBoxElement);
    }

    public void checkSearchBoxFieldIsActive() {
        checkElementIsActive(searchBoxElement);
    }

    public void waitForLoadingTextTypeToSearchElement() {
        wait = new Wait(driver);
        wait.forVisibility(typeToSearchElement);
    }

    public void checkTextTypeToSearchElement() {
        Assert.assertTrue(typeToSearchElement.getAttribute("placeholder").contains("Type to search"));
    }

    public void waitForLoadingUserNameElement() {
        wait = new Wait(driver);
        wait.forVisibility(userNameElement);
    }

    public void checkUserNameElement(String expectedUserNameElement) {
        checkItemText(userNameElement, expectedUserNameElement, "userNameElementText is not equal to expected");
    }

    public void waitForLoadingUserName_ValueElement() {
        wait = new Wait(driver);
        wait.forVisibility(userName_ValueElement);
    }

    public void checkUserName_ValueElement(String expectedUserNameElement) {
        checkItemText(userName_ValueElement, expectedUserNameElement, "UserNameElementText is not equal to expected");
    }


    public void waitForLoadingLogoutButtonElement() {
        wait = new Wait(driver);
        wait.forVisibility(logoutButtonElement);
    }

    public void checkLogOutButtonIsActive() {
        checkElementIsActive(logoutButtonElement);
    }

    public void checkLogOutButtonName(String expectedLogOutButtonName) {
        checkItemText(logoutButtonElement, expectedLogOutButtonName, "LogoutButtonName is not equal to expected");
    }

    public void clickOnLogoutButton() {
        click(logoutButtonElement);
    }

    public void checkAllBooksLinkIsEnabled() {

        List<WebElement> actualElements = new ArrayList<>();

        actualElements.add(bookGitPocketGuideElement);
        actualElements.add(bookLearningJavaScriptElement);
        actualElements.add(bookDesigningEvolvableWebAPIsElement);
        actualElements.add(bookSpeakingJavaScriptElement);
        actualElements.add(bookProgrammingJavaScriptApplicationsElement);
        actualElements.add(bookEloquentJavaScriptSecondEditionElement);
        actualElements.add(bookUnderstandingECMAScript6Element);

        boolean isEnabled = false;
        for (WebElement element : actualElements) {
            if (element.isEnabled()) {
                isEnabled = true;
            }
        }

        if (isEnabled) {
            System.out.println("Ссылки  активны");
        } else {
            System.out.println("Ссылки не активны");
        }
    }


    public void waitForLoadingBackToBookStoreAndClick() {
        wait = new Wait(driver);
        wait.forVisibility(buttonBackToBookStore);
        click(buttonBackToBookStore);
    }

    public void waitForLoadingAddToYourCollectionAndClick() {
        wait = new Wait(driver);
        wait.forVisibility(buttonAddToYourCollection);
        click(buttonAddToYourCollection);
    }

    public WebElement listOfBooksNameOnBookStorePage(String bookName) throws InterruptedException {

        List<WebElement> actualElements = new ArrayList<>();

        actualElements.add(bookGitPocketGuideElement);
        actualElements.add(bookLearningJavaScriptElement);
        actualElements.add(bookDesigningEvolvableWebAPIsElement);
        actualElements.add(bookSpeakingJavaScriptElement);
        actualElements.add(bookProgrammingJavaScriptApplicationsElement);
        actualElements.add(bookEloquentJavaScriptSecondEditionElement);
        actualElements.add(bookUnderstandingECMAScript6Element);

        for (WebElement element : actualElements) {
            String elementText = element.getText();
            if (elementText.equals(bookName)) {
              return  element;
            }
        }

        throw new NoSuchElementException("Книга '" + bookName + "' не найдена в списке элементов.");
    }

    public void alert() {
        wait = new Wait(driver);
        wait.setWait().until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

    }
   /* public void alert() {
        wait = new Wait(driver);
        wait.setWait().until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        System.out.println(text);
        driver.switchTo().alert().accept();
        assertTrue(text.contains("Book added to your collection."));
    }*/
}


