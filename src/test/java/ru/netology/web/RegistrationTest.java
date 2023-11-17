package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {
    private String generateDate(int addDays, String pattern) {
return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldRegister() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(20))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
    @Test
    void shouldRedEmptyCity() {
        open("http://localhost:9999");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldRedWrongDate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub")
                .shouldHave(Condition.exactText("Неверно введена дата"));
    }
    @Test
    void shouldRedEmptyName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldRedEmptyPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub")
                .shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldRedWrongCity() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Тагил");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub")
                .shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }
    @Test
    void shouldRed3DayLessDate() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue("19.11.2023");
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub")
                .shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }
    @Test
    void shouldRedWrongName() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Nikita");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub")
                .shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldRedWrongPhone() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub")
                .shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldRedUnagreement() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Екатеринбург");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='name'] input").setValue("Никита");
        $("[data-test-id='phone'] input").setValue("+79200000000");
        $("button.button").click();
        $("[data-test-id='agreement']")
                .shouldNotBe(checked);
    }
}

