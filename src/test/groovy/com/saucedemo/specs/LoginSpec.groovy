package com.saucedemo.specs

import com.saucedemo.pages.LoginPage
import spock.lang.Narrative
import spock.lang.Title

/**
 * Тесты для страницы логина
 */
@Title("Тесты авторизации")
@Narrative("Проверка функциональности авторизации на сайте SauceDemo")
class LoginSpec extends BaseSpec {
    
    def "Успешная авторизация с верными учетными данными"() {
        given: "Открыта страница логина"
        LoginPage loginPage = new LoginPage(driver)
        loginPage.open()
        
        when: "Вводим корректные учетные данные и нажимаем кнопку логина"
        boolean loginSuccess = loginPage.login("standard_user", "secret_sauce")
        
        then: "Должны быть успешно авторизованы"
        loginSuccess
        driver.getCurrentUrl().contains("inventory.html")
    }
    
    def "Ошибка авторизации с неверным паролем"() {
        given: "Открыта страница логина"
        LoginPage loginPage = new LoginPage(driver)
        loginPage.open()
        
        when: "Вводим неверный пароль и нажимаем кнопку логина"
        boolean loginSuccess = loginPage.login("standard_user", "wrong_password")
        String errorText = loginPage.getErrorText()
        
        then: "Должны получить ошибку"
        !loginSuccess
        errorText != null
        errorText.contains("Username and password do not match")
    }
}