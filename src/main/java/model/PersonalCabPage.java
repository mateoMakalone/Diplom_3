package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalCabPage {
    private WebDriver driver;
    private static final By ACCOUNT_BACKGROUND = By.cssSelector("#root > div > main");
    //Верстка страницы личного кабинета (авторизованный пользователь)
    private static final By LOGOUT_BUTTON = By.cssSelector("#root > div > main > div > nav > ul > li:nth-child(3) > button");
    //Кнопка "Выйти"
    public PersonalCabPage(WebDriver driver){
        this.driver = driver;
    }
    public boolean isProfileDisplayed(){
        WebElement profile = new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(ACCOUNT_BACKGROUND));
        return profile.isDisplayed();
    }
    public void clickLogoutButton(){
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON));
        driver.findElement(LOGOUT_BUTTON).click();
    }

}
