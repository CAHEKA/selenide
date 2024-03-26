package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Inputs {

    private final SelenideElement inputField = $x("//div[@class='example']/input");

    public Inputs() {
        Selenide.open("https://the-internet.herokuapp.com/inputs");
        inputField.should(visible, Duration.ofSeconds(30));
    }

    public Inputs enterRandomNumber() {
        String randomNumber = String.valueOf(new Random().nextInt(10000) + 1);
        inputField.sendKeys(randomNumber);
        printCurrentValue();
        return this;
    }
    
    private Inputs printCurrentValue(){
        System.out.println("Entered number: " + inputField.getValue());
        return this;
    }
    
}