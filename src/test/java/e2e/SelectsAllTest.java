package e2e;

import org.testng.annotations.Test;
import ui.pages.DataPicker;
import ui.pages.SelectPages;
import ui.TestBase;

public class SelectsAllTest extends TestBase {
SelectPages selectPage;

@Test
public void checkSelectValue(){
    selectPage = new SelectPages(app.driver);
    selectPage.goToSelectPage();
    selectPage.selectInFirstInput("Group 1, options 2");
}

    @Test
    public void checkSelectValue1(){
        selectPage = new SelectPages(app.driver);
        selectPage.goToSelectPage();
        selectPage.selectOld("8");
}
    @Test
    public void checkMultiSelectValue()  {
        selectPage = new SelectPages(app.driver);
        selectPage.goToSelectPage();
        selectPage.selectMulti();
    }
    @Test
    public void checkSelectValue2(){
        selectPage = new SelectPages(app.driver);
        selectPage.goToSelectPage();
        selectPage.selectInSecond("Prof.");
    }

    @Test
    public void checkDateInputCorrect() throws InterruptedException {
        DataPicker dataPicker = new DataPicker(app.driver);
        dataPicker.goToDataPickerPage();
        dataPicker.inputDate("03/10/1985");
    }

}
