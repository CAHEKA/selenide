package widgets;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class NotificationMessage {

    private SelenideElement container = $x("//div[@class='example']");

    private By href = By.xpath(".//a[@href]");
    
    public NotificationMessage() {
        Selenide.open("https://the-internet.herokuapp.com/notification_message_rendered");
        container.should(visible, Duration.ofSeconds(30));
    }

    public NotificationMessage clickHere(){
        container.find(href).click();
       return this; 
    }

}
