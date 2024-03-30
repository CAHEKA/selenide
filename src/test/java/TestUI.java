import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import widgets.*;

import java.awt.*;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Arrays.asList;


public class TestUI extends BaseTest {

    static Stream<Arguments> checkboxData() {
        return Stream.of(
                Arguments.of(Map.of("checkbox 1", true,
                        "checkbox 2", false)),
                Arguments.of(Map.of("checkbox 2", false,
                        "checkbox 1", true))
        );
    }

    @ParameterizedTest
    @MethodSource("checkboxData")
    public void testCheckbox(Map<String, Boolean> checkboxes) {
        checkboxes.forEach((name, state) ->
                new Checkboxes()
                        .selectCheckbox(name)
                        .checkStateCheckbox(name, state)
                        .refresh()
        );
    }


    @ParameterizedTest(name = "Line number {0} has the value: {1}")
    @CsvSource({
            "'1', 'Option 1'",
            "'2', 'Option 2'",
    })
    public void testDropdown(String num, String value) {
        new Dropdown()
                .selectDropdownByNum(num)
                .checkState(value);
    }

    @Test
    @RepeatedTest(value = 10, name = "Disappearing elements: {currentRepetition} out of {totalRepetitions}")
    public void testDisappearingElements() {
        new DisappearingElements()
                .checkNumberOfElements(5);
    }

    @TestFactory
    public Stream<DynamicTest> testInputs() {
        Inputs inputs = new Inputs();
        return Stream.concat(
                inputs.checkValidInputs(asList("-99", "-1", "0", "1", "9e+18")),
                inputs.checkInvalidInputs(asList("abc", "!@#", " 123", "456 ", "1q"))
        );
    }

    @ParameterizedTest(name = "Hovers with index {0} and text: {1}")
    @CsvSource({
            "0, 'name: user1'",
            "1, 'name: user2'",
            "2, 'name: user3'",
    })
    public void testHovers(Integer index, String text) {
        new Hovers()
                .highlightFigure(index).
                checkTextSelectedFigure(text);
    }

    @RepeatedTest(value = 10, name = "Notification message: {currentRepetition} of {totalRepetitions} ")
    public void testNotificationMessages() {
        new NotificationMessage()
                .clickHere()
                .checkForMessage("Action successful");
    }

    @TestFactory
    public Stream<DynamicTest> testAddRemoveElements() {
        return new AddRemoveElements()
                .addAndDeleteElements(asList("2:1", "5:2", "1:3"));
    }

    @ParameterizedTest(name = "StatusCodes: {0}")
    @CsvSource({
            "200",
            "301",
            "404",
            "500",
    })
    public void testStatusCodes(String code) {
        new StatusCodes()
                .clickOnStatusCode(code)
                .checkContainsUrl(code);
    }


}
