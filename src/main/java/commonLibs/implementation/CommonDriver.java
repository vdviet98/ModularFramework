package commonLibs.implementation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class CommonDriver {

    private final WebDriver driver;

    private FluentWait wait;

    private int pageLoadTimeout;

    private int elementDetectionTimeout;

    public CommonDriver(String browserType) throws Exception {
        setPageLoadTimeout(30);
        setElementDetectionTimeout(30);

        String BROWSER_TYPE = browserType.toUpperCase().replace(" ","_");

        switch (BROWSER_TYPE) {
            case "CHROME" -> driver = new ChromeDriver();
            case "EDGE" -> driver = new EdgeDriver();
            case "FIREFOX" -> driver = new FirefoxDriver();
            default -> throw new Exception("Unknown BrowserType: " + browserType);
        }

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
    }

    public void navigateToUrl (String url){
        driver.manage().timeouts().pageLoadTimeout(Duration.of(pageLoadTimeout, ChronoUnit.SECONDS));
        wait.withTimeout(Duration.ofSeconds(elementDetectionTimeout));

        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void setElementDetectionTimeout(int elementDetectionTimeout) {
        this.elementDetectionTimeout = elementDetectionTimeout;
    }
}
