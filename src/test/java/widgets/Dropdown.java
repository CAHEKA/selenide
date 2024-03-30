package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {

    private final SelenideElement dropdown = $x("//select[@id='dropdown']");

    public Dropdown() {
        Selenide.open("https://the-internet.herokuapp.com/dropdown");
        dropdown.should(visible, Duration.ofSeconds(30));
    }

    @Step("Select dropdown num {num}")
    public Dropdown selectDropdownByNum(String num) {
        dropdown.selectOptionByValue(num);
        printCurrentElementText();
        return this;
    }
    private Dropdown printCurrentElementText(){
        System.out.println("Current element text: " + dropdown.getSelectedOption().getText());
        return this;
    }
    @Step("Check state dropdown: {state}")
    public Dropdown checkState(String state){
        Assertions.assertEquals(dropdown.getSelectedOption().getText(), state);
        return this;
    }
    
}
