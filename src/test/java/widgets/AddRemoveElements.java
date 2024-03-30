package widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class AddRemoveElements {
    private final SelenideElement container = $x("//div[@class='example']");
    private final By addButton = By.xpath(".//button[@onclick='addElement()']");
    private final By deleteButton = By.xpath(".//button[@onclick='deleteElement()']");

    public AddRemoveElements() {
        Selenide.open("https://the-internet.herokuapp.com/add_remove_elements/");
        container.should(visible, Duration.ofSeconds(30));
    }

    public AddRemoveElements addElement() {
        container.find(addButton).click();
        System.out.println("Added an element. Current count: " + getAllElements().size());
        return this;
    }

    @Step("Add elements: {num}")
    public AddRemoveElements addElements(Integer num) {
        for (int i = 0; i < num; i++) {
            addElement();
            Assertions.assertEquals(getAllElements().size(), i + 1, "Expected " + (i + 1) + " elements, but found " + getAllElements().size());
        }
        return this;
    }

    @Step("Delete elements: {num}")
    public AddRemoveElements deleteElements(Integer num) {
        int firstState = getAllElements().size();
        Assertions.assertTrue(firstState >= num, "Cannot delete " + num + " elements. Only " + firstState + " elements exist.");
        for (int i = 0; i < num; i++) {
            deleteLastElement();
            Selenide.sleep(1000);
            Assertions.assertEquals(getAllElements().size(), firstState - (i + 1), "Expected " + (firstState - (i + 1)) + " elements after deletion, but found " + getAllElements().size());
        }
        return this;
    }

    public ElementsCollection getAllElements() {
        return container.findAll(deleteButton);
    }

    public AddRemoveElements deleteLastElement() {
        getAllElements().last().click();
        printToConsole();
        return this;
    }

    public void printToConsole() {
        System.out.println("Remaining number of Delete buttons: " + getAllElements().size());
        getAllElements().forEach(b -> System.out.println(b.getText()));
    }

    public Stream<DynamicTest> addAndDeleteElements(List<String> invalidInputs) {
        return invalidInputs.stream().map(number ->
                dynamicTest("Add and remove elements: " + number, () -> {
                    String[] value = number.split(":");
                    addElements(Integer.valueOf(value[0]));
                    deleteElements(Integer.valueOf(value[1]));
                    Selenide.refresh();
                })
        );
    }
}
