package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    private static final By LOGIN_BUTTON = By.xpath(".//a[text() = 'Войти']");
    //Кнопка "Войти"
    private static final By REGISTRATION_FIELD = By.xpath(".//div[@class = 'Auth_login__3hAey']");
    //Верстка формы регистрации
    private static final By NAME_FIELD = By.xpath(".//label[text() = 'Имя']/../input");
    //Поле "Имя"
    private static final By EMAIL_FIELD = By.xpath(".//label[text() = 'Email']/../input");
    //Поле "email"
    private static final By PASSWORD_FIELD = By.xpath(".//label[text() = 'Пароль']/../input");
    //Поле "Пароль"
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //Кнопка "Зарегистрироваться"
    private static final By INCORRECT_PASSWORD = By.xpath(".//p[@class = 'input__error text_type_main-default']");
    private final WebDriver driver;

    //Выденное ошибкой поле "Пароль"
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы")
    public void waitPageLoad() {
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(REGISTRATION_FIELD));
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationPage(String name, String email, String password) {
        clickNameField();
        fillNameField(name);
        clickEmailField();
        fillEmailField(email);
        clickPasswordField();
        fillPasswordField(password);
    }

    @Step("Клик по полю Имя")
    public void clickNameField() {
        driver.findElement(NAME_FIELD).click();
    }

    @Step("Заполнение поля Имя")
    public void fillNameField(String name) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step("Клик по полю email")
    public void clickEmailField() {
        driver.findElement(EMAIL_FIELD).click();
    }

    @Step("Заполнение поля email")
    public void fillEmailField(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step("Клик по полю Пароль")
    public void clickPasswordField() {
        driver.findElement(PASSWORD_FIELD).click();
    }

    @Step("Заполнение поля Пароль")
    public void fillPasswordField(String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(REGISTRATION_BUTTON).click();
    }

    @Step("Клик по кнопке Войти")
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    @Step("Проверка отображения ошибки поля Пароль")
    public boolean isPasswordFieldhighlighted() {
        WebElement ishighlighted =
                new WebDriverWait(driver, 7).until(
                        ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD));
        return ishighlighted.isDisplayed();
    }
}
