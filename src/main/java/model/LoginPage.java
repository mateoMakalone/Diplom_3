package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private static final By REGISTRATION_BUTTON = By.xpath(".//a[@class = 'Auth_link__1fOlj' and text() = 'Зарегистрироваться']");
    //Кнопка Регистрация страницы логина
    private static final By RECOVER_PASSWORD_BUTTON = By.xpath(".//a[text() = 'Восстановить пароль']");
    //Кнопка восстановить пароль страницы логина
    private static final By EMAIL_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div > input");
    //Поле заполнения эмейла
    private static final By ACTIVE_EMAIL_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div.input.pr-6.pl-6.input_type_text.input_size_default.input_status_active > input");
    //Выбранное поле эмейл
    private static final By PASSWORD_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div > input");
    //Поле заполнения пароля
    private static final By ACTIVE_PASSWORD_FIELD = By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div.input.pr-6.pl-6.input_type_password.input_size_default.input_status_active > input");
    //Выбранное поле пароль
    private static final By LOGIN_BUTTON = By.xpath(".//button[text() = 'Войти']");
    //Кнопка Войти формы логина
    private static final By ENTER_LOGO = By.xpath(".//div[@class = 'Auth_login__3hAey']");
    //Верстка формы логина
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickRegistrationButton(){
        driver.findElement(REGISTRATION_BUTTON).click();
    }
    public void scrollToRegistrationButton(){
        WebElement element = driver.findElement(REGISTRATION_BUTTON);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
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
    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }
    public void clickRecoveryPassButton(){
        driver.findElement(RECOVER_PASSWORD_BUTTON).click();
    }
    public void fillLoginForm(String email, String password){
        clickEmailField();
        fillEmailField(email);
        clickPasswordField();
        fillPasswordField(password);
        clickLoginButton();
    }
    public boolean isHeaderDisplayed(){
        WebElement enterLogo =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(ENTER_LOGO));
                return enterLogo.isDisplayed();
    }

}


