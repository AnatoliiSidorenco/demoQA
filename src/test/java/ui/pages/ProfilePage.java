package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.wait.Wait;

import java.util.List;


public class ProfilePage extends PageBase {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    Wait wait;
    @FindBy(css = "[class='main-header']")
    WebElement mainHeaderProfile;

//(//label[@id='userName-label'])[1]
    @FindBy(id = "userName-value")
    WebElement username;

    @FindBy(id = "gotoStore")
    WebElement gotoStoreButton;

    @FindBy(css = "[class='rt-tr-group']")
    public List<WebElement> listForAddedBooksInProfile;


    public void goToProfilePage() {
        driver.get("https://demoqa.com/profile");
        wait = new Wait(driver);
        wait.forVisibility(mainHeaderProfile);
    }


    public void waitForLoading() {
       wait = new Wait(driver);
        wait.forVisibility(username);
    }

    public void checkUsername(String expectedUsername) {
        checkItemText(username, expectedUsername, "Username is not equal to expected");
    }

    public void clickOnGotoStoreButton() {
        click(gotoStoreButton);
    }
public void waitForLoadingAddedBooks(){
    wait = new Wait(driver);
    wait.forListVisibility(listForAddedBooksInProfile);
}
    public String getTextOfAddedBook(){
        return listForAddedBooksInProfile.get(0).getText();
    }

    public void listOfBooksInProfileIsEmpty() {
        boolean isDisplayed = false;
        for (WebElement name : listForAddedBooksInProfile) {
            if (name.isDisplayed()) {
                isDisplayed = true;
                break;
            }
        }
        if (isDisplayed) {
            System.out.println("У вас нет добавленых книг");
        } else {
            System.out.println("метод не работает");
        }
    }
}
