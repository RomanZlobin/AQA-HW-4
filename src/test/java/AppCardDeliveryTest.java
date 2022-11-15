import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {


    @Test
    void shouldDeliveryCard() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, +4);
        SimpleDateFormat today = new SimpleDateFormat("dd.MM.yyyy");
        String expectedDate = today.format(calendar.getTime());

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] .input__control").setValue("Новосибирск");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(expectedDate);
        $("[data-test-id='name'] .input__control").setValue("Николай Римский-Корсаков");
        $("[data-test-id='phone'] .input__control").setValue("+79529991111");
        $("span[class='checkbox__box']").click();
        $("span[class='button__text']").click();
        $("div[class='notification__content']")
                .shouldBe(visible, Duration.ofSeconds(15));
    }

}
