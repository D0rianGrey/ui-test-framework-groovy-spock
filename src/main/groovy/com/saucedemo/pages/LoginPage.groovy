package com.saucedemo.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Page Object для страницы логина
 */
class LoginPage extends BasePage {
    
    static final Logger log = LoggerFactory.getLogger(LoginPage.class)
    
    @FindBy(id = "user-name")
    WebElement usernameInput
    
    @FindBy(id = "password")
    WebElement passwordInput
    
    @FindBy(id = "login-button")
    WebElement loginButton
    
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage
    
    LoginPage(WebDriver driver) {
        super(driver)
    }
    
    /**
     * Открытие страницы логина
     */
    void open() {
        log.info("Открытие страницы логина")
        driver.get("https://www.saucedemo.com")
        waitForPageLoad()
    }
    
    /**
     * Авторизация пользователя
     * @param username логин
     * @param password пароль
     * @return true при успешной авторизации, false при ошибке
     */
    boolean login(String username, String password) {
        log.info("Авторизация пользователя: ${username}")
        waitForVisibility(usernameInput)
        usernameInput.sendKeys(username)
        passwordInput.sendKeys(password)
        waitForClickability(loginButton).click()
        
        // Проверяем успешность авторизации
        try {
            return driver.getCurrentUrl().contains("inventory.html")
        } catch (Exception e) {
            return false
        }
    }
    
    /**
     * Получение текста ошибки
     * @return текст ошибки или null, если ошибки нет
     */
    String getErrorText() {
        try {
            return waitForVisibility(errorMessage).getText()
        } catch (Exception e) {
            return null
        }
    }
}