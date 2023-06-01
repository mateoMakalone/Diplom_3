package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private static final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site/";
    //Адрес странницы
    private static final By LOGIN_BUTTON = By.xpath(".//button[text() = 'Войти в аккаунт']");
    //Кнопка "Войти в аккаунт"
    private static final By CONSTRUCTOR_MENU = By.xpath(".//h1[text() = 'Соберите бургер']");
    //Заголовок конструктора
    private static final By CONSTRUCTOR_BUTTON_ACTIVE = By.cssSelector("#root > div > header > nav > ul > li:nth-child(1) > a.AppHeader_header__link__3D_hX.AppHeader_header__link_active__1IkJo");
    //Выбранная кнопка конструктор хэдера страницы
    private static final By MAIN_PAGE = By.xpath(".//main[@class = 'App_componentContainer__2JC2W']");
    //Верстка главной страницы
    private static final By NON_SELECTED_BUN_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");
    //Неактивная кнопка "Булки" конструктора бургеров
    private static final By SELECTED_BUN_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Булки']");
    //Активная кнопка "Булки" конструктора бургеров
    private static final By SELECTED_SAUCE_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");
    //Активная кнопка "Соусы" конструктора бургеров
    private static final By NON_SELECTED_SAUCE_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Соусы']");
    //Неактивная кнопка "Соусы" конструктора бургеров
    private static final By NON_SELECTED_FILLING_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");
    //Неактивная кнопка "Начинки" конструктора бургеров
    private static final By SELECTED_FILLING_BUTTON = By.xpath(".//div[@class = 'tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[text() = 'Начинки']");
    //Активная кнопка "Начинки" конструктора бургеров
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    public void open(){
        driver.get(URL_MAIN_PAGE);
    }
    public void clickLoginButton(){
        new WebDriverWait(driver, 7).until(
                ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();
    }
    public void clickBunButton(){
        driver.findElement(NON_SELECTED_BUN_BUTTON).click();
    }
    public void clickSauceButton(){
        driver.findElement(NON_SELECTED_SAUCE_BUTTON).click();
    }
    public void clickFillingButton(){
        driver.findElement(NON_SELECTED_FILLING_BUTTON).click();
    }
    public boolean bunButtonActive(){
        WebElement bunButton =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(SELECTED_BUN_BUTTON));
        return bunButton.isDisplayed();
    }
    public boolean sauceButtonActive(){
        WebElement sauceButton =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(SELECTED_SAUCE_BUTTON));
        return sauceButton.isDisplayed();
    }
    public boolean fillingButtonActive(){
        WebElement fillingButton =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(SELECTED_FILLING_BUTTON));
        return fillingButton.isDisplayed();
    }
    public boolean constructorMenuDisplayed(){
        WebElement constructorMenu =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCTOR_MENU));
        return constructorMenu.isDisplayed();
    }
    public boolean constructorButtonActive(){
        WebElement isActive =
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(CONSTRUCTOR_BUTTON_ACTIVE));
        return isActive.isDisplayed();
    }
    public boolean mainPageDisplayed(){
        WebElement mainPage =
                new WebDriverWait(driver, 7)
                        .until(ExpectedConditions.visibilityOfElementLocated(MAIN_PAGE));
        return mainPage.isDisplayed();
    }
}
