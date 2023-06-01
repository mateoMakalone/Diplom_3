package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPasswordPage {
    private WebDriver driver;
    public RecoveryPasswordPage(WebDriver driver){
        this.driver = driver;
    }
    private static final By LOGIN_BUTTON = By.xpath(".//a[text() = 'Войти']");
    //Кнопка "Войти"
    public void clickLoginButton(){
        driver.findElement(LOGIN_BUTTON).click();
    }

}
