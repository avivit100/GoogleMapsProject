package BuyMeSite;

import org.openqa.selenium.By;

public class Constants {
    //Config file path:
    public static final String CONFIG_FILE = "D:\\Automation\\Projects\\SeleniumProject\\configProject.XML";
    public static final String DRIVER_CHROME = "chrome";

    //User (String) details:
    public static final String MY_NAME = "abcd";
    public static final String MY_EMAIL = "abcd@yahoo.com";
    public static final String MY_PASSWORD = "abcdabcd";
    public static final String SEND_NAME = "Dear friend";
    public static final String SEND_BLESS = "Thank you for everything!";
    public static final String SEND_EMAIL = "avivit.hubara@gmail.com";

    //Registration screen - Elements names:
    public static final By BTN_ENTER = By.className("seperator-link");
    public static final By LINK_SUBSCRIBE = By.tagName("strong");
    public static final By ELEMS_DETAILS = By.className("input-theme");
    public static final By CHECK_REGISTER = By.xpath("//*[@for='register-consent']");
    public static final By CHECK_UPDATES = By.xpath("//*[@for='register-mailing']");
    public static final By BTN_SUBMIT = By.xpath("//*[@type='submit']");
    public static final By MSG_ERROR = By.className("login-error");
    public static final By LINK_USER = By.className("header-link");
    public static final By ERR_MSG = By.className("parsley-required");
    public static final String ASRT_ERR = "כל המתנות מחכות לך! אבל קודם צריך מייל וסיסמה";
    public static final By BTN_CLOSE = By.className("icon-times");

    //Home screen - Elements names:
    public static final By ELEMS_SELECT = By.className("active-result");
    public static final By DDOWN_PRICES = By.xpath("//span[.='סכום']");
    public static final By DDOWN_AREAS = By.xpath("//span[.='אזור']");
    public static final By DDOWN_CATEGORIES = By.xpath("//span[.='קטגוריה']");
    public static final By BTN_SEARCH = By.className("btn-primary");

    //Choose Gift screen - Elements names:
    public static final By PIC_PROMO = By.className("promo-item");
    public static final By TXT_PRICE = By.className("input-cash");
    public static final By DOWN_LOGO = By.className("buyme-footer-logo");
    public static final By UP_LOGO = By.className("site-logo");

    //Sender & Receiver Information screen - Elements names:
    public static final By CHECK_SOMEONE = By.className("for-friend-label");
    public static final By TXT_UP_FILE = By.name("fileUpload");
    public static final By CHECK_NOW = By.className("send-now");
    public static final By ICON_ENV = By.className("icon-envelope");
    public static final By TXT_EMAIL = By.className("input-mail");
    public static final By BTN_SAVE = By.className("btn-save");
    public static final String CLR_YELLOW = "rgba(247, 179, 54, 1)";
    public static final By ASRT_NAME = By.className("name");
    public static final By ASRT_BLESS = By.className("card-text");
    public static final By STEP_NAME = By.className("item-summary");
}


