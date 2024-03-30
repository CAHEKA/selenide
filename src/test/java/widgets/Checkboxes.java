package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Checkboxes {

    private final SelenideElement checkboxes = $x("//form[@id='checkboxes']");
    
    public Checkboxes() {
        Selenide.open("https://the-internet.herokuapp.com/checkboxes");
        checkboxes.should(visible, Duration.ofSeconds(30));
    }

    @Step("Select checkbox {text}")
    public Checkboxes selectCheckbox(String text){
        String xpath = String.format(".//input[contains(following-sibling::text(), '%s')]", text);
        checkboxes.find(By.xpath(xpath)).click();
        System.out.println("Checked attribute : " + checkboxes.find(By.xpath(xpath)).isSelected());
       return this; 
    }

    @Step("Wait result: Checkbox {name} state {state}")
    public Checkboxes checkStateCheckbox(String name, Boolean state){
        String xpath = String.format(".//input[contains(following-sibling::text(), '%s')]", name);
        Assertions.assertEquals(checkboxes.find(By.xpath(xpath)).isSelected(), state);;
        return this;
    }
    
}
