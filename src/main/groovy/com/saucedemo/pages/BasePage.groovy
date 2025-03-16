package com.saucedemo.pages

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.time.Duration

/**
 * Базовый класс для всех Page Objects
 */
abstract class BasePage {
    
    protected static final Logger log = LoggerFactory.getLogger(BasePage.class)
    protected WebDriver driver
    protected WebDriverWait wait
    
    BasePage(WebDriver driver) {
        this.driver = driver
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10))
        PageFactory.initElements(driver, this)
    }
    
    /**
     * Прокрутка страницы до элемента
     * @param element элемент до которого нужно прокрутить
     */
    void scrollToElement(WebElement element) {
        log.info("Прокрутка до элемента: ${element}")
        JavascriptExecutor js = (JavascriptExecutor) driver
        js.executeScript("arguments[0].scrollIntoView(true);", element)
    }
    
    /**
     * Ожидание загрузки страницы
     */
    void waitForPageLoad() {
        log.info("Ожидание загрузки страницы")
        wait.until { webDriver ->
            ((JavascriptExecutor) webDriver).executeScript("return document.readyState") == "complete"
        }
    }
    
    /**
     * Ожидание видимости элемента
     * @param element элемент
     * @return элемент после ожидания
     */
    WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element))
    }
    
    /**
     * Ожидание кликабельности элемента
     * @param element элемент
     * @return элемент после ожидания
     */
    WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element))
    }
    
    /**
     * Получение элемента по CSS селектору
     * @param selector CSS селектор
     * @return найденный элемент
     */
    WebElement getElementByCss(String selector) {
        log.info("Поиск элемента по CSS: ${selector}")
        return driver.findElement(By.cssSelector(selector))
    }
    
    /**
     * Получение элемента по XPath
     * @param xpath XPath
     * @return найденный элемент
     */
    WebElement getElementByXpath(String xpath) {
        log.info("Поиск элемента по XPath: ${xpath}")
        return driver.findElement(By.xpath(xpath))
    }
}