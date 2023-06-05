package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalCabPage {
    private static final By ACCOUNT_BACKGROUND = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']");
    //Верстка страницы личного кабинета (авторизованный пользователь)
    private static final By LOGOUT_BUTTON = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive']");
    private final WebDriver driver;

    //Кнопка "Выйти"
    public PersonalCabPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка отображения верстки Личного Кабинета")
    public boolean isProfileDisplayed() {
        WebElement profile = new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(ACCOUNT_BACKGROUND));
        return profile.isDisplayed();
    }

    @Step("Клик по кнопке Выход")
    public void clickLogoutButton() {
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
    }

}
