import org.openqa.selenium.By;

public class Constants {
    //Log file path & Drivers:
    public static final String LOG_FILE = "C:\\ProjectReport\\";
    public static final String DRIVER_CHROME = "chrome";
    public static final String DRIVER_IE = "ie";
    public static final String DRIVER_FF = "firefox";

    //Database details:
    public static final String DB_OBJECT = "con.mysql.jdbc.Driver";
    public static final String DB_CONN = "jdbc:mysql://sql12.freemysqlhosting.net:3306";
    public static final String DB_USER = "sql12246863";
    public static final String DB_PASS = "YTbV1drZU1";
    public static final String DB_QUERY = "SELECT * FROM data.SiteDetails;";
    public static final String DB_SITE = "web_site";
    public static final String DB_CRITERIA = "criteria";

    //API details:
    public static final String API_GOOGLE = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public static final String API_KEY = "&key=AIzaSyDBmXR_y1SPt2EYc7YNtQHdN4RwjYVtXX4";
    public static final String API_RESULTS = "results";
    public static final String API_GEOMETRY = "geometry";
    public static final String API_LOCATION = "location";
    public static final String API_LAT = "lat";
    public static final String API_LNG = "lng";

    //Search in Google Maps page:
    public static final By BOX_SEARCH = By.id("searchboxinput");
    public static final By BTN_SEARCH = By.id("searchbox-searchbutton");
    public static final By MSG_SEARCH = By.className("section-bad-query-title");
}


