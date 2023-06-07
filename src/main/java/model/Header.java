package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header {
    private static final By PERSONAL_CAB = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text()= 'Личный Кабинет']");
    //Кнопка личного кабинета хэдера
    private static final By LOGO = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    //Кнопка лого хэдера
    private static final By CONSTRUCTOR_BUTTON = By.xpath(".//p[@class = 'AppHeader_header__linkText__3q_va ml-2' and text() = 'Конструктор']");
    private final WebDriver driver;

    //Кнопка Конструктор хэдера
    public Header(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Личного кабинета")
    public void clickPesonalCabButton() {
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(PERSONAL_CAB));
        driver.findElement(PERSONAL_CAB).click();
    }

    @Step("Клик по логотипу сервиса")
    public void clickLogo() {
        driver.findElement(LOGO).click();
    }

    @Step("Клик по кнопке конструктор")
    public void clickConstructorButton() {
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }
}
