package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.wait.Wait;

import static java.lang.Thread.sleep;

public class SelectPages extends PageBase {
    Wait wait;

    public SelectPages(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "withOptGroup")
    WebElement withOptGroup;

    @FindBy(id = "react-select-2-input")
    WebElement firstInput;


    @FindBy(id = "selectOne")
    WebElement pickOneTitle;

    @FindBy(id = "react-select-3-input")
    WebElement pickTitleElements;

    @FindBy(xpath = "(//*[@class=' css-2b097c-container'])[3]")
    WebElement mainMultiSelectElement;

    @FindBy(id = "react-select-4-input")
    WebElement multiSelectDropDown;


    public void goToSelectPage() {
        driver.get("https://demoqa.com/select-menu");
        wait = new Wait(driver);
        wait.forVisibility(withOptGroup);
    }

    public void selectInFirstInput(String inputValue) {
        driver.findElement(By.id("withOptGroup")).click();
        wait.forVisibility(firstInput);
        driver.findElement(By.id("react-select-2-input")).sendKeys(inputValue);
        driver.findElement(By.id("react-select-2-input")).sendKeys(Keys.ENTER);
    }

    public void selectOld(String inputValue) {
        WebElement selectElement = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(selectElement);
        select.selectByValue(inputValue);
        select.selectByIndex(0);
        select.selectByVisibleText("Blue");
    }

    public void selectMulti() {
        WebElement selectElement = driver.findElement(By.id("cars"));
        Select select = new Select(selectElement);
        select.selectByValue("opel");
        select.selectByValue("audi");
    }

    public void selectInSecond(String inputValue) {
        click(pickOneTitle);
        wait.forVisibility(pickTitleElements);
        pickTitleElements.sendKeys(inputValue);
        pickTitleElements.sendKeys(Keys.ENTER);
    }

    public void multiSelectDD(String inputValue)  {
        click(mainMultiSelectElement);
        fillField(multiSelectDropDown, inputValue);
        pressKey(multiSelectDropDown, Keys.ENTER);
    }

    public void cleanInput() {
       click(mainMultiSelectElement);
       pressKey(multiSelectDropDown, Keys.valueOf(Keys.CONTROL + "a"));
       pressKey(multiSelectDropDown,Keys.DELETE);
    }


}
