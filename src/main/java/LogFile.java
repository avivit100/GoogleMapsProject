import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;
import org.monte.media.math.Rational;
import org.monte.media.Format;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;
import java.awt.*;

public class LogFile {
    //Objects for the log file:
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest test;

    //Get current time for the log file name, and  the screen-shot file name:
    private static String timeStamp = new SimpleDateFormat("HHmmss_ddMMyyyy").format(Calendar.getInstance().getTime());

    //Get the Log file path from the config file:
    private static String strPath = "C:\\ProjectReport\\";

    //Counter for the print-Shot file name:
    private static int iCount = 0;

    //EXTRA record the automation test and add to the log file:
    private static ScreenRecorder screenRecorder;


    public static void OpenLogFile(){
        //the Log file name = Log path + the current date&time:
        String strLogFile = strPath + timeStamp + ".html";

        //Create the log file, and add definitions:
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(strLogFile);
        htmlReporter.setAppendExisting(true);
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Avivit Hubara");
        extent.setSystemInfo("Java version", "8, update 151");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Test type", "Sanity Test");
        test = extent.createTest("Test Search in Google Maps" , "The results of the automation test of Google Maps site");
        //test.log(Status.INFO, "Starts to run Automation Test.");
    }

    //write details to the log file::
    public static void write(Status status, String details){
        test.log(status, details);
    }

    //write details with screen-shot to the log file:
    public static void write(Status status, String details, WebDriver webPrintScreen){
        test.log(status, details);
        try {
            //screen-shot of the screen and add it to the log file:
            test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath
                    (takeScreenShot(webPrintScreen)).build());
        }
        catch(IOException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    private static String takeScreenShot(WebDriver driver) {
        //each screen-shot file name = Log path + current date&time + counter:
        iCount++;
        String strImgFile = strPath + "PrintShot\\" + timeStamp + "_" + iCount;

        //take the screen-shot:
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(strImgFile+".png");

        try {
            //locate the shot file under it's path and show in the log file:
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return strImgFile + ".png";
    }
    //Close and locate the log file under it's path:
    public static void CloseLog(){
        extent.flush();
    }

    //EXTRA start to record the test
    public static void EXTRA_startRecording()
    {
        try {
            GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            screenRecorder = new ScreenRecorder(gc,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey,
                            Rational.valueOf(30)), null);
            screenRecorder.start();
        }
        catch (Exception ex){
            write(Status.FAIL, "ERROR: in record test.\n" + ex.getMessage());
        }
    }

    //EXTRA stop to record the test
    public static void EXTRA_stopRecording() {
        try {
            screenRecorder.stop();
        }
        catch(Exception ex){
            write(Status.FAIL, "ERROR: in record test.\n" + ex.getMessage());
        }
    }

}
