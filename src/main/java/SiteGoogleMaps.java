import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SiteGoogleMaps {
    public static WebDriver driver;

    public SiteGoogleMaps(WebDriver driver) {
        this.driver = driver;
    }



    public void SearchCoordinates(String strWebSite, double dLat, double dLng){
        try {
            driver.get(strWebSite);
            LogFile.write(Status.PASS, "The site: " + strWebSite + " is opened.", driver);

            String strCoor = dLat + ", " + dLng;

            driver.findElement(By.id("searchboxinput")).sendKeys(strCoor);
            driver.findElement(By.id("searchbox-searchbutton")).click();

            if (driver.findElement(By.className("section-bad-query-title")).getText().contains("can't find")) {
                LogFile.write(Status.ERROR, "Search coordinates has a mistake.", driver);
            }
            else{
                LogFile.write(Status.PASS, "The coordinates are typed and searched.", driver);
            }
        }
        catch(NullPointerException ex){
            LogFile.write(Status.FAIL, "FAIL in search coordinates: " +ex.getMessage());
        }
        catch(WebDriverException ex){
            LogFile.write(Status.FAIL, "FAIL in search coordinates: " +ex.getMessage());
        }
        catch(Exception ex){
            LogFile.write(Status.FAIL, "FAIL in search coordinates: " +ex.getMessage());
        }
    }

    public static void CloseSite(){
        LogFile.write(Status.INFO, "End of test.");
        driver.quit();
    }
}
