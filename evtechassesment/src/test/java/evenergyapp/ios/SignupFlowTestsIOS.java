package evenergyapp.ios;

import evenergyapp.Setup.TestDataGenerator;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SignupFlowTestsIOS {
    private static IOSDriver driver;
    private static TestDataGenerator dataGenerator = new TestDataGenerator();

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

    //...  tests with iOS XPaths
}
