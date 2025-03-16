package com.saucedemo.specs

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

/**
 * Базовый класс для всех тестов
 */
abstract class BaseSpec extends Specification {
    
    protected static final Logger log = LoggerFactory.getLogger(BaseSpec.class)
    protected WebDriver driver
    
    /**
     * Метод, выполняемый перед каждым тестом
     */
    def setup() {
        log.info("Запуск теста: ${specificationContext.currentIteration.name}")
        setupDriver()
    }
    
    /**
     * Метод, выполняемый после каждого теста
     */
    def cleanup() {
        log.info("Завершение теста: ${specificationContext.currentIteration.name}")
        if (driver != null) {
            driver.quit()
        }
    }
    
    /**
     * Настройка драйвера
     */
    protected void setupDriver() {
        WebDriverManager.chromedriver().setup()
        ChromeOptions options = new ChromeOptions()
        // options.addArguments("--headless")  // Раскомментируйте для запуска в headless режиме
        options.addArguments("--window-size=1920,1080")
        driver = new ChromeDriver(options)
    }
}