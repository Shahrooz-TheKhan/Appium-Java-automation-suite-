package evenergyapp.ios;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDriveTheAppFlowTestsIOS {
    private static IOSDriver driver;

    @BeforeEach
    public void setupApp() throws InterruptedException, MalformedURLException {
        if (driver == null) {
            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("iOS");
            options.setAutomationName("XCUITest");
            options.setDeviceName("iPhone 13");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.app");
            options.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0");

            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        } else {
            driver.quit();
            XCUITestOptions options = new XCUITestOptions();
            options.setPlatformName("iOS");
            options.setAutomationName("XCUITest");
            options.setDeviceName("iPhone 13");
            options.setApp(System.getProperty("user.dir") + "/apps/universal.app");
            options.setCapability(MobileCapabilityType.PLATFORM_VERSION, "15.0");

            driver = new IOSDriver(new URL("http://127.0.0.1:4723"), options);
            Thread.sleep(4000);
        }
    }

    @AfterAll
    public static void closeIOSApp() {
        if (driver != null) {
            driver.quit();
        }
    }

    //... Tests with placeholders for iOS XPaths
}