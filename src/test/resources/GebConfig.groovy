import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

// Настройка драйверов
driver = {
    WebDriverManager.chromedriver().setup()
    ChromeOptions options = new ChromeOptions()
    options.addArguments("--headless")  // Для запуска браузера в фоновом режиме
    options.addArguments("--window-size=1920,1080")
    new ChromeDriver(options)
}

// Определяем различные окружения для тестирования
environments {
    // Для запуска в Chrome
    chrome {
        driver = {
            WebDriverManager.chromedriver().setup()
            ChromeOptions options = new ChromeOptions()
            options.addArguments("--window-size=1920,1080")
            new ChromeDriver(options)
        }
    }
    
    // Для запуска в Firefox
    firefox {
        driver = {
            WebDriverManager.firefoxdriver().setup()
            FirefoxOptions options = new FirefoxOptions()
            options.addArguments("--window-size=1920,1080")
            new FirefoxDriver(options)
        }
    }
    
    // Для запуска в Chrome в режиме Headless
    chromeHeadless {
        driver = {
            WebDriverManager.chromedriver().setup()
            ChromeOptions options = new ChromeOptions()
            options.addArguments("--headless")
            options.addArguments("--window-size=1920,1080")
            new ChromeDriver(options)
        }
    }
}

// Базовый URL для тестирования
baseUrl = "https://www.saucedemo.com"

// Таймауты
waiting {
    timeout = 10
    retryInterval = 0.5
}

// Директория для отчетов
reportsDir = 'build/geb-reports'