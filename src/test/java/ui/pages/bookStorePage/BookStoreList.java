package ui.pages.bookStorePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.PageBase;
import ui.wait.Wait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStoreList extends PageBase {
    public BookStoreList(WebDriver driver) {
        super(driver);
    }

    Wait wait;


    @FindBy(id = "searchBox")
    WebElement searchBoxElement;
    @FindBy(xpath = "(//*[@class='rt-resizable-header-content'])[3]")
    WebElement authorElement;
    @FindBy(xpath = "(//*[@class='rt-resizable-header-content'])[4]")
    WebElement publisherElement;


    @FindBy(xpath = "(//div[@class='rt-tr-group'])[1]//div[@class='rt-td']")
    public List<WebElement> elementsOfFirstBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[2]//div[@class='rt-td']")
    public List<WebElement> elementsOfSecondBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[3]//div[@class='rt-td']")
    public List<WebElement> elementsOfThirdBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[4]//div[@class='rt-td']")
    public List<WebElement> elementsOfFourthBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[5]//div[@class='rt-td']")
    public List<WebElement> elementsOfFifthBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[6]//div[@class='rt-td']")
    public List<WebElement> elementsOfSixthBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[7]//div[@class='rt-td']")
    public List<WebElement> elementsOfSeventhBook;
    @FindBy(xpath = "(//div[@class='rt-tr-group'])[8]//div[@class='rt-td']")
    public List<WebElement> elementsOfEighthBook;


    public void goToBookStorePage() { // что б сразу переходить на страницу BookStore без отработки Registration - > Login-> Profile
        driver.get("https://demoqa.com/books");
        wait = new Wait(driver);
        wait.forVisibility(searchBoxElement);
    }


    public void waitForLoadingListOfAllBooks() {
        wait = new Wait(driver);
        wait.forListVisibility(elementsOfFirstBook);
        wait.forListVisibility(elementsOfSecondBook);
        wait.forListVisibility(elementsOfThirdBook);
        wait.forListVisibility(elementsOfFourthBook);
        wait.forListVisibility(elementsOfFifthBook);
        wait.forListVisibility(elementsOfSixthBook);
        wait.forListVisibility(elementsOfSeventhBook);
        wait.forListVisibility(elementsOfEighthBook);
    }

    public void checkCompareBookInSearchBoxViaBookInList(String expectedWord) {

        List<String> firstBookElements = new ArrayList<>();

        firstBookElements.add(elementsOfFirstBook.get(1).getText());
        firstBookElements.add(elementsOfFirstBook.get(2).getText());
        firstBookElements.add(elementsOfFirstBook.get(3).getText());

        System.out.println("1 список " + firstBookElements);

        List<String> secondBookElements = new ArrayList<>();

        secondBookElements.add(elementsOfSecondBook.get(1).getText());
        secondBookElements.add(elementsOfSecondBook.get(2).getText());
        secondBookElements.add(elementsOfSecondBook.get(3).getText());

        System.out.println("2 список " + secondBookElements);

        List<String> thirdBookElements = new ArrayList<>();

        thirdBookElements.add(elementsOfThirdBook.get(1).getText());
        thirdBookElements.add(elementsOfThirdBook.get(2).getText());
        thirdBookElements.add(elementsOfThirdBook.get(3).getText());

        System.out.println("3 список " + thirdBookElements);

        List<String> fourthBookElements = new ArrayList<>();

        fourthBookElements.add(elementsOfFourthBook.get(1).getText());
        fourthBookElements.add(elementsOfFourthBook.get(2).getText());
        fourthBookElements.add(elementsOfFourthBook.get(3).getText());

        System.out.println("4 список " + fourthBookElements);

        List<String> fifthBookElements = new ArrayList<>();

        fifthBookElements.add(elementsOfFifthBook.get(1).getText());
        fifthBookElements.add(elementsOfFifthBook.get(2).getText());
        fifthBookElements.add(elementsOfFifthBook.get(3).getText());

        System.out.println("5 список " + fifthBookElements);

        List<String> sixthBookElements = new ArrayList<>();

        sixthBookElements.add(elementsOfSixthBook.get(1).getText());
        sixthBookElements.add(elementsOfSixthBook.get(2).getText());
        sixthBookElements.add(elementsOfSixthBook.get(3).getText());

        System.out.println("6 список " + sixthBookElements);

        List<String> seventhBookElements = new ArrayList<>();

        seventhBookElements.add(elementsOfSeventhBook.get(1).getText());
        seventhBookElements.add(elementsOfSeventhBook.get(2).getText());
        seventhBookElements.add(elementsOfSeventhBook.get(3).getText());

        System.out.println("7 список " + seventhBookElements);

        List<String> eighthBookElements = new ArrayList<>();

        eighthBookElements.add(elementsOfEighthBook.get(1).getText());
        eighthBookElements.add(elementsOfEighthBook.get(2).getText());
        eighthBookElements.add(elementsOfEighthBook.get(3).getText());

        System.out.println("8 список " + eighthBookElements);

        List<String> actualNames = new ArrayList<>();

        actualNames.addAll(firstBookElements);
        actualNames.addAll(secondBookElements);
        actualNames.addAll(thirdBookElements);
        actualNames.addAll(fourthBookElements);

        System.out.println("Oбщий список " + actualNames);

        fillField(searchBoxElement, expectedWord);
        String actualWord = searchBoxElement.getText();

        boolean isBookFound = false;
        for (String book : actualNames) {
            if (book.toLowerCase().contains(actualWord.toLowerCase())) {
                isBookFound = true;
                break;
            }
        }
        if (isBookFound) {
            System.out.println("Верная книга найдена по слову \" " + expectedWord + " \".");
        } else {
            System.out.println("Верная книга не найдена по слову \" " + expectedWord + " \".");
        }
    }


    public void waitForLoadingAuthorElement() {
        wait = new Wait(driver);
        wait.forVisibility(authorElement);
    }

    public void clickOnAuthor() {
        click(authorElement);
    }

    public boolean checkSortAlphabeticallyForAuthor() {
        List<String> actualNames = new ArrayList<>();

        actualNames.add(elementsOfFirstBook.get(2).getText());
        actualNames.add(elementsOfSecondBook.get(2).getText());
        actualNames.add(elementsOfThirdBook.get(2).getText());
        actualNames.add(elementsOfFourthBook.get(2).getText());
        actualNames.add(elementsOfFifthBook.get(2).getText());
        actualNames.add(elementsOfSixthBook.get(2).getText());
        actualNames.add(elementsOfSeventhBook.get(2).getText());
        actualNames.add(elementsOfEighthBook.get(2).getText());


        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        return actualNames.equals(expectedNames);
    }


    public void waitForLoadingPublisherElement() {
        wait = new Wait(driver);
        wait.forVisibility(publisherElement);
    }

    public void clickOnPublisher() {
        click(publisherElement);
    }

    public boolean checkSortAlphabeticallyForPublisher() {
        List<String> actualNames = new ArrayList<>();

        actualNames.add(elementsOfFirstBook.get(3).getText());
        actualNames.add(elementsOfSecondBook.get(3).getText());
        actualNames.add(elementsOfThirdBook.get(3).getText());
        actualNames.add(elementsOfFourthBook.get(3).getText());
        actualNames.add(elementsOfFifthBook.get(3).getText());
        actualNames.add(elementsOfSixthBook.get(3).getText());
        actualNames.add(elementsOfSeventhBook.get(3).getText());
        actualNames.add(elementsOfEighthBook.get(3).getText());


        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        return actualNames.equals(expectedNames);
    }
}
