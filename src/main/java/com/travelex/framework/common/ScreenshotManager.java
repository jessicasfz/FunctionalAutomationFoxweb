package com.travelex.framework.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.log4testng.Logger;

/**
 * ScreenshotManager to take screenshots using logger class
 *
 */
public class ScreenshotManager {
    private static final Logger logger = Logger.getLogger( ScreenshotManager.class );

    public static void takeScreenshot( WebDriver driver, String filepath ) {
       
        WebDriver augmentedDriver = new Augmenter().augment( driver );
        File screenshot = ( (TakesScreenshot) augmentedDriver ).getScreenshotAs( OutputType.FILE );
       
        try {
            File destFile = new File( filepath );
            destFile.getParentFile().mkdirs();
            FileUtils.copyFile( screenshot, destFile );
            screenshot.delete(); // it will delete the previous screenshots
            logger.debug( "screenshot taken and stored at " + destFile.getAbsolutePath() );
        } catch ( IOException e ) {
            logger.error( e.getMessage(), e );
        }
    }

    /**
     * takeScreenshot to take screenshots by passing driver as parameter with
     * date and time
     *
     */
    public static void takeScreenshot( WebDriver driver ) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd-hhmmss-SSS" );
        String path = "screenshots/Test-" + sdf.format( cal.getTime() ) + ".jpg";
        takeScreenshot( driver, path );
    }
}