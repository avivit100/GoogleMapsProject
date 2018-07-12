import com.aventstack.extentreports.Status;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class MainClass {
    WebDriver driverCH, driverIE, driverFF;

    @Parameters("browser")
    @BeforeClass
    /* Initialize - This method initialize the log file */
    public  void Initialize(String browser) {
        try {
            LogFile.OpenLogFile();
        } catch (Exception ex) {
            System.out.println("ERROR in Log File: " + ex.getMessage());
            return;
        }
    }

    @Parameters("browser")
    @Test
    /* OpenBrowser - This method opens the current browser according to the testNG.xml file */
    public void OpenBrowser(String browser){
        System.out.println("Now run "+browser);
        try {
            switch(browser) {
                case Constants.DRIVER_CHROME:
                    System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Installs\\Selenium\\chromedriver.exe");
                    driverCH = new ChromeDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    OpenSite(driverCH);
                    CloseSite(driverCH);
                    break;
                case Constants.DRIVER_IE:
                    System.setProperty("webdriver.ie.driver", "D:\\Automation\\Installs\\Selenium\\IEDriverServer.exe");
                    driverIE = new InternetExplorerDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    OpenSite(driverIE);
                    CloseSite(driverIE);
                    break;
                case Constants.DRIVER_FF:
                    System.setProperty("webdriver.gecko.driver", "D:\\Automation\\Installs\\Selenium\\geckodriver.exe");
                    driverFF = new FirefoxDriver();
                    LogFile.write(Status.INFO, "Running test in parallel, over browser: " + browser);
                    OpenSite(driverFF);
                    CloseSite(driverFF);
                    break;
            }
        }
        catch (NullPointerException ex) {
            LogFile.write(Status.FAIL,browser + " FAIL in Opening the browser: " + ex.getMessage());
        }
        catch (SessionNotCreatedException ex){
            LogFile.write(Status.FAIL,browser + " FAIL in Opening the browser: " + ex.getMessage());
        }
        catch (Exception ex) {
            LogFile.write(Status.FAIL,browser + " FAIL in Opening the browser: " + ex.getMessage());
        }
    }

    /* OpenSite - This method reads the details from the database, reads the coordinates from the API,
       then opens the web-site and search for the location */
    private void OpenSite(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ReadDatabase.GetDetailsFromDB();
        PlaceCoordinatesAPI.GetCoordinates(ReadDatabase.GetCriteria());
        SiteGoogleMaps.SearchCoordinates(driver, ReadDatabase.GetWebSite(), PlaceCoordinatesAPI.getValLAT(), PlaceCoordinatesAPI.getValLNG());
    }

    /* CloseSite - This method close the current browser */
    public void CloseSite(WebDriver driver){
        try {
            driver.quit();
            LogFile.write(Status.PASS, "driver is closed.");
        }
        catch (NullPointerException ex) {
            LogFile.write(Status.FAIL,"FAIL in closing the driver" + ex.getMessage());
        }

        catch (Exception ex) {
            LogFile.write(Status.FAIL, "FAIL in closing the driver" + ex.getMessage());
        }

        LogFile.CloseLog();
    }
}

