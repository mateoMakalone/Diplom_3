package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private static final By LOGIN_BUTTON = By.xpath(".//a[text() = 'Войти']");
    private final WebDriver driver;

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка "Войти"
    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

}
