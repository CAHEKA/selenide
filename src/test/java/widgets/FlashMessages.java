package widgets;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;

public class FlashMessages {
    private SelenideElement container = $x("//div[@id='flash-messages']");
    private By text = By.xpath(".//div[text()]");

    public boolean contains(String messages) {
        container.isDisplayed();
        return container.find(text).getText().contains(messages);
    }
    
}
