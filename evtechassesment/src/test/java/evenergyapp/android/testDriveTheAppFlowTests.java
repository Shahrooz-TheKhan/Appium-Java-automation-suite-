package evenergyapp.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class testDriveTheAppFlowTests {
    private static AndroidDriver driver;

    @BeforeEach
    public void setupApp() throws InterruptedException, MalformedURLException {
        if (driver == null) {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            options.setDeviceName("Pixel7");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        } else {
            // Reinitialize or reset the app as needed for each test
            driver.quit(); // Close the current app session
            // Reinitialize the driver to launch the app again
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
            options.setDeviceName("Pixel7");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        }
    }

    @AfterAll
    public static void closeAndroidApp() {
        if (driver != null) {
            driver.quit();
        }
    }
    private void tapByCoordinates(int x, int y) {
        new TouchAction<>(driver)
                .tap(PointOption.point(x, y))
                .perform();
    }

    @Test
    public void navigateToDemoApp() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);

        WebElement welcomeDash = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"text-modalTitle\"]"));
        assertNotNull(welcomeDash);

    }

    @Test
    public void dashboardTourPopUps() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);

        WebElement welcomeDash = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\" Charge history\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(welcomeDash);

    }

    @Test
    public void startChargeBoost() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"button-dashboard-boost\"]"))
                .click();
        Thread.sleep(1000);

        WebElement boostStarted = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Boost charging\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(boostStarted);

    }

    @Test
    public void endChargeBoost() throws InterruptedException{
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"button-dashboard-boost\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"button-dashboard-boost\"]"))
                .click();
        Thread.sleep(1000);

        WebElement boostStarted = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Smart charge planned\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(boostStarted);

    }

    @Test
    public void navigateToSmartCharge() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Smart\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();

        WebElement smartChargeDash = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Smart charging\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(smartChargeDash);


    }

    @Test
    public void viewFullSmartSchedule() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Smart\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Full schedule\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(1000);

        WebElement smartChargeTitle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Ready by times\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(smartChargeTitle);


    }

    @Test
    public void onlyChargeOffPeakSmartToggle() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Smart\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.Switch[@resource-id=\"switch-Only charge off-peak\"]"));

        WebElement toggleOn = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"We’ll plan your charge for low-demand hours with cheaper prices, according to your rate \uD83D\uDCB0\n" +
                "\n" +
                "Note: Some off-peak windows are too short for a 0-100% battery charge.\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(toggleOn);


    }

    @Test
    public void navigateToStats() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Stats\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();


        WebElement statsDash = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Statistics\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(statsDash);

    }

    @Test
    public void view24HourGraph() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Stats\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"24 hours\" and @class=\"android.widget.TextView\"]"))
                .click();


        WebElement twentyFourHourChart = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"75 kWh\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(twentyFourHourChart);

    }

    @Test
    public void viewOneYearGraph() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Stats\"]"))
                .click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Got it\" and @class=\"android.widget.TextView\"]"))
                .click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"1 year\" and @class=\"android.widget.TextView\"]"))
                .click();


        WebElement twentyFourHourChart = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"2798 kWh\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(twentyFourHourChart);

    }

    @Test
    public void navigateToRewards() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-openDemo\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-dashboard-tour\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"tab-bar-icon-Rewards\"]"))
                .click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-rewards-modalOverlay\"]"))
                .click();
        Thread.sleep(2000);

        WebElement rewardDash = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id=\"button-rewards-modalOverlay\"]"));
        assertNotNull(rewardDash);

    }


}
