import com.aventstack.extentreports.Status;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteGoogleMaps {

    /* SearchCoordinates - This method get the web-site and the two coordinates,
       it opens the site and search in it, the location */
    public static void SearchCoordinates(WebDriver driver, String strWebSite, double dLat, double dLng){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            driver.get(strWebSite);
            LogFile.write(Status.PASS, "The site: " + strWebSite + " is opened.", driver);

            String strCoor = dLat + ", " + dLng;
            wait.until(ExpectedConditions.visibilityOfElementLocated(Constants.BOX_SEARCH));
            driver.findElement(Constants.BOX_SEARCH).sendKeys(strCoor);
            driver.findElement(Constants.BTN_SEARCH).click();
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

        //Check if the error message appears:
        try {
            if (driver.findElement(Constants.MSG_SEARCH).isDisplayed()) {
                LogFile.write(Status.ERROR, "Search coordinates has a mistake.", driver);
            }
        }
        catch(NoSuchElementException ex){ ///There is no error:
            LogFile.write(Status.PASS, "The coordinates are typed and searched.", driver);
        }
    }
}
