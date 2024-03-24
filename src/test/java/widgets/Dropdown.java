package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Dropdown {

    private SelenideElement dropdown = $x("//select[@id='dropdown']");

    public Dropdown() {
        Selenide.open("https://the-internet.herokuapp.com/dropdown");
        dropdown.should(visible, Duration.ofSeconds(30));
    }

    public Dropdown selectDropdownByNum(String num) {
        dropdown.selectOptionByValue(num);
        printCurrentElementText();
        return this;
    }
    private Dropdown printCurrentElementText(){
        System.out.println("Current element text: " + dropdown.getSelectedOption().getText());
        return this;
    }

}
