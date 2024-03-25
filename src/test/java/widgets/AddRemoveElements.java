package widgets;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

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
        System.out.println("Add elements: " + getAllElements().last().getText());
        return this;
    }

    public AddRemoveElements addElements(Integer num) {
        for (int i = 0; i < num; i++) {
            addElement();
        }
        return this;
    }

    public ElementsCollection getAllElements() {
        return container.findAll(deleteButton);
    }

    public AddRemoveElements deleteElementFromIndex(Integer i) {
        getAllElements().get(i).click();
        printToConsole();
        return this;
    }

    public void printToConsole() {
        System.out.println("Remaining number of Delete buttons: " + getAllElements().size());
        getAllElements().forEach(b -> System.out.println(b.getText()));
    }

}
