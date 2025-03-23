package evenergyapp.android;

import evenergyapp.Setup.TestDataGenerator;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import org.openqa.selenium.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class loginPageTestAndroid {
    private static AndroidDriver driver;
    private static TestDataGenerator dataGenerator = new TestDataGenerator(); //Created a class in Setup folder to register with randomized data

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
    //This is to grab the latest valid login credentials
    public String[] readLatestCredentials(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                // the format is "FullName: [full name], Email: [email], Password: [password]"
                String[] parts = lastLine.split(", ");
                String email = parts[1].split(": ")[1];
                String password = parts[2].split(": ")[1];
                return new String[]{email, password};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"", ""};
    }


    @Test
    public void navigateToLoginPage() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(2000);
        //assertions to verify correctly redirected to login page
        WebElement nextStepElement =
                (WebElement) driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"])[1]"));
        assertNotNull(nextStepElement, "Failed to navigate to the next step after location permissions.");
        assertTrue(nextStepElement.isDisplayed(), "Next step element is not displayed.");

    }

    @Test
    public void enterNoPassword() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-email']"))
                .sendKeys("test@example.com");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-password']"))
                .sendKeys("");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id='button-login-submit']"))
                .click();
        Thread.sleep(2000);

        //Assertion to verify error message
        WebElement errorMsg = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Enter a password. It must be at least 8 characters.\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(errorMsg);

    }

    @Test
    public void enterInvalidEmail() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-email']"))
                .sendKeys("invalidemail");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-password']"))
                .sendKeys("password123");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id='button-login-submit']"))
                .click();
        Thread.sleep(2000);

        WebElement errorMsg = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Enter a valid email address.\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(errorMsg);
    }

    @Test
    public void invalidLoginCredentials() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-email']"))
                .sendKeys("user@example.com");
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-password']"))
                .sendKeys("wrongpassword");
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id='button-login-submit']"))
                .click();
        Thread.sleep(2000);

        WebElement errorMsg = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"We don’t recognise that email or password. Check details and try again.\" and @class=\"android.widget.TextView\"]"));
        assertNotNull(errorMsg);

    }

    @Test
    public void successfulLoginTest() throws InterruptedException {
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"]"))
                .click();
        Thread.sleep(3000);
        // Read the latest credentials
        String[] credentials = readLatestCredentials("src/test/testdata/signupData.txt");
        String email = credentials[0];
        String password = credentials[1];
        // Enter email and password from latest user in signup.txt for valid login
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-email']"))
                .sendKeys(email);
        driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='input-login-password']"))
                .sendKeys(password);
        driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@resource-id='button-login-submit']"))
                .click();
        Thread.sleep(7000);

        //Assertion for valid log in by checking that "Log in" header is missing
        //since users might land on a flow page if account set up is not complete.
        boolean isLoginTextPresent;
        try {
            driver.findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"Log in\" and @class=\"android.widget.TextView\"])[1]"));
            isLoginTextPresent = true;
        } catch (NoSuchElementException e) {
            isLoginTextPresent = false;
        }
        assertFalse(isLoginTextPresent, "Login was not successful. Still on the login screen.");

    }


}
