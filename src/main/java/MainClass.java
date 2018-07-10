import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

//@FiMethodOrder(MethodSorters.NAME_ASCENDING)

public class MainClass {
    public static WebDriver driver;
    private static boolean bLog = false;
    private static boolean bDetails = false;
    private static boolean bSite = false;
    //Screens of the BuyMe site
    static SiteGoogleMaps siteGoogleMaps;

    @Parameters({"browser","runAll"})
    @BeforeClass
    public static void Initialize(String browser, String runAll) {
        try {
            //Open the log file:
            if(!bLog) {
                LogFile.OpenLogFile();
                bLog = true;
            }
        }
        catch(Exception ex){
            System.out.println("ERROR in Log File: " + ex.getMessage());
            return;
        }

        //OpenBrowser(String browser, String runAll){
        System.out.println("runAll "+runAll);
        try {
            switch(browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Installs\\Selenium\\chromedriver.exe");
                    driver = new ChromeDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    break;
                case "ie":
                    System.setProperty("webdriver.edge.driver", "D:\\Automation\\Installs\\Selenium\\MicrosoftWebDriver.exe");
                    driver = new EdgeDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "D:\\Automation\\Installs\\Selenium\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    break;
            }
            //Initialize the screens of the BuyMe site:
            siteGoogleMaps = PageFactory.initElements(driver, SiteGoogleMaps.class);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        catch (NullPointerException ex) {
            LogFile.write(Status.FAIL,"FAIL in Initialize: " + ex.getMessage());
        }

        catch (Exception ex) {
            LogFile.write(Status.FAIL, "FAIL in Initialize: " + ex.getMessage());
        }
    }

    @Test
    private void OpenSite() {
        //Read details from database & API:
        if (!bDetails) {
            ReadDatabase.GetDetailsFromDB();
            PlaceCoordinatesAPI.GetCoordinates(ReadDatabase.GetCriteria());
            bDetails = true;
        }

        //Open the site:
        if (!bSite) {
            siteGoogleMaps.SearchCoordinates(ReadDatabase.GetWebSite(), PlaceCoordinatesAPI.getValLAT(), PlaceCoordinatesAPI.getValLNG());
            bSite = true;
        }
    }

    @Parameters("browser")
    @AfterClass
    public void CloseSite(String browser){
        try {
            switch(browser) {
                case "chrome":
                    driver.quit();
                    LogFile.write(Status.PASS,"Driver of chrome is closed.");
                    break;
                case "ie":
                    driver.quit();
                    LogFile.write(Status.PASS,"Driver of ie is closed.");
                    break;
                case "firefox":
                    LogFile.write(Status.PASS,"Driver of firefox is closed.");
                   driver.quit();
                    break;
            }
        }
        catch (NullPointerException ex) {
            LogFile.write(Status.FAIL,"FAIL in closing the browser: " + ex.getMessage());
        }

        catch (Exception ex) {
            LogFile.write(Status.FAIL, "FAIL in closing the browser: " + ex.getMessage());
        }
        LogFile.CloseLog();

    }

}
