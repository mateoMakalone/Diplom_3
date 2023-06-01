package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class RegisterPage {
    private final WebDriver driver;
    private static final By LOGIN_BUTTON = By.xpath(".//a[text() = 'Войти']");
    //Кнопка "Войти"
    private static final By REGISTRATION_FIELD = By.xpath(".//div[@class = 'Auth_login__3hAey']");
    //Верстка формы регистрации
    private static final By NAME_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div");
    //Поле "Имя"
    private static final By ACTIVE_NAME_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div.input.pr-6.pl-6.input_type_text.input_size_default.input_status_active > input");
    //Выбранное поле "Имя"
    private static final By EMAIL_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div > input");
    //Поле "email"
    private static final By ACTIVE_EMAIL_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div.input.pr-6.pl-6.input_type_text.input_size_default.input_status_active > input");
    //Выбранное поле "email"
    private static final By PASSWORD_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > div > input");
    //Поле "Пароль"
    private static final By ACTIVE_PASSWORD_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > div.input.pr-6.pl-6.input_type_password.input_size_default.input_status_active > input:nth-child(2)");
    //Активное поле "Пароль"
    private static final By REGISTRATION_BUTTON = By.xpath(".//button[text() = 'Зарегистрироваться']");
    //Кнопка "Зарегистрироваться"
    private static final By INCORRECT_PASSWORD = By.xpath(".//p[@class = 'input__error text_type_main-default']");
    //Выденное ошибкой поле "Пароль"
    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }
    public void waitPageLoad(){
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(REGISTRATION_FIELD));
    }
    public void fillRegistrationPage(String name, String email,String password){
        clickNameField();
        fillNameField(name);
        clickEmailField();
        fillEmailField(email);
        clickPasswordField();
        fillPasswordField(password);
    }
    public void clickNameField(){
        driver.findElement(NAME_FIELD).click();
    }
    public void fillNameField(String name){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(ACTIVE_NAME_FIELD).sendKeys(name);
    }
    public void clickEmailField(){
        driver.findElement(EMAIL_FIELD).click();
    }
    public void fillEmailField(String email){
        driver.findElement(ACTIVE_EMAIL_FIELD).sendKeys(email);
    }
    public void clickPasswordField(){
        driver.findElement(PASSWORD_FIELD).click();
    }
    public void fillPasswordField(String password){
        driver.findElement(ACTIVE_PASSWORD_FIELD).sendKeys(password);
    }
    public void clickRegistrationButton(){
        driver.findElement(REGISTRATION_BUTTON).click();
    }
    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }
    public boolean isPasswordFieldhighlighted(){
        WebElement ishighlighted =
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD));
        return ishighlighted.isDisplayed();
    }
}
