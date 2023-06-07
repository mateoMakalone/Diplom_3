package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final By REGISTRATION_BUTTON = By.xpath(".//a[@class = 'Auth_link__1fOlj' and text() = 'Зарегистрироваться']");
    //Кнопка Регистрация страницы логина
    private static final By RECOVER_PASSWORD_BUTTON = By.xpath(".//a[text() = 'Восстановить пароль']");
    //Кнопка восстановить пароль страницы логина
    private static final By EMAIL_FIELD = By.xpath(".//input[@name='name']");
    //Поле заполнения email
    private static final By PASSWORD_FIELD = By.xpath(".//input[@name='Пароль']");
    //Поле заполнения пароля
    private static final By LOGIN_BUTTON = By.xpath(".//button[text() = 'Войти']");
    //Кнопка Войти формы логина
    private static final By ENTER_LOGO = By.xpath(".//div[@class = 'Auth_login__3hAey']");
    private final WebDriver driver;

    //Верстка формы логина
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Регистрация")
    public void clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Скролл до кнопки Регистрация")
    public void scrollToRegistrationButton() {
        WebElement element = driver.findElement(REGISTRATION_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Клик по полю email")
    public void clickEmailField() {
        driver.findElement(EMAIL_FIELD).click();
    }

    @Step("Заполнение поля email")
    public void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Клик по полю пароль")
    public void clickPasswordField() {
        driver.findElement(PASSWORD_FIELD).click();
    }

    @Step("Заполнение поля пароль")
    public void fillPasswordField(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void clickRecoveryPassButton() {
        driver.findElement(RECOVER_PASSWORD_BUTTON).click();
    }

    @Step("Заполнение полей формы логина, клик по кнопке Войти")
    public void fillLoginForm(String email, String password) {
        clickEmailField();
        fillEmailField(email);
        clickPasswordField();
        fillPasswordField(password);
        clickLoginButton();
    }

    @Step("Отображение верстки формы логина")
    public boolean isHeaderDisplayed() {
        WebElement enterLogo =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(ENTER_LOGO));
        return enterLogo.isDisplayed();
    }

}


