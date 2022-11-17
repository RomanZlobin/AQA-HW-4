import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    void shouldDeliveryCard() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Новосибирск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue("Николай Римский-Корсаков");
        $("[data-test-id='phone'] .input__control").setValue("+79529991111");
        $("span[class='checkbox__box']").click();
        $("span[class='button__text']").click();
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

}
