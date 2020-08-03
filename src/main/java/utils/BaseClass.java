package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public static Properties Pro;

    public static WebDriver getDriver() throws IOException {
        Pro = new Properties();
        FileInputStream fls = new FileInputStream("src\\test\\resources\\Objects\\object.properties");
        Pro.load(fls);
        System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;

    }

    public static void waitForPageToLoad() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void TearDown(){
        driver.close();
    }
}