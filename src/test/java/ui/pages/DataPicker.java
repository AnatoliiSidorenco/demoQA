package ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.wait.Wait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertEquals;

public class DataPicker extends PageBase{
    public DataPicker(WebDriver driver) {
        super(driver);
    }

    Wait wait;

    @FindBy(id = "datePickerMonthYearInput")
    WebElement datePickerMonthYearInput;

    public void goToDataPickerPage() throws InterruptedException {
        driver.get("https://demoqa.com/date-picker");
        wait = new Wait(driver);
        wait.forVisibility(datePickerMonthYearInput);
    }

    public void inputDate(String expectedinputValue) throws InterruptedException {
        wait.forVisibility(datePickerMonthYearInput);
        sleep(1000);
        click(datePickerMonthYearInput);
        sleep(1000);
       // datePickerMonthYearInput.clear();
        datePickerMonthYearInput.sendKeys(Keys.CONTROL + "a");
        datePickerMonthYearInput.sendKeys(Keys.DELETE);
        sleep(1000);
        datePickerMonthYearInput.sendKeys(expectedinputValue);
        sleep(1000);
        datePickerMonthYearInput.sendKeys(Keys.ENTER);
        sleep(1000);

        String dateString = datePickerMonthYearInput.getAttribute("value");

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String actualImputedDate = date.format(outputFormatter);
        System.out.println(dateString + " Дата - 1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(actualImputedDate + " дата преобр !!!!!!!!!!");

        assertEquals(actualImputedDate, expectedinputValue, "Date is not Equal");
    }

}
