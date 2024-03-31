package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class Inputs {

    private final SelenideElement inputField = $x("//div[@class='example']/input");

    public Inputs() {
        Selenide.open("https://the-internet.herokuapp.com/inputs");
        inputField.should(visible, Duration.ofSeconds(30));
    }

    public Inputs enterNumber(String number) {
        inputField.sendKeys(number);
        printCurrentValue();
        return this;
    }

    public Inputs clearInput() {
        inputField.clear();
        return this;
    }

    private void printCurrentValue() {
        System.out.println("Entered number: " + inputField.getValue());
    }

    public Stream<DynamicTest> checkValidInputs(List<String> validInputs) {
        return validInputs.stream().map(number ->
                dynamicTest("Test valid input: " + number, () -> {
                    enterInput(number);
                    checkValidNumber(number);
                })
        );
    }

    public Stream<DynamicTest> checkInvalidInputs(List<String> invalidInputs) {
        return invalidInputs.stream().map(number ->
                dynamicTest("Test invalid input: " + number, () -> {
                    enterInput(number);
                    checkInvalidNumber(number);
                })
        );
    }

    @Step("Enter input: {number}")
    public void enterInput(String number) {
        clearInput();
        enterNumber(number);
    }
    
    @Step("Check valid number: {number}")
    public void checkValidNumber(String number) {
        Assertions.assertEquals(number, inputField.getValue(),
                "Expected input value to match " + number + ", but found " + inputField.getValue());
    }

    @Step("Check invalid number: {number}")
    public void checkInvalidNumber(String number) {
        Assertions.assertNotEquals(number, inputField.getValue(),
                "Expected input value to not match " + number + ", but found " + inputField.getValue());
    }

}