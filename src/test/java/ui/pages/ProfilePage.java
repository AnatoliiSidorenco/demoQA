package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

    @FindBy(xpath = "(//*[@id='submit'])[1]")
    WebElement logOutButton;


    @FindBy(css = "[class='rt-tr-group']")
    public List<WebElement> listForAddedBooksInProfile;

    @FindBy(css = "[aria-label='rows per page']")
    WebElement selectRowsPerPage;

    @FindBy(id = "delete-record-undefined")
    WebElement deleteBook;

    @FindBy(id = "closeSmallModal-cancel")
    WebElement deleteBookCancel;

    @FindBy(id = "closeSmallModal-ok")
    WebElement deleteBookOk;

    @FindBy(xpath = "(//*[@class='btn btn-primary'])[4]")
    WebElement deleteAllBooksButton;

    @FindBy(xpath = "(//*[@class='btn btn-primary'])[3]")
    WebElement deleteAccountButton;





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
        Wait wait = new Wait(driver);
        wait.forVisibility(gotoStoreButton);
        click(gotoStoreButton);
    }

    public void clickOnLogOutButton() {
        click(logOutButton);
    }

    public void waitForLoadingAddedBooks() {
        wait = new Wait(driver);
        wait.forListVisibility(listForAddedBooksInProfile);
    }

    public String getTextOfAddedBook() {
        return listForAddedBooksInProfile.get(0).getText();
    }

    public boolean checkProfileIsEmpty() {
        return listForAddedBooksInProfile.get(0).isDisplayed();
    }

    public boolean listOfBooksInProfileIsEmpty() {
        boolean isNotDisplayed = true;
        if (!listForAddedBooksInProfile.get(0).isDisplayed()) {
            isNotDisplayed = false;

        }
        return isNotDisplayed;
    }

    public void selectRows(String inputValue) {
        wait = new Wait(driver);
        wait.forVisibility(selectRowsPerPage);
        Select select = new Select(selectRowsPerPage);
        select.selectByValue(inputValue);
        select.selectByIndex(0);
        select.selectByVisibleText("20 rows");
    }

    public void deleteBook(){
        wait = new Wait(driver);
        wait.forVisibility(deleteBook);
        deleteBook.click();
    }
    public void clickOnCancelInModalWindow(){
        wait = new Wait(driver);
        wait.forVisibility(deleteBookCancel);
        deleteBookCancel.click();
    }
    public void clickOnOkInModalWindow(){
        wait = new Wait(driver);
        wait.forVisibility(deleteBookOk);
        deleteBookOk.click();
    }
    public void clickOnButtonGoToStoreOnProfile(){
        wait = new Wait(driver);
        wait.forVisibility(gotoStoreButton);
       gotoStoreButton.click();
    }

    public void clickOnButtonDeleteAllBooksButton(){
        wait = new Wait(driver);
        wait.forVisibility(deleteAllBooksButton);
        deleteAllBooksButton.click();
    }

    public void clickOnButtonDeleteAccount(){
        wait = new Wait(driver);
        wait.forVisibility(deleteAccountButton);
        deleteAccountButton.click();
    }


}
